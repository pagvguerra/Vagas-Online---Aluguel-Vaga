<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<%@ taglib uri="../../tlds/c.tld" prefix="c" %>
<c:set var="js" value="${pageContext.request.contextPath}/estaticos/js/"/>
<c:set var="css_bootstrap" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<c:set var="jspaginas" value="${pageContext.request.contextPath}/estaticos/js/paginas"/>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>ESTACIONAMENTO ONLINE - ALUGAR VAGA</title>
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
		<script>
		function alugar() {
			var form = document.getElementById("form");
			var placa = $("#placa").val().trim();
			var modelo = $("#modelo").val().trim();
				
			if(placa === undefined || placa == null || placa === '') {
				alert('Preencha a placa');
				$("#placa").focus();
				return false;
			}

			if(modelo === undefined || modelo == null || modelo === '') {
				alert('Preencha o Modelo');
				$("#modelo").focus();
				return false;
			}
				
			form.action = 'http://localhost:8080/EstacionamentoOnlineEntradaFuncionario/servlet/VagaController';
			form.method = 'post';
			form.acao.value = 'ALUGAR_VAGA';
			form.submit();
		}		
		
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
	</body>
		<div class="container">
			<div class="col-sm-10">
				<%@include file="../../includes/cabecalho.jsp" %>
				<fieldset>
				<legend><B>HOME - TELA DE ALUGUEL DE VAGA</B></legend>
					<form id="form" name="form">
					<input name="acao" id="acao" type="hidden">
					<input name="id" id="id" type="hidden" value="${vagaBean.id}">
					<input name="idEstacionamento" id="idEstacionamento" type="hidden" value="${idEstacionamento}">
						<br/>
						<fieldset>
							<legend>Aluguel de Vaga</legend>
							<br>
							<div class="form-group">
								Código da Vaga..: ${vagaBean.codigo} - Tipo: ${vagaBean.tipoVagaBean.nome}
							</div>
							<div class="form-group">
								Preço por/Hora..: <b>R$ ${vagaBean.tipoVagaBean.preco},00</b>
							</div>
							<div class="form-group">
								Placa..: <input class="form-control" type="text" name="placa" id="placa" maxlength="10">
							</div>
							<div class="form-group">
								Modelo..: <input class="form-control" type="text" name="modelo" id="modelo" maxlength="50">
							</div>
							<br/><br/>
							<center>
							<input type="submit" onclick="return alugar();" name="botaoAlugar" id="botaoAlugar" value="ALUGAR VAGA" class="btn btn-success">
							<input type="submit" onclick="return voltar();" name="botaoVoltar" id="botaoVoltar" value="VOLTAR" class="btn btn-info">
							</center>	
						</fieldset> 
					</form>
				</fieldset>
			</div>
		</div>	
</html>