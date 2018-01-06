<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<%
	String basePath = request.getContextPath() + "/";
%>
<html>
<head>
<style>
@media print {
	.noprint {
		display: none
	}
}
</style>
<base href="<%=basePath%>" />
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
<link href="BJUI/themes/css/print.css" rel="stylesheet" type="text/css" media="print" />

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
		JSPATH : '<%=basePath%>BJUI/', //[可选]框架路径
		PLUGINPATH : '<%=basePath%>BJUI/plugins/', //[可选]插件路径
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
		var DocForm = document.forms[0];
		//var mCount = DocForm.SignatureControl.PrintDocument(false, 1, 2); // 打印控制窗体
		//alert("实际打印份数：" + mCount);
		window.print();
		document.all.bar.style.display = "";
	}

	function closeit() {
		window.parent.$.pdialog.close("approve_print");
	}

	function ChaKanZhengWen() {
		var url = "<%=basePath%>Main/Office/view?RecordID=${dc.pid}&EditType=0,0";
		window.parent.$.pdialog.open(url, "editword", "查看正文", {
			mask : true,
			drawable : false,
			resizable : false,
			maxable : false,
			width : 1050,
			height : 600
		});
	}
</script>
</head>
<body>
	<div class="bjui-pageContent">
		<form id="DocForm" class="pageForm" action="" method="post">
			<input type="hidden" name="DocumentID" id="id" value="${dc.pid}">
			<div style="width: 100%; height: 60px; padding: 15px; text-align: center">
				<span style="font-size: 30px; margin-left: 10px"><B>文件呈批表</B></span>
			</div>
			<table class="wordInfo" align="center" style="width: 95%; margin-bottom: 6px">
				<tr>
					<td width="15%" class="title">来文单位</td>
					<td width="25%" colspan="2"><input name="t_Doc_Receive.unit" type="text" value="${dc.unit}" class="required" data-msg-required="必填"
						data-rule="required" maxlength="50" style="width: 100%" /></td>
					<td width="13%" class="title">收文日期</td>
					<td width="17%"><input name="t_Doc_Receive.receivedate" data-toggle="datepicker" type="text" value="${dc.receivedate}" class="required date"
						data-rule="required" style="width: 100%" /></td>
					<td width="15%" class="title">办文编号</td>
					<td width="15%"><input name="t_Doc_Receive.docno" type="text" value="${dc.docno}" maxlength="50" style="width: 100%" /></td>
				</tr>
				<tr>
					<td class="title">经办人</td>
					<td colspan="2"><input type="text" name="" value="${receiver}" style="width: 100%"></td>
					<td class="title">分办人</td>
					<td colspan="3"><input type="text" name="" value="${dc.auditor}" style="width: 100%"></td>
				</tr>
				<tr>
					<td class="title">密 级</td>
					<td width="15%"><select name="t_Doc_Achieve.security" data-style="selectrequired" data-msg-required="必填" data-rule="required"
						data-toggle="selectpicker" data-width="100%">
							<option value="无" <c:if test="${dc.bsecurity=='无' }">selected="selected"</c:if>>无</option>
							<option value="秘密" <c:if test="${dc.bsecurity=='秘密' }">selected="selected"</c:if>>秘密</option>
					</select></td>
					<td class="title" rowspan="2">标 题</td>
					<td colspan="4" rowspan="2" class="normal" align="center"><input name="t_Doc_Receive.title" type="text"
						style="font-size: 20px; width: 100%; height: 40px" data-rule="required" class="required" data-msg-required="必填" maxlength="250"
						value="${dc.title}" /></td>
				</tr>
				<tr>
					<td class="title">紧急程度</td>
					<td><input name="t_Doc_Achieve.degree" value="${dc.degree}" type="text" data-rule="number" style="width: 100%" style="text-align: center;" /></td>
				</tr>
				<tr class="noprint">
					<td class="title">文件列表</td>
					<td colspan="6"><div id="uploadFile" class="unitBox">
							<c:import url="../../Common/Attachment/view1.jsp" />
						</div></td>
				</tr>
				<tr>
					<td class="title"><br>批办意见<br>
					<br>及<br>
					<br>领导批示<br></td>
					<td colspan="6"><div id="opinion1_1">${opinion1 }</div></td>
				</tr>

				<tr>
					<td class="title" style="height: 150px;"><br>传<br>
					<br>阅<br>
					<br>签<br>
					<br>名<br></td>
					<td colspan="6"><div id="opinion2_1">${opinion2}</div></td>
				</tr>
				<tr>
					<td class="title" style="height: 150px;"><br>办<br><br>理<br><br>情<br><br>况<br></td>
					<td colspan="6">${opinion3}</td>
				</tr>

			</table>
		</form>
	</div>
	<div class="bjui-pageFooter">
		<ul>
			<li><button type="button" class="btn btn-default" data-icon="clock-o" onclick="LiuZhuanJiLu()">记录</button></li>
		</ul>
	</div>
</body>
</html>

