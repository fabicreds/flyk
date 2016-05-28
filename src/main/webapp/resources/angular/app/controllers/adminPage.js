flyk.controller("adminPageCtrl", function($rootScope, $scope, $location, $http, $uibModal) {


	$scope.pesquisar = function() {
	
		
		$http({
			url : 'buscarUsuarios',
			method : "POST",
			data : {
				'usuarioBusca' : $scope.usuarioBusca,
				'checkAdministrador' : $scope.checkAdministrador
			}
		}).then(function(response) {
			if(response.data==""){
				$rootScope.messageErroInativar = "Nenhum usuário encontrado!";
			}else{
				$rootScope.messageErroInativar = "";
				$rootScope.data = response.data;
				$location.path('/userPageInfos');
			}
			
		});
	}

	$scope.sendPostAdm = function() {

		$http({
			url : 'cadastroAdministrador',
			method : "POST",
			data : {
				'nome' : $scope.admname,
				'usuario' : $scope.admusername,
				'senha' : $scope.admpassword
			}
		}).then(function(response) {
			console.log(response.data);
			if(response.data.retorno == "erro"){
				$scope.messageErroCadastro = response.data.mensagem;
				$scope.messageSucessoCadastro = "";
			}else{
				$scope.messageErroCadastro = "";
				$scope.messageSucessoCadastro = response.data.mensagem;
			}
			$scope.admname = "";
			$scope.admusername = "";
			$scope.admpassword = "";
			
		}, function(response) {
			// fail case
			console.log(response);
			$scope.message = response;
		});

		
	};
	
   $scope.sendPostServices = function() {
		
		$http({
			url : 'cadastroServicos', // nome do request mapping da classe java criada para cadastro de serviços
			method : "POST",
			data : {
				'nome' : $scope.servname,
				'descricao' : $scope.servdescription
			}
		}).then(function(response) {
			console.log(response.data);
			if(response.data.retorno == "erro"){
				$scope.messageErro = response.data.mensagem; // configurar mensagem se o serviço já estiver cadastrado
			}else{
				$scope.messageSucesso = response.data.mensagem; // configurar mensagem para exibir que o serviço foi cadastrado com sucesso
			}
			$scope.servname = "";
			$scope.servdescription = "";
			
		}, function(response) {
			// fail case
			console.log(response);
			$scope.message = response;
		});

		
	};
	

	$scope.resetadmform = function()
	{
		 $scope.admname="";
		 $scope.admusername="";
		 $scope.admpassword="";
	}
	
	$scope.resetservform = function()
	{
		 $scope.servname="";
		 $scope.servdescription="";
	}
	
});