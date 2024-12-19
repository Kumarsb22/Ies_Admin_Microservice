package com.ies.services;

import com.ies.binding.UnlockAccForm;
import com.ies.binding.UserAccountForm;
import com.ies.constants.AppConstants;
import com.ies.entities.UserEntity;
import com.ies.repositories.UserRepo;
import com.ies.utils.EmailUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements  AccountService{

    private UserRepo userRepo;

    private EmailUtil emailUtil;

    @Autowired
    public AccountServiceImpl(UserRepo userRepo,EmailUtil emailUtil){
        this.userRepo=userRepo;
        this.emailUtil=emailUtil;
    }


    @Override
    public boolean createUserAccount(UserAccountForm userAccountForm) {

        UserEntity userEntity=new UserEntity();
        BeanUtils.copyProperties(userAccountForm,userEntity);

        userEntity.setActiveSw(AppConstants.STR_Y);
        userEntity.setActiveStatus(AppConstants.STR_LOCKED);

       // set password
        userEntity.setPazzword(generatePazzword());

        userRepo.save(userEntity);

        //send email
        String subject=AppConstants.STR_USER_REG_MAIL_SUB;
        String body= emailUtil.readEmailBody(AppConstants.STR_REG_EMAIL_BODY_TXT,userEntity);
        return this.emailUtil.sendEmail(userEntity.getEmail(),subject,body);
    }

    private String generatePazzword(){
        return RandomStringUtils.random( 15, AppConstants.STR_CHARACTOR);
    }

    @Override
    public List<UserAccountForm> fecthUserAccounts() {
        List<UserEntity> list=this.userRepo.findAll();
        List<UserAccountForm>  uaf=new ArrayList<>();
        for(UserEntity ue: list){
            UserAccountForm userAccountForm=new UserAccountForm();
            BeanUtils.copyProperties(ue,userAccountForm);
            uaf.add(userAccountForm);
        }
        return uaf;
    }

    @Override
    public UserAccountForm getUserAccountById(Integer accId) {
        if(accId!=null){
            Optional<UserEntity> userEntity=this.userRepo.findById(accId);
            if(userEntity.isPresent()){
                UserAccountForm userAccountForm=new UserAccountForm();
                BeanUtils.copyProperties(userEntity.get(),userAccountForm);
                return userAccountForm;
            }
        }
        return null;
    }

    @Override
    public String changeUserAccountStatus(Integer userId, String status) {
      Integer  count=  this.userRepo.updateAccountStatus( userId,   status);
      if (count>0){
          return AppConstants.STR_STS_CGD;
      }
        return AppConstants.STR_STS_NOT_CGD;
    }

    @Override
    public String unlockUserAcc(UnlockAccForm unlockAccForm) {
        UserEntity user = this.userRepo.findByEmail(unlockAccForm.getEmail());
        return
                "";
    }
}
