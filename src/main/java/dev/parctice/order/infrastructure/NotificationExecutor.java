package dev.parctice.order.infrastructure;

import dev.parctice.order.domain.notification.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

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

    @Override
    public void sendEmail(String email, String title, String description) {
        log.info("sendEmail");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setFrom(FROM_ADDRESS);
        message.setSubject(title);
        message.setText(description);

        mailSender.send(message);
    }

    @Override
    public void sendKakao(String phoneNo, String description) {
        log.info("sendKakao");
    }

    @Override
    public void sendSms(String phoneNo, String description) {
        log.info("sendSms");
    }
}
