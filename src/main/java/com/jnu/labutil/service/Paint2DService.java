/*******************************************************************
 /*
 /*   @Author:            LiJiaJun
 /*   @CreateDate:        2021/5/12
 /*   @Description:       The panting 2D service
 /*
 /********************************************************************
 */

package com.jnu.labutil.service;

import com.jnu.labutil.entity.Point2D;
import com.jnu.labutil.util.Painting2DUtil;

import java.util.ArrayList;

public class Paint2DService {

    /*   画直线   */
    public static ArrayList<Point2D> GetLine(ArrayList<Point2D> pointList) {
        return pointList;
    }

    /*   画三次贝塞尔   */
    public static ArrayList<Point2D> GetBezierCurve(ArrayList<Point2D> pointList) {
        return Painting2DUtil.GetBezierPointList(pointList);
    }

    /*   画三次样条曲线   */
    public static ArrayList<Point2D> GetSplineCurve(ArrayList<Point2D> pointList) {
        return Painting2DUtil.GetSplineCurvePointList(pointList);
    }

    /*   画圆   */
    public static ArrayList<Point2D> GetCircle(ArrayList<Point2D> pointList) {
        if (pointList.size() < 2) {
            return null;
        }
        return Painting2DUtil.GetCirclePointList(pointList);
    }

    /*   画圆弧   */
    public static ArrayList<Point2D> GetArc(ArrayList<Point2D> pointList) {
        if (pointList.size() < 3) {
            return null;
        }
        return Painting2DUtil.GetArcPointList(pointList.get(0), pointList.get(1), pointList.get(2));
    }
}
