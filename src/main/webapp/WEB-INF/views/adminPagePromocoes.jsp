<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">

<form class="form-horizontal" name="userForm" novalidate role="form">
	<div class="container"  data-ng-init="initAdmin()">
		<fieldset>
			<legend>Promoções:</legend>
		</fieldset>
		<table class="table table-hover">
			<thead>
			<tr>
				<th width="20%">Nome</th>
				<th ></th>
				<th width="25%">Descrição </th>
				<th></th>
				<th style="text-align: center;">Início de Vigência</th>
				<th></th>
				<th style="text-align: center;">Fim de Vigência</th>
				<th></th>
				<th style="text-align: center;">Status</th>
				<th></th>
				<th style="text-align: center;" width="15%">Categoria</th>
				<th style="text-align: center;">Preço</th>
			</tr>
			</thead>
			<tbody>
			<tr  ng-repeat="promocao in $root.listaPromocoes | orderBy:'nome' " >
				<td  style="vertical-align: middle;">{{promocao.nome}}</td>
				<td  style="vertical-align: middle; "><div style="height: 30px;border-left: 2px solid;color: #ddd"></div></td>
				<td style="vertical-align: middle; ">{{promocao.descricao}} </td>
				<td  style="vertical-align: middle; "><div style="height: 30px;border-left: 2px solid;color: #ddd"></div></td>
				<td style="vertical-align: middle; text-align: center;">{{promocao.dataInicio}}</td>
				<td  style="vertical-align: middle; "><div style="height: 30px;border-left: 2px solid;color: #ddd"></div></td>
				<td style="vertical-align: middle; text-align: center;">{{promocao.dataFim}}</td>
				<td  style="vertical-align: middle; "><div style="height: 30px;border-left: 2px solid;color: #ddd"></div></td>
				<td style="vertical-align: middle; text-align: center;">
				<div ng-if="promocao.status">Ativo</div>
				<div ng-if="!promocao.status">Inativo</div>
				<td  style="vertical-align: middle; "><div style="height: 30px;border-left: 2px solid;color: #ddd"></div></td>
				
				<td style="vertical-align: middle; text-align: center;">
					<div ng-repeat="pre in promocao.precos" style="padding: 3px">
						<div ng-if="pre.i !=0" style="border-top: 2px solid;color: #ddd;padding-bottom: 3px"></div>
						{{pre.categoria}}
					</div>
				</td>
				<td style="vertical-align: middle; text-align: center;">
				<div ng-repeat="pre in promocao.precos"  style="padding: 3px">
						<div ng-if="pre.i !=0" style="border-top: 2px solid;color: #ddd;padding-bottom: 3px"></div>
						{{pre.preco}}
					</div>
				</td>
				
			</tr>
			</tbody>
		</table>
		
	</div>
</form>