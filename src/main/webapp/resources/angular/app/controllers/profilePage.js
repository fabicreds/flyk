flyk.controller("profilePageCtrl", function ($scope, $rootScope, $location, $http, fileReader) {

    $scope.showProfilePageEdit = function() {
    	$location.path('/profilePageEdit');
    }
    

    
    $scope.init = function () {
    	$scope.showProfilePage();
    }
    
    $scope.showProfilePage = function() {
		if ($rootScope.data == null || ($rootScope.data != null && $rootScope.data.nome == null)) {
			$http({
				url : 'profilePage',
				method : "POST",
				data : {
					'usuario' : localStorage.getItem("usuarioLogado"),
					'idUsuario' : localStorage.getItem("idUsuarioLogado"),
					'tipoUsuario' : localStorage.getItem("tipoUsuarioLogado")
				}
			}).then(function(response) {
				if (response.data.retorno != "erro") {
					$rootScope.usuarioLogado = response.data.usuario;
					$rootScope.tipoUsuarioLogado = response.data.tipoCadastro;
					$rootScope.idUsuarioLogado = response.data.cliente.id;
					$rootScope.data = response.data.cliente;
					localStorage.setItem("dadosCliente", JSON.stringify($rootScope.data));
					$location.path('/profilePage');
				} else {
					$location.path('/profilePage');
				}
			}, function(response) {

			});
		} else {
			$location.path('/profilePage');
		}
	};
});