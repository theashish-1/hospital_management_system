package com.example.database.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.database.DTO.AdminStatsSummaryDTO;
import com.example.database.Service.AdminService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminStatsController {
    private final AdminService  adminService;

    @GetMapping("/stats/summary")
    public ResponseEntity<AdminStatsSummaryDTO> getSummary() {
        return ResponseEntity.ok(adminService.hospitalSummary());
        
    }
    
}
