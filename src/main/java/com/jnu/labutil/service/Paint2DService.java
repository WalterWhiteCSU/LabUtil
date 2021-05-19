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
<<<<<<< Updated upstream
    public static CurvePaintingPageOutPut GetLine(ArrayList<Point2D> pointList) {
=======
    private static CurvePaintingPageOutPut GetLine(ArrayList<Point2D> pointList) {
>>>>>>> Stashed changes
        CurvePaintingPageOutPut result = new CurvePaintingPageOutPut();

        result.setControlPointList(pointList);
        result.setPointList(pointList);

        return result;
    }

    /*   画三次贝塞尔   */
<<<<<<< Updated upstream
    public static CurvePaintingPageOutPut GetBezierCurve(ArrayList<Point2D> pointList) {
=======
    private static CurvePaintingPageOutPut GetBezierCurve(ArrayList<Point2D> pointList) {
        CurvePaintingPageOutPut result = new CurvePaintingPageOutPut();

>>>>>>> Stashed changes
        return Painting2DUtil.GetBezierPointList(pointList);
    }

    /*   画三次样条曲线   */
<<<<<<< Updated upstream
    public static CurvePaintingPageOutPut GetSplineCurve(ArrayList<Point2D> pointList) {
        CurvePaintingPageOutPut result = new CurvePaintingPageOutPut();

        result.setControlPointList(pointList);
        result.setPointList(Painting2DUtil.GetSplineCurvePointList(pointList));
=======
    private static CurvePaintingPageOutPut GetSplineCurve(ArrayList<Point2D> pointList) {
        CurvePaintingPageOutPut result = new CurvePaintingPageOutPut();

        result.setPointList(Painting2DUtil.GetSplineCurvePointList(pointList));
        result.setControlPointList(pointList);
>>>>>>> Stashed changes

        return result;
    }

    /*   画圆   */
<<<<<<< Updated upstream
    public static CurvePaintingPageOutPut GetCircle(ArrayList<Point2D> pointList) {
        if (pointList.size() < 2) {
            return null;
        }

        CurvePaintingPageOutPut result = new CurvePaintingPageOutPut();

        result.setPointList(Painting2DUtil.GetCirclePointList(pointList));

=======
    private static CurvePaintingPageOutPut GetCircle(ArrayList<Point2D> pointList) {
        CurvePaintingPageOutPut result = new CurvePaintingPageOutPut();
        if (pointList.size() < 2) {
            return null;
        }
        result.setPointList(Painting2DUtil.GetCirclePointList(pointList));
>>>>>>> Stashed changes
        ArrayList<Point2D> controlPointList = new ArrayList<>();
        controlPointList.add(pointList.get(0));
        controlPointList.add(pointList.get(1));
        result.setControlPointList(controlPointList);
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
        return result;
    }

    /*   画圆弧   */
<<<<<<< Updated upstream
    public static CurvePaintingPageOutPut GetArc(ArrayList<Point2D> pointList) {
        if (pointList.size() < 3) {
            return null;
        }

        CurvePaintingPageOutPut result = new CurvePaintingPageOutPut();

        result.setPointList(Painting2DUtil.GetArcPointList(pointList.get(0), pointList.get(1), pointList.get(2)));

=======
    private static CurvePaintingPageOutPut GetArc(ArrayList<Point2D> pointList) {
        CurvePaintingPageOutPut result = new CurvePaintingPageOutPut();
        if (pointList.size() < 3) {
            return null;
        }
        result.setPointList(Painting2DUtil.GetArcPointList(pointList.get(0), pointList.get(1), pointList.get(2)));
>>>>>>> Stashed changes
        ArrayList<Point2D> controlPointList = new ArrayList<>();
        controlPointList.add(pointList.get(0));
        controlPointList.add(pointList.get(1));
        controlPointList.add(pointList.get(2));
        result.setControlPointList(controlPointList);
<<<<<<< Updated upstream
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
=======

        return result;
    }

    public static CurvePaintingPageOutPut Painting(CurvePaintingPageInPut pageInPut){
        switch (pageInPut.getPaintingType()){
            case"Line":
                return GetLine(pageInPut.getPointList());
            case "Spline":
                return GetSplineCurve(pageInPut.getPointList());
            case "Bezier":
                return GetBezierCurve(pageInPut.getPointList());
            case "Circle":
                return GetCircle(pageInPut.getPointList());
            case "Arc":
                return GetArc(pageInPut.getPointList());
            default:
                return null;
        }

>>>>>>> Stashed changes
    }
}
