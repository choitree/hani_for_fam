package com.haniwon.dto.outcome.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
public class OutcomeRequestDTO {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String item;
    private Integer amount;
    private String memo;

}
