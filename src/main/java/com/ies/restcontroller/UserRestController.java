package com.ies.restcontroller;

import com.ies.binding.DashboardCardForm;
import com.ies.binding.LoginForm;
import com.ies.binding.UserAccountForm;
import com.ies.constants.AppConstants;
import com.ies.services.UserService;
import com.ies.services.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRestController {

    Logger logger= LoggerFactory.getLogger(UserRestController.class);

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService){
        this.userService=userService;
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginForm loginForm){
        logger.debug(AppConstants.STR_LOGIN_PR_ST);
        String status = this.userService.login(loginForm);
        logger.debug(AppConstants.STR_LOGIN_PR_COM);
        if (status.equals(AppConstants.STR_SUCCESS)){
            logger.info(AppConstants.STR_LOGIN_SUCC);
            return new ResponseEntity<>(AppConstants.STR_DASHBOAD_URL+loginForm.getEmail(),HttpStatus.OK);
        }
        return new ResponseEntity<>(status,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardCardForm> dashboard(@RequestParam("email") String  email){
        logger.debug(AppConstants.STR_FETCH_DASHBO_PR_ST);
        DashboardCardForm cardForm = this.userService.fecthDashboardInfo();
        UserAccountForm userAccountForm = this.userService.fetchUser(email);
        logger.debug(AppConstants.STR_FETCH_DASHBO_PR_COM);
        cardForm.setUserAccountForm(userAccountForm);
        logger.info(AppConstants.STR_FETCH_DASHBO_SUCC);
        return  new ResponseEntity<>(cardForm, HttpStatus.OK);
    }








}
