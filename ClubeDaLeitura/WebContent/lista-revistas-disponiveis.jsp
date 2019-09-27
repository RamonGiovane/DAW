<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clube da Leitura</title>
</head>
<body>

	<jsp:useBean id="dao" class="br.tsi.daw.dao.RevistaDAO"></jsp:useBean>

	<table border="1">
		<tr>
			<th>ID</th>
			<th>Coleção</th>
			<th>Edição</th>
			<th>Ano</th>
			<th>Caixa</th>
			<th>Opções<th>					
			
		</tr>
	
		<c:forEach var="revista" items="${dao.revistasDisponiveis}" varStatus="i">
			<tr bgcolor="#${i.count % 2 == 0 ? '#babaca' : 'white'}">
				<td>${revista.id}</td>
				<td>${revista.colecao}</td>
				<td>${revista.numeroEdicao}</td>
				<td>${revista.ano}</td>
				<td>${revista.idCaixa}</td>
				<td>
					<a href="mvc?logica=EmprestaRevista&id=${revista.id}&colecao=${revista.colecao}&edicao=${revista.numeroEdicao}">
					[Emprestar]
					</a>
				</td>
			</tr>

		</c:forEach>
		
		</table>
	
		</body>
</html>