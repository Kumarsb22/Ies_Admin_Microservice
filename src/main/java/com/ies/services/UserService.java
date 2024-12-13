package com.ies.services;

import com.ies.binding.DashboardCardForm;
import com.ies.binding.LoginForm;

public interface UserService {

    public String login(LoginForm loginForm);

    public  boolean recoverPazzword(String email);

    public DashboardCardForm fecthDashboardInfo();
}
