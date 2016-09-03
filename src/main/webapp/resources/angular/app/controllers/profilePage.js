flyk.controller("profilePageCtrl", function ($scope, $rootScope, $location, $http) {

    $scope.showProfilePageEdit = function() {
    	$location.path('/profilePageEdit');
    }

    
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
	
    
    $scope.pesquisaCep = function(userCEP)
    {
        console.log(userCEP);
        $http.get('http://api.postmon.com.br/v1/cep/' + userCEP).success(function (local) {
            $scope.data.endereco = local;
            console.log(local);
        });
    };
});