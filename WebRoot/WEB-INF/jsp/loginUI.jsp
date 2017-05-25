<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jspf"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>misaki</title>
</head>

<body>
	
	<div class="row">
		<div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-4">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">Log in</div>
				<div class="panel-body">
					<form role="form" action="${proPath }/admin/login.action" method="POST">
						<fieldset>
							<div class="form-group">
								<input class="form-control" placeholder="用户名" name="userAccount" type="text" autofocus="">
							</div>
							<div class="form-group">
								<input class="form-control" placeholder="密码" name="userPass" type="password">
							</div>
							<div class="form-group">
								<label style="color:red">${requestScope.msg }</label>
							</div>
							<input class="btn btn-primary" type="submit" value="Login" />
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		
	</script>	
</body>

</html>