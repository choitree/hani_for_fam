package com.haniwon.dto.outcome.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UpdateOutcomeVendorRequestDTO {

    private String companyName;

}
