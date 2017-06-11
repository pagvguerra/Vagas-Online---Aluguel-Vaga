$(function() {
	
	$("#enviar").on('click', function() {

		var form  = $("#formularioAutenticacao");
		var login = $("#login").val().trim();
		var senha = $("#senha").val().trim();
			
		if(login === undefined || login == null || login === '') {
			alert('Preencha o Login');
			$("#login").focus();
			return;
		}

		if(senha === undefined || senha == null || senha === '') {
			alert('Preencha a Senha');
			$("#senha").focus();
			return;
		}
		 
		form.submit();
		
	});
	
} );