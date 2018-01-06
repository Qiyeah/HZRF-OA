<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
	<form action="Main/InnerSend/update" class="pageForm" data-toggle="validate">
		<input type="hidden" name="t_Inner_Send.id" value="${dc.id}" /> 
		<input type="hidden" name="t_Inner_Send.u_id" value="${dc.u_id}" /> 
		<input type="hidden" name="t_Inner_Send.d_id" value="${dc.d_id}" />
		<table class="wordInfo" align="center">
			<tr>
				<td class="title" colspan="4">内部发文</td>
			</tr>
			<tr>
				<td width="15%" class="title">发文人</td>
				<td width="35%" style="text-align: center;">${uname}</td>
				<td width="15%" class="title">发文日期</td>
				<td width="35%" style="text-align: left;"><input type="text" name="t_Inner_Send.senddate" value="${dc.senddate}" data-toggle="datepicker" readonly class="required" 
				    data-rule="required date" data-msg-required="必填" style="width: 100%" /></td>
			</tr>
			<tr>
				<td class="title">来文单位</td>
				<td><input type="text" name="achieveDepart.unit" value="${dc.unit}" class="required" data-rule="required" data-msg-required="必填" maxlength="50" style="width: 100%; text-align: center;" /></td>
				<td class="title">来文文号</td>
				<td><input type="text" name="t_Inner_Send.docno" value="${dc.docno}" maxlength="50" style="width: 100%; text-align: left;" /></td>
			</tr>
			<tr>
				<td class="title">文件标题</td>
				<td colspan="3" class="normal" align="center"><input type="text" name="t_Inner_Send.title" value="${dc.title}" class="required" data-msg-required="必填"
					data-rule="required" maxlength="250" style="width: 100%; text-align: center;" /></td>
			</tr>
			<tr>
				<td class="title">文件列表</td>
				<td colspan="3"><%@ include file="../../Common/Attachment/editrequired.jsp"%></td>
			</tr>
			<tr>
				<td class="title">秘密等级</td>
				<td><select name="t_Inner_Send.security" data-style="selectrequired" data-toggle="selectpicker" data-width="100%">
						<option value="无" <c:if test="${dc.security=='无' }">selected</c:if>>无</option>
						<option value="秘密" <c:if test="${dc.security=='秘密' }">selected</c:if>>秘密</option>
				</select></td>
				<td class="title">紧急程度</td>
				<td><select name="t_Inner_Send.security" data-toggle="selectpicker" data-width="100%">
						<option value="0" <c:if test="${dc.degree=='0' }">selected="selected"</c:if>>平件</option>
						<option value="1" <c:if test="${dc.degree=='1' }">selected="selected"</c:if>>急件</option>
						<option value="2" <c:if test="${dc.degree=='2' }">selected="selected"</c:if>>特急</option>
				</select></td>
			</tr>
			<tr class="nowrap">
				<td class="title">接 收 人</td>
				<td colspan="3"><input type="hidden" name="param.userId" value="${dc.receiver }" /> <input type="text" name="param.userName" value="${dc.unames }" readonly="readonly" data-group="param"
					data-title="选择接收人" data-toggle="lookup" data-url="Main/Mail/getUsers" data-width="600" data-height="550" size="69" /></td>
			</tr>
			<tr>
				<td class="title">备注</td>
				<td colspan="3"><input name="t_Inner_Send.memo" type="text" value="${dc.memo}" maxlength="100" style="width: 100%" /></td>
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