package vn.titv.spring.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
    @GetMapping("/")
    public String index()
    {
        return "Hello Fam Twen";
    }
@GetMapping("/h2")
    public String h2()
    {
        return "H2";
    }
}
