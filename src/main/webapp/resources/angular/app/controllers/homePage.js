flyk.controller("homeCtrl", function ($animate, $rootScope, $scope) {
	
	$rootScope.usuarioLogado = "";
	$rootScope.tipoUsuarioLogado = "";
	$rootScope.idUsuarioLogado = "";
	$rootScope.data = "";
	localStorage.setItem("usuarioLogado", "");
	localStorage.setItem("idUsuarioLogado", "");
	localStorage.setItem("tipoUsuarioLogado", "");
	localStorage.setItem("dadosCliente", "");
	 $scope.myInterval = 3000;
	 $scope.noWrapSlides = false;
	  $scope.active = 0;
	  $scope.slides = [
	    {
	      image: 'images/bricklayers2.jpg',
	      text:"Encontre profissionais com facilidade",
	      subtext: "Busca de servicos eficiente com ranking dos melhores profissionais a partir do feedback de contratantes"
	    },
	    {
		      image: 'images/electrician.jpg',
		      text:"Nunca mais perca contato com os prestadores de servico",
		      subtext:"FLYK te oferece uma rede social e te deixa conectado com o prestador"
		    },
	    {
	      image: 'images/manicure.jpg',
	      text:"Tudo online!",
		  subtext:"Contratacao de servicos, divulgacao de servicos, contato consumidor/prestador com mais facilidade!"
	    }
	  ];
	 
});