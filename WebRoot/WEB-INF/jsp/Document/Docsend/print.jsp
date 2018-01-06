<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<%
	String basePath = request.getContextPath() + "/";
%>
<html>
<head>
<base href="<%=basePath %>" />
<meta name="renderer" content="ie-comp" />
<meta name="renderer" content="ie-stand" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- bootstrap - css -->
<link href="BJUI/themes/css/bootstrap.css" rel="stylesheet">
<!-- core - css -->
<link href="BJUI/themes/css/style.css" rel="stylesheet">
<link href="BJUI/themes/blue/core.css" id="bjui-link-theme" rel="stylesheet">
<link href="css/common.css" rel="stylesheet" />
<!-- plug - css -->
<link href="BJUI/plugins/kindeditor_4.1.10/themes/default/default.css" rel="stylesheet">
<link href="BJUI/plugins/colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
<link href="BJUI/plugins/niceValidator/jquery.validator.css" rel="stylesheet">
<link href="BJUI/plugins/bootstrapSelect/bootstrap-select.css" rel="stylesheet">
<link href="BJUI/plugins/zhilian.css" rel="stylesheet" />
<link href="BJUI/themes/css/FA/css/font-awesome.min.css" rel="stylesheet">
<!-- print css -->
<link href="BJUI/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>

<!-- jquery -->
<script type="text/javascript" src="BJUI/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="BJUI/js/jquery.cookie.js"></script>
<!--[if lte IE 9]>
<script type="text/javascript" src="BJUI/other/jquery.iframe-transport.js"></script>    
<![endif]-->
<!-- 以下是bjui和主要业务功能 js -->
<script type="text/javascript" src="BJUI/js/bjui-all.js"></script>
<script type="text/javascript" src="BJUI/js/zhilian-suggest.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/workflow1.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/template.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/iSignatureHTML.js"></script>
<!-- 打印 -->
<script type="text/javascript" src="js/jQuery.print.js"></script> 
<!-- ztree -->
<script type="text/javascript" src="BJUI/plugins/ztree/jquery.ztree.all-3.5.js"></script>
<!-- nice validate -->
<script type="text/javascript" src="BJUI/plugins/niceValidator/jquery.validator.js"></script>
<script type="text/javascript" src="BJUI/plugins/niceValidator/jquery.validator.themes.js"></script>
<!-- bootstrap plugins -->
<script type="text/javascript" src="BJUI/plugins/bootstrap.min.js"></script>
<script type="text/javascript" src="BJUI/plugins/bootstrapSelect/bootstrap-select.min.js"></script>
<script type="text/javascript" src="BJUI/plugins/bootstrapSelect/defaults-zh_CN.min.js"></script>
<!-- icheck -->
<script type="text/javascript" src="BJUI/plugins/icheck/icheck.min.js"></script>
<!-- dragsort -->
<script type="text/javascript" src="BJUI/plugins/dragsort/jquery.dragsort-0.5.1.min.js"></script>
<!-- kindeditor -->
<script type="text/javascript" src="BJUI/plugins/kindeditor_4.1.10/kindeditor-all.min.js"></script>
<script type="text/javascript" src="BJUI/plugins/kindeditor_4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript">
$(function() {
	BJUI.init({
		JSPATH : '<%=basePath %>BJUI/', //[可选]框架路径
		PLUGINPATH : '<%=basePath %>BJUI/plugins/', //[可选]插件路径
		loginInfo : {
			url : '/Login',
			title : '登录',
			width : 400,
			height : 200
		}, // 会话超时后弹出登录对话框
		theme : 'sky'
	});	
});

	function LiuZhuanJiLu() { // “流程记录”操作
		  var url ="Main/Workflow/showsteps/${dc.pid}";
		  $(this).dialog({
				id : 'ShowSteps',
				url : url,
				title : '流转记录',
				mask : true,
				drawable : true,
				resizable : true,
				maxable : true,
				width : 800,
				height : 400
			});
	}
	
	function PrintDocument() {
		//document.all.bar.style.display = "none";
		//var DocForm = document.forms[0];
		//var mCount = DocForm.SignatureControl.PrintDocument(false, 1, 2); // 打印控制窗体
		//alert("实际打印份数：" + mCount);
		window.print();
		document.all.bar.style.display = "";
	}
	function closeit() {
		window.parent.$.pdialog.close("approve_print");
	}
	function ChaKanZhengWen() {
		var url = "<%=basePath%>Main/Office/view?RecordID=${dc.pid}&EditType=1,0";		
		window.parent.$(this).dialog({id:'editword',url:url, title:'查看正文', mask:'true',drawable:'true',resizable:'false',maxable:'false',max:'true'});
	}
