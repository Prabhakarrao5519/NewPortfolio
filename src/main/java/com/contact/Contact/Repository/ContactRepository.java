package com.contact.Contact.Repository;

import com.contact.Contact.Entity.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ContactRepository extends JpaRepository<ContactForm, Long> {
}
