<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/FLYK.png">
    <script src="${pageContext.request.contextPath}/resources/js/jquery-1.12.3.min.js"></script>
	        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
    

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastrar promoção</title>
</head>
<body>

	<form role="form" class="form-vertical" action="/FLYK-TCC/promocao"	method="POST">


		<div class="form-group">

			<label class="control-label">Nome da promoção:</label> 
			<input type="text" name="nome" class="form-control">
		</div>
		<div class="form-group">
			<label class="control-label">Valor normal: <input
				type="text" name="valorNormal" class="form-control">
			
			</label>
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
	</form>
	
	
	

	</div>
</body>
</html>