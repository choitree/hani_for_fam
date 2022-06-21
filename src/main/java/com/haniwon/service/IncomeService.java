package com.haniwon.service;

import com.haniwon.domain.Income;
import com.haniwon.domain.Patient;
import com.haniwon.dto.income.request.IncomeRequestDTO;
import com.haniwon.dto.income.request.UpdateIncomeInfoRequestDTO;
import com.haniwon.dto.income.request.UpdateIncomePatientRequestDTO;
import com.haniwon.dto.income.response.IncomeResponseDTO;
import com.haniwon.dto.income.response.IncomeSummeryResponseDTO;
import com.haniwon.repository.income.IncomeRepository;
import com.haniwon.repository.patient.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class IncomeService {

      private final PatientRepository patientRepository;
    private final IncomeRepository incomeRepository;

    public IncomeService(PatientRepository patientRepository, IncomeRepository incomeRepository) {
        this.patientRepository = patientRepository;
        this.incomeRepository = incomeRepository;
    }

    @Transactional
    public void addIncome(IncomeRequestDTO incomeRequestDTO, Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new NoSuchElementException("해당 환자가 존재하지 않아 등록이 불가합니다."));
        Income income = Income.createIncome(incomeRequestDTO, patient);
        Patient visitPatient = patientRepository.findByNameAndBirthday(incomeRequestDTO.getName(), incomeRequestDTO.getBirthday()).orElseThrow(() -> new NoSuchElementException("해당 환자가 존재하지 않아 등록이 불가합니다."));
        if(patient != visitPatient) {
            throw new IllegalArgumentException("다른 환자의 매출 정보는 등록할 수 없습니다.");
        }
        incomeRepository.save(income);
        LocalDate lastVisit = incomeRequestDTO.getDate();
        if(incomeRepository.findLastVisitIncomeByPatient(patient).isPresent()) {
            lastVisit = incomeRepository.findLastVisitIncomeByPatient(patient).get().getDate();
        }
        patient.updateLastVisit(lastVisit);
        patientRepository.save(patient);
    }

    public IncomeResponseDTO showIncome(Long incomeId) {
        Income income = incomeRepository.findById(incomeId).orElseThrow(() -> new NoSuchElementException("조회할 매출에 정보가 존재하지 않습니다."));
        return IncomeResponseDTO.from(income);
    }

    public IncomeSummeryResponseDTO showIncomeByDay(LocalDate date) {
        List<Income> incomes = incomeRepository.findAllByDate(date);

        List<IncomeResponseDTO> incomeResponseDTOS = incomes.stream()
                .map(income -> showIncome(income.getId()))
                .collect(Collectors.toList());

        return IncomeSummeryResponseDTO.from(incomeResponseDTOS);
    }

    public IncomeSummeryResponseDTO showIncomePerMonth(YearMonth yearMonth) {
        List<Income> incomes = incomeRepository.findAllByMonth(yearMonth);

        List<IncomeResponseDTO> incomeResponseDTOS = incomes.stream()
                .map(income -> showIncome(income.getId()))
                .collect(Collectors.toList());
        return IncomeSummeryResponseDTO.from(incomeResponseDTOS);
    }

    public IncomeSummeryResponseDTO showIncomePerYear(Year year) {
        List<Income> incomes = incomeRepository.findAllByYear(year);

        List<IncomeResponseDTO> incomeResponseDTOS = incomes.stream()
                .map(income -> showIncome(income.getId()))
                .collect(Collectors.toList());
        return IncomeSummeryResponseDTO.from(incomeResponseDTOS);
    }

    public IncomeSummeryResponseDTO showIncomeByPeriod(LocalDate date1, LocalDate date2) {
        List<Income> incomes = incomeRepository.findAllByPeriod(date1, date2);

        List<IncomeResponseDTO> incomeResponseDTOS = incomes.stream()
                .map(income -> showIncome(income.getId()))
                .collect(Collectors.toList());
        return IncomeSummeryResponseDTO.from(incomeResponseDTOS);
    }

    public IncomeSummeryResponseDTO showIncomeByPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new NoSuchElementException("조회할 환자 정보가 존재하지 않습니다."));
        List<Income> incomes = incomeRepository.findAllByPatient(patient);

        List<IncomeResponseDTO> incomeResponseDTOS = incomes.stream()
                .map(income -> showIncome(income.getId()))
                .collect(Collectors.toList());
        return IncomeSummeryResponseDTO.from(incomeResponseDTOS);
    }

    @Transactional
    public void updateIncomeInfo(UpdateIncomeInfoRequestDTO incomeInfoRequestDTO, Long incomeId) {
        Income income = incomeRepository.findById(incomeId).orElseThrow(() -> new NoSuchElementException("수정할 매출에 정보가 존재하지 않습니다."));
        Patient patient = income.getPatient();
        income.updateIncomeInfo(incomeInfoRequestDTO);
        incomeRepository.save(income);
        LocalDate lastVisit = incomeRepository.findLastVisitByPatient(patient);
        patient.updateLastVisit(lastVisit);
        patientRepository.save(patient);
    }

    @Transactional
    public void updateIncomePatient(UpdateIncomePatientRequestDTO incomePatientRequestDTO, Long incomeId) {
        Income income = incomeRepository.findById(incomeId).orElseThrow(() -> new NoSuchElementException("수정할 매출에 정보가 존재하지 않습니다."));
        Patient oldPatient = income.getPatient();
        Patient newPatient = patientRepository.findByNameAndBirthday(incomePatientRequestDTO.getName(), incomePatientRequestDTO.getBirthday()).orElseThrow(() -> new NoSuchElementException("변경하려는 환자 정보가 존재하지 않습니다."));
        income.updateIncomePatient(newPatient);
        incomeRepository.save(income);
        LocalDate lastVisit = incomeRepository.findLastVisitByPatient(newPatient);
        newPatient.updateLastVisit(lastVisit);
        patientRepository.save(newPatient);
        lastVisit = incomeRepository.findLastVisitByPatient(oldPatient);
        oldPatient.updateLastVisit(lastVisit);
        patientRepository.save(oldPatient);
    }

    @Transactional
    public void deleteIncome(Long incomeId) {
        Income income = incomeRepository.findById(incomeId).orElseThrow(() -> new NoSuchElementException("삭제할 매출에 정보가 존재하지 않습니다."));
        Patient patient = income.getPatient();
        incomeRepository.delete(income);
        LocalDate lastVisit = incomeRepository.findLastVisitByPatient(patient);
        patient.updateLastVisit(lastVisit);
        patientRepository.save(patient);
    }

    public IncomeSummeryResponseDTO showAllIncomes() {
        List<Income> incomes = incomeRepository.findAll();
        List<IncomeResponseDTO> incomeResponseDTOS = incomes.stream()
                .map(income -> showIncome(income.getId()))
                .collect(Collectors.toList());
        return IncomeSummeryResponseDTO.from(incomeResponseDTOS);
    }
}
