
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<form class="form-horizontal" name="userForm" novalidate role="form">

    <fieldset>
        <legend>Cadastro - Novo cliente</legend>
        <div class="form-group" show-errors>
            <label for="fullName" class="col-sm-3 control-label">Nome</label>
            <div class="col-sm-6">
                <input type="text" id="fullName" name="fullName" class="form-control"
                       ng-model="user.fullName" ng-required="true" />
                <span class="help-block"
                      ng-if="userForm.fullName.$error.required">O nome é obrigatório!</span>
            </div>
        </div>

        <div class="form-group" show-errors>
            <label for="email" class="col-sm-3 control-label">Email</label>
            <div class="col-sm-6">
                <input type="email" id="email" name="email" class="form-control"
                       ng-model="user.email" ng-required="true" />
                <span class="help-block"
                      ng-if="userForm.email.$error">Um e-mail inválido foi digitado! </span>
            </div>
        </div>

        <div class="form-group" show-errors>
            <label for="fullName" class="col-sm-3 control-label">Apelido</label>
            <div class="col-sm-6">
                <input type="text" id="nickname" name="nickname" class="form-control"
                       ng-model="user.nickname" ng-required="true" />
                <span class="help-block"
                      ng-if="userForm.nickname.$error.required">O apelido é obrigatório!</span>
            </div>
        </div>

        <div class="form-group" show-errors>          
            <label for="password" class="col-sm-3 control-label">Senha</label>
            <div class="col-sm-3">
                <span uib-tooltip="A senha deve conter letra minuscula, maiuscula, numero e caracter especial."
                      uib-tooltip-placement="right"></span>
                <input type="password" id="password" name="password" class="form-control" ng-model="user.password"
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

        <div class="form-group" show-errors>
            <label for="confirmPassword" class="col-sm-3 control-label"> Confirme a senha</label>
            <div class="col-sm-3">
                <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" ng-model="user.confirmPassword"
                       ng-required="true" ng-minlength="5" ng-maxlength="10" ng-blur="validatePassword(user)" /> <!--Com o form-control, ele fica responsivel. Não use value para inserior o valor digitado. -->
            </div>
        </div>

        <div class="form-group" show-errors>
            <label for="CPF" class="col-sm-3 control-label">CPF</label>
            <div class="col-sm-3">
                <input type="text" id="CPF" name="CPF" class="form-control" ng-model="user.CPF" ng-required="true" ui-cpf/>
                <span class="help-block"
                      ng-if="userForm.CPF.$error.required">CPF é obrigatório!</span>
             </div>
        </div>

    
        <div class="form-group" show-errors>
            <label for="Date" class="col-sm-3 control-label">Data de nascimento</label>
            <div class="col-sm-3">
                <input type="text" id="date" name="date" class="form-control" ng-model="user.date" ui-date />
            </div>
            </div>

        <div class="form-group" show-errors>
            <label for="numberContact" class="col-sm-3 control-label">Telefone</label>
            <div class="col-sm-3">
                <input type="text" id="Telephone" name="telephone" class="form-control" ng-model="user.telephone" ui-telefone/>
           
            </div>
        </div>

        <div class="form-group">
            <label for="userType" class="col-sm-3 control-label">Tipo de cadastro</label>
            <div class="col-sm-9">

                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="userType" value="consumidor"
                               ng-model="user.userTypeConsumidor" /> Consumidor
                    </label><br />
                </div>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="userType" value="prestador"
                               ng-model="user.userTypePrestador" /> Prestador
                    </label><br />
                </div>
              

            </div>
        </div>

        <div class="form-group" uib-collapse="!user.userTypePrestador">
            <label for="nonCompeteNotes" class="col-sm-4 control-label">
               Tipo de serviço
            </label>
            <div class="col-sm-8">
                <div class="radio">
                    <label>
                        <input type="radio" name="serviceType" value="free"
                               ng-model="user.serviceType" /> Free
                    </label><br />
                </div>
                <div class="radio">
                    <label>
                        <input type="radio" name="serviceType" value="premium"
                               ng-model="user.serviceType" /> Premium
                    </label><br />
                </div>
            </div>
        </div>

        <div class="form-group" show-errors>
            <label for="address1" class="col-sm-3 control-label">Endereço</label>
            <div class="col-sm-2">
                <input type="text" id="addressPostalCode" name="addressPostalCode" class="form-control" ng-model="user.address.cep"
                       ng-blur="pesquisaCep(user.address.cep)" placeholder="CEP" /> <!--Popular a partir do banco de dados-->
            </div>
        </div>

        <div class="form-group" show-errors>
            <div class="col-sm-3">
                </div>
            <div class="col-sm-5">
                <input type="text" id="addressStreet" name="addressStreet" class="form-control" ng-model="user.address.logradouro" placeholder="Logradouro" />
            </div>
            <div class="col-sm-1">
                <input type="text" id="addressNumber" name="addressNumber" class="form-control" ng-model="user.address.numero" placeholder="Nº" />
            </div>
            <div class="col-sm-2">
                <input type="text" id="addressComp" name="addressComp" class="form-control" ng-model="user.address.comp" placeholder="Complemento" />
            </div>

            </div>
        <div class="form-group" show-errors>
            <div class="col-sm-3"></div>
            <div class="col-sm-3">
                <input type="text" name="addressBairro" id="bairro" class="form-control" ng-model="user.address.bairro" placeholder="Bairro" />
            </div>
            <div class="col-sm-2">
                <input type="text" name="addressCity" id="city" class="form-control" ng-model="user.address.cidade" placeholder="Cidade"/>
            </div>
            <div class="col-sm-1">
                <input type="text" name="addressState" id="state" class="form-control" ng-model="user.address.estado" placeholder="Estado"/>
                
            </div>
            <div class="col-sm-2">
                <input type="text" name="addressCountry" id="country" class="form-control" ng-model="user.address.country" placeholder="Pais" />


            </div>
        </div>

      
           
        <div class="form-group">
            <div class="col-sm-3">
            </div>
            <div class="col-sm-3">
                <label for="info">* Campos obrigatórios</label>
            </div>
        </div>



</fieldset>
   
    <br />




    <div class="col-sm-offset-3 col-sm-9">
        <input type="button" class="btn btn-default" value="Cancel"
               ng-click="cancelForm()" />
        <input type="submit" class="btn btn-primary" value="Submit"
               ng-click="submitForm()"  />
        <input type="reset" class="btn btn-warning" value="Reset"
               ng-click="resetForm()" />
    </div>

    <fieldset>
        <legend>Created by </legend>
    </fieldset>

</form>
