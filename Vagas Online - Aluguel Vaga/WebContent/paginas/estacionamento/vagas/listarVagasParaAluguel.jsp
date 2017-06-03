<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<%@ taglib uri="../../tlds/c.tld" prefix="c" %>
<c:set var="js" value="${pageContext.request.contextPath}/estaticos/js"/>
<c:set var="css_bootstrap" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<c:set var="servlet" value="${pageContext.request.contextPath}/estaticos/js/bootstrap/css"/>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>TELA DE ALUGUEL DE VAGAS DO ESTACIONAMENTO</title>		
		<link href="${css_bootstrap}/bootstrap.min.css" rel="stylesheet">
		<script src="${js}/jquery-2.1.1.min.js" ></script>
		<script src="${js}/bootstrap/js/bootstrap.min.js"></script>
		<script>
		function prepararAlugarVaga(idVaga, idEstacionamento) {
			var form = document.getElementById("form");
			form.action = 'http://localhost:8080/EstacionamentoOnlineEntradaFuncionario/servlet/VagaController';
			form.method = 'post';
			form.acao.value = 'PREPARAR_ALUGAR_VAGA';
			form.id.value = idVaga;
			form.idEstacionamento.value = idEstacionamento;
			form.submit();
		}
		function liberarVaga(idVaga, idEstacionamento) {
			var form = document.getElementById("form");
			form.action = 'http://localhost:8080/EstacionamentoOnlineEntradaFuncionario/servlet/VagaController';
			form.method = 'post';
			form.acao.value = 'PREPARAR_LIBERAR_VAGA';
			form.id.value = idVaga;
			form.idEstacionamento.value = idEstacionamento;
			form.submit();
		}
		</script>
	</head>
	<body>
		<br/><br/>
		<div class="container">
			<div class="col-sm-12">
				<%@include file="../../includes/cabecalho.jsp" %> 
				<fieldset>
					<legend>
					<B>TELA DE ALUGUEL DE VAGAS DO ESTACIONAMENTO</B></legend>
					<form id="form" name="form" method="post">
					<input name="acao" id="acao" type="hidden">
					<input name="id" id="id" type="hidden" value="">
					<input name="idEstacionamento" id="idEstacionamento" type="hidden" value="${idEstacionamento}">
					<br><br>
					<table width="100%" class="table table-hover">
						<thead>
						<tr>
							<th>Código da Vaga</th>
							<th>Tipo da Vaga</th>
							<th>Status da Vaga</th>
							<th><center>Data/Hora Entrada</center></th>
							<th><center>Ação</center></th>
						</tr>
						</thead>
						<c:forEach var="vaga" items="${listaVagas}" >
							<tr>
								<td><c:out value="${vaga.codigo}"/></td>
								<td><c:out value="${vaga.tipoVagaBean.nome}"/></td>
								<td>Vaga&nbsp;
									<c:if test="${vaga.status == 'D'}">Disponível</c:if>
									<c:if test="${vaga.status == 'I'}">Alugada</c:if>
								</td>
								<c:if test="${vaga.status == 'D'}">
									<td><center>-</center></td>
								</c:if>
								<c:if test="${vaga.status == 'I'}">
									<td><center><c:out value="${vaga.dataLocacaoVagaStr}"/></center></td>
								</c:if>
								<td>
									<center>
										<c:if test="${vaga.status == 'D'}">
											<input type="submit" onclick="return prepararAlugarVaga(${vaga.id}, ${idEstacionamento});" name="botaoPrepararAlugarVaga" id="botaoPrepararAlugarVaga" value="ALUGAR VAGA" class="btn btn-warning">
										</c:if>
										<c:if test="${vaga.status == 'I'}">
											<input type="submit" onclick="return liberarVaga(${vaga.id}, ${idEstacionamento});" name="botaoLiberarVaga" id="botaoLiberarVaga" value="LIBERAR VAGA" class="btn btn-info">
										</c:if>
									</center>	
								</td>
							</tr>
						</c:forEach>
					</table>
					<br /><br />
				</form>
				</fieldset>
			</div>
		</div>
	</body>
</html>