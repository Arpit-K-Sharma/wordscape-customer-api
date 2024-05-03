package com.example.ERP_V2.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDTO {
    protected String fullName;

    @Email(message = "Not valid Email")
    protected String email;

    protected String password;

}