<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Shop 管理中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />




<body >
<s:actionerror />
<form method="post" action="${pageContext.request.contextPath }/adminUser_login.action" name='theForm' >
  <table cellspacing="0" cellpadding="0" style="margin-top: 100px" align="center">
  <tr>
    
   <td style="padding-left: 50px">
      <table>
      <tr>
        <td>管理员姓名：</td>
        <td><input type="text" name="username" /></td>
      </tr>
      <tr>
        <td>管理员密码：</td>
        <td><input type="password" name="password" /></td>
      </tr>
      
      <tr><td>&nbsp;</td><td><input type="submit" value="登录" class="button" /></td>
      </tr>
      <tr>
        <td><a href="${pageContext.request.contextPath }/admin/index.jsp" >返回首页</a></td> 
        <td><a href="" >您忘记了密码吗?</a></td>
      </tr>
      </table>
    </td>
  </tr>
  </table>
  
</form>

</body>