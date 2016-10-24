flyk.controller("friendsProfilePageCtrl", function ($scope, $rootScope, $location, $http, fileReader) {

    $scope.sendPostPerfilAmigo = function(id, tipoCadastro, isRecomendacaoDada) {
    	if(id!=$rootScope.idUsuarioLogado){
	    	$http({
				url : 'friendsProfilePage',
				method : "POST",
				data : {
					'idUsuarioLogado': $rootScope.idUsuarioLogado,
					'idAmigo' : id,
					'tipoUsuarioAmigo' : tipoCadastro
				}
			}).then(function(response) {
				if (response.data.retorno != "erro") {
					$rootScope.data.amigo = response.data.amigo;
					$rootScope.data.amigo.statusAmizade = response.data.statusAmizade;
					$rootScope.data.amigo.statusAmizadeDescricao = response.data.statusAmizadeDescricao;
					$rootScope.data.amigo.isRecomendacaoDada = isRecomendacaoDada;
					localStorage.setItem("dadosCliente", JSON.stringify($rootScope.data));
					$location.path('/friendsProfilePage');
				} else {
					$location.path('/friendsProfilePage');
				}
			}, function(response) {
	
			});
	    }else{
	    	$location.path('/profilePage');
	    }
    }
    

    
    $scope.init = function () {
    	$rootScope.usuarioLogado = localStorage.getItem("usuarioLogado");
		$rootScope.tipoUsuarioLogado = localStorage.getItem("tipoUsuarioLogado");
		$rootScope.idUsuarioLogado = localStorage.getItem("idUsuarioLogado");
		$rootScope.data = JSON.parse(localStorage.getItem("dadosCliente"));
    }
    
    $scope.sendPostDesfazerAmizade = function(idAmigo) {
    	
    	$http({
			url : 'desfazerAmizade',
			method : "POST",
			data : {
				'idUsuarioLogado': $rootScope.idUsuarioLogado,
				'idAmigo' : idAmigo,
			}
		}).then(function(response) {
			if (response.data.retorno != "erro") {
				$rootScope.data.listaAmigos = response.data.listaAmigos;
				$rootScope.data.numAmigos = response.data.numAmigos;
				$rootScope.$emit("CallProfilePageMethod", {});
				$location.path('/friendsPage');
			} else {
				$location.path('/friendsPage');
			}
		}, function(response) {

		});
    	
    }
    
	$scope.sendPostSolicitarAmizade = function(idAmigo) {
	    	
	    	$http({
				url : 'solicitarAmizade',
				method : "POST",
				data : {
					'idUsuarioLogado': $rootScope.idUsuarioLogado,
					'idAmigo' : idAmigo,
				}
			}).then(function(response) {
				if (response.data.retorno != "erro") {
					$rootScope.data.listaAmigos = response.data.listaAmigos;
					$rootScope.data.numAmigos = response.data.numAmigos;
					$rootScope.$emit("CallProfilePageMethod", {});
					$location.path('/friendsPage');
				} else {
					$location.path('/friendsPage');
				}
			}, function(response) {
	
			});
	    	
	    }
	
	$scope.sendPostAceitarAmizade = function(idAmigo) {
		
		$http({
			url : 'aceitarAmizade',
			method : "POST",
			data : {
				'idUsuarioLogado': $rootScope.idUsuarioLogado,
				'idAmigo' : idAmigo,
			}
		}).then(function(response) {
			if (response.data.retorno != "erro") {
				$rootScope.data.listaAmigos = response.data.listaAmigos;
				$rootScope.data.numAmigos = response.data.numAmigos;
				$rootScope.$emit("CallProfilePageMethod", {});
				$location.path('/friendsPage');
			} else {
				$location.path('/friendsPage');
			}
		}, function(response) {
	
		});
		
	}
	
	$scope.sendPostRecomendarPrestador = function(idAmigo) {
			
			$http({
				url : 'recomendarPrestador',
				method : "POST",
				data : {
					'idUsuarioLogado': $rootScope.idUsuarioLogado,
					'idAmigo' : idAmigo,
				}
			}).then(function(response) {
				if (response.data.retorno != "erro") {
					$rootScope.data.listaAmigos = response.data.listaAmigos;
					$rootScope.data.numAmigos = response.data.numAmigos;
					$rootScope.$emit("CallProfilePageMethod", {});
					$location.path('/friendsPage');
				} else {
					$location.path('/friendsPage');
				}
			}, function(response) {
		
			});
			
		}
	
	$scope.sendPostDesfazerRecomendacaoPrestador = function(idAmigo) {
		
		$http({
			url : 'desfazerRecomendacaoPrestador',
			method : "POST",
			data : {
				'idUsuarioLogado': $rootScope.idUsuarioLogado,
				'idAmigo' : idAmigo,
			}
		}).then(function(response) {
			if (response.data.retorno != "erro") {
				$rootScope.data.listaAmigos = response.data.listaAmigos;
				$rootScope.data.numAmigos = response.data.numAmigos;
				$rootScope.$emit("CallProfilePageMethod", {});
				$location.path('/friendsPage');
			} else {
				$location.path('/friendsPage');
			}
		}, function(response) {
	
		});
		
	}
	

    
    
});