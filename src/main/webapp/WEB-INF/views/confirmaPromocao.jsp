<legend>Confirmação da promoçãoo</legend>
<meta charset="utf-8"/>

<div class=" col-md-6 ">
<div class="well panel  panel-success">
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
					
			<div class="form-group col-md-8">
			
				<label for="categoria" class="control-label">Categoria do serviço</label>
				              
				              
					<select name="categoriaservico" class="form-control" >
						
						<option value={{catPromo}}>{{catPromo}}</option>
					
					</select>
		

				
				
					
			</div>	

    
    <div class="row">
      <div class="col-md-6">
      <label for="dataini" class="control-label">Data fim</label>
        <p class="input-group">
          <input type="text" class="form-control" uib-datepicker-popup="{{format}}" ng-model="dtini" is-open="popup1.opened" datepicker-options="dateOptions"  ng-required="true" close-text="Close" 
          current-text="Hoje" clear-text="Limpar" close-text="Sair" />
          <span class="input-group-btn">
            <button type="button" class="btn btn-default" ng-click="open1()"><i class="glyphicon glyphicon-calendar"></i></button>
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
    
    <!--  <button type="button" class="btn btn-sm btn-danger" ng-click="clear()">Clear</button>-->



    
		  	
			<div class="form-group col-md-8">
				<label for="valorprom" class="control-label">Valor promocional</label>
				<div class="">                
					<div class="input-group">
								<div class="input-group-addon">$</div>
								<input type="text" class="form-control"name="valorpromocional"
									placeholder="Valor promocional" ng-model="valorpromo">
								<div class="input-group-addon">.00</div>
							</div>
					
				</div>
				</div>
					<div class="form-group col-md-12">
						<input type="submit" class="btn btn-default" value="Confirmar" style="background-color:#005E2F; color:white" ng-disabled="promForm.$invalid"/>
				</div>
			
		        </p>
    </div>
		
			
		
				 
			
			</form>
 
  </div>
  
<div class="alert alert-success" role="alert" ng-show="msg">
  <p>{{mensagem.msgSucesso}}</p>
</div>  
</div>
</div>

