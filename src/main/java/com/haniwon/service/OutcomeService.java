package com.haniwon.service;

import com.haniwon.domain.Outcome;
import com.haniwon.domain.Vendor;
import com.haniwon.dto.outcome.request.OutcomeRequestDTO;
import com.haniwon.dto.outcome.request.UpdateOutcomeVendorRequestDTO;
import com.haniwon.dto.outcome.response.OutcomeResponseDTO;
import com.haniwon.dto.outcome.response.OutcomeSummeryResponseDTO;
import com.haniwon.repository.outcome.OutcomeRepository;
import com.haniwon.repository.vendor.VendorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    public void updateOutcomeInfo(OutcomeRequestDTO outcomeRequestDTO, Long outcomeId) {
        Outcome outcome = outcomeRepository.findById(outcomeId).orElseThrow(() -> new NoSuchElementException("수정하려는 매입이 존재하지 않습니다."));
        outcome.updateOutcomeInfo(outcomeRequestDTO);
        outcomeRepository.save(outcome);
    }

    public void updateOutcomeVendor(UpdateOutcomeVendorRequestDTO updateOutcomeVendorRequestDTO, Long outcomeId) {
        Outcome outcome = outcomeRepository.findById(outcomeId).orElseThrow(() -> new NoSuchElementException("수정하려는 매입이 존재하지 않습니다."));
        Vendor vendor = vendorRepository.findByCompanyName(updateOutcomeVendorRequestDTO.getCompanyName()).orElseThrow(() -> new NoSuchElementException("수정하려는 거래처가 존재하지 않습니다."));
        outcome.updateOutcomeOfVendor(vendor);
        outcomeRepository.save(outcome);
    }

    public void deleteOutcome(Long outcomeId) {
        Outcome outcome = outcomeRepository.findById(outcomeId).orElseThrow(() -> new NoSuchElementException("삭제하려는 매입이 존재하지 않습니다."));
        outcomeRepository.delete(outcome);
    }

    public OutcomeResponseDTO showOutcome(Long outcomeId) {
        Outcome outcome = outcomeRepository.findById(outcomeId).orElseThrow(() -> new NoSuchElementException("조회하려는 매입이 존재하지 않습니다."));
        return OutcomeResponseDTO.from(outcome);
    }

    public OutcomeSummeryResponseDTO showOutcomesByDay(LocalDate date) {
        List<Outcome> outcomes = outcomeRepository.findAllByDay(date);
        List<OutcomeResponseDTO> outcomeResponseDTOS = outcomes.stream()
                .map(outcome -> showOutcome(outcome.getId()))
                .collect(Collectors.toList());
        return OutcomeSummeryResponseDTO.from(outcomeResponseDTOS);
    }
}
