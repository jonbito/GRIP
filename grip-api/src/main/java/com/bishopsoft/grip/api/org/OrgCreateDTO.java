package com.bishopsoft.grip.api.org;

import lombok.Data;

@Data
public class OrgCreateDTO {
    private int id;
    private String name;
    private String email;
    private String url;
}
