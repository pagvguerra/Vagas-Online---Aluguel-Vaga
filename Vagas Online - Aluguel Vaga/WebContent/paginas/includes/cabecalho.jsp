<table width="100%">
	<tr>
		<td><img src="${pageContext.request.contextPath}/estaticos/images/logotipo.png" width="34%;" height="70%;"></td>
		<td><b>Estacionamento:</b>&nbsp;${estacionamentoBean.nomeFantasia}
			<br><br>
			<b>Nome do Funcionário..:</b>&nbsp;${usuario.nome}
		</td>
		<td align="right">
			<form>
				<a href="http://localhost:8080/EstacionamentoOnlineEntradaFuncionario/servlet/AutenticacaoController?acao=LOGOUT"><img src="${pageContext.request.contextPath}/estaticos/images/icon_porta_sair.png" width="40" height="40" title="Logout" ></a>
			</form>
		</td>
	</tr>
</table>
<br /><br />