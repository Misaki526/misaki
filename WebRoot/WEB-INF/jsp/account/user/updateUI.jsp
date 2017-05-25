<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="proPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
  
  	<body>
  	<form id="ff" method="post">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
          <h4 class="modal-title" id="myModalLabel">修改用户</h4>
      </div>
      
      <div class="modal-body">
      	  <div class="form-group">
			    <label>用户ID</label>
			    <input id="userId" class="form-control" readonly="readonly">
	      </div>
      	  <div class="form-group">
			    <label>用户账号</label>
			    <input id="userAccount" class="form-control" placeholder="输入用户账号">
	      </div>
	      <div class="form-group">
			    <label>用户姓名</label>
			    <input id="userName" class="form-control" placeholder="输入用户姓名">
	      </div>
	      <div class="form-group">
			    <label>用户密码</label>
			    <input id="userPass" class="form-control" placeholder="输入用户密码">
	      </div>
      </div>
      
      <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          <button id="btn" type="button" class="btn btn-primary" data-dismiss="modal">提交更改</button>
      </div>
    </form>
  	</body>
  	<script type="text/javascript">
		$(function() {
			// 返回iframe页面文档(window)，即listUI界面
			var win = parent.$("iframe[id='userFrame']").get(0).contentWindow;
			
			// 加载被选中的信息
			var selectContent = win.$("#user_table").bootstrapTable('getSelections'); 
			$("#userId").val(selectContent[0].userId);
			$("#userAccount").val(selectContent[0].userAccount);
			$("#userName").val(selectContent[0].userName);
			$("#userPass").val(selectContent[0].userPass);

			$("#btn").click(function() {
				var userId = $("#userId").val();
				var userAccount = $("#userAccount").val();
				var userName = $("#userName").val();
				var userPass = $("#userPass").val();
				$.ajax({
	  				url: "${proPath}/account/user/updateByPk.action", 
	  				type: "POST", 
	  				traditional: true, 
	  				data: {userId:userId, userAccount:userAccount, userName:userName, userPass:userPass}, 
	  				success: function(html) {
	      				win.$("#user_table").bootstrapTable("refresh", null);
	      				win.$("#user_table").bootstrapTable("uncheckAll");
					}, 
					dataType:'json'
				});
 
			});
  		});
	</script>
</html>