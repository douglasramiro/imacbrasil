package br.com.drsource.imacbrasil.security;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credential implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Short id;

    @Column(unique = true)
    private String email;

    private String password;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;

}
