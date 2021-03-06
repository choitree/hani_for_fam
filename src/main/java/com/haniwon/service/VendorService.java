package com.haniwon.service;

import com.haniwon.domain.Outcome;
import com.haniwon.domain.Vendor;
import com.haniwon.dto.vendor.request.VendorRequestDTO;
import com.haniwon.dto.vendor.response.VendorResponseDTO;
import com.haniwon.exception.VendorExistException;
import com.haniwon.repository.outcome.OutcomeRepository;
import com.haniwon.repository.vendor.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;
    private final OutcomeRepository outcomeRepository;

    public VendorService(VendorRepository vendorRepository, OutcomeRepository outcomeRepository) {
         this.vendorRepository = vendorRepository;
         this.outcomeRepository = outcomeRepository;
    }

    public void createVendor(VendorRequestDTO vendorRequestDTO) {
        if(vendorRepository.findByCompanyName(vendorRequestDTO.getCompanyName()).isPresent()) {
            throw new VendorExistException("해당 거래처가 이미 존재합니다.");
        }
        Vendor vendor = Vendor.createVendor(vendorRequestDTO);
        System.out.println("vendor = " + vendor);
        vendorRepository.save(vendor);
    }

    public VendorResponseDTO showVendor(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow(() -> new NoSuchElementException("해당 거래처가 존재하지 않습니다."));
        List<Outcome> outcomes = outcomeRepository.findAllByVendor(vendor);
        return VendorResponseDTO.from(vendor, outcomes);
    }

    public List<VendorResponseDTO> showAllVendor() {
        List<Vendor> vendors = vendorRepository.findAll();
        return vendors.stream()
                .map(vendor -> showVendor(vendor.getId()))
                .collect(Collectors.toList());
    }

    public void updateVendor(VendorRequestDTO vendorRequestDTO, Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow(() -> new NoSuchElementException("수정할 거래처가 존재하지 않습니다."));
        vendor.updateVendor(vendorRequestDTO);
        vendorRepository.save(vendor);
    }

    public void deleteVendor(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow(() -> new NoSuchElementException("삭제할 거래처가 존재하지 않습니다."));
        vendorRepository.delete(vendor);
    }

}
