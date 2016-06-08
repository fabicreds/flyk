<style>
.selected{
color:red;
}

.non-selected{
color:black;
}

</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<body>


 <div class="row" data-ng-init="init()">
<!--<div class="row"> -->
	<div class="col-sm-6">
		<form class="form-horizontal" name="inactivateForm" novalidate role="form" ng-submit="pesquisar()" ng-controller="adminPageCtrl">
			
			 <fieldset>
				<div class="card" style="height:50%; margin-bottom:1%">
					<legend class="tituloInat">Inativar Usuario</legend>
					<div class="form-group">
						<div class="col-sm-9" style="margin-left:3%" show-errors>
					
							<label for=lblsearchUser>Digite o usuário que deseja inativar:</label>
					
							<input type="text" id="serchUser" name="searchUser" class="form-control" ng-model="usuarioBusca" ng-required="true"/>
							<span class="help-block"
                     	    		ng-if="inactivateForm.searchUser.$error.required">O usuário é obrigatório!</span>	
							<br/>
							<input type="checkbox" name="administrador" value="administrador" ng-model="checkAdministrador"/> Usuário Administrador
							<div class="form-group" style="float:right">
							 <input type="submit" class="btn btn-success" value="Pesquisar"  ng-disabled="inactivateForm.$invalid"/>
							  <input type="button" class="btn btn-default" value="Limpar" ng-click="resetPromForm()" />
							 
							 </div>
              		 		<br/>
              		 		<br/>
              		 		<p>* Ao inativar um usuário do sistema, você bloquea imediatamente o acesso do mesmo. </p>
              		 		<p>
								<font color="red"> {{messageErroInativar}}</font> <font color="green">
									{{messageSucessoInativar}}</font>

							</p>
					</div>
				</div>
			</div>	
		</fieldset>
	</form>
 </div>
 

	<div class="col-sm-6">
			
			
		<form class="form-horizontal" name="promForm" novalidate role="form"  ng-submit="sendPostProm()">
		<fieldset>
		<div  class="card" style="height:50%;">
			<legend class="tituloProm"> Administrar promoções</legend>
		
			<div class="form-group" show-errors>
				<label for="nomepromocao" class="col-sm-3 control-label">Nome</label>
				<div class="col-md-6">                
						   <input type="text" name="nomeprom"  class="form-control"  ng-model="promnome" ng-required="true" >
						   <span class="help-block"
                     	    		ng-if="promForm.nomeprom.$error.required">O nome é obrigatório!</span>					
				</div>
			</div>	
			<div class="form-group" show-errors>
				<label for="descricao" class="col-sm-3 control-label">Descrição</label>
				<div class="col-sm-6">                
					<textarea rows="5" cols="50" name="promdescricao" ng-model="promdescricao" class="form-control" ng-required="true">
					</textarea>
					<span class="help-block"
                     	    		ng-if="promForm.promdescricao.$error.required">A descrição é obrigatória!</span>
				</div>
			</div>
	
          <!--  
			<div style="float:right;margin-right:100px">	
			    
					
			</div>
			-->
      <div class=form-group>
						<div class="col-sm-3 control label">
						</div>
						<div class="col-sm-6">
					  			 <input type="submit" class="btn btn-success" value="Adicionar" style="color:white" ng-disabled="promForm.$invalid"/>
              			 
              		 			<input type="button" class="btn btn-default" value="Limpar"	ng-click="resetPromForm()" />
              		 	
   	           		 	
              			 
              		 </div>
              	  </div>
              		 
              		 
              		 	
			
		        
		</div>
		</form>
					
		</div>
		</fieldset>
			
		
		
	


</div>

