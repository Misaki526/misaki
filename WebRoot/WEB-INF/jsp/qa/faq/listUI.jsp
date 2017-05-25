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
				<li class="active">FAQ管理</li>
			</ol>
		</div><!--/.row-->
		
		<div class="row">
		
			<div class="col-lg-12">
				<h1 class="page-header">Frequently Asked Questions</h1>
				
			</div>
		</div><!--/.row-->
				
		
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">Frequently Asked Questions</div>
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
				 		<!-- <button id="btn_import" type="button" class="btn btn-default">
				 			<span class="glyphicon glyphicon-import" aria-hidden="true"></span>导入
				 		</button>
				 		<button id="btn_export" type="button" class="btn btn-default">
				 			<span class="glyphicon glyphicon-export" aria-hidden="true"></span>导出
				 		</button> -->
				 		<button id="btn_synchro" type="button" class="btn btn-default">
				 			<span class="glyphicon glyphicon-cloud-upload" aria-hidden="true"></span>同步Solr
				 		</button>
				 	</div>
						
					<table id="faq_table" data-toggle="table" data-url="" data-show-refresh="true"
						 data-show-toggle="true" data-show-columns="true" data-toolbar="#toolbar" 
						 data-pagination="true" data-row-style="rowStyle">
					    <thead>
					    <tr>
					        <th data-field="id" data-checkbox="true">ID</th>
					        <th data-field="faqId" data-sortable="true" data-visible="false">FAQ ID</th>
					        <th data-field="faqTitle"  data-sortable="true">FAQ Title</th>
					        <th data-field="faqAnswer" data-sortable="true">FAQ Answer</th>
					        <th data-field="faqType" data-sortable="true">FAQ Type</th>
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
				 $('#faq_table').bootstrapTable('destroy');
				 
				 // 初始化表格, 动态从服务器加载数据  
				 $("#faq_table").bootstrapTable({  
				     method: "get",
				     url: "${proPath }/qa/faq/selectPage.action",
				     pagination: true, 
				     pageSize: 10,
				     pageNumber:1,
				     pageList: [5, 10, 15, 20, 25],
				     search: true,  // 是否启用查询  
				     showColumns: true,  // 显示下拉框勾选要显示的列  
				     showRefresh: true,  // 显示刷新按钮  
				     sidePagination: "server", // 表示服务端请求  
				     
				     // 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder  
				     // 设置为limit可以获取limit, offset, search, sort, order  
				     queryParamsType : "undefined",   
				     queryParams: function queryParams(params) {   // 设置查询参数  
				       	var param = {    
				     		pageNumber: params.pageNumber,    
				    		pageSize: params.pageSize,
				         	// orderNum : $("#orderNum").val()  
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
		    	var text = "${proPath }/base/goURL/qa/faq/addUI.action";
		    	parent.$('#win').modal({
		    		backdrop: 'static', 
		            keyboard: false, 
		    		remote : text,
		    	});
		    });
		    
		    $("#btn_edit").click(function() {
				var selectContent = $("#faq_table").bootstrapTable('getSelections'); 
		    	
		        if (selectContent.length != 1) { 
			   		alert("请选中一条数据");
			    	return false; 
		        } else { 
		        	var text = "${proPath }/base/goURL/qa/faq/updateUI.action";
			    	parent.$('#win').modal({
			    		backdrop: 'static', 
			            keyboard: false, 
			    		remote : text,
			    	});
		        }
		    });
		    
		    $("#btn_delete").click(function() {
				var selectContent = $("#faq_table").bootstrapTable('getSelections'); 
		    	
		        if (selectContent.length == 0) {
			   		alert("请选择要删除的数据");
			    	return false;
		        } else { 
		        	// 定义数组，存放选中记录的id
		        	var ids = new Array();
		        	for (var i = 0; i < selectContent.length; i++) {
		        		ids[i] = selectContent[i].faqId;
		        	}
		        	
		        	// 锁定整个页面，需要加parent
		        	var ret = parent.confirm("确认删除？");
		        	if (ret) {
		        		$.ajax({
		        			url: "${proPath}/qa/faq/deleteByPks.action", 
		        			type: "POST", 
		        			traditional: true, 
		        			data: {pks : ids}, 
		        			success: function(html) {
								// 重新刷新页面
								$("#faq_table").bootstrapTable("refresh", null);
								// 请除所有勾选的行
								$("#faq_table").bootstrapTable("uncheckAll");
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
	        			url: "${proPath}/qa/faq/synchroSolr.action", 
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