package com.haniwon.service;

import com.haniwon.domain.Outcome;
import com.haniwon.domain.Vendor;
import com.haniwon.dto.outcome.request.OutcomeRequestDTO;
import com.haniwon.repository.outcome.OutcomeRepository;
import com.haniwon.repository.vendor.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class OutcomeService {

    private final OutcomeRepository outcomeRepository;
    private final VendorRepository vendorRepository;

    public OutcomeService(OutcomeRepository outcomeRepository, VendorRepository vendorRepository) {
        this.outcomeRepository = outcomeRepository;
        this.vendorRepository = vendorRepository;
    }

    public void createOutcome(OutcomeRequestDTO outcomeRequestDTO, Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new NoSuchElementException("등록하려는 매입에 해당하는 거래처가 존재하지 않습니다."));
        Outcome outcome = Outcome.createOutcome(outcomeRequestDTO, vendor);
        outcomeRepository.save(outcome);
    }
}
