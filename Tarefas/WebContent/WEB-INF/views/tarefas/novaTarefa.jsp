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
	
	
	<form action="adicionaTarefa" method="get">
		Descrição:
		<textarea rows="5" cols="100" name="descricao"></textarea>
		
		<input type="submit" value="Adicionar">
	
		<br>
	
		<!-- Se o preenchimento do campo descrição não atender as validações feitas no atributo
		descrição da classe tarefa, ele recupera a mensagem de erro configurada na validação
		Obs: Para isso é necessário a taglib form do stringframework -->
		<form:errors path="tarefa.descricao" cssStyle="background-color:#9b1c31; color:white; border: 1px, black;" />
	</form>
</body>
</html>