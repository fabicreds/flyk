<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<form class="form-horizontal" name="userForm" novalidate role="form">
	<div class="container">
		<fieldset>
			<legend>Solicitar Servi�o</legend>
			<div class="col-sm-12">
				<div class="form-group" show-errors>
					<label for="fullName" class="col-sm-3 control-label">Nome
						Cliente</label>
					<div class="col-sm-6">
						<input type="text" id="nome_cliente" name="nome_cliente"
							class="form-control" ng-model="data.nome" disabled="disabled" />
					</div>
				</div>
				<div class="form-group" show-errors>
					<label for="fullName" class="col-sm-3 control-label">Nome
						Prestador</label>
					<div class="col-sm-6">
						<input type="text" id="nome_prestador" name="nome_prestador"
							class="form-control" ng-model="data.amigo.nome"
							disabled="disabled">
					</div>
				</div>
				<div class="form-group" show-errors>
					<label for="fullName" class="col-sm-3 control-label">Servi�o</label>
					<div class="col-sm-6">
						<select class="form-control" ng-model="servico"
							ng-options="item as item.nome for item in $root.data.amigo.listaCategoriaServicosPrestados">
						</select>
					</div>
				</div>
				<div class="form-group" show-errors>
					<label for="fullName" class="col-sm-3 control-label">Data
						In�cio</label>
					<div class="col-sm-2">
						<input type="text" id="data_inicio" name="data_inicio" ui-date
							class="form-control" ng-model="data_inicio"
							placeholder="Data de In�cio" />
					</div>
					<label for="fullName" class="col-sm-1 control-label">Hor�rio
						In�cio</label>
					<div class="col-sm-1">
						<select class="form-control" ng-model="hora_inicio"
							ng-options="item.id as item.label for item in horas">
						</select>
					</div>
					<div class="col-sm-1">
						<select class="form-control" ng-model="minuto_inicio"
							ng-options="item.id as item.label for item in minutos">
						</select>
					</div>
				</div>
				<div class="form-group" show-errors>
					<label for="fullName" class="col-sm-3 control-label">Data
						Fim</label>
					<div class="col-sm-2">
						<input type="text" id="data_fim" name="data_fim" ui-date
							class="form-control" ng-model="data_fim"
							placeholder="Data de Fim" />
					</div>
					<label for="fullName" class="col-sm-1 control-label">Hor�rio
						Fim</label>
					<div class="col-sm-1">
						<select class="form-control" ng-model="hora_fim"
							ng-options="item.id as item.label for item in horas">
						</select>
					</div>
					<div class="col-sm-1">
						<select class="form-control" ng-model="minuto_fim"
							ng-options="item.id as item.label for item in minutos">
						</select>
					</div>
				</div>
				<div class="form-group" show-errors>
					<label for="descricao_servico" class="col-sm-3 control-label">Descri��o Servi�o</label>
					<div class="col-sm-6">
						<textarea rows="5" id="descricao_servico" name="descricao_servico"
							class="form-control" ng-model="descricao_servico" placeholder="Descri��o do Servi�o que est� sendo solicitado, informando detalhes para que seja poss�vel a or�amenta��o do servi�o."></textarea>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-3"></div>

				<div class="col-sm-9">
					<input type="submit" class="btn btn-success" value="Finalizar"
						ng-click="sendPostContratarServico()" /> <input type="submit" class="btn btn-default"
						value="Limpar" ng-click="resetContratarSevicoForm()" />
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

