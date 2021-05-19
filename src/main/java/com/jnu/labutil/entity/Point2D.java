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
}
