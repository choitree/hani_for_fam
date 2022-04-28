package com.haniwon.controller;

import com.haniwon.dto.ResponseDTO;
import com.haniwon.dto.outcome.request.OutcomeRequestDTO;
import com.haniwon.service.OutcomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
        outcomeService.createOutcome(outcomeRequestDTO, vendorId);
        return ResponseEntity.ok(new ResponseDTO("ok"));
    }


}
