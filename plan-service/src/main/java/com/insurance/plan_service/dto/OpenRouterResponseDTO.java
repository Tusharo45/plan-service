package com.insurance.plan_service.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenRouterResponseDTO {

    private List<OpenRouterChoiceDTO> choices;

}