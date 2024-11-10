package vn.famtwen.dependencyinjection.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.famtwen.dependencyinjection.service.EmailService;
import vn.famtwen.dependencyinjection.service.MessageService;

@RestController
public class NotificationController {

    private MessageService service;

    /*Constructor Injection*/
    @Autowired
    public NotificationController(MessageService service) {
        this.service = service;
    }

    @GetMapping("/send-email")
    public String sendEmail() {
        return this.service.sendMessage();
    }
}
