package com.example.event_registration_system.controller;

import com.example.event_registration_system.model.Registration;
import com.example.event_registration_system.service.RegistrationService;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;



@RestController
@RequestMapping("/api/registrations")
@CrossOrigin(origins = "http://127.0.0.1:5500/")
@Slf4j
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Registration> createRegistration(
            @RequestParam("fullName") String fullName,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("photo") MultipartFile photo) throws IOException, DocumentException {

        log.info("Received registration request for user: {}", fullName);
        // Save the photo to the local file system
        String photoPath = savePhoto(photo);
        log.debug("Photo saved at path: {}", photoPath);

        // Create a Registration object
        Registration registration = new Registration();
        registration.setFullName(fullName);
        registration.setEmail(email);
        registration.setPhone(phone);
        registration.setPhotoPath(photoPath);

        // Save the registration with the photo path and generate PDF
        Registration savedRegistration = registrationService.saveRegistration(registration);
        log.info("Registration completed successfully for: {}", fullName);
        return ResponseEntity.ok(savedRegistration);
    }

    private String savePhoto(MultipartFile photo) throws IOException {
        String uploadDirectory = "uploads"; // Directory to store the uploaded photos

        String originalFilename = photo.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

        String uniqueFileName = UUID.randomUUID().toString().replace("-", "") + fileExtension;

        Path path = Paths.get(uploadDirectory, uniqueFileName);
        Files.createDirectories(path.getParent());

        Files.write(path, photo.getBytes());

        // Return the full path including the uploads directory
        return "./" + uploadDirectory + "/" + uniqueFileName;
    }
    @GetMapping
    public ResponseEntity<List<Registration>> getAllRegistrations() {
        List<Registration> registrations = registrationService.getAllRegistrations();
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Registration> getRegistrationById(@PathVariable Long id) {
        Registration registration = registrationService.getRegistrationById(id);
        if (registration == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(registration);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long id) {
        registrationService.deleteRegistration(id);
        return ResponseEntity.noContent().build();
    }
}