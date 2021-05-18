/*******************************************************************
 /*
 /*   @Author:            LiJiaJun
 /*   @CreateDate:        2021/5/12
 /*   @Description:       The Parameterization util
 /*
 /********************************************************************
 */


package com.jnu.labutil.util;

import com.jnu.labutil.entity.Point2D;
import com.jnu.labutil.entity.Point2DWithParameter;

import java.util.ArrayList;

public class ParameterizationUtil {

    /*   均匀参数化   */
    public static ArrayList<Point2DWithParameter> UniformParametric(ArrayList<Point2D> pointList) {
        ArrayList<Point2DWithParameter> result = new ArrayList<>();

        double t = 0;
        double delta = 1.0 / (pointList.size() - 1);
        for (int i = 0; i < pointList.size() - 1; i++) {
            Point2DWithParameter model = new Point2DWithParameter();
            model.setT(t);
            model.setX(pointList.get(i).getX());
            model.setY(pointList.get(i).getY());
            t += delta;
            result.add(model);
        }
        //加最后一个点
        Point2DWithParameter model = new Point2DWithParameter();
        model.setT(1.0);
        model.setX(pointList.get(pointList.size() - 1).getX());
        model.setY(pointList.get(pointList.size() - 1).getY());
        result.add(model);
        model = null;

        return result;
    }
}
