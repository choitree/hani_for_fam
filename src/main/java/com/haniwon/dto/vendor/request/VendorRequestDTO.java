package com.haniwon.dto.vendor.request;

import com.haniwon.domain.Vendor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
public class VendorRequestDTO {

    private final String companyName;
    private final String manager;
    private final String phone;

}
