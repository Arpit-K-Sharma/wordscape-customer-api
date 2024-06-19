package com.example.ERP_V2.DTO;

import com.example.ERP_V2.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO extends PersonDTO {
    private int userId;

    private String address;

    private long phoneNumber;

    private boolean status;


    private final RoleEnum role = RoleEnum.ROLE_USER;

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserDTO(int userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}