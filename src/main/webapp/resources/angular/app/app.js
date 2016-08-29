﻿/// <reference path="C:\Users\vntlume\Desktop\FLYK2016\FacebookLogin\FacebookLogin\login.html" />
/// <reference path="C:\Users\vntlume\Desktop\FLYK2016\FacebookLogin\FacebookLogin\login.html" />
/// <reference path="C:\Users\vntlume\Desktop\FLYK2016\FacebookLogin\FacebookLogin\login.html" />
var flyk = angular.module("flyk", ["ngAnimate","ui.bootstrap", "ngRoute"]);

//importar $facebookProvider, ngFacebook
flyk.config( function($routeProvider) {
    // $facebookProvider.setAppId('219775141733399');
	
    $routeProvider
        .when("/login",
        {
            templateUrl: "login.html",
            controller: "loginPageCtrl" // só se for abrir uma pag nova
        })
        .when("/cadastro",
        {
            templateUrl: "cadastro.html",
            controller: "cadastroCtrl"
        })
         .when("/home",
        {
            templateUrl: "home.html",
            controller: "homeCtrl"
        })
         .when("/adminPage",
        {
            templateUrl: "adminPage.html",
            controller: "adminPageCtrl"
        })
         .when("/userPageInfos",
        {
            templateUrl: "userPageInfos.html",
            controller: "userPageInfosCtrl"
        })
            .when("/confirmaPromocao",
        {
            templateUrl: "confirmaPromocao.html",
            controller: "confirmaPromocaoCtrl"
        })
             .when("/admPromocao",
        {
            templateUrl: "admPromocao.html",
            controller: "admPromocaoCtrl"
        })
                .when("/profilePage",
        {
            templateUrl: "profilePage.html",
            controller: "profilePageCtrl"
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
flyk.controller("flykCtrl", function ($rootScope, $scope, $location, $http, $uibModal) {
	

    //$scope.isLoggedIn = false;
  
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

 

    $scope.showFormAdmin = function()

    {
    	$location.path('/adminPage');
    };

    
});
flyk.directive('ngCarousel', function() {
    return function(scope, element, attrs) {
      var el = element[0];
      var containerEl = el.querySelector("ul");
      var slidesEl = containerEl.querySelectorAll("li");
      scope.numSlides = slidesEl.length;
      scope.curSilde = 1;   
      scope.$watch('curSlide', function(num) {
        containerEl.style.left = (-1*100*(num-1)) + '%';
      });
      
      el.style.position = 'relative';
      el.style.overflow = 'hidden';

      containerEl.style.position = 'absolute';
      containerEl.style.width = (scope.numSlides*100)+'%';
      containerEl.style.listStyleType = 'none';
      containerEl.style.margin =0;
      containerEl.style.padding=0;
      containerEl.style.transition = '1s';
      
      for(var i=0; i<slidesEl.length; i++) {
        var slideEl = slidesEl[i];
        slideEl.style.display = 'inline-block';
        slideEl.style.width = (100/scope.numSlides) + '%';
      }
    };
  });


flyk.controller("homeCtrl", function ($animate,$rootScope, $scope) {
	$rootScope.usuarioLogado = "";
	$rootScope.tipoUsuarioLogado = "";
	
});