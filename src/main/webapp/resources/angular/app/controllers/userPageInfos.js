flyk.controller("userPageInfosCtrl", function ($scope, $rootScope, $location, $http) {
	
	
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