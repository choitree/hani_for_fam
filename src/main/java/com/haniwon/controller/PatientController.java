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

    @GetMapping("/{patientId}/modify")
    public ModelAndView modifyForm(@PathVariable Long patientId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("patient/modify");
        logger.info("환자 수정 폼");
        mv.addObject("patientId", patientId);
        PatientResponseDTO patient = patientService.showPatient(patientId);
        mv.addObject("patient", patient);
        return mv;
    }

    @PutMapping("{patientId}/modify")
    public ModelAndView updatePatient(@ModelAttribute @RequestBody PatientRequestDTO patientRequestDTO,
                                      @ModelAttribute PatientResponseDTO patient,
                                      @PathVariable Long patientId) {
        logger.info("환자 수정");
        ModelAndView mv = new ModelAndView("redirect:/patient/" + patientId);
        patientService.updatePatient(patientRequestDTO, patientId);
        return mv;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deletePatient(@PathVariable Long id) {
        logger.info("환자 삭제");
        patientService.deletePatient(id);
        return ResponseEntity.ok(new ResponseDTO("OK"));
    }

}
