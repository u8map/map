package com.u8.entity;

import lombok.Data;

import java.io.Serializable;

/**
 *  收货地址信息
 */
@Data
public class AddressInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    // 订单号
    private String orderNo;
    // 用户所在城市
    private String region;
    // 用户收货地址
    private String address;
    // 纬度
    private double lat;
    // 经度
    private double lng;
    // 姓名
    private String info;
    // 重叠的订单数
    private int orderNum = 1;


    public AddressInfo(){};

    public AddressInfo(String orderNo, String region, String address, double lat, double lng, String info) {
        this.orderNo = orderNo;
        this.region = region;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.info = info;
    }

    @Override
    public String toString() {
        return "AddressInfo{" +
                "orderNo='" + orderNo + '\'' +
                ", region='" + region + '\'' +
                ", address='" + address + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", info='" + info + '\'' +
                ", orderNum=" + orderNum +
                '}';
    }
}
