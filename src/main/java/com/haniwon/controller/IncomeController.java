package com.haniwon.controller;

import com.haniwon.dto.ResponseDTO;
import com.haniwon.dto.income.request.IncomeRequestDTO;
import com.haniwon.dto.income.request.UpdateIncomeInfoRequestDTO;
import com.haniwon.dto.income.request.UpdateIncomePatientRequestDTO;
import com.haniwon.dto.income.response.IncomeResponseDTO;
import com.haniwon.dto.income.response.IncomeSummeryResponseDTO;
import com.haniwon.service.IncomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

@Controller
@RequestMapping("/income")
public class IncomeController {

    Logger logger = LoggerFactory.getLogger(IncomeController.class);

    private final IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
         this.incomeService = incomeService;
    }

    @GetMapping("/income/total")
    public ModelAndView showAllIncome() {
        logger.info("전체 매출 조회");
        IncomeSummeryResponseDTO incomeSummery = incomeService.showAllIncomes();
        ModelAndView mv = new ModelAndView("income/multi");
        mv.addObject("incomeSummery", incomeSummery);
        return mv;
    }

    @GetMapping("/income/{incomeId}")
    public ResponseEntity<IncomeResponseDTO> showIncome(@PathVariable Long incomeId) {
        logger.info("매출 1건 조회하기");
        return ResponseEntity.ok(incomeService.showIncome(incomeId));
    }

    @GetMapping("/day/{date}")
    public ResponseEntity<IncomeSummeryResponseDTO> showIncomePerDay(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        logger.info("하루에 대한 매출 조회하기");
        return ResponseEntity.ok(incomeService.showIncomeByDay(date));
    }

    @GetMapping("/month/{yearMonth}")
    public ResponseEntity<IncomeSummeryResponseDTO> showIncomePerMonth(@PathVariable @DateTimeFormat(pattern = "yyyy-MM")YearMonth yearMonth) {
        logger.info("월별 매출 조회하기");
        return ResponseEntity.ok(incomeService.showIncomePerMonth(yearMonth));
    }
    @GetMapping("/year/{year}")
    public ResponseEntity<IncomeSummeryResponseDTO> showIncomePerYear(@PathVariable @DateTimeFormat(pattern = "yyyy")Year year) {
        logger.info("년별 매출 조회하기");
        return ResponseEntity.ok(incomeService.showIncomePerYear(year));
    }
    @GetMapping("/period")
    public ResponseEntity<IncomeSummeryResponseDTO> showIncomeByPeriod(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        logger.info("기간별 매출 조회하기");
        return ResponseEntity.ok(incomeService.showIncomeByPeriod(startDate, endDate));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<IncomeSummeryResponseDTO> showIncomeByPatient(@PathVariable Long patientId) {
        logger.info("환자 1명에 대한 매출 조회하기");
        return ResponseEntity.ok(incomeService.showIncomeByPatient(patientId));
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
