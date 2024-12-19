package com.ies.services;

import com.ies.binding.DashboardCardForm;
import com.ies.binding.LoginForm;
import com.ies.binding.UserAccountForm;

public interface UserService {

    public String login(LoginForm loginForm);

    public  boolean recoverPazzword(String email);

    public DashboardCardForm fecthDashboardInfo();

    public UserAccountForm fetchUser(String email);
}
