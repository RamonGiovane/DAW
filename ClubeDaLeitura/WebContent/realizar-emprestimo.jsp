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
		ID Revista: <input name="revista" type="text" readonly="readonly" value="${requestScope.id}">
		Coleção: <input type="text" readonly="readonly" value="${requestScope.colecao}">
		Número de Edição: <input type="text" readonly="readonly" value="${requestScope.edicao}">
		ID do Amigo: <input name="amigo" type="text">
		
		
		<input type="hidden" name="logica" value="AdicionaEmprestimo">
		<input type="submit" value="Gravar">
	</form>
</body>
</html>