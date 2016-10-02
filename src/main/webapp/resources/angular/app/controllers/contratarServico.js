flyk.controller("contratarServicoCtrl", function($scope, $rootScope, $location,
		$http, fileReader) {
	
	$scope.horas = [ {id: 0,label: '00'},  {id: 1,label: '01'},  {id: 2,label: '02'}, {id: 3,label: '03'},
	                      {id: 4,label: '04'},  {id: 5,label: '05'},  {id: 6,label: '06'},
	                      {id: 7,label: '07'},  {id: 8,label: '08'},  {id: 9,label: '09'},
	                      {id: 10,label: '10'}, {id: 11,label: '11'}, {id: 12,label: '12'},
	                      {id: 13,label: '13'}, {id: 14,label: '14'}, {id: 15,label: '15'},
	                      {id: 16,label: '16'}, {id: 17,label: '17'}, {id: 18,label: '18'},
	                      {id: 19,label: '19'}, {id: 20,label: '20'}, {id: 21,label: '21'},
	                      {id: 21,label: '21'}, {id: 22,label: '22'}, {id: 23,label: '23'},
	                      {id: 24,label: '24'}
	                  ];
	$scope.minutos = [ {id: 0,label: '00'},  {id: 5,label: '05'},  {id: 10,label: '10'}, {id: 15,label: '15'},
                     {id: 20,label: '20'},  {id: 25,label: '25'},  {id: 30,label: '30'},
                     {id: 35,label: '35'},  {id: 40,label: '40'},  {id: 45,label: '45'},
                     {id: 50,label: '50'}, {id: 55,label: '55'}
                 ];

	$scope.hora_inicio = $scope.horas[0].id;
	$scope.minuto_inicio = $scope.minutos[0].id;
	$scope.hora_fim = $scope.horas[0].id;
	$scope.minuto_fim = $scope.minutos[0].id;
	
	$scope.carregarTelaContratarServico = function() {

		$location.path('/contratarServico');

	}

	$scope.resetContratarSevicoForm = function() {
		$scope.data_inicio = "";
		$scope.data_fim = "";
	}

	$scope.sendPostContratarServico = function(){
		$http({
			url : 'contratarServico',
			method : "POST",
			data : {
				'idCliente' : $rootScope.data.id,
				'idPrestador' : $rootScope.data.amigo.id,
				'idCategoriaServico' : $scope.servico.id,
				'dataInicio': $scope.data_inicio,
				'dataFim': $scope.data_fim,
				'hora_inicio':$scope.hora_inicio,
				'minuto_inicio':$scope.minuto_inicio,
				'hora_fim':$scope.hora_fim,
				'minuto_fim':$scope.minuto_fim
					
			}
		}).then(function(response) {
			if (response.data.retorno != "erro") {
				$scope.mensagemErro = "";
				$scope.mensagemSucesso = response.data.mensagem;
				$rootScope.data.listaServicosContratados = response.data.listaServicosContratados;
				$location.path('/contratarServico');
			} else {
				$scope.mensagemErro = response.data.mensagem;
				$scope.mensagemSucesso = "";
				$location.path('/contratarServico');
			}
		}, function(response) {

		});
	}
	
	$scope.resetContratarSevicoForm = function(){
		$scope.data_inicio = "";
		$scope.data_fim = "";
		$scope.hora_inicio = $scope.horas[0].id;
		$scope.minuto_inicio = $scope.minutos[0].id;
		$scope.hora_fim = $scope.horas[0].id;
		$scope.minuto_fim = $scope.minutos[0].id;
	}
});