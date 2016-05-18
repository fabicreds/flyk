<style>
    .jumbotron {
        width: 400px;
        margin-top: 50px;
        margin-left: auto;
        margin-right: auto;
	    text-align: left;
    }
    .btn-success
    {
        background-color:#008000;
    }

    .sb-page-header {
        position: relative;
        padding: 30px 15px;
        text-align: center;
        color: inherit;
        background-color: #eeeeee;
        margin-bottom: 40px;
        font-size: 20px;
    }

     .sb-page-header p {
    margin-bottom: 0;
    line-height: 1.4;
}
</style>


<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

</head>
<body>

    
  <!-- <header class="sb-page-header">
        <div class="container">
            <p>Encontre o serviço que deseja com facilidade ou divulgue com eficiencia o seu serviço!</p>
        </div> 

    </header> -->
      
        <form name="userForm" role="form">


            <div class="form-group" show-errors>
                <label for="email">Email</label>
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="glyphicon glyphicon-tag"></i>
                    </span>
                    <input type="email" id="email" name="email" class="form-control"
                           ng-model="user.email" ng-required="true" />
                </div>
                <span class="help-block"
                      ng-if="userForm.email.$error.required">Para continuar, digite um e-mail!</span>
            </div>


            <div class="form-group" show-errors>
                <!--A classe has-error do bootstrap muda de cor todos os elementos do div de acordo com-->
                <label for="password">Senha</label>
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="glyphicon glyphicon-lock"></i>
                    </span>
                    <input type="password" id="password" name="password" class="form-control"
                           ng-model="user.password" ng-required="true" ng-minlength="5" ng-maxlength="10" /> <!--Com o form-control, ele fica responsivel. Não use value para inserior o valor digitado. -->
                </div>
                <span class="help-block"
                      ng-if="userForm.password.$error.required">Para continuar, digite uma senha!</span>
            </div>

            <div class="form-group">
                <label for="info" class="checkbox-inline">
                    <input type="checkbox" />
                    Remember me
                </label>
                <span class="pull-right">
                    <a href="#">Forget password?</a>
                </span>
            </div>


        </form>

    <div class="form-group">
        <button class="btn btn-success btn-sm" ng-click="" ng-disabled="usuarioForm.$invalid">Login</button>
        <span class="text-center"> or login with</span>
        <input type="image" src="/main/webapp/images/facebook.png"" height="20" width="20" />
        <!--<input type="image" class="btn-block" src="/img/facebook.png" ng-click="login()" ng-hide="isLoggedIn" height="30" width="30" />  ng-click="login()" ng-hide="isLoggedIn"-->
    
    </div>
</body>