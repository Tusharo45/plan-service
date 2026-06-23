package com.insurance.plan_service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.insurance.plan_service.dto.PlanBenefitDTO;
import com.insurance.plan_service.dto.PlanDetailDTO;
import com.insurance.plan_service.dto.PlanSummaryDTO;
import com.insurance.plan_service.entity.Plan;
import com.insurance.plan_service.entity.PlanBenefit;

@Component
public class PlanMapper {

    // Plan -> PlanSummaryDTO
    public PlanSummaryDTO mapToSummaryDTO(Plan plan) {

        PlanSummaryDTO dto = new PlanSummaryDTO();

        dto.setPlanId(plan.getPlanId());
        dto.setPlanName(plan.getPlanName());

        dto.setLineOfBusiness(
                plan.getLineOfBusiness());

        dto.setPlanType(
                plan.getPlanType());

        dto.setNetworkType(
                plan.getNetworkType());

        dto.setMarketSegment(
                plan.getMarketSegment());

        dto.setState(
                plan.getState());

        dto.setEffectiveDate(
                plan.getStartDate());

        dto.setExpiryDate(
                plan.getEndDate());

        dto.setMemberCount(
                plan.getMemberCount());

        dto.setBaselineVersion(
                plan.getBaselineVersion());

        return dto;
    }

    // PlanBenefit -> PlanBenefitDTO
    public PlanBenefitDTO mapBenefitDTO(
            PlanBenefit benefit) {

        PlanBenefitDTO dto =
                new PlanBenefitDTO();

        dto.setBenefitCode(
                benefit.getBenefitCode());

        dto.setDescription(
                benefit.getDescription());

        dto.setLimitCount(
                benefit.getLimitCount());

        dto.setLimitPeriod(
                benefit.getLimitPeriod());

        dto.setLimitUnit(
                benefit.getLimitUnit());

        dto.setDeductible(
                benefit.getDeductible());

        dto.setCopay(
                benefit.getCopay());

        dto.setCoinsurancePct(
                benefit.getCoinsurancePct());

        dto.setAuthRequired(
                benefit.getAuthRequired());

        dto.setReferralRequired(
                benefit.getReferralRequired());

        dto.setCoverageLevel(
                benefit.getCoverageLevel());

        dto.setInNetworkCovered(
                benefit.getInNetworkCovered());

        dto.setOutNetworkCovered(
                benefit.getOutNetworkCovered());

        dto.setEffectiveStart(
                benefit.getEffectiveStart());

        dto.setEffectiveEnd(
                benefit.getEffectiveEnd());

        return dto;
    }

    // Plan -> PlanDetailDTO
    public PlanDetailDTO mapToDetailDTO(
            Plan plan) {

        PlanDetailDTO dto =
                new PlanDetailDTO();

        dto.setPlanId(plan.getPlanId());

        dto.setPlanName(
                plan.getPlanName());

        dto.setLineOfBusiness(
                plan.getLineOfBusiness());

        dto.setPlanType(
                plan.getPlanType());

        dto.setNetworkType(
                plan.getNetworkType());

        dto.setMarketSegment(
                plan.getMarketSegment());

        dto.setState(
                plan.getState());

        dto.setEffectiveDate(
                plan.getStartDate());

        dto.setExpiryDate(
                plan.getEndDate());

        dto.setMemberCount(
                plan.getMemberCount());

        dto.setBaselineVersion(
                plan.getBaselineVersion());

        List<PlanBenefitDTO> benefits =
                plan.getBenefits()
                        .stream()
                        .map(this::mapBenefitDTO)
                        .collect(Collectors.toList());

        dto.setBenefits(benefits);

        return dto;
    }
}