package com.ies.services;

import com.ies.binding.UnlockAccForm;
import com.ies.binding.UserAccountForm;
import com.ies.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements  AccountService{

    private UserRepo userRepo;

    
    public AccountServiceImpl(UserRepo userRepo){
        this.userRepo=userRepo;
    }


    @Override
    public boolean createUserAccount(UserAccountForm userAccountForm) {
        return false;
    }

    @Override
    public List<UserAccountForm> fecthUserAccounts() {
        return List.of();
    }

    @Override
    public UserAccountForm getUserAccountById(Integer accId) {
        return null;
    }

    @Override
    public String changeUserAccountStatus(Integer userId, String status) {
        return "";
    }

    @Override
    public String unlockUserAcc(UnlockAccForm unlockAccForm) {
        return "";
    }
}
