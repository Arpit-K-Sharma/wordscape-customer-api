package com.example.ERP_V2.DTO;

import com.example.ERP_V2.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AdminDTO extends PersonDTO{
    private String admin_id;
    private final RoleEnum role = RoleEnum.ROLE_ADMIN;
}
