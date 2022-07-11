package com.haniwon.service;

import com.haniwon.domain.Income;
import com.haniwon.domain.Patient;
import com.haniwon.dto.patient.request.PatientRequestDTO;
import com.haniwon.dto.patient.response.MultiPatientResponseDTO;
import com.haniwon.dto.patient.response.PatientResponseDTO;
import com.haniwon.exception.PatientExistException;
import com.haniwon.repository.income.IncomeRepository;
import com.haniwon.repository.patient.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PatientService {

    Logger logger = LoggerFactory.getLogger(PatientService.class);

    private final PatientRepository patientRepository;
    private final IncomeRepository incomeRepository;

    public PatientService(PatientRepository patientRepository, IncomeRepository incomeRepository) {
        this.patientRepository = patientRepository;
        this.incomeRepository = incomeRepository;
    }

    public PatientResponseDTO showPatient(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        logger.info("patient : {}", patient);
        List<Income> incomes = incomeRepository.findAllByPatient(patient);
        return PatientResponseDTO.from(patient, incomes);
    }

    public List<PatientResponseDTO> showSameNamePatients(String name) {
        List<Patient> patients = patientRepository.findAllByNameContains(name);
        return patients.stream()
                .map(patient -> showPatient(patient.getId()))
                .collect(Collectors.toList());
    }

    public List<PatientResponseDTO> showAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(patient -> showPatient(patient.getId()))
                .collect(Collectors.toList());
    }

    public void createPatient(PatientRequestDTO patientRequestDTO) {
        if(patientRepository.findBychartId(patientRequestDTO.getChartId()).isPresent()) {
            throw new PatientExistException("이미 해당 번호의 환자가 존재합니다.");
        }
        Patient patient = Patient.createPatient(patientRequestDTO);
        patientRepository.save(patient);
    }

    @Transactional
    public void updatePatient(PatientRequestDTO patientRequestDTO, Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 번호의 환자가 존재하지 않습니다."));
        patient.updatePatient(patientRequestDTO);
        patientRepository.save(patient);
    }

    @Transactional
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 번호의 환자가 존재하지 않습니다."));
        incomeRepository.deleteAllByPatient(patient);
        patientRepository.delete(patient);
    }

}
