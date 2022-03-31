package com.haniwon.controller;

import com.haniwon.dto.ResponseDTO;
import com.haniwon.dto.income.request.IncomeRequestDTO;
import com.haniwon.service.IncomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/income")
public class IncomeController {

    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
         this.incomeService = incomeService;
    }

    @PostMapping("/{patientId}")
    public ResponseEntity<ResponseDTO> addIncome(@RequestBody IncomeRequestDTO incomeRequestDTO, @PathVariable Long patientId) {
        incomeService.addIncome(incomeRequestDTO, patientId);
        return ResponseEntity.ok(new ResponseDTO("OK"));
    }
}
