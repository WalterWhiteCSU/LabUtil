/*******************************************************************
 /*
 /*   @Author:            LiJiaJun
 /*   @CreateDate:        2021/5/12
 /*   @Description:       The panting 2D util
 /*
 /********************************************************************
 */

package com.jnu.labutil.util;

import com.jnu.labutil.entity.CurvePaintingPageOutPut;
import com.jnu.labutil.entity.Point2D;
import com.jnu.labutil.entity.Point2DWithParameter;
import com.jnu.labutil.entity.Vector;
import org.ujmp.core.Matrix;
import org.ujmp.core.doublematrix.DoubleMatrix;

import java.util.ArrayList;

public class Painting2DUtil {
    static double BEZIER_DELTA = 0.01;
    static double SPLINE_DELTA = 0.01;

    /*
     *
     *   添加控制顶点
     * */
    private static ArrayList<Point2D> GetControlPointList(ArrayList<Point2D> pointList) {
        ArrayList<Point2D> result = new ArrayList<>();

        //添加第一第二个点
        result.add(pointList.get(0));
        Vector temp = new Vector(pointList.get(0), pointList.get(1)).zoom(0.5);
        result.add(pointList.get(0).addVector(temp));

        //添加之后的控制顶点
        for (int i = 1; i < pointList.size() - 1; i++) {
            temp = new Vector(pointList.get(i + 1), pointList.get(i - 1)).zoom(0.25);
            result.add(pointList.get(i).addVector(temp));
            result.add(pointList.get(i));
            result.add(pointList.get(i).addVector(temp.zoom(-1.0)));
        }

        //添加最后一个顶点
        temp = new Vector(pointList.get(pointList.size() - 1), pointList.get(pointList.size() - 2)).zoom(0.5);
        result.add(pointList.get(pointList.size() - 1).addVector(temp));
        result.add(pointList.get(pointList.size() - 1));

        return result;
    }

    /*
     *
     *   得到三次贝塞尔曲线
     * */
    private static ArrayList<Point2D> GetCubeBezier(Point2D point_1, Point2D point_2, Point2D point_3, Point2D point_4) {
        ArrayList<Point2D> result = new ArrayList<>();

        double t = 0.0;
        while (t < 1.0) {
            Point2D model = new Point2D();
            model.setX(Math.pow((1 - t), 3) * point_1.getX() + 3 * t * Math.pow(1 - t, 2) * point_2.getX() + 3 * t * t * (1 - t) * point_3.getX() + t * t * t * point_4.getX());
            model.setY(Math.pow((1 - t), 3) * point_1.getY() + 3 * t * Math.pow(1 - t, 2) * point_2.getX() + 3 * t * t * (1 - t) * point_3.getY() + t * t * t * point_4.getY());
            result.add(model);
            t += BEZIER_DELTA;
        }

        t = 1.0;
        Point2D model = new Point2D();
        model.setX(Math.pow((1 - t), 3) * point_1.getX() + 3 * t * Math.pow(1 - t, 2) * point_2.getX() + 3 * t * t * (1 - t) * point_3.getX() + t * t * t * point_4.getX());
        model.setY(Math.pow((1 - t), 3) * point_1.getY() + 3 * t * Math.pow(1 - t, 2) * point_2.getX() + 3 * t * t * (1 - t) * point_3.getY() + t * t * t * point_4.getY());
        result.add(model);
        model = null;

        return result;
    }

    /*
     *   三次贝塞尔曲线绘制
     *   @Remark:abandon
     */
    public static CurvePaintingPageOutPut GetBezierPointList(ArrayList<Point2D> pointList) {
        CurvePaintingPageOutPut result = new CurvePaintingPageOutPut();
<<<<<<< Updated upstream

        ArrayList<Point2D> curvePointList = new ArrayList<>();
=======
>>>>>>> Stashed changes

        ArrayList<Point2D> controlPointList = GetControlPointList(pointList);

        result.setControlPointList(controlPointList);
        for (int i = 0; i < controlPointList.size() - 3; i += 3) {
<<<<<<< Updated upstream
            curvePointList.addAll(GetCubeBezier(controlPointList.get(i), controlPointList.get(i + 1), controlPointList.get(i + 2), controlPointList.get(i + 3)));
=======
            result.setPointList(GetCubeBezier(controlPointList.get(i), controlPointList.get(i + 1), controlPointList.get(i + 2), controlPointList.get(i + 3)));
>>>>>>> Stashed changes
        }

        result.setPointList(curvePointList);
        result.setControlPointList(controlPointList);

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
        ArrayList<Point2DWithParameter> parameterPointList = ParameterizationUtil.ChordParametetric(pointList);

        //解三弯矩方程
        ArrayList<Double> hList = GetHList(parameterPointList);
        ArrayList<ArrayList<Double>> momentList = GetMomentParameter(parameterPointList, hList);

        result = GetSplineCurveResultPointList(hList, momentList, parameterPointList);

        return result;
    }

    /*
     *
     *   得到圆弧的点的序列
     *
     * */
    private static ArrayList<Point2D> GetArc(double radius, double startAngle, double endAngle, Vector moveVector) {
        ArrayList<Point2D> result = new ArrayList<>();

        double delta = 5.0 / radius;
        double angle = startAngle;
        while (angle < endAngle) {
            Point2D model = new Point2D();
            model.setX(radius * Math.cos(angle));
            model.setY(radius * Math.sin(angle));
            result.add(model.addVector(moveVector));
            angle += delta;
        }

        Point2D model = new Point2D();
        model.setX(radius * Math.cos(endAngle));
        model.setY(radius * Math.sin(endAngle));
        result.add(model.addVector(moveVector));
        model = null;

        return result;
    }

    /*
     *
     *   画圆
     * */
    public static ArrayList<Point2D> GetCirclePointList(ArrayList<Point2D> pointList) {
        ArrayList<Point2D> result = new ArrayList<>();

        //得到圆心点
        Point2D center = pointList.get(0);
        //得到半径
        double radius = Point2D.GetTwoPointDistance(pointList.get(0), pointList.get(1));

        Vector moveVector = new Vector(new Point2D(0.0, 0.0), center);

        result = GetArc(radius, 0.0, 2 * Math.PI, moveVector);

        return result;
    }

    public static ArrayList<Point2D> GetArcPointList(Point2D center, Point2D firstPoint, Point2D secongPoint) {
        ArrayList<Point2D> result = new ArrayList<>();

        //计算得到半径
        double radius = Math.sqrt(Math.pow(center.getX() - firstPoint.getX(), 2) + Math.pow(center.getY() - firstPoint.getY(), 2));

        Vector moveVector = new Vector(new Point2D(0.0, 0.0), center);
        //得到x正方向的向量
        Vector xVector = new Vector(1.0, 0.0, 0.0);
        Vector startVector = new Vector(center, firstPoint);
        Vector endVector = new Vector(center, secongPoint);

        //得到起始边和终止边与x正方向的夹角
        double startAngle = Vector.GetAngle(xVector, startVector);
        double endAngle = Vector.GetAngle(xVector, endVector);

        //得到正确的角度
        if (startVector.getY() < 0.0)
            startAngle *= -1.0;
        if (endVector.getY() < 0.0)
            endAngle *= -1.0;

        if (startAngle == endAngle)
            return null;

        //如果起始角度大于终止角度，需要将终止角度加360
        if (startAngle > endAngle)
            endAngle += Math.PI * 2;

        //得到结果
        result = GetArc(radius, startAngle, endAngle, moveVector);
        return result;
    }
}
