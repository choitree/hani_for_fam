package com.haniwon.controller;

import com.haniwon.dto.ResponseDTO;
import com.haniwon.dto.patient.request.PatientRequestDTO;
import com.haniwon.repository.PatientRepository;
import com.haniwon.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
