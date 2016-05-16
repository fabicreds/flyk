
<body>


<div class="row">
	<div class="col-sm-6">
		<form>
			<div class="form-group">
			<fieldset>
				<div  style="background-color:#f8f8f8 ;height:40%; padding:-1%">
					<legend> Inativar Usuario</legend>
					
					<label for=lblsearchUser>Digite o nome/email que deseja:</label>
					<input type="text" id="serchUser" name="searchUser" class="form-control" />
					<input type="submit" value="Pesquisar" style="float:right"/>
					
				</div>
			</fieldset>
			</div>
		</form>
	</div>


	<div class="col-sm-6">
		<form>
			<div class="form-group">
			<fieldset>
				<div  style="background-color:#f8f8f8 ;height:40%; padding:-1%">
					<legend> Administrar promoções</legend>
					
				</div>
			</fieldset>
			</div>
		</form>
	</div>


</div>

<div class="row">
	
	<div class="col-sm-6">
		<form>
			<fieldset>
				<div  style="background-color:#f8f8f8 ;height:40%; padding:-1%">
					<legend> Analisar rendimentos</legend>
				</div>
			</fieldset>
		</form>
	</div>

	<div class="col-sm-6">
		<form>
			<fieldset>
				<div  style="background-color:#f8f8f8 ;height:40%; padding:-1%">
					<legend>Cadastrar administrador</legend>
					
					<label for="lblnewAdm">Digite o nome do novo administrador</label>
					<input type="text" id="newAdm" name="newAdm" class="form-control"/>
					<input type="submit" value="Adicionar" on-click="alert('Tem certeza que deseja adicionar?')" style="float:right"/>
				</div>
			</fieldset>
		</form>
	</div>


</div>

</body>
