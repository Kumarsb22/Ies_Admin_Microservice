package com.ies.binding;

import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserAccountForm {
    private  Integer userId;
    private String fullName;
    private String email;
    private String phonNo;
    private LocalDateTime dob;
    private String ssn;
}
