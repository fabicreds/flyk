<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">
<body>
	<div class="row center-block container" data-ng-init="init()">
		<div ng-repeat="amizade in $root.data.listaAmigos">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="col-md-3 vcenter">
						<div class="text-center">
							<img ng-src={{amizade.amigo.fotoPerfil}}
										class="avatar img-circle img-thumbnail" alt="avatar"
										style="height: 150px; width: 150px;">
						</div>
					</div>
					<!--/col-->
					<div class="col-md-3 vcenter">
						<p>
							<strong>Nome: </strong>
						<p style="text-indent: 1em;">{{amizade.amigo.nome}}</p>
						</p>
						<p>
							<strong>Data de In�cio da Amizade: </strong>
						<p style="text-indent: 1em;">{{amizade.dataInicioAmizade}}</p>
						</p>
					</div>
					<div class="col-md-2 vcenter">
						<p>
							<strong>Tipo de Cadastro: </strong>
						<p style="text-indent: 2em;">{{amizade.amigo.perfil}}</p>
						</p>
						<p>
							<strong>Status da Amizade: </strong>
						<p ng-if="amizade.status == 1" style="text-indent: 1em;">
							<font color="green"> <span
								class="glyphicon glyphicon-ok-sign"></span>
								{{amizade.statusDescricao}}
							</font>
						</p>
						<p ng-if="amizade.status == 2" style="text-indent: 1em;">
							<font color="red"> <span
								class="glyphicon glyphicon-remove-sign"></span>
								{{amizade.statusDescricao}}
							</font>
						</p>
						<p ng-if="amizade.status == 3 || amizade.status == 4 "
							style="text-indent: 1em;">
							<font color="blue"> <span
								class="glyphicon glyphicon-question-sign"></span>
								{{amizade.statusDescricao}}
							</font>
						</p>
						</p>
					</div>
					<div class="col-md-3 vcenter" style="text-align: center;">
						<p>
							<input type="submit" class="btn btn-primary"
								value="Visualizar Perfil"
								ng-click="sendPostPerfilAmigo(amizade.amigo.id, amizade.amigo.tipoCadastro, amizade.isRecomendacaoDada)"
								ng-controller="friendsProfilePageCtrl" />
						</p>
						<div ng-if="amizade.isRecomendacaoDada" style="color: red"> 
							<button class="btn disabled" >
							<table style="color: red;">
							<tr>
							<td style="padding-right: 10px"><font size="6"><span class="glyphicon glyphicon-heart"></span></font>  </td> 
							<td><font size="3">Recomendado</font></td>
							</tr>
							</table>
							</button >
						</div>
						<div ng-if="amizade.isRecomendacaoRecebida" > 
							<button class="btn disabled" >
							<table style="color: green;">
							<tr>
							<td style="padding-right: 10px"><font size="6"><span class="glyphicon glyphicon-heart"></span></font>  </td> 
							<td><font size="3">Recomenda��o <br>Recebida </font></td>
							</tr>
							</table>
							</button >
						</div>
					</div>
				</div>
			</div>
		</div>
		<div ng-if="$root.data.numAmigos == 0" style="text-align: center;">
			<img
				src="${pageContext.request.contextPath}/images/no-friends-minios.png" />
			<h3>Voc� ainda n�o tem amigos!</h3>
		</div>
	</div>
</body>

