package dev.khan;

import dev.khan.routing.RouteScanner;
import dev.khan.server.HttpServer;

public class JRouteApplication {

    public static void run(Class<?> entryPoint) {
        RouteScanner routeScanner = new RouteScanner();
        routeScanner.scanAndRegisterRoutes(entryPoint.getPackageName());

        HttpServer server = new HttpServer(8080);
        server.start();
    }

}
