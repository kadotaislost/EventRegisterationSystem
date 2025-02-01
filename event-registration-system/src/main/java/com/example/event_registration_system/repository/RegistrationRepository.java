package com.example.event_registration_system.repository;
import com.example.event_registration_system.model.Registration;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    // This interface extends the JpaRepository interface, which provides CRUD operations for the Registration entity.
    // The Registration entity is the type of entity that this repository manages, and Long is the type of the entity's ID.
    // This interface does not need to define any methods because JpaRepository provides all the necessary CRUD operations.
}
