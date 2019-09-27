<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clube da Leitura</title>
</head>
<body>
	
	<form action="mvc" method="post">
		Nome: <input type="text" name="nome">
		Telefone: <input type="text" name="telefone">
		<input type="hidden" name="logica" value="AdicionaAmigo">
		<input type="submit" value="Gravar">
	</form>
	
</body>
</html>