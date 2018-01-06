<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<style>
<!--
form#approveForm span.wrap_bjui_btn_box{
	 width:100%;  
}
-->
</style>
<div class="bjui-pageContent tableContent">
	<form id="approveForm" action="Main/Docachieve/update" class="pageForm" data-toggle="validate">
		<div class="pageFormContent">
			<input type="hidden" name="t_Doc_Achieve.id" value="${dc.id}" /> <input type="hidden" name="t_Doc_Achieve.u_id" value="${dc.u_id}" /> <input type="hidden"
				name="t_Doc_Achieve.d_id" value="${dc.d_id}" /> <input type="hidden" name="t_Doc_Achieve.status" value="${dc.status}" />
			<table class="wordInfo" align="center" style="width: 95%;margin-top:23px">
				<tr>
					<td class="title" colspan="4">收文登记</td>
				</tr>
				<tr>
					<td width="15%" class="title">登记人</td>
					<td width="35%" style="text-align: center;">${uname}</td>
					<td width="15%" class="title">登记日期</td>
					<td width="35%" align="left"><input data-toggle="datepicker" readonly name="t_Doc_Achieve.receivedate" type="text" value="${dc.receivedate}" data-rule="required date" data-msg-required="必填" class="required" style="width: 100%" /></td>
				</tr>
				<tr>
					<td class="title">来文单位</td>
					<td><input name="achieveDepart.unit" type="text" value="${dc.unit}" suggestFields="unit" suggestUrl="Main/search/achieveDepart" lookupGroup="achieveDepart"
						data-rule="required" class="required" data-msg-required="必填" maxlength="50" style="width: 100%" /></td>
					<td class="title">来文文号</td>
					<td><input name="t_Doc_Achieve.docno" type="text" value="${dc.docno}" maxlength="50" style="width: 100%" /></td>
				</tr>
				<tr>
					<td class="title">单位分类</td>
					<td><select name="t_Doc_Achieve.type" data-toggle="selectpicker" data-width="100%">
							<option value="">---请选择---</option>
							<c:forEach items="${details}" var="temp">
								<option value="${temp.name}" <c:if test="${dc.type==temp.name}">selected</c:if>>${temp.name}</option>
							</c:forEach>
					</select></td>
					<td class="title">来文份数</td>
					<td><input name="t_Doc_Achieve.count" type="text" data-rule="number" style="width: 100%" value="${dc.count}" /></td>
				</tr>
				<tr>
					<td class="title">文件标题</td>
					<td colspan="3" class="normal"  align="center"><input name="t_Doc_Achieve.title" type="text" value="${dc.title}" class="required" data-msg-required="必填" data-rule="required" maxlength="250" style="width: 100%" /></td>
				</tr>
				<tr>
					<td class="title">文件列表</td>
					<td colspan="3">
						<%@ include file="../../Common/Attachment/editrequired.jsp"%>
						<!-- <button type="button" class="btn-green" data-url="Main/Docachieve/photograph" data-toggle="dialog" data-icon="plus" data-options="{beforeClose:beforeHighShotMeterPageClose}"
								data-mask="true" data-id="photograph" data-title="拍照" data-width="1050" data-height="680">拍照</button> -->
					</td>
				</tr>
				<tr>
					<td class="title">秘密等级</td>
					<td><select name="t_Doc_Achieve.security" data-style="selectrequired" data-toggle="selectpicker" data-width="100%">
							<option value="无" <c:if test="${dc.security=='无' }">selected</c:if>>无</option>
							<option value="秘密" <c:if test="${dc.security=='秘密' }">selected</c:if>>秘密</option>
					</select> </td>
					<td class="title">紧急程度</td>
					<td><select name="t_Doc_Achieve.security" data-toggle="selectpicker" data-width="100%">
							<option value="0" <c:if test="${dc.degree=='0' }">selected="selected"</c:if>>平件</option>
							<option value="1" <c:if test="${dc.degree=='1' }">selected="selected"</c:if>>平急</option>
							<option value="2" <c:if test="${dc.degree=='2' }">selected="selected"</c:if>>特急</option>
					</select></td>
				</tr>
				<tr>
					<td class="title">备注</td>
					<td colspan="3"><textarea name="t_Doc_Achieve.memo" rows="2" maxlength="250" style="width: 100%;">${dc.memo}</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>

<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn btn-close" data-icon="close">关闭</button></li>
		<li><button type="submit" class="btn btn-default" data-icon="save">保存</button></li>
	</ul>
</div>