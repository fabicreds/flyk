flyk.controller("confirmaPromocaoCtrl", function($rootScope, $scope, $location, $http, $uibModal, $filter) {
	
	
	
	$scope.dados= $rootScope.data;	
	$scope.nomepromo=$scope.dados.nomeprom;
	$scope.descrprom=$scope.dados.descrprom;
	$scope.valorpromo=$scope.dados.valorpromocional;
	$scope.catPromo=$scope.dados.listacategoria;
	console.log($scope.catPromo)
	    $scope.msg = false;

	
	
	$scope.today = function() {
	    $scope.dtfim = new Date();
	    $scope.dtini = new Date();
	  };
	  $scope.today();

	
	
	
	
	$scope.cadProm = function() {
		

   $scope.datainicio =$filter('date')( $scope.dtini, "dd/MM/yyyy");    
   $scope.datafim =$filter('date')( $scope.dtfim, "dd/MM/yyyy"); 
  console.log($scope.datainicio)
		$http({

            url : 'confirmaPromocao',
            method : "POST",
            data : {
                'nomeprom' : $scope.nomepromo,
                'descrprom' : $scope.descrprom,                
                'valorpromocional' : $scope.valorpromo   ,
                'dataini' : 	$scope.datainicio,
                'datafim' : 	$scope.datafim

                
            }
        }).then(function(response) {           
        	
              $rootScope.data = response.data;  		
              $scope.mensagem=response.data;
              $scope.msg=true;

  			//$location.path('/confirmaPromocao');
      
        }, function(response) {
           
            console.log();	
            $scope.message = response;
        });
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	  $scope.clear = function() {
	    $scope.dtini = null;
	    $scope.dtfim = null;

	  };

	  $scope.inlineOptions = {
	    customClass: getDayClass,
	    minDate: new Date(),
	    showWeeks: true
	  };

	  $scope.dateOptions = {
	    dateDisabled: disabled,
	    formatYear: 'yy',
	    maxDate: new Date(2020, 5, 22),
	    minDate: new Date(),
	   
	    startingDay: 1
	  };

	  // Disable weekend selection
	  function disabled(data) {
	    var date = data.date,
	      mode = data.mode;
	    return mode === 'day' && (date.getDay() === 9 || date.getDay() === 50);
	  }

	  $scope.toggleMin = function() {
	    $scope.inlineOptions.minDate = $scope.inlineOptions.minDate ? null : new Date();
	    $scope.dateOptions.minDate = $scope.inlineOptions.minDate;
	  };

	  $scope.toggleMin();

	  $scope.open1 = function() {
	    $scope.popup1.opened = true;
	    
	  };

	  $scope.open2 = function() {
	    $scope.popup2.opened = true;
	   
	  };

	  $scope.setDate = function(year, month, day) {
	    $scope.dtini = new Date(year, month, day)
	    $scope.dtfim = new Date(year, month, day);

	   
	    
	  };

	  $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
	  $scope.format ='dd-MM-yyyy';
	  $scope.altInputFormats = ['dd-MMMM-yyyy'];

	  $scope.popup1 = {
	    opened: false
	  };

	  $scope.popup2 = {
	    opened: false
	  };

	  var tomorrow = new Date();
	  tomorrow.setDate(tomorrow.getDate() + 1);
	  var afterTomorrow = new Date();
	  afterTomorrow.setDate(tomorrow.getDate() + 1);
	  $scope.events = [
	    {
	      date: tomorrow,
	      status: 'full'
	    },
	    {
	      date: afterTomorrow,
	      status: 'partially'
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
	
});