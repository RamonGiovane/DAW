<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Adicionar Revista</title>
</head>
<body>
	<h2>Adicionar Revista</h2>
	<form action="mvc" method="post" >
		Cole��o: <input type="text" name="colecao">
		N�mero da Edi��o: <input type="text" name="numero">
		Ano da Revista: <input type="text" name="ano">
		ID da Caixa: <input type="text" name="caixa">
		
		<input type="hidden" name="logica" value="AdicionaRevista">
		<input type="submit" value="Gravar">
		
	</form> 
	
	</body>
</html>