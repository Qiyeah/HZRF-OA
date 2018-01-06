<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<%
	String basePath = request.getContextPath() + "/";
%>
<html>
<head>
<base href="<%=basePath %>" />

<link href="BJUI/themes/css/bootstrap.css" rel="stylesheet">
<!-- core - css -->
<link href="BJUI/themes/css/style.css" rel="stylesheet">
<link href="BJUI/themes/blue/core.css" id="bjui-link-theme" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="css/common.css" />
<!-- plug - css -->
<link href="BJUI/plugins/kindeditor_4.1.10/themes/default/default.css" rel="stylesheet">
<link href="BJUI/plugins/colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
<link href="BJUI/plugins/niceValidator/jquery.validator.css" rel="stylesheet">
<link href="BJUI/plugins/bootstrapSelect/bootstrap-select.css" rel="stylesheet">
<link href="BJUI/plugins/zhilian.css" rel="stylesheet" type="text/css" />
<link href="BJUI/themes/css/FA/css/font-awesome.min.css" rel="stylesheet">

<!-- jquery -->
<script type="text/javascript" src="BJUI/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="BJUI/js/jquery.cookie.js"></script>
<!-- 以下是bjui和主要业务功能 js -->
<script type="text/javascript" src="BJUI/js/bjui-all.js"></script>
<script type="text/javascript" src="BJUI/js/zhilian-suggest.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="js/workflow.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/template.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/iSignatureHTML.js"></script>
<!-- 打印 -->
<script type="text/javascript" src="js/jQuery.print.js"></script> 
<!-- nice validate -->
<script type="text/javascript" src="BJUI/plugins/niceValidator/jquery.validator.js"></script>
<script type="text/javascript" src="BJUI/plugins/niceValidator/jquery.validator.themes.js"></script>
<!-- bootstrap plugins -->
<script type="text/javascript" src="BJUI/plugins/bootstrap.min.js"></script>
<script type="text/javascript" src="BJUI/plugins/bootstrapSelect/bootstrap-select.min.js"></script>
<script type="text/javascript" src="BJUI/plugins/bootstrapSelect/defaults-zh_CN.min.js"></script>

<!-- kindeditor -->
<script type="text/javascript" src="BJUI/plugins/kindeditor_4.1.10/kindeditor-all.min.js"></script>
<script type="text/javascript" src="BJUI/plugins/kindeditor_4.1.10/lang/zh_CN.js"></script>
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
form#approveForm span.wrap_bjui_btn_box{
	width: 100%;
}
</style>
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
		theme : 'sky' // 若有Cookie['bjui_theme'],优先选择Cookie['bjui_theme']。皮肤[五种皮肤:default, orange, purple, blue, red, green]
	});	
});
	function DoSignature() {	
		var DocForm = document.forms[0];
		DocForm.SignatureControl.FieldsList = "1=1";
		DocForm.SignatureControl.UserName = "lyj"; // 文件版签章用户
		DocForm.SignatureControl.RelativeTagId= "${wa.opinionfield}_1";
		DocForm.SignatureControl.PositionByTagType = "1";
		DocForm.SignatureControl.PositionBySignType = "1";
		DocForm.SignatureControl.Position(100, 0);
		DocForm.SignatureControl.RunSignature(false); // 执行签章操作
	}
	
	
	
	function closeit() {
		window.parent.$.pdialog.close("approve_dialog");	
	}
	
	function hasFill() {
		return true;
	}
</script>
</head>
<body>

