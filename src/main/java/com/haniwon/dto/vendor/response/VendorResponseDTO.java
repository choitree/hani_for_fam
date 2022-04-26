package com.haniwon.dto.vendor.response;

import com.haniwon.domain.Outcome;
import com.haniwon.domain.Vendor;
import com.haniwon.dto.outcome.response.OutcomeResponseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Builder
@Getter
public class VendorResponseDTO {

    private final String companyName;
    private final String manager;
    private final String phone;

    private final List<OutcomeResponseDTO> outcomes;

    private static List<OutcomeResponseDTO> convertIncomes(List<Outcome> outcomes) {
        return outcomes.stream()
                .map(outcome -> OutcomeResponseDTO.from(outcome))
                .collect(Collectors.toList());

    }

    public static VendorResponseDTO from(Vendor vendor, List<Outcome> outcomes) {
        return VendorResponseDTO.builder()
                .companyName(vendor.getCompanyName())
                .manager(vendor.getManager())
                .phone(vendor.getPhone())
                .outcomes(convertIncomes(outcomes))
                .build();
    }
}
