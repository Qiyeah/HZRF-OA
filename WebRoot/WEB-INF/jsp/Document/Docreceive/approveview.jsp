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
form#approveForm span.wrap_bjui_btn_box{
	 width:100%;  
}
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
	function BianJiZhengWen(filepath) {
		var url = encodeURI("<%=basePath%>Main/Office/edit?RecordID=${dc.pid}&EditType=1,0&FilePath="+filepath);
		window.parent.$.pdialog.open(url, "editword", "编辑草稿", {mask:true,drawable:false,resizable:false,maxable:false,width:1050,height:600});
	}

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
		//var DocForm = document.forms[0];
		//$("#bar").css("display","none");
 		$("div#navTab").removeClass(); //移除有class 
 		
		//$("input").removeClass().addClass("hide_border");
		
		//$("#navTab").css('display','block'); 
		//var printData = document.getElementById("navTab").innerHTML; //获得 div 里的所有 html 数据
		//var oldstr = document.body.innerHTML;  
		 
		//document.body.innerHTML =printData
		
		//window.print();  
		//document.body.innerHTML =  oldstr
	
		//$("#bar").css("display","");
		  $("#navTab").print({
		     append: false,
    		prepend: false
		   });
		   
		   $("div#navTab").addClass("bjui-pageContent"); //class 
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
				FaSongdoc();
			}
		}
	}
	
	function Receive() {
		var temp = window.confirm("该操作代表此文将由您负责办理,是否确认？");	
		if(temp){
			$(this).dialog({
				id : 'selna',
				url : "Main/Workflow/jieguo/" ,
				title : '请手动关掉',
				mask : true,
				drawable : false,
				resizable : false,
				maxable : false,
				width : 0,
				height : 0
			});
			$("#isreceive").val("2");
			$("#approveForm").submit();
		}else{
			return;
		}
	}
	
	function selectman() {
		var doman = $("#doman").val();
		var opinion = $("#opinion").val();
        if (opinion == "") {
            alert("请填写意见！");
            return;
        }
      
        $(this).dialog({
            id : 'selna',
            url : "Main/Docreceive/selectman/" +doman,
            title : '确认窗口',
            mask : true,
            drawable : false,
            resizable : false,
            maxable : false,
            width : 300,
            height : 400
        });
	   
	}
	
	function editopinion(){
		$(this).dialog({
			id : 'selna',
			url : "Main/Docreceive/editopinion/" ,
			title : '反馈意见',
			mask : true,
			drawable : false,
			resizable : false,
			maxable : false,
			width : 600,
			height : 300
		});
	}
	
	function editOpinion1(){
		$("#opinionfield").val("opinion1");
		TianXieYiJian();
	}
