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
          <h4 class="modal-title" id="myModalLabel">修改保姆</h4>
      </div>
      
      <div class="modal-body">
      	  <div class="form-group">
			    <label>ID</label>
			    <input id="nurseId" class="form-control" readonly="readonly">
	      </div>
      	  <div class="form-group">
			    <label>姓名</label>
			    <input id="nurseName" class="form-control" placeholder="输入姓名">
	      </div>
	      <div class="form-group">
			    <label>性别</label>
			    <input id="nurseSex" class="form-control" placeholder="输入性别">
	      </div>
	      <div class="form-group">
			    <label>薪水</label>
			    <input id="nurseSalary" class="form-control" placeholder="输入薪水">
	      </div>
	      <div class="form-group">
			    <label>手机号码</label>
			    <input id="nurseMobile" class="form-control" placeholder="输入手机号码">
	      </div>
	      <div class="form-group">
			    <label>评分</label>
			    <input id="nurseScore" class="form-control" placeholder="输入评分">
	      </div>
	      <div class="form-group">
			    <label>教育水平</label>
			    <input id="nurseEducation" class="form-control" placeholder="输入教育水平">
	      </div>
	      <div class="form-group">
			    <label>出生日期</label>
			    <input id="nurseBirthday" class="form-control" placeholder="输入出生日期">
	      </div>
	      <div class="form-group">
			    <label>工作经验</label>
			    <input id="nurseExperience" class="form-control" placeholder="输入工作经验">
	      </div>
	      <div class="form-group">
			    <label>所属区域</label>
			    <input id="nurseArea" class="form-control" placeholder="输入所属区域">
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
			var win = parent.$("iframe[id='nurseFrame']").get(0).contentWindow;
			
			// 加载被选中的信息
			var selectContent = win.$("#nurse_table").bootstrapTable('getSelections'); 
			$("#nurseId").val(selectContent[0].nurseId);
			$("#nurseName").val(selectContent[0].nurseName);
			$("#nurseSex").val(selectContent[0].nurseSex);
			$("#nurseSalary").val(selectContent[0].nurseSalary);
			$("#nurseMobile").val(selectContent[0].nurseMobile);
			$("#nurseScore").val(selectContent[0].nurseScore);
			$("#nurseEducation").val(selectContent[0].nurseEducation);
			$("#nurseBirthday").val(selectContent[0].nurseBirthday);
			$("#nurseExperience").val(selectContent[0].nurseExperience);
			$("#nurseArea").val(selectContent[0].nurseArea);

			$("#btn").click(function() {
				var nurseId = $("#nurseId").val();
				var nurseName = $("#nurseName").val();
				var nurseSex = $("#nurseSex").val();
				var nurseSalary = $("#nurseSalary").val();
				var nurseMobile = $("#nurseMobile").val();
				var nurseScore = $("#nurseScore").val();
				var nurseEducation = $("#nurseEducation").val();
				var nurseBirthday = $("#nurseBirthday").val();
				var nurseExperience = $("#nurseExperience").val();
				var nurseArea = $("#nurseArea").val();
				$.ajax({
	  				url: "${proPath}/ls/nurse/updateByPk.action", 
	  				type: "POST", 
	  				traditional: true, 
	  				data: {
	  					nurseName:nurseName, nurseSalary:nurseSalary, nurseMobile:nurseMobile, 
	  					nurseScore:nurseScore, nurseEducation:nurseEducation, nurseBirthday:nurseBirthday, 
	  					nurseExperience:nurseExperience, nurseArea:nurseArea, nurseId:nurseId, nurseSex:nurseSex
	  				}, 
	  				success: function(html) {
	      				win.$("#nurse_table").bootstrapTable("refresh", null);
	      				win.$("#nurse_table").bootstrapTable("uncheckAll");
					}, 
					dataType:'json'
				});
 
			});
  		});
	</script>
</html>