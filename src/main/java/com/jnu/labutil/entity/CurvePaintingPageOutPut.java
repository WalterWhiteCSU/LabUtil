/*******************************************************************
 /*
 /*   @Author:            LiJiaJun
 /*   @CreateDate:        2021/5/19
 /*   @Description:       The Curve Painting Page OutPut
 /*
 /********************************************************************
 */

package com.jnu.labutil.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CurvePaintingPageOutPut {
    private ArrayList<Point2D> pointList;

    private ArrayList<Point2D> controlPointList;
}
