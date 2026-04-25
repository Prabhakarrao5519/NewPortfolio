package com.contact.Contact.Controller;

import com.contact.Contact.Entity.ContactForm;
import com.contact.Contact.Services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Frontend ko connect karne ke liye allow karega
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/contact")
    public ResponseEntity<String> submitForm(@RequestBody ContactForm form) {
        try {
            contactService.processContactForm(form);
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error submitting form");
        }
    }
}
