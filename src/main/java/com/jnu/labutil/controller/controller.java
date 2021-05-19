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
import com.jnu.labutil.entity.CurvePaintingPageInPut;
import com.jnu.labutil.entity.CurvePaintingPageOutPut;
import com.jnu.labutil.entity.Point2D;
import com.jnu.labutil.service.Paint2DService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@RestController
public class controller {
    /*   页面展示   */
    @RequestMapping(value = "/curve_painting",method = RequestMethod.GET)
    public ModelAndView test(){
        ModelAndView mv = new ModelAndView("test");
        return mv;
    }

<<<<<<< Updated upstream
    @PostMapping(value = "/test/1")
    public String test(@RequestBody String pageInPut){
        System.out.println(pageInPut);
        ObjectMapper mapper = new ObjectMapper();
        String resultList = new String();
        try {
            CurvePaintingPageInPut curvePaintingPageInPut = mapper.readValue(pageInPut, CurvePaintingPageInPut.class);
            CurvePaintingPageOutPut result = Paint2DService.GetCurve(curvePaintingPageInPut);
=======
    /*   绘制   */
    @PostMapping(value = "/curve_painting/paint")
    public String line(@RequestBody String pageInput){
        System.out.println(pageInput);
        ObjectMapper mapper = new ObjectMapper();
        String resultList = new String();
        try {
            CurvePaintingPageInPut pointList = mapper.readValue(pageInput, CurvePaintingPageInPut.class);
            CurvePaintingPageOutPut result = Paint2DService.Painting(pointList);
>>>>>>> Stashed changes
            resultList = mapper.writeValueAsString(result);
//            if (result != null && result.size() != 0) {
//                WriteCSVUtil.Write2DPointListToCSV(result, "C:\\Users\\WalterWhite\\Desktop\\");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(resultList);
        return resultList;
    }
}
