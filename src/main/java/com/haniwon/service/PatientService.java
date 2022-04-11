package com.haniwon.service;

import com.haniwon.domain.Patient;
import com.haniwon.dto.patient.request.PatientRequestDTO;
import com.haniwon.exception.PatientExistException;
import com.haniwon.repository.PatientRepository;
import org.springframework.stereotype.Service;

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

    public void showAllPatients() {
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
