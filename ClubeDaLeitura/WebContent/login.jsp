<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="mvc" method="post">
		Login:<input type="text" name="usuario">
		Senha:<input type="password" name="senha">
		<input type="hidden" name="logica" value="Login">
		<input type="submit" value="Entrar">
	</form>
</body>
</html>