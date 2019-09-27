<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="autentica.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clube da Leitura</title>
</head>
<body>
	<h1>Bem-vindo(a)</h1>
	<a href="mvc?logica=Logout">[Sair]</a><br>
	<a href="adicionar-amigo.jsp">Adicionar Amigo</a>
	<br>
	<a href="adicionar-caixa.jsp">Adicionar Caixa</a>
	<br>
	<a href="adicionar-revista.jsp">Adicionar Revista</a>
	<br>
	<a href="lista-revistas-disponiveis.jsp">Realizar Empréstimo</a>
	<br>
	<a href="lista-emprestimos.jsp">Lista de Empréstimos</a>
	<br>
	<a href="lista-emprestimos-atrasados.jsp">Lista de Empréstimos Atrasados</a>
	<br>
	<a href="buscar-amigo.jsp">Procurar Emprestimo de Amigo</a> 	
</body>
</html>