</script>
</head>
<body>
<div id="navTab" class="bjui-pageContent unitBox">
<form id="approveForm" method="post" action="Main/Docreceive/save1" data-toggle="validate">
		<div class="pageFormContent">
		    <input type="hidden" name="superdoflag" value="${superdoflag}">
			<input type="hidden" name="DocumentID" value="${dc.pid}">
			<input type="hidden" name="t_Doc_Receive.id" value="${dc.id}" />
			<input type="hidden" name="t_Doc_Receive.u_id" id="userid" value="${dc.u_id}" />
			<input type="hidden" name="t_Doc_Receive.d_id" value="${dc.d_id}" />
			<input type="hidden" name="t_Doc_Receive.receiver" value="${dc.receiver}" />
			<input type="hidden" name="zhk.auditor" value="${dc.auditor}" />
			<input type="hidden" name="t_Doc_Receive.opinion1" id="opinion1" value="${dc.opinion1}" />
			<input type="hidden" name="t_Doc_Receive.opinion2" id="opinion2" value="${dc.opinion2}" />
			<input type="hidden" name="t_Doc_Receive.degree" value="${dc.degree}" /> 
			<input type="hidden" name="t_Doc_Receive.pid" id="pid" value="${dc.pid}" />
			<input type="hidden" name="t_Doc_Receive.pstatus" value="${dc.pstatus}" />
			<c:forEach items="${fileList}" var="list"><input type="hidden" name="fjid" value="${list.id}" /></c:forEach>
			
			<div style="width: 100%;height: 60px;padding:15px;text-align:center">
		    <span style="font-size:30px; margin-left:10px"><B>文件呈批表</B></span>		    
		    </div>
			<table class="wordInfo" align="center" style="width: 95%;margin-bottom:30px">
				<tr>
					<td width="15%" class="title">来文单位</td>
					<td width="25%" colspan="2"><div style="margin:5px;">${dc.unit}</div></td>
					<td width="13%" class="title">收文日期</td>
					<td width="17%"><div style="margin:5px;">${dc.receivedate}</div></td>
					<td width="12%" class="title">办文编号</td>
					<td width="18%"><div style="margin:5px;">${dc.docno}</div></td>
				</tr>
				<tr>
					<td class="title">经办人</td><td colspan="2"><div style="margin:5px;">${receiver}</div></td>
					<td class="title">分办人</td><td><div style="margin:5px;">${dc.auditor}</div></td>
					<td class="title">处理类型</td>
					<td><div style="margin:5px;">
						<c:if test="${dc.doflag=='1'}">一般阅知</c:if>
						<c:if test="${dc.doflag=='2'}">普通收文</c:if>
						</div>
					</td>
				</tr>
				<tr>
					<td class="title">密　级</td>
					<td width="15%"><div style="margin:5px;">${dc.security}</div></td>
					<td class="title" rowspan="2">标　题</td>
					<td colspan="4" rowspan="2" class="normal" align="center" style="font-size:20px;width:100%;line-height:30px<c:if test="${dc.degree!='0'}">; color: red</c:if>">${dc.title}</td>
				</tr>
				<tr>
					<td class="title">分　数</td>
					<td><div style="margin:5px;">${dc.count}</div></td>			
				</tr>
						
				<tr>
					<td class="title">文件列表</td>
					<td colspan="6"><div id="uploadFile" class="unitBox"><c:import url="../../Common/Attachment/view1.jsp" /></div></td>
				</tr>
	            <tr>
	                <td class="title">备 注</td>
	                <td colspan="6" style="word-break: break-all;">${dc.memo}</td>
	            </tr>
				<tr>
					<td class="title"><br>批办意见<br><br>及<br><br>领导批示<br></td><td colspan="6"><div id="opinion1_1">${opinion1 }</div> 
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion1' && superdoflag !='2' && wf.isreceive=='0'}">
								<div class="noprint">
									<br /> 
									<a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
									<c:if test="${wa.sealword == '1'}">
										<a class="btn btn-blue" data-icon="fa-tag" onclick="DoSignature()" style="margin-left: 5px;">签章</a>
									</c:if>
									<c:if test="${todomannum > 1}">
										<c:if test="${wa.amount != 3 &&  (wf.itemid == '26' || wf.itemid == '24')}">
											<a class="btn btn-blue" data-icon="fa-check" onclick="ChuLiWanBi()" style="margin-left: 5px;">提交</a>
										</c:if>
										<c:if
											test="${wa.amount == 3 || (wf.iscanreceive =='1' && wf.itemid != '26' && wf.itemid != '24')  && mustreceive=='0' &&  (nextstepsnum>0 || wf.isreceive=='1'|| wf.iscanreceive=='0')}">
											<a class="btn btn-blue" data-icon="fa-check" onclick="FaSongdoc()" style="margin-left: 5px;">提交</a>
										</c:if>
									</c:if>
									<c:if test="${todomannum <= 1}">
										<c:if test="${wa.atype == '3'}">
											<a class="btn btn-default" data-icon="fa-save" onclick="WanCheng()" style="margin-left: 5px;">完成</a>
										</c:if>
										<c:if test="${wa.atype != '3' && (nextstepsnum>0 || wf.isreceive=='1' || wf.iscanreceive=='0') && mustreceive=='0'}">
											<a class="btn btn-blue" data-icon="fa-check" onclick="FaSongdoc()" style="margin-left: 5px;">提交</a>
										</c:if>
									</c:if>
								</div>
					</c:if> 
					<c:if test="${wa.id==107 && mustsubmit=='0' && candelay}">
						<a class="btn btn-blue" data-icon="fa-edit" onclick="editOpinion1()" style="margin-left: 5px;">填写意见</a>
					</c:if></td>
				</tr>
				<tr>
					<td class="title" style="height: 150px;"><br>传<br><br>阅<br><br>签<br><br>名<br></td>
					<td colspan="6"><div id="opinion2_1">${opinion2}</div>
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion2' && superdoflag !='2' && wf.isreceive=='0' }">
                                <div class="noprint">
                                    <br /> 
                                    <a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
                                    <c:if test="${wa.sealword == '1'}">
                                        <a class="btn btn-blue" data-icon="fa-tag" onclick="DoSignature()" style="margin-left: 5px;">签章</a>
                                    </c:if>
                                    <c:if test="${todomannum > 1}">
                                        <c:if test="${wa.amount != 3 &&  (wf.itemid == '26' || wf.itemid == '24')}">
                                            <a class="btn btn-blue" data-icon="fa-check" onclick="ChuLiWanBi()" style="margin-left: 5px;">提交</a>
                                        </c:if>
                                        <c:if
                                            test="${wa.amount == 3 || (wf.iscanreceive =='1' && wf.itemid != '26' && wf.itemid != '24')  && mustreceive=='0' &&  (nextstepsnum>0 || wf.isreceive=='1'|| wf.iscanreceive=='0')}">
                                            <a class="btn btn-blue" data-icon="fa-check" onclick="FaSongdoc()" style="margin-left: 5px;">提交</a>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${todomannum <= 1}">
                                        <c:if test="${wa.atype == '3'}">
                                            <a class="btn btn-default" data-icon="fa-save" onclick="WanCheng()" style="margin-left: 5px;">完成</a>
                                        </c:if>
                                        <c:if test="${wa.atype != '3' && (nextstepsnum>0 || wf.isreceive=='1' || wf.iscanreceive=='0') && mustreceive=='0'}">
                                            <a class="btn btn-blue" data-icon="fa-check" onclick="FaSongdoc()" style="margin-left: 5px;">提交</a>
                                        </c:if>
                                    </c:if>
                                </div>
					</c:if></td>
				</tr>
                <c:if test="${! empty receiverlist}">
                    <c:forEach items="${receiverlist}" var="model" varStatus="vs">
                        <tr>
                            <td class="title">签 收 人</td>
                            <td colspan="2">${model.receive_man }</td>
                            <td class="title">签收时间</td>
                            <td colspan="3"><fmt:formatDate value="${model.receive_time }" pattern="yyyy-MM-dd HH:mm"/></td>
                        </tr>
                    </c:forEach>
                </c:if>
				<tr><td class="title" style="height: 150px;"><br>办<br><br>理<br><br>情<br><br>况<br>
					<td colspan="6"><div id="opinion3_1">${opinion3}</div>
					<c:if test="${wf.isreceive=='1'&& mustreceive=='0' }">
						<a class="btn btn-blue" data-icon="fa-edit" onclick="editopinion()" style="margin-left: 5px;">填写意见</a>
						<c:if test="${todomannum > 1}">
						    <c:if test="${wa.amount != 3 && (wf.itemid == '26' || wf.itemid == '24')}">
							    <a class="btn btn-blue" data-icon="fa-check" onclick="ChuLiWanBi()" style="margin-left: 5px;">提交</a>
							</c:if>
							<c:if test="${wa.amount == 3 || (wf.iscanreceive =='1' && wf.itemid != '26' && wf.itemid != '24') && mustreceive=='0' && (nextstepsnum>0 || wf.isreceive=='1'|| wf.iscanreceive=='0')}">
							    <a class="btn btn-blue" data-icon="fa-check" onclick="FaSongdoc()" style="margin-left: 5px;">提交</a>
							</c:if>
						</c:if>
						<c:if test="${todomannum <= 1}">
						    <c:if test="${wa.atype != '3'&&(nextstepsnum>0 || wf.isreceive=='1'|| wf.iscanreceive=='0')&& mustreceive=='0'}">
							    <a class="btn btn-blue" data-icon="fa-check" onclick="FaSongdoc()" style="margin-left: 5px;">提交</a>
					        </c:if>
				        </c:if>
					</c:if></td>
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
			<input type="hidden" name="opinion" id="opinion" value="${wkit.opinion }" />
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
			
			<input type="hidden" name="iscanreceive" id="iscanreceive" value="${wf.iscanreceive }">
			<input type="hidden" name="isreceive" id="isreceive" value="${mustsubmit }">
			<input type="hidden" name="opinion3" id="opinion3" value="${opinionother }" />
			<input type="hidden" name="opinion10" id="opinion10" value="${opinion10 }" />
			<input type="hidden" name="t_Workflow.nextshoulddo"  value="${wf.nextshoulddo }" />
			<input type="hidden" name="isPoint" id="isPoint" value="${wkit.isPoint }">
			<input type="hidden" name="doman" id="doman" value="${doman }">
		</div>
	</form>
