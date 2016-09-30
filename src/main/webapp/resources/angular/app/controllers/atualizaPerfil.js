flyk.controller("atualizaPerfilCtrl", function ($scope, $rootScope, $location, $http, fileReader, $localStorage,
		$sessionStorage, $window) {
	
	 $scope.categoriasServico = [];
	 $scope.servicosSelecionados = [];
	 $scope.servicos = [];
	 $scope.cliente = {};
	 $scope.cliente.id = $rootScope.data.id;
		
		 $scope.categorias = [
		                      {
		                  	  id: 1,
		                  	  label: 'FIXO'
		                  	}, 
		                  	{
		                  	  id: 2,
		                  	  label: 'COMERCIAL',
		                  	},
		                  	{
		                    	  id: 3,
		                    	  label: 'MOVEL',
		                    	}
		                  ];
		                  
		                  $scope.operadoras = [
		                      {
		                  	 id: 1,
		                	  	 label: 'CLARO'
		                		}, 
		                		{
		                		 id: 2,
		                	     label: 'VIVO',
		                		}, 
		                		{
		                		 id: 3,
		                		 label: 'TIM',
		                		}, 
		                		{
		                	     id: 4,
		                	     label: 'OI',
		                		}, 
		                		{
		                	  	 id: 5,
		                	  	 label: 'NEXTEL',
		                	  	}, 
		                		{
		                	     id: 6,
		                	     label: 'OUTROS',
		                		}
		                	];
		                  
		              	$scope.tipoprivacidade = [ 
		                     {
		              		id : 1,
		              		label : 'Publico'
		              	   }, {
		              		id : 2,
		              		label : 'Apenas amigos',
		              	   }, {
		              		id : 3,
		              		label : 'Privado',
		              	
		              	   }];
		              	$scope.atualizaPerfil = function() {
		              		$scope.cliente.listaTelefone = {};
		              		$scope.cliente.listaTelefone=$rootScope.data.listaTelefone;
		              		
		            		$http({
		            			url : 'atualizarPerfil',
		            			method : "POST",
		            			data : {
		            				'cliente' : $scope.cliente
		            				
		            			}

		            		})
		            		.then(function(response) {
		            			$rootScope.usuarioLogado = response.data.usuario;
		            			$rootScope.tipoUsuarioLogado = response.data.tipoCadastro;
		            			if (response.data.tipoCadastro == "1"|| response.data.tipoCadastro == "2") {
		            			$rootScope.data = response.data.cliente;
		            			$scope.listaTelefone = response.data.cliente.listaTelefone;
		            			localStorage.setItem("dadosCliente", JSON.stringify($rootScope.data));
		            			$location.path('/profilePage');
		            		} else {
		            			// $location.path('/adminPage');
		            			
		            			$rootScope.data = response.data.cliente;
		            			localStorage.setItem("dadosCliente", JSON.stringify($rootScope.data));
		            			console.log(response.data);
		            		}
		            			
		            		},
		            		function(response) {
		            			$rootScope.data = response.data.cliente;
		            			console.log(response.data.cliente);

		            		
		            			localStorage.setItem("dadosCliente", JSON.stringify($rootScope.data));		            		});
		                
		            	}
		              	
		              	$scope.carregaCategorias = function() {
		              		$http({
		            			url : 'consultaCategoriaCadastradasCadastro',
		            			method : "POST",
		            			data : {}
		            		}).then(
		            				function(response) {
		            					if (response.data.retorno != "erro") {
		            						$rootScope.data = response.data;
		            						if (response.data.listaCategorias != null) {
		            							angular.forEach(response.data.listaCategorias,
		            									function(item, key) {
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
		            		var servico = {id: 0, label: "Serviço 1"};
		            		$scope.servicosSelecionados.push(servico);
		            	};
		            	

		            	$scope.selecionarNumServicos = function(numServicos) {
		            		
		            		$scope.servicosSelecionados = [];
		            		var i = 0;
		            		
		            		for (i = 0; i <numServicos; i++) {
		            			var servico = {id: i, label: "Serviço " + i};
		            			$scope.servicosSelecionados.push(servico);
		            		}
		            	};


});