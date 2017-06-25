<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<%@ taglib uri="paginas/tlds/c.tld" prefix="c" %>
<c:set var="js" value="${pageContext.request.contextPath}/estaticos/js"/>
<c:set var="jspaginas" value="${pageContext.request.contextPath}/estaticos/js/paginas"/>
<c:set var="css_bootstrap" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<c:set var="images" value="${pageContext.request.contextPath}/estaticos/images"/>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>ESTACIONAMENTO ONLINE - HOME FUNCIONÁRIO</title>
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
		<script src="${jspaginas}/autenticacao.js" ></script>
	</head>
	<body>
		<br/><br/>
		<div class="container">
			<div class="col-sm-2">
				<br><br><br><br><br><br>
				<img src="${images}/logotipo.png" width="110%;" height="25%;">
			</div>
			<div class="col-sm-5">
				<fieldset>
    				<legend><b>FUNCIONÁRIO DE ESTACIONAMENTO</b><br><br>FORMULÁRIO DE ACESSO</legend>
    				<c:if test="${not empty msg}">
	    				<font color="red"><b>${msg}</b></font><br/></br>
					</c:if>
					<form id="formularioAutenticacao" name="autentica" method="post" action="http://localhost:8080/EstacionamentoOnlineEntradaFuncionario/servlet/AutenticacaoController">

						<input type="hidden" name="acao" value="login">

						<!-- LOGIN -->
						<div class="form-group">
							<font color="red">*</font>&nbsp;<b>Login..:</b>
							<input type="text" name="LOGIN" id="login" maxlength="50" class="form-control" placeholder="INFORME O LOGIN">
						</div>	
						
						<!-- SENHA -->
						<div class="form-group">
							<font color="red">*</font>&nbsp;<b>Senha..:</b>
							<input type="password" name="SENHA" id="senha" maxlength="100" class="form-control" placeholder="INFORME A SENHA">
						</div>
						
						<!-- BOTÃO ENVIAR -->
						<input type="button" id="enviar" value="LOGAR" class="btn btn-primary"/>
					</form>
				</fieldset>	
			</div>
		</div>		
	</body>
</html>