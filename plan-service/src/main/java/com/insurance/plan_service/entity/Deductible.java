package com.insurance.plan_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Deductible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double individualAmount;

    private Double familyAmount;

    private String unit;

    @OneToOne
    @JoinColumn(name = "plan_id")
    @JsonIgnore
    private Plan plan;
}