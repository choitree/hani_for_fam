package com.haniwon.controller;

import com.haniwon.dto.ResponseDTO;
import com.haniwon.dto.income.request.IncomeRequestDTO;
import com.haniwon.dto.income.request.UpdateIncomeInfoRequestDTO;
import com.haniwon.dto.income.request.UpdateIncomePatientRequestDTO;
import com.haniwon.dto.income.response.IncomeResponseDTO;
import com.haniwon.service.IncomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/income")
public class IncomeController {

    Logger logger = LoggerFactory.getLogger(IncomeController.class);

    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
         this.incomeService = incomeService;
    }

    @GetMapping("/{incomeId}")
    public ResponseEntity<IncomeResponseDTO> showIncome(@PathVariable Long incomeId) {
        logger.info("매출 1건 조회하기");
        return ResponseEntity.ok(incomeService.showIncome(incomeId));
    }

    @PostMapping("/{patientId}")
    public ResponseEntity<ResponseDTO> addIncome(@RequestBody IncomeRequestDTO incomeRequestDTO, @PathVariable Long patientId) {
        logger.info("매출 추가하기");
        incomeService.addIncome(incomeRequestDTO, patientId);
        return ResponseEntity.ok(new ResponseDTO("OK"));
    }

    @PutMapping("/{incomeId}/info")
    public ResponseEntity<ResponseDTO> updateIncomeInfo(@RequestBody UpdateIncomeInfoRequestDTO incomeInfoRequestDTO, @PathVariable Long incomeId) {
        logger.info("매출 세부정보 수정하기");
        incomeService.updateIncomeInfo(incomeInfoRequestDTO, incomeId);
        return ResponseEntity.ok(new ResponseDTO("OK"));
    }

    @PutMapping("/{incomeId}/patient")
    public ResponseEntity<ResponseDTO> updateIncomePatient(@RequestBody UpdateIncomePatientRequestDTO incomePatientRequestDTO, @PathVariable Long incomeId) {
        logger.info("매출에 해당하는 환자 수정하기");
        incomeService.updateIncomePatient(incomePatientRequestDTO, incomeId);
        return ResponseEntity.ok(new ResponseDTO("OK"));
    }

    @DeleteMapping("/{incomeId}")
    public ResponseEntity<ResponseDTO> deleteIncome(@PathVariable Long incomeId) {
        logger.info("매출 삭제하기");
        incomeService.deleteIncome(incomeId);
        return ResponseEntity.ok(new ResponseDTO("OK"));
    }


}
