<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">

<body>
	<c:import url="cabecalho.jsp"></c:import>
	<div style="display:inline-block; margin-left: 60px">
		<form action="efetuarLogin" method="post"> <br>
			Login:<input type="text" name="usuario"> <br>
			Senha:<input type="password" name="senha">		
			<input type="submit" value="Entrar">
		</form>
	</div>
	
</body>

</head>

</html>