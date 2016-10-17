flyk.controller("servicosContratadosCtrl", function ($scope, $rootScope, $location, $http, fileReader) {
	
	$scope.init = function () {
		$rootScope.usuarioLogado = localStorage.getItem("usuarioLogado");
		$rootScope.tipoUsuarioLogado = localStorage.getItem("tipoUsuarioLogado");
		$rootScope.idUsuarioLogado = localStorage.getItem("idUsuarioLogado");
		$rootScope.data = JSON.parse(localStorage.getItem("dadosCliente"));
    }
	
	$scope.showServicosContratados = function() {
		$location.path('/servicosContratados');
	};
	
	$scope.sendMarcarServico = function(compromisso) {
		$http({
			url : 'atualizarCompromisso',
			method : "POST",
			data : {
				'idPrestador' : compromisso.contrato.prestador.id,
				'idCliente':localStorage.getItem("idUsuarioLogado"),
				'compromisso' : compromisso,
				'status': 4,
				'cliente': true
			}
		}).then(function(response) {
			if (response.data.retorno != "erro") {
				$rootScope.$emit("CallProfilePageMethod", {});
				$location.path('/servicosContratados');
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
				'idPrestador' : compromisso.contrato.prestador.id,
				'idCliente':localStorage.getItem("idUsuarioLogado"),
				'compromisso' : compromisso,
				'status': 5,
				'cliente': false
			}
		}).then(function(response) {
			if (response.data.retorno != "erro") {
				$rootScope.$emit("CallProfilePageMethod", {});
				$location.path('/servicosContratados');
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
				'idPrestador' : compromisso.contrato.prestador.id,
				'idCliente':localStorage.getItem("idUsuarioLogado"),
				'compromisso' : compromisso,
				'status': 2,
				'cliente': false
			}
		}).then(function(response) {
			if (response.data.retorno != "erro") {
				$rootScope.$emit("CallProfilePageMethod", {});
				$location.path('/servicosContratados');
				$scope.mensagemSucesso = 'OK';
			} else {
				$scope.mensagemErro = response.data.mensagem;
			}
		}, function(response) {

		});
	};
	
	$scope.avaliarPrestador = function(compromisso) {
		$rootScope.data.compromisso = {};
		$rootScope.data.compromisso = compromisso;
		localStorage.setItem("dadosCliente", JSON.stringify($rootScope.data));
		$location.path('/avaliarPrestador');
	};
});