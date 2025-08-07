package dev.khan.routing;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.khan.annotations.RequestBody;
import dev.khan.util.JsonUtils;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.function.Consumer;

public class RouteInvoker {

    public static Consumer<HttpServerExchange> create(Object controller, Method method) {
        return exchange -> {
            try {
                ObjectMapper mapper = JsonUtils.getInstance();

                Class<?>[] paramTypes = method.getParameterTypes();
                Annotation[][] paramAnnotations = method.getParameterAnnotations();
                Object result;

                if (paramTypes.length == 0) {
                    result = method.invoke(controller);
                } else {
                    Object[] args = new Object[paramTypes.length];
                    for (int i = 0; i < paramTypes.length; i++) {
                        boolean isRequestBody = false;
                        for (Annotation annotation : paramAnnotations[i]) {
                            if (annotation.annotationType().equals(RequestBody.class)) {
                                isRequestBody = true;
                                break;
                            }
                        }
                        if (isRequestBody) {
                            exchange.startBlocking();
                            InputStream bodyStream = exchange.getInputStream();
                            args[i] = mapper.readValue(bodyStream, paramTypes[i]);
                        } else {
                            args[i] = null;
                        }
                    }
                    result = method.invoke(controller, args);
                }

                String json = mapper.writeValueAsString(result);
                exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
                exchange.getResponseSender().send(json);

            } catch (Exception e) {
                exchange.setStatusCode(500);
                exchange.getResponseSender().send("Internal Server Error\n" + e.getMessage());
                e.printStackTrace();
            }
        };
    }

}