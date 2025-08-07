package dev.khan.routing;

import dev.khan.annotations.Controller;
import dev.khan.annotations.DELETE;
import dev.khan.annotations.GET;
import dev.khan.annotations.POST;
import dev.khan.annotations.PUT;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class RouteScanner {

    public void scanAndRegisterRoutes(String basePackage) {
        RouteDefinition routeDefinition = RouteDefinition.getInstance();

        Reflections reflections = new Reflections(basePackage);
        Set<Class<?>> controllers = reflections.getTypesAnnotatedWith(Controller.class);

        for (Class<?> controllerClass : controllers) {
            try {
                Object controller = controllerClass.getDeclaredConstructor().newInstance();

                for (Method method : controllerClass.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(GET.class)) {
                        String path = method.getAnnotation(GET.class).value();
                        routeDefinition.get(path, RouteInvoker.create(controller, method));
                    }
                    if (method.isAnnotationPresent(POST.class)) {
                        String path = method.getAnnotation(POST.class).value();
                        routeDefinition.post(path, RouteInvoker.create(controller, method));
                    }
                    if (method.isAnnotationPresent(PUT.class)) {
                        String path = method.getAnnotation(PUT.class).value();
                        routeDefinition.put(path, RouteInvoker.create(controller, method));
                    }
                    if (method.isAnnotationPresent(DELETE.class)) {
                        String path = method.getAnnotation(DELETE.class).value();
                        routeDefinition.delete(path, RouteInvoker.create(controller, method));
                    }
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

//    private void invoke(Object controller, Method method, HttpServerExchange exchange) {
//        try {
//            method.invoke(controller, exchange);
//        } catch (Exception e) {
//            exchange.setStatusCode(500);
//            exchange.getResponseSender().send("Internal Server Error");
//            e.printStackTrace();
//        }
//    }

}