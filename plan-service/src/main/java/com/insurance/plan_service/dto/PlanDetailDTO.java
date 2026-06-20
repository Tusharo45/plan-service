package com.insurance.plan_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class PlanDetailDTO {

    private String planId;

    private String planName;

    private String lineOfBusiness;

    private String planType;

    private String status;

    private Integer memberCount;

    private String networkType;

    private String description;

    private String baselineVersion;

    private DeductibleDTO deductible;

    private OutOfPocketMaxDTO outOfPocketMax;

    private CoverageDetailDTO coverageDetails;

    private List<CopayDTO> copays;

    private List<VisitLimitDTO> visitLimits;

    private List<AuthorizationRuleDTO> authorizationRules;
}