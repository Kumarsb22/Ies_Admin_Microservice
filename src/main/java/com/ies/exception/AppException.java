package com.ies.exception;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppException {
    private String exceptCode;
    private String exceptDesc;
    private LocalDateTime exceptDate;




}
