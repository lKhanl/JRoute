package dev.khan.routing;

import io.undertow.server.HttpServerExchange;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class RouteDefinition {

    private static final RouteDefinition INSTANCE = new RouteDefinition();

    private final Map<String, Consumer<HttpServerExchange>> getRoutes = new HashMap<>();
    private final Map<String, Consumer<HttpServerExchange>> postRoutes = new HashMap<>();
    private final Map<String, Consumer<HttpServerExchange>> putRoutes = new HashMap<>();
    private final Map<String, Consumer<HttpServerExchange>> deleteRoutes = new HashMap<>();

    private RouteDefinition() {
    }

    public static RouteDefinition getInstance() {
        return INSTANCE;
    }

    void get(String path, Consumer<HttpServerExchange> handler) {
        getRoutes.put(path, handler);
    }

    void post(String path, Consumer<HttpServerExchange> handler) {
        postRoutes.put(path, handler);
    }

    void put(String path, Consumer<HttpServerExchange> handler) {
        putRoutes.put(path, handler);
    }

    void delete(String path, Consumer<HttpServerExchange> handler) {
        deleteRoutes.put(path, handler);
    }

    Map<String, Consumer<HttpServerExchange>> getGetRoutes() {
        return getRoutes;
    }

    Map<String, Consumer<HttpServerExchange>> getPostRoutes() {
        return postRoutes;
    }

    Map<String, Consumer<HttpServerExchange>> getPutRoutes() {
        return putRoutes;
    }

    Map<String, Consumer<HttpServerExchange>> getDeleteRoutes() {
        return deleteRoutes;
    }

}