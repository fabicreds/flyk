
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/resources/css/app.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/resources/angular/angular-ui/ui-bootstrap-tpls.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/angular/angular-route.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/angular/app/app.js"></script>
    <script src="${pageContext.request.contextPath}/resources/angular/app/controllers/modal/modalConfirm.js"></script>
    <script src="${pageContext.request.contextPath}/resources/angular/app/controllers/cadastro.js"></script>
    <script src="${pageContext.request.contextPath}/resources/angular/app/controllers/adminPage.js"></script>
    <script src="${pageContext.request.contextPath}/resources/angular/app/directives/ValidationDirective.js"></script>
    <script src="${pageContext.request.contextPath}/resources/angular/app/directives/uiCPFdirective.js"></script>
    <script src="${pageContext.request.contextPath}/resources/angular/app/directives/uiDateDirective.js"></script>
    <script src="${pageContext.request.contextPath}/resources/angular/app/directives/uiTelefoneDirective.js"></script>
    <script src="${pageContext.request.contextPath}/resources/angular/app/directives/uiCEPDirective.js"></script>
    <script src="${pageContext.request.contextPath}/resources/angular/app/directives/fileReaderDirective.js"></script>
  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastrar promoção</title>
</head>
<body>

	<form:form  modelAttribute="formPromocao" class="form-vertical" action="/FLYK-TCC/promocao"	 method="POST">
		<div class="form-group">
			<label class="control-label">Nome da promoção:</label> 
			<input type="text" name="nome" class="form-control">
		</div>

		<div class="form-group">
			<label class="control-label">Descrição da promoção:
				<input type="text" name="descricao" class="form-control">
				
		    <div class="form-group">
            <label class="control-label">Categoria do serviço</label>
  			<select name="categoriaservico">
  			<c:forEach items="${catList}" var="categList">
   				 <option value="${categList}">${categList}</option>
  				</c:forEach>
				</select>
        </div>
		


			
							
				<div class="form-group">
					<label class="control-label">Data de validade: <input type="text" name="dataVal" class="form-control">
					</p> <label for="valorPromocional">Valor
							promocional</label>
						<div class="input-group">
							<div class="input-group-addon">$</div>
							<input type="text" class="form-control" name="valorPromocional"
								placeholder="Valor promocional">
							<div class="input-group-addon">.00</div>
						</div>
						<br>
						<button type="submit" class="btn btn-primary">Salvar</button>
				</div>
	</form:form>
	
	
	
	${formPromocao.descricao}
	</div>
</body>
</html>