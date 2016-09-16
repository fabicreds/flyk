flyk.controller("profilePageCtrl", function ($scope, $rootScope, $location, $http, fileReader) {

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
    
	$scope.tipoprivacidade = [ 
       {
		id : 1,
		label : 'Publico'
	   }, {
		id : 2,
		label : 'Apenas amigos',
	   }, {
		id : 3,
		label : 'Privado',
	
	   }];
	
    
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
    
    
	
	
});