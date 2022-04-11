package com.haniwon.controller;

import com.haniwon.dto.ResponseDTO;
import com.haniwon.dto.income.request.IncomeRequestDTO;
import com.haniwon.dto.income.request.UpdateIncomeInfoRequestDTO;
import com.haniwon.dto.income.request.UpdateIncomePatientRequestDTO;
import com.haniwon.service.IncomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{incomeId}/info")
    public ResponseEntity<ResponseDTO> updateIncomeInfo(@RequestBody UpdateIncomeInfoRequestDTO incomeInfoRequestDTO, @PathVariable Long incomeId) {
        incomeService.updateIncomeInfo(incomeInfoRequestDTO, incomeId);
        return ResponseEntity.ok(new ResponseDTO("OK"));
    }

    @PutMapping("/{incomeId}/patient")
    public ResponseEntity<ResponseDTO> updateIncomePatient(@RequestBody UpdateIncomePatientRequestDTO incomePatientRequestDTO, @PathVariable Long incomeId) {
        incomeService.updateIncomePatient(incomePatientRequestDTO, incomeId);
        return ResponseEntity.ok(new ResponseDTO("OK"));
    }

 
}
