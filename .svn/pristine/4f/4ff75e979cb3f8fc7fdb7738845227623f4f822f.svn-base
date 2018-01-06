<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<%
	String basePath = request.getContextPath() + "/";
%>
<html>
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
<!--[if lte IE 7]>
<link href="BJUI/themes/css/ie7.css" rel="stylesheet">
<![endif]-->
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lte IE 9]>
<script type="text/javascript" src="BJUI/other/html5shiv.min.js"></script>
<script type="text/javascript" src="BJUI/other/respond.min.js"></script>
<![endif]-->
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
<style>

@media print {
	.noprint {
		display: none
	}
}

form#approveForm span.wrap_bjui_btn_box{
	width: 100%;
}
</style>
<head>
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
	
	function PrintDocument() {
		var DocForm = document.forms[0];
		$("#bar").css("display","none");
		//var mCount = DocForm.SignatureControl.PrintDocument(false, 1, 2); // 打印控制窗体
		//alert("实际打印份数：" + mCount);
		window.print();
		$("#bar").css("display","");
	}
	
	function closeit() {
	    window.parent.$.pdialog.close("approve_dialog");	
	}
	
	function hasFill() {
		return true;
	}
	
	function doit() {
		if ($("#todomannum").val() > 1) {
			ChuLiWanBi();
		} else {
			if ($("#watype").val() == "3") {
				WanCheng();
			} else {
				FaSong();
			}
		}
	}
	
	function showrule() {
		window.parent.$.pdialog.open("<%=basePath%>Main/Leave/showrule", "ShowRule", "请假说明", {mask:true,drawable:false,resizable:false,width:850,height:600});
	}
	
	function showhistory() {
		window.parent.$.pdialog.open("<%=basePath%>Main/Leave/showhistory/${la.u_id}-${year}", "ShowHistory", "本年度已请假明细", {mask:true,drawable:false,resizable:false,width:850,height:300});
	}	
