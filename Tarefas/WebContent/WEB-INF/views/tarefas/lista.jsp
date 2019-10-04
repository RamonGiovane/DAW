<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Descrição</th>
			<th>Finalizado</th>
			<th>Data de Finalização</th>
			<th>Opções</th>
		</tr>
		<c:forEach items="${lista}" var="t">
			<tr>
				<td>${t.id}</td>
				<td>${t.descricao}</td>
				<td>${t.finalizado}</td>
				<td>
					<fmt:formatDate value="${t.datafinalizacao.time}" pattern="dd/MM/yyyy"/> 
				</td>
				<td>
					<a>[Finalizar]</a>
					<a >[Alterar]</a>
					<a>[Remover]</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>