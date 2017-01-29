<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="tt" tagdir="/WEB-INF/tags/templating" %> 
<%@ taglib prefix="mm" tagdir="/WEB-INF/tags/messages" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
<tt:template title="Cadastro de contatos"> 
	<jsp:attribute name="tscript"> 
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> 
		<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script> 
	</jsp:attribute> 			
	
	<jsp:body>
	<div class="container">
			<div class="jumbotron">
				<h2>Memori<i class="glyphicon glyphicon-phone"></i>m</h2>
				
				<h3>Dados da Operadora</h3>
				
										
				<form action="${pageContext.request.contextPath}/controller.do" method="post" class="form-horizontal">
					<input type="hidden" name="op" value="cadopr">
					<input type="hidden" id="id" name="id" value="${operadora.id}"> 
					<input class="form-control" id="nome" type="text" name="nome" value="${operadora.nome}" placeholder="Nome da operadora">
					<input class="form-control" id="prefixo" type="text" name="prefixo" value="${operadora.prefixo}" placeholder="Prefixo">
					<input class="fomr-control btn btn-primary" type="submit" value="Salvar"> 
					
					<!-- Mensagens de erro do formulario -->
					<mm:messages value="${msgs}" erroStyle="color:red" infoStyle="color:blue" avisoStyle="color:grey"/> 
				
				</form>
			</div>
		</div>
		
		<c:set var="endofconversation" value="true" scope="request"/>
	</jsp:body> 
</tt:template>