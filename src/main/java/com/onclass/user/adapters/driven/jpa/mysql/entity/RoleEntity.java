package com.onclass.user.adapters.driven.jpa.mysql.entity;


import jakarta.persistence.*;
import lombok.*;



@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
@Getter
@Setter
@Builder
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,  length = 50)
    private String name;
    @Column(length = 90)
    private String description;



}