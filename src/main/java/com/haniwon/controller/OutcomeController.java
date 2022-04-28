package com.haniwon.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.haniwon.dto.ResponseDTO;
import com.haniwon.dto.outcome.request.OutcomeRequestDTO;
import com.haniwon.dto.outcome.request.UpdateOutcomeVendorRequestDTO;
import com.haniwon.dto.outcome.response.OutcomeResponseDTO;
import com.haniwon.dto.outcome.response.OutcomeSummeryResponseDTO;
import com.haniwon.service.OutcomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;

@Controller
@RequestMapping("/outcome")
public class OutcomeController {

    Logger logger = LoggerFactory.getLogger(OutcomeController.class);

    private final OutcomeService outcomeService;

    public OutcomeController(OutcomeService outcomeService) {
        this.outcomeService = outcomeService;
    }

    @GetMapping("/{outcomeId}")
    public ResponseEntity<OutcomeResponseDTO> showOutcome(@PathVariable Long outcomeId) {
        logger.info("매입 단건 조회하기");
        return ResponseEntity.ok(outcomeService.showOutcome(outcomeId));
    }

    @GetMapping("/day/{date}")
    public ResponseEntity<OutcomeSummeryResponseDTO> showOutcomesByDay(@PathVariable
                                                                       @JsonFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        logger.info("하루 매입 조회하기");
        return ResponseEntity.ok(outcomeService.showOutcomesByDay(date));
    }

    @GetMapping("/month/{yearMonth}")
    public ResponseEntity<OutcomeSummeryResponseDTO> showOutcomeByMonth(@PathVariable @JsonFormat(pattern = "yyyy-MM")YearMonth yearMonth) {
        return ResponseEntity.ok(outcomeService.showOutcomesByMonth(yearMonth));
    }

    @PostMapping("/{vendorId}")
    public ResponseEntity<ResponseDTO> createOutcome(@RequestBody OutcomeRequestDTO outcomeRequestDTO, @PathVariable Long vendorId) {
        logger.info("매입 생성하기");
        outcomeService.createOutcome(outcomeRequestDTO, vendorId);
        return ResponseEntity.ok(new ResponseDTO("ok"));
    }

    @PutMapping("/{outcomeId}/info")
    public ResponseEntity<ResponseDTO> updateOutcomeInfo(@RequestBody OutcomeRequestDTO outcomeRequestDTO, @PathVariable Long outcomeId) {
        logger.info("매입 세부정보 수정");
        outcomeService.updateOutcomeInfo(outcomeRequestDTO, outcomeId);
        return ResponseEntity.ok(new ResponseDTO("ok"));
    }

    @PutMapping("/{outcomeId}/vendor")
    public ResponseEntity<ResponseDTO> updateOutcomeVendor(@RequestBody UpdateOutcomeVendorRequestDTO updateOutcomeVendorRequestDTO, @PathVariable Long outcomeId) {
        logger.info("매입에 해당하는 거래처 수정");
        outcomeService.updateOutcomeVendor(updateOutcomeVendorRequestDTO, outcomeId);
        return ResponseEntity.ok(new ResponseDTO("ok"));
    }

    @DeleteMapping("/{outcomeId}")
    public ResponseEntity<ResponseDTO> deleteOutcome(@PathVariable Long outcomeId) {
        logger.info("매입 삭제");
        outcomeService.deleteOutcome(outcomeId);
        return ResponseEntity.ok(new ResponseDTO("ok"));
    }


}
