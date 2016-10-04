flyk
		.controller(
				"atualizaPerfilCtrl",
				function($scope, $rootScope, $location, $http, fileReader,
						$localStorage, $sessionStorage, $window) {

					$scope.init = function() {
						$rootScope.usuarioLogado = localStorage
								.getItem("usuarioLogado");
						$rootScope.tipoUsuarioLogado = localStorage
								.getItem("tipoUsuarioLogado");
						$rootScope.idUsuarioLogado = localStorage
								.getItem("idUsuarioLogado");
						$rootScope.data = JSON.parse(localStorage
								.getItem("dadosCliente"));

						$scope.cliente = {};
						$scope.cliente.id = $rootScope.data.id;
						$scope.categoriasServico = [];
						$rootScope.servicosSelecionados = [];
						$scope.servicos = [];
						if ($rootScope.tipoUsuarioLogado != 1) {
							$scope.prestador = {};
							if ($rootScope.tipoUsuarioLogado == 2) {
								$scope.prestador.flag = true;
								$scope.prestador.type = "free";
							}
							if ($rootScope.tipoUsuarioLogado == 3) {
								$scope.prestador.type = "premium";
							}
						}
						$scope.numServicos = $rootScope.data.numCategoriaServicosPrestados;
						var i = 0;
						$scope.selecionarNumServicos();
						angular	.forEach($rootScope.data.listaCategoriaServicosPrestados,	function(item,key) {
							$scope.servicos[i] = item.id;
							i++
						})
//						for(i=0;i<$rootScope.data.numCategoriaServicosPrestados;i++){
//							$scope.servicos[i] = $rootScope.data.listaCategoriaServicosPrestados[i].id;
//						}
						
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
					} ];

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
					$scope.atualizaPerfil = function() {
						
						$scope.cliente.listaTelefone = {};
						$scope.cliente.listaTelefone = $rootScope.data.listaTelefone;
						$scope.cliente.nome = $rootScope.data.nome;
						$scope.cliente.servicos = $scope.servicos;
						$scope.cliente.id=$rootScope.idUsuarioLogado;
						
						$scope.prestador = {};
						
						

						if ($scope.prestador.flag = true)
						{
							$scope.data.tipoCadastro = 2;
								if ($scope.prestador.type = "premium")
									$scope.data.tipoCadastro = 3;
						
						} else
							$scope.data.tipoCadastro = 1;
					
						$scope.cliente.tipoCadastro = $scope.data.tipoCadastro;
						console.log($scope.data.tipoCadastro);
						//console.log($rootScope.data)
						$http({
							url : 'atualizarPerfil',
							method : "POST",
							data : {
								'cliente' : $scope.cliente

							}

						})
								.then(
										function(response) {
											$rootScope.usuarioLogado = response.data.usuario;
											$rootScope.tipoUsuarioLogado = response.data.tipoCadastro;
											if (response.data.tipoCadastro == "1"
													|| response.data.tipoCadastro == "2") {
												$rootScope.data = response.data.cliente;
												$scope.listaTelefone = response.data.cliente.listaTelefone;
												localStorage
														.setItem(
																"dadosCliente",
																JSON
																		.stringify($rootScope.data));
												$location.path('/profilePage');
											} else {
												// $location.path('/adminPage');

												$rootScope.data = response.data.cliente;
												localStorage
														.setItem(
																"dadosCliente",
																JSON
																		.stringify($rootScope.data));
												console.log(response.data);
											}

										},
										function(response) {
											$rootScope.data = response.data.cliente;
											console.log(response.data.cliente);

											localStorage
													.setItem(
															"dadosCliente",
															JSON
																	.stringify($rootScope.data));
										});

					}
					// Upload imagem de perfil
					console.log(fileReader)
					$scope.getFile = function() {
						$scope.progress = 0;
						fileReader.readAsDataUrl($scope.file, $scope).then(
								function(result) {
									$scope.imageSrc = result;
									$scope.cliente.imagem = result;
									console.log($scope.cliente.imagem);
								});
					};

					$scope.$on("fileProgress", function(e, progress) {
						$scope.progress = progress.loaded / progress.total;
					});

					$scope.carregaCategorias = function() {
						$http({
							url : 'consultaCategoriaCadastradasCadastro',
							method : "POST",
							data : {}
						})
								.then(
										function(response) {
											if (response.data.retorno != "erro") {
												$rootScope.data.listaCategorias = response.data.listaCategorias;
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

					$scope.selecionarNumServicos = function() {
						$rootScope.servicosSelecionados = [];
						var i = 0;
						
						for (i = 0; i < $scope.numServicos; i++) {
							var servico = {id: i, label: "Serviço " + i};
							$rootScope.servicosSelecionados.push(servico);
						}
					}

				});