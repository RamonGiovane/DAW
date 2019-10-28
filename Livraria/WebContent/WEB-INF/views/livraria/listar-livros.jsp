<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>E-Livros</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>
		<c:import url="cabecalho.jsp"></c:import>
		<div style="display: flex; align-items: center; margin: 60px 200px;">
			
			<c:if test="${empty lista}">
				Não há nenhum produto disponível a ser mostrado aqui :(
			</c:if>
			<c:if test="${not empty lista}">
			<table border=1>
				<tr>
				
					<th>Título</th>
					<th>Autor</th>
					<th>Descrição</th>
					<th>Categoria</th>
					<th>Preço</th>
					<th>Comprar</th>
				</tr>
	
				<c:forEach items="${lista}" var="livro">
					<tr>
						<td>${livro.titulo}</td>
						<td>${livro.autor}</td>
						<td>${livro.descricao}</td>
						<td>${livro.categoria.descricao}</td>
						<td>R$ ${livro.preco}</td>
						<td><a href="adicionarAoCarrinho+?codigo=${livro.codigo}">Comprar</a></td>
						
					</tr>
				</c:forEach>
			</table>
			</c:if>
		</div>
	
</body>
</html>