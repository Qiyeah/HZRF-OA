<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$('#sdate').on('afterchange.bjui.datepicker', function(e, data) {
		$("#edate").val($("#sdate").val());
	});
</script>
<input type="hidden" name="t_LeaderSchedule.id" value="${leader_Schedule.id}" />
<input type="hidden" name="t_LeaderSchedule.u_id" value="${leader_Schedule.u_id}" />
<table class="wordInfo" align="center" style="width: 98%;">
	<tr>
		<td class="title">领 导</td>
		<td><select name="t_LeaderSchedule.leader_id" class="combox" data-toggle="selectpicker" data-style="selectrequired" data-width="100%">
			    <c:if test="${! empty option}"><option value="">---请选择---</option></c:if>
				<c:forEach items="${leaders}" var="o">
					<option value="${o.id}" <c:if test="${leader_Schedule.leader_id==o.id}"> selected </c:if>>${o.name}</option>
				</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td width="20%" class="title">开始时间</td>
		<td width="80%">
			<table>
				<tr>
					<td style="border: 0px;"><input type="text" id="sdate" data-toggle="datepicker" data-rule="required;date" data-msg-date="格式错误" data-msg-required="必填"
						name="t_LeaderSchedule.date"
						<c:if test="${! empty leader_Schedule.date}">
                                       value="${leader_Schedule.date}"
                                   </c:if>
						<c:if test="${empty leader_Schedule.date}">
                                        value="${nowday}"
                                    </c:if> style="width: 120px"
						class="required" maxlength="13" /></td>
					<td style="border: 0px;">日</td>
					<td style="border: 0px;"><select name="t_LeaderSchedule.hour" data-toggle="selectpicker">
							<c:forEach var="x" begin="7" end="23" step="1">
								<option value="${x}" <c:if test="${x==leader_Schedule.hour}"> selected </c:if>>${x}</option>
							</c:forEach>
					</select></td>
					<td style="border: 0px;">时</td>
					<td style="border: 0px;"><select name="t_LeaderSchedule.minute" data-toggle="selectpicker">
							<c:forEach var="x" begin="0" end="50" step="10">
								<option value="${x}" <c:if test="${x==leader_Schedule.minute}"> selected </c:if>>${x}</option>
							</c:forEach>
					</select></td>
					<td style="border: 0px;">分</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="20%" class="title">结束时间</td>
		<td width="80%">
			<table>
				<tr>
					<td style="border: 0px;"><input type="text" id="edate" name="t_LeaderSchedule.edate" data-toggle="datepicker"
						<c:if test="${! empty leader_Schedule.date}">
                                       value="${leader_Schedule.edate}"
                                   </c:if>
						<c:if test="${empty leader_Schedule.edate}">
                                        value="${nowday}"
                                    </c:if> style="width: 120px"
						class="required" data-rule="required;date" data-msg-date="格式错误" data-msg-required="必填" maxlength="13" /></td>
					<td style="border: 0px;">日</td>
					<td style="border: 0px;"><select name="t_LeaderSchedule.ehour" class="combox" data-toggle="selectpicker">
							<c:forEach var="x" begin="7" end="23" step="1">
								<option value="${x}" <c:if test="${x==leader_Schedule.ehour}"> selected </c:if>>${x}</option>
							</c:forEach>
					</select></td>
					<td style="border: 0px;">时</td>
					<td style="border: 0px;"><select name="t_LeaderSchedule.eminute" class="combox" data-toggle="selectpicker">
							<c:forEach var="x" begin="0" end="50" step="10">
								<option value="${x}" <c:if test="${x==leader_Schedule.eminute}"> selected </c:if>>${x}</option>
							</c:forEach>
					</select></td>
					<td style="border: 0px;">分</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="20%" class="title">公开范围</td>
		<td width="80%"><label><input type="radio" data-toggle="icheck" name="t_LeaderSchedule.scope" value="0"  <c:if test="${leader_Schedule.scope == 0}"> checked </c:if>>全部人员</label>
		 <label><input type="radio" data-toggle="icheck" name="t_LeaderSchedule.scope" value="1" <c:if test="${leader_Schedule.scope == 1}"> checked </c:if>>指定人员</label></td>
	</tr>
	<tr>
		<td width="20%" class="title">公开人员</td>
		<td width="80%"><input name="userId" type="hidden" value="${leader_Schedule.receiver_id}" /><input type="text" readonly="readonly" name="userName"
			style="width:100%" lookupGroup="param" data-title="选择公开人员" data-toggle="lookup" data-url="Main/Mail/getUsers" data-width="600" data-height="550" value="${leader_Schedule.receiver}" /></td>
	</tr>
	<tr>
		<td class="title">日程标题</td>
		<td><input type="text" name="t_LeaderSchedule.title" value="${leader_Schedule.title}" class="required" data-rule="required" data-msg-required="必填" style="width: 100%" /></td>
	</tr>
	<tr>
		<td class="title">日程内容</td>
		<td><textarea name="t_LeaderSchedule.content" rows="6" style="width: 100%; overflow: auto">${leader_Schedule.content}</textarea></td>
	</tr>
</table>
