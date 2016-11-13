flyk.controller("contatoCtrl", function($rootScope, $scope, $location, $http, $uibModal) {
	
	$scope.showContatoPage = function(){
		$location.path('/contato');
	}
	
	$scope.enviarEmail = function(){
		$http({
			url : 'enviarEmail',
			method : "POST",
			data : {
				'nome' : $scope.nome,
				'email' : $scope.email,
				'assunto': $scope.assunto,
				'mensagem':$scope.mensagem				
			}
		}).then(function(response) {
			if (response.data.retorno != "erro") {

				$scope.messageErro="";
				$scope.messageSucesso= "Email enviado com sucesso!";
			} else {
				$scope.messageErro= response.data.mensagem;
				$scope.messageSucesso="";
			}
		}, function(response) {

		});
	}
	
	$scope.limpar = function(){
		$scope.nome = null;
		$scope.email = null;
		$scope.assunto = null;
		$scope.mensagem = null;
		$scope.messageErro = null;
		$scope.messageSucesso = null;
	}
	
});