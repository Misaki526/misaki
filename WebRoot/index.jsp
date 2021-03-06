<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="common/common.jspf" %>
<html>
<head>
	<style type="text/css">
		body {
			background:url(images/bg.jpg);
			background-size:100% 100%
		}
	</style>
</head>
<body>
	<div style="padding: 100px 300px 10px;">
    <form class="bs-example bs-example-form" role="form">
        <div class="row">
            <div class="col-lg-12">
                <div class="input-group">
                    <input id="order" type="text" class="form-control" style="height:60px">
                    <span class="input-group-btn">
                        <button id="btn_sbt" class="btn btn-default" type="button" style="height:60px;background-color: #c6cbd4">Go!</button>
                    </span>
                </div>
            </div>
        </div>
    </form>
    
	<!-- 模态框 -->
	<div class="modal fade" id="replyId" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	        	  <div class="modal-header">
		              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		              <h4 class="modal-title" id="myModalLabel">The Reply：</h4>
			      </div>
			      <div class="modal-body">
			      	  <div class="form-group">
						    <label id="result" style="font-size: 18px"></label>
				      </div>
			      </div>
			      <div class="modal-footer">
			          <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			      </div>
	        </div>
	    </div>
	</div>
	
	<script type="text/javascript">
		// 防止数据只加载一次
		$("#replyId").on("hidden.bs.modal", function() { 
		    $(this).removeData("bs.modal");
		});
	
		$("#btn_sbt").click(function() {
			var order = $("#order").val();
    		$.ajax({
    			// url: "${proPath}/base/allocation.action", 
    			url: "${proPath}/qa/reasoning/findAnswer.action", 
    			type: "POST", 
    			traditional: true, 
    			data: {
    				order:order
  				},
    			success: function(html) {
					// 成功返回答案
					$("#result").html(html);
					
			    	$('#replyId').modal({
			    		backdrop: 'static', 
			            keyboard: true
			    	});
				},
				dataType:'json'
			});
	    });
	</script>
</div>
</body>
</html>
