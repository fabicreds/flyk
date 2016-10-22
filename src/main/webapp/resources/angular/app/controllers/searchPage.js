flyk.controller("searchPageCtrl", function($scope, $rootScope, $location,
		$http, fileReader) {
	

	
	$scope.tipoBusca = [ {
		id : 1,
		label : 'PESSOAS'
	}, {
		id : 2,
		label : 'SERVICOS',
	}];
	

	
	$scope.init = function () {
		$rootScope.usuarioLogado = localStorage.getItem("usuarioLogado");
		$rootScope.tipoUsuarioLogado = localStorage.getItem("tipoUsuarioLogado");
		$rootScope.idUsuarioLogado = localStorage.getItem("idUsuarioLogado");
		$rootScope.data = JSON.parse(localStorage.getItem("dadosCliente"));
    }

	$scope.showSearchPage = function() {
		$location.path('/searchPage');
	};
	
	$scope.find = function () {
		
		$http({

            url : 'efetuarBusca',
            method : "POST",
         
            data : {
                'tipoBusca' : $scope.campotipoBusca,
                'valorBusca' : $scope.valorBusca
                

                
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
            
        	
        	console.log(response.data);
      
        }, function(response) {
           
        
            
            //$scope.message = response;
        });
		
		
		
		
	}

});













	/**
	Copyright 2016 Google Inc. All Rights Reserved. 
	Use of this source code is governed by an MIT-style license that can be foundin the LICENSE file at http://material.angularjs.org/HEAD/license.
	**/