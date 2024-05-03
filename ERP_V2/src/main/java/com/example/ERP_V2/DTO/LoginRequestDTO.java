package com.example.ERP_V2.DTO;

import com.example.ERP_V2.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginRequestDTO {

    private String email;
    private String password;
    private RoleEnum role;
}
