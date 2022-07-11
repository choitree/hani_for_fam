package com.haniwon.controller;

import java.time.LocalDate;
import java.util.Date;

import com.haniwon.dto.DateRange;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {

    @GetMapping({ "/", "/index" })
    public String main(Model model) {
        DateRange dateRange = new DateRange();
        dateRange.setDateFrom(new Date());
        dateRange.setDateTo(new Date());
        model.addAttribute("dateRange", dateRange);
        return "index";
    }

    @GetMapping("/picker")
    public String dateRange(Model model) {
        return "daterangepicker";
    }

    @GetMapping("modal")
    public String modal() {
        return "modal/index";
    }

    @GetMapping("modals/modal1")
    public String modal1() {
        return "modal/modal1";
    }

    @GetMapping("modals/modal2")
    public String modal2(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "modal/modal2";
    }

    @PostMapping
    public String save(DateRange dateRange, Model model) {
        model.addAttribute("dateRange", dateRange);
        return "saved";
    }
}