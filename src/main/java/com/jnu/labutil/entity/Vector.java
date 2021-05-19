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
    public Vector zoom(Double scale){
        X *= scale;
        Y *= scale;
        Z *= scale;
        return this;
    }
}
