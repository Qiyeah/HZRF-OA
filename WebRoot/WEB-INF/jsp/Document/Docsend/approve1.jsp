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
<!-- <link href="css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" media="screen" /> -->
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
.hide_border {
	border:1px;;
	
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
			theme : 'sky'
		});	
	});
    function unitwh() { // 编辑文号
     // 只有编号环节才触发事件
         //if($("#nextitemid").val() == 56 || $("#nextitemid").val() == 33){
             if($("#wh1").val()!="wait"){
                var year = $("#wh2").val().replace("〔","").replace("〕","");
                var url = "Main/Docsend/getwh?boferStr=" + $("#wh1").val() + "&year=" + year;	
                var num = "";
                 $.post(encodeURI(url), function(data){
                    if(data){
                       num = data;
			           $("#wh3").val(num);
			           $("#wh").val($("#wh1").find("option:selected").text() + $("#wh2").val() + num + $("#wh4").val());
			        }
			    },"text"); 
			 } else {
		         $("#wh").val("");
		     }
	    //};
	   // $("#wh").val($("#wh1").val() + $("#wh2").val() + $("#wh3").val() + $("#wh4").val());
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
		
	}
	function closeit() {
		window.parent.$.pdialog.close("approve_dialog");	
	}
	
	function hasFill() {
		return true;
	}
	
	function BianJiZhengWen() {
		var templateid = $("#templateid").val();
		var title = $("#title").val();
		var docno = $("#wh").val();
		var send1 = $("#send1").val();
		var send2 = $("#send2").val();
		var num = $("#num").val();
		var url = encodeURI("Main/Office/edit?RecordID=${dc.pid}&EditType=1,0&Template=" + templateid + "&Title=" + title + "&Docno=" + docno + "&Send1=" + send1 + "&Send2=" + send2 + "&Num=" + num);		
		window.parent.$(this).dialog({
			id : 'editword',
			url : url,
			title : '正文拟稿',
			mask : 'true',
			drawable : 'true',
			resizable : 'false',
			maxable : 'false',
			max:'true'
			
		});
	}
	
	function ShenHeZhengWen() {
		var url = "Main/Office/edit?RecordID=${dc.pid}&EditType=2,0";	
		//var url = "Main/Office/edit?RecordID=${tempid}&EditType=1,0&Template=" + templateid + "&Title=" + title + "&Docno=" + docno + "&Send1=" + send1 + "&Send2=" + send2 + "&Num=" + num;
		window.parent.$(this).dialog({
			id : 'editword',
			url : url,
			title : '正文核稿',
			mask : 'true',
			drawable : 'true',
			resizable : 'false',
			maxable : 'false',
			max:'true'
		});
	}
	
	function ChaKanZhengWen() {
		var url = "Main/Office/view?RecordID=${dc.pid}&EditType=1,0";		
		window.parent.$(this).dialog({
			id : 'editword',
			url : url,
			title : '查看正文',
			mask : 'true',
			drawable : 'true',
			resizable : 'false',
			maxable : 'false',
			max:'true'
		});
	}
