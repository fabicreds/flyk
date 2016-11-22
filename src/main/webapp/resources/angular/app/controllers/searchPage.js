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
	
	
	$scope.pesquisarCat = function(pesquisa){

		// Se a pesquisa for vazia
		if (pesquisa == ""){

			// Retira o autocomplete
			$scope.completing = false;

		}else{
			$scope.completing = true;	
			$scope.dicas=[];
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
															$scope.dicas
																	.push(itemCategoria);
														});
									}
								}
							}, function(response) {
								// fail case
								console.log(response);
								$scope.message = response;
							});


		}
	};
	
	$scope.dirty = {};

	  var states = ['Alabama', 'Alaska', 'California', /* ... */ ];

	  function suggest_state(term) {
	    var q = term.toLowerCase().trim();
	    var results = [];

	    // Find first 10 states that start with `term`.
	    for (var i = 0; i < states.length && results.length < 10; i++) {
	      var state = states[i];
	      if (state.toLowerCase().indexOf(q) === 0)
	        results.push({ label: state, value: state });
	    }

	    return results;
	  }

	  $scope.autocomplete_options = {
	    suggest: suggest_state
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
				console.log($scope.listaClientes);
				
				var i=0;
				angular
				.forEach($scope.listaClientes,function(item,key) {
							
					if($scope.listaClientes.cliente + i.listaRecomendacoesRecebidas != 'undefined' || $scope.listaClientes.cliente0.listaRecomendacoesRecebidas === null)
					{
						console.log("usuario recomendando");
						//str = $parse("cliente" + i);
						var str="cliente";
						var a=i;
						
						$scope.indice=str + a;
						console.log($scope.indice);
						//console.log($scope.listaClientes.$scope.indice);
						
					
						
						console.log(i);
					}
						
						i++;
						});
				
				
				
				console.log($scope.listaClientes.cliente0.listaRecomendacoesRecebidas);

			}
			
				
				
				if( typeof $rootScope.cliente.listaRecomendacoesRecebidas === 'undefined' || $rootScope.cliente.listaRecomendacoesRecebidas === null ){
				    // Do stuff
				}
				else {
					console.log("usuario recomendado");
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
	
	$scope.isUndefined = function (thing) {
	    return (typeof thing === "undefined");
	}
	

});