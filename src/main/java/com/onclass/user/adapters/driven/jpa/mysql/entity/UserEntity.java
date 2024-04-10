package com.onclass.user.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String document;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    private String phone;
    private String email;
    private Long roleId;
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;
}
