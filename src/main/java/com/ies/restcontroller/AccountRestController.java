package com.ies.restcontroller;

import com.ies.binding.UserAccountForm;
import com.ies.constants.AppConstants;
import com.ies.services.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/user")
public class AccountRestController {

    Logger logger= LoggerFactory.getLogger(AccountRestController.class);

    private AccountService accountService;

    @Autowired
    public AccountRestController( AccountService accountService){
        this.accountService=accountService;
    }

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody UserAccountForm userAccountForm){
        logger.debug(AppConstants.STR_ACC_CR_PR_S);
       Boolean status= this.accountService.createUserAccount(userAccountForm);
        logger.debug(AppConstants.STR_ACC_CR_PR_COM);
       if(status){
           logger.info(AppConstants.STR_ACC_CR_SUCC);
           return new ResponseEntity<>(AppConstants.STR_ACC_CR_SUCC, HttpStatus.CREATED);
       }
           logger.info(AppConstants.STR_USER_ACC_CR_FAILED);
           return new ResponseEntity<>(AppConstants.STR_USER_ACC_CR_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserAccountForm>> getUsers(){
        logger.debug(AppConstants.STR_FETCH_USERS_ACC_ST);
        List<UserAccountForm> userAccountForms = this.accountService.fecthUserAccounts();
        logger.debug(AppConstants.STR_FETCH_USERS_ACC_COM);
        logger.info(AppConstants.STR_FETCH_USERS_ACC_SUCC);
        return new ResponseEntity<>(userAccountForms,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserAccountForm> getUserById(@PathVariable("userId") Integer userId){
        logger.debug(AppConstants.STR_FETCH_USER_ACC_ST);
        UserAccountForm accountForm = this.accountService.getUserAccountById(userId);
        logger.debug(AppConstants.STR_FETCH_USER_ACC_COM);
        logger.info(AppConstants.STR_FETCH_USER_ACC_SUCC);
        return  new ResponseEntity<>(accountForm,HttpStatus.OK);
    }

    @PutMapping("/user/{userId}/{status}")
    public ResponseEntity<List<UserAccountForm>> updateUser(@PathVariable("userId") Integer userId, @PathVariable("status") String status){
        logger.debug(AppConstants.STR_UPDATE_USER_STATUS_ST);

        this.accountService.changeUserAccountStatus(userId, status);
        logger.debug(AppConstants.STR_UPDATE_USER_STATUS_COM);
        logger.info(AppConstants.STR_UPDATE_USER_STATUS_SUCC);
        return new ResponseEntity<>(this.accountService.fecthUserAccounts(),HttpStatus.OK);
    }


}
