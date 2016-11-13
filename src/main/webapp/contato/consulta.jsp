<%@ page contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Memoriam</title>
	<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/memoriam.css" rel="stylesheet">

	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script type=text/javascript>
/* 		function habilitarBotao(){
			if (document.getElementById('contatoschk').checked){
				$("#btnexcluir").show();
			 }else{
				 $("#btnexcluir").hide();
			 }
		} */
		
		function habilitarBotao(){
			var checks = document.forms[0];
			var totalchecks = 0;
			var i;
			for (i = 0; i < checks.length; i++) {
			  if (checks[i].checked) {
				  totalchecks = totalchecks + 1;
			  }
			}
			
			if (totalchecks > 0){
				$("#btnexcluir").show();
			}else{
				$("#btnexcluir").hide();
			}
		}
	</script>
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<h2>Memori<i class="glyphicon glyphicon-phone"></i>m</h2>
			
			<form action="${pageContext.request.contextPath}/controller.do" method="post" class="form-horizontal">
				<input type="hidden" name="op" value="excctt">
				<table>
					<tr align="left">
						<th></th>
						<th style="width: 30%">Nome</th>
						<th>Telefone</th>
						<th>Operadora</th>
					</tr>
					
					<c:forEach var="contato" items="${contatos}">
					<tr align="left">			
							<td><input onClick="habilitarBotao()" id="contatoschk" name="contatoschk" type="checkbox" value="${contato.id}"></td>
							<td><a href="controller.do?op=edtctt&id=${contato.id}">${contato.nome}</a></td>
							<td>${contato.fone}</td>
							<td>${contato.operadora.nome}</td>
					</tr>
					</c:forEach>				
				</table>
				<div id="btnexcluir" style="display:none">
					<input type="submit" class="form-control btn-danger" value="Excluir" onClick="return confirm('Deseja realmente deletar os contatos selecionados?')">
				</div>
			</form>
			
			<c:if test="${contatoschk.checked}">
				
			</c:if>			
			<a href="contato/cadastro.jsp" class="form-control btn btn-primary">Novo</a>
		</div>
	</div>

</body>
</html>