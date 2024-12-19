package com.ies.services;

import com.ies.binding.PlanForm;
import com.ies.constants.AppConstants;
import com.ies.entities.PlansEntitiy;
import com.ies.repositories.PlansRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class PlanServiceImpl implements  PlanService{

    private PlansRepo plansRepo;

    @Autowired
    public PlanServiceImpl(PlansRepo plansRepo){
        this.plansRepo=plansRepo;
    }

    @Override
    public boolean createPlan(PlanForm planForm) {
        PlansEntitiy plansEntitiy=new PlansEntitiy();
        BeanUtils.copyProperties(planForm,plansEntitiy);
        this.plansRepo.save(plansEntitiy);
        return false;
    }

    @Override
    public List<PlanForm> fetchPlans() {
      List<PlansEntitiy> list = this.plansRepo.findAll();
      List<PlanForm> planForms=new ArrayList<>();

      if(list!=null){
          for(PlansEntitiy pe: list){
              PlanForm planForm=new PlanForm();
              BeanUtils.copyProperties(pe,planForm);
              planForms.add(planForm);
          }
      }
        return planForms;
    }

    @Override
    public PlanForm getPlanById(Integer planId) {
        if(planId!=null){
            PlanForm planForm =new PlanForm();
         Optional<PlansEntitiy> plansEntitiy = this.plansRepo.findById(planId);
         if(plansEntitiy.isPresent()){
             BeanUtils.copyProperties(plansEntitiy.get(),planForm);
         }
            return planForm;
        }
        return null;
    }

    @Override
    public String changePlanStatus(Integer planId, String status) {
        Integer count = this.plansRepo.updatePlansStatus(planId, status);
        if(count>0){
            return AppConstants.STR_STS_CGD;
        }
        return  AppConstants.STR_STS_NOT_CGD;
    }
}
