package com.u8.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "u8_map_role")
public class MapRole {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
}
