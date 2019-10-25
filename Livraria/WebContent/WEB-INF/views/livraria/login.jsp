<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">

<body>
	<form action="mvc" method="post">
		Login:<input type="text" name="usuario">
		Senha:<input type="password" name="senha">		
		<input type="hidden" name="logica" value="login">
		<input type="submit" value="Entrar">
	</form>
</body>

</head>

</html>