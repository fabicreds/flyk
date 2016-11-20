<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">

<form class="form-horizontal" name="userForm" novalidate role="form">
	<div class="container"  data-ng-init="initAdmin()">
		<fieldset>
			<legend>Lista de Usuários Administradores:</legend>
		</fieldset>
		<table class="table table-hover">
			<thead>
			<tr>
				<th width="40%">Nome</th>
				<th ></th>
				<th style="text-align: center;" width="20%">Usuário </th>
				<th></th>
				<th style="text-align: center;"  width="20%">Tipo Cadastro</th>
				<th></th>
				<th style="text-align: center;"  width="20%">Status</th>
			</tr>
			</thead>
			<tbody>
			<tr  ng-repeat="admin in $root.listaAdministradores | orderBy:'nome' " >
				<td  style="vertical-align: middle;">{{admin.nome}}</td>
				<td  style="vertical-align: middle; "><div style="height: 30px;border-left: 2px solid;color: #ddd"></div></td>
				<td style="vertical-align: middle; text-align: center; ">{{admin.usuario}} </td>
				<td  style="vertical-align: middle; "><div style="height: 30px;border-left: 2px solid;color: #ddd"></div></td>
				<td style="vertical-align: middle; text-align: center;">{{admin.tipoCadastro}} - {{admin.tipoCadastroDescricao}}</td>
				<td  style="vertical-align: middle; "><div style="height: 30px;border-left: 2px solid;color: #ddd"></div></td>
				<td style="vertical-align: middle; text-align: center;">
					<div ng-if="admin.ativo">Ativo</div>
					<div ng-if="!admin.ativo">Inativo</div>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
</form>