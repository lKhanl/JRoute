package dev.khan.server;

import dev.khan.routing.Router;
import io.undertow.Undertow;

public class HttpServer {

    private final int port;
    private final Router router = new Router();

    public HttpServer(int port) {
        this.port = port;
    }

    public void start() {
        Undertow server = Undertow.builder()
                .addHttpListener(port, "0.0.0.0")
                .setHandler(router)
                .build();

        server.start();
        System.out.println("Undertow server started on port " + port);
    }

}