<link href="https://fonts.googleapis.com/css?family=Amaranth"
	rel="stylesheet">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<form class="form-horizontal" name="userForm" novalidate role="form">

    <fieldset>
        <legend>Cadastro - Novo cliente</legend>
        <div class="col-sm-3">
         <h4>Escolha sua foto de perfil</h4>
      	  <div class="text-center">
        	<form>
        		
        		<input type="file" ng-file-select="onFileSelect($files)" ng-model="imageProfile" >
				 <!--  <input type="file" ng-file-select="onFileSelect($files)" multiple> -->

  
   			</form>
   			<b>Preview:</b><br />
  			
   			<img ng-src="{{imageSrc}}" style="height: 200px; width: 200px; border-radius: 50%; border: none;"/><br/>
   			
   			<b>Progress:</b>
  			<progress value="{{progress}}"></progress>
  			
      	  </div>
       </div>
       <div class="col-sm-9"> 
        <div class="form-group" show-errors>
            <label for="fullName" class="col-sm-3 control-label">Nome*</label>
            <div class="col-sm-6">
                <input type="text" id="fullName" name="fullName" class="form-control"
                       ng-model="fullName" ng-required="true" />

                <span class="help-block"
                      ng-if="userForm.fullName.$error.required">O nome é obrigatório!</span>
            </div>
        </div>

        <div class="form-group" show-errors>
            <label for="email" class="col-sm-3 control-label">Email*</label>
            <div class="col-sm-6">
                <input type="email" id="email" name="email" class="form-control"
                       ng-model="email" ng-required="true" />
                <span class="help-block"
                      ng-if="userForm.email.$error">Um e-mail inválido foi digitado! </span>
            </div>
        </div>

        <div class="form-group" show-errors>
            <label for="nickname" class="col-sm-3 control-label">Apelido*</label>
            <div class="col-sm-6">
                <input type="text" id="nickname" name="nickname" class="form-control"
                       ng-model="nickname" ng-required="true" />
                <span class="help-block"
                      ng-if="userForm.nickname.$error.required">O apelido é obrigatório!</span>
            </div>
        </div>

        <div class="form-group" show-errors>          
            <label for="password" class="col-sm-3 control-label">Senha*</label>
            <div class="col-sm-3">
                <span uib-tooltip="A senha deve conter letra minuscula, maiuscula, numero e caracter especial."
                      uib-tooltip-placement="right"></span>
                <input type="password" id="password" name="password" class="form-control" ng-model="password"
                 ng-required="true" ng-pattern="/((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/" ng- ng-maxlength="10" /> <!--Com o form-control, ele fica responsivel. Não use value para inserior o valor digitado. -->
                <span class="help-block"
                      ng-if="userForm.password.$error.required">A senha é obrigatória! </span>
                <span class="help-block"
                      ng-if="userForm.password.$error.minlength">A senha é muito curta! </span> <!-- mostra a mensagem até antes do usuario digitar. Assim que ele digita, a mensagem desaparece.-->
                <span class="help-block"
                      ng-if="userForm.password.$error.maxlength">A senha é muito longa!</span>
                <span class="help-block"
                      ng-if="userForm.password.$error.pattern"> A senha não atende aos requisitos!         
                </span>
            </div>
        </div>
        
          <div class="form-group">
          		<div class="col-sm-3 control-label">
         		</div>
         		<div class="col-sm-9">
         		<label for="warningpass"><h8>A senha deve conter letra minuscula, maiuscula, numero e caracter especial.</h8></label>
         		</div>
          </div>

        <div class="form-group" show-errors>
            <label for="confirmPassword" class="col-sm-3 control-label"> Confirme a senha*</label>
            <div class="col-sm-3">
                <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" ng-model="confirmPassword"
                       ng-required="true" ng-minlength="5" ng-maxlength="10" ng-blur="validatePassword(user)" /> <!--Com o form-control, ele fica responsivel. Não use value para inserior o valor digitado. -->
            </div>
        </div>

        <div class="form-group" show-errors>
            <label for="CPF" class="col-sm-3 control-label">CPF*</label>
            <div class="col-sm-3">
                <input type="text" id="CPF" name="CPF" class="form-control" ng-model="CPF" ng-required="true" ui-cpf/>
                <span class="help-block"
                      ng-if="userForm.CPF.$error.required">CPF é obrigatório!</span>
             </div>
        </div>

    
        <div class="form-group" show-errors>
            <label for="Date" class="col-sm-3 control-label">Data de nascimento</label>
            <div class="col-sm-3">
                <input type="text" id="date" name="date" class="form-control" ng-model="dateBirth" ui-date />
            </div>
            </div>

        <div class="form-group" show-errors>
            <label for="numberContact" class="col-sm-3 control-label">Telefone 1*</label>
            <div class="col-sm-3">
                <input type="text" id="telephone1" name="telephone1" class="form-control" ng-model="telephone1"  ng-required="true" ui-telefone/>
                 <span class="help-block"
                      ng-if="userForm.telephone1.$error.required">Este telefone é obrigatório!</span>
           
            </div>
        </div>
        
        <div class="form-group" show-errors>
            <label for="numberContact2" class="col-sm-3 control-label">Telefone 2</label>
            <div class="col-sm-3">
                <input type="text" id="telephone2" name="telephone2" class="form-control" ng-model="telephone2" ui-telefone/>
           
            </div>
        </div>

        <div class="form-group">
            <label for="userType" class="col-sm-3 control-label"></label>
            <div class="col-sm-9">

                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="userType" value="prestador"
                               ng-model="prestador.flag" /> Deseja se tornar um prestador de serviço? 
                    </label><br />
                </div>
              

            </div>
        </div>

        <div class="form-group" uib-collapse="!prestador.flag">
            <label for="nonCompeteNotes" class="col-sm-4 control-label">
               Tipo de Perfil
            </label>
            <div class="col-sm-8">
                <div class="radio">
                    <label>
                        <input type="radio" name="serviceType" value="free"
                               ng-model="prestador.type" /> Free 
                    </label><br />
                </div>
                <div class="radio">
                    <label>
                        <input type="radio" name="serviceType" value="premium"
                               ng-model="prestador.type" /> Premium 
                    </label><br />
                </div>
            </div>
        </div>

        <div class="form-group" show-errors>
            <label for="address1" class="col-sm-3 control-label">Endereço</label>
            <div class="col-sm-2">
                <input type="text" id="addressPostalCode" name="addressPostalCode" class="form-control" ng-model="address.cep"
                       ng-blur="pesquisaCep(address.cep)" placeholder="CEP" /> <!--Popular a partir do banco de dados-->
            </div>
        </div>

        <div class="form-group" show-errors>
            <div class="col-sm-3">
                </div>
            <div class="col-sm-5">
                <input type="text" id="addressStreet" name="addressStreet" class="form-control" ng-model="address.logradouro" placeholder="Logradouro" />
            </div>
            <div class="col-sm-1">
                <input type="text" id="addressNumber" name="addressNumber" class="form-control" ng-model="address.numero" placeholder="Nº" />
            </div>
            <div class="col-sm-2">
                <input type="text" id="addressComp" name="addressComp" class="form-control" ng-model="address.comp" placeholder="Complemento" />
            </div>

            </div>
        <div class="form-group" show-errors>
            <div class="col-sm-3"></div>
            <div class="col-sm-3">
                <input type="text" name="addressBairro" id="bairro" class="form-control" ng-model="address.bairro" placeholder="Bairro" />
            </div>
            <div class="col-sm-2">
                <input type="text" name="addressCity" id="city" class="form-control" ng-model="address.cidade" placeholder="Cidade"/>
            </div>
            <div class="col-sm-1">
                <input type="text" name="addressState" id="state" class="form-control" ng-model="address.estado" placeholder="UF"/>
                
            </div>
            <div class="col-sm-2">
                <input type="text" name="addressCountry" id="country" class="form-control" ng-model="address.pais" placeholder="Pais" />


            </div>
        </div>
        
        {{address}}
        

      
           
        <div class="form-group">
            <div class="col-sm-3">
            </div>
            <div class="col-sm-3">
                <label for="info">* Campos obrigatórios</label>
            </div>
        </div>
        
        <div class="form-group">
    	<div class="col-sm-3">
    	</div>
    
        <div class="col-sm-9">
        <input type="submit" class="btn btn-success" value="Finalizar"
               ng-click="sendPostCadastroCliente()"  />
        <input type="reset" class="btn btn-default" value="Limpar"
               ng-click="resetcadastroform()" />
    	</div>
    </div>

 </div>
 
    
    

</fieldset>
   
    <br />



	 <div class="form-group">
          <label for="name" class="col-sm-3 control-label"></label>
              <div class="col-sm-6">
                  <font color="red"> {{messageErroCadastro}}</font>
                   <font color="green"> {{messageSucessoCadastro}}</font>
      		  </div>
     </div> 		  
   

</form>

