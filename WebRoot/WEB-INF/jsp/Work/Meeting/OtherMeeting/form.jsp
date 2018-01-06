<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<table class="wordInfo" align="center" style="width: 98%;">
	<tr>
		<td width="20%" class="title">日期</td>
		<td width="80%"><input type="text" name="t_MinistryMeeting.date" size="59" data-toggle="datepicker" data-pattern="yyyy-MM-dd HH:mm:ss" readonly
			value="${fn:substring(model.date, 0, 19)}"></td>
	<tr>
		<td class="title">地点</td>
		<td><input type="text" name="t_MinistryMeeting.place" value="${model.place}" maxlength="100" style="width: 100%" /></td>
	</tr>
	<tr>
		<td class="title">主持人</td>
		<td><input type="hidden" name="chairman.userid" value="${model.hostid}" /> <input name="chairman.username" type="text"
			suggestFields="username" suggestUrl="Main/search/searchUser" lookupGroup="chairman" value="${model.host}" /></td>
	</tr>
	<tr>
		<td class="title">参加人员</td>
		<td><input name="attendee.userId" type="hidden" value="${model.attendeeid}" /> <input type="text" readonly="readonly" name="attendee.userName"
			size="59" value="${model.attendee }" data-group="attendee" data-title="参加人员" data-toggle="lookup" data-url="Main/Mail/getUsers" data-width="600"
			data-height="550" /></td>
	</tr>
	<tr>
		<td class="title">附件列表<br>(会议材料<br>会议记录)
		</td>
		<td>
			<div id="uploadFile" class="unitBox" style="overflow-x: hidden;">
				<c:import url="/WEB-INF/jsp/Common/Attachment/edit.jsp" />
			</div>
		</td>
	</tr>
</table>