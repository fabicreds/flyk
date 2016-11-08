<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/chat.css"
	rel="stylesheet" />
<html>
<style>
.image { 
   position: relative; 
   width: 100%; /* for IE 6 */
}

h2 { 
   position: absolute; 
   top: 100px; 
   left: 0; 
   width: 100%; 
}


</style>
<body>
	<div class="row center-block container" data-ng-init="init()">
		<div class="panel panel-default col-md-3" style="height: 600px">
			<div class="panel-heading">Amigos</div>
			<div class="panel-body">
				<div>
					<table class="table table-hover">
						<tbody>
							<tr ng-repeat="amizade in $root.data.listaAmigos">
								
								<td ng-if="$root.data.chat.amigo == amizade.amigo.id" class="info">
									<div class="link" ng-click="mostrarConversa(amizade.amigo.id)">{{amizade.amigo.nome}}</div>
								</td>
								<td ng-if="$root.data.chat.amigo != amizade.amigo.id">
									<div class="link" ng-click="mostrarConversa(amizade.amigo.id)">{{amizade.amigo.nome}}</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div class="panel panel-default col-md-9" style="height: 600px;">
			<div class="panel-heading">Mensagems</div>
			<div class="panel-body col-md-12">
				<div ng-if="$root.data.chat.listaConversa==null || $root.data.chat.numMensagens==0" style="height: 400px">
					<div class="image" style="text-align: center; vertical-align: middle;">
						<img src="${pageContext.request.contextPath}/images/chat-background.jpg" height="360" width="420">
						<h2>Escreva uma<br> mensagem...</h2>
					</div>
				</div>
				<div ng-if="$root.data.chat.listaConversa!=null && $root.data.chat.numMensagens!=0">
				<div  class="col-md-12"  ng-style="{'height': '400px', 'overflow-y': (parentScrollable && 'scroll')}">
				<div ng-repeat="conversa in $root.data.chat.listaConversa | orderBy:'conversa':true " style="position:relative ;">
					<div ng-if="conversa.flagEnviadoOuRecebido=='R'"
						style="text-align: left ;">
						<div class="talk-bubble-recebida tri-right round left-top"
							ng-if="conversa.flagEnviadoOuRecebido=='R'">
							<div class="talktext">
								<p>{{conversa.msg}}</p>

							</div>
						</div>
					</div>
					<div ng-if="conversa.flagEnviadoOuRecebido!='R'"
						style="text-align: right;">
						<div class="talk-bubble tri-right round right-top">
							<div class="talktext">
								<p>{{conversa.msg}}</p>

							</div>
						</div>
				</div></div>
				</div></div>
				<div class="form-group"  ng-if="$root.data.chat.listaConversa!=null || $root.data.chat.numMensagens==0">
					
					<div class="col-md-11" style="height: 80px">
						<br>
						<textarea rows="3" id="nova_mensagem" name="nova_mensagem"
							class="form-control" ng-model="$root.data.nova_mensagem"
							placeholder="Escreva uma mensagem..."></textarea>
					</div>
					<div class="col-md-1" ng-click="enviarNovaMensagem()" style="height: 80px;vertical-align: middle; ">
						<br>
						<button class="btn btn-success">Enviar</button>
					</div>
					<div class="col-md-12">
					<font color="red">{{mensagemErro}}</font>
					</div>
				</div>
			</div>
		</div>


	</div>

</body>
</html>