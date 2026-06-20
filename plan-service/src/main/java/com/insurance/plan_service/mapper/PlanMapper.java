package com.insurance.plan_service.mapper;

import com.insurance.plan_service.dto.*;
import com.insurance.plan_service.entity.Plan;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PlanMapper {

    // GET /plans
    public PlanSummaryDTO mapToSummaryDTO(Plan plan) {

        PlanSummaryDTO dto = new PlanSummaryDTO();

        dto.setPlanId(plan.getPlanId());
        dto.setPlanName(plan.getPlanName());
        dto.setLineOfBusiness(plan.getLineOfBusiness());
        dto.setPlanType(plan.getPlanType());
        dto.setStatus(plan.getStatus());

        return dto;
    }

    // GET /plans/{planId}
    public PlanDetailDTO mapToDetailDTO(Plan plan) {

        PlanDetailDTO dto = new PlanDetailDTO();

        dto.setPlanId(plan.getPlanId());
        dto.setPlanName(plan.getPlanName());
        dto.setLineOfBusiness(plan.getLineOfBusiness());
        dto.setPlanType(plan.getPlanType());
        dto.setStatus(plan.getStatus());

        dto.setMemberCount(plan.getMemberCount());
        dto.setNetworkType(plan.getNetworkType());
        dto.setDescription(plan.getDescription());
        dto.setBaselineVersion(plan.getBaselineVersion());

        // Deductible
        if (plan.getDeductible() != null) {

            DeductibleDTO deductibleDTO =
                    new DeductibleDTO();

            deductibleDTO.setIndividual(
                    plan.getDeductible().getIndividualAmount());

            deductibleDTO.setFamily(
                    plan.getDeductible().getFamilyAmount());

            deductibleDTO.setUnit(
                    plan.getDeductible().getUnit());

            dto.setDeductible(deductibleDTO);
        }

        // Out Of Pocket Max
        if (plan.getOutOfPocketMax() != null) {

            OutOfPocketMaxDTO oopDTO =
                    new OutOfPocketMaxDTO();

            oopDTO.setIndividual(
                    plan.getOutOfPocketMax().getIndividualAmount());

            oopDTO.setFamily(
                    plan.getOutOfPocketMax().getFamilyAmount());

            oopDTO.setUnit(
                    plan.getOutOfPocketMax().getUnit());

            dto.setOutOfPocketMax(oopDTO);
        }

        // Coverage Details
        if (plan.getCoverageDetail() != null) {

            CoverageDetailDTO coverageDTO =
                    new CoverageDetailDTO();

            coverageDTO.setPreventiveCare(
                    plan.getCoverageDetail().getPreventiveCare());

            coverageDTO.setGenericDrugs(
                    plan.getCoverageDetail().getGenericDrugs());

            coverageDTO.setBrandDrugs(
                    plan.getCoverageDetail().getBrandDrugs());

            coverageDTO.setSpecialtyDrugs(
                    plan.getCoverageDetail().getSpecialtyDrugs());

            coverageDTO.setMentalHealthParity(
                    plan.getCoverageDetail().getMentalHealthParity());

            coverageDTO.setTelehealth(
                    plan.getCoverageDetail().getTelehealth());

            dto.setCoverageDetails(coverageDTO);
        }

        // Copays
        if (plan.getCopays() != null) {

            dto.setCopays(
                    plan.getCopays()
                            .stream()
                            .map(copay -> {

                                CopayDTO copayDTO =
                                        new CopayDTO();

                                copayDTO.setServiceType(
                                        copay.getServiceType());

                                copayDTO.setAmount(
                                        copay.getAmount());

                                copayDTO.setUnit(
                                        copay.getUnit());

                                return copayDTO;
                            })
                            .collect(Collectors.toList())
            );
        }

        // Visit Limits
        if (plan.getVisitLimits() != null) {

            dto.setVisitLimits(
                    plan.getVisitLimits()
                            .stream()
                            .map(limit -> {

                                VisitLimitDTO visitDTO =
                                        new VisitLimitDTO();

                                visitDTO.setServiceType(
                                        limit.getServiceType());

                                visitDTO.setLimit(
                                        limit.getLimitCount());

                                visitDTO.setUnit(
                                        limit.getUnit());

                                return visitDTO;
                            })
                            .collect(Collectors.toList())
            );
        }

        // Authorization Rules
        if (plan.getAuthorizationRules() != null) {

            dto.setAuthorizationRules(
                    plan.getAuthorizationRules()
                            .stream()
                            .map(rule -> {

                                AuthorizationRuleDTO authDTO =
                                        new AuthorizationRuleDTO();

                                authDTO.setServiceType(
                                        rule.getServiceType());

                                authDTO.setPriorAuthRequired(
                                        rule.getPriorAuthRequired());

                                return authDTO;
                            })
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }
}