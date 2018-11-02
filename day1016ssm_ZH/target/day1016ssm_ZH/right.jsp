<!doctype html public "-//w3c//dtd xhtml 1.0 frameset//en" "http://www.w3.org/tr/xhtml1/dtd/xhtml1-frameset.dtd">
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv=content-type content="text/html; charset=utf-8" />
        <link href="css/admin.css" type="text/css" rel="stylesheet" />
        <script type="text/javascript" src="Script/jquery-1.8.0.min.js"></script>
    </head>
    <body>
        <table cellspacing=0 cellpadding=0 width="100%" align=center border=0>
            <tr height=28>
                <td background=./img/title_bg1.jpg>当前位置: </td></tr>
            <tr>
                <td bgcolor=#b1ceef height=1></td></tr>
            <tr height=20>
                <td background=./img/shadow_bg.jpg></td></tr></table>
        <table cellspacing=0 cellpadding=0 width="90%" align=center border=0>
            <tr height=100>
                <td align=middle width=100>
					<img height=100 src="./img/admin_p.gif"  width=90>
				</td>
                <td width=60>&nbsp;</td>
                <td>
                    <table height=100 cellspacing=0 cellpadding=0 width="100%" border=0>

                        <tr>
                         <td><div id="clock" ></div></td>
						</tr>
                        <tr>
                            <td style="font-weight: bold; font-size: 16px">${u1.userName}</td>
						</tr>
                        <tr>
                            <td>欢迎进入网站管理中心！</td></tr></table></td>
						</tr>
            <tr>
         <td colspan=3 height=10></td></tr></table>
        <table cellspacing=0 cellpadding=0 width="95%" align=center border=0>
            <tr height=20>
                <td></td></tr>
            <tr height=22>
                <td style="padding-left: 20px; font-weight: bold; color: #ffffff" 
                    align=middle background=./img/title_bg2.jpg>个人信息</td></tr>
            <tr bgcolor=#ecf4fc height=12>
                <td></td></tr>
            <tr height=20>
                <td></td></tr></table>
        <table cellspacing=0 cellpadding=2 width="95%" align=center border=0>
            <tr>
                <td align=right width=100>登陆帐号：</td>
                <td style="color: #880000">${u1.userName}</td></tr>
            <tr>
                <td align=right>真实姓名：</td>
                <td style="color: #880000">${u1.userRealname}</td></tr>
            <tr>
                <td align=right>注册时间：</td>
                <td style="color: #880000"><fmt:formatDate value="${u1.regdate}" pattern="yyyy-MM-dd"/></td></tr>
            <tr>
                <td align=right>登陆次数：</td>
                <td style="color: #880000">${u1.logincount}</td></tr>
            <tr>
                <td align=right>上线时间：</td>
                <td style="color: #880000"><fmt:formatDate value="${logintime}" pattern="yyyy-MM-dd hh:mm:ss"/></td></tr>
            <tr>
                <td align=right>归属地查询：</td>
                <td style="color: #880000"><input type="text" name="phone"/><span></span> </td>
            </tr>

            <script>
                $(function () {
                    $("[name=phone]").blur(function () {
                        var phone=$(this).val();
                        $.ajax({
                            url:"getaddress",
                            data:"phone="+phone,
                            type:"post",
                            dataType:"text",
                            success:function (rs) {
                                //截取输出结果
                                // var index =rs.lastIndexOf(" ");
                                // rs=rs.substring(index+1);
                                $("[name=phone]").next().html(rs);
                            }
                        })
                    })
                })
            </script>
        </table>		
<div style="text-align:center;">
<p>维护信息：<a href="http://www.zparkedu.com" target="_blank">湖南管理学院</a></p>
<p>联系电话：<a href="http://www.zparkedu.com" target="_blank">17110771077</a></p>
</div>
    <script type="text/javascript">
        function display(clock){
            var now=new Date();   //创建Date对象
            var year=now.getFullYear(); //获取年份
            var month=now.getMonth(); //获取月份
            var date=now.getDate();  //获取日期
            var day=now.getDay();  //获取星期
            var hour=now.getHours(); //获取小时
            var minu=now.getMinutes(); //获取分钟
            var sec=now.getSeconds(); //获取秒钟
            month=month+1;
            var arr_week=new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
            var week=arr_week[day];  //获取中文的星期
            var time=year+"年"+month+"月"+date+"日 "+week+" "+hour+":"+minu+":"+sec; //组合系统时间
            clock.innerHTML="当前时间："+time; //显示系统时间
        }
        window.onload=function(){
            window.setInterval("display(clock)", 1000);
        }
    </script>
    </body>
</html>