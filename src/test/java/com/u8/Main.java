package com.u8;

import com.u8.util.ArrangeUtil;
import com.u8.util.Util;

import java.awt.geom.Point2D;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * 计算快递员在各个点送快递最短路径问题:<br>
 * 输入参数例子： <br/>
 * 4<br/>
 * 2,2 <br/>
 * 2,8 <br/>
 * 4,4<br/>
 * 7,2 <br/>
 * 输出应为：30
 *
 * @author 蓝亭书序
 *
 */
public class Main {
//    // 起始点
//    static final Point2D START = new Point2D.Double(111.739614,27.25749);
//    // 记录最短路径（默认是“无限大”表示不可达）
//    static double minPath = Integer.MAX_VALUE;
//
//    static List<Point2D> pointList = new ArrayList<>();
//
//    public static void main(String[] args) {
//        // 创建输入解析器
////        InputParser parser = new InputParser(System.in);
////        parser.parse();// 获取输入，开始转换
////        System.out.println(rangeAll(parser.getPoints(), 0));
//
//        List<Point2D> points =  new ArrayList<>();
//        points.add(new Point2D.Double(111.750719,27.265851));
//        points.add(new Point2D.Double(111.747929,27.264381));
//        points.add(new Point2D.Double(111.791391,27.265168));
//        points.add(new Point2D.Double(111.751788,27.242717));
//
//
//        System.out.println(rangeAll(points, 0));
//        for (Point2D point : pointList) {
//            System.out.println("最小路径长度"+point.getX()+" "+point.getY());
//        }
//
//    }
//
//    /**
//     * 用全排列解决此问题
//     *
//     * @param points 坐标点
//     * @param n
//     */
//    public static double rangeAll(List<Point2D> points, int n) {
//        if (n == points.size()) {
//            List<Point2D> tmpPointList = new ArrayList<>();
//            // 计算这次排列的路径长度
//            double sum = Util.getDistance(points.get(0),Util.START);
//            for (int i = 1; i < points.size(); i++) {
//                sum += Util.getDistance(points.get(i-1),points.get(i));
//                tmpPointList.add(points.get(i - 1));
//            }
//            // 【千万不要忘了加上回到原点的路径】
//            sum += Util.getDistance(points.get(points.size() - 1),Util.START);
//            tmpPointList.add(points.get(points.size() - 1));
//            // 记录最短长度【其实这里也可以记录下路径详情(也就是这次全排列的顺序)】
//            if(minPath > sum){
//                minPath = sum;
//                pointList = tmpPointList;
//            }
////            System.out.println("这一次路径长度"+sum);
////            for (Point point : tmpPointList) {
////                System.out.println("这一次的点排列"+point.px+" "+point.py);
////            }
//            return minPath;
//        }
//        // 全排列算法部分
//        for (int i = n; i < points.size(); i++) {
//            swap(points, n, i);
//            rangeAll(points, n + 1);
//            swap(points, n, i);
//        }
//        return minPath;
//    }
//
//    /**
//     * 交换数组的i,j两个值
//     *
//     * @param points
//     * @param i
//     * @param j
//     */
//    public static void swap(List<Point2D> points, int i, int j) {
//        if (i == j) {
//            return;
//        }
//        Point2D temp = points.get(i);
//        points.set(i,points.get(j));
//        points.set(j,temp);
//    }
//
//}
//
///**
// * 输入解析类
// *
// * @author 蓝亭书序
// *
// */
//class InputParser {
//
//    private Scanner scanner;// 输入扫描器
//    private Point2D[] points;// 点集
//    private int num;// 总共有几个点
//
//    public InputParser(InputStream in) {
//        scanner = new Scanner(in);
//    }
//    public Point2D[] getPoints() {
//        return points;
//    }
//    public int getNum() {
//        return num;
//    }
//    /**
//     * 解析输入，格式例如：<br/>
//     * 3<br/>
//     * 2,2<br/>
//     * 2,8<br/>
//     * 6,6<br/>
//     */
//    public void parse() {
//        // 获取有几个点
//        num = Integer.parseInt(scanner.nextLine().trim());
//        points = new Point2D[num];// 构建点数组
//        // 分别获取各个点的坐标
//        for (int i = 0; i < num; i++) {
//            String[] locations = scanner.nextLine().trim().split(",");
//            points[i] = new Point2D.Double(Double.parseDouble(locations[0]), Double.parseDouble(locations[1]));
//        }
//    }
//}
//
///**
// * 送货点类
// *
// * @author 蓝亭书序
// *
// */
//class Point {
//    int px;
//    int py;
//
//    public Point(int px, int py) {
//        this.px = px;
//        this.py = py;
//    }

    public static void main(String[] args) {
        BigDecimal xLng = new BigDecimal(String.valueOf(111.496206));
        BigDecimal yLng = new BigDecimal(String.valueOf(111.494454));
        BigDecimal xLat = new BigDecimal(String.valueOf(27.232767));
        BigDecimal yLat = new BigDecimal(String.valueOf(27.232767));
        BigDecimal resultLng = xLng.subtract(yLng);
        BigDecimal resultLat = xLat.subtract(yLat);
        BigDecimal result = resultLng.abs().add(resultLat.abs());


        System.out.println(result);


        if (result.doubleValue() < 0.0005) {
            System.out.println("在范围内");
        }else{
            System.out.println("在范围外");
        }
    }


}
