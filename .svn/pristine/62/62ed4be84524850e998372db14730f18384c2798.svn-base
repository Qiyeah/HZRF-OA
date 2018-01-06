<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<style>
.grid .gridTbody td div {
	overflow: inherit;
	height: 30px;
}
</style>
<div class="bjui-pageHeader" style="height: 30px;"></div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top " data-toggle="tablefixed">
		<thead>
			<tr>
				<th width="3%" align="center">序号</th>
				<th width="3%" align="center">选择</th>
				<th width="25%" align="left">铃声</th>
			</tr>
		</thead>
		<tbody>
			<tr style="height: 40px;">
				<td align="center">1</td>
				<td align="center"><input type="radio" name="sound.voice_id" data-toggle="icheck" value="1"
					<c:if test="${sound.voice_id==1}">checked="checked"</c:if>></td>
				<td class="img" align="left"><img src="images/1.png" height="30" width="30" onclick="voiceReplace(1)" /></td>
			</tr>
			<tr style="height: 40px;">
				<td align="center">2</td>
				<td align="center"><input type="radio" name="sound.voice_id" data-toggle="icheck" value="2"
					<c:if test="${sound.voice_id==2}">checked="checked"</c:if>></td>
				<td class="img" align="left"><img src="images/1.png" height="30" width="30" onclick="voiceReplace(2)" /></td>
			</tr>
			<tr style="height: 40px;">
				<td align="center">3</td>
				<td align="center"><input type="radio" name="sound.voice_id" data-toggle="icheck" value="3"
					<c:if test="${sound.voice_id==3}">checked="checked"</c:if>></td>
				<td class="img" align="left"><img src="images/1.png" height="30" width="30" onclick="voiceReplace(3)" /></td>
			</tr>
			<tr style="height: 40px;">
				<td align="center">4</td>
				<td align="center"><input type="radio" name="sound.voice_id" data-toggle="icheck" value="4"
					<c:if test="${sound.voice_id==4}">checked="checked"</c:if>></td>
				<td class="img" align="left"><img src="images/1.png" height="30" width="30" onclick="voiceReplace(4)" /></td>
			</tr>
			<tr style="height: 40px;">
				<td align="center">5</td>
				<td align="center"><input type="radio" name="sound.voice_id" data-toggle="icheck" value="5"
					<c:if test="${sound.voice_id==5}">checked="checked"</c:if>></td>
				<td class="img" align="left"><img src="images/1.png" height="30" width="30" onclick="voiceReplace(5)" /></td>
			</tr>
		</tbody>
	</table>
	<div class="panelBar"></div>
</div>
<a id="setSoundLink" style="display: none;" href="Main/Sound/update/" data-toggle="doajax"></a>
<script type="text/javascript">
/* 	$('input[name="sound.voice_id"]').on('ifChanged', function(e) {
		var checked = $(this).is(':checked'), val = $(this).val()
		if (checked)
			setSound(val);
	}); */

	$('input[name="sound.voice_id"]').on('ifChanged', function(e) {
		var checked = $(this).is(':checked'), val = $(this).val()
		if (checked)
			$(this).alertmsg('info', '你选择了' + val)
		else
			$(this).alertmsg('info', '你取消了' + val)
	});

	function voiceReplace(no) {
		if (no == 1) {
			$("#bgsound").attr("src", "sound/1.wav");
			$("#music").attr("src", "sound/1.wav");
		} else if (no == 2) {
			$("#bgsound").attr("src", "sound/2.wav");
			$("#music").attr("src", "sound/2.wav");
		} else if (no == 3) {
			$("#bgsound").attr("src", "sound/3.wav");
			$("#music").attr("src", "sound/3.wav");
		} else if (no == 4) {
			$("#bgsound").attr("src", "sound/4.wav");
			$("#music").attr("src", "sound/4.wav");
		} else if (no == 5) {
			$("#bgsound").attr("src", "sound/5.wav");
			$("#music").attr("src", "sound/5.wav");
		}
	}

	function setSound(no) {
		alert(no);
		voiceReplace(no);
		var url = "Main/Sound/update/";
		$("#setSoundLink").attr("href", url + no);
		$("#setSoundLink").click();
	}
</script>
