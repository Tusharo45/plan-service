package com.insurance.plan_service.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParamsResponseDto {

    private Long planId;

    private String baselineVersion;

    private List<ParamDto> params;

}