package com.contact.Contact.Services;

import com.contact.Contact.Entity.ContactForm;
import com.contact.Contact.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    @Autowired
    private JavaMailSender mailSender;

    public void processContactForm(ContactForm form) {
        // 1. Save data to the Database
        repository.save(form);

        // 2. Get current Date and Time (for notification display)
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
        String formattedDateTime = now.format(formatter);

        // 3. Set Email Notification Format
        SimpleMailMessage mail = new SimpleMailMessage();

        // THE EMAIL ADDRESS WHERE YOU WANT TO RECEIVE NOTIFICATIONS
        mail.setTo("prabhakaryadav5519@gmail.com");

        // Subject (Added an alert emoji for a notification feel)
        mail.setSubject("🚨 New Notification: Form Submitted by " + form.getName());

        // Body (Proper structured format in English)
        String emailBody = "Hello Admin,\n\n"
                + "A new contact form has been submitted on your website. The details are as follows:\n\n"
                + "-------------------------------------------------\n"
                + "👤 Name      : " + form.getName() + "\n"
                + "📧 Email ID  : " + form.getEmail() + "\n"
                + "📅 Date/Time : " + formattedDateTime + "\n"
                + "-------------------------------------------------\n\n"
                + "📝 Message / Feedback :\n"
                + form.getMessage() + "\n\n"
                + "-------------------------------------------------\n"
                + "❤️";

        mail.setText(emailBody);

        // 4. Send the Mail
        mailSender.send(mail);
    }
}