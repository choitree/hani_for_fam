package com.haniwon.controller;

import com.haniwon.dto.ResponseDTO;
import com.haniwon.dto.outcome.request.OutcomeRequestDTO;
import com.haniwon.service.OutcomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/outcome")
public class OutcomeController {

    Logger logger = LoggerFactory.getLogger(OutcomeController.class);

    private final OutcomeService outcomeService;

    public OutcomeController(OutcomeService outcomeService) {
        this.outcomeService = outcomeService;
    }

    @PostMapping("/{vendorId}")
    public ResponseEntity<ResponseDTO> createOutcome(@RequestBody OutcomeRequestDTO outcomeRequestDTO, @PathVariable Long vendorId) {
        logger.info("매입 생성하기");
        outcomeService.createOutcome(outcomeRequestDTO, vendorId);
        return ResponseEntity.ok(new ResponseDTO("ok"));
    }

    @PutMapping("/{outcomeId}/info")
    public ResponseEntity<ResponseDTO> updateOutcome(@RequestBody OutcomeRequestDTO outcomeRequestDTO, @PathVariable Long outcomeId) {
        logger.info("매입 세부정보 수정");
        outcomeService.updateOutcomeInfo(outcomeRequestDTO, outcomeId);
        return ResponseEntity.ok(new ResponseDTO("ok"));
    }


}
