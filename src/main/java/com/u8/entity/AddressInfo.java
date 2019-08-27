package com.u8.entity;

import lombok.Data;

import java.io.Serializable;

/**
 *  收货地址信息
 */
@Data
public class AddressInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    // 用户所在城市
    private String region;
    // 用户收货地址
    private String address;
    // 纬度
    private double lat;
    // 经度
    private double lng;

    public AddressInfo(){};

    public AddressInfo(String region, String address, double lat, double lng) {
        this.region = region;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }
}
