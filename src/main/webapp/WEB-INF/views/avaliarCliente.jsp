<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<form class="form-horizontal" name="userForm" novalidate role="form">
	<div class="container"  data-ng-init="init()" ng-controller="avaliarServicoCtrl">
		<fieldset>
			<legend>Avaliar Cliente</legend>
			<div class="col-sm-12">
				<div class="form-group" show-errors>
					<label for="fullName" class="col-sm-3 control-label">Nota
						Cliente</label>
					<div class="col-sm-1">
						<select class="form-control" ng-model="avaliacao.avaliacaoCliente"
							ng-options="item.id as item.label for item in avaliacaoNota">
						</select>
					</div>
					<div>
						<font color="yellow" size="5">
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoCliente>=1"></span>
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoCliente>=2"></span>
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoCliente>=3"></span>
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoCliente>=4"></span>
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoCliente>=5"></span>
						</font>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-3"></div>

				<div class="col-sm-9">
					<input type="submit" class="btn btn-success" value="Avaliar" ng-controller="avaliarServicoCtrl"
						ng-click="avaliarCliente()" /> <input type="submit" class="btn btn-default"
						value="Limpar" ng-click="" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-3"></div>

				<div class="col-sm-9">
					<font color="red">{{mensagemErro}}</font>
					<font color="green">{{mensagemSucesso}}</font>
				</div>
			</div>
		</fieldset>
	</div>
</form>

