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
import com.jnu.labutil.entity.Point2DWithParameter;
import org.ujmp.core.Matrix;
import org.ujmp.core.doublematrix.DoubleMatrix;
import org.ujmp.core.importer.source.MatrixClipboardImportSource;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Painting2DUtil {
    static double BEZIER_DELTA = 0.01;
    static double SPLINE_DELTA = 0.01;

    /*
     *   多段线绘制
     *   @Remark:abandon
     */
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

    /*
     *   三次贝塞尔曲线绘制
     *   @Remark:abandon
     */
    public static ArrayList<Point2D> GetNURBSPointList(ArrayList<Point2D> pointList) {
        ArrayList<Point2D> result = new ArrayList<>();

        /*   均匀参数化   */
        ArrayList<Point2DWithParameter> point2DWithParameters = ParameterizationUtil.UniformParametric(pointList);
        //画贝塞尔曲线
        for (int i = 2; i < pointList.size(); i += 2) {

        }

        return result;
    }

    /*
     *   得到h的数组
     *
     *   @param:
     *       pointList: 参数化之后的点列表
     *
     *   @return:
     *       h数列
     */
    private static ArrayList<Double> GetHList(ArrayList<Point2DWithParameter> pointList) {
        ArrayList<Double> result = new ArrayList<>();


        for (int i = 0; i < pointList.size() - 1; i++) {
            double h = 0.0;
            h = pointList.get(i + 1).getT() - pointList.get(i).getT();
            result.add(h);
        }

        return result;
    }

    /*
     *
     *   计算得到三弯矩方程
     *   http://staff.ustc.edu.cn/~lgliu/Courses/GAMES102_2020/documents/GAMES102-suppl-2-CubicSpline.pdf
     *
     *   @param:
     *       pointList: 带参数化后的点列
     *       hList： h数列
     *
     *   @return:
     *       解得的弯矩
     *
     * */
    private static ArrayList<ArrayList<Double>> GetMomentParameter(ArrayList<Point2DWithParameter> pointList, ArrayList<Double> hList) {
        ArrayList<ArrayList<Double>> momentList = new ArrayList<>();

        ArrayList<Double> x_momentList = new ArrayList<>();
        ArrayList<Double> y_momentList = new ArrayList<>();

        //点的个数
        int pointCount = pointList.size();

        //A矩阵赋值
        Matrix A = DoubleMatrix.Factory.zeros(pointCount, pointCount);
        //第一行
        A.setAsDouble(1.0, 0, 0);
        //第二行到倒数第二行
        for (int i = 1; i <= pointCount - 2; i++) {
            A.setAsDouble(hList.get(i - 1), i, i - 1);
            A.setAsDouble(2 * (hList.get(i) + hList.get(i - 1)), i, i);
            A.setAsDouble(hList.get(i), i, i + 1);
        }
        //最后一行
        A.setAsDouble(1.0, pointCount - 1, pointCount - 1);


        //v矩阵赋值
        Matrix x_V = DoubleMatrix.Factory.zeros(pointCount, 1);
        Matrix y_V = DoubleMatrix.Factory.zeros(pointCount, 1);
        //第一行
        x_V.setAsDouble(0.0, 0, 0);
        y_V.setAsDouble(0.0, 0, 0);
        //第二行到倒数第二行
        for (int i = 1; i <= pointCount - 2; i++) {
            double X = (pointList.get(i + 1).getX() - pointList.get(i).getX()) / hList.get(i) - (pointList.get(i).getX() - pointList.get(i - 1).getX()) / hList.get(i - 1);
            double Y = (pointList.get(i + 1).getY() - pointList.get(i).getY()) / hList.get(i) - (pointList.get(i).getY() - pointList.get(i - 1).getY()) / hList.get(i - 1);
            x_V.setAsDouble(6 * X, i, 0);
            y_V.setAsDouble(6 * Y, i, 0);
        }
        //最后一行
        x_V.setAsDouble(0.0, pointCount - 1, 0);
        y_V.setAsDouble(0.0, pointCount - 1, 0);

        //得到弯矩
        Matrix x_M = A.inv().mtimes(x_V);
        Matrix y_M = A.inv().mtimes(y_V);

        for (int i = 0; i < pointList.size(); i++) {
            x_momentList.add(x_M.getAsDouble(i, 0));
            y_momentList.add(y_M.getAsDouble(i, 0));
        }

        momentList.add(x_momentList);
        momentList.add(y_momentList);

        return momentList;
    }

    /*
     *   计算一段的三次样条曲线
     *
     *   @param:
     *       h: h值
     *       x_m_1: x的起始弯矩
     *       x_m_2: x的终止弯矩
     *       y_m_1: y的起始弯矩
     *       y_m_2: y的终止弯矩
     *       point_1: 起点
     *       point_2: 终点
     *
     *   @return:
     *       结果
     *
     */
    private static ArrayList<Point2D> GetSingleSplineCurveResultPointList(double h, double x_m_1, double x_m_2, double y_m_1, double y_m_2, Point2DWithParameter point_1, Point2DWithParameter point_2) {
        ArrayList<Point2D> result = new ArrayList<>();

        double t = point_1.getT();

        double x = 0.0;
        double y = 0.0;
        while (t < point_2.getT()) {
            Point2D model = new Point2D();

            x = x_m_1 / (6 * h) * Math.pow(point_2.getT() - t, 3) +
                    x_m_2 / (6 * h) * Math.pow(t - point_1.getT(), 3) +
                    (point_2.getX() / h - x_m_2 * h / 6) * (t - point_1.getT()) +
                    (point_1.getX() / h - x_m_1 * h / 6) * (point_2.getT() - t);
            y = y_m_1 / (6 * h) * Math.pow(point_2.getT() - t, 3) +
                    y_m_2 / (6 * h) * Math.pow(t - point_1.getT(), 3) +
                    (point_2.getY() / h - y_m_2 * h / 6) * (t - point_1.getT()) +
                    (point_1.getY() / h - y_m_1 * h / 6) * (point_2.getT() - t);
            model.setX(x);
            model.setY(y);
            result.add(model);
            t += SPLINE_DELTA;
        }
        t = point_2.getT();
        x = x_m_2 / (6 * h) * Math.pow(t - point_1.getT(), 3) + (point_2.getX() / h - x_m_2 * h / 6) * (t - point_1.getT());
        y = y_m_2 / (6 * h) * Math.pow(t - point_1.getT(), 3) + (point_2.getY() / h - y_m_2 * h / 6) * (t - point_1.getT());
        Point2D model = new Point2D();
        model.setX(x);
        model.setY(y);
        result.add(model);
        model = null;

        return result;
    }

    /*
     *   通过弯矩计算得到结果
     *
     *   @param:
     *       hList: b数列
     *       momentList: 弯矩数列
     *       pointList: 带参数的点列
     *
     *   @return:
     *       结果
     *
     */
    private static ArrayList<Point2D> GetSplineCurveResultPointList(ArrayList<Double> hList, ArrayList<ArrayList<Double>> momentList, ArrayList<Point2DWithParameter> pointList) {
        ArrayList<Point2D> result = new ArrayList<>();

        for (int i = 0; i < pointList.size() - 1; i++) {
            result.addAll(GetSingleSplineCurveResultPointList(hList.get(i), momentList.get(0).get(i), momentList.get(0).get(i + 1), momentList.get(1).get(i), momentList.get(1).get(i + 1), pointList.get(i), pointList.get(i + 1)));
        }

        return result;
    }

    /*
     *   三次样条曲线
     *
     *   @param:
     *       pointList: 带参数的点列
     *
     *   @return:
     *       结果
     *
     */
    public static ArrayList<Point2D> GetSplineCurvePointList(ArrayList<Point2D> pointList) {
        if (pointList.size() < 2)
            return null;
        ArrayList<Point2D> result = new ArrayList<>();

        //参数化
        ArrayList<Point2DWithParameter> parameterPointList = ParameterizationUtil.UniformParametric(pointList);

        //解三弯矩方程
        ArrayList<Double> hList = GetHList(parameterPointList);
        ArrayList<ArrayList<Double>> momentList = GetMomentParameter(parameterPointList, hList);

        result = GetSplineCurveResultPointList(hList, momentList, parameterPointList);

        return result;
    }
}
