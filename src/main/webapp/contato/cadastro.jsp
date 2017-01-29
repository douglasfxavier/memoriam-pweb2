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
				
				
				 
			
				<h3>Dados do Contato</h3>
				
				<form action="${pageContext.request.contextPath}/controller.do" method="post" class="form-horizontal">
					<input type="hidden" name="op" value="cadctt">
					<input type="hidden" id="id" name="id" value="${contato.id}">
					<input type="hidden" id="idusuario" name="idusuario" value="${sessionScope.usuario.id}">
					<input class="form-control" id="nome" type="text" name="nome" value="${contato.nome}" placeholder="Nome">
					<input class="fomr-control" id="fone" type="text" name="fone" value="${contato.fone}" placeholder="Fone">
					<fmt:formatDate pattern="dd/MM/yyyy" var="dataAniv" value="${contato.dataAniversario}"/>
					<input class="fomr-control" id="dataaniv" type="date" name="dataaniv" value="${dataAniv}" placeholder="Data de criação (dd/mm/aaaa)">
					<select class="form-control" id="operadora" name="operadora">
						<option value="${null}" label="Selecione a operadora"> Selecione a Operadora </option>
						<c:forEach var="operadora" items="${utilBean.operadoras}">
							<c:if test="${operadora.id eq contato.operadora.id}">
								<option value="${operadora.id}" label="${operadora.nome}" selected> ${operadora.nome}</option>									
							</c:if>
							<c:if test="${operadora.id ne contato.operadora.id}">
								<option value="${operadora.id}" label="${operadora.nome}"> ${operadora.nome}</option>									
							</c:if>						
						</c:forEach> 			
					</select>
					<input class="fomr-control btn btn-primary" type="submit" value="Salvar">
					
					<!-- Mensagens de erro do formulario -->
					<mm:messages value="${msgs}" erroStyle="color:red" infoStyle="color:blue" avisoStyle="color:grey"/> 
				
				</form>
			</div>
		</div>
		
		<c:set var="endofconversation" value="true" scope="request"/>
		<c:remove var="msgs" scope="application" />	
	</jsp:body> 
</tt:template>
