package com.ies.binding;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DashboardCardForm {
    private Long noofPlans;
    private Long citizenApproved;
    private Long citizenDenied;
    private Double benfitsGiven;
    private UserAccountForm userAccountForm;

}
