<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Emprestimos Atrasados</title>
</head>
<body>
	<jsp:useBean class="br.tsi.daw.dao.EmprestimoDAO" id="dao"></jsp:useBean>
	<table border="1">

			<tr>
					<td>ID</td>
					<td>Data de Empr�stimo</td>
					<td>Data de Devolu��o</td>
					<td>ID do Amigo</td>
					<td>Id da Revista</td>
					<td>Op��es</td>
			</tr>
			<c:forEach items="${dao.emprestimosAtrasados}" var="emp" varStatus="i">
			<tr bgcolor="#${i.count % 2 == 0 ? '#fff' : '#babaca'}">
			
				<td>${emp.id}</td>
				<td>
					<fmt:formatDate pattern="dd/MM/yyyy" value="${emp.dataEmprestimo.time}"/>
				</td>
				<td>
					<fmt:formatDate pattern="dd/MM/yyyy" value="${emp.dataDevolucao.time}"/>
				</td>
				
				<td>${emp.idAmigo}</td>
				<td>${emp.idRevista}</td>
				
				<td>
					<a href="mvc?logica=DevolveRevista&id=${emp.id}&amigo=${emp.idAmigo}&revista=${emp.idRevista}">[Devolver]</a>
				</td>
				
			</tr>
		</c:forEach>
	</table>
</body>
</html>