package com.haniwon.dto.income.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class UpdateIncomePatientRequestDTO {

    private String name;
    private LocalDate birthday;
}
