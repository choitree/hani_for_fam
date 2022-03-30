package com.haniwon.controller;

import com.haniwon.dto.ResponseDTO;
import com.haniwon.dto.patient.request.PatientRequestDTO;
import com.haniwon.repository.PatientRepository;
import com.haniwon.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/patient")
@Controller
public class PatientController {

    private PatientService patientService;
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createPatient(@RequestBody PatientRequestDTO patientRequestDTO) {
        patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok(new ResponseDTO("OK"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updatePatient(@RequestBody PatientRequestDTO patientRequestDTO, @PathVariable Long id) {
        patientService.updatePatient(patientRequestDTO, id);
        return ResponseEntity.ok(new ResponseDTO("OK"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deletePatient(@RequestBody PatientRequestDTO patientRequestDTO, @PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok(new ResponseDTO("OK"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> showPatient(@RequestBody PatientRequestDTO patientRequestDTO, @PathVariable Long id) {
        patientService.showPatient(id);
        return ResponseEntity.ok(new ResponseDTO("OK"));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> showAllPatients() {
        patientService.showAllPatients();
        return ResponseEntity.ok(new ResponseDTO("OK"));
    }


}
