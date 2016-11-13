flyk.controller("searchPageCtrl", function($scope, $rootScope, $location,$http, fileReader) {
	
	
	$scope.tipoBusca = [ {
		id : 1,
		label : 'Clientes'
	}, {
		id : 2,
		label : 'Prestadores',
	}];
	
	$scope.listaCategorias = [];
	$scope.categoriasServico = [];

	
	$scope.init = function () {
		$rootScope.usuarioLogado = localStorage.getItem("usuarioLogado");
		$rootScope.tipoUsuarioLogado = localStorage.getItem("tipoUsuarioLogado");
		$rootScope.idUsuarioLogado = localStorage.getItem("idUsuarioLogado");
		$rootScope.cliente = angular.fromJson(localStorage.getItem("dadosCliente"));
		
		
		$scope.cidade = $rootScope.cliente.endereco.cidade;
    }

	$scope.showSearchPage = function() {
		$location.path('/searchPage');
	};
	
	
	$scope.carregaCategorias = function() {
		
	//	console.log(localStorage.getItem("dadosCliente"));
		
		$http({
			url : 'consultaCategoriaCadastradasCadastro',
			method : "POST",
			data : {}
		})
				.then(
						function(response) {
							console.log(response.data);
							if (response.data.retorno != "erro") {
								console.log(response.data.listaCategorias);
								//$rootScope.data.listaCategorias = response.data.listaCategorias.listaCategorias;
								if (response.data.listaCategorias != null) {
									angular
											.forEach(
													response.data.listaCategorias,
													function(
															item,
															key) {
														var itemCategoria = {
															id : item.id,
															nome : item.nome
														};
														$scope.categoriasServico
																.push(itemCategoria);
													});
								}
							}
						}, function(response) {
							// fail case
							console.log(response);
							$scope.message = response;
						});
	};
	
	$scope.find = function () {
		$rootScope.cliente = localStorage.getItem("dadosCliente");
		//console.log(	$rootScope.cliente.endereco.cidade );	
		$http({

            url : 'efetuarBusca',
            method : "POST",
         
            data : {
                'tipoPesquisa' : $scope.campotipoBusca,
                'stringBusca' : $scope.valorBusca,
                'idCateg': $scope.servicos,
                'cidade': $scope.cidade,
                'idUsuarioLogado': $rootScope.idUsuarioLogado
                

                
            },
            headers: {
                'Content-Type': 'application/json'
       }
        }).then(function(response) {          	


			if (response.data.retornoVazio == "Nenhum cliente encontrado.") {
				$scope.listaClientes = response.data.retornoVazio;

			} else {
				$scope.listaClientes = response.data.listaClientes;
				
			}
             // $rootScope.data.listaClientes = response.data.listaClientes;
             // $scope.listaClientes = response.data.listaClientes;
             
             // $scope.mensagem=response.data;
              //$scope.msg=true;
              //console.log(JSON.stringify(response.data));
  			//$location.path('/confirmaPromocao');
            
        	
        	console.log($scope.listaClientes);
      
        }, function(response) {
           
        
            
            //$scope.message = response;
        });
		
		
		
		
	}

});













	/**
	Copyright 2016 Google Inc. All Rights Reserved. 
	Use of this source code is governed by an MIT-style license that can be foundin the LICENSE file at http://material.angularjs.org/HEAD/license.
	**/