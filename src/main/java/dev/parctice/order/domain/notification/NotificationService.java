package dev.parctice.order.domain.notification;

/**
 * 공지를 위한 요구사항을 추상화한다.
 */
public interface NotificationService {

    void sendEmail(String email, String title, String description);
    void sendKakao(String phoneNo, String description);
    void sendSms(String phoneNo, String description);
}
