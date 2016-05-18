flyk.controller("modalConfirmCtrl", function($scope, $window, $uibModalInstance) {

    $scope.submit = function () {

        //$scope.employee = angular.copy($scope.employee);
        $uibModalInstance.close($scope.employee);

    };

    $scope.cancel = function () {

        // delete $scope.employee;
        //$scope.employeeForm.$setPristine();

        $uibModalInstance.dismiss('cancel');
    };

});
