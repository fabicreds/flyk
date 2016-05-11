/// <reference path="C:\Users\vntlume\Desktop\FLYK2016\FacebookLogin\FacebookLogin\login.html" />
/// <reference path="C:\Users\vntlume\Desktop\FLYK2016\FacebookLogin\FacebookLogin\login.html" />
/// <reference path="C:\Users\vntlume\Desktop\FLYK2016\FacebookLogin\FacebookLogin\login.html" />
var flyk = angular.module("flyk", ["ui.bootstrap", "ngRoute"]);

//importar $facebookProvider, ngFacebook
flyk.config( function($routeProvider) {
    // $facebookProvider.setAppId('219775141733399');
    $routeProvider
        .when("/login",
        {
            templateUrl: "login.html",
            controller: "flykController"
        })
        .when("/cadastro",
        {
            templateUrl: "cadastro.html",
            controller: "flykController"
        })
         .when("/home",
        {
            templateUrl: "home.html",
            controller: "flykController"
        })
        .otherwise({
            redirectTo: "/home"
        });
})

/*flyk.run( function( $rootScope ) {
    // Load the facebook SDK asynchronously
    (function(){
        // If we've already installed the SDK, we're done
        if (document.getElementById('facebook-jssdk')) {return;}

        // Get the first script element, which we'll use to find the parent node
        var firstScriptElement = document.getElementsByTagName('script')[0];

        // Create a new script element and set its id
        var facebookJS = document.createElement('script'); 
        facebookJS.id = 'facebook-jssdk';

        // Set the new script's source to the source of the Facebook JS SDK
        facebookJS.src = '//connect.facebook.net/en_US/all.js';

        // Insert the Facebook JS SDK into the DOM
        firstScriptElement.parentNode.insertBefore(facebookJS, firstScriptElement);
    }());
}); */
 // importar $facebook
flyk.controller("flykController", function ($scope, $location, $http, $uibModal) {

    $scope.teste = 1;
    //$scope.isLoggedIn = false;
    $scope.states = [
          "Engineering",
            "Marketing",
            "Finance",
            "Administration"

    ];
   /* $scope.login = function () {
        $facebook.login().then(function () {
            refresh();
        });
    }
    function refresh() {
        $facebook.api("/me").then(
          function (response) {
              $scope.welcomeMsg = "Welcome " + response.name;
              $scope.isLoggedIn = true;
          },
          function (err) {
              $scope.welcomeMsg = "Please log in";
          });
    } 

    refresh(); */

    $scope.showFormCadastro = function () {
        $location.path('/cadastro');
    };

    $scope.showFormLogin = function () {
        $location.path('/login');
    };

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

    $scope.showLoginModal = function () {


        $uibModal.open({
            templateUrl: "login.html",
            controller: "flykController"
        });
    }
});