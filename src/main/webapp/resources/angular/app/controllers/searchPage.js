flyk.controller("searchPageCtrl", function($scope, $rootScope, $location,$http, fileReader, $filter) {
	
	
	$scope.tipoBusca = [ {
		id : 1,
		label : 'Clientes'
	}, {
		id : 2,
		label : 'Prestadores',
	}];
	
	$scope.listaCategorias = [];
	$scope.categoriasServico = [];

	$scope.makeLowerCase = function(string){
		   return angular.lowercase(string);
		};
	

	
	
	$scope.init = function () {
		$rootScope.usuarioLogado = localStorage.getItem("usuarioLogado");
		$rootScope.tipoUsuarioLogado = localStorage.getItem("tipoUsuarioLogado");
		$rootScope.idUsuarioLogado = localStorage.getItem("idUsuarioLogado");
		$rootScope.cliente = angular.fromJson(localStorage.getItem("dadosCliente"));
		$scope.cidade = $rootScope.cliente.endereco.cidade;
		console.log($rootScope.cliente);
		$scope.getCidade = function(){
		       $scope.cidade=$rootScope.cliente.endereco.cidade;
		        
	};
		
    }

	$scope.showSearchPage = function() {
		$location.path('/searchPage');
	};
	
	
	$scope.carregaCategorias = function() {
		
	
		
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
									
									if (response.data.listaCategorias != null) {
										angular
												.forEach(
														response.data.listaCategorias,
														function(
																item,
																key) {
															var itemCategoria = {
																//id : item.id,
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
	
	
	  
	
	$scope.find = function () {
		delete $scope.msgErro;
		$scope.listaClientes=[];
		$rootScope.cliente = localStorage.getItem("dadosCliente");			
	
		$scope.servicos= angular.toJson($scope.foo);
		
		
		$http({

            url : 'efetuarBusca',
            method : "POST",
         
            data : {
                'tipoPesquisa' : $scope.campotipoBusca,
                'stringBusca' : $scope.valorBusca,
                'idCateg': $scope.servicos,
                'cidade': $scope.nomeCidade,
                'idUsuarioLogado': $rootScope.idUsuarioLogado
                

                
            },
            headers: {
                'Content-Type': 'application/json'
       }
        }).then(function(response) {          	


			if (response.data.retornoVazio == "Nenhum cliente encontrado.") {
				$scope.msgErro = response.data.retornoVazio;
				console.log($scope.msgErro);

			} else {
				$scope.listaClientes = response.data.listaClientes;

			}			
				
				if( typeof $rootScope.cliente.listaRecomendacoesRecebidas === 'undefined' || $rootScope.cliente.listaRecomendacoesRecebidas === null ){
				    // Do stuff
				}
				else {
					console.log("usuario recomendado");
				}
			
            
      
        }, function(response) {
           
        
            
           
        });
		
		
		
		
	}
	
	$scope.isUndefined = function (thing) {
	    return (typeof thing === "undefined");
	}
	 $scope.source1 = ["this", "is", "array", "of", "text"]; 
     $scope.source2 = [{id:1, value:'One'}, {id:2, value:'Two'}, {id:3, value:'Three'}, {id:4, value:'Four'}];
     $scope.callback = function(selected) {
       $scope.foo_ids = $scope.foo.map(function(el) {return el;}).join(',');
       $scope.selected = selected;
     };
     $scope.servicos = [];
     $scope.foo_ids = '1,2';
     $scope.source = $scope.categoriasServico;
   //$scope.source =  $scope.source1;

     $scope.disabled = false;
     $scope.customListFormatter = function(data) {
       return ''+data.nome+'';
     };
     $scope.prefillFunc = function(url) {
       $http.get(url).success(function(data) {
         $scope.foo = data;
       });
     }

     var printHTML = function(selector) {
         document.write('<pre>'+
           document.querySelector(selector).outerHTML.replace(/</g,'\n&lt;').replace(/[ ]([a-z])/g,"\n  $1") +
         '</pre>')
       };
       
       $scope.listFormatter = function(data) {
           return ''+data.nome+''
         };
});