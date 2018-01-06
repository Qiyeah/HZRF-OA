<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<form id="pagerForm" action="Main/LeaderSchedule/main" method="post" data-toggle="ajaxsearch">
		<input type="hidden" name="pageCurrent" value="${page.pageNumber}" />
		 <input type="hidden" name="pageSize" value="${page.pageSize}" />
		<div class="bjui-searchBar">
			<label>日程标题：</label> <input type="text" name="stitle" value="${stitle}" size="15" placeholder="请输入关键字"/>
			<label>日期：</label> <input type="text" name="sdate" data-toggle="datepicker" readonly value="${sdate}" size="12"> - <input
				type="text" name="edate" data-toggle="datepicker" readonly value="${edate}" size="12"/>
			<button type="submit" class="btn-default" data-icon="search">查询</button>
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
			<div class="pull-right">
				<c:if test="${pert:hasperti(applicationScope.addLeaderSchedule, loginModel.limit)}">
					<button type="button" class="btn btn-green" data-url="Main/LeaderSchedule/addip" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="scheduleid"
					data-title="日程添加" data-width="900" data-height="450">添加</button>
				</c:if>
				<c:if test="${pert:hasperti(applicationScope.updateLeaderSchedule, loginModel.limit)}">
					<a href="Main/LeaderSchedule/updateip/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-mask="ture" data-id="leaderschedule_update" data-icon="fa-edit" data-title="日程修改"
					data-width="900" data-height="450">编辑</a>
				</c:if>
				
				<c:if test="${pert:hasperti(applicationScope.deleteLeaderSchedule, loginModel.limit)}">
					 <a href="Main/LeaderSchedule/delete/{#bjui-selected}" class="btn btn-red" data-toggle="doajax" data-icon="fa-trash-o" data-confirm-msg="您确定要删除该记录吗?">删除</a>
				</c:if>
				<c:if test="${pert:hasperti(applicationScope.viewLeaderSchedule, loginModel.limit)}">
					 <a href="Main/LeaderSchedule/view/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-icon="fa-eye" data-mask="ture" data-id="Enterprpdate" data-title="查看日程"
					data-width="900" data-height="400">查看</a>
				</c:if>
			</div>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-nowrap="true" data-toggle="tablefixed" data-selected="true">
		<thead>
			<tr align="center">
				<th width="5%">序号</th>
				<th width="8%">领导</th>
				<th width="15%">开始时间</th>
				<th width="15%">结束时间</th>
				<th width="20%">日程标题</th>
				<th width="37%">日程内容</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="temp" varStatus="vs">
						<tr style="cursor:pointer" title="双击打开" align="center" data-id="${temp.id}" dbDialog="true" dbTitle="查看日程" dbDialogId="viewLeaderSchedule" maxable="true" mask="true" dbWidth="700"
							dbHeight="400" href="Main/LeaderSchedule/view/${temp.id}">
							<td>${vs.index+1}</td>
							<td>${userHM.get(temp.leader_id)}</td>
							<td>${temp.date} ${temp.hour}时${temp.minute}分</td>
							<td>${temp.edate} ${temp.ehour}时${temp.eminute}分</td>
							<td class="custom">${temp.title}</td>
							<td class="custom">${temp.content}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr align="center">
						<td colspan="6" style="color: red;">无任何记录！</td>
					</tr>
				</c:otherwise>
			</c:choose>

		</tbody>
	</table>
</div>
	<%@ include file="/include/pagination.jsp" %>