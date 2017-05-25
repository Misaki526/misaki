<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<c:set var="proPath" value="${pageContext.request.contextPath}" />
<link href="${proPath }/css/bootstrap.min.css" rel="stylesheet">
<link href="${proPath }/css/datepicker3.css" rel="stylesheet">
<link href="${proPath }/css/bootstrap-table.css" rel="stylesheet">
<link href="${proPath }/css/styles.css" rel="stylesheet">
<script src="${proPath }/js/jquery-1.11.1.min.js"></script>

<!--[if lt IE 9]>
<script src="${proPath }/js/html5shiv.js"></script>
<script src="${proPath }/js/respond.min.js"></script>
<![endif]-->

</head>

<body>
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">	
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
				<li class="active">保姆信息管理</li>
			</ol>
		</div><!--/.row-->
		
		<div class="row">
		
			<div class="col-lg-12">
				<h1 class="page-header">Nurse Manage</h1>
				
			</div>
		</div><!--/.row-->
				
		
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">Nurse Manage</div>
					<div class="panel-body">
					
					<div id="toolbar" class="btn-group">
				 		<button id="btn_add" type="button" class="btn btn-default">
				 			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
				 		</button>
				 		<button id="btn_edit" type="button" class="btn btn-default">
				 			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
				 		</button>
				 		<button id="btn_delete" type="button" class="btn btn-default">
				 			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
				 		</button>
				 		<button id="btn_synchro" type="button" class="btn btn-default">
				 			<span class="glyphicon glyphicon-cloud-upload" aria-hidden="true"></span>同步Solr
				 		</button>
				 	</div>
						
					<table id="nurse_table" data-toggle="table" data-url="" data-show-refresh="true"
						 data-show-toggle="true" data-show-columns="true" data-toolbar="#toolbar" 
						 data-pagination="true" data-row-style="rowStyle">
					    <thead>
					    <tr>
					        <th data-field="id" data-checkbox="true">ID</th>
					        <th data-field="nurseId" data-sortable="true" data-visible="false">ID</th>
					        <th data-field="nurseName" data-sortable="true">姓名</th>
					        <th data-field="nurseSex" data-sortable="true">性别</th>
					        <th data-field="nurseSalary" data-sortable="true">薪水</th>
					        <th data-field="nurseMobile" data-sortable="true">手机号码</th>
					        <th data-field="nurseScore" data-sortable="true">评分</th>
					        <th data-field="nurseEducation" data-sortable="true">教育水平</th>
					        <th data-field="nurseBirthday" data-sortable="true">出生日期</th>
					        <th data-field="nurseExperience" data-sortable="true">工作经验</th>
					        <th data-field="nurseArea" data-sortable="true">所属区域</th>
					    </tr>
					    </thead>
					</table>
						
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">            
		      function initTable() {  
				 // 先销毁表格  
				 $('#nurse_table').bootstrapTable('destroy');
				 
				 // 初始化表格, 动态从服务器加载数据  
				 $("#nurse_table").bootstrapTable({  
				     method: "get",
				     url: "${proPath }/ls/nurse/selectPage.action",
				     pagination: true, 
				     pageSize: 10,
				     pageNumber:1,
				     pageList: [5, 10, 15, 20, 25],
				     search: true,  // 是否启用查询  
				     showColumns: true,  // 显示下拉框勾选要显示的列  
				     showRefresh: true,  // 显示刷新按钮  
				     sidePagination: "server", // 表示服务端请求  
				     
				     queryParamsType : "undefined",   
				     queryParams: function queryParams(params) {   // 设置查询参数  
				       	var param = {    
				     		pageNumber: params.pageNumber,    
				    		pageSize: params.pageSize,
				       	};    
				       	return param;                   
				     },  
				     onLoadSuccess: function(){
				     },  
				     onLoadError: function(){
				     }  
				});
		      }  
		  
		      $(document).ready(function() {          
		          initTable();
		      });  
		</script>
		
		<script>
		    function rowStyle(row, index) {
		        if (index % 2 === 0) {
		            return {
		                classes: 'active'
		            };
		        }
		        return {};
		    }
		    
		    $("#btn_add").click(function() {
		    	var text = "${proPath }/base/goURL/ls/nurse/addUI.action";
		    	parent.$('#win').modal({
		    		backdrop: 'static', 
		            keyboard: false, 
		    		remote : text,
		    	});
		    });
		    
		    $("#btn_edit").click(function() {
				var selectContent = $("#nurse_table").bootstrapTable('getSelections'); 
		    	
		        if (selectContent.length != 1) { 
			   		alert("请选中一条数据");
			    	return false; 
		        } else { 
		        	var text = "${proPath }/base/goURL/ls/nurse/updateUI.action";
			    	parent.$('#win').modal({
			    		backdrop: 'static', 
			            keyboard: false, 
			    		remote : text,
			    	});
		        }
		    });
		    
		    $("#btn_delete").click(function() {
				var selectContent = $("#nurse_table").bootstrapTable('getSelections'); 
		    	
		        if (selectContent.length == 0) {
			   		alert("请选择要删除的数据");
			    	return false;
		        } else { 
		        	// 定义数组，存放选中记录的id
		        	var ids = new Array();
		        	for (var i = 0; i < selectContent.length; i++) {
		        		ids[i] = selectContent[i].nurseId;
		        	}
		        	
		        	// 锁定整个页面，需要加parent
		        	var ret = parent.confirm("确认删除？");
		        	if (ret) {
		        		$.ajax({
		        			url: "${proPath}/ls/nurse/deleteByPks.action", 
		        			type: "POST", 
		        			traditional: true, 
		        			data: {pks : ids}, 
		        			success: function(html) {
								// 重新刷新页面
								$("#nurse_table").bootstrapTable("refresh", null);
								// 请除所有勾选的行
								$("#nurse_table").bootstrapTable("uncheckAll");
							}, 
							error: function (XMLHttpRequest, textStatus, errorThrown) {
								alert('删除错误','请联系管理员！','error');
							}, 
							dataType:'json'
						});

		        	}
		        }
		    });
		    
		    $("#btn_synchro").click(function() {
	        	var ret = parent.confirm("确认同步到Solr？");
	        	if (ret) {
	        		$.ajax({
	        			url: "${proPath}/ls/nurse/synchroSolr.action", 
	        			type: "POST", 
	        			traditional: true, 
	        			success: function(html) {
							alert("同步成功");
						}, 
						error: function (XMLHttpRequest, textStatus, errorThrown) {
							alert('同步错误','请联系管理员！','error');
						}, 
						dataType:'json'
					});

	        	}
		    });
		</script>
	</div>
	
	<script src="${proPath }/js/bootstrap.min.js"></script>
	<script src="${proPath }/js/bootstrap-datepicker.js"></script>
	<script src="${proPath }/js/bootstrap-table.js"></script>
	<script src="${proPath }/js/chart.min.js"></script>
	<script src="${proPath }/js/chart-data.js"></script>
	<script src="${proPath }/js/easypiechart.js"></script>
	<script src="${proPath }/js/easypiechart-data.js"></script>
</body>
</html>