package com.haniwon.controller;

import com.haniwon.dto.DateRange;
import com.haniwon.dto.ResponseDTO;
import com.haniwon.dto.income.request.IncomeRequestDTO;
import com.haniwon.dto.income.request.UpdateIncomeInfoRequestDTO;
import com.haniwon.dto.income.request.UpdateIncomePatientRequestDTO;
import com.haniwon.dto.income.response.IncomeResponseDTO;
import com.haniwon.dto.income.response.IncomeSummeryResponseDTO;
import com.haniwon.dto.patient.response.PatientResponseDTO;
import com.haniwon.service.IncomeService;
import com.haniwon.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;

@Controller
public class IncomeController {

    Logger logger = LoggerFactory.getLogger(IncomeController.class);

    private final IncomeService incomeService;
    private final PatientService patientService;

    public IncomeController(IncomeService incomeService, PatientService patientService) {
        this.incomeService = incomeService;
        this.patientService = patientService;
    }

    public LocalDate convertFromDateToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    @GetMapping("/income/total")
    public ModelAndView showAllIncome() {
        logger.info("전체 매출 조회");
        IncomeSummeryResponseDTO incomeSummery = incomeService.showAllIncomes();
        ModelAndView mv = new ModelAndView("income/multi");

        DateRange dateRange = new DateRange();
        dateRange.setDateFrom(new Date());
        dateRange.setDateTo(new Date());
        mv.addObject("dateRange", dateRange);
        mv.addObject("incomeSummery", incomeSummery);
        return mv;
    }

    @GetMapping("/income/{incomeId}")
    public ResponseEntity<IncomeResponseDTO> showIncome(@PathVariable Long incomeId) {
        logger.info("매출 1건 조회하기");
        return ResponseEntity.ok(incomeService.showIncome(incomeId));
    }

//    @GetMapping("/income/day/{date}")
//    public ResponseEntity<IncomeSummeryResponseDTO> showIncomePerDay(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
//        logger.info("하루에 대한 매출 조회하기");
//        return ResponseEntity.ok(incomeService.showIncomeByDay(date));
//    }


//    @GetMapping("/income/day")
//    public ModelAndView showIncomePerDay(@RequestParam(value = "oneDay", name = "oneDay", required = false)
//                                             @DateTimeFormat(pattern = "yyyy-MM-dd") Date oneDay) {
//        ModelAndView mv = new ModelAndView("/income/multi");
//
//        logger.info("하루에 대한 매출 조회하기");
//        IncomeSummeryResponseDTO incomeSummery = incomeService.showIncomeByDay(convertFromDateToLocalDate(oneDay));
//        mv.addObject("oneDay", oneDay);
////        mv.addObject("dateRange", dateRange);
//        mv.addObject("incomeSummery", incomeSummery);
//        return mv;
//    }

    @GetMapping("/income/day")
    public ModelAndView showIncomePerDay(@ModelAttribute DateRange dateRange) {
        ModelAndView mv = new ModelAndView("/income/multi");

        logger.info("하루에 대한 매출 조회하기");
//        dateRange.setDateTo(dateRange.getDateTo());
        IncomeSummeryResponseDTO incomeSummery = incomeService.showIncomeByDay(convertFromDateToLocalDate(dateRange.getDateFrom()));
//        mv.addObject("oneDay", dateRange.getDateFrom());
        mv.addObject("dateRange", dateRange);
        mv.addObject("incomeSummery", incomeSummery);
        return mv;
    }

    @GetMapping("/income/month/{yearMonth}")
    public ResponseEntity<IncomeSummeryResponseDTO> showIncomePerMonth(@PathVariable @DateTimeFormat(pattern = "yyyy-MM")YearMonth yearMonth) {
        logger.info("월별 매출 조회하기");
        return ResponseEntity.ok(incomeService.showIncomePerMonth(yearMonth));
    }

    @GetMapping("/income/year/{year}")
    public ResponseEntity<IncomeSummeryResponseDTO> showIncomePerYear(@PathVariable @DateTimeFormat(pattern = "yyyy")Year year) {
        logger.info("년별 매출 조회하기");
        return ResponseEntity.ok(incomeService.showIncomePerYear(year));
    }

