flyk.controller("userPageInfosCtrl", function ($scope, $rootScope, $location, $http) {
	
	$scope.pesquisar = function()
	{
		   $location.path('/userPageInfos');
	}
	
	$scope.teste = "Luciana";
	
});