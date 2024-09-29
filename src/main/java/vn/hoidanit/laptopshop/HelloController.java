package vn.hoidanit.laptopshop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String index() {
        return "Hello world update";
    }

    @GetMapping("/user")
    public String user() {
        return "Hello i am user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello i am admin";
    }
}
