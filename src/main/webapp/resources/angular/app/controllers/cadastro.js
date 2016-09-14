flyk.controller("cadastroCtrl", function ($scope, $location, $http, $uibModal, fileReader) {
	
	
	  
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
	
	   $scope.validatePassword = function (form) {
	        
	        if(form.password != form.confirmPassword) 
	        {
	            alert("Please, enter the same password to continue!");
	        }
	    };
	    
	    $scope.pesquisaCep = function(userCEP)
	    {
	        console.log(userCEP);
	        $http.get('http://api.postmon.com.br/v1/cep/' + userCEP).success(function (local) {
	            $scope.address = local;
	            console.log(local);
	        });
	    };
	    
	    $scope.resetcadastroform = function()
		{
			 $scope.fullName="";
			 $scope.email="";
			 $scope.nickname="";
			 $scope.password="";
			 $scope.confirmPassword="";
			 $scope.CPF="";
			 $scope.dateBirth="";
			 $scope.telephone="";
			 $scope.prestador.type="";
			 $scope.prestador.flag="";
			 $scope.address.cep="";
			 $scope.address.logradouro="";
			 $scope.address.numero="";
			 $scope.address.comp="";
			 $scope.address.bairro="";
			 $scope.address.cidade="";
			 $scope.address.estado="";
			 $scope.address.pais="";
			 $scope.imageProfile = "";
			 
			 
		}

	    $scope.sendPostCadastroCliente = function() {
	    	
			$http({
				url : 'cadastro',
				method : "POST",
				data : {
					'nome' : $scope.fullName,
					'email' : $scope.email,
					'apelido' : $scope.nickname,
					'senha' : $scope.password,
					'cpf' : $scope.CPF,
					'datanascimento':  $scope.dateBirth,
					'telefone1' : $scope.telephone1,
					'telefone2' : $scope.telephone2,
					'prestador' : $scope.prestador,
					'cep' : $scope.cep,
					'logradouro' : $scope.logradouro,
					'numero' : $scope.numero,
					'comp' : $scope.comp,
					'bairro' : $scope.bairro,
					'cidade' : $scope.cidade,
					'estado' : $scope.estado,
					'pais' : $scope.pais,
					'imagem' :$scope.imageSrc
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
				 $scope.fullName="";
				 $scope.email="";
				 $scope.nickname="";
				 $scope.password="";
				 $scope.confirmPassword="";
				 $scope.CPF="";
				 $scope.dateBirth="";
				 $scope.telephone="";
				 $scope.prestador.type="";
				 $scope.prestador.flag="";
				 $scope.address.cep="";
				 $scope.address.logradouro="";
				 $scope.address.numero="";
				 $scope.address.comp="";
				 $scope.address.bairro="";
				 $scope.address.cidade="";
				 $scope.address.estado="";
				 $scope.address.pais="";
				
			}, function(response) {
				// fail case
				console.log(response);
				$scope.message = response;
			});

			
		};
		
		//Upload imagem de perfil
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
		 

});
