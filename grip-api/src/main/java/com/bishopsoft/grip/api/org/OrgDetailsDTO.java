package com.bishopsoft.grip.api.org;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class OrgDetailsDTO {
    private int id;
    private String name;
    private String email;
    private String url;
}
