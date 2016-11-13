<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">

<form class="form-horizontal" name="userForm" novalidate role="form">
	<div class="container">
		<fieldset>
			<legend>Entre em contato conosco:</legend>
		</fieldset>
		<div class="form-group" show-errors>
			<label for="fullName" class="col-sm-3 control-label">Nome*</label>
			<div class="col-sm-6">
				<input type="text" id="fullName" name="fullName"
					placeholder="Digite seu Nome" class="form-control"
					ng-model="nome" ng-required="true" /> <span class="help-block"
					ng-if="userForm.fullName.$error.required">O nome é
					obrigatório!</span>
			</div>
		</div>
		<div class="form-group" show-errors>
			<label for="email" class="col-sm-3 control-label">Email*</label>
			<div class="col-sm-6">
				<input type="email" id="email" name="email" class="form-control"
					ng-model="email" ng-required="true" placeholder="Digite seu Email" />
				<span class="help-block" ng-if="userForm.email.$error">Um
					e-mail inválido foi digitado! </span>
			</div>
		</div>
		<div class="form-group" show-errors>
			<label for="fullName" class="col-sm-3 control-label">Assunto*</label>
			<div class="col-sm-6">
				<input type="text" id="assunto" name="assunto"
					placeholder="Assunto do Email" class="form-control"
					ng-model="assunto" ng-required="true" /> <span class="help-block"
					ng-if="userForm.fullName.$error.required">O assunto é
					obrigatório!</span>
			</div>
		</div>

		<div class="form-group" show-errors>
			<label for="fullName" class="col-sm-3 control-label">Mensagem*</label>
			<div class="col-sm-6">
				<textarea id="mensagem" name="mensagem"
					placeholder="Mensagem do Email" class="form-control"
					ng-model="mensagem" ng-required="true" rows="15"></textarea>
				<span class="help-block" ng-if="userForm.fullName.$error.required">O
					assunto é obrigatório!</span>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-3"></div>

			<div class="col-sm-9">
				<input type="submit" class="btn btn-success" value="Finalizar"
					ng-click="enviarEmail()" /> 
					<input type="submit" ng-click="limpar()"
					class="btn btn-default" value="Limpar"
					/>
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-3 control-label"></label>
			<div class="col-sm-6">
				<font color="red"> {{messageErro}}</font> <font
					color="green"> {{messageSucesso}}</font>
			</div>
		</div>
	</div>
</form>