flyk.controller("friendsPageCtrl", function ($scope, $rootScope, $location, $http, fileReader) {
	
	$scope.init = function () {
		$rootScope.usuarioLogado = localStorage.getItem("usuarioLogado");
		$rootScope.tipoUsuarioLogado = localStorage.getItem("tipoUsuarioLogado");
		$rootScope.idUsuarioLogado = localStorage.getItem("idUsuarioLogado");
		$rootScope.data = JSON.parse(localStorage.getItem("dadosCliente"));
//    	$scope.showFriendsPage();
    	$rootScope.$emit("CallProfilePageMethod", {});
    }
	
	$scope.showFriendsPage = function() {
//		if ($rootScope.data == null) {
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
//		} else {
//			if($rootScope.data.listaAmigos == null){
//				$rootScope.data.numAmigos = 0;
//			}
//			$location.path('/friendsPage');
//		}
	};
});