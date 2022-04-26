package com.haniwon.controller;

import com.haniwon.dto.ResponseDTO;
import com.haniwon.dto.vendor.request.VendorRequestDTO;
import com.haniwon.dto.vendor.response.VendorResponseDTO;
import com.haniwon.service.VendorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/vendor")
@Controller
public class VendorController {

    private final VendorService vendorService;

    Logger logger = LoggerFactory.getLogger(VendorController.class);

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createVendor(@RequestBody VendorRequestDTO vendorRequestDTO) {
        vendorService.createVendor(vendorRequestDTO);
        return ResponseEntity.ok(new ResponseDTO("ok"));
    }

}
