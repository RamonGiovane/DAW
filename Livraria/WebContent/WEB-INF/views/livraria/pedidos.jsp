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
			
			<c:if test="${empty pedidos}">
				Nenhum pedido feito ainda. 
			</c:if>
			<c:if test="${not empty pedidos}">
				<table border=1>
					<tr>
						
						<th>Código</th>
						<th>Valor</th>
						<th>Livros</th>
						
					</tr>
		
					<c:forEach items="${pedidos}" var="p">
						<tr>
							<td>${p.codigo}</td>
							<td>R$${p.preco}</td>
							<td>
								<c:forEach items="${p.livros}" var="l">
									<p>${l.titulo} x ${l.quantidade}</p>
									<br>
								</c:forEach>
							</td>
							
						</tr>
						
					</c:forEach>
				</table>
			</c:if>
		</div>
	
</body>
</html>