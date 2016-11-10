<%@ page contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Memoriam</title>
	<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/memoriam.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<h2>Memori<i class="glyphicon glyphicon-phone"></i>m</h2>
			<table>
				<tr align="left">
					<th></th>
					<th style="width: 50%">Operadora</th>
					<th>Prefixo</th>
				</tr>
				
				<c:forEach var="operadora" items="${operadoras}">
				<tr align="left">
					<td><input type="checkbox"></td>
					<td><a href="controller.do?op=edtopr&id=${operadora.id}">${operadora.nome}</a></td>
					<td>${operadora.prefixo}</td>
				</tr>
				</c:forEach>
			</table>
			<a href="operadora/cadastro.jsp" class="form-control btn btn-primary">Novo</a>
		</div>
	</div>
</body>
</html>