<div class="row">
	
	<div class="col-sm-6">
		<form class="form-horizontal" name="serviceForm" novalidate role="form" ng-submit="sendPostServices()">
			<fieldset>
				<div class="card" style="height:50%;">
					<legend class="tituloCadCat">Categoria de serviços
						<span class="glyphicon glyphicon-pencil" style="float:right; margin-right:1%" ng-click="editCategory()"
						 ng-class="{'selected': edit, 'non-selected': !edit}"> </span>
						<span class="glyphicon glyphicon-plus" style="float:right; margin-right:1%" ng-click="addCategory()"
						 ng-class="{'selected': add, 'non-selected': !add}"></span>
					</legend>
			 <!--  <div ng-if="add===true">-->
					 <div class="form-group" show-errors>
            			<label for="name" class="col-sm-3 control-label">Nome</label>
            				<div class="col-sm-6">
                				<input type="text" id="servname" name="servname" class="form-control" ng-model="servname" ng-required="true"/>
                				
                				
                				<span class="help-block"
                     			 ng-if="serviceForm.servname.$error.required">O nome do serviço é obrigatório!</span>
                           </div>
                    </div>
                    
                     <div class="form-group" show-errors>
            			<label for="desricao" class="col-sm-3 control-label">Descrição</label>
            				<div class="col-sm-6">
                				<textarea rows="5" cols="50" id="servdescription" name="servdescription" class="form-control" ng-model="servdescription" ng-required="true"> </textarea>
                				<span class="help-block"
                     			 ng-if="serviceForm.servdescription.$error.required">A descrição do serviço é obrigatória! </span>
                           </div>
                    </div>
                     <div class=form-group>
						<div class="col-sm-3 control label">
						</div>
						<div class="col-sm-6">
					  			<input type="submit" class="btn btn-success" value="Adicionar"
              			 		style=" color:white" ng-disabled="serviceForm.$invalid" />
              			 
              		 			<input type="button" class="btn btn-default" value="Limpar"
              		 			ng-click="resetservform()" />
              		 	
   	           		 	
              			 
              		 </div>
              	  </div>
              	<!-- </div>  ng-if -->
				</div>
			</fieldset>
		</form>
	</div>

	<div class="col-sm-6">
		<form class="form-horizontal" name="admForm" novalidate role="form" ng-submit="sendPostAdm()">
			<fieldset>
				<div  class="card" style="height:50%;">
					<legend class="tituloCadAdm" >Cadastrar administrador</legend>
					
					 <div class="form-group" show-errors>
            			<label for="name" class="col-sm-3 control-label">Nome</label>
            				<div class="col-sm-6">
                				<input type="text" id="admname" name="admname" class="form-control" ng-model="admname" ng-required="true"/>
                				<span class="help-block"
                     			 ng-if="admForm.admname.$error.required">O usuário é obrigatório!</span>
                           </div>
                    </div>
                    
                     <div class="form-group" show-errors>
            			<label for="name" class="col-sm-3 control-label">Usuario</label>
            				<div class="col-sm-6">
                				<input type="text" id="admusername" name="admusername" class="form-control" ng-model="admusername" ng-required="true"/>
                				<span class="help-block"
                     			 ng-if="admForm.admusername.$error.required">O usuário é obrigatório!</span>
                           </div>
                    </div>
                    
                    <div class="form-group" show-errors>
            			<label for="name" class="col-sm-3 control-label">Senha</label>
            				<div class="col-sm-6">
                				<input type="password" id="admpassword" name="admpassword" class="form-control" ng-model="admpassword" ng-required="true"/>
                				 <span class="help-block"
                     			 ng-if="admForm.admpassword.$error.required">A senha é obrigatória!</span>
                           </div>
                    </div>
				
					<div class=form-group>
						<div class="col-sm-3 control label">
						</div>
						<div class="col-sm-6">
					  <input type="submit" class="btn btn-success" value="Adicionar"
              			 style=" color:white" ng-disabled="admForm.$invalid" />
              			 
              		 <input type="button" class="btn btn-default" value="Limpar"
              		 	ng-click="resetadmform()" />
              		 	
   	           		 	
              			 
              		 </div>
              	</div>
						<div class=form-group>
							<div class="col-sm-3 control label"></div>
							<div class="col-sm-6">
								<font color="red"> {{messageErroCadastro}}</font>
								 <font color="green">
									{{messageSucessoCadastro}}</font>

							</div>
						</div>

					</div>
			</fieldset>
		</form>
	</div>


</div>

</body>