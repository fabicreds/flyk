flyk.controller("talkCtrl", function($scope, $rootScope, $location,
		$http, fileReader, $interval, $anchorScroll) {
	
	$scope.parentScrollable = true;
	$interval(callAtInterval, 3000);
	
	function callAtInterval() {
		$rootScope.usuarioLogado = localStorage.getItem("usuarioLogado");
		$rootScope.tipoUsuarioLogado = localStorage.getItem("tipoUsuarioLogado");
		$rootScope.idUsuarioLogado = localStorage.getItem("idUsuarioLogado");
		if($rootScope.tipoUsuarioLogado!=4){
		    if($rootScope.data!=null && $rootScope.data.chat !=null && $rootScope.data.chat.amigo !=null && $rootScope.data.chat.amigo != ''){
		    	$scope.mostrarConversa($rootScope.data.chat.amigo);
		    }
		}
	}
	
	$scope.init = function () {
		
		$rootScope.usuarioLogado = localStorage.getItem("usuarioLogado");
		$rootScope.tipoUsuarioLogado = localStorage.getItem("tipoUsuarioLogado");
		$rootScope.idUsuarioLogado = localStorage.getItem("idUsuarioLogado");
		$rootScope.data = JSON.parse(localStorage.getItem("dadosCliente"));
		$rootScope.data.chat = [];
		$rootScope.data.chat.amigo = localStorage.getItem("chat");
		if($rootScope.data.chat.amigo!=null && $rootScope.data.chat.amigo!= ''){
			$scope.mostrarConversa($rootScope.data.chat.amigo);
		}
		$rootScope.data.chat.sendWithEnter = Boolean(localStorage.getItem("sendWithEnter")=="true");
    }
	
	$scope.showTalkWithFriend = function (){
		$rootScope.data.chat = [];
		$rootScope.data.chat.amigo = '';
		localStorage.setItem("chat", $rootScope.data.chat.amigo);
		$location.path('/talkWithFriend');
	}
	$scope.mostrarConversaEnter = function(keyEvent){
		if (keyEvent.which == 13){
			if($rootScope.data.chat.sendWithEnter && $rootScope.data.nova_mensagem!=null && $rootScope.data.nova_mensagem != ''){
				$scope.enviarNovaMensagem();
			}
		}
		
	}
	
	$scope.mostrarConversa = function(idAmigo){
		
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
				localStorage.setItem("chat", $rootScope.data.chat.amigo);
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
				localStorage.setItem("chat", $rootScope.data.chat.amigo);
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
	
	$scope.changeSendWithEnter = function(){
		localStorage.setItem("sendWithEnter", $rootScope.data.chat.sendWithEnter);
	}
});