package com.insurance.plan_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.plan_service.dto.PlanDetailDTO;
import com.insurance.plan_service.dto.PlanSearchResponseDTO;
import com.insurance.plan_service.service.PlanService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/plans")
@RequiredArgsConstructor
public class PlanController {

    private static final Logger logger =
            LoggerFactory.getLogger(PlanController.class);

    private final PlanService planService;

    @GetMapping
    public PlanSearchResponseDTO getPlans(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "20")
            int size) {

        logger.info(
                "Received request to fetch plans. page={}, size={}",
                page,
                size);

        return planService.getPlans(page, size);
    }

    @GetMapping("/{planId}")
    public PlanDetailDTO getPlanById(
            @PathVariable Long planId) {

        logger.info(
                "Received request for planId={}",
                planId);

        return planService.getPlanById(planId);
    }
}