<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>测试传参</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form action="registAction.action" method="post">
    <table>
    <tr>
    <td>用户名：</td>
    <td><input type="text" id="name" name="name"></td>
    </tr>
    <tr>
    <td>密码：</td>
    <td><input type="password" id="password" name="password"></td>
    </tr>
     <tr>
    
    <td colspan="2"><input type="submit" value="提交"></td>
    </tr>
    </table>
     
    </form>
  </body>
</html>
