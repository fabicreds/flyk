flyk.controller("searchPageCtrl", function($scope, $rootScope, $location,
		$http, fileReader) {
	
	$scope.init = function () {
		$rootScope.usuarioLogado = localStorage.getItem("usuarioLogado");
		$rootScope.tipoUsuarioLogado = localStorage.getItem("tipoUsuarioLogado");
		$rootScope.idUsuarioLogado = localStorage.getItem("idUsuarioLogado");
		$rootScope.data = JSON.parse(localStorage.getItem("dadosCliente"));
    }

	$scope.showSearchPage = function() {
		$location.path('/searchPage');
	};

});