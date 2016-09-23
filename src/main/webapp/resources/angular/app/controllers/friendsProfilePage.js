flyk.controller("friendsProfilePageCtrl", function ($scope, $rootScope, $location, $http, fileReader) {

    $scope.sendPostPerfilAmigo = function(id, tipoCadastro) {
    	$http({
			url : 'friendsProfilePage',
			method : "POST",
			data : {
				'idUsuarioLogado': $rootScope.idUsuarioLogado,
				'idAmigo' : id,
				'tipoUsuarioAmigo' : tipoCadastro
			}
		}).then(function(response) {
			if (response.data.retorno != "erro") {
				$rootScope.data.amigo = response.data.amigo;
				$rootScope.data.amigo.statusAmizade = response.data.statusAmizade;
				$rootScope.data.amigo.statusAmizadeDescricao = response.data.statusAmizadeDescricao;
				$location.path('/friendsProfilePage');
			} else {
				$location.path('/friendsProfilePage');
			}
		}, function(response) {

		});
    }
    

    
    $scope.init = function () {
    	$rootScope.usuarioLogado = localStorage.getItem("usuarioLogado");
		$rootScope.tipoUsuarioLogado = localStorage.getItem("tipoUsuarioLogado");
		$rootScope.idUsuarioLogado = localStorage.getItem("idUsuarioLogado");
		$rootScope.data = JSON.parse(localStorage.getItem("dadosCliente"));
    }
    
    
});