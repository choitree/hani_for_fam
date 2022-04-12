package com.haniwon.service;

import com.haniwon.domain.Income;
import com.haniwon.domain.Patient;
import com.haniwon.dto.patient.request.PatientRequestDTO;
import com.haniwon.dto.patient.response.MultiPatientResponseDTO;
import com.haniwon.dto.patient.response.PatientResponseDTO;
import com.haniwon.exception.PatientExistException;
import com.haniwon.repository.IncomeRepository;
import com.haniwon.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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

    public MultiPatientResponseDTO showSameNamePatients(String name) {
        List<Patient> patients = patientRepository.findAllByName(name);
        List<PatientResponseDTO> patientsResponseDTO = patients.stream()
                .map(patient -> showPatient(patient.getId()))
                .collect(Collectors.toList());
        return MultiPatientResponseDTO.from(patientsResponseDTO);
    }

    public MultiPatientResponseDTO showAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        List<PatientResponseDTO> patientsResponseDTO = patients.stream()
                .map(patient -> showPatient(patient.getId()))
                .collect(Collectors.toList());
        return MultiPatientResponseDTO.from(patientsResponseDTO);
    }

    //pid 중복 검사
    public void createPatient(PatientRequestDTO patientRequestDTO) {
        if(patientRepository.findBychartId(patientRequestDTO.getChartId()).isPresent()) {
            throw new PatientExistException("이미 해당 번호의 환자가 존재합니다.");
        }
        Patient patient = Patient.createPatient(patientRequestDTO);
        patientRepository.save(patient);
    }

    public void updatePatient(PatientRequestDTO patientRequestDTO, Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 번호의 환자가 존재하지 않습니다."));
        patient.updatePatient(patientRequestDTO);
        patientRepository.save(patient);
    }

    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당 번호의 환자가 존재하지 않습니다."));
        patientRepository.delete(patient);
    }

}
