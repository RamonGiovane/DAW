<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de Empr�stimos</title>
</head>
<body>
	<jsp:useBean id="dao" class="br.tsi.daw.dao.EmprestimoDAO" ></jsp:useBean>
	<table border="1">
			<tr>
					<td>ID</td>
					<td>Data de Empr�stimo</td>
					<td>Data de Devolu��o</td>
					<td>ID do Amigo</td>
					<td>Id da Revista</td>
					<td>Op��es</td>
			</tr>
		<c:forEach  var="emp" items="${dao.emprestimos}" varStatus="i">
			<tr bgcolor="#${i.count % 2 == 0 ? '#fffff' : '#babaca'}">
				<td>${emp.id}</td>
				<td>
					<fmt:formatDate value="${emp.dataEmprestimo.time}" pattern="dd/MM/yyyy"/>
				</td>
				<td>
					<fmt:formatDate value="${emp.dataDevolucao.time}" pattern="dd/MM/yyyy"/>
				</td>
				<td>${emp.idAmigo}</td>
				<td>${emp.idRevista}</td>
				<td>
					<a href="mvc?logica=DevolveRevista&revista=${emp.idRevista}&amigo=${emp.idAmigo}&id=${emp.id}">
						[Devolver]
					</a>
				</td>
			</tr>
			
			
		</c:forEach>
	
	</table>
</body>
</html>