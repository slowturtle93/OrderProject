package dev.parctice.order.infrastructure;

import dev.parctice.order.domain.notification.NotificationService;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 공지 요구사항에 대해 실제 구현체를 담당한다.
 */
@Slf4j
@Component
public class NotificationExecutor implements NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String FROM_ADDRESS;

    @Value("${example.sms.apikey}")
    private String smsApiKey;

    @Value("${example.sms.apisecret}")
    private String smsApiSecretKey;

    @Value("${example.sms.phone}")
    private String smsToPhone;

    @Override
    public void sendEmail(String email, String title, String description) {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setFrom(FROM_ADDRESS);
            message.setSubject(title);
            message.setText(description);

            mailSender.send(message);
            log.info("sendEmail success");
        }catch (Exception e){
            log.info("sendEmail fail");
        }
    }

    @Override
    public void sendKakao(String phoneNo, String description) {
        log.info("sendKakao");
    }

    @Override
    public void sendSms(String phoneNo, String description) {

        try{

            Message coolSms = new Message(smsApiKey, smsApiSecretKey);
            HashMap<String, String> params = new HashMap<String, String>();

            params.put("to", smsToPhone);
            params.put("from", phoneNo);
            params.put("type", "SMS");
            params.put("text", description);
            params.put("app_version", "test app 1.2");

            JSONObject obj = (JSONObject) coolSms.send(params);

            log.info("sendSms Success : " + obj.toString());
        } catch (CoolsmsException e){
            log.info("sendSms Fail");
        }
    }
}
