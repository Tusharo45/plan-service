package com.insurance.plan_service.dto;

import java.util.List;

import lombok.Data;

@Data
public class PlanSearchResponseDTO {

    private Integer page;

    private Integer size;

    private Long totalCount;

    private List<PlanSummaryDTO> plans;
}