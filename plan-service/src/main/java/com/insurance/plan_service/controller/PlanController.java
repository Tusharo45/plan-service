package com.insurance.plan_service.controller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.insurance.plan_service.entity.Plan;
import com.insurance.plan_service.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;
    @GetMapping
    public Page<Plan> getPlans(

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
    @GetMapping("/{planId}")
    public Plan getPlanById(
            @PathVariable String planId) {

        return planService.getPlanById(planId);
    }
}