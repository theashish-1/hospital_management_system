package com.example.database.Controller;

import com.example.database.DTO.BillRequestDTO;
import com.example.database.DTO.BillResponseDTO;
import com.example.database.Service.BillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/bill")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService){
        this.billService = billService;
    }

    @PostMapping("/generateBill")
    public ResponseEntity<BillResponseDTO> billGeneration(@RequestBody BillRequestDTO billRequestDTO){
        return ResponseEntity.ok(billService.generateBillOfPatient(billRequestDTO));
    }



}
