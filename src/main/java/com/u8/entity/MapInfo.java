package com.u8.entity;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * 起送点信息实体类
 */
@Proxy(lazy=false)
@Data
@Entity
@Table(name = "u8_map_info")
public class MapInfo {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String AK;
    @Column
    private Double lat;
    @Column
    private Double lng;
    @Column
    private String info;
    @Column
    private String region;
    @Column
    private String keywords;

}
