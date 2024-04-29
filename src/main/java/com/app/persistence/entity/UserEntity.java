package com.app.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)//un usuario debe de ser unico
    private String username;
    private String password;

    @Column(name = "is_enable")
    private boolean isEnable;

    @Column(name = "account_No_Expired")
    private boolean accountNoExpired;

    @Column(name = "account_No_Locked")
    private boolean accountNoLocked;

    @Column(name = "credential_No_Expired")
    private boolean credentialNoExpired;

    //EAGER: me carga automaticanemte todos los roles que tiene
    //CASCADE TYPE ALL : si yo guardo un usuario en la tabla me guarda auto todos los roles que tenga asociado
    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    //set funciona igual que una lista pero aqui no permite repetidos en la lista si
    @JoinTable(name = "user_roles" , joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();



}
