<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">

<form class="form-horizontal" name="userForm" novalidate role="form">
	<div class="container"  data-ng-init="initAdmin()">
		<fieldset>
			<legend>Categorias de Servi�os:</legend>
		</fieldset>
		<table class="table table-hover">
			<thead>
			<tr>
				<th width="20%">Nome</th>
				<th ></th>
				<th width="50%">Descri��o </th>
				<th></th>
				<th style="text-align: center;">In�cio de Vig�ncia</th>
				<th></th>
				<th style="text-align: center;">Fim de Vig�ncia</th>
				<th></th>
				<th style="text-align: center;">Status</th>
				<th></th>
				<th style="text-align: center;">A��o</th>
			</tr>
			</thead>
			<tbody>
			<tr  ng-repeat="categoria in $root.listaTotalCategorias | orderBy:'nome' " >
				<td  style="vertical-align: middle;">{{categoria.nome}}</td>
				<td  style="vertical-align: middle; "><div style="height: 30px;border-left: 2px solid;color: #ddd"></div></td>
				<td style="vertical-align: middle; ">{{categoria.descricao}} </td>
				<td  style="vertical-align: middle; "><div style="height: 30px;border-left: 2px solid;color: #ddd"></div></td>
				<td style="vertical-align: middle; text-align: center;">{{categoria.inicio_vigencia}}</td>
				<td  style="vertical-align: middle; "><div style="height: 30px;border-left: 2px solid;color: #ddd"></div></td>
				<td style="vertical-align: middle; text-align: center;">{{categoria.fim_vigencia}}</td>
				<td  style="vertical-align: middle; "><div style="height: 30px;border-left: 2px solid;color: #ddd"></div></td>
				<td style="vertical-align: middle; text-align: center;">{{categoria.status}}</td>
				<td  style="vertical-align: middle; "><div style="height: 30px;border-left: 2px solid;color: #ddd"></div></td>
				<td style="vertical-align: middle; text-align: center;" ng-if="categoria.status=='A'">
					<button class="btn btn-danger" ng-click="desativarCategoria(categoria.id)">Desativar</button>
				</td>
				<td style="vertical-align: middle; text-align: center;" ng-if="categoria.status=='I'">
					<button class="btn btn-success" ng-click="ativarCategoria(categoria.id)">Ativar</button>
				</td>
			</tr>
			</tbody>
		</table>
	</div>
</form>