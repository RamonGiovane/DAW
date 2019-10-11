<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- Se o preenchimento do campo descrição não atender as validações feitas no atributo 
descrição da classe tarefa, ele  recupera a mensagem de erro configurada na validação.
Obs.: Necessidade da Taglib -->
<form:errors path="tarefa.descricao" cssStyle="color:red" /><br/>
	<form action="adicionaTarefa" method="get">
		Descrição:
		<textarea rows="5" cols="100" name="descricao"></textarea><br/>
		<input type="submit" value="Adicionar">
	</form>
</body>
</html>