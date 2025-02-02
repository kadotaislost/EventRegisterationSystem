package com.example.event_registration_system.controller;

import com.example.event_registration_system.model.Registration;
import com.example.event_registration_system.service.RegistrationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/registrations")
@CrossOrigin(origins = "http://127.0.0.1:5500/") // Allow requests from your frontend
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
            @RequestParam("photo") MultipartFile photo) throws IOException {

        // Save the photo to the local file system
        String photoPath = savePhoto(photo);

        // Create a Registration object
        Registration registration = new Registration();
        registration.setFullName(fullName);
        registration.setEmail(email);
        registration.setPhone(phone);
        registration.setPhotoPath(photoPath);

        // Save the registration with the photo path
        Registration savedRegistration = registrationService.saveRegistration(registration);

        return ResponseEntity.ok(savedRegistration);
    }

    private String savePhoto(MultipartFile photo) throws IOException {
        String uploadDirectory = "./uploads"; // Directory where files are saved

        // Get the original file extension
        String originalFilename = photo.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

        // Generate a unique file name (UUID + file extension)
        String uniqueFileName = UUID.randomUUID().toString().replace("-", "") + fileExtension;

        // Ensure the upload directory exists
        Path path = Paths.get(uploadDirectory, uniqueFileName);
        Files.createDirectories(path.getParent());

        // Save the file with the unique name
        Files.write(path, photo.getBytes());

        // Return just the unique file name (e.g., "uniqueFileName.jpg")
        return uniqueFileName;
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
