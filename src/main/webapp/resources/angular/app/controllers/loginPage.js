flyk.controller("loginPageCtrl", function($rootScope, $scope, $location, $http, $uibModal) {
	
	$scope.showLoginModal = function () {


        $scope.modal = $uibModal.open({
            templateUrl: "login.html",
            controller: "loginPageCtrl",
            scope: $scope
        });
    }
    
    $scope.closeLoginModal = function(){
    	$scope.modal.dismiss('cancel');
    }
    
    $scope.sendPostLogin = function(){
		$http({
			url : 'efetuarLogin',
			method : "POST",
			data : {
				'email' : $scope.useremail,
				'senha' : $scope.userpassword,
				'lembrar' : $scope.lembrar
			}
		}).then(function(response) {
			if(response.data.retorno == "erro"){
				$scope.mensagemLogin = response.data.mensagem;
				$scope.useremail = "";
				$scope.userpassword = "";
			}else{
				$scope.closeLoginModal();
				$scope.mensagemLogin = "";
				$rootScope.usuarioLogado = response.data.usuario;
				$rootScope.tipoUsuarioLogado = response.data.tipoCadastro;
				$location.path('/adminPage');
			}
			
		}, function(response) {
			$scope.mensagemLogin = response.data.mensagem;
			$scope.useremail = "";
			$scope.userpassword = "";
		});
    }

    $scope.logOff = function(){
    	$rootScope.usuarioLogado = "";
		$rootScope.tipoUsuarioLogado = "";
		$location.path('/home');
    }
   
});
