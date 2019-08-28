package com.u8.util;

import com.u8.entity.AddressInfo;

import java.awt.geom.Point2D;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class Util {
    // 百度地图的密匙
    public static final String AK = "871b773ac9c6dd602ea8b581e8060f1e";

    // 地点索引的链接
    public static final String MAP_SEARCH_URL = "http://api.map.baidu.com/place/v2/search";

    // 起送点纬度
    public static final double START_LAT = 27.25749;

    // 起送点经度
    public static final double START_LNG = 111.739614;

    // 起送点经度
    public static String START_INFO = "优八起送点";

    // 限定区域
    public static String REGION = "湖南省邵阳市邵东县";

    // 起始点
    public static final AddressInfo START = new AddressInfo(REGION,START_INFO,START_LAT,START_LNG);

    // 平均半径,单位：m
    private static final double EARTH_RADIUS = 6371393;

    /**
     * 通过AB点经纬度获取距离
     * @param pointA A点(经，纬)
     * @param pointB B点(经，纬)
     * @return 距离(单位：米)
     */
    public static double getDistance(AddressInfo pointA, AddressInfo pointB) {
        // 经纬度（角度）转弧度。弧度用作参数，以调用Math.cos和Math.sin
        double radiansAX = Math.toRadians(pointA.getLng()); // A经弧度
        double radiansAY = Math.toRadians(pointA.getLat()); // A纬弧度
        double radiansBX = Math.toRadians(pointB.getLng()); // B经弧度
        double radiansBY = Math.toRadians(pointB.getLat()); // B纬弧度

        // 公式中“cosβ1cosβ2cos（α1-α2）+sinβ1sinβ2”的部分，得到∠AOB的cos值
        double cos = Math.cos(radiansAY) * Math.cos(radiansBY) * Math.cos(radiansAX - radiansBX)
                + Math.sin(radiansAY) * Math.sin(radiansBY);
//        System.out.println("cos = " + cos); // 值域[-1,1]
        double acos = Math.acos(cos); // 反余弦值
//        System.out.println("acos = " + acos); // 值域[0,π]
//        System.out.println("∠AOB = " + Math.toDegrees(acos)); // 球心角 值域[0,180]
        return EARTH_RADIUS * acos; // 最终结果

    }


    /**
     * 10位int型的时间戳转换为String(yyyy-MM-dd HH:mm:ss)
     * @param time
     * @return
     */
    public static String timestampToString(Long time){
        //int转long时，先进行转型再进行计算，否则会是计算结束后在转型
        long temp = time*1000;
        Timestamp ts = new Timestamp(temp);
        String tsStr = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            tsStr = dateFormat.format(ts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tsStr;
    }
}
