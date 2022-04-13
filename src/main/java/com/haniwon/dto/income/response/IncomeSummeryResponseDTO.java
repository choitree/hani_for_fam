package com.haniwon.dto.income.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.text.NumberFormat;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
@Builder
public class IncomeSummeryResponseDTO {

    private final long totalCase;
    private final long acupunctureCase;
    private final long medicineCase;

    private final long totalAmount;
    private final long acupunctureAmount;
    private final long medicineAmount;

    private final List<IncomeResponseDTO> incomeResponseDTOS;

    public static IncomeSummeryResponseDTO from(List<IncomeResponseDTO> incomeResponseDTOS) {

        long medicineAmount = incomeResponseDTOS.stream()
                .filter(incomeResponseDTO -> incomeResponseDTO.getIsAcupuncture() == false)
                .mapToLong(incomeResponseDTO -> incomeResponseDTO.getAmount())
                .sum();
        long acupunctureAmount = incomeResponseDTOS.stream()
                .filter(incomeResponseDTO -> incomeResponseDTO.getIsAcupuncture() == true)
                .mapToLong(incomeResponseDTO -> incomeResponseDTO.getAmount())
                .sum();
        long medicineCase = incomeResponseDTOS.stream()
                .filter(incomeResponseDTO -> incomeResponseDTO.getIsAcupuncture() == false)
                .count();

        long acupunctureCase = incomeResponseDTOS.stream()
                .filter(incomeResponseDTO -> incomeResponseDTO.getIsAcupuncture() == true)
                .count();

        return IncomeSummeryResponseDTO.builder()
                .incomeResponseDTOS(incomeResponseDTOS)
                .medicineAmount(medicineAmount)
                .acupunctureAmount(acupunctureAmount)
                .totalAmount(medicineAmount + acupunctureAmount)
                .medicineCase(medicineCase)
                .acupunctureCase(acupunctureCase)
                .totalCase(medicineCase + acupunctureCase)
                .build();
    }
}
