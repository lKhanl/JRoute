package dev.khan.routing;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Methods;

import java.util.function.Consumer;

public class Router implements HttpHandler {

    @Override
    public void handleRequest(HttpServerExchange exchange) {
        if (exchange.isInIoThread()) {
            exchange.dispatch(this);
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

}