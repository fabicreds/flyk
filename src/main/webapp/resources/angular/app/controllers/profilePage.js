flyk.controller("profilePageCtrl", function ($scope, $rootScope, $location, $http, fileReader, $locale) {

	
    $scope.showProfilePageEdit = function() {
    	$location.path('/profilePageEdit');
    }
    
    $scope.init = function () {
    	$scope.showProfilePage();
    	$rootScope.data = angular.fromJson(localStorage.getItem("dadosCliente"));
    	console.log($rootScope.data);
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
					localStorage.setItem("dadosCliente", JSON.stringify($rootScope.data));
					
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
	
	 $rootScope.$on("CallProfilePageMethod", function(){
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
					//$rootScope.data = response.data.cliente;
					localStorage.setItem("dadosCliente", JSON.stringify(response.data.cliente));
					console.log(JSON.stringify(response.data.cliente));
					console.log(JSON.stringify(response.data.cliente.fotoPerfil));
				} else {
					$location.path('/profilePage');
					
				}
			}, function(response) {

			});
      });
	 

	
	 
		  $scope.options = {
		    customClass: getDayClass,
		    minDate: new Date(),
		    showWeeks: false
		  };



		  $scope.setDate = function(year, month, day) {
		    $scope.dt = new Date(year, month, day);
		  };

		  var tomorrow = new Date();
		  tomorrow.setDate(tomorrow.getDate() + 1);
		  var afterTomorrow = new Date(tomorrow);
		  afterTomorrow.setDate(tomorrow.getDate() + 1);
		  var dateTest = new Date(tomorrow);
		  dateTest.setDate(tomorrow.getDate() + 5);
		  
		  
		  
		
		  $scope.events = [];

		  
		 $scope.datasCalendario = function()
		  {
			 
			$scope.datas = [];
			  
			  /*
			   * Obtem todas as datas da listaContratosServicosPrestados e insere em scope.datas
			   */
			  
			  angular.forEach($rootScope.data.listaContratosServicosPrestados, function(value, key) {
			    	
			        $scope.datas.push(value);
			   });
			    
			   var i=0;
			   $scope.eventsY= [];
				
			   
			    for( i=0;i<$scope.datas.length;i++)
			    {				  

				    var dataIni = $scope.datas[i].dataInicio.substring(0, 10);
	
				    $scope.dataInicio =dataIni.replace(/[^0-9]+/g, "-");				    				    
	
				    /*
					 * Formatando data para mm-dd-yyyy
					 */
				    $scope.dataParametro = $scope.dataInicio.substring(3, 6) + $scope.dataInicio.substring(0, 3).replace(/^0+/, '') + $scope.dataInicio.substring(6, 10);
	
	                console.log($scope.dataParametro);
	
	
	
				    var dataFormatada= new Date($scope.dataParametro);
				    
				    $scope.eventsY = [ {			
				    	date : dataFormatada,
				    	status : 'full'
				    } ];
				    		
				    		
				    		$scope.events = $scope.events.concat($scope.eventsY);
				  
			    }
			    
				

			    console.log( $scope.events);
			    

		 
		  }

		  function getDayClass(data) {
		    var date = data.date,
		      mode = data.mode;
		    if (mode === 'day') {
		      var dayToCheck = new Date(date).setHours(0,0,0,0);

		      for (var i = 0; i < $scope.events.length; i++) {
		        var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

		        if (dayToCheck === currentDay) {
		          return $scope.events[i].status;
		        }
		      }
		    }

		    return '';
		  }
		  
		 
});