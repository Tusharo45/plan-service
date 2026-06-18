package com.insurance.plan_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class PlanListResponseDTO {

    private int page;

    private int size;

    private long totalCount;

    private List<PlanSummaryDTO> plans;
}