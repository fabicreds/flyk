flyk.controller("servicosPrestadosCtrl", function ($scope, $rootScope, $location, $http, fileReader) {
	
	$scope.init = function () {
		$rootScope.usuarioLogado = localStorage.getItem("usuarioLogado");
		$rootScope.tipoUsuarioLogado = localStorage.getItem("tipoUsuarioLogado");
		$rootScope.idUsuarioLogado = localStorage.getItem("idUsuarioLogado");
		$rootScope.data = JSON.parse(localStorage.getItem("dadosCliente"));
    }
	
	$scope.showServicosPrestados = function() {
		$location.path('/servicosPrestados');
	};
});