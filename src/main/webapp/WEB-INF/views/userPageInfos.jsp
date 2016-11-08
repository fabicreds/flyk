
<div class="row  center-block container" data-ng-init="init()">
	<div>
		<legend>Pesquisa de usuário</legend>
		<div class="col-md-8 col-xs-10">
			<div class="well panel panel-default">
				<div class="panel-body">
					<div class="col-md-4 col-sm-6 col-xs-12">
						<div class="text-center" ng-if="data.tipoCadastro==4">
							<img src="${pageContext.request.contextPath}/images/admicon.jpg"
								class="avatar img-circle img-thumbnail" alt="avatar"
								style="height: 150px; width: 150px;">
						</div>
						<div class="text-center" ng-if="data.tipoCadastro!=4">
							<img ng-src={{data.fotoPerfil}}
								class="avatar img-circle img-thumbnail" alt="avatar"
								style="height: 150px; width: 150px;">
						</div>
					</div>
					<!--/col-->
					<div class="col-xs-12 col-sm-8">
						<form class="form-horizontal" name="inactivateForm" novalidate
							role="form" ng-submit="inativar()">
							<h2>{{usuario.nome}}</h2>
							<p>
								<strong>Usuário: </strong>{{data.usuario}}
							</p>
							<p>
								<strong>Nome: </strong>{{data.nome}}
							</p>
							<p>
								<strong>Email: </strong>{{data.email}}
							</p>
							<p>
								<strong>Tipo de cadastro: </strong> {{data.tipoCadastro}} -
								{{data.tipoCadastroDescricao}}
							</p>
							<p>


								<input type="submit" class="btn btn-success"
									style="float: right; background-color: #005E2F; color: white"
									value="{{data.btn}}" />
							</p>
						</form>
					</div>
					<!--/col-->
					<div class="clearfix"></div>

					<!--/col-->
					<!--/col-->
				</div>

			</div>
		</div>
	</div>



</div>

