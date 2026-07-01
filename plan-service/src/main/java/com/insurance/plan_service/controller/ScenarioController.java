package com.insurance.plan_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.insurance.plan_service.dto.GenerateScenarioRequestDTO;
import com.insurance.plan_service.dto.GenerateScenarioResponseDTO;
import com.insurance.plan_service.service.ScenarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/scenario")
@RequiredArgsConstructor
public class ScenarioController {

    private static final Logger logger =
            LoggerFactory.getLogger(ScenarioController.class);

    private final ScenarioService scenarioService;

    @PostMapping("/generate")
    public GenerateScenarioResponseDTO generateScenario(
            @RequestBody GenerateScenarioRequestDTO request) {

        logger.info(
                "Received scenario generation request for planId={}",
                request.getPlanId());

        return scenarioService.generateScenario(request);
    }
}