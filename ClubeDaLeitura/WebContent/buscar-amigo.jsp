<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clube da Leitura</title>
</head>
<body>
	<form action="mvc" method="post">
		Nome do Amigo: <input type="text" name="nome">
		<input type="hidden" name="logica" value="BuscaAmigo" >
		<input type="submit" value="Pesquisar">
	</form> 
</body>
</html>