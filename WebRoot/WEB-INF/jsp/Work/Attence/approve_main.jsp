<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<form id="pagerForm" action="Main/Attence/approveMain" method="post" data-toggle="ajaxsearch">
		<input type="hidden" name="pageNum" value="${page.pageNumber}" /> <input type="hidden" name="numPerPage" value="${page.pageSize}" />
		<div class="bjui-searchBar">
			<label>姓名：</label> <input type="text" placeholder="请输入关键字" name="sname" value="${sname}" size="12"> 
			<label>查询日期：</label> <input type="text" name="sdate" data-toggle="datepicker" style="width:120px" readonly value="${sdate}"> - 
			<input type="text" name="edate" data-toggle="datepicker" style="width:120px" readonly value="${edate}">
			<button type="submit" class="btn-default" data-icon="search" >查询</button>
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch"  data-clear-query="true" data-icon="undo">清空查询</a>
			<div class="pull-right">
				<c:if test="${pert:hasperti(applicationScope.approveAttendance, loginModel.limit)}">
				<a class="btn btn-blue" data-icon="edit" href="Main/Attence/approveip/{#bjui-selected}" data-toggle="dialog" data-mask="true" data-id="approveAttendance" data-title="申诉审批"
					data-width="900" data-height="600"><span>申诉审批</span></a>
			</c:if>
			</div>  
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed">
	<thead>
		<tr align="center">
				<th width="5%" rowspan="2">序号</th>
				<th width="10%" rowspan="2">姓名</th>
				<th width="13%" rowspan="2">科室</th>
				<th width="8%" rowspan="2">日期</th>
				<th width="32%" colspan="4">上午</th>
				<th width="32%" colspan="4">下午</th>
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
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty attencePage.list}">
					<c:forEach items="${attencePage.list}" var="temp" varStatus="vs">
						<tr style="cursor:pointer" title="双击打开" align="center" target="id" data-id="${temp.id}" dbDialog="true" dbTitle="审批" dbDialogId="approveAttendance" data-mask="true" dbWidth="900" dbHeight="600"
							href="Main/Attence/approveip/${temp.id}">
							<td>${vs.index+1}</td>
							<td>${temp.name}</td>
							<td>${temp.deptName}</td>
							<td>${temp.date}</td>
							<td>${fn:substring(temp.am_on_time, 11, 16)}</td>
							<td><c:choose>
									<c:when test="${temp.am_on_status == 1}">正常
                                </c:when>
									<c:when test="${temp.am_on_status == 2}"> 请假
                                </c:when>
									<c:when test="${temp.am_on_status == 3}">
										<span class="attention">迟到</span>
									</c:when>
									<c:when test="${temp.am_on_status == 4}">
										<span class="attention">早退</span>
									</c:when>
									<c:when test="${temp.am_on_status == 5}">
										<span class="attention">缺勤</span>
									</c:when>
									<c:when test="${temp.am_on_status == 6}"> 确认正常
                                </c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose></td>
							<td>${fn:substring(temp.am_off_time, 11, 16)}</td>
							<td><c:choose>
									<c:when test="${temp.am_off_status == 1}">正常
                                </c:when>
									<c:when test="${temp.am_off_status == 2}"> 请假
                                </c:when>
									<c:when test="${temp.am_off_status == 3}">
										<span class="attention">迟到</span>
									</c:when>
									<c:when test="${temp.am_off_status == 4}">
										<span class="attention">早退</span>
									</c:when>
									<c:when test="${temp.am_off_status == 5}">
										<span class="attention">缺勤</span>
									</c:when>
									<c:when test="${temp.am_on_status == 6}">  确认正常
                                </c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose></td>
							<td>${fn:substring(temp.pm_on_time, 11, 16)}</td>
							<td><c:choose>
									<c:when test="${temp.pm_on_status == 1}">正常
                                </c:when>
									<c:when test="${temp.pm_on_status == 2}">请假
                                </c:when>
									<c:when test="${temp.pm_on_status == 3}">
										<span class="attention">迟到</span>
									</c:when>
									<c:when test="${temp.pm_on_status == 4}">
										<span class="attention">早退</span>
									</c:when>
									<c:when test="${temp.pm_on_status == 5}">
										<span class="attention">缺勤</span>
									</c:when>
									<c:when test="${temp.am_on_status == 6}">确认正常
                                </c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose></td>
							<td>${fn:substring(temp.pm_off_time, 11, 16)}</td>
							<td><c:choose>
									<c:when test="${temp.pm_off_status == 1}">正常
                                </c:when>
									<c:when test="${temp.pm_off_status == 2}">请假
                                </c:when>
									<c:when test="${temp.pm_off_status == 3}">
										<span class="attention">迟到</span>
									</c:when>
									<c:when test="${temp.pm_off_status == 4}">
										<span class="attention">早退</span>
									</c:when>
									<c:when test="${temp.pm_off_status == 5}">
										<span class="attention">缺勤</span>
									</c:when>
									<c:when test="${temp.am_on_status == 6}"> 确认正常
                                </c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose></td>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr align="center">
						<td colspan="12" style="color: red;">无任何记录！</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>
<%@ include file="/include/pagination.jsp" %>