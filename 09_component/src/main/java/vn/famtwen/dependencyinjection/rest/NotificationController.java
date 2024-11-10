package vn.famtwen.dependencyinjection.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.famtwen.dependencyinjection.service.EmailService;
import vn.famtwen.dependencyinjection.service.MessageInterface;

@RestController
public class NotificationController {

    private MessageInterface email;

    /*Constructor Injection*/
    @Autowired
    public NotificationController(EmailService email)
    {
        this.email = email;
    }

    @GetMapping("/send-email")
    public String sendEmail(){
        return this.email.sendMessage();
    }
}
