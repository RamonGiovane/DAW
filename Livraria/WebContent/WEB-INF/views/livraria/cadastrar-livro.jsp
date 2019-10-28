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
		
		<c:choose>
			<c:when test="${sessionScope.usuarioLogado.tipoUsuario eq TipoUsuario.USER}">
				<p> ERRO: Permissão negada.</p>
			</c:when>
			<c:otherwise>
				<h4>Cadastrar Livro</h4>
				<form action="cadastrar-livro" method="post"> <br>
					Título:<input type="text" name="titulo"> <br>
					Autor:<input type="text" name="autor"><br>
					Descrição:<input type="text" name="descricao"><br>
					Quantidade: <input type="text" name="quantidade"><br>
					Categoria: <select name="codCategoria">
		
					<c:forEach var="cat" items="${categorias}"> 
						<option value="${cat.id}">${cat.descricao}</option> 
					</c:forEach> 
					</select><br>
					Preço:<input type="text" name="preco"><br>		
				<input type="submit" value="Cadastrar">
				</form>
				<c:if test="${sessionScope.erroCadastro eq true}">
					<p>ERRO: Todos os campos devem ser preenchidos.</p>
				</c:if>
			</c:otherwise>
		</c:choose>
		
	</div>
	
	
</body>

</head>

</html>