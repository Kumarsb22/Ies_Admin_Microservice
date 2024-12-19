package com.ies.binding;

import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlanForm {

    private Integer planId;
    private String planName;
    private LocalDateTime planStartDate;
    private LocalDateTime planEndDate;
}
