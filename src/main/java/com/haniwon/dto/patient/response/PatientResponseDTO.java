package com.haniwon.dto.patient.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.haniwon.domain.Income;
import com.haniwon.domain.Patient;
import com.haniwon.dto.income.response.IncomeResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Builder
@Getter
public class PatientResponseDTO {

    private final Integer chartId;

    private final String name;
    private final String sex;
    private final String phone;
    private final LocalDate firstVisit;
    private final LocalDate lastVisit;

    private final LocalDate birthday;
    private final String memo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<IncomeResponseDTO> incomes;

    private static List<IncomeResponseDTO> convertIncomes(List<Income> incomes) {
        return incomes.stream()
                .map(income -> IncomeResponseDTO.from(income))
                .collect(Collectors.toList());

    }

    public static PatientResponseDTO from(Patient patient, List<Income> incomes) {
        return PatientResponseDTO.builder()
                .chartId(patient.getChartId())
                .name(patient.getName())
                .sex(patient.getSex())
                .phone(patient.getPhone())
                .firstVisit(patient.getFirstVisit())
                .lastVisit(patient.getLastVisit())
                .birthday(patient.getBirthday())
                .memo(patient.getMemo())
                .incomes(convertIncomes(incomes))
                .build();
    }

}
