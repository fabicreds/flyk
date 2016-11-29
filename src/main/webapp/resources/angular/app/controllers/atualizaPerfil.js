flyk.controller("atualizaPerfilCtrl",function($scope, $rootScope, $location, $http, fileReader,	$localStorage, $sessionStorage, $window, $uibModal,	$http) {

	/*
	 * Definicao das variaveis
	 */
	$scope.cliente = {};
	$scope.cliente.id = $rootScope.data.id;
	$scope.categoriasServico = [];
	/*$rootScope.servicos = $rootScope.data.listaCategoriaServicosPrestados;
	$scope.numServicos = Object	.keys($rootScope.data.listaCategoriaServicosPrestados).length;
	$scope.servicos = [];*/

	console.log($rootScope.data.listaTelefone);
	

	
	/*
	 * Function init - Executado ao carregar a pagina
	 */
	$scope.init = function() {
		$scope.cliente.fotoPerfil= $rootScope.data.fotoPerfil;

		
		$scope.getIndexFromValue = function(value) {
			var x=0;
			console.log(value);
			for (x=0; x < $scope.operadoras.length; x++)
				{
				
				 if($scope.operadoras[x].id == value)
					{
					 console.log(x);
					 return x;
					}
		            
				}
			
			
			
		   }
		
		$scope.getIndexFromValuePrivacidade = function(value) {
			var x=0;
			console.log(value);
			for (x=0; x < $scope.tipoprivacidade.length; x++)
				{
				
				 if($scope.tipoprivacidade[x].label == value)
					{
					 console.log(x);
					 return x;
					}
		            
				}
			
			
			
		   }

	
				$rootScope.usuarioLogado = localStorage	.getItem("usuarioLogado");
				$rootScope.tipoUsuarioLogado = localStorage	.getItem("tipoUsuarioLogado");
				$rootScope.idUsuarioLogado = localStorage.getItem("idUsuarioLogado");
				
				$rootScope.cliente = angular.fromJson(localStorage.getItem("dadosCliente"));
				
				
				
				/* 
				 * Definicao do tipo de usuario logado, consultando localStorage
				 */
				if ($rootScope.tipoUsuarioLogado != 1) {
					
					$scope.prestador = {};
					
					$scope.numServicos = Object	.keys($rootScope.cliente.listaCategoriaServicosPrestados).length;


					$scope.categoriasServico =$rootScope.cliente.listaCategoriaServicosPrestados ;
					$scope.servicos=[];
					$scope.categoriasServico=[];
					
					
					angular
					.forEach(
							$rootScope.cliente.listaCategoriaServicosPrestados,
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
				if ($rootScope.tipoUsuarioLogado == 2) {
						
						$scope.prestador.flag = true;
						$scope.prestador.type = "free";
				}
				if ($rootScope.tipoUsuarioLogado == 3) {
					
						$scope.prestador.type = "premium";
						$scope.prestador.flag = true;
				
					}
				}			
				
				
				var i = 0;
				
				$scope.selecionarNumServicos();
				angular
						.forEach(
								$rootScope.data.listaCategoriaServicosPrestados,
								function(item, key) {
									$scope.servicos[i] = item.id;
									i++
								})
				
				
	
		
	
	}

		$scope.showPagModal = function() {
		
			$scope.modal = $uibModal.open({
				templateUrl : "pagamento.html",
		controller : 'ModalInstanceCtrl'
			});
		}
		
		$scope.numeroServicos = [ {
			id : 0,
			label : '0'
		}, {
			id : 1,
			label : '1',
		}, {
			id : 2,
			label : '2',
		}, {
			id : 3,
			label : '3',
		}, {
			id : 4,
			label : '4',
		}, {
			id : 5,
			label : '5',
		}, {
			id : 6,
			label : '6',
		} ];
		
		$scope.categorias = [ {
			id : 1,
			label : 'FIXO'
		}, {
			id : 2,
			label : 'COMERCIAL',
		}, {
			id : 3,
			label : 'MOVEL',
		} ];
		
		$scope.operadoras = [ {
			id : 1,
			label : 'CLARO'
		}, {
			id : 2,
			label : 'VIVO',
		}, {
			id : 3,
			label : 'TIM',
		}, {
			id : 4,
			label : 'OI',
		}, {
			id : 5,
			label : 'NEXTEL',
		}, {
			id : 6,
			label : 'OUTROS',
		},
		];
		
		$scope.tipoprivacidade = [ {
			id : 1,
			label : 'Publico'
		}, {
			id : 2,
			label : 'Apenas amigos',
		}, {
			id : 3,
			label : 'Privado',
		
		} ];
		
		/* 
		 * Function atualizaPerfil, envia objeto cliente e retorna objeto cliente atualizado
		 */
		$scope.atualizaPerfil = function() {
			
			delete $scope.mensagemErro;
			
							
			$scope.cliente.listaTelefone = {};
			$scope.cliente.listaTelefone = $rootScope.data.listaTelefone;
			$scope.cliente.nome = $rootScope.data.nome;
			$scope.cliente.servicos = $scope.servicos;
			$scope.cliente.id = $rootScope.idUsuarioLogado;
			
					
			if  (angular.isDefined($scope.prestador))
				
			{
			
			if (angular.isDefined($scope.prestador.flag) && $scope.prestador.flag) {
				$scope.data.tipoCadastro = 2;
				if ($scope.prestador.type == "premium")
					$scope.data.tipoCadastro = 3;
			
				}
			}
			else
			$scope.data.tipoCadastro = 1;
			
							
			$scope.cliente.tipoCadastro = $scope.data.tipoCadastro;
			$http({
				url : 'atualizarPerfil',
				method : "POST",
				data : {	'cliente' : $scope.cliente}
			
			})
				.then(function(response) {
					console.log(response.data);			
					$rootScope.usuarioLogado = response.data.usuario;
					$rootScope.tipoUsuarioLogado = response.data.tipoCadastro;
					console.log(response.data);
					
					if(response.data.retorno == "erro")
					{
						$scope.mensagemErro = response.data.retorno.mensagem;
							
					}
					else {
					
						if (response.data.cliente.tipoCadastro == "1"|| response.data.cliente.tipoCadastro == "2") 
						{
							
							$scope.listaTelefone = response.data.cliente.listaTelefone;
							localStorage.setItem("dadosCliente",JSON.stringify(response.data.cliente));
							console.log(response.data);
							$location.path('/profilePage');
						} else {
											
							//$rootScope.data = response.data.cliente;
							localStorage.setItem("dadosCliente",JSON.stringify(response.data.cliente));
							console.log(response.data);
							$location.path('/profilePage');
						}
					}
					
					},
					function(response) {
						//$rootScope.data.cliente = response.data.cliente;
						//console.log(response.data.cliente);
					
						localStorage
								.setItem(
										"dadosCliente",
										JSON
												.stringify(response.data.cliente));
			});

		}
		// Upload imagem de perfil
		console.log(fileReader)
		$scope.getFile = function() {
			$scope.progress = 0;
			fileReader.readAsDataUrl($scope.file, $scope).then(
					function(result) {
						$scope.imageSrc = result;
						$scope.cliente.fotoPerfil = result;
						console.log($scope.cliente.imagem);
					});
		};

		$scope.$on("fileProgress", function(e, progress) {
			$scope.progress = progress.loaded / progress.total;
		});
		
		$scope.isUndefined = function (thing) {
		    return (typeof thing === "undefined");
		}

					$scope.carregaCategorias = function() {
						
						$scope.categoriasServico=[];
						$http({
							url : 'consultaCategoriaCadastradasCadastro',
							method : "POST",
							data : {}
						})
								.then(
										function(response) {
											if (response.data.retorno != "erro") {
												console.log(response.data);
												$rootScope.data.listaCategorias = response.data.listaCategorias.listaCategorias;
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
												console.log("funcao");
											}
										}, function(response) {
											// fail case
											console.log(response);
											$scope.message = response;
										});
					};

$scope.selecionarNumServicos = function() {
	$rootScope.servicosSelecionados = [];
	var i = 0;

	for (i = 0; i < $scope.numServicos; i++) {
		var servico = {
			id : i,
			label : "Serviço " + i
							};
							$rootScope.servicosSelecionados.push(servico);
						}
					}

				});

flyk.controller('ModalInstanceCtrl', function($rootScope, $uibModalInstance,
	$scope) {

$scope.cancel = function() {
	$uibModalInstance.dismiss('cancel');
	$rootScope.prestador.type = false;

};

});