</script>
</head>
<body>
<div id="navTab" class="bjui-pageContent">
	<form id="approveForm" class="pageForm" action="Main/Leave/save1" method="post" data-toggle="validate">   
        <div class="pageFormContent" layoutH="59">
			<input type="hidden" name="DocumentID" value="${la.pid}">
			<input type="hidden" name="t_Leave_Approve.id" value="${la.id}" />
			<input type="hidden" name="t_Leave_Approve.u_id" id="userid" value="${la.u_id}" />
			<input type="hidden" name="t_Leave_Approve.d_id" value="${la.d_id}" />
			<input type="hidden" name="t_Leave_Approve.opinion1" id="opinion1" value="${la.opinion1}" />
			<input type="hidden" name="t_Leave_Approve.opinion2" id="opinion2" value="${la.opinion2}" />
			<input type="hidden" name="t_Leave_Approve.opinion3" id="opinion3" value="${la.opinion3}" />
			<input type="hidden" name="t_Leave_Approve.opinion4" id="opinion4" value="${la.opinion4}" />
			<input type="hidden" name="t_Leave_Approve.pid" id="pid" value="${la.pid}" />
			<input type="hidden" name="t_Leave_Approve.pstatus" value="${la.pstatus}" />
			<input type="hidden" name="flag" value="0" />
            <div style="width: 95%;height: 40px;text-align:center">
                <span style="font-size:25px; margin-left:40px"><B>请休假审批单</B>
            </div>			
			<table class="wordInfo" align="center" style="width: 95%; margin-bottom: 6px">
				<tr>
					<td style="width: 15%;" class="title">申 请 人</td>
					<td style="width: 35%;" align="center">${starter}</td>
					<td style="width: 15%;" class="title">所在科室</td>
					<td style="width: 35%;" align="center" width="35%">${startdept}</td>
				</tr>
				<tr>
                    <td class="title">请休假类型</td>
                    <td><select id="type" name="t_Leave_Approve.type" data-toggle="selectpicker" data-width="100%"  data-width="100%">
                            <option value="年休假" <c:if test="${la.type == '年休假'}">selected</c:if>>年休假</option>
                            <option value="事假" <c:if test="${la.type == '事假'}">selected</c:if>>事假</option>
                            <option value="病假" <c:if test="${la.type == '病假'}">selected</c:if>>病假</option>
                            <option value="婚丧假" <c:if test="${la.type == '婚丧假'}">selected</c:if>>婚丧假</option>
                            <option value="产假" <c:if test="${la.type == '产假'}">selected</c:if>>产假</option>
                            <option value="探亲假" <c:if test="${la.type == '探亲假'}">selected</c:if>>探亲假</option>
                        </select>
                    </td>
					<td class="title">婚姻状态</td>
					<td style="text-align: center;">${married}</td>
				</tr>
				<tr>
					<td class="title">申请时间</td>
					<td><input name="t_Leave_Approve.approvedate" type="text" value="${la.approvedate}" class="required" size="27" data-toggle="datepicker" data-rule="required;date" data-msg-date="格式错误" data-msg-required="必填" maxlength="13"/></td>
					<td class="title"><a href="Main/Leave/showrule" data-toggle="dialog" data-mask="ture" data-id="showrule" data-title="请假说明"
					data-width="850" data-height="600"><span style="color: blue; font-family:'黑体'; font-size: 15px">请假说明</span></a></td>
                    <td align="center"><a href="Main/Leave/showhistory/${la.u_id}-${year}" data-toggle="dialog" data-mask="ture" data-id="showhistory" data-title="本年度已请假明细"
					data-width="900" data-height="400"><span style="font-size: 14px">本年度已请假明细</span></a>
                    </td>
                </tr>
				<tr>
					<td style="width: 15%;" class="title">起止时间</td>
					<td style="width: 35%;"><table>
							<tr>
								<td style="border: 0px"><input name="t_Leave_Approve.begindate" id="begindate" type="text" value="${la.begindate }" size="12" class="required" data-toggle="datepicker" data-rule="开始日期:required;date" data-msg-date="格式错误" data-msg-required="必填" maxlength="13"/></td>
								<td style="border: 0px">—</td>
								<td style="border: 0px"><input	name="t_Leave_Approve.enddate" id="enddate" type="text" value="${la.enddate }" size="12" class="required" data-toggle="datepicker" data-rule="结束日期:required;date;match[gte, t_Leave_Approve.begindate, date]" data-msg-date="格式错误" data-msg-required="必填" maxlength="13"/></td>
							</tr>
						</table></td>
					<td style="width: 15%;" class="title">请休假天数</td>
                    <td style="width: 35%;" align="center"><input name="t_Leave_Approve.dayt" id="dayt" type="text"value="${la.dayt }" class="required" data-rule="required,digits" data-msg-required="必填" style="width: 100%; text-align: center" /></td>
                </tr>
                <tr id="annualVacationTr" style="display: none;">
                    <td style="width: 15%;" class="title">工作年数</td>
                    <td style="width: 35%;" align="center">${workyear}</td>
                    <td style="width: 15%;" class="title">年假情况</td>
                    <td style="width: 35%;" style="text-align: center;">${enableday} 天，已休 ${nxjs} 天，剩余 ${canday} 天</td>
                </tr>
				<tr>
					<td class="title" style="height:50px">请休假事由</td>
					<td colspan="3"><textarea name="t_Leave_Approve.reason" rows="3" class="required" style="width: 100%; overflow: auto" data-msg-required="必填" data-rule="required;length[~800]" placeholder="请根据实际情况填写">${la.reason }</textarea></td>
				</tr>
				<tr>
					<td class="title" style="">附件列表</td>
					<td colspan="3">
						<div id="uploadFile" class="unitBox"><c:import url="../../Common/Attachment/edit.jsp" /></div>
					</td>
				</tr>
				<tr>
					<td class="title" style="height: 80px">科 室 意 见</td>
					<td colspan="3">
                        <div id="opinion1_1" class="opinion">${opinion1 }</div>
                        <c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion1'}">
						<div class="noprint">
                            <br/>
                            <a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
                            <c:if test="${wa.sealword == '1'}">
						    <a class="btn btn-blue" onclick="DoSignature()" style="margin-left: 5px;">签章</a>
					        </c:if>
                            <c:if test="${wa.atype != '3'}">
                                <a class="btn btn-blue" data-icon="check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
                            </c:if>
                        </div>
                        </c:if>
                    </td>
				</tr>
				<tr>
					<td class="title" style="height: 120px;">分管领导意见</td>
					<td colspan="3">
                        <div id="opinion2_1" class="opinion">${opinion2 }</div>
                        <c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion2'}">
						<div class="noprint">
                            <br/>
                            <a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
                            <c:if test="${wa.sealword == '1'}">
						    <a class="btn btn-blue" onclick="DoSignature()" style="margin-left: 5px;">签章</a>
					        </c:if>
                            <c:if test="${wa.atype != '3'}">
                                <a class="btn btn-blue" data-icon="check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
                            </c:if>
                            </div>
                        </c:if>
                    </td>
				</tr>
				<tr>
					<td class="title" style="height: 120px">主 任 意 见</td>
					<td colspan="3">
                        <div id="opinion3_1" class="opinion">${opinion3 }</div>
                        <c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion3'}">
                        	<div class="noprint">                        
                            <br/>
                            <a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
                            <c:if test="${wa.sealword == '1'}">
						        <a class="btn btn-blue"  onclick="DoSignature()" style="margin-left: 5px;">签章</a>
					         </c:if>
                            <c:if test="${wa.atype != '3'}">
                                <a class="btn btn-blue" data-icon="check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
                            </c:if>
                            </div>
                        </c:if>
                    </td>
				</tr>
				<tr>
					<td class="title" style="">销假时间0</td>
					<td><input name="t_Leave_Approve.backdate" type="text" value="${la.backdate}" data-toggle="datepicker" data-rule="date" data-msg-date="格式错误" maxlength="13" style="width: 100%" /></td>
					<td class="title" style="">实际天数</td>
					<td><input name="t_Leave_Approve.days" id="days" type="text" value="${la.days}" data-rule="digits" style="width: 100%" /></td>
				</tr>
			</table>
			<input type="hidden" name="positionid" id="positionid" value="${positionid }" />
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
			<input type="hidden" name="todomannum" id="todomannum" value="${todomannum}" />
			<input type="hidden" name="watype" id="watype" value="${wa.atype}" />
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
<div id="bar" class="bjui-pageFooter">
	<ul>
		<li><a class="btn btn-default" data-icon="fa-edit" onclick="LiuZhuanJiLu()">记录</a></li>
		<li><!-- <a class="button" onclick="PrintDocument()">打印</a> --></li>
		<c:if test="${wkit1 != null}">
			<li><a class="btn btn-blue" onclick="QuHui()">取回</a></li>
		</c:if>
		<c:if test="${candeal}">
			<c:if test="${wa.haveopinionfield == '1' && wa.handround == '1'}">
				<li><a class="btn btn-blue" onclick="TongYi()">已阅</a></li>
			</c:if>
			<c:if test="${wa.haveopinionfield == '1' && wa.haveopinionfield == '1'}">
				<li><a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()">填写意见</a></li>
			</c:if>
			<c:if test="${wa.sealword == '1'}">
				<li><a class="btn btn-blue" onclick="DoSignature()">签章</a></li>
			</c:if>
			<li class="line"></li>
			<c:if test="${wa.specialsend == '1'}">
				<li><a class="btn btn-default" data-icon="fa-plane" onclick="TeSong()">特送</a></li>
			</c:if>
			<c:if test="${wa.endprocess == '1'}">
				<li><a class="btn btn-red" data-icon="fa-share-square" onclick="ZhongZhi()">撤销</a></li>
			</c:if>
			<c:if test="${wa.atype != '1'}">
				
				<c:if test="${wa.backlaststep == '1'}">
					<li><a class="btn btn-red" data-icon="fa-share-square" onclick="TuiHuiShangBu()">退回</a></li>
				</c:if>
				<c:if test="${wa.backfirststep == '1'}">
					<li><a class="btn btn-red" data-icon="fa-share-square" onclick="TuiHuiNiGao()">退回经办人</a></li>
				</c:if>
			</c:if>
			<c:if test="${wa.atype == '3'}">
					<li><a class="btn btn-default" data-icon="save" onclick="WanCheng()">完成</a></li>
			</c:if>
			<c:if test="${wa.atype != '3'}">
					<li><a class="btn btn-blue" data-icon="check" onclick="FaSong()">提交</a></li>
			</c:if>
			
			<c:if test="${canedit || wa.haveopinionfield == '1'}">
				<li><a class="btn btn-default" data-icon="save" onclick="BaoCunTuiChu()">保存</a></li>
			</c:if>
		</c:if>
	</ul>
</div>
<script>
    $(document).ready(function() {
        //年假行的处理
        handleVacationTypeTr();
        $("#type").change(function(){
            handleVacationTypeTr();
        });
    });

    function handleVacationTypeTr(){
        var vacationType = $("#type  option:selected").val();
        if(vacationType == "年休假"){
            $("#annualVacationTr").show();
        }else{
            $("#annualVacationTr").hide();
        }
    }
</script>
</body>
</html>	