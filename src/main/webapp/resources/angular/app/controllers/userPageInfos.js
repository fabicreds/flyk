flyk.controller("userPageInfosCtrl", function ($scope, $location, $http) {
	
	$scope.pesquisar = function()
	{
		   $location.path('/userPageInfos');
	}
	
	$scope.teste = "Luciana";
	
});