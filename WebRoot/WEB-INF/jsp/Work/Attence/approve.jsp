<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent tableContent">
	<form id="attendanceForm" method="post" action="Main/Attence/approve" class="pageForm" data-toggle="validate">
		<input type="hidden" name="attendance_Status.id" value="${attendance_Status.id}" />
		<input type="hidden" id="attendanceStatus" name="attendance_Status.status" value="${attendance_Status.status}" />
        <div class="pageFormContent" layoutH="56">
            <table class="wordInfo" align="center" style="width: 95%;margin-bottom:23px">
                <tbody>
                <tr align="center">
                    <th width="10%" rowspan="2" align="center">姓名</th>
                    <th width="13%" rowspan="2" align="center">科室</th>
                    <th width="8%" rowspan="2" align="center">日期</th>
                    <th width="32%" colspan="4" align="center">上午</th>
                    <th width="32%" colspan="4" align="center">下午</th>
                    <th width="5%" rowspan="2" align="center">状态</th>
                </tr>
                <tr>
                    <th width="8%" style="text-align: center">上班时间</th>
                    <th width="8%" style="text-align: center">考勤结果</th>
                    <th width="8%" style="text-align: center">下班时间</th>
                    <th width="8%" style="text-align: center">考勤结果</th>
                    <th width="8%" style="text-align: center">上班时间</th>
                    <th width="8%" style="text-align: center">考勤结果</th>
                    <th width="8%" style="text-align: center">下班时间</th>
                    <th width="8%" style="text-align: center">考勤结果</th>
                </tr>
               
                
                <tr align="center">
                    <td>${attendance_Status.name}</td>
                    <td>${attendance_Status.deptName}</td>
                    <td>${attendance_Status.date}</td>
                    <td>${fn:substring(attendance_Status.am_on_time, 11, 16)}</td>
                    <td>
                        <c:choose>
                            <c:when test="${attendance_Status.am_on_status == 1}">正常
                            </c:when>
                            <c:when test="${attendance_Status.am_on_status == 2}">请假
                            </c:when>
                            <c:when test="${attendance_Status.am_on_status == 3}">
                                <span class="attention">迟到</span>
                            </c:when>
                            <c:when test="${attendance_Status.am_on_status == 4}">
                                <span class="attention">早退</span>
                            </c:when>
                            <c:when test="${attendance_Status.am_on_status == 5}">
                                <span class="attention">缺勤</span>
                            </c:when>
                            <c:when test="${attendance_Status.am_on_status == 6}">确认正常
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${fn:substring(attendance_Status.am_off_time, 11, 16)}</td>
                    <td>
                        <c:choose>
                            <c:when test="${attendance_Status.am_off_status == 1}">正常
                            </c:when>
                            <c:when test="${attendance_Status.am_off_status == 2}">请假
                            </c:when>
                            <c:when test="${attendance_Status.am_off_status == 3}">
                                <span class="attention">迟到</span>
                            </c:when>
                            <c:when test="${attendance_Status.am_off_status == 4}">
                                <span class="attention">早退</span>
                            </c:when>
                            <c:when test="${attendance_Status.am_off_status == 5}">
                                <span class="attention">缺勤</span>
                            </c:when>
                            <c:when test="${attendance_Status.am_off_status == 6}">确认正常
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${fn:substring(attendance_Status.pm_on_time, 11, 16)}</td>
                    <td>
                        <c:choose>
                            <c:when test="${attendance_Status.pm_on_status == 1}">正常
                            </c:when>
                            <c:when test="${attendance_Status.pm_on_status == 2}">请假
                            </c:when>
                            <c:when test="${attendance_Status.pm_on_status == 3}">
                                <span class="attention">迟到</span>
                            </c:when>
                            <c:when test="${attendance_Status.pm_on_status == 4}">
                                <span class="attention">早退</span>
                            </c:when>
                            <c:when test="${attendance_Status.pm_on_status == 5}">
                                <span class="attention">缺勤</span>
                            </c:when>
                            <c:when test="${attendance_Status.pm_on_status == 6}">确认正常
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${fn:substring(attendance_Status.pm_off_time, 11, 16)}</td>
                    <td>
                        <c:choose>
                            <c:when test="${attendance_Status.pm_off_status == 1}">正常
                            </c:when>
                            <c:when test="${attendance_Status.pm_off_status == 2}">请假
                            </c:when>
                            <c:when test="${attendance_Status.pm_off_status == 3}">
                                <span class="attention">迟到</span>
                            </c:when>
                            <c:when test="${attendance_Status.pm_off_status == 4}">
                                <span class="attention">早退</span>
                            </c:when>
                            <c:when test="${attendance_Status.pm_off_status == 5}">
                                <span class="attention">缺勤</span>
                            </c:when>
                            <c:when test="${attendance_Status.pm_off_status == 6}">确认正常
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${temp.status == 1}">已说明
                            </c:when>
                            <c:when test="${temp.status == 2}">已确认
                            </c:when>
                            <c:when test="${temp.status == 3}">
                                <span class="attention">已回退</span>
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tbody>
            </table>
            <hr>
            <table class="wordInfo" align="center" style="width: 95%;">
                <tr>
                    <td style="width: 10%;height:60px;" class="title">说 明 项</td>
                    <td style="width: 90%;">
                        <label><input type="checkbox" data-toggle="icheck" name="attendance_Status.am_on_need_explain" value="1"
                            <c:if test="${attendance_Status.am_on_need_explain == 1}">checked="checked"</c:if> disabled="disabled" />上午上班</label>
                        <label><input type="checkbox" data-toggle="icheck" name="attendance_Status.am_off_need_explain" value="1"
                            <c:if test="${attendance_Status.am_off_need_explain == 1}">checked="checked"</c:if> disabled="disabled" />上午下班</label>
                        <label><input type="checkbox" data-toggle="icheck" name="attendance_Status.pm_on_need_explain" value="1"
                            <c:if test="${attendance_Status.pm_on_need_explain == 1}">checked="checked"</c:if> disabled="disabled" />下午上班</label>
                        <label><input type="checkbox" data-toggle="icheck" name="attendance_Status.pm_off_need_explain" value="1"
                            <c:if test="${attendance_Status.pm_off_need_explain == 1}">checked="checked"</c:if> disabled="disabled" />下午下班</label>
                    </td>
                </tr>
                <tr>
                    <td class="title">说　　明</td>
                    <td colspan="3"><textarea rows="6" readonly="readonly" style="width: 99%" maxlength="500" >${attendance_Status.explain}</textarea></td>
                </tr>
                <tr>
                    <td class="title">领导意见</td>
                    <td colspan="3"><textarea rows="6" name="attendance_Status.opinion" style="width: 99%" maxlength="500" >${attendance_Status.opinion}</textarea></td>
                </tr>
            </table>
        </div>
    </form>
</div>
		<div class="bjui-pageFooter">
			<ul>
                <li><button type="button" class="btn btn-red" data-icon="fa-mail-reply" onclick="javascript:onSubmit(3);">回退</button></li>
				<li><button type="button" class="btn btn-green" data-icon="fa-check" onclick="javascript:onSubmit(2);">确认</button></li>
				<li><button type="button" class="btn btn-close" data-icon="close">关闭</button></li>
			</ul>
		</div>

<script type="text/javascript">
    function onSubmit(value) {
        $("#attendanceStatus").val(value);
        $("#attendanceForm").submit();
    }
</script>