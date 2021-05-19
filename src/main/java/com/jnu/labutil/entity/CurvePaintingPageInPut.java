/*******************************************************************
 /*
 /*   @Author:            LiJiaJun
 /*   @CreateDate:        2021/5/19
<<<<<<< Updated upstream
 /*   @Description:       The Curve Painting Page InPut
=======
 /*   @Description:       The Curve panting page input
>>>>>>> Stashed changes
 /*
 /********************************************************************
 */

package com.jnu.labutil.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CurvePaintingPageInPut {
    private String paintingType;
    private ArrayList<Point2D> pointList;
}
