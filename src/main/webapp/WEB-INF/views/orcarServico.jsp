<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<form class="form-horizontal" name="userForm" novalidate role="form">
	<div class="container">
		<fieldset>
			<legend>Orçar Serviço</legend>
			<div class="col-sm-12">
				<div class="form-group" show-errors>
					<label for="fullName" class="col-sm-3 control-label">Nome
						Cliente</label>
					<div class="col-sm-6">
						<input type="text" id="nome_cliente" name="nome_cliente"
							class="form-control" ng-model="$root.data.compromisso.contrato.cliente.nome" disabled="disabled" />
					</div>
				</div>
				<div class="form-group" show-errors>
					<label for="fullName" class="col-sm-3 control-label">Nome
						Prestador</label>
					<div class="col-sm-6">
						<input type="text" id="nome_prestador" name="nome_prestador"
							class="form-control" ng-model="$root.data.nome"
							disabled="disabled">
					</div>
				</div>
				<div class="form-group" show-errors>
					<label for="fullName" class="col-sm-3 control-label">Serviço</label>
					<div class="col-sm-6">
						<input type="text" id="nome_servico" name="nome_servico"
							class="form-control" ng-model="$root.data.compromisso.contrato.servico.nome"
							disabled="disabled">
					</div>
				</div>
				<div class="form-group" show-errors>
					<label for="fullName" class="col-sm-3 control-label">Data
						Início</label>
					<div class="col-sm-2">
						<input type="text" id="data_inicio" name="data_inicio"  disabled="disabled"
							class="form-control disabled" ng-model="$root.data.compromisso.dataInicio"
							placeholder="Data de Início" />
					</div>
				</div>
				<div class="form-group" show-errors>
					<label for="fullName" class="col-sm-3 control-label">Data
						Fim</label>
					<div class="col-sm-2">
						<input type="text" id="data_fim" name="data_fim" 
							class="form-control disabled" ng-model="$root.data.compromisso.dataFim" disabled="disabled"
							placeholder="Data de Fim" />
					</div>
				</div>
				<div class="form-group" show-errors>
					<label for="descricao_servico" class="col-sm-3 control-label">Descrição Serviço</label>
					<div class="col-sm-6">
						<textarea rows="5" id="descricao_servico" name="descricao_servico"
							class="form-control disabled" ng-model="$root.data.compromisso.contrato.descricaoServico" disabled="disabled"></textarea>
					</div>
				</div>
				<div class="form-group" show-errors>
					<label for="fullName" class="col-sm-3 control-label">Custo</label>
					<div class="col-sm-2">
						<input type="text" id="custo" name="custo"
							class="form-control" ng-model="$root.data.compromisso.contrato.custo" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-3"></div>

				<div class="col-sm-9">
					<input type="submit" class="btn btn-success" value="Finalizar" ng-controller = "servicosPrestadosCtrl"
						ng-click="sendOrcarServico()" /> 
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

