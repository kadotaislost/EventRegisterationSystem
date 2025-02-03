package com.example.event_registration_system.service;

import com.example.event_registration_system.model.Registration;
import com.example.event_registration_system.repository.RegistrationRepository;
import com.itextpdf.text.DocumentException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public Registration saveRegistration(Registration registration) throws DocumentException, IOException {
        createBadgesDirectory();
        Registration savedRegistration = registrationRepository.save(registration);
        String pdfPath = "./badges/" + savedRegistration.getId() + "_badge.pdf";
        PdfGenerator.generateVisitorBadge(savedRegistration, pdfPath);
        savedRegistration.setBadgePath(pdfPath); // Set the badge path
        return registrationRepository.save(savedRegistration); // Save the updated registration
    }

    private void createBadgesDirectory() throws IOException {
        Path path = Paths.get("./badges");
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
    }

    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    public Registration getRegistrationById(Long id) {
        return registrationRepository.findById(id).orElse(null);
    }

    public void deleteRegistration(Long id) {
        registrationRepository.deleteById(id);
    }
}