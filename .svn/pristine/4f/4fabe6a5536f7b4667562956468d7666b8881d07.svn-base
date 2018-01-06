<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<%
	String basePath = request.getContextPath() + "/";
%>
<html>
<head>

<script type="text/javascript">
	<%-- function LiuZhuanJiLu() { // “流程记录”操作
		window.parent.$.pdialog.open("<%=basePath%>Main/Workflow/showsteps/${la.pid}", "ShowSteps", "流转记录", {mask:true,drawable:true,resizable:false,width:700,height:400});
	}
	 --%>
	function PrintDocument() {
		document.all.bar.style.display = "none";
		//var DocForm = document.forms[0];
		//var mCount = DocForm.SignatureControl.PrintDocument(false, 1, 2); // 打印控制窗体
		window.print();
		alert("实际打印份数：" + mCount);
		document.all.bar.style.display = "";
	}
	function print1() { //打印函数         
        pagesetup_null(); //打印之前去掉页眉，页脚 
        setdivhidden("div1"); //打印之前先隐藏不想打印输出的元素 
        var WebBrowser = '<OBJECT ID="WebBrowser1" WIDTH=0 HEIGHT=0 CLASSID="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></OBJECT>';
        document.body.insertAdjacentHTML('beforeEnd', WebBrowser); //在body标签内加入html（WebBrowser activeX控件） 
        WebBrowser1.ExecWB(6, 1); //打印 
        WebBrowser1.outerHTML = ""; //从代码中清除插入的html代码 
        pagesetup_default(); //打印结束后页眉页脚恢复默认值 
        setdivvisible("div1"); //打印结束后显示按钮 
    } 
</script>
</head>
<body>
  <div class="bjui-pageContent">
	<form id="DocForm">
		<input type="hidden" id="id" name="" value="${la.pid}">
		<input type="hidden" name="DocumentID" value="${la.pid}">
            <div style="width: 95%;height: 40px;text-align:center">
                <span style="font-size:25px; margin-left:40px"><B>请休假审批单</B></span>
            </div>			
        	<table class="wordInfo" align="center" style="width: 95%; margin-bottom: 6px">
				<tr>
					<td style="width: 15%;" class="title">申 请 人</td>
					<td style="width: 35%;" align="center">${starter}</td>
					<td style="width: 15%;" class="title">所在科室</td>
					<td style="width: 35%;" align="center">${startdept}</td>
				</tr>
				<tr>
					<td class="title">请休假类型</td>
					<td align="center">${la.type}</td>
					<td class="title">婚姻状态</td>
					<td align="center">${married}</td>
				</tr>
				<tr>
					<td class="title">申请时间</td>
					<td style="text-align: center;">${la.approvedate}</td>
					<td rowspan="2" class="title">请休假天数</td>
					<td rowspan="2" style="text-align: center;">${la.dayt }</td>
				</tr>
				<tr>
					<td class="title">起止时间</td>
					<td style="text-align: center;">${la.begindate } - ${la.enddate }</td>
				</tr>
				<tr>
					<td class="title" style="height:50px">请休假事由</td>
					<td colspan="3" style="text-align: center;">${la.reason }</td>
				</tr>
				<tr class="noprint">
					<td class="title">附件列表</td>
					<td colspan="3"><div id="uploadFile" class="unitBox"><c:import url="../../Common/Attachment/view.jsp" /></div></td>
				</tr>
				<tr>
					<td class="title" style="height: 80px">科　　室<br>意　　见</td>
					<td colspan="3"><div id="opinion1_1" class="opinion">${opinion1 }</div></td>
				</tr>
				<tr>
					<td class="title" style="height: 120px">副    主    任<br>意　　见</td>
					<td colspan="3"><div id="opinion2_1" class="opinion">${opinion2 }</div></td>
				</tr>
				<tr>
					<td class="title" style="height: 120px">主　　任<br>意　　见</td>
					<td colspan="3"><div id="opinion3_1" class="opinion">${opinion3 }</div></td>
				</tr>
				<tr>
					<td class="title">销假时间</td>
					<td style="text-align: center;">${la.backdate}</td>
					<td class="title">实际天数</td>
					<td style="text-align: center;">${la.days}</td>
				</tr>
			</table>
	</form>
	</div>
	<div class="bjui-pageFooter">
			<ul>
				<li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
				<li><a class="btn btn-default" data-icon="fa-edit" onclick="LiuZhuanJiLu()"><span>记录</span></a></li>
			</ul>
        </div>
</body>
</html>