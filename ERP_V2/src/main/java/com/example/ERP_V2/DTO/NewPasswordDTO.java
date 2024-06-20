package com.example.ERP_V2.DTO;


import com.example.ERP_V2.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewPasswordDTO {
    private int otp;

    private String newPassword;

    private String email;

    private RoleEnum role;
}
