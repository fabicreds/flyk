flyk.controller("adminPageCtrl", function ($scope, $location, $http, $uibModal) {
	
	 $scope.confirm = function () {
	     
	        $uibModal.open({
	            templateUrl: "modalConfirm.html",
	            controller: "modalConfirmCtrl"
	        });
	    }
	 
	 $scope.pesquisar = function()
		{
			   $location.path('/userPageInfos');
		}
		
	$scope.sendPostAdm = function()
	{
		 $http({
	            url : 'cadastroAdministrador',
	            method : "POST",
	            data : {
	                'name' : $scope.admname,
	                'user' : $scope.admuser,
	                'password': $scope.admpassword
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