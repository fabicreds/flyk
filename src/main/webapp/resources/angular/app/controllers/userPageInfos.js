flyk.controller("userPageInfosCtrl", function ($scope, $rootScope, $location, $http) {
	
	$scope.pesquisar = function()
	{
		   $location.path('/userPageInfos');
	}
	
	$scope.teste = "Luciana";
	
	$scope.inativar = function(){
		$http({
			url : 'inativarUsuario',
			method : "POST",
			data : {
				'usuario' : $scope.user,
				'tipoCadastro' :tipoCadastro,
				'ativo': $scope.ativo
			}
		}).then(function(response) {
			$rootScope.data = response.data;
			$location.path('/adminPage');
			response.data = [];
		}, function(response) {
		});
		
		
	}
	
	
});