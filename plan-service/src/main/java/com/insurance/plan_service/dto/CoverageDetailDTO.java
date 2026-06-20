package com.insurance.plan_service.dto;

import lombok.Data;

@Data
public class CoverageDetailDTO {

    private String preventiveCare;

    private String genericDrugs;

    private String brandDrugs;

    private String specialtyDrugs;

    private Boolean mentalHealthParity;

    private Boolean telehealth;
}