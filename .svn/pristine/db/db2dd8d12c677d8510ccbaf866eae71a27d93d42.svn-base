<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<form id="pagerForm" action="Main/Grant/main" method="post" data-toggle="ajaxsearch">
		<input type="hidden" name="pageCurrent" value="${page.pageNumber}" />
		<input type="hidden" name="pageSize" value="${page.pageSize}" /> 
		<div class="bjui-searchBar">
			<label>授权范围：</label>
			<select name="stype" class="combox" style="width:100px;" data-toggle="selectpicker">
			    <option value="">所有</option>
				<option value="9" <c:if test="${stype=='9'}">selected</c:if>>全部</option>
				<option value="1" <c:if test="${stype=='1'}">selected</c:if>>收文办理</option>
				<option value="2" <c:if test="${stype=='2'}">selected</c:if>>发文办理</option>
				<option value="3" <c:if test="${stype=='3'}">selected</c:if>>请休假审批</option>
				
				<option value="5" <c:if test="${stype=='5'}">selected</c:if>>会议审批</option>
			</select>
			<label>授权日期：</label>
			<input type="text" name="sdate" data-toggle="datepicker" readonly value="${sdate}" size="12"/> - <input type="text" name="edate" data-toggle="datepicker" readonly value="${edate}" size="12"/>
			<button type="submit" class="btn-default" data-icon="search">查询</button>
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
			<div class="pull-right">
			<c:if test="${pert:hasperti(applicationScope.grant_add, loginModel.limit)}">
				<button type="button" class="btn btn-green" data-url="Main/Grant/addip" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="scheduleid" data-title="添加授权"
					data-width="900" data-height="450">添加</button>
					</c:if>
				<c:if test="${pert:hasperti(applicationScope.grant_update, loginModel.limit)}">
				<a href="Main/Grant/updateip/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-mask="ture" data-id="Enterprpdate" data-icon="fa-edit" data-title="修改授权"
					data-width="900" data-height="450">编辑</a> 
					</c:if>
					<c:if test="${pert:hasperti(applicationScope.grant_delete, loginModel.limit)}">
					<a href="Main/Grant/delete/{#bjui-selected}" class="btn btn-red" data-toggle="doajax" data-icon="fa-trash-o"
					data-confirm-msg="您确定要删除该记录吗?">删除</a>
					</c:if>
			</div>
		</div>
</div>
</form>
<div class="bjui-pageContent tableContent">
		<table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed" data-selected="true">
		<thead>
			<tr align="center">
				<th width="5%">序号</th>
				<th width="15%">被授权人</th>
				<th width="15%">开始日期</th>
				<th width="15%">结束日期</th>
				<th width="15%">授权范围</th>
				<th width="15%">是否启用</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="temp" varStatus="vs">
						<tr style="cursor:pointer" title="双击打开" align="center" data-id="${temp.id}" dbDialog="true" dbTitle="修改授权" dbDialogId="grant_update" maxable="true" mask="true" dbWidth="900"
							dbHeight="450" href="Main/Grant/updateip/${temp.id}">
							<td>${vs.index+1}</td>
							<td>${temp.uname}</td>
							<td>${temp.s_date}</td>
							<td>${temp.e_date}</td>
							<td><c:choose>
									<c:when test="${temp.type=='9'}">全部</c:when>
									<c:when test="${temp.type=='1'}">收文办理</c:when>
									<c:when test="${temp.type=='2'}">发文办理</c:when>
									<c:when test="${temp.type=='3'}">请休假审批</c:when>
									<c:when test="${temp.type=='4'}">出国境审批</c:when>
									<c:when test="${temp.type=='5'}">会议审批</c:when>
									<c:otherwise></c:otherwise>
								</c:choose></td>
							<td><c:choose>
									<c:when test="${temp.usable == 0}">启用</c:when>
									<c:when test="${temp.usable == 1}">禁用</c:when>
									<c:otherwise></c:otherwise>
								</c:choose></td>
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
