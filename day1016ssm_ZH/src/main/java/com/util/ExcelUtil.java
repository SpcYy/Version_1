package com.util;

import com.bean.Classes;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExcelUtil {
    //1、创建一个Excel对象
    public static HSSFWorkbook excel = new HSSFWorkbook();

    //2、创建一个sheet文件
    public static HSSFSheet sheet = excel.createSheet("sheet1");

    //3、创建保存头文件目录的数组
    public static String[] headers={"院系","班级编号","班级名称","班主任老师","人数","班级状态"};

    //4、保存sheet表中的列数
    public static int cellcount=headers.length;

    //5、创建第一行
    public static void createhead(){


        HSSFRow row = sheet.createRow(0);
        for (int i=0;i<cellcount;i++){
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
        }
    }

    //6、创建其他行list保存的是要导出的数据
    public static void createother(List list){
        for (int i = 0; i < list.size(); i++) {
            Classes classes = (Classes)list.get(i);
            HSSFRow row = sheet.createRow(i+1);
            HSSFCell c1 =row.createCell(0);
            HSSFCell c2 =row.createCell(1);
            HSSFCell c3 =row.createCell(2);
            HSSFCell c4 =row.createCell(3);
            HSSFCell c5 =row.createCell(4);
            HSSFCell c6 =row.createCell(5);

            c1.setCellValue(classes.getDepartment().getDepartname());
            c2.setCellValue(classes.getClassnum());
            c3.setCellValue(classes.getClassname());
            c4.setCellValue(classes.getClassteacher());
            c5.setCellValue(classes.getPeoplecount());
            c6.setCellValue(classes.getClassstate());
        }
    }

    //7、设置io流和excel的关系 输出流
    public static void export(OutputStream outputStream){

        try {
            //1、设置以表格的方式输出
            sheet.setGridsPrinted(true);
            //2、建立io的关系
            excel.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
