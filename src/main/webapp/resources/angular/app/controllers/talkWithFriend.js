flyk.controller("talkCtrl", function($scope, $rootScope, $location,
		$http, fileReader) {
	$scope.parentScrollable = true;
	
	$scope.init = function () {
		$rootScope.usuarioLogado = localStorage.getItem("usuarioLogado");
		$rootScope.tipoUsuarioLogado = localStorage.getItem("tipoUsuarioLogado");
		$rootScope.idUsuarioLogado = localStorage.getItem("idUsuarioLogado");
		$rootScope.data = JSON.parse(localStorage.getItem("dadosCliente"));
    }
	
	$scope.showTalkWithFriend = function (){
		$location.path('/talkWithFriend');
	}
	
	$scope.mostrarConversa = function(idAmigo){
		$rootScope.data.chat = [];
		$http({
			url : 'mostrarConversa',
			method : "POST",
			data : {
				'idCliente' : $rootScope.data.id,
				'idAmigo' : idAmigo,
			}
		}).then(function(response) {
			if (response.data.retorno != "erro") {
				$rootScope.data.chat.amigo = response.data.amigo;
				$rootScope.data.chat.listaConversa= response.data.conversaAmigo;
				$rootScope.data.chat.numMensagens= response.data.numMensagens;
				$scope.mensagemErro="";
			} else {
				$rootScope.data.chat.amigo = null;
				$rootScope.data.chat.listaConversa= null;
			}
		}, function(response) {

		});
	}
	
	$scope.enviarNovaMensagem = function(){
		if($rootScope.data.id!=null && $rootScope.data.chat.amigo!=null && $rootScope.data.nova_mensagem!=null){
		$http({
			url : 'enviarNovaMensagem',
			method : "POST",
			data : {
				'idCliente' : $rootScope.data.id,
				'idAmigo' : $rootScope.data.chat.amigo,
				'mensagem': $rootScope.data.nova_mensagem
			}
		}).then(function(response) {
			if (response.data.retorno != "erro") {
				$rootScope.data.chat.amigo = response.data.amigo;
				$rootScope.data.chat.listaConversa= response.data.conversaAmigo;
				$rootScope.data.chat.numMensagens= response.data.numMensagens;
				$rootScope.data.nova_mensagem = "";
				$scope.mensagemErro="";
			} else {
				$scope.mensagemErro = "Erro no envio";
			}
		}, function(response) {

		});
	}else{
		$scope.mensagemErro = "Faltando Dados para o Envio";
	}
	}
});