<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="tt" tagdir="/WEB-INF/tags/templating" %> 
<%@ taglib prefix="mm" tagdir="/WEB-INF/tags/messages" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<tt:template title="Consulta a operadoras"> 
	
	<jsp:attribute name="tscript"> 
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> 
		<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script> 
	</jsp:attribute> 
	
	<jsp:body>
		<div class="container">
			<div class="jumbotron">
				<h2>Memori<i class="glyphicon glyphicon-phone"></i>m</h2>
				<table class="table">
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
	</jsp:body> 
</tt:template>