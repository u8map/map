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
        List<AddressInfo> addressInfoArray = new ArrayList();
        // 起点
        addressInfoArray.add(Util.START);

//        AddressInfo addressInfo = mapService.LocationRetrieval("湖南省邵阳市邵东县","人民政府",1);
        // 查询点信息

        addressInfoArray.add(mapService.LocationRetrieval(Util.REGION, "人民政府"));
        addressInfoArray.add(mapService.LocationRetrieval(Util.REGION, "龙城宾馆"));
        addressInfoArray.add(mapService.LocationRetrieval(Util.REGION, "邦盛凤凰城"));
        addressInfoArray.add(mapService.LocationRetrieval(Util.REGION, "人民医院"));
        addressInfoArray.add(mapService.LocationRetrieval(Util.REGION, "百富广场"));
        addressInfoArray.add(mapService.LocationRetrieval(Util.REGION, "一中"));
        addressInfoArray.add(mapService.LocationRetrieval(Util.REGION, "格林春天"));
        // 按距离排序
        ArrangeUtil arrangeUtil = new ArrangeUtil(addressInfoArray);

        map.addAttribute("minPath", String.format("总距离%.2f米",arrangeUtil.getMinPath()));
        map.addAttribute("addressInfoArray", arrangeUtil.getPointList());
        return "map";
    }

    @RequestMapping("/hello")
    public String hello(ModelMap map) {
        map.addAttribute("message", "你好");
        return "hello";
    }
}
