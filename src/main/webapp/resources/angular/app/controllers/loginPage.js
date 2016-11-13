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
				$rootScope.idUsuarioLogado = response.data.idUsuario;
				localStorage.setItem("usuarioLogado", $rootScope.usuarioLogado);
				localStorage.setItem("tipoUsuarioLogado", $rootScope.tipoUsuarioLogado);
				localStorage.setItem("idUsuarioLogado", $rootScope.idUsuarioLogado);
				localStorage.setItem("dadosCliente", angular.toJson	(response.data.cliente));
				var dadosCli = angular.fromJson(localStorage.getItem("dadosCliente"));

				
				
			
				if(response.data.tipoCadastro == "4"){
					$location.path('/adminPage');
				}else{
					$rootScope.data = response.data.cliente;
					$location.path('/profilePage');
					console.log(response.data.cliente);
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
		$rootScope.idUsuarioLogado = "";
		localStorage.setItem("usuarioLogado", "");
		localStorage.setItem("tipoUsuarioLogado", "");
		localStorage.setItem("idUsuarioLogado", "");
		$location.path('/home');
    }
   
    $scope.newUser = function()
    {
    	$location.path('/cadastro');
    	$scope.modal.dismiss('cancel');
    }
});
