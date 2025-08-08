# JRoute

jroute — a **lightweight**, **dependency-free**, and **easy-to-use** HTTP framework written in Java.  
Its goal is to let you build web servers with minimal code and maximum control.

## ✨ Features
- **Zero dependencies** — runs on plain Java SE.
- **Simple routing** — easily define HTTP methods like `GET` and `POST`.
- **Minimal API** — easy to learn and use.

## 🚀 Getting Started

### Maven Dependency
```xml
<dependency>
    <groupId>dev.khan</groupId>
    <artifactId>jroute</artifactId>
    <version>0.0.1</version>
</dependency>
```

### Simple Example

```java
@Controller
public class MyController {
    
    @GET("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @POST("hello")
    public String echo(@RequestBody String body) {
        return "Echo: " + body;
    }
}

public class Main {
    public static void main(String[] args) {
        JRouteApplication.run(Main.class);
    }
}
```

## 📌 Roadmap
- [ ] Path variable support (`/users/:id`)
- [ ] Request parameter support (`?key=value`)
- [ ] Config support (server-level configuration options)
- [ ] Custom JSON serialization & deserialization support (plug-in your own JSON library)
- [ ] Middleware support (run code before/after handling requests)
- [ ] File upload/download support
- [ ] CORS support (cross-origin resource sharing)
- [ ] Authentication & authorization support (basic auth, JWT, etc.)
- [ ] Rate limiting support (prevent abuse)
- [ ] Logging support (request/response logging)
- [ ] Error handling support (custom error pages)
- [ ] Dependency injection support

## 🛠️ Contributing

Contributions are welcome! If you'd like to contribute to JRoute, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bugfix (git checkout -b feature-name)
3. Commit your changes (git commit -m "Add some feature")
4. Push to your branch (git push origin feature-name)
5. Open a pull request describing your changes

Please make sure your code follows the existing style and includes tests if applicable.
