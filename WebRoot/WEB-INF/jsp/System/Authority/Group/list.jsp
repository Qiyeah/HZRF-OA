<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<form id="pagerForm" action="Main/Group/main/list/${groupid }" method="post" data-toggle="ajaxsearch">
		<div class="bjui-searchBar">
			<label>姓名：</label><input type="text" name="uname" value="${uname}" size="12" placeholder="请输入关键字"/>
			<button type="submit" class="btn-default" data-icon="search">查询</button>
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
		
	<div class="pull-right">
		<%-- <c:if test="${pert:hasperti(applicationScope.addTel, loginModel.limit)}">
            <button type="button" class="btn btn-green" data-url="Main/Personal/addip" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="scheduleid" data-title="添加内部用户档案"
				data-width="600" data-height="350">添加</button>
         </c:if> --%>
          	<a href="Main/Group/creategroupip" class="btn btn-green" data-toggle="dialog" data-mask="ture" data-id="Enterprpdate" data-icon="fa-user-plus" data-title="创建自定义群组"
					data-width="800" data-height="550">创建群组</a> 
		<c:if test="${groupid!=0 }">
       		<a href="Main/Group/editgroupip/${groupid }" class="btn btn-blue" data-toggle="dialog" data-mask="ture" data-id="Enterprpdate" data-icon="fa-user-plus" data-title="编辑自定义群组"
					data-width="800" data-height="550">编辑群组</a> 
            <a href="Main/Group/deletegroup/${groupid }" class="btn btn-red" data-toggle="doajax" data-icon="fa-trash-o"data-confirm-msg="您确定要删除该群组吗?">删除群组</a> 
		</c:if>
     </div>	 
     </div>
	</form>
	</div>




<div class="tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed" data-selected="true">
		<thead>
			<tr align="center">
				<th width="5%">序号</th>
				<th width="10%">姓名</th>
				<th width="15%">内部电话</th>
				<th width="15%">手机</th>
				<th width="15%">所属科室</th>
				<th width="15%">职务名称</th>
				<th width="15%">职务级别</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="temp" varStatus="vs">
						<tr  style="cursor:pointer" title="双击打开" align="center" target="id" data-id="${temp.id}" dbDialog="true" dbTitle="查看" dbDialogId="viewTel" data-maxable="true" data-mask="true" data-drawable="true"
							data-resizable="true" dbWidth="600" dbHeight="265" href="Main/Personal/viewTel/${temp.id}">
							<td>${vs.index+1}</td>
							<td>${temp.uname}</td>
							<td>${temp.phone}</td>
							<td>${temp.mbphone}</td>
							<td>${deptHM.get(temp.d_id)}</td>
							<td>${temp.gradename}</td>
							<td>${temp.gradelevel}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr align="center">
						<td colspan="7" style="color: red;">无任何记录！</td>
					</tr>
				</c:otherwise>
			</c:choose>

		</tbody>
	</table>
</div>
	<%@ include file="/include/pagination.jsp" %>
