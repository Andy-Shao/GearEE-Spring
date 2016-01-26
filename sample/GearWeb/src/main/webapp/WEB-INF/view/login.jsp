<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>WMS</title>
</head>
<body>
<center>
<h1>Login Page</h1>
<form action="<%=request.getContextPath()%>/login/process.html" method="post">
<label>Username:</label>
<input type="text" name="username"/><br/>
<label>Password:</label>
<input type="password" name="password"/><br/>
<input type="submit"/>
</form>
</center>
</body>
</html>