</div>



<div id="bar" class="bjui-pageFooter">
	<ul>
		<li><a class="btn btn-default" data-icon="fa-edit" onclick="LiuZhuanJiLu()">记录</a></li>
		
	    <c:if test="${superdoflag == '2'}">
	    	<li><a class="btn btn-blue" data-icon="fa-eye" type="submit">督办</a></li>
			
		</c:if>
		<c:if test="${superdoflag == '3' && superdoflag !='2'}">
			<li><a class="btn btn-blue" data-icon="fa-rotate-right" onclick="ChongZhi()">重置处理人</a></li>
		</c:if>
		<c:if test="${superdoflag != '2' && superdoflag != '3'}">
			<c:if test="${wkit1 != null}">
				<li><a class="btn btn-blue" data-icon="fa-check" onclick="QuHui()">取回</a></li>
			</c:if>
			<c:if test="${candeal}">
				<c:if test="${wa.sealword == '1'}">
					<li><a class="btn btn-blue" data-icon="fa-tag" onclick="DoSignature()">签章</a></li>
				</c:if>
				<c:if test="${wa.specialsend == '1'}">
					<li><a class="btn btn-blue" data-icon="fa-plane" onclick="TeSong()">特送</a></li>
				</c:if>
				<c:if test="${wa.endprocess == '1'}">
					<li><a class="btn btn-red" data-icon="fa-share-square" onclick="ZhongZhi()">撤销</a></li>
				</c:if>
				<c:if test="${wa.id==107 && mustsubmit=='0'}">
					<li><a class="btn btn-blue" data-icon="fa-check" onclick="Receive()">签收</a></li>
				</c:if>
				<c:if test="${wa.id==107 && mustsubmit=='0' && candelay}">
				    <li><a class="btn btn-blue" data-icon="hand-o-right" onclick="selectman()">指派</a></li>
				</c:if>
				<c:if test="${todomannum > 1}">
					<c:if test="${wa.atype == '3'}">
						<li><a class="btn btn-default" data-icon="fa-save" onclick="WanCheng()">完成</a></li>
					</c:if>
					<c:if test="${wa.atype != '3'}">
					    <c:if test="${wa.amount != 3 && (wf.itemid == '26' || wf.itemid == '24')}">
						<li><a class="btn btn-blue" data-icon="fa-check" onclick="ChuLiWanBi()">提交</a></li>
						</c:if>
						 <c:if test="${wa.amount == 3 || (wf.iscanreceive =='1' && wf.itemid != '26' && wf.itemid != '24')  && mustreceive=='0' &&  (nextstepsnum>0 || wf.isreceive=='1'|| wf.iscanreceive=='0')}">
						<li><a class="btn btn-blue" data-icon="fa-check" onclick="FaSongdoc()">提交</a></li>
						</c:if>
					</c:if>
				</c:if>
				<c:if test="${todomannum <= 1}">
					<c:if test="${wa.atype == '3'}">
						<li><a class="btn btn-blue" data-icon="fa-folder-o" onclick="WanCheng()">归档</a></li>
					</c:if>
					<c:if test="${wa.atype != '3'&& mustreceive=='0' &&  (nextstepsnum>0 || wf.isreceive=='1'|| wf.iscanreceive=='0')}">
						<li><a class="btn btn-blue" data-icon="fa-check" onclick="FaSongdoc()">提交</a></li>
					</c:if>
				</c:if>
				<c:if test="${wa.haveopinionfield == '1'}">
					<li><a class="btn btn-default" data-icon="fa-save" onclick="BaoCunTuiChu()">保存</a></li>
				</c:if>
				<c:if test="${wa.atype != '1'}">
					
					<c:if test="${wa.backlaststep == '1' && wf.isreceive=='0'}">
						<li><a class="btn btn-red" data-icon="fa-share-square" onclick="TuiHuiShangBu()">退回
						</a></li>
					</c:if>
					<c:if test="${wa.backfirststep == '1' && wf.isreceive=='0'}">
						<li><a class="btn btn-red" data-icon="fa-share-square" onclick="TuiHuiNiGao()">退回经办人</a></li>
					</c:if>
				</c:if>
			</c:if>
		</c:if>
	</ul>
</div>	
</body>
</html>		