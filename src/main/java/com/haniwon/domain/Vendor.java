package com.haniwon.domain;

import com.haniwon.dto.vendor.request.VendorRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vendor")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String companyName;

    private String manager;
    private String phone;

    @OneToMany(mappedBy = "vendor")
    private List<Outcome> outcomes = new ArrayList<>();

    public static Vendor createVendor(VendorRequestDTO vendorRequestDTO) {
        return Vendor.builder()
                .companyName(vendorRequestDTO.getCompanyName())
                .manager(vendorRequestDTO.getManager())
                .phone(vendorRequestDTO.getPhone())
                .build();
    }

    public void updateVendor(VendorRequestDTO vendorRequestDTO) {
        this.companyName = vendorRequestDTO.getCompanyName();
        this.manager = vendorRequestDTO.getManager();
        this.phone = vendorRequestDTO.getPhone();
    }

}
