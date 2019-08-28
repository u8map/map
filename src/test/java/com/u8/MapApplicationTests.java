package com.u8;

import com.u8.entity.AddressInfo;
import com.u8.entity.Order;
import com.u8.service.MapService;
import com.u8.service.OrderService;
import com.u8.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapApplicationTests {
    @Autowired
    OrderService orderService;

    @Autowired
    MapService mapService;

//	{
//		"status":0,
//			"message":"ok",
//			"total":2,
//			"results":[
//		{
//			"name":"佳惠超市(邵阳店)",
//				"location":{
//			"lat":27.246062,
//					"lng":111.486649
//		},
//			"address":"湖南省邵阳市双清区五一南路佳惠百货(国湘大楼东北50米)",
//				"province":"湖南省",
//				"city":"邵阳市",
//				"area":"双清区",
//				"street_id":"3da907f3c86e2553b320a8ca",
//				"detail":1,
//				"uid":"3da907f3c86e2553b320a8ca"
//		}
//    ]
//	}

    @Test
    public void contextLoads() {
//		List<Order> list = orderService.findByIsSend(0);
//
//		for (Order order : list) {
//			log.info(order.getAddress());
//		}


//		AddressInfo addressInfo = mapService.LocationRetrieval("湖南省邵阳市双清区","人民广场佳惠超市",1);
//		log.info(addressInfo.toString());
        List<Order> list = orderService.findOrderList();

        for (Order order : list) {
            log.info(order.getOrder_no() + " " + order.getAddress() + " " + order.getName() + " " + Util.timestampToString(order.getPay_time()) + " " +
					order.getMobile());
        }
    }


}
