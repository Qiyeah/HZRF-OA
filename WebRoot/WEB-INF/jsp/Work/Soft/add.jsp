<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent tableContent" >
	<form id="approveForm" action="Main/Soft/add" method="post" class="pageForm" data-toggle="validate">
		<input type="hidden" name="flag" value="0" /> <input type="hidden" name="t_Soft.u_id" value="${u_id}" />
		<table class="wordInfo" align="center" style="width: 95%;margin-top:23px">
			<tr>
				<td width="25%" class="title">软件名</td>
				<td width="75%"><input type="text" name="t_Soft.softname" class="required" data-rule="required" data-msg-required="必填" maxlength="100" style="width: 100%" /></td>
			</tr>
			<tr>
				<td class="title">软件描述</td>
				<td><textarea name="t_Soft.softdetail" maxlength="250" rows="7" style="width: 100%; overflow: auto"></textarea></td>
			</tr>
			<tr>
				<td class="title">文件列表</td>
				<td colspan="3">
					<%@ include file="../../Common/Attachment/addrequired.jsp"%>
				</td>
			</tr>
		</table>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn btn-close" data-icon="close">关闭</button></li>
		<li><button type="submit" class="btn btn-default" data-icon="save">保存</button></li>
	</ul>
</div>