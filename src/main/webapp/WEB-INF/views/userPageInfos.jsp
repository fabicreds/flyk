<legend>Pesquisa de usu�rio</legend>
<div class="container">
    <div class="row">
        <div class="col-md-8 col-xs-10">
            <div class="well panel panel-default">
                <div class="panel-body">
                    <div class="row" ng-repeat="usuario in data">
                        <div class="col-xs-12 col-sm-4 text-center">
                            <img src="http://api.randomuser.me/portraits/women/21.jpg" alt="" class="center-block img-circle img-thumbnail img-responsive">
                            <ul class="list-inline ratings text-center" title="Ratings">
                                <li><span class="glyphicon glyphicon-star"></span></li>
                                <li><span class="glyphicon glyphicon-star"></span></li>
                                <li><span class="glyphicon glyphicon-star"></span></li>
                                <li><span class="glyphicon glyphicon-star"></span></li>
                                <li><span class="glyphicon glyphicon-star"></span></li>
                            </ul>
                        </div>
                        <!--/col-->
                        <div class="col-xs-12 col-sm-8">
                            <h2>{{usuario.nome}}</h2>
                            <p><strong>Username: </strong> {{usuario.usuario}} </p>
                            <p><strong>Email: </strong> {{usuario.email}} </p>
                            <p><strong>Tipo de cadastro: </strong> {{usuario.tipoCadastro}} </p>
                            <p><input type="checkbox" name="inativo" value="inativo" />Inativar usu�rio </p>    
                          
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
            <!--/panel-->
        </div>
        <!--/col-->
    </div>
    <!--/row-->
	{{user}}
</div>
<!--/container-->
