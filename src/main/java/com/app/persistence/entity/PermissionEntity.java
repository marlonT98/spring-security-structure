package com.app.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permission")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //tienen que ser unicos , no nulos , hay un persmiso que se llamara read_product <- ya no se puede actualizar
    @Column(unique = true , nullable = false , updatable = false)
    private String name;





}
