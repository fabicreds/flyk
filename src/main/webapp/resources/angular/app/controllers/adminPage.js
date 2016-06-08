flyk.controller("adminPageCtrl", function($rootScope, $scope, $location, $http, $uibModal) {


	$scope.pesquisar = function() {
	
		
		$http({
			url : 'buscarUsuarios',
			method : "POST",
			data : {
				'usuarioBusca' : $scope.usuarioBusca,
				'checkAdministrador' : $scope.checkAdministrador
			}
		}).then(function(response) {
			if(response.data==""){
				$rootScope.messageErroInativar = "Nenhum usuário encontrado!";
			}else{
				$rootScope.messageErroInativar = "";
				$rootScope.data = response.data;
				$location.path('/userPageInfos');
			}
			
		});
	}

	$scope.sendPostAdm = function() {
	
		$http({
			url : 'cadastroAdministrador',
			method : "POST",
			data : {
				'nome' : $scope.admname,
				'usuario' : $scope.admusername,
				'senha' : $scope.admpassword
			}
		}).then(function(response) {
			console.log(response.data);
			if(response.data.retorno == "erro"){
				$scope.messageErroCadastro = response.data.mensagem;
				$scope.messageSucessoCadastro = "";
			}else{
				$scope.messageErroCadastro = "";
				$scope.messageSucessoCadastro = response.data.mensagem;
			}
			$scope.admname = "";
			$scope.admusername = "";
			$scope.admpassword = "";
			
		}, function(response) {
			// fail case
			console.log(response);
			$scope.message = response;
		});

		
	};
	
   $scope.sendPostServices = function() {
		console.log($scope.servname);
		$http({
			url : 'cadastroServicos', // nome do request mapping da classe java criada para cadastro de serviços
			method : "POST",
			data : {
				'nome' : $scope.servname,
				'descricao' : $scope.servdescription
			}
		}).then(function(response) {
			console.log(response.data);
			if(response.data.retorno == "erro"){
				$scope.messageErro = response.data.mensagem; // configurar mensagem se o serviço já estiver cadastrado
			}else{
				$scope.messageSucesso = response.data.mensagem; // configurar mensagem para exibir que o serviço foi cadastrado com sucesso
			}
			//$scope.servname = "";
			//$scope.servdescription = "";
			
		}, function(response) {
			// fail case
			console.log(response);
			$scope.message = response;
		});

		
	};
	

	$scope.resetadmform = function()
	{
		 $scope.admname="";
		 $scope.admusername="";
		 $scope.admpassword="";
	}
	
	$scope.resetservform = function()
	{
		 $scope.servname="";
		 $scope.servdescription="";
	}
	
	$scope.resetPromForm = function()
	{
		 $scope.promnome="";
		 $scope.promdescricao="";
	}

	$scope.init= function() {
		$http({

            url : 'getValueCatList',
            method : "GET",
          
        }).then(function(response) {           
        	
              $rootScope.data = response.data;   	 
  
              $scope.dadosCateg=JSON.stringify(response.data);  			 
  			
  			console.log($scope.dadosCateg);
  			
  				$scope.categorias = [];
  				angular.forEach(response.data, function(item, key) {
  							
  					$scope.categorias.push(item.nome);
  			  
  				});
  				
  			  var obj = []
  			  	//$scope.choices = [{option: 'Office', number: '9090909090'}, {option: 'Mobile', number: '9090909090'}];
  			  	// $scope.choices=[{"id":1,"nome":"Manicure"},{"id":2,"nome":"Fotografia"}];
  			  $scope.listaCategorias= response.data;
  			  	//console.log(response.data);
  			  $scope.addNewChoice = function() {
  			  var newItemNo = $scope.listaCategorias.length+1;
  			    $scope.listaCategorias.push({'id':''+newItemNo});
  			  };
  			    
  			  $scope.removeChoice = function() {
  			    var lastItem = $scope.listaCategorias.length-1;
  			    $scope.listaCategorias.splice(lastItem);
  			  };

        }, function(response) {
           
            console.log();
            $scope.message = response;
        });
		
	}


	
	  
	$scope.sendPostProm = function() {
		console.log($scope.choices);
		
		$http({

            url : 'cadastroPromocao',
            method : "POST",
            data : {
                'nomeprom' : $scope.promnome,
                'descrprom' : $scope.promdescricao               
                
            }
        }).then(function(response) {           
        	
              $rootScope.data = response.data;  		
  			$location.path('/confirmaPromocao');
      
        }, function(response) {
           
            console.log();
            $scope.message = response;
        });
	}
	
	$scope.add = true;
	$scope.edit = false;
	
	$scope.addCategory = function()
	{
		this.add = !this.add;
		this.edit = !this.edit;
	}
	
	$scope.editCategory = function()
	{
		this.edit = !this.edit;
		this.add = !this.add;
	}
	
	
});