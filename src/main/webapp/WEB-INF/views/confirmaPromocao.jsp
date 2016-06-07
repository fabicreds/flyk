<legend>Promoção </legend>
<meta charset="utf-8"/>
<div data-ng-init="init()"></div>
<div class=" col-md-12 " >
   <div class="panel  panel-default">
      <div class="panel-heading head-promo">Confirme o cadastro da promoção</div>
      <div class="panel-body">
         <form class="form-horizontal" name="confirmaProm"  ng-submit="cadProm()">
            <div class="form-group col-md-8" >
               <label for="nomepromocao" class="control-label">Nome da promoção</label>
               <input type="text" name="nomeprom" ng-model="nomepromo"  class="form-control" >
            </div>
            <div ng-bind="nome">{{nome}}</div>
            <div class="form-group col-md-8">
               <label for="descricao" class="control-label">Descricão da promocão</label>
               <div class="">                
                  <input type="text" name="descrprom" ng-model="descrprom" class="form-control">
               </div>
            </div>
            <!--<div class="form-group col-md-8">
               <label for="categoria" class="control-label">Categoria do serviço</label>
               <select name="categoriaservico" class="form-control" >
                  <option value={{catPromo}}>{{catPromo}}</option>
               </select>
            </div>-->
            
              <div class="form-group col-md-8 form-inline" >
               <label for="categoria" class="control-label">Valor por categoria do serviço</label>
               <fieldset  data-ng-repeat="categoria in listaCategorias">
                  <select ng-model="categoria.nome" class="form-control" style="margin-top:15px;">
                     <option ng-repeat="nomeCat in categorias track by $index" value="{{nomeCat}}">{{nomeCat}}</option>
                  </select>
                  
               
                  <div class="input-group" style="margin-top:15px;">
                     <div class="input-group-addon">$</div>
                      <input type="text" class="form-control" ng-model="categoria.valorpromocional" 	 name="" placeholder="Valor promocional">
                     
                  </div>
                   
                  
                  
                  <button class="btn" ng-show="$last" style="margin-top:15px"  ng-click="removeCategoria()">-</button>
               </fieldset>
                <!-- <button class="btn" ng-click="addNewCategoria()">+</button> 
               <div id="choicesDisplay">
                  {{ choices }}
               </div>-->
            </div>
            
            
            <div class="row">
               <div class="col-md-6">
                  <label for="dataini" class="control-label ">Data início</label>
                  <p class="input-group">
                     <input type="text" class="form-control"  uib-datepicker-popup="{{format}}" ng-model="dtini" is-open="popup1.opened" datepicker-options="dateOptions"  ng-required="true" close-text="Close" 
                        current-text="Hoje" clear-text="Limpar" close-text="Sair" />
                     <span class="input-group-btn">
                     <button type="button" class="btn btn-default" ng-click="open1()"><i class="glyphicon glyphicon-calendar"  style="line-height: -1 !important;"></i></button>
                     </span>
                  </p>
               </div>
               <div class="col-md-6">
                  <label for="dataini" class="control-label">Data fim</label>
                  <p class="input-group">
                     <input type="text" class="form-control" uib-datepicker-popup="{{format}}" ng-model="dtfim" is-open="popup2.opened" datepicker-options="dateOptions" ng-required="true" close-text="Fechar" />
                     <span class="input-group-btn">
                     <button type="button" class="btn btn-default" ng-click="open2()"><i class="glyphicon glyphicon-calendar"></i></button>
                     </span>
                  </p>
               </div>
            </div>
           
          <!--  
            <div class="form-group col-md-8">
               <label for="valorprom" class="control-label">Valor promocional</label>
               <div class="">
                  <div class="input-group">
                     <div class="input-group-addon">$</div>
                     <input type="text" class="form-control"name="valorpromocional"
                        placeholder="Valor promocional" ng-model="valorpromo">
                  </div>
               </div>
            </div>-->
            <div class="form-group col-md-12">
               <input type="submit" class="btn-confirmar btn" value="Confirmar"  ng-disabled="promForm.$invalid"/>
            </div>
            </p>
      </div>
      </form>
   </div>
   <div class="alert alert-success" role="alert" ng-show="msg">
      <p>{{mensagem.msgSucesso}}</p>
   </div>
</div>
