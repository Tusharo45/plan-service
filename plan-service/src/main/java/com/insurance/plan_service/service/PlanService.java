package com.insurance.plan_service.service;

import com.insurance.plan_service.dto.PlanDetailDTO;
import com.insurance.plan_service.dto.PlanSummaryDTO;
import com.insurance.plan_service.entity.Plan;
import com.insurance.plan_service.exception.ResourceNotFoundException;
import com.insurance.plan_service.mapper.PlanMapper;
import com.insurance.plan_service.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;
    private final PlanMapper planMapper;

    // GET /plans/{planId}
    public PlanDetailDTO getPlanById(String planId) {

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Plan Not Found With Id : " + planId));

        return planMapper.mapToDetailDTO(plan);
    }

    // GET /plans
    public List<PlanSummaryDTO> getAllPlans(
            String lob,
            String planType,
            String status,
            Pageable pageable) {

        return planRepository
                .findByLineOfBusinessAndPlanTypeAndStatus(
                        lob,
                        planType,
                        status,
                        pageable)
                .getContent()
                .stream()
                .map(planMapper::mapToSummaryDTO)
                .collect(Collectors.toList());
    }
}