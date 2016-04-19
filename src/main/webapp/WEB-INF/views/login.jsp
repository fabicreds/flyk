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
<head>
<title>FLYK</title>
</head>
<header>
	<div class="container-fluid"
		style="background-color: #68248a; box-shadow: 0 0 30px black, inset 0 -10px 30px rgba(0, 0, 0, 0.21);">
		<div class="container">
			<div class="row" style="padding-top: 10px; padding-bottom: 10px;">
				<div class="col-lg-1">&nbsp;</div>
				<div class="col-lg-6" style="color: white">
					<img src="${pageContext.request.contextPath}/images/FLYK.png"
						style="width: 44px; height: 50px;"> <img
						src="${pageContext.request.contextPath}/images/FLYK-TITLE.png"
						style="width: 62px; height: 30px">
				</div>
				<form action="/FLYK-TCC/login" method="POST">
					<div class="col-lg-5" style="align: right;">

						<table class="table-header">

							<tr style="padding-left: 10px">
								<td>Login:</td>
								<td><input type="text" class="input-login" name="usuario"></td>
								<td>Senha:</td>
								<td><input type="password" class="input-login" name="senha"></td>
								<td><button class="btn btn-default">>></button>
							</tr>
							<tr>
								<td colspan="2" />
								<td colspan="2" style="text-align: right;">
									<input type="submit" class="link-header" value="Esqueceu a senha?" formaction="/FLYK-TCC/resgatarSenha"/>
								</td>
							</tr>
							<tr>
								<td colspan="4" style="text-align: right;">
									<font color="red" style="font-size: 12px;">${errorMessage }</font>
								</td>
							</tr>
						</table>

					</div>
				</form>
			</div>
		</div>
	</div>
</header>
<body class="body-login" >
	<div class="container-fluid"
		style="background-color: #e6e6e6; box-shadow: 0 0 30px black, inset 0 10px 30px rgba(0, 0, 0, 0.4);height: 100%">
		<div class="container">
			teste<br> teste<br> teste<br>
		</div>
	</div>

</body>
</html>