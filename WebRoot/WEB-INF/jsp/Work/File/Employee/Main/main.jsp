<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="pageHeader">
	<form id="pagerForm" method="post" action="Main/employee/main">
		<input type="hidden" name="pageNum" value="${page.pageNumber}" /> 
		<input type="hidden" name="numPerPage" value="${page.pageSize}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>姓名：<input type="text" name="name" value="${name }"/></td>
					<td><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="Main/employee/addip" target="dialog" mask="true" drawable="true" rel="employ_add" title="人员添加" width="650" height="450"><span>添加</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="95">
		<thead>
			<tr align="center">
				<th width="44">序号</th>
				<th width="10%">姓名</th>
				<th width="50">姓别</th>
				<th width="150">技术档案</th>
				<th width="150">培训记录</th>
				<th>个人业绩档案</th>
				<th width="75">查看</th>
				<th width="55">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="list" varStatus="vs">
				<tr align="center" target="id" rel="${list.id}">
					<td>${vs.count }</td>
					<td>${list.name}</td>
					<td>
						<c:if test="${list.sex ==1 }">男</c:if> 
						<c:if test="${list.sex ==0 }">女</c:if>
					</td>
					<td>
						<a href="Main/employee_duty/main/${list.id}" rel="employee_duty" target="dialog" width="700" height="500" mask="true"><span style="color: purple;">技术档案卡</span></a>
					</td>
					<td>
						<a href="Main/employee_train/main/${list.id}" rel="employee_train" target="dialog" width="700" height="500" mask="true"><span style="color: purple;">记录表</span></a>
					</td>
					<td>
						<a href="Main/employee_education/main/${list.id}" rel="employee_education" target="dialog" width="700" height="500" mask="true"><span style="color: purple;">教育培训</span></a>
						|
						<a href="Main/employee_work/main/${list.id}" rel="employee_work" target="dialog" width="700" height="500" mask="true"><span style="color: purple;">工作业绩</span></a>
						|
						<a href="Main/employee_rewards/main/${list.id}" rel="employee_rewards" target="dialog" width="700" height="500" mask="true"><span style="color: purple;">奖惩项目</span></a>
					</td>
					<td>
						<a title="查看下载" rel="employee_view" target="dialog" href="Main/employee/view/${list.id}" width="700" height="500" mask="true" resizable="true"><span style="color: green;">查看</span></a>
					</td>
					<td>
						<a title="确定删除该选项" target="ajaxTodo" href="Main/employee/delete/${list.id}" class="btnDel">删除</a>
						<a title="编辑" target="dialog" href="Main/employee/updateip/${list.id}" width="650" height="450" class="btnEdit" mask="true" resizable="true">修改</a>
					</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar" style="border-bottom:0px;">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option <c:if test="${page.pageSize==20}">selected="selected"</c:if>value="20">20</option>
				<option <c:if test="${page.pageSize==50}">selected="selected"</c:if>value="50">50</option>
				<option <c:if test="${page.pageSize==100}">selected="selected"</c:if>value="100">100</option>
				<option <c:if test="${page.pageSize==200}">selected="selected"</c:if>value="200">200</option>
			</select> <span>种，共${page.totalRow}种</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${page.totalRow}" numPerPage="${page.pageSize}" pageNumShown="9" currentPage="${page.pageNumber}"></div>
	</div>
</div>
