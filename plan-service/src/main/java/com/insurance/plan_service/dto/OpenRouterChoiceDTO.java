package com.insurance.plan_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenRouterChoiceDTO {

    private OpenRouterMessageDTO message;

}