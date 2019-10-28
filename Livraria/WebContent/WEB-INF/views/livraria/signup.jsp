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
		<h4>Cadastrar-se</h4>
		<form action="signup" method="post"> <br>
			Nome de Usuário:<input type="text" name="usuario"> <br>
			Defina uma Senha:<input type="password" name="senha">		
			<input type="submit" value="Cadastrar">
		</form>
		
		<c:if test="${sessionScope.erroCadastro eq true}">
			<p>ERRO: Este usuário já existe.</p>
		</c:if>
	</div>
	
</body>

</head>

</html>