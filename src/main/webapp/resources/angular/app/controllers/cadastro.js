flyk.controller("cadastroCtrl", function ($scope, $location, $http, $uibModal) {
	
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
	            $scope.user.address = local;
	            console.log(local);
	        });
	    };
	
});