/*******************************************************************
 /*
 /*   @Author:            LiJiaJun
 /*   @CreateDate:        2021/5/19
 /*   @Description:       The Curve Painting Page InPut
 /*
 /********************************************************************
 */

package com.jnu.labutil.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CurvePaintingPageInPut {
    private String paintingType;
    private ArrayList<Point2D> pointList;
}