    @GetMapping("/income/period")
    public ModelAndView showIncomeByPeriod(@ModelAttribute DateRange dateRange) {
        logger.info("기간별 매출 조회하기");

        ModelAndView mv = new ModelAndView("/income/multi");
        IncomeSummeryResponseDTO incomeSummery = incomeService.showIncomeByPeriod(convertFromDateToLocalDate(dateRange.getDateFrom()), convertFromDateToLocalDate(dateRange.getDateTo()));
        mv.addObject("incomeSummery", incomeSummery);
        mv.addObject("dateRange", dateRange);
        return mv;
    }

//    @GetMapping("/income/period")
//    public ResponseEntity<IncomeSummeryResponseDTO> showIncomeByPeriod(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
//                                                                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
//        logger.info("기간별 매출 조회하기");
//        return ResponseEntity.ok(incomeService.showIncomeByPeriod(startDate, endDate));
//    }

    @GetMapping("/income/patient/{patientId}")
    public ResponseEntity<IncomeSummeryResponseDTO> showIncomeByPatient(@PathVariable Long patientId) {
        logger.info("환자 1명에 대한 매출 조회하기");
        return ResponseEntity.ok(incomeService.showIncomeByPatient(patientId));
    }

    @GetMapping("{patientId}/income/create")
    public ModelAndView createIncomeForm(@PathVariable Long patientId) {
        ModelAndView mv = new ModelAndView("income/createIncome");
        logger.info("매출 폼 등록하기");
        mv.addObject("income", new IncomeRequestDTO());
        mv.addObject("patientId", patientId);
        PatientResponseDTO patient = patientService.showPatient(patientId);
        mv.addObject("patient", patient);
        return mv;
    }

    @PostMapping("{patientId}/income/create")
    public ModelAndView addIncome(@ModelAttribute ("income") @RequestBody IncomeRequestDTO incomeRequestDTO,
                                  @ModelAttribute PatientResponseDTO patient,
                                  @PathVariable Long patientId) {
        logger.info("매출 추가하기");
        ModelAndView mv = new ModelAndView("redirect:/patient/" + patientId);
        incomeService.addIncome(incomeRequestDTO, patientId);
        return mv;
    }

    @GetMapping("/patient/{patientId}/income/{incomeId}/modify")
    public ModelAndView modifyIncomeForm(@PathVariable Long patientId,
                                         @PathVariable Long incomeId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("income/modify");
        logger.info("매출 세부정보 수정하기 폼");
        mv.addObject("patientId", patientId);
        PatientResponseDTO patient = patientService.showPatient(patientId);
        IncomeResponseDTO income = incomeService.showIncome(incomeId);
        mv.addObject("patient", patient);
        mv.addObject("income", income);
        return mv;
    }

    @PutMapping("/patient/{patientId}/income/{incomeId}/modify")
    public ModelAndView updateIncomeInfo(@PathVariable Long patientId,
                                                        @ModelAttribute @RequestBody UpdateIncomeInfoRequestDTO incomeInfoRequestDTO,
                                                        @ModelAttribute PatientResponseDTO patient,
                                                        @ModelAttribute IncomeResponseDTO income,
                                                        @PathVariable Long incomeId) {
        logger.info("매출 세부정보 수정하기");
        ModelAndView mv = new ModelAndView("redirect:/patient/" + patientId);
        incomeService.updateIncomeInfo(incomeInfoRequestDTO, incomeId);
        return mv;
    }


    @PutMapping("/income/{incomeId}/patient")
    public ResponseEntity<ResponseDTO> updateIncomePatient(@RequestBody UpdateIncomePatientRequestDTO incomePatientRequestDTO, @PathVariable Long incomeId) {
        logger.info("매출에 해당하는 환자 수정하기");
        incomeService.updateIncomePatient(incomePatientRequestDTO, incomeId);
        return ResponseEntity.ok(new ResponseDTO("OK"));
    }

    @DeleteMapping("/income/{patientId}/{incomeId}")
    public ModelAndView deleteIncome(@PathVariable Long patientId,
                                     @PathVariable Long incomeId) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("incomeId", incomeId);
        mv.setViewName("redirect:/patient/" + patientId);
        logger.info("매출 삭제하기");
        incomeService.deleteIncome(incomeId);
        return mv;
    }



}
