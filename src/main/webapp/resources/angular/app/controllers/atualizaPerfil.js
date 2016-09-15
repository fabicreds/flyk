flyk.controller("atualizaPerfilCtrl", function ($scope, $rootScope, $location, $http, fileReader, $localStorage,
		$sessionStorage, $window) {
	
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
		              		
		            		$http({
		            			url : 'atualizarPerfil',
		            			method : "POST",
		            			data : {
		            				'cliente' : $scope.cliente,
		            				'listaTelefone' : $scope.data.listaTelefone
		            			}

		            		})
		            		.then(function(response) {
		            			$rootScope.usuarioLogado = response.data.usuario;
		            			$rootScope.tipoUsuarioLogado = response.data.tipoCadastro;
		            			if (response.data.tipoCadastro == "1"|| response.data.tipoCadastro == "2") {
		            			$rootScope.data = response.data.cliente;
		            			$scope.listaTelefone = response.data.cliente.listaTelefone;
		            			console.log(response.data.cliente);
		            			$location.path('/profilePage');
		            		} else {
		            			// $location.path('/adminPage');
		            			$localStorage.LocalMessage = response.data.cliente;
		            			$rootScope.data = response.data.cliente;
		            			console.log(response.data);
		            		}
		            		$localStorage.LocalMessage = response.data.cliente;
		            		},
		            		function(response) {
		            			$rootScope.data = response.data.cliente;
		            			console.log(response.data.cliente);

		            			// $scope.message = response;
		            			$localStorage.LocalMessage = response.data.cliente;
		            		});
		                
		            	}


});