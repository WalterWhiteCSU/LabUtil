/*******************************************************************
 /*
 /*   @Author:            LiJiaJun
 /*   @CreateDate:        2021/5/13
 /*   @Description:       The CSV Write Util
 /*
 /********************************************************************
 */

package com.jnu.labutil.util;

import com.jnu.labutil.entity.Point2D;

import java.io.*;
import java.util.ArrayList;

public class WriteCSVUtil {

    /*   判断文件是否存在   */
    private static void isFileExsis(File file) throws IOException {
        if (!file.exists()) {
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            file.createNewFile();
        }
    }

    /*   将点写入csv文件中   */
    public static boolean Write2DPointListToCSV(ArrayList<Point2D> pointList, String filePath) throws IOException {
        String fileName = String.valueOf(System.currentTimeMillis()) + ".csv";

        boolean flag = false;

        FileOutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        File CSVFile = new File(filePath + fileName);
        isFileExsis(CSVFile);
        out = new FileOutputStream(CSVFile);
        osw = new OutputStreamWriter(out, "UTF-8");

        bw = new BufferedWriter(osw);

        if (pointList != null && !pointList.isEmpty()) {
            for (Point2D point : pointList) {
                bw.append(point.getX() + ",");
                bw.append(String.valueOf(point.getY()));
                bw.append("\r");
            }

            if (bw != null) {
                bw.close();
                bw = null;
            }
            if (osw != null) {
                osw.close();
                osw = null;
            }
            if (out != null) {
                out.close();
                out = null;
            }
        }

        return true;
    }
}
