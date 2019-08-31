package com.u8.controller;

import com.u8.entity.AddressInfo;
import com.u8.service.MapService;
import com.u8.util.ArrangeUtil;
import com.u8.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class MapController {
    @Autowired
    MapService mapService;

    @RequestMapping("/map")
    public String hello(Model map) {


//        AddressInfo addressInfo = mapService.LocationRetrieval("湖南省邵阳市邵东县","人民政府",1);
        // 查询点信息

//        addressInfoArray.add(mapService.LocationRetrieval("人民政府"));
//        addressInfoArray.add(mapService.LocationRetrieval("龙城宾馆"));
//        addressInfoArray.add(mapService.LocationRetrieval("邦盛凤凰城"));
//        addressInfoArray.add(mapService.LocationRetrieval("人民医院"));
//        addressInfoArray.add(mapService.LocationRetrieval("百富广场"));
//        addressInfoArray.add(mapService.LocationRetrieval("一中"));
//        addressInfoArray.add(mapService.LocationRetrieval("格林春天"));


//        map.addAttribute("minPath", String.format("总距离%.2f米",arrangeUtil.getMinPath()));

        List<AddressInfo>[] lists = mapService.search();
        map.addAttribute("addressInfoArray", lists[0]);
        map.addAttribute("errorAddressInfoArray", lists[1]);


        return "map";
    }

    @RequestMapping("/hello")
    public String hello(ModelMap map) {
        map.addAttribute("message", "你好");
        return "hello";
    }
}
