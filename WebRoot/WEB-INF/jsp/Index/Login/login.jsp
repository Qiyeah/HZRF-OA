<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="renderer" content="ie-comp" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta name="renderer" content="ie-stand" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<base href="<%=basePath %>" />
<title>${system_title }</title>
<link rel="icon" type="image/x-icon" href="<%=basePath %>images/favicon.ico" />
<link rel="shortcut icon" type="image/x-icon" href="<%=basePath %>images/bitbug_favicon.ico" />
<link type="text/css" rel="stylesheet" href="<%=basePath %>css/login.css" />
<style>
.loginbody {
	background: url(<%=basePath %>images/login.png) no-repeat center center;
	width: 100%;
	height: 585px;
	overflow: hidden;
	position: relative;
}

.loginbox {
	width: 753px;
	height: 585px;
	position: aboslute;
}

.loginuser {
	width: 299px;
	height: 44px;
	background: url(<%=basePath %>images/loginuser.png) no-repeat;
	border: none;
	line-height: 44px;
	padding-left: 44px;
	font-size: 16px;
	font-weight: bold;
}

.loginpwd {
	width: 299px;
	height: 44px;
	background: url(<%=basePath %>images/loginpassword.png) no-repeat;
	border: none;
	line-height: 44px;
	padding-left: 44px;
	font-size: 16px;
	color: #90a2bc;
}

.loginbtn {
	width: 111px;
	height: 35px;
	background: url(<%=basePath %>images/buttonbg.png) repeat-x;
	font-size: 16px;
	font-weight: bold;
	text-align:center;
	text:登录;
	color: #fff;
	cursor: pointer;
	line-height: 35px;
}
</style>
<!--[if lte IE 7]>
<link href="<%=basePath %>BJUI/themes/css/ie7.css" rel="stylesheet">
<![endif]-->
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lte IE 9]>
<script type="text/javascript" src="<%=basePath %>BJUI/other/html5shiv.min.js"></script>
<script type="text/javascript" src="<%=basePath %>BJUI/other/respond.min.js"></script>
<![endif]-->
<!-- jquery -->
<script type="text/javascript" src="<%=basePath %>BJUI/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=basePath %>BJUI/js/jquery.cookie.js"></script>
<!--[if lte IE 9]>
<script type="text/javascript" src="<%=basePath %>BJUI/other/jquery.iframe-transport.js"></script>    
<![endif]-->
<script language="javascript">
	$(function() {
		$('.loginbody').css({
			'position' : 'relative',
			'top' : $(window).height() < 585 ? 0 : ($(window).height() - 585) / 2
		});
		$('.loginbox').css({
			'position' : 'absolute',
			'left' : $(window).width() < 753 ? 0 : ($(window).width() - 753) / 2,
			'top' : 145
		});
		$(window).resize(function() {
			$('.loginbody').css({
				'position' : 'absolute',
				'top' : $(window).height() < 585 ? 0 : ($(window).height() - 585) / 2
			});
			$('.loginbox').css({
				'position' : 'absolute',
				'left' : $(window).width() < 753 ? 0 : ($(window).width() - 753) / 2,
				'top' : 145
			});
		});
		
		if ("${mes}" != ""){
			${mes};
		}
		
		if (!window.applicationCache) {
			document.location = "<%=basePath %>Login/changeBrowse";
		}  
	});
	
	
</script>
<script type="text/javascript">
$(function(){ 
	$(document).keydown(function(event){ 
		if(event.keyCode==13){ 		
			changeStatus();
		} 
	});
}); 
	function changeStatus(){
		
		$("#loginbtn").val("登录中...");
		$("#loginbtn").css("color","gray");
		$("#loginbtn").attr("disabled", true);
		$("#form1").submit();
		setTimeout("recover()",5000);
		
	}
	function recover(){
		$("#loginbtn").val("登录");
		$("#loginbtn").addClass("loginbtn");
		$("#loginbtn").attr("disabled", false);
	}
	
</script>
</head>

<body style="background-color: #1c77ac; background-image: url(<%=basePath %>images/light.png); background-repeat: no-repeat; background-position: center top; overflow: hidden;">
	<div id="mainBody"></div>
	<div class="loginbody">
		<div class="loginbox">
			<form id="form1" name="form1" method="post" action="<%=basePath %>Login/login">
				<ul id="inputBox" style="margin-top: 85px">
					<li><input type="text" name="dlid" value="${dlid}" class="loginuser" maxlength="20" onmouseover="this.focus()" onfocus="this.select()" /></li>
					<li><input type="password" name="pwd" value="${pwd}" class="loginpwd" onmouseover="this.focus()" onfocus="this.select()" /></li>
					<li><input type="button" id="loginbtn"  class="loginbtn" value="登录" onclick=";changeStatus()"/></li>
				</ul>
			</form>
			<span class="span1">版权所有©惠州市人民防空办公室 电  话：0752-2898046  邮 箱：hzrenfangban@126.com</span>
			<span>技术服务：惠州市智联科技有限公司 电话：0752-2589937</span>
		</div>
	</div>
</body>
</html>