<div id="navTab" class="bjui-pageContent tableContent">
	<form id="approveForm" method="post" action="<%=basePath%>Main/Meeting/save1" class="pageForm" data-toggle="validate" >
		<div class="pageFormContent" layoutH="60">
			<input type="hidden" name="DocumentID" value="${mt.pid}">
			<input type="hidden" name="t_Meeting_Approve.id" value="${mt.id}" />
			<input type="hidden" name="t_Meeting_Approve.u_id" id="userid" value="${mt.u_id}" />
			<input type="hidden" name="t_Meeting_Approve.d_id" value="${mt.d_id}" />
			<input type="hidden" name="t_Meeting_Approve.opinion1" id="opinion1" value="${mt.opinion1}" />
			<input type="hidden" name="t_Meeting_Approve.opinion2" id="opinion2" value="${mt.opinion2}" />
			<input type="hidden" name="t_Meeting_Approve。opinion3" id="opinion3" value="${mt.opinion3}" />
			<input type="hidden" name="t_Meeting_Approve.opinion4" id="opinion4" value="${mt.opinion4}" />
			<input type="hidden" name="t_Meeting_Approve。pid" id="pid" value="${mt.pid}" />
			<input type="hidden" name="t_Meeting_Approve.pstatus" value="${mt.pstatus}" />
			<input type="hidden" name="flag" value="0" />
            <div style="width: 95%;height: 50px;padding:15px;text-align:center">
                <span style="font-size:25px; margin-left:40px"><B>会议审批单</B></span>
            </div>			
			<table class="wordInfo" align="center" style="width: 95%;margin-bottom:50px">
				<tr>
					<td width="15%" class="title">申 请 人</td>
					<td width="30%" style="text-align: center;">${starter}</td>
					<td width="15%" class="title">申请科室</td>
					<td width="40%" style="text-align: center;">${startdept}</td>
				</tr>
				<tr>
					<td class="title">申请日期</td>
					<td><input  name="t_Meeting_Approve.approvedate" data-toggle="datepicker" readonly value="${mt.approvedate }" class="required" style="width: 100%;text-align: center;" /></td>
                    <td class="title">会议时间</td>
                    <td>
                        <table style="width: 100%">
                            <tr>
                                <td style="border: 0px;" ><input type="text" name="t_Meeting_Approve.mdate" data-toggle="datepicker" readonly data-rule="required" class="required" style="width: 100%" value="${mt.mdate}"/></td>
                                <td style="border: 0px;">日</td>
                                <td style="border: 0px;"><select name="t_Meeting_Approve.hour" data-toggle="selectpicker">
                                    <c:forEach var="x" begin="0" end="23" step="1">
                                        <option value="${x}" <c:if test="${mt.hour == x}">selected="selected"</c:if>> ${x}</option>
                                    </c:forEach>
                                </select></td>
                                <td style="border: 0px;">时</td>
                                <td style="border: 0px;"><select name="t_Meeting_Approve.minute" data-toggle="selectpicker">
                                    <c:forEach var="x" begin="0" end="60" step="5">
                                        <option value="${x}" <c:if test="${mt.minute == x}">selected="selected"</c:if>>${x}</option>
                                    </c:forEach>
                                </select></td>
                                <td style="border: 0px;">分</td>
                            </tr>
                        </table>
                    </td>
				</tr>
				<tr>
					<td class="title">会议地点</td>
					<td colspan="3"><input type="text" name="t_Meeting_Approve.address" class="required" data-rule="required" value="${mt.address }"
                                           style="text-align: center;width: 100%" /></td>
				</tr>
				<tr>
                    <td class="title">主 持 人</td>
					<td><input type="hidden" name="chairman.userid" value="${mt.chairman }" />					
						<input type="text"  style="text-align: center;width:100%;" name="chairman.username" value="${chairman}" suggestFields="username"
                               suggestUrl="<%=basePath%>Main/search/searchUser" lookupGroup="chairman"  class="required" data-rule="required"/></td>
                    <td class="title">类　　型</td>
					<td><input type="text" name="t_Meeting_Approve.type" value="${mt.type }" style="text-align: center;line-height:20px;width
					:100%" /></td>
				</tr>
				<tr>
                    <td class="title">参加人员</td>
					<td colspan="3"><input type="hidden" name="attendee.id" value="${mt.attendee }" />
						<input type="text" name="attendee.name" value="${attendee}" readonly="readonly" 
						data-toggle="lookup" data-url="<%=basePath%>Main/search/searchUsers1" lookupGroup="attendee" data-id="searchUser"  data-width="500" data-height="480" 
						data-title="出席人" style="padding-right: 0px;width:100%;"/>
				</tr>
				<tr>
                    <td class="title">主　　题</td>
					<td colspan="3"><input type="text" name="t_Meeting_Approve.title" value="${mt.title }" class="required"
                                           style="text-align: center;width: 100%" /></td>
				</tr>
				<tr>
                    <td class="title">内　　容</td>
					<td colspan="3"><textarea name="t_Meeting_Approve.content" rows="6" style="width: 100%; line-height:20px;overflow:auto">${mt.content }</textarea></td>
				</tr>
				<tr>
					<td class="title">附件列表</td>
					<td colspan="3">
						<div id="uploadFile" class="unitBox"><c:import url="../../Common/Attachment/edit.jsp" /></div>
					</td>
				</tr>
				<tr>
                    <td class="title" style="height: 80px">科　　室<br/>意　　见</td>
					<td colspan="3"><div id="opinion1_1">${opinion1 }</div>
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion1'}">
					<div class="noprint">
                            <br/>
                            <a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
                            <c:if test="${wa.atype != '3'}">
                                <a class="btn btn-blue" data-icon="fa-check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
                            </c:if>
					</div>                            
                    </c:if></td>
				</tr>
				<tr>
                    <td class="title" style="height: 120px">副 主 任<br/>批　　办</td>
					<td colspan="3"><div id="opinion2_1">${opinion2 }</div>
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion2'}">
					<div class="noprint">
                            <br/>
                            <a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
                            <c:if test="${wa.sealword == '1'}">
						        <a class="btn btn-blue" data-icon="fa-tag" onclick="DoSignature()" style="margin-left: 5px;">签章</a>
					        </c:if>
                            <c:if test="${wa.atype != '3'}">
                                <a class="btn btn-blue" data-icon="fa-check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
                            </c:if>
                     </div>
                     </c:if>
                   </td>
				</tr>
				<tr>
                    <td class="title" style="height: 120px">主　　任<br/>批　　办</td>
					<td colspan="3"><div id="opinion3_1">${opinion3 }</div>
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion3'}">
					<div class="noprint">
                            <br/>
                            <a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
                            <c:if test="${wa.sealword == '1'}">
						        <a class="btn btn-blue" data-icon="fa-tag" onclick="DoSignature()" style="margin-left: 5px;">签章</a>
					         </c:if>
                            <c:if test="${wa.atype != '3'}">
                                <a class="btn btn-blue" data-icon="fa-check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
                            </c:if>
                        </div>
                        </c:if>
                    </td>
				</tr>
			</table>
			<input type="hidden" name="positionid" id="positionid" value="${positionid }" />
			<input type="hidden" name="sendnote" id="num" value="${sendnote}" />
			<input type="hidden" name="t_Workflow.id" id="id" value="${wf.id}" />
			<input type="hidden" name="t_Workflow.flowname" id="flowname" value="${wf.flowname}" />
			<input type="hidden" name="t_Workflow.workpath" value="${wf.workpath}" />
			<input type="hidden" name="t_Workflow.flowform" value="${wf.flowform}" />
			<input type="hidden" name="t_Workflow.formname" value="${wf.formname}" />
			<input type="hidden" name="t_Workflow.title" id="title" value="${wf.title}" />
			<input type="hidden" name="t_Workflow.starter" id="starter" value="${wf.starter}" />
			<input type="hidden" name="t_Workflow.startdept" id="startdept" value="${wf.startdept}" />
			<input type="hidden" name="t_Workflow.starttime" value="${wf.starttime}" />
			<input type="hidden" name="t_Workflow.reader" value="${wf.reader}" />
			<input type="hidden" name="t_Workflow.todoman" value="${wf.todoman}" />
			<input type="hidden" name="t_Workflow.doneuser" value="${wf.doneuser}" />
			<input type="hidden" name="t_Workflow.todousers" value="${wf.todousers}" />
			<input type="hidden" name="t_Workflow.isopen" value="${wf.isopen}" />
			<input type="hidden" name="t_Workflow.editor" value="${wf.editor}" />
			<input type="hidden" name="t_Workflow.mainflowid" value="${wf.mainflowid}" />
			<input type="hidden" name="t_Workflow.subflowid" value="${wf.subflowid}" />
			<input type="hidden" name="t_Workflow.subflowname" value="${wf.subflowname}" />
			<input type="hidden" name="t_Workflow.isend" value="${wf.isend}" />
			<input type="hidden" name="t_Workflow.isnormalend" value="${wf.isnormalend}" />
			<input type="hidden" name="t_Workflow.ishold" value="${wf.ishold}" />
			<input type="hidden" name="t_Workflow.islock" value="${wf.islock}" />
			<input type="hidden" name="t_Workflow.isnewdoc" value="0" />
			<input type="hidden" name="t_Workflow.itemid" id="itemid" value="${wf.itemid}" />
			<input type="hidden" name="t_Workflow.hastemplate" value="${wf.hastemplate}" />
			<input type="hidden" name="t_Workflow.templatename" value="${wf.templatename}" />
			<input type="hidden" name="t_Workflow.worddocname" value="${wf.worddocname}" />
			<input type="hidden" name="t_Workflow.bodyiscreated" value="${wf.bodyiscreaded}" />
			<input type="hidden" name="t_Workflow.bodyauthor" value="${wf.bodyauthor}" />
			<input type="hidden" name="t_Workflow.bodyversion" value="${wf.bodyversion}" />			
			<input type="hidden" name="todomannum" value="${todomannum}" />
			<input type="hidden" name="issequence" value="${wa.issequence}" />
			<input type="hidden" name="havesubflow" value="${wa.havesubflow}" />
			<input type="hidden" name="editword" value="${wa.editword}" />
			<input type="hidden" name="viewword" value="${wa.viewword}" />
			<input type="hidden" name="endprocess" value="${wa.endprocess}" />
			<input type="hidden" name="specialsend" value="${wa.specialsend}" />
			<input type="hidden" name="backlaststep" value="${wa.backlaststep}" />
			<input type="hidden" name="backfirststep" value="${wa.backfirststep}" />
			<input type="hidden" name="haveopinionfield" id="haveopinionfield" value="${wa.haveopinionfield}" />
			<input type="hidden" name="opinionfield" id="opinionfield" value="${wa.opinionfield}" />
			<input type="hidden" name="opinionname" id="opinionname" value="${opinionname}" />
			<input type="hidden" name="opinion" id="opinion" value="${wkit.opinion}" />
			<input type="hidden" name="opiniontime" id="opiniontime" value="" />
			<input type="hidden" name="operation" id="operation" value="" />
			<input type="hidden" name="wid" id="wid" value="${wp.id}" />
			<input type="hidden" name="nexttodoman" id="nexttodoman" value="" />
			<input type="hidden" name="nextitemid" id="nextitemid" value="${nextitemid}" />
			<input type="hidden" name="nextstepsnum" id="nextstepsnum" value="${nextstepsnum}" />
			<input type="hidden" name="sendsms" id="sendsms" value="0" />
			<input type="hidden" name="sendemail" id="sendemail" value="0" />
			<input type="hidden" name="curuser" id="curuser" value="${u_id}" />
			<input type="hidden" name="curdept" id="curdept" value="${d_id}" />
			<input type="hidden" name="localarea" value="${localarea}" />
			<input type="hidden" name="seldept" id="seldept" value="${d_id}" />
			<input type="hidden" name="curuser1" id="curuser1" value="${uname}" />
		</div>
		
	</form>
