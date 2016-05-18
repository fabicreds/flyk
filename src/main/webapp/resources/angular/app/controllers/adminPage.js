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
		
	
});