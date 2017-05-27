<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<%@ taglib uri="../../tlds/c.tld" prefix="c" %>
<c:set var="js" value="${pageContext.request.contextPath}/estaticos/js"/>
<c:set var="jspaginas" value="${pageContext.request.contextPath}/estaticos/js/paginas"/>
<c:set var="js" value="${pageContext.request.contextPath}/estaticos/js"/>
<c:set var="css_bootstrap" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>ESTACIONAMENTO ONLINE - ALUGUEL DE VAGA</title>
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
		<script>
			function voltar() {
				var form = document.getElementById("form");
				form.action = 'http://localhost:8080/EstacionamentoOnlineEntradaFuncionario/servlet/VagaController';
				form.method = 'post';
				form.acao.value = 'LISTAR_TODOS';
				form.submit();
			}
		</script>
	</head>
	<body>
		<br/><br/>
		<div class="container">
			<div class="col-sm-10">
				<%@include file="../../includes/cabecalho.jsp" %>
				<fieldset>
				<legend><B>TELA DE ALUGUEL DE VAGA</B></legend>
				<br><br>
					<div class="form-group">
						<b>Vaga alugada com sucesso</b>
					</div>
					<br>
					<form id="form">
						<input type="hidden" name="acao" id="acao" value="LISTAR_TODOS">
						<input type="hidden" name="idEstacionamento" id="idEstacionamento" value="${idEstacionamento}">
						
						<!-- BOTÃO VOLTAR -->
						<input type="button" id="botaoVoltar" value="VOLTAR PARA A LISTA DE VAGAS" class="btn btn-info" onclick="voltar()"/>
					</form>
				</fieldset>	
			</div>
		</div>		
	</body>
</html>