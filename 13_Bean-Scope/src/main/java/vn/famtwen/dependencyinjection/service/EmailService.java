package vn.famtwen.dependencyinjection.service;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationPropertiesBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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
