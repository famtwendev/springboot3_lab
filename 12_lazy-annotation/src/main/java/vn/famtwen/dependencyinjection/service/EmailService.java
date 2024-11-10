package vn.famtwen.dependencyinjection.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class EmailService implements MessageService {
    public EmailService()
    {
        System.out.println("Constructor của lớp : " + getClass().getSimpleName());
    }

    @Override
    public String sendMessage() {
        return "Email sending ..... ";
    }
}
