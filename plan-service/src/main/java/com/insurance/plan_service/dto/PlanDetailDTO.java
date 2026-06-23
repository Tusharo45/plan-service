package com.insurance.plan_service.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class PlanDetailDTO {

    private Long planId;

    private String planName;

    private String lineOfBusiness;

    private String planType;

    private String networkType;

    private String marketSegment;

    private String state;

    private LocalDate effectiveDate;

    private LocalDate expiryDate;

    private Integer memberCount;

    private String baselineVersion;

    private List<PlanBenefitDTO> benefits;
}