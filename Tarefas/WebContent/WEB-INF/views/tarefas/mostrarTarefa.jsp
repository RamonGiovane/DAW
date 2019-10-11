<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="alterarTarefa" method="get">
		Id: <input type="text" name="id" readonly value="${tarefa.id}"></><br/><br/>
		Descrição:
		<textarea rows="5" cols="100" name="descricao">${tarefa.descricao}</textarea><br/>
		<input type="submit" value="Alterar">
	</form>
</body>
</html>