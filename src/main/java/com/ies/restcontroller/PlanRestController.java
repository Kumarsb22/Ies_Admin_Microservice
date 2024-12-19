package com.ies.restcontroller;

import com.ies.binding.PlanForm;
import com.ies.constants.AppConstants;
import com.ies.services.PlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/plan")
@RestController
public class PlanRestController {

    Logger logger = LoggerFactory.getLogger(PlanRestController.class);

    private PlanService planService;

    @Autowired
    public PlanRestController(PlanService planService){
        this.planService=planService;
    }

    @PostMapping("/plan")
    public ResponseEntity<String> createPlan(@RequestBody PlanForm planForm){
        logger.debug(AppConstants.STR_PLAN_CR_P_ST);
        boolean plan = this.planService.createPlan(planForm);
        logger.debug(AppConstants.STR_PLAN_CR_P_COM);
        if (plan){
            logger.info(AppConstants.STR_PLAN_CR_SUCC);
            return new ResponseEntity<>(AppConstants.STR_PLAN_CR_SUCC, HttpStatus.CREATED);
        }
        logger.info(AppConstants.STR_PLAN_CR_FAILED);
        return new ResponseEntity<>(AppConstants.STR_PLAN_CR_FAILED,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/plans")
    public ResponseEntity<List<PlanForm>> fetchPlans(){
        logger.info(AppConstants.STR_FETCTED_PLANS_SUCC);
        return new ResponseEntity<>(this.planService.fetchPlans(),HttpStatus.OK);
    }

    @GetMapping("/plan/{planId}")
    public ResponseEntity<PlanForm> fetchPlanById(@PathVariable("planId") Integer planId){
        logger.info(AppConstants.STR_FETCTED_PLAN_SUCC);
        return new ResponseEntity<>(this.planService.getPlanById(planId),HttpStatus.OK);
    }

    @PutMapping("/plan/{planId}/{status}")
    public ResponseEntity<List<PlanForm>> updatePlanstatus(@PathVariable("planId") Integer planId,@PathVariable("status") String status){
        logger.debug(AppConstants.STR_UPDATE_PLAN_PR_ST);
        this.planService.changePlanStatus(planId,status);
        logger.debug(AppConstants.STR_UPDATE_PLAN_PR_COM);
        logger.info(AppConstants.STR_UPDATE_PLAN_SUCC);
        return new ResponseEntity<>(this.planService.fetchPlans(),HttpStatus.OK);

    }



}
