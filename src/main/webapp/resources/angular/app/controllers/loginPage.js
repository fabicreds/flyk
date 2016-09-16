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
				if(response.data.tipoCadastro == "4"){
					$location.path('/adminPage');
				}else{
					$rootScope.data = response.data.cliente;
					$location.path('/profilePage');
				}
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
   
    $scope.newUser = function()
    {
    	$location.path('/cadastro');
    	$scope.modal.dismiss('cancel');
    }
});
