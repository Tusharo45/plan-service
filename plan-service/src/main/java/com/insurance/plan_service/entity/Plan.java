package com.insurance.plan_service.entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Entity
@Table(name = "plans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plan {

    @Id
    private String planId;

    private String planName;

    private String lineOfBusiness;

    private String planType;

    private String status;

    private Integer memberCount;

    private String networkType;

    private String description;

    private String baselineVersion;

    @OneToOne(mappedBy = "plan",
            cascade = CascadeType.ALL)
    private Deductible deductible;

    @OneToOne(mappedBy = "plan",
            cascade = CascadeType.ALL)
    private OutOfPocketMax outOfPocketMax;

    @OneToOne(mappedBy = "plan",
            cascade = CascadeType.ALL)
    private CoverageDetail coverageDetail;

    @OneToMany(mappedBy = "plan",
            cascade = CascadeType.ALL)
    private List<Copay> copays;

    @OneToMany(mappedBy = "plan",
            cascade = CascadeType.ALL)
    private List<VisitLimit> visitLimits;

    @OneToMany(mappedBy = "plan",
            cascade = CascadeType.ALL)
    private List<AuthorizationRule> authorizationRules;
}