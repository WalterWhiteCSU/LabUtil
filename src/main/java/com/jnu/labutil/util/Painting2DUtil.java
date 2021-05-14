/*******************************************************************
 /*
 /*   @Author:            LiJiaJun
 /*   @CreateDate:        2021/5/12
 /*   @Description:       The panting 2D util
 /*
 /********************************************************************
 */

package com.jnu.labutil.util;

import com.jnu.labutil.entity.Point2D;

import java.util.ArrayList;

public class Painting2DUtil {
    static double BEZIERDELTA = 0.01;

    /*   多段线绘制   */
    public static ArrayList<Point2D> GetLinePointList(Point2D startPoint, Point2D endPoint) {
        ArrayList<Point2D> result = new ArrayList<>();
        double x = startPoint.getX();
        double k = (endPoint.getY() - startPoint.getY()) / (endPoint.getX() - startPoint.getX());
        double b = startPoint.getY() - k * startPoint.getX();
        while (x < endPoint.getX()) {
            x += 1;
            Point2D model = new Point2D();
            model.setX(x);
            model.setY(k * x + b);
            result.add(model);
        }
        return result;
    }

    /*   通过三个点画贝塞尔曲线   */
    private static ArrayList<Point2D> GetBezierCurve(Point2D firstPointX, Point2D secondPointX, Point2D thirdPointX, Point2D firstPointY, Point2D secondPointY, Point2D thirdPointY) {
        ArrayList<Point2D> result = new ArrayList<>();

        double t = 0;
        while (t < 1) {
            Point2D model = new Point2D();
            double x = (1 - t) * (1 - t) * firstPointX.getY() + 2 * t * (1 - t) * secondPointX.getY() + t * t * thirdPointX.getY();
            double y = (1 - t) * (1 - t) * firstPointY.getY() + 2 * t * (1 - t) * secondPointY.getY() + t * t * thirdPointY.getY();
            model.setX(x);
            model.setY(y);
            t += BEZIERDELTA;
            result.add(model);
        }

        return result;
    }

    /*   三次贝塞尔曲线绘制   */
    public static ArrayList<Point2D> GetBezierCurvePointList(ArrayList<Point2D> pointList) {
        if (pointList.size() == 3) {
            double a = 0;
        }
        ArrayList<Point2D> result = new ArrayList<>();
        //如果点数少于三个，不画
        if (pointList.size() < 2) {
            return null;
        }

        //参数化
        ArrayList<Double> x_value = new ArrayList<>();
        ArrayList<Double> y_value = new ArrayList<>();
        for (Point2D point : pointList) {
            x_value.add(point.getX());
            y_value.add(point.getY());
        }
        ArrayList<Point2D> x_Point = ParameterizationUtil.UniformParametric(x_value);
        ArrayList<Point2D> y_Point = ParameterizationUtil.UniformParametric(y_value);

        //画贝塞尔曲线
        for (int i = 2; i < pointList.size(); i += 2) {
            ArrayList<Point2D> currentCurveList = GetBezierCurve(x_Point.get(i - 2), x_Point.get(i - 1), x_Point.get(i), y_Point.get(i - 2), y_Point.get(i - 1), y_Point.get(i));
            result.addAll(currentCurveList);
        }

        return result;
    }

    /*   计算得到三弯矩方程   */
    private static ArrayList<Double> GetMomentParameter(ArrayList<Point2D> pointList){
        ArrayList<Double> momentList = new ArrayList<Double>();



        return momentList;
    }


    /*   画三次样条曲线   */
    public static ArrayList<Point2D> GetSplineCurvePointList(ArrayList<Point2D> pointList){
        ArrayList<Point2D> result = new ArrayList<>();

        //解三弯矩方程
        ArrayList<Double> momentList = GetMomentParameter(pointList);

        return result;
    }
}
