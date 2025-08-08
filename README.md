# JRoute

jroute — a **lightweight**, **dependency-free**, and **easy-to-use** HTTP framework written in Java.  
Its goal is to let you build web servers with minimal code and maximum control.

---

## ✨ Features
- **Zero dependencies** — runs on plain Java SE.
- **Simple routing** — easily define HTTP methods like `GET` and `POST`.
- **Minimal API** — easy to learn and use.

---

## 🚀 Usage

### Simple Example

```java
import com.github.jroute.JRoute;
import com.github.jroute.annotations.GET;
import com.github.jroute.annotations.POST;
import com.github.jroute.annotations.Path;
import com.github.jroute.annotations.RequestBody;
import com.github.jroute.annotations.ResponseBody;
import com.github.jroute.annotations.RestController;
import com.github.jroute.http.HttpRequest;
import com.github.jroute.http.HttpResponse;
import dev.khan.annotations.Controller;

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