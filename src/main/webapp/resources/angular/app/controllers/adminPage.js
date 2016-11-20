flyk.controller("adminPageCtrl", function($rootScope, $scope, $location, $http, $uibModal) {


	$scope.initAdmin = function () {
		$rootScope.usuarioLogado = localStorage.getItem("usuarioLogado");
		$rootScope.tipoUsuarioLogado = localStorage.getItem("tipoUsuarioLogado");
		$rootScope.idUsuarioLogado = localStorage.getItem("idUsuarioLogado");
		$scope.buscarTodasCategoria();
		$scope.buscarTodasPromocoes()
		$scope.buscarTodosAdministradores();
    }
	
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
				$rootScope.messageErroInativar = "Nenhum usuario encontrado!";
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
				$scope.messageSucesso = response.data.mensagem; // configurar mensagem se o serviço já estiver cadastrado
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
        	
              $rootScope.data = response.data.listaCategoria;   	 
  
              $scope.dadosCateg=JSON.stringify(response.data.listaCategoria);  			 
  			
//  			console.log($scope.dadosCateg);
  			
  				$scope.categorias = [];
  				angular.forEach(response.data, function(item, key) {
  							
  					$scope.categorias.push(item.nome);
  			  
  				});
  				
  			  var obj = []
  			  	//$scope.choices = [{option: 'Office', number: '9090909090'}, {option: 'Mobile', number: '9090909090'}];
  			  	// $scope.choices=[{"id":1,"nome":"Manicure"},{"id":2,"nome":"Fotografia"}];
  			  $scope.listaCategorias= response.data.listaCategoria;
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
		$rootScope.usuarioLogado = localStorage.getItem("usuarioLogado");
		$rootScope.tipoUsuarioLogado = localStorage.getItem("tipoUsuarioLogado");
		$rootScope.idUsuarioLogado = localStorage.getItem("idUsuarioLogado");
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
	
	$scope.adminPage = function(){
		$location.path('/adminPage');
	}
	
	$scope.adminPageCategorias = function(){
		$scope.buscarTodasCategoria();
		$location.path('/adminPageCategorias');
	}
	
	$scope.adminPagePromocoes = function(){
		$scope.buscarTodasPromocoes();
		$location.path('/adminPagePromocoes');
	}
	
	$scope.buscarTodasCategoria = function(){
		$http({
			url : 'adminPageCategorias', // nome do request mapping da classe java criada para cadastro de serviços
			method : "POST",
			data : {
			}
		}).then(function(response) {
			if(response.data.retorno != "erro"){
				$rootScope.listaTotalCategorias = response.data.listaCategoria;
			}
		}, function(response) {
		});
	}
	
	$scope.buscarTodasPromocoes = function(){
		$http({
			url : 'adminPagePromocoes', // nome do request mapping da classe java criada para cadastro de serviços
			method : "POST",
			data : {
			}
		}).then(function(response) {
			if(response.data.retorno != "erro"){
				$rootScope.listaPromocoes = response.data.listaPromocao;
			}
		}, function(response) {
		});
	}
	
	$scope.desativarCategoria = function(id){
		$http({
			url : 'atualizarCategoria', // nome do request mapping da classe java criada para cadastro de serviços
			method : "POST",
			data : {
				idCategoria: id,
				acao: 1
			}
		}).then(function(response) {
			if(response.data.retorno != "erro"){
				$rootScope.listaTotalCategorias = response.data.listaCategoria;
			}
		}, function(response) {
		});
	}
	
	$scope.ativarCategoria = function(id){
		$http({
			url : 'atualizarCategoria', // nome do request mapping da classe java criada para cadastro de serviços
			method : "POST",
			data : {
				idCategoria: id,
				acao: 2
			}
		}).then(function(response) {
			if(response.data.retorno != "erro"){
				$rootScope.listaTotalCategorias = response.data.listaCategoria;
			}
		}, function(response) {
		});
	}
	$scope.adminPageAdministradores = function(){
		$scope.buscarTodosAdministradores();
		$location.path('/adminPageAdministradores');
	}
	$scope.buscarTodosAdministradores = function(){
		$http({
			url : 'adminPageAdministradores', // nome do request mapping da classe java criada para cadastro de serviços
			method : "POST",
			data : {
			}
		}).then(function(response) {
			if(response.data.retorno != "erro"){
				$rootScope.listaAdministradores = response.data.listaAdministradores;
			}
		}, function(response) {
		});
	}
	
	
});