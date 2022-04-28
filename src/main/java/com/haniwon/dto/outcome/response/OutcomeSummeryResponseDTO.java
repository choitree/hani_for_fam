package com.haniwon.dto.outcome.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Builder
@Getter
public class OutcomeSummeryResponseDTO {

    private final List<OutcomeResponseDTO> outcomeResponseDTOS;
    private final Long sum;

    public static OutcomeSummeryResponseDTO from(List<OutcomeResponseDTO> outcomeResponseDTOS) {
        return OutcomeSummeryResponseDTO.builder()
                .outcomeResponseDTOS(outcomeResponseDTOS)
                .sum(outcomeResponseDTOS.stream().mapToLong(outcomeResponseDTO -> outcomeResponseDTO.getAmount()).sum())
                .build();
    }
}
