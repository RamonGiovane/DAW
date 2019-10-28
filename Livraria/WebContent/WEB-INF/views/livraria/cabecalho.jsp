<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

</head>
<body>
	<div>
		<h2>Bem-vindo à Livraria E-livros!</h2>
			<h4>Encontre um produto</h4>
			<form action="buscarLivro" method="get">
				Nome do Livro: <input type="text" name="nomeLivro" placeholder="Ex: A Ilha do Tesouro">
				<input type="submit" value="Pesquisar">
			</form>
	</div>	
		<div style="display:inline-block; float:left">
			<h4>Explore</h4>
			
			<table border=1>
				<tr>
					
					<th>Categorias</th>
				</tr>
	
				<c:forEach items="${categorias}" var="cat">
					<tr>
						<td><a href="buscarLivroPorCategoria?id=${cat.id}">${cat.descricao}</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
		<div style="margin-left: 200px">
			<br>
			
			
			<a href="inicio">HOME</a>
			 | 
			<a href="exibirCarrinho+">CARRINHO</a>
			<c:if test="${not empty sessionScope.usuarioLogado}">
				 | 
				<a href="pedidos+">PEDIDOS</a>				
				 | 
				<a href="logout">SAIR</a>
			</c:if>
			<c:if test="${empty sessionScope.usuarioLogado}">
				 | 
				<a href="formLogin">ENTRAR</a>
			</c:if>
		</div>
</body>
</html>