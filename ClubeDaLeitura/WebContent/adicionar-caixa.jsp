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
	<form action="mvc" method="post">
		Cor da Caixa: <input type="text" name="cor">
		
		<input type="hidden" name="logica" value="AdicionaCaixa">
		<input type="submit" value="Gravar">
	</form>
</body>
</html>