</script>
</head>
<body>
<div id="navTab" class="bjui-pageContent unitBox">
	<form id="approveForm" action="Main/Docsend/save1" method="post" data-toggle="validate">
		<div class="pageFormContent">
		  <input type="hidden" name="DocumentID" value="${dc.pid}">
			<input type="hidden" name="t_Doc_Send.id" value="${dc.id}" />
			<input type="hidden" name="t_Doc_Send.u_id" id="userid" value="${dc.u_id}" />
			<input type="hidden" name="t_Doc_Send.d_id" value="${d_id}" />
			<input type="hidden" name="t_Doc_Send.opinion1" id="opinion1" value="${dc.opinion1}" />
			<input type="hidden" name="t_Doc_Send.opinion2" id="opinion2" value="${dc.opinion2}" />
			<input type="hidden" name="t_Doc_Send.opinion3" id="opinion3" value="${dc.opinion3}" />
			<input type="hidden" name="t_Doc_Send.opinion4" id="opinion4" value="${dc.opinion4}" />
			<input type="hidden" name="t_Doc_Send.opinion5" id="opinion5" value="${dc.opinion5}" />
			<input type="hidden" name="t_Doc_Send.pid" id="pid" value="${dc.pid }" />
			<input type="hidden" name="t_Doc_Send.pstatus" value="${dc.pstatus }" />
			<input type="hidden"  id="templateid"  value="${templateid}"/>
			
			<div style="width: 100%;height: 40px;text-align:center;margin-top: 10px">
		    	<span style="font-size:25px; margin-left: 40px"><B>发文呈批笺</B></span>
			</div>
			<table class="wordInfo" align="center" style="width: 98%;margin-bottom:50px">				
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">拟稿科室</td>
					<td style="text-align: center;">${startdept}</td> 
					<td class="title" style="height: 40px;font-size:15px;">文　号</td>
					<td colspan="3" style="text-align: left;">
					 <c:if test="${empty dc.docno}">
					 <div class="noprint">
					 <select id="wh1" name="wh1" data-toggle="selectpicker" onchange="unitwh()">
					     <option value="">请选择</option>
						<c:forEach items="${docnoList}" var="docnoList" >
						<option value="${docnoList.name}">${docnoList.name}</option>
						</c:forEach>
					</select>
					<input id="wh2" name="wh2" value="〔${nowyear}〕" size="10" onchange="unitwh()"/>
					<input id="wh3" name="wh3" value="" size="15" onchange="unitwh()" />
					<select id="wh4" name="wh4" data-toggle="selectpicker" onchange="unitwh()">
						<option value="号">号</option>
						<option value="期">期</option>
					</select>
					</div>
					
					<input type="hidden" name="t_Doc_Send.docno" id="wh" value="${dc.docno }" style="text-align: center;width: 100%;" />
					</c:if>
					<c:if test="${ !empty dc.docno}">
					<input  name="t_Doc_Send.docno" id="wh" value="${dc.docno }" style="text-align: center;width: 100%;" />
					</c:if>
					</td>
				</tr>
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">拟稿时间</td>
					<td style="text-align: center;" colspan="3" id="t_Doc"><input name="t_Doc_Send.approvedate" id="t_Doc_Sendapprovedate" type="text" value="${dc.approvedate}" class="required" data-toggle="datepicker" data-rule="required;date" data-msg-date="格式错误" data-msg-required="必填" style="width:100%;"/></td>
					<td class="title" style="height: 40px;font-size:15px;">密　级</td>
					<td style="text-align: center;">
					   <select name="t_Doc_Send.security" data-toggle="selectpicker" data-rule="required"  data-msg-required="必选" data-width="100%" style="width:100%">
                            <option value="无" <c:if test="${dc.security=='无' }">selected="selected"</c:if>>无</option>
                            <option value="秘密" <c:if test="${dc.security=='秘密' }">selected="selected"</c:if>>秘密</option>
                        </select>
					</td>
				</tr>
				<tr>
					<td width="16%" class="title" style="height: 40px;font-size:15px;">拟稿人</td>
					<td width="17%" style="text-align: center;">${starter}</td>
					<td width="16%" class="title" style="height: 40px;font-size:15px;">校稿人</td>
					<td width="17%"><input name="jbr.username" type="text" class="required" data-rule="required" data-msg-required="必填" suggestFields="username"
                                           suggestUrl="Main/search/searchUser" lookupGroup="jbr" value="${dc.proof}" style="width: 100%;text-align: center;" /></td>
					<td width="16%" class="title" style="height: 40px;font-size:15px;">份　数</td>
					<td width="17%"><input name="t_Doc_Send.num" id="num" class="number" value="${dc.num }" type="text"
                                           style="line-height:20px;width: 85%;text-align: center;" /> 份</td>
				</tr>
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">主　送</td>
					<td colspan="5"><input name="sendDept1.send1" id="send1" type="text" class="required" data-rule="required"  data-msg-required="必填" suggestFields="send1"
                                           suggestUrl="Main/search/send1" lookupGroup="sendDept1" value="${dc.send1}"
                                           style="text-align: center;width: 100%" /></td>
				</tr>
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">抄　送</td>
					<td colspan="5"><input name="sendDept2.send2" id="send2" type="text"  suggestFields="send2"
                                           suggestUrl="Main/search/send2" lookupGroup="sendDept2" value="${dc.send2}"
                                           style="text-align: center;width: 100%;line-height:20px;" /></td>
				</tr>
				<tr>
					<td class="title" style="height: 60px;font-size:15px;">文件标题</td>
					<td colspan="5"><input name="t_Doc_Send.title" id="title" type="text" value="${dc.title }" class="required" data-rule="required"  data-msg-required="必填"
                                           style="text-align: center;font-family: '方正小标宋'; font-size: 20px;height: 50px; font-weight: bold;
                                            width: 100%" /></td>
				</tr>
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">正　文</td>
					<td colspan="5">
					    <c:if test="${wa.atype == '1'}">
				           <a class="btn btn-blue" data-icon="eye" onclick="BianJiZhengWen()" style="margin-left: 5px;">&nbsp;&nbsp;查&nbsp;&nbsp;看&nbsp;&nbsp;正&nbsp;&nbsp;文&nbsp;&nbsp;</a>
			            </c:if>
						<c:if test="${wa.atype == '2'}">
				           <a class="btn btn-blue" data-icon="eye" onclick="ShenHeZhengWen()" style="margin-left: 5px;">&nbsp;&nbsp;查&nbsp;&nbsp;看&nbsp;&nbsp;正&nbsp;&nbsp;文&nbsp;&nbsp;</a>
			            </c:if>
			            <c:if test="${wa.atype == '3'}">
				           <a class="btn btn-blue" data-icon="eye" onclick="ChaKanZhengWen()" style="margin-left: 5px;">&nbsp;&nbsp;查&nbsp;&nbsp;看&nbsp;&nbsp;正&nbsp;&nbsp;文&nbsp;&nbsp;</a>
			            </c:if>
			               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请在此处审阅正文
					</td>
				</tr>
				<tr>
					<td class="title">附件列表</td>
					<td colspan="5"><div id="uploadFile" class="noprint"><c:import url="../../Common/Attachment/edit.jsp" /></div></td>
				</tr>				
				<tr>
					<td class="title"><br>批办意见<br><br>及<br><br>领导批示<br></td><td colspan="6"><div id="opinion1_1">${opinion1 }</div><c:if test="${wa.haveopinionfield == '1'}">
						<div class="noprint">
						<br/>
						<a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
						<a class="btn btn-blue" data-icon="fa-check"  onclick="FaSong()" style="margin-left: 5px;">提交</a>
					</div>
					</c:if>
					</td>
				</tr>
				<%-- <c:if test="${d_id != '2'}">
				<tr>
					<td class="title" style="height: 140px;font-size:15px" >科　室<br>意　见</td>
					<td colspan="5"><div id="opinion1_1">${opinion1 }</div>
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion1'}">
					<div class="noprint">
						<br/>
						<a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
						<c:if test="${wa.atype != '3'}">
							<c:if test="${wa.amount == 3}">
					             <a class="btn btn-blue" data-icon="check" onclick="ChuLiWanBi()" style="margin-left: 5px;">提交</a>
					          </c:if>
					          <c:if test="${wa.amount != 3}">
					             <a class="btn btn-blue" data-icon="check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
					          </c:if>
						</c:if>	
						</div>				
					</c:if>	
					</td>
				</tr>
				<c:if test="${wa.opinionfield == 'opinion5'}">
				<tr>
					<td class="title" style="height: 140px;font-size:15px" >相关科室<br>意　见</td>
					<td colspan="5"><div id="opinion5_1">${opinion5 }</div>
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion5'}">
					<div class="noprint">
						<br/>
						<a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
						<c:if test="${todomannum > 1}">
					          <c:if test="${wa.amount != 3}">
					             <a class="btn btn-blue" data-icon="check" onclick="ChuLiWanBi()" style="margin-left: 5px;">提交</a>
					          </c:if>
					          <c:if test="${wa.amount == 3}">
					             <a class="btn btn-blue" data-icon="check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
					          </c:if>
				        </c:if>
				        <c:if test="${todomannum <= 1}">
					      <c:if test="${wa.atype == '3'}">
						      <a class="button" onclick="WanCheng()" style="margin-left: 5px;">完成</a>
					      </c:if>
					      <c:if test="${wa.atype != '3'}">
						      <a class="btn btn-blue" data-icon="check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
					      </c:if>
				          </c:if>	
						</div>				
					</c:if>	
					</td>
				</tr>
				</c:if>
				<c:if test="${wa.opinionfield != 'opinion5'}">
				<c:if test="${opinion5 != ''}">
				<tr>
					<td class="title" style="height: 140px;font-size:15px" >相关科室<br>意　见</td>
					<td colspan="5"><div id="opinion5_1">${opinion5 }</div>
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion5'}">
					<div class="noprint">
						<br/>
						<a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
						<c:if test="${wa.atype != '3'}">
							<a class="btn btn-blue" data-icon="check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
						</c:if>	
						</div>				
					</c:if>	
					</td>
				</tr>
				</c:if>
				</c:if>
				<tr>
					<td class="title" style="height: 140px;font-size:15px">办公室<br>意　见</td>
					<td colspan="5"><div id="opinion2_1">${opinion2 }</div>
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion2'}">
					<div class="noprint">
						<br/>
						<a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
						<c:if test="${wa.sealword == '1'}">
						<a class="button" onclick="DoSignature()" style="margin-left: 5px;">签章</a>
					    </c:if>
						<c:if test="${wa.atype != '3'}">
							<a class="btn btn-blue" data-icon="check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
						</c:if>	
						</div>					
					</c:if>	
					</td>
				</tr>
				</c:if>
				<c:if test="${d_id == '2'}">
				
				<tr>
					<td class="title" style="height: 140px;font-size:15px" >办公室<br>意　见</td>
					<td colspan="5"><div id="opinion1_1">${opinion1 }</div>
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion1'}">
					<div class="noprint">
						<br/>
						<a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
						<c:if test="${wa.atype != '3'}">
							<a class="btn btn-blue" data-icon="check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
						</c:if>	
						</div>				
					</c:if>	
					</td>
				</tr>
				
				<c:if test="${wa.opinionfield == 'opinion5'}">
				<tr>
					<td class="title" style="height: 140px;font-size:15px" >相关科室<br>意　见</td>
					<td colspan="5"><div id="opinion5_1">${opinion5 }</div>
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion5'}">
					<div class="noprint">
						<br/>
						<a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
						<c:if test="${todomannum > 1}">
					          <c:if test="${wa.amount != 3}">
					             <a class="btn btn-blue" data-icon="check" onclick="ChuLiWanBi()" style="margin-left: 5px;">提交</a>
					          </c:if>
					          <c:if test="${wa.amount == 3}">
					             <a class="btn btn-blue" data-icon="check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
					          </c:if>
				        </c:if>
				        <c:if test="${todomannum <= 1}">
					      <c:if test="${wa.atype == '3'}">
						      <a class="button" onclick="WanCheng()" style="margin-left: 5px;">完成</a>
					      </c:if>
					      <c:if test="${wa.atype != '3'}">
						      <a class="btn btn-blue" data-icon="check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
					      </c:if>
				          </c:if>	
						</div>				
					</c:if>	
					</td>
				</tr>
				</c:if>
				<c:if test="${wa.opinionfield != 'opinion5'}">
				<c:if test="${opinion5 != ''}">
				<tr>
					<td class="title" style="height: 140px;font-size:15px" >相关科室<br>意　见</td>
					<td colspan="5"><div id="opinion5_1">${opinion5 }</div>
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion5'}">
					<div class="noprint">
						<br/>
						<a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
						<c:if test="${wa.atype != '3'}">
							<a class="btn btn-blue" data-icon="check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
						</c:if>	
						</div>				
					</c:if>	
					</td>
				</tr>
				</c:if>
				</c:if>
				
				</c:if>
				<tr>
					<td class="title" style="height: 140px;font-size:15px">分　管<br>副部长<br>批　示</td>
					<td colspan="5"><div id="opinion3_1">${opinion3 }</div>
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion3'}">
					<div class="noprint">
						<br/>
						<a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
						<c:if test="${wa.sealword == '1'}">
						<a class="button" onclick="DoSignature()" style="margin-left: 5px;">签章</a>
					    </c:if>
						<c:if test="${todomannum > 1}">
					          <c:if test="${wa.amount != 3}">
					             <a class="btn btn-blue" data-icon="check" onclick="ChuLiWanBi()" style="margin-left: 5px;">提交</a>
					          </c:if>
					          <c:if test="${wa.amount == 3}">
					             <a class="btn btn-blue" data-icon="check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
					          </c:if>
				        </c:if>
				        <c:if test="${todomannum <= 1}">
					      <c:if test="${wa.atype == '3'}">
						      <a class="btn btn-default" data-icon="save" onclick="WanCheng()" style="margin-left: 5px;">完成</a>
					      </c:if>
					      <c:if test="${wa.atype != '3'}">
						      <a class="btn btn-blue" data-icon="check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
					      </c:if>
				          </c:if>
					</div>
					</c:if>	
					</td>
				</tr>
				<tr>
					<td class="title" style="height: 140px;font-size:15px">部　长<br>批　示</td>
					<td colspan="5"><div id="opinion4_1">${opinion4 }</div>
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion4'}">
					<div class="noprint">
						<br/><br/>
						<a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
						<c:if test="${wa.sealword == '1'}">
						<a class="button" onclick="DoSignature()" style="margin-left: 5px;">签章</a>
					    </c:if>
						<c:if test="${wa.atype != '3'}">
							<a class="btn btn-blue" data-icon="check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
						</c:if>
					</div>
					</c:if>
					</td>
				</tr> --%>
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
		
		</div>
	</form>
