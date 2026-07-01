package com.insurance.plan_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.insurance.plan_service.dto.ParamDto;
import com.insurance.plan_service.dto.ParamsResponseDto;
import com.insurance.plan_service.dto.PlanDetailDTO;
import com.insurance.plan_service.dto.PlanSearchResponseDTO;
import com.insurance.plan_service.dto.PlanSummaryDTO;
import com.insurance.plan_service.entity.Plan;
import com.insurance.plan_service.exception.ResourceNotFoundException;
import com.insurance.plan_service.mapper.ParamMapper;
import com.insurance.plan_service.mapper.PlanMapper;
import com.insurance.plan_service.repository.PlanRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanService {

    private static final Logger logger =
            LoggerFactory.getLogger(PlanService.class);

    private final PlanRepository planRepository;
    private final PlanMapper planMapper;
    private final ParamMapper paramMapper;

    // GET /api/v1/plans
    public PlanSearchResponseDTO getPlans(
            int page,
            int size) {

        logger.info(
                "Fetching plans. page={}, size={}",
                page,
                size);

        Pageable pageable =
                PageRequest.of(page, size);

        Page<Plan> planPage =
                planRepository.findAll(pageable);

        logger.info(
                "Found {} plans",
                planPage.getTotalElements());

        List<PlanSummaryDTO> plans =
                planPage.getContent()
                        .stream()
                        .map(planMapper::mapToSummaryDTO)
                        .collect(Collectors.toList());

        PlanSearchResponseDTO response =
                new PlanSearchResponseDTO();

        response.setPage(page);
        response.setSize(size);
        response.setTotalCount(
                planPage.getTotalElements());

        response.setPlans(plans);

        return response;
    }

    // GET /api/v1/plans/{planId}
    public PlanDetailDTO getPlanById(
            Long planId) {

        logger.info(
                "Fetching plan details for planId={}",
                planId);

        Plan plan =
                planRepository.findById(planId)
                        .orElseThrow(() -> {

                            logger.error(
                                    "Plan not found. planId={}",
                                    planId);

                            return new ResourceNotFoundException(
                                    "Plan Not Found With Id : "
                                            + planId);
                        });

        logger.info(
                "Successfully found plan {}",
                plan.getPlanName());

        return planMapper.mapToDetailDTO(plan);
    }

    // GET /api/v1/plans/{planId}/params
    public ParamsResponseDto getEditableParams(
            Long planId) {

        logger.info(
                "Fetching editable parameters for planId={}",
                planId);

        Plan plan =
                planRepository.findById(planId)
                        .orElseThrow(() -> {

                            logger.error(
                                    "Plan not found. planId={}",
                                    planId);

                            return new ResourceNotFoundException(
                                    "Plan Not Found With Id : "
                                            + planId);
                        });

        List<ParamDto> params =
                paramMapper.mapPlanBenefitsToParams(plan);

        logger.info(
                "Generated {} editable parameters",
                params.size());

        return ParamsResponseDto.builder()
                .planId(plan.getPlanId())
                .baselineVersion(plan.getBaselineVersion())
                .params(params)
                .build();
    }

}