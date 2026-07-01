package com.insurance.plan_service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.insurance.plan_service.dto.ParamDto;
import com.insurance.plan_service.entity.Plan;
import com.insurance.plan_service.entity.PlanBenefit;

@Component
public class ParamMapper {

    public List<ParamDto> mapPlanBenefitsToParams(Plan plan) {

        List<ParamDto> params = new ArrayList<>();

        for (PlanBenefit benefit : plan.getBenefits()) {

            params.add(createVisitLimitParam(benefit));

            params.add(createCopayParam(benefit));

            params.add(createCoinsuranceParam(benefit));

            params.add(createAuthorizationParam(benefit));

            params.add(createReferralParam(benefit));
        }

        return params;
    }

    private ParamDto createVisitLimitParam(PlanBenefit benefit) {

        return ParamDto.builder()
                .paramId("visit_limit_" + benefit.getBenefitCode())
                .benefitCode(benefit.getBenefitCode())
                .changedParam("limit_count")
                .label(benefit.getDescription() + " Visit Limit")
                .category("Visit Limits")
                .dataType("integer")
                .currentValue(benefit.getLimitCount())
                .minValue(null)
                .maxValue(null)
                .unit(benefit.getLimitUnit())
                .limitPeriod(benefit.getLimitPeriod())
                .editable(true)
                .build();
    }

    private ParamDto createCopayParam(PlanBenefit benefit) {

        return ParamDto.builder()
                .paramId("copay_" + benefit.getBenefitCode())
                .benefitCode(benefit.getBenefitCode())
                .changedParam("copay")
                .label(benefit.getDescription() + " Copay")
                .category("Copay")
                .dataType("decimal")
                .currentValue(benefit.getCopay())
                .minValue(null)
                .maxValue(null)
                .unit("USD")
                .editable(true)
                .build();
    }

    private ParamDto createCoinsuranceParam(PlanBenefit benefit) {

        return ParamDto.builder()
                .paramId("coinsurance_" + benefit.getBenefitCode())
                .benefitCode(benefit.getBenefitCode())
                .changedParam("coinsurance_pct")
                .label(benefit.getDescription() + " Coinsurance")
                .category("Coinsurance")
                .dataType("decimal")
                .currentValue(benefit.getCoinsurancePct())
                .minValue(null)
                .maxValue(null)
                .unit("%")
                .editable(true)
                .build();
    }

    private ParamDto createAuthorizationParam(PlanBenefit benefit) {

        return ParamDto.builder()
                .paramId("auth_" + benefit.getBenefitCode())
                .benefitCode(benefit.getBenefitCode())
                .changedParam("auth_required")
                .label(benefit.getDescription() + " Authorization")
                .category("Authorization")
                .dataType("boolean")
                .currentValue(benefit.getAuthRequired())
                .editable(true)
                .build();
    }

    private ParamDto createReferralParam(PlanBenefit benefit) {

        return ParamDto.builder()
                .paramId("referral_" + benefit.getBenefitCode())
                .benefitCode(benefit.getBenefitCode())
                .changedParam("referral_required")
                .label(benefit.getDescription() + " Referral")
                .category("Referral")
                .dataType("boolean")
                .currentValue(benefit.getReferralRequired())
                .editable(true)
                .build();
    }
}