
<style>
    .jumbotron {
        width: 400px;
        margin-top: 50px;
        margin-left: auto;
        margin-right: auto;
	    text-align: left;
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

     
  <!-- <header class="sb-page-header">
        <div class="container">
            <p>Encontre o serviço que deseja com facilidade ou divulgue com eficiencia o seu serviço!</p>
        </div> 

    </header> -->
      <!-- <div class="col-sm-12"> -->
       <div class="modal-dialog modal-sm">
    	<div class="modal-content">
       		<div class="modal-body">
        		<form name="userForm" ng-submit="sendPostLogin()">


            <div class="form-group" show-errors>
                <label for="email">Usuário ou Email</label>
                <div class="input-group">
                    <span class="input-group-addon">
                        <i class="glyphicon glyphicon-tag"></i>
                    </span>
                    <input type="text" id="email" name="email" class="form-control"
                           ng-model="useremail" ng-required="true" />
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
                           ng-model="userpassword" ng-required="true" /> <!--Com o form-control, ele fica responsivel. Não use value para inserior o valor digitado. -->
                </div>
                <span class="help-block"
                      ng-if="userForm.password.$error.required">Para continuar, digite uma senha!</span>
            </div>

            <div class="form-group">
                <label for="info" class="checkbox-inline">
                    <input type="checkbox" ng-model="lembrar" />
                    Lembrar-me
                </label>
                <span class="pull-right">
                    <a href="#">Esqueceu a senha?</a>
                </span>
            </div>


        

    <div class="form-group" align="right">
        <input type="submit" class="btn btn-block btn-success" ng-disabled="usuarioForm.$invalid" value="Log In"/>
    
    </div>
    <div style="color: red;">
    {{mensagemLogin}}
    </div>
    </form>
  </div>
  </div>
 </div>

