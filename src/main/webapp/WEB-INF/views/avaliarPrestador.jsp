<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<form class="form-horizontal" name="userForm" novalidate role="form">
	<div class="container" data-ng-init="init()" ng-controller="avaliarServicoCtrl">
		<fieldset>
			<legend>Avaliar Prestador</legend>
			<div class="col-sm-12">
				<div class="form-group" show-errors>
					<label for="fullName" class="col-sm-3 control-label">Nota
						Pontualidade</label>
					<div class="col-sm-1">
						<select class="form-control" ng-model="avaliacao.avaliacaoTempo"
							ng-options="item.id as item.label for item in avaliacaoNota">
						</select>
					</div>
					<div>
						<font color="yellow" size="5">
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoTempo>=1"></span>
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoTempo>=2"></span>
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoTempo>=3"></span>
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoTempo>=4"></span>
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoTempo>=5"></span>
						</font>
					</div>
				</div>
				<div class="form-group" show-errors>
					<label for="fullName" class="col-sm-3 control-label">Nota
						Qualidade</label>
					<div class="col-sm-1">
						<select class="form-control" ng-model="avaliacao.avaliacaoQualidade"
							ng-options="item.id as item.label for item in avaliacaoNota">
						</select>
					</div>
					<div>
						<font color="yellow" size="5">
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoQualidade>=1"></span>
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoQualidade>=2"></span>
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoQualidade>=3"></span>
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoQualidade>=4"></span>
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoQualidade>=5"></span>
						</font>
					</div>
				</div>
				<div class="form-group" show-errors>
					<label for="fullName" class="col-sm-3 control-label">Nota
						Preço</label>
					<div class="col-sm-1">
						<select class="form-control" ng-model="avaliacao.avaliacaoPreco"
							ng-options="item.id as item.label for item in avaliacaoNota">
						</select>
					</div>
					<div>
						<font color="yellow" size="5">
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoPreco>=1"></span>
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoPreco>=2"></span>
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoPreco>=3"></span>
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoPreco>=4"></span>
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoPreco>=5"></span>
						</font>
					</div>
				</div>
				<div class="form-group" show-errors>
					<label for="fullName" class="col-sm-3 control-label">Nota
						Profissionalismo</label>
					<div class="col-sm-1">
						<select class="form-control" ng-model="avaliacao.avaliacaoProfissionalismo"
							ng-options="item.id as item.label for item in avaliacaoNota">
						</select>						
					</div>
					<div>
						<font color="yellow" size="5">
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoProfissionalismo>=1"></span>
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoProfissionalismo>=2"></span>
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoProfissionalismo>=3"></span>
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoProfissionalismo>=4"></span>
						<span class="glyphicon glyphicon-star" ng-if="avaliacao.avaliacaoProfissionalismo>=5"></span>
						</font>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-3"></div>

				<div class="col-sm-9">
					<input type="submit" class="btn btn-success" value="Avaliar" ng-controller="avaliarServicoCtrl"
						ng-click="avaliarPrestador()" /> 
					<input type="submit" class="btn btn-default" value="Limpar" ng-click="limparAvaliacaoPrestador()" />
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

