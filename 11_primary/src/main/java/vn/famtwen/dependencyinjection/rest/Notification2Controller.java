package vn.famtwen.dependencyinjection.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.famtwen.dependencyinjection.service.MessageService;

@RestController
public class Notification2Controller {
  private MessageService emailService;
  private MessageService zaloService;
  private MessageService smsService;
  @Autowired
  public void Notification2Controller(@Qualifier("emailService") MessageService emailService,
                                      @Qualifier("zaloService") MessageService zaloService,
                                      @Qualifier("smsService") MessageService smsService){
    this.emailService = emailService;
    this.zaloService = zaloService;
    this.smsService = smsService;
  }

  @GetMapping("/email")
  public String sendEmail()
  {
    return this.emailService.sendMessage();
  }

  @GetMapping("/sms")
  public String sendSMS()
  {
    return this.smsService.sendMessage();
  }

  @GetMapping("/zalo")
  public String sendZalo()
  {
    return this.zaloService.sendMessage();
  }
}