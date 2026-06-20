package com.insurance.plan_service.controller;

import com.insurance.plan_service.dto.PlanDetailDTO;
import com.insurance.plan_service.dto.PlanSummaryDTO;
import com.insurance.plan_service.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    // GET /plans
    @GetMapping
    public List<PlanSummaryDTO> getPlans(

            @RequestParam String lob,

            @RequestParam String planType,

            @RequestParam String status,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "20")
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        return planService.getAllPlans(
                lob,
                planType,
                status,
                pageable);
    }

    // GET /plans/{planId}
    @GetMapping("/{planId}")
    public PlanDetailDTO getPlanById(
            @PathVariable String planId) {

        return planService.getPlanById(planId);
    }
}