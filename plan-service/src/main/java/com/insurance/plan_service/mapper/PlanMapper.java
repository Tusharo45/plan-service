package com.insurance.plan_service.mapper;

import com.insurance.plan_service.dto.PlanSummaryDTO;
import com.insurance.plan_service.entity.Plan;
import org.springframework.stereotype.Component;

@Component
public class PlanMapper {

    public PlanSummaryDTO mapToSummaryDTO(Plan plan){

        PlanSummaryDTO dto =
                new PlanSummaryDTO();

        dto.setPlanId(plan.getPlanId());
        dto.setPlanName(plan.getPlanName());
        dto.setLineOfBusiness(
                plan.getLineOfBusiness());
        dto.setPlanType(plan.getPlanType());
        dto.setStatus(plan.getStatus());

        return dto;
    }
}