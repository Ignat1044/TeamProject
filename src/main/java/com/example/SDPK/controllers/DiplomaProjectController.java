package com.example.SDPK.controllers;

import ch.qos.logback.core.model.Model;
import com.example.SDPK.model.DiplomaProject;
import com.example.SDPK.repository.DiplomaProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public class DiplomaProjectController {
    @Autowired
    private DiplomaProjectRepository diplomaProjectRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<DiplomaProject> diplomaProjects = diplomaProjectRepository.findAll();
        model.addAttribute("diplomaProjects", diplomaProjects);
        return "home";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("diplomaProject", new DiplomaProject());
        return "add-form";
    }

    @PostMapping("/add")
    public String addDiplomaProject(@ModelAttribute("diplomaProject") DiplomaProject diplomaProject) {
        diplomaProjectRepository.save(diplomaProject);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteDiplomaProject(@PathVariable("id") Long id) {
        diplomaProjectRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/download/pdf")
    public ResponseEntity<byte[]> generatePdf() {
        List<DiplomaProject> diplomaProjects = diplomaProjectRepository.findAll();
        byte[] pdfBytes = PdfGenerator.generatePdf(diplomaProjects);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.attachment().filename("diploma-projects.pdf").build());
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/download/excel")
    public ResponseEntity<byte[]> generateExcel() {
        List<DiplomaProject> diplomaProjects = diplomaProjectRepository.findAll();
        byte[] excelBytes = ExcelGenerator.generateExcel(diplomaProjects);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment().filename("diploma-projects.xlsx").build());
        return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
    }
}
