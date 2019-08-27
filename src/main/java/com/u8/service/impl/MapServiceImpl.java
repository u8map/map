package com.u8.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.u8.entity.AddressInfo;
import com.u8.service.MapService;
import com.u8.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * 地图服务类
 */
@Slf4j
@Service
public class MapServiceImpl implements MapService {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 地点检索
     *
     * @param region 所在城市区域
     * @param query  检索关键字
     * @return 用户收货地址的信息
     */
    public AddressInfo LocationRetrieval(String region, String query) {
        AddressInfo addressInfo = new AddressInfo();
        // 通过百度获取经纬度
        String output = "json"; // 输出格式
        String tag = "房地产,公司企业"; // 检索分类偏好
        String url = String.format("%s?query=%s&tag=%s&region=%s&output=%s&ak=%s&page_size=%d",
                Util.MAP_SEARCH_URL, query, tag, region, output, Util.AK, 1);
        String json = restTemplate.getForObject(url, String.class);

        // 获取的json中提取经纬度
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(json);
            JsonNode resultsNode = rootNode.path("results"); // 获取results
            JsonNode locationNode = resultsNode.get(0).path("location"); // results是数组,获取第一个地址的json并获得金纬度
            addressInfo.setLng(locationNode.path("lng").asDouble());
            addressInfo.setLat(locationNode.path("lat").asDouble());
            addressInfo.setAddress(query);
            addressInfo.setRegion(region);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return addressInfo;
    }




}
