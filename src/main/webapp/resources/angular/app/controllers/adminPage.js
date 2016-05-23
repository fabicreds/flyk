flyk.controller("adminPageCtrl", function ($scope, $location, $http, $uibModal) {
	
	 $scope.confirm = function () {
	     
	        $uibModal.open({
	            templateUrl: "modalConfirm.html",
	            controller: "modalConfirmCtrl"
	        });
	    }
	 
	 $scope.pesquisar = function()
		{
			   $http({
		            url : 'buscarUsuarios',
		            method : "POST",
		            data : {
		                'usuarioBusca' : $scope.usuarioBusca,
		                'checkAdministrador' : $scope.checkAdministrador
		                }
		        }).then(function(response) {
		            console.log(response.data);
		            $scope.message = response.data;
		        }, function(response) {
		            //fail case
		            console.log(response);
		            $scope.message = response;
		        });
		}
		
	$scope.sendPostAdm = function()
	{
		
		 $http({
	            url : 'cadastroAdministrador',
	            method : "POST",
	            data : {
	                'nome' : $scope.admname,
	                'usuario' : $scope.admusername,
	                'senha': $scope.admpassword
	            }
	        }).then(function(response) {
	            console.log(response.data);
	            $scope.message = response.data;
	        }, function(response) {
	            //fail case
	            console.log(response);
	            $scope.message = response;
	        });
		
	};
});