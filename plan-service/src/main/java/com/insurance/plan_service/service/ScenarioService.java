package com.insurance.plan_service.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.insurance.plan_service.dto.AIScenarioResponseDTO;
import com.insurance.plan_service.dto.GenerateScenarioRequestDTO;
import com.insurance.plan_service.dto.GenerateScenarioResponseDTO;
import com.insurance.plan_service.dto.ScenarioDTO;
import com.insurance.plan_service.dto.ScenarioParamDTO;
import com.insurance.plan_service.entity.Plan;
import com.insurance.plan_service.entity.SimulationScenario;
import com.insurance.plan_service.entity.SimulationScenarioParam;
import com.insurance.plan_service.exception.ResourceNotFoundException;
import com.insurance.plan_service.repository.PlanRepository;
import com.insurance.plan_service.repository.SimulationScenarioParamRepository;
import com.insurance.plan_service.repository.SimulationScenarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScenarioService {

    private static final Logger logger =
            LoggerFactory.getLogger(ScenarioService.class);

    private final PlanRepository planRepository;

    private final SimulationScenarioRepository simulationScenarioRepository;

    private final SimulationScenarioParamRepository simulationScenarioParamRepository;

    private final AIScenarioGenerator aiScenarioGenerator;

    public GenerateScenarioResponseDTO generateScenario(
            GenerateScenarioRequestDTO request) {

        logger.info("Generating scenario for planId={}",
                request.getPlanId());

        // -----------------------------------------------------
        // Validate Plan
        // -----------------------------------------------------
        Plan plan = planRepository.findById(request.getPlanId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Plan Not Found With Id : "
                                        + request.getPlanId()));

        // -----------------------------------------------------
        // Save User Scenario
        // -----------------------------------------------------
        SimulationScenario userScenario =
                SimulationScenario.builder()
                        .planId(plan.getPlanId())
                        .baselineVersion(request.getBaselineVersion())
                        .label("Your Configuration")
                        .isUserDefined(true)
                        .parentScenarioId(null)
                        .aiRationale(null)
                        .createdAt(LocalDateTime.now())
                        .build();

        SimulationScenario savedUserScenario =
                simulationScenarioRepository.save(userScenario);

        logger.info("User Scenario Saved : {}",
                savedUserScenario.getScenarioId());

        // -----------------------------------------------------
        // Save User Parameters
        // -----------------------------------------------------
        for (ScenarioParamDTO edit : request.getUserEdits()) {

            SimulationScenarioParam param =
                    SimulationScenarioParam.builder()
                            .scenario(savedUserScenario)
                            .benefitCode(edit.getBenefitCode())
                            .changedParam(edit.getChangedParam())
                            .oldValue(String.valueOf(edit.getOldValue()))
                            .newValue(String.valueOf(edit.getNewValue()))
                            .build();

            simulationScenarioParamRepository.save(param);
        }

        logger.info("User Parameters Saved");

        // -----------------------------------------------------
        // Call AI
        // -----------------------------------------------------
        AIScenarioResponseDTO aiResponse =
                aiScenarioGenerator.generateScenarios(request);

        // -----------------------------------------------------
        // Build Final Response
        // -----------------------------------------------------
        List<ScenarioDTO> scenarios = new ArrayList<>();

        // Add User Scenario
        scenarios.add(
                ScenarioDTO.builder()
                        .scenarioId(savedUserScenario.getScenarioId())
                        .label(savedUserScenario.getLabel())
                        .isUserDefined(true)
                        .parentScenarioId(null)
                        .aiRationale(null)
                        .params(request.getUserEdits())
                        .build());

        // -----------------------------------------------------
        // Save AI Scenarios
        // -----------------------------------------------------
        for (ScenarioDTO aiScenario : aiResponse.getScenarios()) {

            SimulationScenario aiEntity =
                    SimulationScenario.builder()
                            .planId(plan.getPlanId())
                            .baselineVersion(request.getBaselineVersion())
                            .label(aiScenario.getLabel())
                            .isUserDefined(false)
                            .parentScenarioId(savedUserScenario.getScenarioId())
                            .aiRationale(aiScenario.getAiRationale())
                            .createdAt(LocalDateTime.now())
                            .build();

            SimulationScenario savedAiScenario =
                    simulationScenarioRepository.save(aiEntity);

            logger.info("AI Scenario Saved : {}",
                    savedAiScenario.getScenarioId());

            // Save AI Parameters
            for (ScenarioParamDTO param : aiScenario.getParams()) {

                SimulationScenarioParam entity =
                        SimulationScenarioParam.builder()
                                .scenario(savedAiScenario)
                                .benefitCode(param.getBenefitCode())
                                .changedParam(param.getChangedParam())
                                .oldValue(String.valueOf(param.getOldValue()))
                                .newValue(String.valueOf(param.getNewValue()))
                                .build();

                simulationScenarioParamRepository.save(entity);
            }

            logger.info("AI Parameters Saved");

            scenarios.add(
                    ScenarioDTO.builder()
                            .scenarioId(savedAiScenario.getScenarioId())
                            .label(savedAiScenario.getLabel())
                            .isUserDefined(false)
                            .parentScenarioId(savedUserScenario.getScenarioId())
                            .aiRationale(savedAiScenario.getAiRationale())
                            .params(aiScenario.getParams())
                            .build());
        }

        logger.info("Scenario Generation Completed Successfully");

        return GenerateScenarioResponseDTO.builder()
                .planId(plan.getPlanId())
                .scenarios(scenarios)
                .build();
    }
}