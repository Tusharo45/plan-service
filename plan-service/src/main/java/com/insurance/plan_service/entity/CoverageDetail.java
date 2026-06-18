package com.insurance.plan_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoverageDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String preventiveCare;

    private String genericDrugs;

    private String brandDrugs;

    private String specialtyDrugs;

    private Boolean mentalHealthParity;

    private Boolean telehealth;

    @OneToOne
    @JoinColumn(name = "plan_id")
    @JsonIgnore
    private Plan plan;
}