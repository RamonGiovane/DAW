<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>E-Livros</title>
</head>
<body>
	<h2>Bem-vindo à Livraria E-livros!</h2>
		<h4>Encontre um produto</h4>
		<form action="buscarLivro" method="get">
			Nome do Livro: <input type="text" placeholder="Ex: A Ilha do Tesouro">
			<input type="submit" value="Pesquisar">
		</form>
		
		<h4>Categorias de Livros</h4>
		<table border=1>
			<tr>
				<th>Id</th>
				<th>Descrição</th>
			</tr>

			<c:forEach items="${lista}" var="livro">
				<tr>
					<td>${livro.autor}</td>
					<td>${livro.titulo}</td>
				</tr>
			</c:forEach>
		</table>
	
</body>
</html>