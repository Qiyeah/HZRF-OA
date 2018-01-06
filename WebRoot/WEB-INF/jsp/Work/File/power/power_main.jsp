<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="pageHeader">
	<form id="pagerForm" action="Main/power/main" method="post" onsubmit="return navTabSearch(this);">
	<input type="hidden" name="pageNum" value="${page.pageNumber}" /> 
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>角色名称：<input type="text" name="rolename" value="${rolename }" /></td>
				<td><div class="subBar"><ul><li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li></ul></div></td>
			</tr>
		</table>
	</div>
  </form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="Main/power/addip" target="dialog" mask="true" rel="wdglqx_main" title="目录角色添加" width="520"  height="320"><span style="text-align: center;">添加</span></a></li>	
			<li><a class="delete" href="Main/power/delete/{id}" target="ajaxTodo" title="确定要删除吗?" rel="wdglqx_main"><span>删除</span></a></li>  
			<li><a class="edit" href="Main/power/updateip/{id}" target="dialog" mask="true" rel="update_main" title="目录角色修改" width="520"  height="320"><span style="text-align: center;">修改</span></a></li>
			<li class="line">line</li>
			<li><a class="view" href="Main/power/view/{id}" target="dialog" mask="true" drawable="true" resizable="true" maxable="true" rel="view_man" title="查看人员" width="600" height="460"><span style="text-align: center;">查看人员</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr align="center">
				<th width="35">序号</th>
				<th width="130px">角色名称</th>
				<th>角色描述</th>
				<th width="5%">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="list" varStatus="vs">
				<tr align="center" target="id" rel="${list.id}">
					<td>${vs.count }</td>
					<td>${list.rolename}</td>
					<td>${list.desrole}</td>
					<td>
						<a title="权限配置" class="btnRight" href="Main/power/persetip/${list.id}" target="dialog" mask="true" rel="wdglqx_main" title="角色权限分配" width="600" height="460">配置权限</a>
						<a title="人员配置" class="btnAssign" href="Main/power/usersetip/${list.id }" mask="true" width="600" height="460" rel="user_main" target="dialog" title="人员配置">人员配置</a>
					</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option <c:if test="${page.pageSize==20}">selected="selected"</c:if> value="20" >20</option>
				<option <c:if test="${page.pageSize==50}">selected="selected"</c:if> value="50">50</option>
				<option <c:if test="${page.pageSize==100}">selected="selected"</c:if> value="100">100</option>
				<option <c:if test="${page.pageSize==200}">selected="selected"</c:if> value="200">200</option>
			</select>
			<span>条，共${page.totalRow}条</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${page.totalRow}" numPerPage="${page.pageSize}" pageNumShown="5" currentPage="${page.pageNumber}"></div>
	</div>
</div>