</script>
</head>
<body>
<div id="navTab" class="bjui-pageContent unitBox">
	<form id="DocForm">
		<input type="hidden" name="DocumentID" value="${dc.pid}">
		<div style="width: 95%;height: 40px;text-align:center;margin-top: 10px;">
	    	<span style="font-size:25px; margin-left: 50px;"><B>惠州人防办发文呈批笺</B></span>
		</div>
		<table class="wordInfo" align="center" style="width: 95%;margin-bottom:30px"">
			<tr>
				<td class="title" style="height: 40px;">拟稿科室</td>
				<td colspan="2" style="text-align: center;">${startdept}</td>
				<td class="title" style="height: 40px;">文 号</td>
				<td colspan="2" style="text-align: center;">${dc.docno}</td>
			</tr>
			<tr>
				<td class="title" style="height: 40px;">拟稿时间</td>
				<td colspan="3" style="text-align: center;">${dc.approvedate}</td>
				<td class="title" style="height: 40px;">密 级</td>
				<td style="text-align: center;">${dc.security}</td>
			</tr>
			<tr>
				<td width="16%" class="title" style="height: 40px;">拟稿人</td>
				<td width="17%" style="text-align: center;">
				<c:if test="${dc.opinion1 != ''}">
				${starter}
				</c:if>
				</td>
				<td width="16%" class="title" style="height: 40px;">校稿人</td>
				<td width="17%" style="text-align: center;">${dc.proof}</td>
				<td width="16%" class="title" style="height: 40px;">份 数</td>
				<td width="17%" style="text-align: center;">${dc.num } 份</td>
			</tr>
			<tr>
				<td class="title" style="height: 40px;">主 送</td>
				<td colspan="5" style="text-align: center;">${dc.send1 }</td>
			</tr>
			<tr>
				<td class="title" style="height: 40px;">抄 送</td>
				<td colspan="5" style="text-align: center;">${dc.send2 }</td>
			</tr>
			<tr>
				<td class="title" style="height: 60px;">文件标题</td>
				<td colspan="5" style="text-align: center; font-family: '宋体'; font-size: 20px; font-weight: bold;">${dc.title }</td>

			</tr>
			<tr>
					<td class="title" style="height: 40px;font-size:15px;">正 文</td>
					<td colspan="5">
						 <a type="button" class="btn btn-blue"  onclick="ChaKanZhengWen()" style="margin-left: 5px;">查看正文</a>
			              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请在此处查看正文
					</td>
				</tr>
			<tr class="noprint">
					<td class="title">附件列表</td>
					<td colspan="5"><div id="uploadFile" class="unitBox"><c:import url="../../Common/Attachment/view1.jsp" /></div></td>
				</tr>
				<tr>
					<td class="title"><br>批办意见<br><br>及<br><br>领导批示<br></td><td colspan="6"><div id="opinion1_1">${opinion1 }</div>
					</td>
				</tr>
<%-- 			<c:if test="${d_id != '2'}">
			<tr>
				<td class="title" style="height: 140px;">科 室<br />
				<br />意 见
				</td>
				<td colspan="5">
					<div id="opinion1_1" class="opinion">${dc.opinion1 }</div>
				</td>
			</tr>
			<c:if test="${dc.opinion5 != ''}">
			<tr>
				<td class="title" style="height: 140px;">相关科 室<br />
				<br />意 见
				</td>
				<td colspan="5">
					<div id="opinion5_1" class="opinion">${dc.opinion5 }</div>
				</td>
			</tr>
			</c:if>	
			<tr>
				<td class="title" style="height: 140px;">办公室<br />
				<br />意 见
				</td>
				<td colspan="5">
					<div id="opinion2_1" class="opinion">${dc.opinion2 }</div>
				</td>
			</tr>
			</c:if>
			<c:if test="${d_id == '2'}">
				<tr>
				<td class="title" style="height: 140px;">办公室<br />
				<br />意 见
				</td>
				<td colspan="5">
					<div id="opinion1_1" class="opinion">${dc.opinion1 }</div>
				</td>
			</tr>
			<c:if test="${dc.opinion5 != ''}">
			<tr>
				<td class="title" style="height: 140px;">相关科 室<br />
				<br />意 见
				</td>
				<td colspan="5">
					<div id="opinion5_1" class="opinion">${dc.opinion5 }</div>
				</td>
			</tr>
			</c:if>
			</c:if>
			<tr>
				<td class="title" style="height: 140px;">分 管<br />
				<br />副部长<br />
				<br />批 示
				</td>
				<td colspan="5">
					<div id="opinion3_1" class="opinion">${dc.opinion3 }</div>
				</td>
			</tr>
			<tr>
				<td class="title" style="height: 140px;">部 长<br />
				<br />批 示
				</td>
				<td colspan="5">
					<div id="opinion4_1" class="opinion">${dc.opinion4 }</div>
				</td>
			</tr> --%>
		</table>
	</form>
</div>
<div class="bjui-pageFooter">
<ul>
		<li><a type="button" class="btn btn-default" data-icon="clock-o" onclick="LiuZhuanJiLu()">记录</a></li>
</ul>
</div>
</body>
</html>