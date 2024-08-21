package com.reader.scanner.service;

import com.reader.scanner.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendRegistrationEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom(fromEmail);
        mailSender.send(message);
    }

    public void sendCartDetailsEmail(String toEmail, String username, List<CartItem> items, double totalPrice) {
        StringBuilder body = new StringBuilder();
        body.append("Dear ").append(username).append(",\n\n");
        body.append("Here is the summary of your cart:\n\n");

        for (CartItem item : items) {
            body.append("Product: ").append(item.getName())
                    .append(", Price: ").append(item.getPrice())
                    .append(", Weight: ").append(item.getWeight())
//                    .append(", Quantity: ").append(item.getQuantity())
                    .append("\n");
        }

        body.append("\nTotal Price: ").append(totalPrice).append("\n\n");
        body.append("Thank you for shopping with us!\n\n");
        body.append("Regards,\nYour Company");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your Cart Details");
        message.setText(body.toString());
        message.setFrom(fromEmail);
        mailSender.send(message);
    }
}
