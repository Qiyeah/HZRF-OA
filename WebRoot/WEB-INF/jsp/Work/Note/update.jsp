<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<style>
<!--
form#pageForm1 span.wrap_bjui_btn_box{
	width:100%;
}
-->
</style>
<div class="bjui-pageContent">
		<form id="pageForm1" class="pageForm" action="Main/Note/update" method="post" data-toggle="validate">
		<input type="hidden" name="t_Note.id" value="${note.id}" />
		<input type="hidden" name="t_Note.u_id" value="${note.u_id}" />
		<div class="pageFormContent" layoutH="56">
			<table class="wordInfo" align="center" style="width: 98%;">
				<tr>
					<td width="25%" class="title">创建日期</td>
					<td width="75%"><input type="text" name="t_Note.wdate" value="${note.wdate }" class="required" data-toggle="datepicker" data-rule="required date" data-msg-date="格式错误" data-msg-required="必填" maxlength="13" style="width: 100%;"/></td>
				</tr>
				<tr>
					<td class="title">类　　型</td>
					<td><input type="text" name="t_Note.type" value="${note.type }" style="width: 100%" maxlength="50"/></td>
				</tr>
				<tr>
					<td class="title">标　　题</td>
					<td><input type="text" name="t_Note.title" value="${note.title }" class="required" data-rule="required" data-msg-required="必填" maxlength="150" style="width: 100%" /></td>
				</tr>
				<tr>
					<td class="title">内　　容</td>
					<td><textarea name="t_Note.content" rows="10" style="width: 100%; overflow:auto">${note.content }</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li>
			<button type="button" class="btn-close" data-icon="close">关闭</button></li>
		<li>
			<button type="submit" class="btn-default" data-icon="save">保存</button></li>
	</ul>
</div>