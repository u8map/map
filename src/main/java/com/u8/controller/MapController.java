package com.u8.controller;

import com.u8.entity.AddressInfo;
import com.u8.entity.MapUser;
import com.u8.entity.Msg;
import com.u8.service.MapService;
import com.u8.util.ArrangeUtil;
import com.u8.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String map(Model map) {
        List<AddressInfo>[] lists = mapService.search();
        map.addAttribute("addressInfoArray", lists[0]);
        map.addAttribute("errorAddressInfoArray", lists[1]);

        return "map";
    }

//    @RequestMapping("/main")
//    public String main(ModelMap map) {
//        return "main";
//    }

    @RequestMapping("/")
    public String index(Model model) {
//        Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
//        model.addAttribute("msg", msg);
//        return "index";


        MapUser users = (MapUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        log.info(users.toString());

        if (users.getMapRoles().get(0).getName().equals("ROLE_ADMIN")){ // 管理员
            Msg msg = new Msg("测试标题", "测试内容", "额外信息，只对管理员显示");
            model.addAttribute("msg", msg);
            return "index";
        }else if (users.getMapRoles().get(0).getName().equals("ROLE_USER")){ // 用户账号才能进入地图
            return "main";
        }

        return "index";
    }
}
