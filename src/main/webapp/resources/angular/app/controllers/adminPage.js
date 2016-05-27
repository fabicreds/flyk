flyk.controller("adminPageCtrl", function($rootScope, $scope, $location, $http, $uibModal) {


	
	$scope.confirm = function() {

		$uibModal.open({
			templateUrl : "modalConfirm.html",
			controller : "modalConfirmCtrl"
		});
	}
	

	$scope.alertNoUserFound = function() {

		$uibModal.open({
			templateUrl : "modalAlertInactiveUser.html"
		});
	}

	$scope.pesquisar = function() {
		
		//$uibModal.open({
		//	templateUrl : "modalAlertInactiveUser.html",
		//    controller : "modalConfirmCtrl"
		//});
		
		$http({
			url : 'buscarUsuarios',
			method : "POST",
			data : {
				'usuarioBusca' : $scope.usuarioBusca,
				'checkAdministrador' : $scope.checkAdministrador
			}
		}).then(function(response) {
			$rootScope.data = response.data;
			$location.path('/userPageInfos');
			response.data = [];
		});
	}

	$scope.sendPostAdm = function() {

//		$uibModal.open({
//			templateUrl : "modalConfirm.html",
//			controller : "modalConfirmCtrl"
//		});
		
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
				$scope.messageErro = response.data.mensagem;
			}else{
				$scope.messageSucesso = response.data.mensagem;
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
	
	$scope.reset = function()
	{
		 $scope.admname="";
		 $scope.admusername="";
		 $scope.admpassword="";
	}
	
});