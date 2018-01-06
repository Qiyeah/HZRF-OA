<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<%
	String basePath = request.getContextPath() + "/";
%>
<html>
<head>
<link href="<%=basePath%>styles/dwz/themes/default/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>styles/dwz/themes/css/core.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>styles/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print" />
<style>
body {
	font-size: 14px;
	overflow: auto;
	background-color: #ffffff;
	text-align: left;
}

@media print {
	.noprint {
		display: none
	}
}
</style>
<script src="<%=basePath%>js/iSignatureHTML.js" type="text/javascript"></script>
<script type="text/javascript">
	function LiuZhuanJiLu() { // “流程记录”操作
		window.parent.$.pdialog.open("<%=basePath%>Main/Workflow/showsteps/${dc.pid}", "ShowSteps", "流转记录", {mask:true,drawable:true,resizable:false,width:700,height:400});
	}
	
	function PrintDocument() {
		document.all.bar.style.display = "none";
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
		window.parent.$.pdialog.open(url, "editword", "查看正文", {mask:true,drawable:false,resizable:false,maxable:false,width:1050,height:600});
	}
</script>
</head>
<body onload="document.forms[0].SignatureControl.ShowSignature('${dc.pid}');">
	<form id="DocForm">
		<input type="hidden" name="DocumentID" value="${dc.pid}">
			<div id="bar" class="formBar1" style="_top:expression(offsetParent.scrollTop+0);">
            <ul>
				<li><a class="button" onclick="LiuZhuanJiLu()"><span>记录</span></a></li>
                <li><a class="button" onclick="PrintDocument()"><span>打印</span></a></li>
           </ul>
        </div>
			<div style="height: 35px"></div>
			<div style="width: 100%;height: 100px;padding:15px;text-align:center">
		    <span style="font-size:25px; margin-left:40px">惠州市人防办</span>		    
			<br/><br/>
			<span style="font-size:30px; margin-left:40px"><B>公&nbsp;&nbsp;务&nbsp;&nbsp;文&nbsp;&nbsp;件&nbsp;&nbsp;处&nbsp;&nbsp;理&nbsp;&nbsp;笺</B></span></td>
		    </div>
			<table class="wordInfo" align="center" style="width: 100%">
				<tr>
					<td width="15%" class="title">来文单位</td>
					<td width="35%" style="text-align: center;">${dc.unit}</td>
					<td width="15%" class="title">文　　号</td>
					<td width="35%" style="text-align: center;">${dc.docno}</td>
				</tr>
				<tr>
					<td class="title">来文日期</td>
					<td style="text-align: center;">${dc.receivedate}</td>
					<td class="title">处理类型</td>
					<td style="text-align: center;"><c:choose>
						<c:when test="${dc.doflag=='1'}">一般阅知</c:when>
						<c:when test="${dc.doflag=='2'}">普通收文</c:when>
						<c:when test="${dc.doflag=='3'}">征求意见函</c:when>
					</c:choose></td>
				</tr>
				<tr>
					<td class="title">来文标题</td>
					<td colspan="3" style="text-align: center; height: 40px; font-size:20px;">${dc.title}</td>
				</tr>
				<tr class="noprint">
					<td class="title">文件列表</td>
					<td colspan="3"><div id="uploadFile" class="unitBox"><c:import url="../../Common/Attachment/view1.jsp" /></div></td>
				</tr>
				
				<tr>
					<td class="title">承办科室</td>
					<td style="text-align: center;">${receive1 }&nbsp;${receive2}</td>
					<td class="title">办理时限</td>
					<td style="text-align: center;">${dc.termdate }</td>
				</tr>
				<!-- 
				<tr>
					<td class="title">协办科室</td>
					<td style="text-align: center;" colspan="3">${receive2 }</td>
				</tr>
				 -->
				 <c:if test="${mainDept != '2'}">
				<tr>
					<td class="title" style="height: 120px;">分　办<br><br>意　见</td>
					<td colspan="3"><div id="opinion1_1">${opinion1 }</div></td>
				</tr>
				<tr>
					<td class="title" style="height: 120px;">拟　办<br><br>科　室<br><br>意　见</td>
					<td colspan="3"><div id="opinion2_1">${opinion2 }</div></td>
				</tr>
				</c:if>
				<c:if test="${mainDept == '2'}">
				<tr>
					<td class="title" style="height: 120px;">办公室<br><br>意　见</td>
					<td colspan="3"><div id="opinion1_1">${opinion1 }</div></td>
				</tr>
				
				</c:if>
				<tr>
					<td class="title" style="height: 120px;">分　管<br><br>部领导<br><br>意　见</td>
					<td colspan="3"><div id="opinion3_1">${opinion3 }</div></td>
				</tr>
				<tr>
					<td class="title" style="height: 120px;">部　长<br><br>批　示</td>
					<td colspan="3"><div id="opinion4_1">${opinion4 }</div></td>
				</tr>
				<tr>
					<td class="title" style="height: 120px;">传　阅<br><br>领　导<br><br>签　名</td>
					<td colspan="3"><div id="opinion5_1">${opinion5 }</div></td>
				</tr>
				<!-- 
				<tr>
					<td class="title" style="height: 120px">办　理<br><br>结　果</td>
					<td colspan="3"><div id="opinion6_1">${opinion6 }</div></td>
				</tr>
				-->
		</table>
		<object id="SignatureControl" classid="clsid:D85C89BE-263C-472D-9B6B-5264CD85B36E" codebase="<%=basePath%>cab/iSignatureHTML.cab#version=8,1,0,440" width="0" height="0" VIEWASTEXT>
			<param name="ServiceUrl" value="${mServerUrl}">
			<param name="WebAutoSign" value="0">
			<param name="PrintControlType" value=2>
			<param name="MenuDocVerify" value=false>
			<param name="MenuServerVerify" value=false>
			<param name="MenuDigitalCert" value=false>
			<param name="MenuDocLocked" value=false> 
			<param name="MenuAbout" value=false>
		</object>
	</form>
</body>
</html>
<div class="bjui-pageFooter">
	<ul>
        <li><a class="btn btn-close" data-icon="close">关闭</a></li>
    </ul>
</div>