package com.ies.binding;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UnlockAccForm {
    private String email;
    private String tempPazzword;
    private String newPazzword;
    private String confirmPazzword;

}
