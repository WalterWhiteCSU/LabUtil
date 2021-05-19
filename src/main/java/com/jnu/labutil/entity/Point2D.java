/*******************************************************************
 /*
 /*   @Author:            LiJiaJun
 /*   @CreateDate:        2021/5/12
 /*   @Description:       The Entity of 2D-Point
 /*
 /********************************************************************
 */

package com.jnu.labutil.entity;

import lombok.Data;

@Data
public class Point2D {
    private double X;
    private double Y;

    public Point2D() {
    }

    public Point2D(double x, double y) {
        X = x;
        Y = y;
    }

    @Override
    public String toString() {
        return "Point2D{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }

    /*
     *
     *   点的向量加法
     * */
    public Point2D addVector(Vector vector) {
        Point2D model = new Point2D();
        model.setX(X + vector.getX());
        model.setY(Y + vector.getY());

        return model;
    }

    /*
    *
    *   得到两个点的距离
    *
    * */
    public static double GetTwoPointDistance(Point2D point1, Point2D point2) {
        return Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));
    }
}
