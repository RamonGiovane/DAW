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
	<c:import url="cabecalho.jsp"></c:import>
		
		<div style="display: inline-block; align-items: center; margin: -1px 150px;">
			<br>
			
			<c:if test="${ empty sessionScope.usuarioLogado}">

				Novo por aqui? <a href="formSignup">CADASTRAR-SE</a>
				 | 
				Já possui uma conta? <a href="formLogin">ENTRAR</a>
				  
			</c:if>
			
			<c:if test="${not empty sessionScope.usuarioLogado}">

				Olá, ${sessionScope.usuarioLogado.login}!
				  
			</c:if>
			 
			
			<br>
			
			<c:choose>
				
				<c:when test="${sessionScope.usuarioLogado.tipoUsuario == 'ADMIN' or sessionScope.usuarioLogado.tipoUsuario == 'GERENTE'}">
				
					<h4>Gerenciar</h4>
					<ul>
						<li><a href="formLivro">Adicionar Livro</a></li>
						<li><a href="formCategoria">Adicionar Categoria</a></li>
						<li><a href="formSignup">Adicionar Usuário Gerente</a></li>
					</ul>
				
				</c:when>
				
				<c:otherwise>
					<h4>Todos os livros</h4>
					<table border=1>
							<tr>
							
								<th>Título</th>
								<th>Autor</th>
								<th>Descrição</th>
								<th>Categoria</th>
								<th>Preço</th>
								<th>Comprar</th>
							</tr>
				
							<c:forEach items="${livros}" var="livro">
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
				</c:otherwise>
				

			</c:choose>
			
		</div>
</body>
</html>