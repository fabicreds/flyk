flyk.controller("profilePageCtrl", function ($scope, $rootScope, $location, $http, fileReader) {

    $scope.showProfilePageEdit = function() {
    	$location.path('/profilePageEdit');
    }
    
//    if($rootScope.data.tipoCadastro == "2")
//    	
//    {
//    	$rootScope.data.tipoCadastro = true;
//    }
//    else
//    {
//    	$rootScope.data.tipoCadastro = false;
//    }
    
    
   /* if($rootScope.telefone.categoria == "FIXO")
    	
    {
    	$rootScope.telefone.categoria = 1;
    }
    else if($rootScope.telefone.categoria == "COMERCIAL")
    {
    	$rootScope.telefone.categoria = 2;
    }
    else
    {
    	$rootScope.telefone.categoria = 3;
    }
    */

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
    
	/*if($rootScope.telefone.operadora == "Claro")
	    	
	    {
	    	$rootScope.telefone.operadora = 1;
	    }
	    else if($rootScope.telefone.operadora == "Vivo")
	    {
	    	$rootScope.telefone.operadora = 2;
	    }
	    else if($rootScope.telefone.operadora == "Tim")
	    {
	    	$rootScope.telefone.operadora = 3;
	    }
	    else if($rootScope.telefone.operadora == "Oi")
	    {
	    	$rootScope.telefone.operadora = 4;
	    }
	    else if($rootScope.telefone.operadora == "Nextel")
	    {
	    	$rootScope.telefone.operadora = 5;
	    }
	    else if($rootScope.telefone.operadora == "Outros")
	    {
	    	$rootScope.telefone.operadora = 6;
	    }
    */
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
	
    
    $scope.pesquisaCep = function(userCEP)
    {
        console.log(userCEP);
        $http.get('http://api.postmon.com.br/v1/cep/' + userCEP).success(function (local) {
            $scope.data.endereco = local;
            console.log(local);
        });
    };
    
    console.log(fileReader)
    $scope.getFile = function () {
        $scope.progress = 0;
        fileReader.readAsDataUrl($scope.file, $scope)
                      .then(function(result) {
                          $scope.imageSrc = result;
                      });
    };
 
    $scope.$on("fileProgress", function(e, progress) {
        $scope.progress = progress.loaded / progress.total;
    });
    
    
    $scope.init = function () {
    	$scope.showProfilePage();
    }
    
    $scope.showProfilePage = function() {
		if ($rootScope.data == null || ($rootScope.data != null && $rootScope.data.nome == null)) {
			$http({
				url : 'profilePage',
				method : "POST",
				data : {
					'usuario' : localStorage.getItem("usuarioLogado"),
					'idUsuario' : localStorage.getItem("idUsuarioLogado"),
					'tipoUsuario' : localStorage.getItem("tipoUsuarioLogado")
				}
			}).then(function(response) {
				if (response.data.retorno != "erro") {
					$rootScope.usuarioLogado = response.data.usuario;
					$rootScope.tipoUsuarioLogado = response.data.tipoCadastro;
					$rootScope.idUsuarioLogado = response.data.cliente.id;
					$rootScope.data = response.data.cliente;
					$location.path('/profilePage');
				} else {
					$location.path('/profilePage');
				}
			}, function(response) {

			});
		} else {
			$location.path('/profilePage');
		}
	};
});