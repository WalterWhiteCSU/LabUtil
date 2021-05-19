/*******************************************************************
 /*
 /*   @Author:            LiJiaJun
 /*   @CreateDate:        2021/5/12
 /*   @Description:       The panting 2D service
 /*
 /********************************************************************
 */

package com.jnu.labutil.service;

import com.jnu.labutil.entity.CurvePaintingPageInPut;
import com.jnu.labutil.entity.CurvePaintingPageOutPut;
import com.jnu.labutil.entity.Point2D;
import com.jnu.labutil.util.Painting2DUtil;

import java.util.ArrayList;

public class Paint2DService {

    /*   画直线   */
    public static CurvePaintingPageOutPut GetLine(ArrayList<Point2D> pointList) {
        CurvePaintingPageOutPut result = new CurvePaintingPageOutPut();

        result.setControlPointList(pointList);
        result.setPointList(pointList);

        return result;
    }

    /*   画三次贝塞尔   */
    public static CurvePaintingPageOutPut GetBezierCurve(ArrayList<Point2D> pointList) {
        return Painting2DUtil.GetBezierPointList(pointList);
    }

    /*   画三次样条曲线   */
    public static CurvePaintingPageOutPut GetSplineCurve(ArrayList<Point2D> pointList) {
        CurvePaintingPageOutPut result = new CurvePaintingPageOutPut();

        result.setControlPointList(pointList);
        result.setPointList(Painting2DUtil.GetSplineCurvePointList(pointList));

        return result;
    }

    /*   画圆   */
    public static CurvePaintingPageOutPut GetCircle(ArrayList<Point2D> pointList) {
        if (pointList.size() < 2) {
            return null;
        }

        CurvePaintingPageOutPut result = new CurvePaintingPageOutPut();

        result.setPointList(Painting2DUtil.GetCirclePointList(pointList));

        ArrayList<Point2D> controlPointList = new ArrayList<>();
        controlPointList.add(pointList.get(0));
        controlPointList.add(pointList.get(1));
        result.setControlPointList(controlPointList);

        return result;
    }

    /*   画圆弧   */
    public static CurvePaintingPageOutPut GetArc(ArrayList<Point2D> pointList) {
        if (pointList.size() < 3) {
            return null;
        }

        CurvePaintingPageOutPut result = new CurvePaintingPageOutPut();

        result.setPointList(Painting2DUtil.GetArcPointList(pointList.get(0), pointList.get(1), pointList.get(2)));

        ArrayList<Point2D> controlPointList = new ArrayList<>();
        controlPointList.add(pointList.get(0));
        controlPointList.add(pointList.get(1));
        controlPointList.add(pointList.get(2));
        result.setControlPointList(controlPointList);
        return result;
    }

    /*   画线   */
    public static CurvePaintingPageOutPut GetCurve(CurvePaintingPageInPut curvePaintingPageInPut) {
        switch (curvePaintingPageInPut.getPaintingType()) {
            case "Line":
                return GetLine(curvePaintingPageInPut.getPointList());
            case "Spline":
                return GetSplineCurve(curvePaintingPageInPut.getPointList());
            case "Bezier":
                return GetBezierCurve(curvePaintingPageInPut.getPointList());
            case "Circle":
                return GetCircle(curvePaintingPageInPut.getPointList());
            case "Arc":
                return GetArc(curvePaintingPageInPut.getPointList());
            default:
                return null;
        }
    }
}
