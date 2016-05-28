
<style>
    .footer {
	position:fixed;
	bottom:0;
	width:100%;
}
    .dropdown-menu
    {
        width: 300px;
        margin-top: 100px;
        margin-left: 100px;
        margin-right: 50px;
	    text-align: left;
    }
    
</style>
<html ng-app="flyk">

<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>FLYK �</title>
    <script src="${pageContext.request.contextPath}/resources/angular/angular.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/resources/css/app.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/resources/angular/angular-ui/ui-bootstrap-tpls.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/angular/angular-route.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/angular/app/app.js"></script>
    <script src="${pageContext.request.contextPath}/resources/angular/app/controllers/cadastro.js"></script>
    <script src="${pageContext.request.contextPath}/resources/angular/app/controllers/adminPage.js"></script>
    <script src="${pageContext.request.contextPath}/resources/angular/app/controllers/loginPage.js"></script>
    <script src="${pageContext.request.contextPath}/resources/angular/app/controllers/userPageInfos.js"></script>
    <script src="${pageContext.request.contextPath}/resources/angular/app/directives/ValidationDirective.js"></script>
    <script src="${pageContext.request.contextPath}/resources/angular/app/directives/uiCPFdirective.js"></script>
    <script src="${pageContext.request.contextPath}/resources/angular/app/directives/uiDateDirective.js"></script>
    <script src="${pageContext.request.contextPath}/resources/angular/app/directives/uiTelefoneDirective.js"></script>
    <script src="${pageContext.request.contextPath}/resources/angular/app/directives/uiCEPDirective.js"></script>
    <script src="${pageContext.request.contextPath}/resources/angular/app/directives/fileReaderDirective.js"></script>

  <!--   <script src="//rawgithub.com/GoDisco/ngFacebook/master/ngFacebook.js"></script> --> 
</head>


<body class="container">
    <!-- Respons�vel por responsible pages-->
    <!--  <employee-form/>  Directive created to this new form.-->

    <nav class="navbar navbar-default" ng-controller="flykCtrl">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button"  class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand">
                	<img src="images/flyklogo.png" style="height:30px; width:30px" />
                </a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">
                        <span class="glyphicon glyphicon-home"></span>
                        <span class="text">HOME</span>
                        </a></li>
                    <li><a href="#">
                         <span class="glyphicon glyphicon-user"></span>
                         <span class="text">SOBRE N�S</span>
                         </a></li>
                    <li><a href="#">
                         <span class="glyphicon glyphicon-search"></span>
                         <span class="text">COMO USAR</span>
                        </a></li>
                    <li>
                        <a href="#">
                            <span class="glyphicon glyphicon-heart-empty"></span>
                            <span class="text">CLIENTES</span>
                        </a>
                    </li>
                    <li>
                        <a ng-click="showFormLogin()">
                            <span class="glyphicon glyphicon-envelope"></span>
                            <span class="text">CONTATO</span>
                        </a>
                    </li>
                    
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    
                    <li class="link"><a ng-click="showLoginModal()" ng-controller="loginPageCtrl">Login</a></li>
                    

                    <li class="link"><a ng-click="showFormCadastro()">Crie uma conta</a></li>

                 
                </ul>
            </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
    </nav>
    <div ng-view>
        <!--Permite rotear a p�gina-->
      
    </div>
   
    <footer class="footer">
      		<div class="container">
  			 � 2016 FLYK Company
    	  </div>
    </footer>
</body>


</html>

