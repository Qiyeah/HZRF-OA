<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	function tongyi() {
		$("#writeopinion").val("同意。");
		sendback();
	}
	function yihe() {
		$("#writeopinion").val("已核。");
		sendback();
	}
	function selectopinion() {
		$("#writeopinion").val($("#usedopinion").val());
	}
	function sendback() {
		var pfield = $("#opinionfield", window.parent.document).val();
		var popinion = $("#opinion", window.parent.document);
		var ptime = $("#opiniontime", window.parent.document);
		var timer = new Date();
		var mon, day, hour, minu, secd;
		if (timer.getMonth() < 9)
			mon = "0" + (timer.getMonth() + 1);
		else
			mon = (timer.getMonth() + 1);
		if (timer.getDate() < 10)
			day = "0" + timer.getDate();
		else
			day = timer.getDate();
		if (timer.getHours() < 10)
			hour = "0" + timer.getHours();
		else
			hour = timer.getHours();
		if (timer.getMinutes() < 10)
			minu = "0" + timer.getMinutes();
		else
			minu = timer.getMinutes();
		if (timer.getSeconds() < 10)
			secd = "0" + timer.getSeconds();
		else
			secd = timer.getSeconds();
		var optime = timer.getFullYear() + "-" + mon + "-" + day + " " + hour + ":" + minu + ":" + secd;
		ptime.val(optime);
		popinion.val($("#writeopinion").val());
		$("#" + pfield + "_1", window.parent.document).html(
		$("#writeopinion").val() + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + $("#curuser1", window.parent.document).val() + "&nbsp;&nbsp;[" + optime + "]<br>");
		$(this).dialog("closeCurrent");
	}
</script>

<div class="bjui-pageContent">
	<form method="post" action="" class="pageForm" data-toggle="validate">
		<table class="table table-hover">
		<tr>
			<td width="15%">常用意见：</td>
			<td width="85%">
				<select id="usedopinion" data-toggle="selectpicker" data-width="90%" onchange="selectopinion()">					
					<option value="">--请选择常用意见--</option>
					<c:forEach items="${opinions}" var="temp">
						<option value="${temp.name}">${temp.name}</option>
					</c:forEach>
					<c:forEach items="${myopinions}" var="mytemp">
						<option value="${mytemp.opinion}">${mytemp.opinion}</option>
					</c:forEach>						
				</select>
			</td>
		</tr>
		<tr>
			<td>填写意见：</td>
			<td><textarea id="writeopinion" rows="7" style="width:90%">${opinion}</textarea></td>
		</tr>
	</table>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
        <li><button type="button" class="btn-default" data-icon="save" onclick="sendback()">确定</button></li>
    </ul>
</div>
<script type="text/javascript">
	$("#writeopinion").focus();
	$("#writeopinion").click();
</script>