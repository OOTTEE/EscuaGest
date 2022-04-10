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

}
