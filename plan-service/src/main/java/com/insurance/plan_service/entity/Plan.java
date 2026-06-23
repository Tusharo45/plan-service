package com.insurance.plan_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "plans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plan {

    @Id
    private Long planId;

    private String planName;

    private LocalDate startDate;

    private LocalDate endDate;

    private String lineOfBusiness;

    private String planType;

    private String networkType;

    private String marketSegment;

    private String state;

    private Integer memberCount;

    private String baselineVersion;

    private Integer minLimitAllowed;

    private Integer maxLimitAllowed;

    @OneToMany(
            mappedBy = "plan",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<PlanBenefit> benefits;
}