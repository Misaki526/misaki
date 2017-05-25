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
          <h4 class="modal-title" id="myModalLabel">添加FAQ</h4>
      </div>
      
      <div class="modal-body">
      	  <div class="form-group">
			    <label>FAQ_Title</label>
			    <input id="faqTitle" class="form-control" placeholder="输入问题">
	      </div>
	      <div class="form-group">
			    <label>FAQ_Answer</label>
			    <input id="faqAnswer" class="form-control" placeholder="输入答案">
	      </div>
	      <div class="form-group">
			    <label>FAQ_Type</label>
			    <input id="faqType" class="form-control" placeholder="输入问题类型">
	      </div>
      </div>
      
      <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          <button id="btn" type="button" class="btn btn-primary" data-dismiss="modal">添加</button>
      </div>
    </form>
  	</body>
  	<script type="text/javascript">
		$(function() {
			// 返回iframe页面文档(window)，即listUI界面
			var win = parent.$("iframe[id='faqFrame']").get(0).contentWindow;

			$("#btn").click(function() {
				var faqTitle = $("#faqTitle").val();
				var faqAnswer = $("#faqAnswer").val();
				var faqType = $("#faqType").val();
				$.ajax({
	  				url: "${proPath}/qa/faq/insert.action", 
	  				type: "POST", 
	  				traditional: true, 
	  				data: {faqTitle:faqTitle, faqAnswer:faqAnswer, faqType:faqType}, 
	  				success: function(html) {
	      				win.$("#faq_table").bootstrapTable("refresh", null);
					}, 
					dataType:'json'
				});
 
			});
  		});
	</script>
</html>