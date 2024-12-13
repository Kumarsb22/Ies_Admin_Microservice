package com.ies.services;

import com.ies.binding.UnlockAccForm;
import com.ies.binding.UserAccountForm;

import java.util.List;

public interface AccountService {
    public boolean createUserAccount(UserAccountForm userAccountForm);

    public List<UserAccountForm> fecthUserAccounts();

    public UserAccountForm getUserAccountById(Integer accId);

    public String changeUserAccountStatus(Integer userId,String status);

    public  String unlockUserAcc(UnlockAccForm unlockAccForm);
}
