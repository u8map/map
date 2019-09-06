package com.u8.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.u8.entity.AddressInfo;
import com.u8.entity.MapInfo;
import com.u8.entity.Order;
import com.u8.repository.MapInfoRepository;
import com.u8.repository.OrderRepository;
import com.u8.service.MapService;
import com.u8.util.ArrangeUtil;
import com.u8.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 地图服务类
 */
@Slf4j
@Service
public class MapServiceImpl implements MapService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private MapInfoRepository mapInfoRepository;

    // 缓存上一次数据库查询过的订单数，当有新的查询时，先将此缓存与查询结果做比较，如果结果一致则返回上一次的结果、
    private List<Order> listCache;

    private List<AddressInfo>[] listsCache = new List[2]; // 返回到前台界面的数组

    /**
     * 搜索需要发货的订单并返回地理位置和订单信息等
     *
     * @return
     */
    public List<AddressInfo>[] search() {
        MapInfo mapInfo = mapInfoRepository.getOne(1L);


        List<Order> list = orderRepository.findByIsSendAndIsPayAndApplyDeleteAndIsDeleteAndIsCancelAndIsRecycle
                (0, 1, 0, 0, 0, 0);
        // 如果和缓存中的结果一致 并且listsCache有数据
        if (listCache != null && listCache.containsAll(list) && listCache.size() == list.size() && listsCache != null) {
            return listsCache;
        }
        List<AddressInfo> addressInfos = new ArrayList();
        List<AddressInfo> errorAddressInfos = new ArrayList(); // 查找不到的地址信息
        for (Order order : list) {
            // 如果缓存中有此数据表示已经处理过这条数据了,可以直接获取缓存中的数据
            if (listCache != null && listCache.contains(order)) {
                boolean flag = false; // 因为返回到界面上结果有两个，所已此标识用来标记如果已查到就不用往下执行了。
                // 从缓存中找到
                for (AddressInfo addressInfo : listsCache[0]) {
                    if (addressInfo.getOrderNo().equals(order.getOrderNo())) {
                        addressInfos.add(addressInfo);
                        flag = true;
                        break;
                    }
                }
                if (!flag) { // 如果没有查到
                    for (AddressInfo addressInfo : listsCache[1]) {
                        if (addressInfo.getOrderNo().equals(order.getOrderNo())) {
                            errorAddressInfos.add(addressInfo);
                            break;
                        }
                    }
                }
            } else {
                // 根据用户地址获取经纬度
                Double[] latLng = LocationRetrieval(order.getAddress(), mapInfo.getRegion(), mapInfo.getAK(), mapInfo.getKeywords());
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("订单号：");
                stringBuffer.append(order.getOrderNo());
                stringBuffer.append("<br/>收货人姓名：");
                stringBuffer.append(order.getName());
                stringBuffer.append("<br/>收货地址：");
                stringBuffer.append(order.getAddress().replace(mapInfo.getRegion(), ""));
                stringBuffer.append("<br/>收货人电话：");
                stringBuffer.append(order.getMobile());
                stringBuffer.append("<br/>下单时间：");
                stringBuffer.append(Util.timestampToString(order.getPayTime()));
                stringBuffer.append("<br/>");
                AddressInfo addressInfo = new AddressInfo(order.getOrderNo(), mapInfo.getRegion(), order.getAddress(),
                        latLng[0], latLng[1], stringBuffer.toString());
                log.info(addressInfo.toString());
                // 查找不到的地址信息
                if (latLng[0] != 0 && latLng[1] != 0) {
                    addressInfos.add(addressInfo);
                } else {
                    errorAddressInfos.add(addressInfo);
                }
            }
        }

        List<AddressInfo>[] lists = new List[2];

        List<AddressInfo> addressInfoArray = new ArrayList();

        AddressInfo addressInfoStart = new AddressInfo("", mapInfo.getRegion(), mapInfo.getRegion(),
                mapInfo.getLat(), mapInfo.getLng(), mapInfo.getInfo());
        // 起点
        addressInfoArray.add(addressInfoStart);

        // 所有送货点，送货点将地址一样的订单放到一起
        addressInfoArray.addAll(mergeAll(addressInfos, 0));

        // 按距离排序
//        ArrangeUtil arrangeUtil = new ArrangeUtil(addressInfoArray);

//        lists[0] = arrangeUtil.getPointList();

        lists[0] = sortAll(addressInfoArray);

        lists[1] = errorAddressInfos;

        synchronized (MapServiceImpl.class) { // 写入公用缓存的时候加入同步块
            listCache = list;
            listsCache = lists; // 将查询出来的数据加入缓存
        }
        return lists;
    }

    /**
     * 将地址一样的订单放到一起
     *
     * @param addressInfos
     * @param n
     * @return
     */
    private List<AddressInfo> mergeAll(List<AddressInfo> addressInfos, int n) {
        if (n == addressInfos.size()) {
            return addressInfos;
        }

        AddressInfo addressInfo = addressInfos.get(n);
        // 全排列算法部分
        for (int i = n + 1; i < addressInfos.size(); i++) {
            // 如果坐标位置相差小于一定范围则把订单放在一起
            BigDecimal xLng = new BigDecimal(String.valueOf(addressInfo.getLng()));
            BigDecimal yLng = new BigDecimal(String.valueOf(addressInfos.get(i).getLng()));
            BigDecimal xLat = new BigDecimal(String.valueOf(addressInfo.getLat()));
            BigDecimal yLat = new BigDecimal(String.valueOf(addressInfos.get(i).getLat()));
            BigDecimal resultLng = xLng.subtract(yLng);
            BigDecimal resultLat = xLat.subtract(yLat);
            BigDecimal result = resultLng.abs().add(resultLat.abs());

            if (result.doubleValue() < 0.0005) {
                String info = addressInfos.remove(i).getInfo(); // 删除地址一样的值 并获取他的信息
                addressInfo.setInfo(addressInfo.getInfo() + "---------------------------------------<br/>" + info);
                addressInfo.setOrderNum(addressInfo.getOrderNum() + 1);
            }
        }

        return mergeAll(addressInfos, n + 1);
    }

    /**
     * 地点检索
     *
     * @param query    检索关键字 例：湖南省邵阳市邵东县县政府
     * @param region   所在区域 例：湖南省邵阳市邵东县
     * @param AK       百度地图密匙
     * @param keywords 地区关键字 例：邵东
     * @return 用户收货地址的信息
     */
    private Double[] LocationRetrieval(String query, String region, String AK, String keywords) {
        Double[] doubles = new Double[2];
        AddressInfo addressInfo = new AddressInfo();
        // 通过百度获取经纬度
        String output = "json"; // 输出格式
        String tag = "房地产,公司企业"; // 检索分类偏好
        String url = String.format("%s?query=%s&tag=%s&region=%s&output=%s&ak=%s&page_size=%d",
                Util.MAP_SEARCH_URL, query, tag, region, output, AK, 1);
        String json = restTemplate.getForObject(url, String.class);

        // 获取的json中提取经纬度
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(json);
            JsonNode resultsNode = rootNode.path("results"); // 获取results

            String area = resultsNode.get(0).path("area").asText();
            boolean flag = false;
            String[] key = keywords.split(",");
            for (String str : key) {
                if (area.contains(str)) {
                    flag = true; // 包含所在区域
                    break;
                }
            }

            if (!flag) { // 如果不包含地区关键字
                throw new NullPointerException();
            }

            JsonNode locationNode = resultsNode.get(0).path("location"); // results是数组,获取第一个地址的json并获得金纬度
            doubles[0] = locationNode.path("lat").asDouble();
            doubles[1] = locationNode.path("lng").asDouble();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException ex) { // 空指针表示搜索不到
            doubles[0] = 0d;
            doubles[1] = 0d;
        }

        return doubles;
    }


    /**
     * 贪心算法 获取路径 从新排列送货点的顺序
     *
     * @param points 地址经纬度
     * @return
     */
    private List<AddressInfo> sortAll(List<AddressInfo> points) {
        int pointNum = 0;
        for (int i = 1; i < points.size(); i++) {
            double minPath = Integer.MAX_VALUE;
            for (int j = i; j < points.size(); j++) {
                double sum = Util.getDistance(points.get(i - 1), points.get(j));
                // 选出距离较小的点
                if (minPath > sum) {
                    minPath = sum;
                    pointNum = j; // 记录下此点
                }
            }
            // 替换两点位置
            swap(points, i, pointNum);
        }

        return points;

    }

    /**
     * 交换数组的i,j两个值
     *
     * @param points
     * @param i
     * @param j
     */
    private void swap(List<AddressInfo> points, int i, int j) {
        if (i == j) {
            return;
        }
        AddressInfo temp = points.get(i);
        points.set(i, points.get(j));
        points.set(j, temp);
    }

}
