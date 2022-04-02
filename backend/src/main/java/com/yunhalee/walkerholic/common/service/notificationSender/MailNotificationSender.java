package com.yunhalee.walkerholic.common.service.notificationSender;

import com.yunhalee.walkerholic.order.domain.Order;
import com.yunhalee.walkerholic.user.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailNotificationSender implements NotificationSender {

    private JavaMailSender mailSender;

    private String sender;

    private String baseUrl;

    public MailNotificationSender(JavaMailSender mailSender, @Value("${spring.mail.username}") String sender, @Value("${base-url}") String baseUrl) {
        this.mailSender = mailSender;
        this.sender = sender;
        this.baseUrl = baseUrl;
    }

    @Override
    public void sendCreateOrderNotification(Order order, User user) {
        sendMail(user.getEmail(),
            user.getFullname() + " : Created Order " + order.getId(),
            "Hello" + user.getFirstname() + "! Your order has been made successfully. " +
                "\n\nOrder Id :  " + order.getId() +
                "\nTotal Amount : " + order.getTotalAmount() +
                "\nPaid At : " + order.getPaidAt() +
                "\n\nFor more Details visit " + baseUrl + "/order/" + order.getId());
    }

    @Override
    public void sendCancelOrderNotification(Order order, User user) {
        sendMail(user.getEmail(),
            user.getFullname() + " : Cancel Order " + order.getId(),
            "Hello" + user.getFirstname() + "! Your order has been canceled successfully. " +
                "\n\nOrder Id :  " + order.getId() +
                "\nTotal Amount : " + order.getTotalAmount() +
                "\nCanceled At : " + order.getUpdatedAt() +
                "\n\nFor more Details visit " + baseUrl + "/order/" + order.getId());
    }

    private void sendMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(sender);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
