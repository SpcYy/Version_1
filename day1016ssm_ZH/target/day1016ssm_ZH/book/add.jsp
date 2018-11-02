<%@ page import="java.util.Calendar"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><title>
	学生信息管理平台
</title>
	<link href="../Style/StudentStyle.css" rel="stylesheet" type="text/css" />
	<link href="../Script/jBox/Skins/Blue/jbox.css" rel="stylesheet" type="text/css" />
	<link href="../Style/ks.css" rel="stylesheet" type="text/css" />
	<link href="../css/mine.css" type="text/css" rel="stylesheet">
    <script src="../Script/jBox/jquery-1.4.2.min.js" type="text/javascript"></script>
    <script src="../Script/jBox/jquery.jBox-2.3.min.js" type="text/javascript"></script>
    <script src="../Script/jBox/i18n/jquery.jBox-zh-CN.js" type="text/javascript"></script>
    <script src="../Script/Common.js" type="text/javascript"></script>
    <script src="../Script/Data.js" type="text/javascript"></script>
    

</head>
<body>

		<div class="div_head">
            <span>
                <span style="float:left">当前位置是：书籍管理-》资料上传</span>
                <span style="float:right;margin-right: 8px;font-weight: bold"></span>
            </span>
        </div>
</div>
<div class="cztable">
    <form action="/book/Addbook" method="post" enctype="multipart/form-data">
	<table border="1" width="100%" class="table_a">
                <tr>
                    <td width="120px;">编号：<span style="color:red">*</span>：</td>
                    <td><input type="text" name="informationid"/></td>
                </tr>
                <tr>
                    <td>资料名称 ：<span style="color:red">*</span>：</td>
                    <td>
                       <input type="text" name="informationname" value="" />
                    </td>
                </tr>
               
                <tr>
                    <td>资料类型 ：<span style="color:red">*</span>：</td>
                    <td>
                        <select name="typeid">
                            <option value="1">复习资料</option>
                            <option value="2">练习题</option>
                            <option value="3">内部资料</option>
                            <option value="4">真题</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>更新时间：<span style="color:red">*</span>：</td>
                    <td>
                   <input type="text" name="date" value="<%=new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime())%>"/>
                    </td>
                </tr>

				 <tr>
                    <td>格式：<span style="color:red">*</span>：</td>
                    <td>
                        <select name="filetype">
                            <option value="1">doc</option>
                            <option value="2">文件夹</option>

                        </select>
                </tr>
                <tr>
                    <td>上传人：<span style="color:red">*</span>：</td>
                    <td>
						<input type="text" name="uploader" value="王二麻子" />
                    </td>
                </tr>
				<tr>
                    <td>上传：<span style="color:red">*</span>：</td>
                    <td>
                        <input type="file" name="myfile" />
                    </td> 
                </tr>
				

                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="提交"> <input type="submit" value="返回" onclick="history.back();">
                    </td>
                </tr>  
            </table>
	</form>
</div>

            </div>
        </div>
    </div>

        <script type="text/javascript">
            function display(clock){
                var now=new Date();   //创建Date对象
                var year=now.getFullYear(); //获取年份
                var month=now.getMonth(); //获取月份
                var date=now.getDate();  //获取日期
                month=month+1;
                var time=year+"/"+month+"/"+date; //组合系统时间
                clock.innerHTML=time; //显示系统时间
            }
            window.onload=function(){
                window.setInterval("display(clock)", 1000);
            }
        </script>
</body>
</html>
