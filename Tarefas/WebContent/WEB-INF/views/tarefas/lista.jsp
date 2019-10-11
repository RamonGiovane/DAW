<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Lista de Tarefas</h2>
	<p>Bem-vindo, ${usuarioLogado.login}</p>
	<a href="logout">Sair do Sistema</a> <br> <br>
		<table border=1>
			<tr>
				<th>Id</th>
				<th>Descrição</th>
				<th>Finaliza Agora</th>
				<th>Data Finalização</th>
				<th>Alterar</th>
				<th>Remover</th>
			</tr>

			<c:forEach items="${lista}" var="tarefa">
				<tr>
					<td>${tarefa.id}</td>
					<td>${tarefa.descricao}</td>

					<%-- A sequência de <c:if> a seguir é para testar se  a tarefa foi finalizada ou não.
					Caso tenha sido aparece a mensagem "Finalizada". CAso não tenha sido aparece a pergunta 
					"Finalizar Agora?" apontando par aa ação "finalizar tarefa" em tarefasController --%>
					<c:if test="${tarefa.finalizado eq true}">
						<td>Finalizada</td>
					</c:if>

					<c:if test="${tarefa.finalizado eq false}">
						<td><a href="finalizarTarefa?id=${tarefa.id}">[Finalizar]</a></td>
					</c:if>

					<td>
						<c:if test="${tarefa.finalizado eq false}">
							Em andamento...
						</c:if>
						<c:if test="${tarefa.finalizado eq true}">
							<fmt:formatDate value="${tarefa.datafinalizacao.time}"
								pattern="dd/MM/yyyy" />
						</c:if>
					</td>

					<td><a href="mostrarTarefa?id=${tarefa.id}">Alterar</a></td>
					<td><a href="removerTarefa?id=${tarefa.id}">Remover</a></td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<a href="novaTarefa">Adicionar tarefa</a>
		
	
</body>
</html>