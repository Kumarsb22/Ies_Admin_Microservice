package com.ies.services;

import ch.qos.logback.core.model.INamedModel;
import com.ies.binding.PlanForm;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface PlanService {

    public boolean createPlan(PlanForm planForm);
    public List<PlanForm> fetchPlans();
    public PlanForm getPlanById(Integer planId);
    public String changePlanStatus(Integer planId,String status);
}