</div>
 <div id="bar" class="bjui-pageFooter" >
	<ul>
		<li><a class="btn btn-default" data-icon="fa-edit" onclick="LiuZhuanJiLu()">记录</a></li>
		<c:if test="${wkit1 != null}">
			<li><a class="btn btn-blue" onclick="QuHui()">取回</a></li>
		</c:if>
		<c:if test="${candeal}">
			
			<c:if test="${wa.haveopinionfield == '1' && wa.handround == '0'}">
				<li><a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()">填写意见</a></li>
			</c:if>
			<c:if test="${wa.sealword == '1'}">
				<li><a class="btn btn-blue" onclick="DoSignature()">签章</a></li>
			</c:if>
			<li class="line"></li>
			<c:if test="${wkit1 != null}"><li><a class="btn btn-default" data-icon="fa-rotate-right" onclick="QuHui()">取回</a></li></c:if>
			<c:if test="${wa.specialsend == '1'}">
				<li><a class="btn btn-default" data-icon="fa-plane" onclick="TeSong()">特送</a></li>
			</c:if>
			<c:if test="${wa.endprocess == '1'}">
				<li><a class="btn btn-red" data-icon="fa-share-square" onclick="ZhongZhi()">撤销</a></li>
			</c:if>
			
			<c:if test="${todomannum > 1}">
					<c:if test="${wa.atype == '3'}">
						<li><a class="btn btn-default" data-icon="save" onclick="WanCheng()">完成</a></li>
					</c:if>
					<c:if test="${wa.atype != '3'}">
					    <c:if test="${wa.amount != 3}">
						<li><a class="btn btn-blue" data-icon="check" onclick="ChuLiWanBi()">提交</a></li>
						</c:if>
						 <c:if test="${wa.amount == 3}">
						<li><a class="btn btn-blue" data-icon="check"onclick="FaSong()">提交</a></li>
						</c:if>
					</c:if>
				</c:if>
				<c:if test="${todomannum <= 1}">
					<c:if test="${wa.atype == '3'}">
						<li><a class="btn btn-default" data-icon="save" class="button" onclick="WanCheng()">完成</a></li>
					</c:if>
					<c:if test="${wa.atype != '3'}">
						<li><a class="btn btn-blue" data-icon="check" onclick="FaSong()">提交</a></li>
					</c:if>
				</c:if>
			<c:if test="${wa.haveopinionfield == '1'}">
				<li><a class="btn btn-default" data-icon="save" onclick="BaoCunTuiChu()">保存</a></li>
			</c:if>
			<c:if test="${wa.atype != '1'}">
				
				<c:if test="${wa.backlaststep == '1'}">
					<li><a class="btn btn-red" data-icon="fa-share-square" onclick="TuiHuiShangBu()">退回</a></li>
				</c:if>
				<c:if test="${wa.backfirststep == '1'}">
					<li><a class="btn btn-red" data-icon="fa-share-square" onclick="TuiHuiNiGao()">退回经办人</a></li>
				</c:if>
			</c:if>
		</c:if>
	</ul>
</div> 
</body>
</html>	