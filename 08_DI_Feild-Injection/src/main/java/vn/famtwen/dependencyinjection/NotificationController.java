package vn.famtwen.dependencyinjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {


    /*Field Injection */
    @Autowired
    private MessageInterface email;
    //


    /*Constructor Injection*/
//    @Autowired
//    public NotificationController(EmailService email)
//    {
//        this.email = email;
//    }

    /*Setter Injection*/
//    @Autowired
//    public void SetEmail(EmailService email)
//    {
//        this.email = email;
//    }


    @GetMapping("/send-email")
    public String sendEmail(){
        return this.email.sendMessage();
    }
}
