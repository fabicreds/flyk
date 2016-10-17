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
	
	$scope.orcarServico = function(compromisso) {
		$rootScope.data.compromisso = {};
		$rootScope.data.compromisso = compromisso;
		localStorage.setItem("dadosCliente", JSON.stringify($rootScope.data));
		$location.path('/orcarServico');
	};
	
	$scope.sendOrcarServico = function() {
		$http({
			url : 'atualizarCompromisso',
			method : "POST",
			data : {
				'idPrestador' : localStorage.getItem("idUsuarioLogado"),
				'idCliente': $rootScope.data.compromisso.contrato.cliente.id,
				'compromisso' : $rootScope.data.compromisso,
				'status': 3,
				'cliente': false
			}
		}).then(function(response) {
			if (response.data.retorno != "erro") {
				$rootScope.$emit("CallProfilePageMethod", {});
				$location.path('/servicosPrestados');
				$scope.mensagemSucesso = 'OK';
			} else {
				$scope.mensagemErro = response.data.mensagem;
			}
		}, function(response) {

		});
	};
	
	$scope.realizarServico = function(compromisso) {
		$http({
			url : 'atualizarCompromisso',
			method : "POST",
			data : {
				'idPrestador' : localStorage.getItem("idUsuarioLogado"),
				'idCliente': compromisso.contrato.cliente.id,
				'compromisso' : compromisso,
				'status': 6,
				'cliente': false
			}
		}).then(function(response) {
			if (response.data.retorno != "erro") {
				$rootScope.$emit("CallProfilePageMethod", {});
				$location.path('/servicosPrestados');
				$scope.mensagemSucesso = 'OK';
			} else {
				$scope.mensagemErro = response.data.mensagem;
			}
		}, function(response) {

		});
	};
	
	
	$scope.cancelarServico = function(compromisso) {
		$http({
			url : 'atualizarCompromisso',
			method : "POST",
			data : {
				'idPrestador' : localStorage.getItem("idUsuarioLogado"),
				'idCliente': compromisso.contrato.cliente.id,
				'compromisso' : compromisso,
				'status': 5,
				'cliente': false
			}
		}).then(function(response) {
			if (response.data.retorno != "erro") {
				$rootScope.$emit("CallProfilePageMethod", {});
				$location.path('/servicosPrestados');
				$scope.mensagemSucesso = 'OK';
			} else {
				$scope.mensagemErro = response.data.mensagem;
			}
		}, function(response) {

		});
	};
	
	$scope.recusarServico = function(compromisso) {
		$http({
			url : 'atualizarCompromisso',
			method : "POST",
			data : {
				'idPrestador' : localStorage.getItem("idUsuarioLogado"),
				'idCliente': compromisso.contrato.cliente.id,
				'compromisso' : compromisso,
				'status': 2,
				'cliente': false
			}
		}).then(function(response) {
			if (response.data.retorno != "erro") {
				$rootScope.$emit("CallProfilePageMethod", {});
				$location.path('/servicosPrestados');
				$scope.mensagemSucesso = 'OK';
			} else {
				$scope.mensagemErro = response.data.mensagem;
			}
		}, function(response) {

		});
	};
	
	$scope.avaliarCliente = function(compromisso) {
		$rootScope.data.compromisso = {};
		$rootScope.data.compromisso = compromisso;
		localStorage.setItem("dadosCliente", JSON.stringify($rootScope.data));
		$location.path('/avaliarCliente');
	};
});