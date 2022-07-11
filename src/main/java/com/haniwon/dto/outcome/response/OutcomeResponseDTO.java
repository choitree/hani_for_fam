package com.haniwon.dto.outcome.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.haniwon.domain.Outcome;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class OutcomeResponseDTO {

    private final String item;
    //금액
    private final Long amount;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String memo;

    private final String vendorName;


    public static OutcomeResponseDTO from(Outcome outcome) {
        return OutcomeResponseDTO.builder()
                .item(outcome.getItem())
                .amount(outcome.getAmount())
                .memo(outcome.getMemo())
                .vendorName(outcome.getVendor().getCompanyName())
                .build();
    }

}