</div>
</body>
</html>	
<div class="bjui-pageFooter">
<ul>
		<li><a class="btn btn-default" data-icon="fa-clock-o" onclick="LiuZhuanJiLu()">记录</a></li>
		<c:if test="${wkit1 != null}">
			<li><a class="btn btn-blue" data-icon="fa-check" onclick="QuHui()">取回</a></li>
		</c:if>
		<c:if test="${candeal}">
			<c:if test="${wa.haveopinionfield == '1' && wa.handround == '1'}">
				<li><a class="btn btn-blue" data-icon="fa-check" onclick="TongYi()">已阅</a></li>
			</c:if>
			<c:if test="${wa.haveopinionfield == '1' && wa.handround == '0'}">
				<li><a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()">填写意见</a></li>
			</c:if>
			<c:if test="${wa.sealword == '1'}">
				<li><a class="btn btn-blue" data-icon="fa-tag" onclick="DoSignature()">签章</a></li>
			</c:if>
			<li class="line"></li>
			<c:if test="${wa.specialsend == '1'}">
				<li><a class="btn btn-blue" data-icon="plane" onclick="TeSong()">特送</a></li>
			</c:if>
			<c:if test="${wa.atype != '1'}">
				<c:if test="${wa.endprocess == '1'}">
					<li><a class="btn btn-red" data-icon="fa-power-off" onclick="ZhongZhi()">终止</a></li>
				</c:if>
				<c:if test="${wa.backlaststep == '1'}">
					<li><a class="btn btn-red" data-icon="fa-share-square" onclick="TuiHuiShangBu()">退回</a></li>
				</c:if>
				<c:if test="${wa.backfirststep == '1'}">
					<li><a class="btn btn-red" data-icon="fa-share-square" onclick="TuiHuiNiGao()">退回经办人</a></li>
				</c:if>
			</c:if>
			<c:if test="${todomannum > 1}">
				<li><a class="btn btn-blue" data-icon="fa-check" onclick="ChuLiWanBi()">提交</a></li>
			</c:if>
			<c:if test="${todomannum <= 1}">
				<c:if test="${wa.atype == '3'}">
					<li><a class="btn btn-default" data-icon="fa-save" onclick="WanCheng()">完成</a></li>
				</c:if>
				<c:if test="${wa.atype != '3'}">
					<c:choose>
					     <c:when test="${fn:contains(wa.name, '通知')}">
					           <li><a class="btn btn-blue" data-icon="fa-bullhorn" onclick="FaSong()">发布</a></li>
					     </c:when>
					     <c:otherwise>
						     <li><a class="btn btn-blue" data-icon="fa-check" onclick="FaSong()">提交</a></li>
						 </c:otherwise>
					 </c:choose>
				</c:if>
			</c:if>
			<c:if test="${canedit || wa.haveopinionfield == '1'}">
				<li><a class="btn btn-default" data-icon="fa-save" onclick="BaoCunTuiChu()">保存</a></li>
			</c:if>
		</c:if>
	</ul>
</div>