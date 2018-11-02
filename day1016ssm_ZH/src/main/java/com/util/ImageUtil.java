package com.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ImageUtil {
    //1、定义变量保存生成后的验证码字符串
    static String code="";
    //2、生成验证码
    public static String createcode(){
        //置空，放置拼接字符串
        code="";
        //定义验证码的取值范围
        String a="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        //生成四位字符串
        for (int i = 0; i < 4; i++) {
            //随机生成每个字符
            int index =(int)(Math.random()*62);
            char b = a.charAt(index);
            //拼接
            code+=b;
        }
        return code;
    }

    //查看已经生成的验证码
    public  static String getCode(){
        return code;
    }

    //生成图片
    public static BufferedImage createimage(){
        //生成一个图片框，指定大小和颜色
        BufferedImage bi = new BufferedImage(100,30,BufferedImage.TYPE_INT_RGB);
        //得到一个画布
        Graphics g =bi.getGraphics();
        //添加背景颜色
        g.setColor(Color.magenta);
        //填充
        g.fillRect(0,0,100,30);

        //添加随机颜色的干扰线
        for (int i = 0; i < 30; i++) {
            Random r =new Random();
            //随机三原色的取值范围，并组成随机颜色
            int red = r.nextInt(256);
            int green = r.nextInt(256);
            int blue = r.nextInt(256);
            Color c =new Color(red,green,blue);
            g.setColor(c);

            //随机获取直线两点的坐标
            int x1=r.nextInt(101);
            int y1=r.nextInt(31);

            int x2=r.nextInt(101);
            int y2=r.nextInt(31);

            g.drawLine(x1,y1,x2,y2);
        }
        //添加文字
        g.setColor(Color.white);
        g.setFont(new Font("宋体",Font.BOLD,25));

        //得到验证码
        String str = getCode();
        //将文字填充到画板上
        g.drawString(str,10,25);

        //关闭画布
        g.dispose();

        return bi;
    }
}
