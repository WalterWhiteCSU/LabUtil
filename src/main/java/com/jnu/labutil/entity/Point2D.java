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

    @Override
    public String toString() {
        return "Point2D{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }
}
