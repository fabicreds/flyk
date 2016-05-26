<legend>Pesquisa de usuário</legend>
<div class="container">
    <div class="row">
        <div class="col-md-8 col-xs-10">
            <div class="well panel panel-default">
                <div class="panel-body">
                    <div class="row" ng-repeat="usuario in data">
                     <div class="col-md-4 col-sm-6 col-xs-12">
      						<div class="text-center">
        						<img src="images/admicon.jpg" class="avatar img-circle img-thumbnail" alt="avatar" style="height: 150px; width: 150px;">
    					  </div>
    				</div>
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
                  
                    <!--/row-->
                </div>
                
                <!--/panel-body-->
            </div>
             <input type="submit" class="btn btn-default" value="Concluir" style="float:right; background-color:#005E2F; color:white" />
            <!--/panel-->
        </div>
        <!--/col-->
       
    </div>
    
    <!--/row-->
	
</div>
<!--/container-->
