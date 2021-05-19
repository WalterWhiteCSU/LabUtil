/*******************************************************************
 /*
 /*   @Author:            LiJiaJun
 /*   @CreateDate:        2021/5/18
 /*   @Description:       The Bezier Curve Entity
 /*
 /********************************************************************
 */

package com.jnu.labutil.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class BezierCurve {

    private ArrayList<Point2D> pointList;

    private ArrayList<Point2D> controlPointList;
}
