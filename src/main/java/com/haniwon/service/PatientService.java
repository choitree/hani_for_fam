package com.haniwon.service;

import com.haniwon.domain.Patient;
import com.haniwon.dto.patient.request.PatientRequestDTO;
import com.haniwon.exception.PatientExistException;
import com.haniwon.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    //pid 중복 검사
    public void createPatient(PatientRequestDTO patientRequestDTO) {
        if(patientRepository.findBypId(patientRequestDTO.getPId()).isPresent()) {
            throw new PatientExistException("이미 해당 번호의 환자가 존재합니다.");
        }
        Patient patient = Patient.createPatient(patientRequestDTO);
        patientRepository.save(patient);
    }
}
