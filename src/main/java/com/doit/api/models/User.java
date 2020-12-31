package com.doit.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(unique=true)
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastname;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String firstname;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
}
