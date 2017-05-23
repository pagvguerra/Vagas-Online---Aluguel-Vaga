$("#redefinirSenha").on('click', function() {

	var login = $("#login").val().trim();
	var senha = $("#senha").val().trim();
		
	if(login === undefined || login == null || login === '') {
		alert('Preencha o Login');
		$("#login").focus();
		return;
	}

	if(login.length < 6) {
		alert('Digite um Login com pelo menos 6 caracteres');
		return false;
	}
	
	if(senha === undefined || senha == null || senha === '') {
		alert('Preencha a Senha');
		$("#senha").focus();
		return;
	}
		
	if(senha.length < 6) {
		alert('Digite uma Senha com pelo menos 6 caracteres');
		return false;
	}
	
	$('form').attr({
		action : 'http://localhost:8080/EstacionamentoOnlineEntradaFuncionario/servlet/AutenticacaoController',
		method : 'post'
	});

	$("#acao").val('REDEFINIR_SENHA');
	$('form').submit();

});
