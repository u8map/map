package com.u8.util;

import com.u8.entity.AddressInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 位置排列的工具类
 */
public class ArrangeUtil {
    // 记录最短路径（默认是“无限大”表示不可达）
    private double minPath = Integer.MAX_VALUE;

    public double getMinPath() {
        return minPath;
    }

    // 记录最短路程的点List
    private List<AddressInfo> pointList = new ArrayList<>();

    public List<AddressInfo> getPointList() {
        return pointList;
    }
    public ArrangeUtil(){}

    public ArrangeUtil(List<AddressInfo> points){
        rangeAll(points,0);
    }

  /**
     * 用全排列算法 点多的时候负载太大
     *
     * @param points 坐标点
     * @param n
     */
    public double rangeAll(List<AddressInfo> points, int n) {
        if (n == points.size()) {
            List<AddressInfo> tmpPointList = new ArrayList<>();
            // 计算这次排列的路径长度
//            double sum = Util.getDistance(points.get(0),Util.START); //TODO 加过起点了 此处需要修改
            double sum = 0;
            for (int i = 1; i < points.size(); i++) {
                sum += Util.getDistance(points.get(i-1),points.get(i));
                tmpPointList.add(points.get(i - 1));
            }
            // 【千万不要忘了加上回到原点的路径】
            sum += Util.getDistance(points.get(points.size() - 1),points.get(0));
            tmpPointList.add(points.get(points.size() - 1));
            // 记录最短长度【其实这里也可以记录下路径详情(也就是这次全排列的顺序)】
            if(minPath > sum){
                minPath = sum;
                pointList = tmpPointList;
            }
//            System.out.println("这一次路径长度"+sum);
//            for (Point point : tmpPointList) {
//                System.out.println("这一次的点排列"+point.px+" "+point.py);
//            }
            return minPath;
        }
        // 全排列算法部分
        for (int i = n; i < points.size(); i++) {
            swap(points, n, i);
            rangeAll(points, n + 1);
            swap(points, n, i);
        }
        return minPath;
    }

    /**
     * 交换数组的i,j两个值
     *
     * @param points
     * @param i
     * @param j
     */
    public void swap(List<AddressInfo> points, int i, int j) {
        if (i == j) {
            return;
        }
        AddressInfo temp = points.get(i);
        points.set(i,points.get(j));
        points.set(j,temp);
    }


}
