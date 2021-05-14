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

    public static ArrayList<Point2D> GetLine(ArrayList<Point2D> pointList) {
        ArrayList<Point2D> result = new ArrayList<>();

        for (int i = 0; i < pointList.size() - 1; i++) {
            result.addAll(Painting2DUtil.GetLinePointList(pointList.get(i), pointList.get(i + 1)));
        }

        return result;
    }

    public static ArrayList<Point2D> GetBezierCurve(ArrayList<Point2D> pointList) {
        return Painting2DUtil.GetBezierCurvePointList(pointList);
    }
}
