<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:import url="cabecalho.jsp" />
	Contato ${param.nome} adicionado com sucesso!<br />
	<jsp:useBean id="dao" class="dao.ContatoDAO" />
	<table border="1">
		<tr>
		<td>Nome</td><td>Endereco</td><td>Email</td><td>Data de Nascimento</td><td>Date Formate</td>
		</tr>
		<c:forEach var="contato" items="${dao.lista}" varStatus="id">
			<tr bgcolor="${id.count % 2 == 0 ? 'aaee88' : 'ffffff'}">
			<td>${contato.nome}</td>
			<td>${contato.endereco}</td>
			<td>
			  <c:if test="${not empty contato.email}">
				<a href="mailto:${contato.email}">${contato.email}</a>
			  </c:if>
			</td>
			<td>${contato.dataNascimento.time}</td>
			<td><fmt:formatDate value="${contato.dataNascimento.time}" pattern="dd/MM/yyyy"/></td>
			</tr>
	    </c:forEach>
	</table>
	<c:import url="rodape.jsp"/>
</body>
</html>