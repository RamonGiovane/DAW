<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clube da Leitura</title>
</head>
<body>
	<table border="1">
				<tr>
					<td>Data de Devolução</td>
					<td>ID do Amigo</td>
					<td>Id da Revista</td>
		</tr>
			
		<c:forEach items="${requestScope.emprestimos}"  var="emp" varStatus="i">
			<tr bgcolor="#${i.count % 2 == 0 ? '#fff' : '#babaca'}" >
				<td>${emp.dataDevolucao.time}</td>
				<td>${emp.idAmigo}</td>
				<td>${emp.idRevista}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>