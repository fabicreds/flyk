flyk.controller("profilePageCtrl", function ($scope, $rootScope, $location, $http, fileReader) {

    $scope.showProfilePageEdit = function() {
    	$location.path('/profilePageEdit');
    }
    
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
					$rootScope.data = response.data.cliente;
					localStorage.setItem("dadosCliente", JSON.stringify($rootScope.data));
				} else {
					$location.path('/profilePage');
				}
			}, function(response) {

			});
      });
	 

		  $scope.options = {
		    customClass: getDayClass,
		    minDate: new Date(),
		    showWeeks: true
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
		  
		  
		  
		
		  $scope.events = [
		    {
		      date: tomorrow,
		      status: 'full'
		    },
		    {
		      date: afterTomorrow,
		      status: 'full'
		    },
		    {
			      date: dateTest,
			      status: 'full'
			  }
		  ];

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
		  
		 $scope.datasCalendario = function()
		  {
		  for(var i=0;i<=$rootScope.data.listaContratosServicosPrestados.length;i++)
			  {
			  
			  	$scope.dates = [];
			  	$scope.date = new Date();
			  	$scope.date = $rootScope.data.listaContratosServicosPrestados.dataInicio[i];
			  	$scope.dates[i] = date;
			  	
			  	$scope.eventsTest[i] = [
			 		       		    {
			 		       		      date: $scope.dates[i],
			 		       		      status: 'full'
			 		       		    }
			 		       		  ];
			 		  }
		 
		  }
		  
		 
});