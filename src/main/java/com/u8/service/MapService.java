package com.u8.service;

import com.u8.entity.AddressInfo;

import java.util.List;

public interface MapService {
    /**
     * 地点检索
     *
     * @param region 所在城市区域
     * @param query  检索关键字
     * @return 用户收货地址的信息
     */
    AddressInfo LocationRetrieval(String region, String query);
}
