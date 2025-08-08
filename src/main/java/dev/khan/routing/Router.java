package dev.khan.routing;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderValues;
import io.undertow.util.Headers;
import io.undertow.util.Methods;

import java.util.function.Consumer;

public class Router implements HttpHandler {

    @Override
    public void handleRequest(HttpServerExchange exchange) {
        if (exchange.isInIoThread()) {
            exchange.dispatch(this);
            return;
        }

        if (exchange.getRequestHeaders().contains(Headers.CONTENT_TYPE)) {
            HeaderValues strings = exchange.getResponseHeaders().get(Headers.CONTENT_TYPE);
            if (strings != null && !strings.isEmpty()) {
                String contentType = strings.getFirst();
                if (!contentType.equals("application/json")) {
                    returnUnsupportedMediaType(exchange);
                    return;
                }
            }
        } else {
            returnUnsupportedMediaType(exchange);
            return;
        }

        RouteDefinition routeDefinition = RouteDefinition.getInstance();

        String method = exchange.getRequestMethod().toString();
        String path = exchange.getRequestPath();

        Consumer<HttpServerExchange> handler = switch (method) {
            case Methods.GET_STRING -> routeDefinition.getGetRoutes().get(path);
            case Methods.POST_STRING -> routeDefinition.getPostRoutes().get(path);
            case Methods.PUT_STRING -> routeDefinition.getPutRoutes().get(path);
            case Methods.DELETE_STRING -> routeDefinition.getDeleteRoutes().get(path);
            default -> null;
        };

        if (handler != null) {
            handler.accept(exchange);
        } else {
            exchange.setStatusCode(404);
            exchange.getResponseSender().send("404 Not Found");
        }
    }

    private void returnUnsupportedMediaType(HttpServerExchange exchange) {
        exchange.setStatusCode(415);
        exchange.getResponseSender().send("415 Unsupported Media Type");
    }

}