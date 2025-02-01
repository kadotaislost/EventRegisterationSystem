package com.example.event_registration_system.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity // Marks this class as an entity, meaning it will be mapped to a database table.
@Table(name = "registrations")  // This tells Spring Boot that this entity is mapped to the "registrations" table.
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generates the ID for each new record.
    private Long id;


    @Column(name = "full_name")  // Maps to the "full_name" column in the table
    @NotBlank(message = "Full name is required")  // Ensures that the full name is not blank.
    private String fullName;


    @Column(name = "email", unique = true)  // Maps to the "email" column and makes it unique
    @NotBlank(message = "Email is required")  // Ensures that the email is not blank.
    @Email(message = "Invalid email address")  // Ensures that the email is a valid email address.
    private String email;

    @Column(name = "phone", unique = true)  // Maps to the "phone" column and makes it unique
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10,}", message = "Phone number must be at least 10 digits")
    private String phone;

    @Column(name = "photo_path")  // Maps to the "photo_path" column in the table
    @NotEmpty(message = "Photo path is required") // Allows spaces but not empty
    private String photoPath;

    // Getters and Setters (for Spring Data JPA to access and set values)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
