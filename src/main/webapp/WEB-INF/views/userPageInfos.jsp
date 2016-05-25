<legend>Pesquisa de usuário</legend>
<div class="container">
    <div class="row">
        <div class="col-md-8 col-xs-10">
            <div class="well panel panel-default">
                <div class="panel-body">
                    <div class="row" ng-repeat="usuario in data">
                        
                        <!--/col-->
                        <div class="col-xs-12 col-sm-8">
                            <h2>{{usuario.nome}}</h2>
                            <p><strong>Username: </strong> {{usuario.usuario}} </p>
                            <p><strong>Tipo de cadastro: </strong> {{usuario.tipoCadastro}} </p>
                            <p><input type="checkbox" name="inativo" value="inativo" />Inativar usuário </p>    
                          
                        </div>
                        <!--/col-->
                        <div class="clearfix"></div>
                      
                        <!--/col-->
                        <!--/col-->
                    </div>
                    <br/>
                    <!--/row-->
                </div>
                <!--/panel-body-->
            </div>
            <!--/panel-->
        </div>
        <!--/col-->
    </div>
    <!--/row-->
	<br/>
</div>
<!--/container-->
