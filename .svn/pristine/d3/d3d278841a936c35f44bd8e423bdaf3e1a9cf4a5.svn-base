<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<form id="pagerForm" action="Main/Personal/main" method="post" data-toggle="ajaxsearch">
		<input type="hidden" name="pageCurrent" value="${page.pageNumber}" />
		<input type="hidden" name="pageSize" value="${page.pageSize}" /> 
        <input type="hidden" name="orderField" value="${orderField}" />  
   	    <input type="hidden" name="orderDirection" value="${orderDirection}" />
		<div class="bjui-searchBar">
		<label>姓名：</label><input type="text" name="uname" value="${uname}" size="12" maxlength="30" placeholder="请输入关键字"/>
		<label>所属科室：</label><select name="d_id" data-toggle="selectpicker">
							<option value="">全部科室</option>
							<c:forEach items="${deptstrs}" var="dept">
								<option <c:if test="${!empty d_id && dept[0]==d_id}">selected="selected"</c:if> value="${dept[0]}">${dept[1]}</option>
							</c:forEach>
					</select>
			<button type="submit" class="btn-default" data-icon="search">查询</button>
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
			 <div class="pull-right">
				  <c:if test="${pert:hasperti(applicationScope.personaladd, loginModel.limit)}">
            	<button type="button" class="btn btn-green" data-url="Main/Personal/addip" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="scheduleid" data-title="添加内部用户档案"
					data-width="600" data-height="350">添加</button>
            </c:if>
            
           	<c:if test="${pert:hasperti(applicationScope.personalupdate, loginModel.limit)}">
            <a href="Main/Personal/updateip/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-mask="ture" data-id="Enterprpdate" data-icon="fa-edit" data-title="修改内部用户档案"
					data-width="600" data-height="350">编辑</a> 
					</c:if>
            
           	<c:if test="${pert:hasperti(applicationScope.personaldelete, loginModel.limit)}">
                <a href="Main/Personal/delete/{#bjui-selected}" class="btn btn-red" data-toggle="doajax" data-icon="fa-trash-o"
					data-confirm-msg="您确定要删除该记录吗?">删除</a></c:if>
			</div>	 
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed" data-selected="true">
		<thead>
			<tr align="center">
				<th width="10%">姓名</th>
				<th width="10%">内部电话</th>
				<th width="10%">手机</th>
				<th width="10%">所属科室</th>
				<th width="10%">职务名称</th>
				<th width="15%">职务级别</th>
				<th width="15%">开始工作日期</th>
				<th width="5%">婚姻状态</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="temp">
						<tr  style="cursor:pointer" title="双击打开" align="center" target="id" data-id="${temp.id}" dbDialog="true" dbTitle="修改内部用户档案" dbDialogId="updatePersonal"
                            maxable="true" mask="true" drawable="true" resizable="true" dbWidth="600" dbHeight="315" href="Main/Personal/updateip/${temp.id}">
                            <td>${temp.uname}</td>
							<td>${temp.phone}</td>
							<td>${temp.mbphone}</td>
							<td>${deptHM.get(temp.d_id)}</td>
							<td>${temp.gradename}</td>
							<td>${temp.gradelevel}</td>
							<td>${temp.begindate}</td>
							<td>${temp.married}</td>
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