<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<<script type="text/javascript">


function sendback() {
		var popinion = $("#opinion10");
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
	
		popinion.val($("#writeopinion").val());
		$("#opinion3_1").html(
		$("#opinion3").val() + "&nbsp;&nbsp;" + $("#writeopinion").val() +  "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + $("#curuser1").val() + "&nbsp;&nbsp;[" + optime + "]<br>");
		$(this).dialog("closeCurrent");
	}
</script>
<div class="bjui-pageContent">
	<form method="post" action="" class="pageForm" data-toggle="validate">
		<table class="table table-hover">
		<tr>
			<td style="width:15%">填写意见：</td>
			<td style="width:85%"><textarea id="writeopinion" rows="7" style="width:100%"></textarea></td>
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