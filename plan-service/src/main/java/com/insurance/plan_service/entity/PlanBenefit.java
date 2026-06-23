package com.insurance.plan_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "plan_benefit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanBenefit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne
    @JoinColumn(name = "plan_id")
    @JsonIgnore
    private Plan plan;
}