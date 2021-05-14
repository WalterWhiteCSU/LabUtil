/*******************************************************************
 /*
 /*   @Author:            LiJiaJun
 /*   @CreateDate:        2021/5/12
 /*   @Description:       The Curve panting controller
 /*
 /********************************************************************
 */


package com.jnu.labutil.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jnu.labutil.entity.Point2D;
import com.jnu.labutil.service.Paint2DService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@RestController
public class controller {
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public ModelAndView test(){
        ModelAndView mv = new ModelAndView("test");
        return mv;
    }

    @PostMapping(value = "/test/1")
    public String test(@RequestBody String point){
        System.out.println(point);
        ObjectMapper mapper = new ObjectMapper();
        String resultList = new String();
        try {
            ArrayList<Point2D> pointList = mapper.readValue(point, new TypeReference<ArrayList<Point2D>>() {
            });
            ArrayList<Point2D> result = Paint2DService.GetBezierCurve(pointList);
            resultList = mapper.writeValueAsString(result);
//            if (result != null && result.size() != 0) {
//                WriteCSVUtil.Write2DPointListToCSV(result, "C:\\Users\\WalterWhite\\Desktop\\");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
