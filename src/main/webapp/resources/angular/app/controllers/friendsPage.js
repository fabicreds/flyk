flyk.controller("friendsPageCtrl", function ($scope, $rootScope, $location, $http, fileReader) {
	
	$scope.init = function () {
		$rootScope.usuarioLogado = localStorage.getItem("usuarioLogado");
		$rootScope.tipoUsuarioLogado = localStorage.getItem("tipoUsuarioLogado");
		$rootScope.idUsuarioLogado = localStorage.getItem("idUsuarioLogado");
    	$scope.showFriendsPage();
    }
	
	$scope.showFriendsPage = function() {
		if ($rootScope.data == null) {
			$http({
				url : 'friendsPage',
				method : "POST",
				data : {
					'usuario' : localStorage.getItem("usuarioLogado"),
					'idUsuario' : localStorage.getItem("idUsuarioLogado"),
					'tipoUsuario' : localStorage.getItem("tipoUsuarioLogado")
				}
			}).then(function(response) {
				if (response.data.retorno != "erro") {
					$rootScope.data = response.data;
					$location.path('/friendsPage');
				} else {
					$location.path('/friendsPage');
				}
			}, function(response) {

			});
		} else {
			$location.path('/friendsPage');
		}
	};
});