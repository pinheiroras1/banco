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
Formulario para alteracao de contatos : <br />
<form action="mvc" method="post">
	Id : <input type="text" name="id" /> <br />
	Nome : <input type="text" name="nome" /> <br />
	E-mail : <input type="text" name="email" /><br />
	Endereco : <input type="text" name="endereco"><br />
	Data de Nascimento :  <input type="text" name="dataNascimento"/>
	<fmt:formatDate value="${contato.dataNascimento.time}" pattern="dd/MM/yyyy"/> 
	<input type="hidden" name="logica" value="AlteraContatoLogic"/>
	<input type="submit" value="Enviar"/>
</form>
<c:import url="rodape.jsp" />
</body>
</html>