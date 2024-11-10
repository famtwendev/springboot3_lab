package vn.famtwen.dependencyinjection.service;

import org.springframework.stereotype.Component;

@Component
public class ZaloService implements MessageService{

    @Override
    public String sendMessage() {
        return "Zalo sending ...";
    }
}
