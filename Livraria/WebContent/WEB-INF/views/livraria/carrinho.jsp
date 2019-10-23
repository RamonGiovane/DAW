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
		
		<div style="display: inline-block; align-items: center; margin: -1px 150px;">
			
			<h4>Carrinho</h4>
			<table border=1>
				<tr>
				
					<th>Título</th>
					<th>Autor</th>
					<th>Preço</th>
					<th>Remover</th>
				</tr>
	
				<c:forEach items="${carrinho}" var="livro">
					<tr>
						<td>${livro.titulo}</td>
						<td>${livro.autor}</td>
						<td>R$ ${livro.preco}</td>
						<td style="text-align: center"><a href="removerDoCarrinho?codigo=${livro.codigo}">X</a></td>
						
					</tr>
				</c:forEach>
				<tr>
					<th>Total</th>
					<th style="text-align: right; padding-right: 20px;">R$ ${valorTotal}</th>
				</tr>
					
				
			
				
			</table>
			<br>
			<a href="fecharCarrinho?livro=${livro.codigo}">Fechar carrinho</a>
		</div>
		
</body>
</html>