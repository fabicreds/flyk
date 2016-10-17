flyk.controller("avaliarServicoCtrl", function ($scope, $rootScope, $location, $http, fileReader) {
	
	
	$scope.avaliacaoNota = [{id: 1,label: '1'},{id: 2,label: '2'},{id: 3,label: '3'},{id: 4,label: '4'},{id: 5,label: '5'}]

	
	$scope.init = function () {
		$rootScope.usuarioLogado = localStorage.getItem("usuarioLogado");
		$rootScope.tipoUsuarioLogado = localStorage.getItem("tipoUsuarioLogado");
		$rootScope.idUsuarioLogado = localStorage.getItem("idUsuarioLogado");
		$rootScope.data = JSON.parse(localStorage.getItem("dadosCliente"));
		$scope.avaliacao = {};
		$scope.avaliacao.avaliacaoQualidade = $scope.avaliacaoNota[4].id;
		$scope.avaliacao.avaliacaoTempo = $scope.avaliacaoNota[4].id;
		$scope.avaliacao.avaliacaoPreco = $scope.avaliacaoNota[4].id;
		$scope.avaliacao.avaliacaoProfissionalismo = $scope.avaliacaoNota[4].id;
		$scope.avaliacao.avaliacaoCliente = $scope.avaliacaoNota[4].id;
    }
	
	$scope.limparAvaliacaoPrestador = function(){
		$scope.avaliacao.avaliacaoQualidade = $scope.avaliacaoNota[4].id;
		$scope.avaliacao.avaliacaoTempo = $scope.avaliacaoNota[4].id;
		$scope.avaliacao.avaliacaoPreco = $scope.avaliacaoNota[4].id;
		$scope.avaliacao.avaliacaoProfissionalismo = $scope.avaliacaoNota[4].id;
	}
	
	$scope.limparAvaliacaoCliente = function(){
		$scope.avaliacao.avaliacaoCliente = $scope.avaliacaoNota[4].id;
	}
	
	$scope.avaliarPrestador = function(){
		$http({
			url : 'avaliarPrestador',
			method : "POST",
			data : {
				'idCliente' : $rootScope.idUsuarioLogado,
				'idPrestador' : $rootScope.data.compromisso.contrato.prestador.id,
				'compromisso': $rootScope.data.compromisso,
				'avaliacao': $scope.avaliacao
			}
		}).then(function(response) {
			if (response.data.retorno != "erro") {
				$scope.mensagemErro = "";
				$scope.mensagemSucesso = response.data.mensagem;
				$rootScope.$emit("CallProfilePageMethod", {});
				$location.path('/servicosContratados');
			} else {
				$scope.mensagemErro = response.data.mensagem;
				$scope.mensagemSucesso = "";
				$location.path('/servicosContratados');
			}
		}, function(response) {

		});
	}
	
	$scope.avaliarCliente = function(){
		$http({
			url : 'avaliarCliente',
			method : "POST",
			data : {
				'idCliente' : $rootScope.data.compromisso.contrato.cliente.id,
				'idPrestador' : $rootScope.idUsuarioLogado,
				'compromisso': $rootScope.data.compromisso,
				'avaliacao': $scope.avaliacao.avaliacaoCliente
			}
		}).then(function(response) {
			if (response.data.retorno != "erro") {
				$scope.mensagemErro = "";
				$scope.mensagemSucesso = response.data.mensagem;
				$rootScope.$emit("CallProfilePageMethod", {});
				$location.path('/servicosPrestados');
			} else {
				$scope.mensagemErro = response.data.mensagem;
				$scope.mensagemSucesso = "";
				$location.path('/servicosPrestados');
			}
		}, function(response) {

		});
	}

});