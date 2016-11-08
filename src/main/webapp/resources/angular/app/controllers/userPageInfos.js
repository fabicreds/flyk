flyk.controller("userPageInfosCtrl", function ($scope, $rootScope, $location, $http) {
	
	$scope.init = function(){
		$rootScope.usuarioLogado = localStorage.getItem("usuarioLogado");
		$rootScope.tipoUsuarioLogado = localStorage.getItem("tipoUsuarioLogado");
		$rootScope.idUsuarioLogado = localStorage.getItem("idUsuarioLogado");
	}
	
	$scope.inativar = function(){
		$http({
			url : 'inativarUsuario',
			method : "POST",
			data : {
				'usuario' : $rootScope.data.usuario,
				'tipoCadastro' :$rootScope.data.tipoCadastro,
				'ativo': $rootScope.data.ativo
			}
		}).then(function(response) {
			$rootScope.messageSucessoInativar = "";
			$rootScope.messageErroInativar = "";
			if(response.data.retorno == "erro"){
				$rootScope.messageErroInativar = response.data.mensagem;
				$rootScope.messageSucessoInativar = "";
			}else{
				$rootScope.messageErroInativar = "";
				$rootScope.messageSucessoInativar = response.data.mensagem;
			}
			$location.path('/adminPage');
		}, function(response) {
		});
		
		
	}
	
	
});