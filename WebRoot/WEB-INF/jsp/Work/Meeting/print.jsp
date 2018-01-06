<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/inc.jsp" %>
<%
    String basePath = request.getContextPath() + "/";
%>
<html>
<head>
<link href="<%=basePath%>css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" media="screen" />
<link href="<%=basePath%>css/fullcalendar.css" rel="stylesheet" type="text/css" media="screen" />	
<link href="<%=basePath%>css/start/jquery-ui-1.9.2.custom.min.css" rel="stylesheet" type="text/css" media="screen" />
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

<script type="text/javascript">
	<%-- function LiuZhuanJiLu() { // “流程记录”操作
		window.parent.$.pdialog.open("<%=basePath%>Main/Workflow/showsteps/${mt.pid}", "ShowSteps", "流转记录", {mask:true,drawable:true,resizable:false,width:700,height:400});
	} --%>
	function LiuZhuanJiLu() { // “流程记录”操作
		$(this).dialog({
			id : 'ShowSteps',
			url : "Main/Workflow/showsteps/" + ${mt.pid},
			title : '流转记录',
			mask : true,
			drawable : false,
			resizable : false,
			maxable : false,
			width : 700,
			height : 400
		});
	}
	function PrintDocument() {
		document.all.bar.style.display = "none";
		//var DocForm = document.forms[0];
		//var mCount = DocForm.SignatureControl.PrintDocument(false, 1, 2); // 打印控制窗体
		//alert("实际打印份数：" + mCount);
		window.print();
		document.all.bar.style.display = "";
	}
	function closeit() {
		window.parent.$.pdialog.close("approve_dialog");	
	}
	function attend() {
	    var url = "/Main/Meeting/attend/" + "${mt.pid}" + "-"+"${u_id}";
	    $.post(url);
		window.parent.$.pdialog.close("approve_print");	
	}
	function noattend() {
		$(this).dialog({
			id : 'ShowReasonaaa',
			url : "/Main/Meeting/tonoattend/" + ${mt.pid} + "-"+${u_id},
			title : '不参加理由',
			mask : true,
			drawable : false,
			resizable : false,
			maxable : false,
			width : 500,
			height : 210
		});
	}
</script>
</head>
<body>
<div id="navTab" class="bjui-pageContent tableContent">
	<form id="DocForm" action="Main/Meeting/attend/${mt.pid}-${u_id}" data-toggle="validate">
	    <input type="hidden" name="DocumentID" value="${mt.pid}">
	    <input type="hidden" name="reason" id= "reason" value="">
        <div style="width: 95%;height: 50px;padding:15px;text-align:center">
            <span style="font-size:25px; margin-left:40px"><B>会议审批单</B></span>
        </div>			
	    <table class="wordInfo" align="center" style="width: 98%">
	        <tr>
	            <td width="15%" class="title">申 请 人</td>
	            <td width="30%" style="text-align: center;">${starter}</td>
	            <td width="15%" class="title">申请科室</td>
	            <td width="40%" style="text-align: center;">${startdept}</td>
	        </tr>
	        <tr>
	            <td class="title">申请日期</td>
	            <td style="text-align: center;">${mt.approvedate}</td>
	            <td class="title">会议日期</td>
	            <td style="text-align: center;">${mt.mdate }&nbsp;&nbsp;&nbsp; ${mt.hour} 时 ${mt.minute } 分</td>
	        </tr>
	        <tr>
	            <td class="title">会议地点</td>
	            <td colspan="3" style="text-align: center;">${mt.address}</td>
	        </tr>
	        <tr>
	            <td class="title">主 持 人</td>
	            <td style="text-align: center;">${chairman}</td>
	            <td class="title">类　　型</td>
	            <td style="text-align: center;">${mt.type }</td>
	        </tr>
	        <tr>
	            <td class="title">参加人员</td>
	            <td colspan="3" style="text-align: center;">${attendee}</td>
	        </tr>
	        <tr>
	            <td class="title">标　　题</td>
	            <td colspan="3" style="text-align: center;">${mt.title}</td>
	        </tr>
	        <tr>
	            <td class="title">内　　容</td>
	            <td colspan="3">${mt.content}</td>
	        </tr>
	        <tr>
	            <td class="title">附件列表</td>
	            <td colspan="3">
	                <div id="uploadFile" class="unitBox"><c:import url="../../Common/Attachment/view1.jsp" /></div>
	            </td>
	        </tr>
	        <tr>
	            <td class="title" style="height: 80px">科　　室<br/>意　　见</td>
	            <td colspan="3"><div id="opinion1_1" class="opinion">${mt.opinion1 }</div></td>
	        </tr>
	
	         <tr>
                    <td class="title" style="height: 120px">分　　管<br/>部 领 导<br/>意　　见</td>
					<td colspan="3"><div id="opinion2_1">${mt.opinion2 }</div>
					</td>
				</tr>
				<tr>
                    <td class="title" style="height: 120px">部　　长<br/>批　　示</td>
					<td colspan="3"><div id="opinion3_1">${mt.opinion3 }</div>
					</td>
				</tr>
	    </table>
	</form>
</div>
</body>
</html>
<div class="bjui-pageFooter">
	<ul>
		<li><a class="btn btn-default" data-icon="fa-clock-o" onclick="LiuZhuanJiLu()">流转记录</a></li>
        <c:if test="${attendflg == '2'}">
        	<li>
                <!-- <button class="btn btn-blue" data-icon="fa-check" onclick="attend()">参加</button> -->
                <button class="btn btn-blue" data-icon="fa-check" type="submit" >参加</button>
          </li>
          <li>
                <a class="btn btn-red" data-icon="remove" onclick="noattend()">不参加</a>
          </li>
        </c:if>
    </ul>
</div>