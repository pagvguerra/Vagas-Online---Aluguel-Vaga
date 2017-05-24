<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<%@ taglib uri="tlds/c.tld" prefix="c" %>
<c:set var="js" value="${pageContext.request.contextPath}/estaticos/js"/>
<c:set var="jspaginas" value="${pageContext.request.contextPath}/estaticos/js/paginas"/>
<c:set var="css_bootstrap" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>ESTACIONAMENTOOO ONLINE - ERRO GENÉRICO</title>
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
		<script src="${jspaginas}/autenticacao.js" ></script>
	</head>
	<body>
		<br/><br/>
		<div class="container">
			<div class="col-sm-4">
				<fieldset>
					<div class="form-group">
						<b>Ocorreu um erro! <br> Entre em contato com o administrador do sistema</b>
					</div>
					<br><br>
				</fieldset>	
			</div>
		</div>		
	</body>
</html>