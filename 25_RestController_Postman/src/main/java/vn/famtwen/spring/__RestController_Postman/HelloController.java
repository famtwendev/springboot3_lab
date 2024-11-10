package vn.famtwen.spring.__RestController_Postman;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

    @GetMapping("/hello/vietnamese")
    public String hello2(){
        return "Xin chào!";
    }


    @GetMapping("/myipaddress")
    public Map<String, Object> myIpAddress() {
        Map<String, Object> response = new HashMap<>();
        response.put("ip", "14.167.167.200");
        response.put("hostname", "static.vnpt.vn");
        response.put("city", "Thủ Dầu Một");
        response.put("region", "Bình Dương Province");
        response.put("country", "VN");
        response.put("loc", "10.9804,106.6519");
        response.put("org", "AS45899 VNPT Corp");
        response.put("postal", "75150");
        response.put("timezone", "Asia/Ho_Chi_Minh");
        response.put("readme", "https://ipinfo.io/missingauth");
        return response;
    }
}
