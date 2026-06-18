package com.insurance.plan_service.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.insurance.plan_service.entity.Plan;
import com.insurance.plan_service.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;

    public Plan getPlanById(String planId) {

        return planRepository.findById(planId)
                .orElseThrow(() ->
                        new RuntimeException("Plan Not Found"));
    }
    public Page<Plan> getAllPlans(
            String lob,
            String planType,
            String status,
            Pageable pageable) {

        return planRepository
                .findByLineOfBusinessAndPlanTypeAndStatus(
                        lob,
                        planType,
                        status,
                        pageable);
    }
}