package com.haniwon.dto.income.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.haniwon.domain.Income;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Builder
public class IncomeResponseDTO {

    private final LocalDate date;
    private final Long amount;
    private final String name;
    private final LocalDate birthday;
    private final Boolean isCash;
    private final Boolean isPay;
    private final String symptom;
    private final Boolean isAcupuncture;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String memo;

    public static IncomeResponseDTO from(Income income) {
        return IncomeResponseDTO.builder()
                .date(income.getDate())
                .amount(income.getAmount())
                .name(income.getPatient().getName())
                .birthday(income.getPatient().getBirthday())
                .isCash(income.getIsCash())
                .isPay(income.getIsPay())
                .symptom(income.getSymptom())
                .isAcupuncture(income.getIsAcupuncture())
                .memo(income.getMemo())
                .build();
    }
}
