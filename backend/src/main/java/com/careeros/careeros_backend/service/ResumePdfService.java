package com.careeros.careeros_backend.service;

import com.careeros.careeros_backend.dto.ResumePdfRequestDTO;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class ResumePdfService {

    public byte[] generatePdf(
            ResumePdfRequestDTO request
    ) {

        try {

            Document document =
                    new Document();

            ByteArrayOutputStream outputStream =
                    new ByteArrayOutputStream();

            PdfWriter.getInstance(
                    document,
                    outputStream
            );

            document.open();

            document.add(
                    new Paragraph(
                            request.getRefinedResume()
                    )
            );

            document.close();

            return outputStream
                    .toByteArray();

        } catch (Exception e) {

            throw new RuntimeException(
                    "PDF generation failed"
            );
        }
    }
}