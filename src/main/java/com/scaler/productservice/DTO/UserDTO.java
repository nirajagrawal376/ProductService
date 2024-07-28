package com.scaler.productservice.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private String fullName;

    private List<RoleDTO> roleDTOS;

    private boolean isEmailVerified;

    private String email;


}
