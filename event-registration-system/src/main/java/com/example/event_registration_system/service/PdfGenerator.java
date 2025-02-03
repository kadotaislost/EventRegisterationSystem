package com.example.event_registration_system.service;

import com.example.event_registration_system.model.Registration;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.FileOutputStream;
import java.io.IOException;

public class PdfGenerator {

    public static void generateVisitorBadge(Registration registration, String outputPath) throws DocumentException, IOException {
        Document document = new Document(PageSize.A5);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputPath));
        document.open();

        // Add background color
        Rectangle background = new Rectangle(document.getPageSize());
        background.setBackgroundColor(new BaseColor(240, 240, 240)); // Light grey background
        document.add(background);

        // Add a border to the document
        Rectangle rect = new Rectangle(10, 10, PageSize.A5.getWidth() - 10, PageSize.A5.getHeight() - 10);
        rect.enableBorderSide(Rectangle.BOX);
        rect.setBorderWidth(2);
        rect.setBorderColor(BaseColor.BLACK);
        document.add(rect);

        // Add photo
        String photoPath = registration.getPhotoPath(); // Use the exact path stored in the database
        Image img = Image.getInstance(photoPath);
        img.scaleToFit(150, 150);
        img.setAlignment(Element.ALIGN_CENTER);
        document.add(img);

        // Add some space after the image
        document.add(Chunk.NEWLINE);

        // Add full name in capital letters
        Font nameFont = new Font(Font.FontFamily.HELVETICA, 22, Font.BOLD, BaseColor.BLACK);
        Paragraph name = new Paragraph(registration.getFullName().toUpperCase(), nameFont);
        name.setAlignment(Element.ALIGN_CENTER);
        document.add(name);

        // Add some space after the name
        document.add(Chunk.NEWLINE);

        // Add visitor label with background color and padding
        Font visitorFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.WHITE);
        Chunk visitorChunk = new Chunk("Visitor", visitorFont);
        visitorChunk.setBackground(new BaseColor(200, 165, 30), 8, 8, 8, 8); // Orangish-yellow background with padding
        Paragraph visitorLabel = new Paragraph(visitorChunk);
        visitorLabel.setAlignment(Element.ALIGN_CENTER);
        document.add(visitorLabel);

        // Add a horizontal line
        LineSeparator separator = new LineSeparator();
        separator.setLineColor(BaseColor.BLACK);
        document.add(new Chunk(separator));

        // Add additional information
        Font infoFont = new Font(Font.FontFamily.HELVETICA, 14, Font.NORMAL, BaseColor.DARK_GRAY);
        Paragraph info = new Paragraph("Welcome to our event! Please enjoy your visit.", infoFont);
        info.setAlignment(Element.ALIGN_CENTER);
        document.add(info);

        // Add some space after the welcome message
        document.add(Chunk.NEWLINE);

        // Add more text with smaller font size
        Font smallInfoFont = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.DARK_GRAY);
        Paragraph additionalInfo = new Paragraph("For any assistance, please contact our help desk.", smallInfoFont);
        additionalInfo.setAlignment(Element.ALIGN_CENTER);
        document.add(additionalInfo);

        Paragraph eventDetails = new Paragraph("Event Date: May 10 2025\nLocation: Main Hall, Building A", smallInfoFont);
        eventDetails.setAlignment(Element.ALIGN_CENTER);
        document.add(eventDetails);

        document.close();
    }
}