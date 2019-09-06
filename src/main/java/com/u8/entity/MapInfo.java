package com.u8.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 起送点信息实体类
 */
@Data
@Entity
@Table(name = "u8_map_info")
public class MapInfo {
    @Id
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
