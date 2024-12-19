package com.ies.services;

import com.ies.binding.DashboardCardForm;
import com.ies.binding.LoginForm;
import com.ies.binding.UserAccountForm;
import com.ies.constants.AppConstants;
import com.ies.entities.EligCritiria;
import com.ies.entities.UserEntity;
import com.ies.repositories.EligibCrireriaRepo;
import com.ies.repositories.PlansRepo;
import com.ies.repositories.UserRepo;
import com.ies.utils.EmailUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    private EmailUtil emailUtil;

    private PlansRepo plansRepo;

    private EligibCrireriaRepo crireriaRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo,EmailUtil emailUtil,PlansRepo plansRepo, EligibCrireriaRepo crireriaRepo){
    this.userRepo=userRepo;
    this.emailUtil=emailUtil;
    this.plansRepo=plansRepo;
    this.crireriaRepo=crireriaRepo;
    }

    @Override
    public String login(LoginForm loginForm) {
        UserEntity userEntity = this.userRepo.findByEmailAndPazzword(loginForm.getEmail(), loginForm.getPassword());
        if(userEntity==null){
            return AppConstants.STR_INVALID_CDLS;
        }
        if(AppConstants.STR_Y.equals(userEntity.getActiveSw()) && AppConstants.STR_UNLOCK.equals(userEntity.getActiveStatus())){
            return AppConstants.STR_SUCCESS;

        }
        return AppConstants.STR_ACC_LKD_ULKD;
    }

    @Override
    public  boolean recoverPazzword(String email) {
        UserEntity user = this.userRepo.findByEmail(email);
        if(user!=null){
           String subject=AppConstants.STR_RCR_PWD_BODY;
           String body=emailUtil.readEmailBody(AppConstants.STR_FORGOT_PWD_MAIL_BODY,user);
           return  this.emailUtil.sendEmail(user.getEmail(),subject,body);
        }
        return false;
    }

    @Override
    public DashboardCardForm fecthDashboardInfo() {
        DashboardCardForm cardForm=new DashboardCardForm();
        long count = this.plansRepo.count();
        cardForm.setNoofPlans(count);
        List<EligCritiria> all = this.crireriaRepo.findAll();
        long ap = all.stream().filter(e -> e.getPlanStatus().equals(AppConstants.STR_PLAN_STATUS_AP)).count();
        cardForm.setCitizenApproved(ap);
        long dn = all.stream().filter(e -> e.getPlanStatus().equals(AppConstants.STR_PLAN_STATUS_DN)).count();
        cardForm.setCitizenDenied(dn);
        double sum = all.stream().mapToDouble(e -> e.getBenfiAmt()).sum();
        cardForm.setBenfitsGiven(sum);
        return cardForm;
    }

    @Override
    public UserAccountForm fetchUser(String email) {
        UserEntity user = this.userRepo.findByEmail(email);
        UserAccountForm userAccountForm = new UserAccountForm();
        BeanUtils.copyProperties(user,userAccountForm);
        return userAccountForm;
    }



}
