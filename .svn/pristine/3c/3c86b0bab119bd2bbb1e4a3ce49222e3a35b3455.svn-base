<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<form id="pagerForm" action="Main/Note/main" method="post" data-toggle="ajaxsearch">
	<div class="bjui-searchBar">
		<input type="hidden" name="pageCurrent" value="${page.pageNumber}" />
		<input type="hidden" name="pageSize" value="${page.pageSize}" /> 
		<label>标题：</label> <input type="text" name="stitle" value="${stitle}" size="15" placeholder="请输入关键字"/> 
		<label>类型：</label> <input type="text" name="stype" value="${stype}" size="12" placeholder="请输入关键字"/> 
		<label>日期：</label> <input type="text" name="sdate" data-toggle="datepicker"
			readonly value="${sdate}" size="12"> - <input type="text" name="edate" data-toggle="datepicker" readonly value="${edate}" size="12">
		<button type="submit" class="btn-default" data-icon="search">查询</button>
		<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
		<div class="pull-right">
			<c:if test="${pert:hasperti(applicationScope.note_add, loginModel.limit)}">
				<button type="button" class="btn btn-green" data-url="Main/Note/addip" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="scheduleid"
					data-title="添加记事本" data-width="900" data-height="450">添加</button>
			</c:if>

			<c:if test="${pert:hasperti(applicationScope.note_update, loginModel.limit)}">
				<a href="Main/Note/updateip/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-mask="ture" data-id="Enterprpdate" data-icon="fa-edit" data-title="修改记事本"
					data-width="900" data-height="450">编辑</a>
			</c:if>

			<c:if test="${pert:hasperti(applicationScope.note_delete, loginModel.limit)}">
				<a href="Main/Note/delete/{#bjui-selected}" class="btn btn-red" data-toggle="doajax" data-icon="fa-trash-o" data-confirm-msg="您确定要删除该记录吗?">删除</a>
			</c:if>
		</div>
		 </div>
</div>
</form>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-nowrap="true" data-toggle="tablefixed" data-selected="true">
		<thead>
			<tr align="center">
				<th width="5%">序号</th>
				<th width="15%">日期</th>
				<th width="15%">类型</th>
				<th width="25%">标题</th>
				<th width="40%">内容</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="temp" varStatus="vs">
						<tr style="cursor:pointer" title="双击打开" align="center" data-id="${temp.id}" dbDialog="true" dbTitle="修改记事本" dbDialogId="noteupdate" maxable="true" mask="true" dbWidth="900"
							dbHeight="450" href="Main/Note/updateip/${temp.id}">
							<td>${vs.index+1}</td>
							<td>${temp.wdate}</td>
							<td>${temp.type}</td>
							<td class="custom">${temp.title}</td>
							<td class="custom">${temp.content}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr align="center">
						<td colspan="5" style="color: red;">无任何记录！</td>
					</tr>
				</c:otherwise>
			</c:choose>

		</tbody>
	</table>
</div>
	<%@ include file="/include/pagination.jsp" %>