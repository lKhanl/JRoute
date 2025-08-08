package dev.khan.controllers;

import dev.khan.annotations.Controller;
import dev.khan.annotations.GET;
import dev.khan.annotations.POST;
import dev.khan.annotations.RequestBody;

@Controller
public class HelloController {

    @GET("/slow")
    public void hello() {

        try {
            Thread.sleep(1000); // 1 saniye bekle
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    @POST("/hello")
    public User greet(@RequestBody User user) {
        user.name = "Hello " + user.name;
        return user;
    }

    public static class User {
        public String name;

        public User() {
        }

        public User(String name) {
            this.name = name;
        }
    }

}