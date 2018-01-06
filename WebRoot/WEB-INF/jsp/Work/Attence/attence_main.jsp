<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<form id="pagerForm" action="Main/Attence/main" method="post" data-toggle="ajaxsearch">
		<input type="hidden" name="pageNum" value="${page.pageNumber}" /> <input type="hidden" name="numPerPage" value="${page.pageSize}" />
		<div class="bjui-searchBar">
			<%--部长、副部长、科长有"姓名"查询条件--%>
			<label>姓名：</label><input type="text" placeholder="请输入关键字" name="sname" value="${sname}" size="12">
			<%--部长、副部长有"科室"查询条件--%>
			<label>科室：</label> <select name="departmentId" data-toggle="selectpicker">
				<option value="">---全部---</option>
				<c:forEach items="${deptstrs}" var="dept">
					<option value="${dept[0]}" <c:if test="${!empty departmentId && dept[0]==departmentId}">selected="selected"</c:if>>${dept[1]}</option>
				</c:forEach>
			</select> <label>查询日期：</label> <input type="text" name="sdate" style="width:120px" data-toggle="datepicker" readonly value="${sdate}"> - 
			<input type="text" name="edate" style="width:120px" data-toggle="datepicker" readonly value="${edate}">
			<button type="submit" class="btn-default" data-icon="search" >查询</button>
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch"   data-clear-query="true" data-icon="undo">清空查询</a>
			<div class="pull-right" >    
			<c:if test="${pert:hasperti(applicationScope.attenceUupload, loginModel.limit)}">
				<a class="btn btn-blue" data-icon="download" href="Main/AttenceUpload/uploadip" data-toggle="dialog" data-id="upload" data-title="考勤记录导入" data-mask="true"
					data-width="560" data-height="300"><span>考勤数据导入</span></a>
			</c:if>
			<c:if test="${pert:hasperti(applicationScope.updateAttendance, loginModel.limit)}">
				<a class="btn btn-blue" data-icon="edit" href="Main/Attence/updateip/{#bjui-selected}" data-toggle="dialog" data-id="updateAttendance" data-width="900" data-mask="true"
					data-height="450"><span>考勤申诉</span></a>
			</c:if>
			</div>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed">
	<thead>
		<tr align="center">
			<th width="3%" rowspan="2">序号</th>
			<th width="7%" rowspan="2">姓名</th>
			<th width="13%" rowspan="2">科室</th>
			<th width="8%" rowspan="2">日期</th>
			<th width="32%" colspan="4">上午</th>
			<th width="32%" colspan="4">下午</th>
			<th width="5%" rowspan="2">状态</th>
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
					<tr style="cursor: hand" align="center" target="id" data-id="${temp.id}" 
						<c:if test="${temp.status != 2 && sessionScope.loginModel.userId== temp.u_id
                    && ((temp.am_on_status > 2 && temp.am_on_status < 6) || (temp.am_off_status > 2 && temp.am_off_status < 6)
                    || (temp.pm_on_status > 2 && temp.pm_on_status < 6) || (temp.pm_off_status > 2 && temp.pm_off_status < 6))}">
                    dbDialog="true" dbTitle="情况说明" dbDialogId="updateAttendance" maxable="true"
                    data-mask="true" dbWidth="900" dbHeight="450" href="Main/Attence/updateip/${temp.id}"
                    </c:if>>
						<td>${vs.index+1}</td>
						<td>${temp.name}</td>
						<td>${temp.deptName}</td>
						<td>${temp.date}</td>
						<td>${fn:substring(temp.am_on_time, 11, 16)}</td>
						<td><c:choose>
								<c:when test="${temp.am_on_status < 3}">
									<c:choose>
										<c:when test="${temp.am_on_status == 1}">正常</c:when>
										<c:when test="${temp.am_on_status == 2}">请假</c:when>
									</c:choose>
								</c:when>
								<c:when test="${temp.am_on_status == 6}">确认正常</c:when>
								<c:otherwise>
									<c:if test="${temp.status != 2 && sessionScope.loginModel.userId== temp.u_id}">
										<a class="btn btn-blue" data-icon="edit" href="Main/Attence/updateip/${temp.id}" data-toggle="dialog" data-mask="true" data-drawable="true" data-resizable="true" data-maxable="true" data-title="情况说明" data-id="updateAttendance"
											data-width="900" data-height="360"></a>
									</c:if>
									<span class="attention"> <c:choose>
											<c:when test="${temp.am_on_status == 3}">迟到</c:when>
											<c:when test="${temp.am_on_status == 4}">早退</c:when>
											<c:when test="${temp.am_on_status == 5}">缺勤</c:when>
											<c:otherwise>
											</c:otherwise>
										</c:choose>
									</span>
									<c:if test="${temp.status != 2 && sessionScope.loginModel.userId== temp.u_id}">
									</c:if>
								</c:otherwise>
							</c:choose></td>
						<td>${fn:substring(temp.am_off_time, 11, 16)}</td>
						<td><c:choose>
								<c:when test="${temp.am_off_status < 3}">
									<c:choose>
										<c:when test="${temp.am_off_status == 1}">正常</c:when>
										<c:when test="${temp.am_off_status == 2}">请假 </c:when>
									</c:choose>
								</c:when>
								<c:when test="${temp.am_off_status == 6}">确认正常</c:when>
								<c:otherwise>
									<c:if test="${temp.status != 2 && sessionScope.loginModel.userId== temp.u_id}">
										<a class="btn btn-blue" data-icon="edit" href="Main/Attence/updateip/${temp.id}" data-toggle="dialog" data-mask="true"  data-title="情况说明" data-title="updateAttendance"
											data-width="900" data-height="360"></a>
									</c:if>
									<span class="attention"> <c:choose>
											<c:when test="${temp.am_off_status == 3}">迟到</c:when>
											<c:when test="${temp.am_off_status == 4}">早退</c:when>
											<c:when test="${temp.am_off_status == 5}">缺勤</c:when>
											<c:otherwise>
											</c:otherwise>
										</c:choose>
									</span>
									<c:if test="${temp.status != 2 && sessionScope.loginModel.userId== temp.u_id}">
										
									</c:if>
								</c:otherwise>
							</c:choose></td>
						<td>${fn:substring(temp.pm_on_time, 11, 16)}</td>
						<td><c:choose>
								<c:when test="${temp.pm_on_status < 3}">
									<c:choose>
										<c:when test="${temp.pm_on_status == 1}">正常</c:when>
										<c:when test="${temp.pm_on_status == 2}">请假</c:when>
									</c:choose>
								</c:when>
								<c:when test="${temp.pm_on_status == 6}"> 确认正常</c:when>
								<c:otherwise>
									<c:if test="${temp.status != 2 && sessionScope.loginModel.userId== temp.u_id}">
										<a class="btn btn-blue" data-icon="edit" href="Main/Attence/updateip/${temp.id}" data-toggle="dialog" data-mask="true"  data-title="情况说明" data-id="updateAttendance"
											data-width="900" data-height="360"></a>
									</c:if>
									<span class="attention"> <c:choose>
											<c:when test="${temp.pm_on_status == 3}">迟到</c:when>
											<c:when test="${temp.pm_on_status == 4}">早退</c:when>
											<c:when test="${temp.pm_on_status == 5}">缺勤</c:when>
											<c:otherwise>
											</c:otherwise>
										</c:choose>
									</span>
									<c:if test="${temp.status != 2 && sessionScope.loginModel.userId== temp.u_id}">
										
									</c:if>
								</c:otherwise>
							</c:choose></td>
						<td>${fn:substring(temp.pm_off_time, 11, 16)}</td>
						<td><c:choose>
								<c:when test="${temp.pm_off_status < 3}">
									<c:choose>
										<c:when test="${temp.pm_off_status == 1}">正常</c:when>
										<c:when test="${temp.pm_off_status == 2}">请假</c:when>
									</c:choose>
								</c:when>
								<c:when test="${temp.pm_off_status == 6}">确认正常</c:when>
								<c:otherwise>
									<c:if test="${temp.status != 2 && sessionScope.loginModel.userId== temp.u_id}">
										<a class="btn btn-blue" data-icon="edit" href="Main/Attence/updateip/${temp.id}" data-toggle="dialog" data-mask="true"  data-title="情况说明" data-id="updateAttendance"
											data-width="900" data-height="360"></a>
									</c:if>
									<span class="attention"> <c:choose>
											<c:when test="${temp.pm_off_status == 3}">迟到</c:when>
											<c:when test="${temp.pm_off_status == 4}">早退</c:when>
											<c:when test="${temp.pm_off_status == 5}">缺勤</c:when>
											<c:otherwise>
											</c:otherwise>
										</c:choose>
									</span>
									<c:if test="${temp.status != 2 && sessionScope.loginModel.userId== temp.u_id}">
										
									</c:if>
								</c:otherwise>
							</c:choose></td>
						<td><c:choose>
								<c:when test="${temp.status == 1}">已说明</c:when>
								<c:when test="${temp.status == 2}">已确认</c:when>
								<c:when test="${temp.status == 3}"><span class="attention">已回退</span></c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr align="center">
					<td colspan="13" style="color: red;">无任何记录！</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>
</div>
<%@ include file="/include/pagination.jsp" %>