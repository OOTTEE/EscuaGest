package com.escualos.domain.swimmer;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Builder
@Data
@Document
public class Swimmer {

    public String id;
    public String username;
    public String licence;
    public LocalDate birthday;
    //TODO

}
