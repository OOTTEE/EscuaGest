package com.escualos.domain.user;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
@Data
public class User {
    private String id;
    private String username;
    private String fullname;
    private String firstName;
    private String lastsNames;
    private boolean emailVerified;
    private String email;
    private String licence;
    private Sexo sexo;
}
