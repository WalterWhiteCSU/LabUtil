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

import java.util.ArrayList;

public class ParameterizationUtil {

    /*   均匀参数化   */
    public static ArrayList<Point2D> UniformParametric(ArrayList<Double> pointList) {
        ArrayList<Point2D> result = new ArrayList<>();

        double t = 0;
        double delta = 1.0 / (pointList.size() - 1);
        for (double point : pointList) {
            Point2D model = new Point2D();
            model.setX(t);
            model.setY(point);
            t += delta;
            result.add(model);
        }

        return result;
    }
}
