package com.haniwon.dto.patient.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.haniwon.domain.Income;
import com.haniwon.domain.Patient;
import com.haniwon.dto.income.response.IncomeResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Builder
@Getter
public class PatientResponseDTO {

    private final Long id;

    private final Long chartId;

    private final String name;
    private final String sex;
    private final String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate firstVisit;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate lastVisit;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate birthday;
    private final String memo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<IncomeResponseDTO> incomes;

    private static List<IncomeResponseDTO> convertIncomes(List<Income> incomes) {
        return incomes.stream()
                .map(income -> IncomeResponseDTO.from(income))
                .collect(Collectors.toList());

    }

    public static String convertPhone(String phone) {
        String phone1 = phone.substring(0, 3);
        String phone2 = phone.substring(3, 7);
        String phone3 = phone.substring(7);
        return phone1 + "-" + phone2 + "-" + phone3;
    }

    public static PatientResponseDTO from(Patient patient, List<Income> incomes) {
        return PatientResponseDTO.builder()
                .id(patient.getId())
                .chartId(patient.getChartId())
                .name(patient.getName())
                .sex(patient.getSex())
                .phone(convertPhone(patient.getPhone()))
                .firstVisit(patient.getFirstVisit())
                .lastVisit(patient.getLastVisit())
                .birthday(patient.getBirthday())
                .memo(patient.getMemo())
                .incomes(convertIncomes(incomes))
                .build();
    }

}
