package com.haniwon.controller;

import com.haniwon.dto.ResponseDTO;
import com.haniwon.dto.patient.request.PatientRequestDTO;
import com.haniwon.dto.patient.response.MultiPatientResponseDTO;
import com.haniwon.dto.patient.response.PatientResponseDTO;
import com.haniwon.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/patient")
@Controller
public class PatientController {


    Logger logger = LoggerFactory.getLogger(PatientController.class);
    private final PatientService patientService;
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> showPatient(@PathVariable Long id) {
        logger.info("환자 1명 조회");
        return ResponseEntity.ok(patientService.showPatient(id));
    }

    @GetMapping
    public ResponseEntity<MultiPatientResponseDTO> showSameNamePatients(@RequestParam String name) {
        logger.info("이름이 같은 환자 조회");
        return ResponseEntity.ok(patientService.showSameNamePatients(name));
    }

    @GetMapping("/total")
    public ResponseEntity<MultiPatientResponseDTO> showAllPatients() {
        logger.info("전체 환자 조회");
        return ResponseEntity.ok(patientService.showAllPatients());
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createPatient(@RequestBody PatientRequestDTO patientRequestDTO) {
        logger.info("환자 생성");
        patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok(new ResponseDTO("OK"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updatePatient(@RequestBody PatientRequestDTO patientRequestDTO, @PathVariable Long id) {
        logger.info("환자 수정");
        patientService.updatePatient(patientRequestDTO, id);
        return ResponseEntity.ok(new ResponseDTO("OK"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deletePatient(@PathVariable Long id) {
        logger.info("환자 삭제");
        patientService.deletePatient(id);
        return ResponseEntity.ok(new ResponseDTO("OK"));
    }

}
