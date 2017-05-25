<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/common.jspf"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Misaki</title>
</head>

<body style="padding:0px;">
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"><span>Misaki</span>Admin</a>
				<ul class="user-menu">
					<li class="dropdown pull-right">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> ${sessionScope.user.userAccount} <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div><!-- /.container-fluid -->
	</nav>
	
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
		<form role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			</div>
		</form>
		<ul class="nav menu">
			<li class="parent active">
				<a href="#">
					<span class="glyphicon glyphicon-user"></span> 用户管理 <span data-toggle="collapse" data-target="#sub-item-1" class="icon pull-right collapsed"><em class="glyphicon glyphicon-s glyphicon-plus"></em></span> 
				</a>
				<ul class="children collapse" id="sub-item-1">
					<li>
						<a title="userFrame" href="${proPath }/base/goURL/account/user/listUI.action">
							<span class="glyphicon glyphicon-share-alt"></span> 个人信息管理
						</a>
					</li>
					<li>
						<a href="#">
							<span class="glyphicon glyphicon-share-alt"></span> 角色管理
						</a>
					</li>
				</ul>
			</li>
			
			<li class="parent">
				<a href="#">
					<span class="glyphicon glyphicon-list"></span> 问答系统 <span data-toggle="collapse" data-target="#sub-item-2" class="icon pull-right collapsed"><em class="glyphicon glyphicon-s glyphicon-plus"></em></span> 
				</a>
				<ul class="children collapse" id="sub-item-2">
					<li>
						<a title="faqFrame" href="${proPath }/base/goURL/qa/faq/listUI.action">
							<span class="glyphicon glyphicon-share-alt"></span> FAQ 管理
						</a>
					</li>
					<li>
						<a href="#">
							<span class="glyphicon glyphicon-share-alt"></span> 分类模型
						</a>
					</li>
				</ul>
			</li>

			<li class="parent">
				<a href="#">
					<span class="glyphicon glyphicon-dashboard"></span> 生活服务<span data-toggle="collapse" data-target="#sub-item-3" class="icon pull-right collapsed"><em class="glyphicon glyphicon-s glyphicon-plus"></em></span> 
				</a>
				<ul class="children collapse" id="sub-item-3">
					<li>
						<a title="nurseFrame" href="${proPath }/base/goURL/ls/nurse/listUI.action">
							<span class="glyphicon glyphicon-share-alt"></span> 保姆信息管理
						</a>
					</li>
					<li>
						<a href="#">
							<span class="glyphicon glyphicon-share-alt"></span> 餐馆信息管理
						</a>
					</li>
				</ul>
			</li>
						
			<li><a href="#"><span class="glyphicon glyphicon-th"></span> Widgets</a></li>
			<li><a href="#"><span class="glyphicon glyphicon-stats"></span> Charts</a></li>
			<li><a href="#"><span class="glyphicon glyphicon-list-alt"></span> Tables</a></li>
			<!-- <li class="active"><a href="#"><span class="glyphicon glyphicon-pencil"></span> Index</a></li> -->
			<li><a href="#"><span class="glyphicon glyphicon-info-sign"></span> Alerts &amp; Panels</a></li>
			
		</ul>
	</div>
	
	<div id="main">
	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
				<li class="active">Welcome</li>
			</ol>
		</div>
		
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Welcome</h1>
			</div>
		</div>
	</div>
	</div>
	
	<!-- 模态框 -->
	<div class="modal fade" id="win" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	        	
	        </div>
	    </div>
	</div>
	
	<script>
		!function ($) {
			$(document).on("click","ul.nav li.parent > a > span.icon", function(){		  
				$(this).find('em:first').toggleClass("glyphicon-minus");
				
				$(".parent").attr("class", "parent");
				$(this).parent().parent().addClass("active");
			}); 
			$(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		}(window.jQuery);

		$(window).on('resize', function () {
		  	if ($(window).width() > 768) $('#sidebar-collapse').collapse('show');
		});
		$(window).on('resize', function () {
		  	if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide');
		});
		
		// 防止数据只加载一次
		$("#win").on("hidden.bs.modal", function() { 
    	    $(this).removeData("bs.modal");
    	});
	</script>
	
	<script type="text/javascript">	
	$(function() {
		var timer = null;
		
		$("a[title]").click(function() {
		   	var text = this.href;
			
			$("#main").html("<iframe id='" + this.title + "' src='" + text + "' height='100%' width='100%' frameborder='0px' scrolling='no'></iframe>");
			$("#" + this.title).load(function() {
			    /* var mainheight = $(this).contents().find(".main").height() + 500;
			    $(this).height(mainheight); */
			    
				if (timer) {
		            clearInterval(timer);
		        }
		        // pre_height用于记录上次检查时body的高度
		        // mainheight用于获取本次检查时body的高度，并赋予iframe的高度
		        var mainheight, pre_height = null;
		        var frame_content = $(this);
		        timer = setInterval(function() {
		            mainheight = frame_content.contents().find(".main").height() + 30;
		            if (mainheight != pre_height){
		                pre_height = mainheight;
		                frame_content.height(Math.max(mainheight,350));
		             }
		        }, 500); // 每0.5秒检查一次
			});

			return false; // attention:防止打开新的页面
		});
		
	});
	</script>
</body>

</html>