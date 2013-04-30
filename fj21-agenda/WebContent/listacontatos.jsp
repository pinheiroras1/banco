<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*,java.util.*,model.*,java.text.*" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:import url="cabecalho.jsp" />
<!-- <a href="removecontato.html">excluir contato</a> <a href="testa-altera-mvc.jsp">alterarcontato</a> -->
<table border="1">
	<tr>
		<td>Id</td>
		<td>Nome</td>
		<td>Email</td>
		<td>Endereco</td>
		<td>Data de Nascimento</td>
		<td>==acao==</td>
	</tr>

<%
	ContatoDAO dao = new ContatoDAO();
	List<Contato> contatos = dao.getLista();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
	for(Contato contato: contatos) {
		
%>
	<tr>
		<td><%=contato.getId() %></td>
		<td><%=contato.getNome()%></td>
		<td><%=contato.getEmail()%></td>
		<td><%=contato.getEndereco()%></td>
		<td><%=sdf.format(contato.getDataNascimento().getTime())%></td>
		<td><a href="removecontato.jsp">excluir contato</a> <a href="testa-altera-mvc.jsp">alterarcontato</a></td>
	</tr>
	<%} %>
</table>
<c:import url="rodape.jsp" />	
</body>
</html>