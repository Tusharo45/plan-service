package com.insurance.plan_service.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PlanBenefitDTO {

    private String benefitCode;

    private String description;

    private Integer limitCount;

    private String limitPeriod;

    private String limitUnit;

    private Double deductible;

    private Double copay;

    private Double coinsurancePct;

    private Boolean authRequired;

    private Boolean referralRequired;

    private String coverageLevel;

    private Boolean inNetworkCovered;

    private Boolean outNetworkCovered;

    private LocalDate effectiveStart;

    private LocalDate effectiveEnd;
}