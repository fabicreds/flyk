<legend>Pesquisa de usuário</legend>
<div class="container">
	<div class="row">
		<div class="col-md-8 col-xs-10">
			<div class="well panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-md-4 col-sm-6 col-xs-12">
							<div class="text-center">
								<img src="${pageContext.request.contextPath}/images/admicon.jpg"
									class="avatar img-circle img-thumbnail" alt="avatar"
									style="height: 150px; width: 150px;">
							</div>
						</div>
						<!--/col-->
						<div class="col-xs-12 col-sm-8">
							<form class="form-horizontal" name="inactivateForm" novalidate role="form"  ng-submit="inativar()">
								<h2>{{usuario.nome}}</h2>
								<p>
									<strong>Username: </strong>{{data.usuario}}</label>
								</p>
								<p>
									<strong>Tipo de cadastro: </strong> {{data.tipoCadastro}}
								</p>
								<p>


									<input type="submit" class="btn btn-default"
										style="float: right; background-color: #005E2F; color: white" value="{{data.btn}}"/>
								</p>
							</form>
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

</div>
<!--/container-->

