/*******************************************************************
 /*
 /*   @Author:            LiJiaJun
 /*   @CreateDate:        2021/5/18
 /*   @Description:       The Vector Curve Entity
 /*
 /********************************************************************
 */

package com.jnu.labutil.entity;

import lombok.Data;

@Data
public class Vector {
    private double X;

    private double Y;

    private double Z;

    public Vector(double x, double y, double z) {
        X = x;
        Y = y;
        Z = z;
    }

    public Vector() {
    }

    /*
     *
     *   通过两个二维的点得到向量
     * */
    public Vector(Point2D startPoint, Point2D endPoint) {
        X = endPoint.getX() - startPoint.getX();
        Y = endPoint.getY() - startPoint.getY();
    }

    /*
     *
     *   对向量进行缩放
     *
     * */
    public Vector zoom(Double scale) {
        X *= scale;
        Y *= scale;
        Z *= scale;
        return this;
    }

    /*
     *
     *   向量的模
     *
     * */
    public double mold() {
        return Math.sqrt(X * X + Y * Y + Z * Z);
    }

    /*
     *
     *   向量的数量积
     *
     * */
    public static double Product(Vector vector1, Vector vector2) {
        return vector1.getX() * vector2.getX() + vector1.getY() * vector2.getY() + vector1.getZ() * vector2.getZ();
    }

    /*
     *
     *   得到两个向量的夹角
     * */
    public static double GetAngle(Vector firstVector, Vector secondVector) {
        double angle = 0.0;

        //求得两个向量的模
        double mold_1 = firstVector.mold();
        double mold_2 = secondVector.mold();

        //求得两个向量的数量积
        double product = Vector.Product(firstVector, secondVector);

        //得到角度
        angle = Math.acos(product / (mold_1 * mold_2));

        return angle;
    }
}
