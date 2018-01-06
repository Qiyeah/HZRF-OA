<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<form id="pagerForm" action="Main/Bookmark/main" method="post" onsubmit="return navTabSearch(this);">
	<div class="pageHeader">
		<input type="hidden" name="pageNum" value="${page.pageNumber}" />
		<input type="hidden" name="numPerPage" value="${page.pageSize}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>书签名称：<input type="text" name="sname" value="${sname }"/></td>
					<td><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></td>
				</tr>
			</table>
		</div>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="Main/Bookmark/addip" target="dialog" mask="true" drawable="true" resizable="true" maxable="true" rel="bookmark_add" title="添加书签" width="400" height="190"><span>添加</span></a></li>
				<li><a class="edit" href="Main/Bookmark/updateip/{id}" target="dialog" mask="true" drawable="true" resizable="true" maxable="true" rel="bookmark_update" title="修改书签" width="400" height="190"><span>修改</span></a></li>
				<li><a class="delete" href="Main/Bookmark/delete/{id}" target="ajaxTodo" title="您确定要删除该书签吗?"><span>删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="94">
			<thead>
				<tr align="center">
					<th width="5%">序号</th>
					<th width="15%">书签名称</th>
					<th width="25%">书签说明</th>
					<th width="40%">书签备注</th>
					<th width="15%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${! empty page.list}">
						<c:forEach items="${page.list}" var="temp" varStatus="vs">
							<tr align="center" target="id" rel="${temp.id}">
								<td>${vs.index+1}</td>
								<td>${temp.name}</td>
								<td>${temp.descript}</td>
								<td>${temp.value}</td>
									<td><a class="btnEdit" href="Main/Bookmark/updateip/${temp.id}" target="dialog" mask="true" drawable="true" resizable="true" rel="bookmark_update" width="400" height="190" title="编辑书签"></a>
										<a class="btnDel" href="Main/Bookmark/delete/${temp.id}" target="ajaxTodo" title="您确定要删除该书签吗?">删除</a></td>
							</tr>		
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr align="center">
							<td colspan="4" style="color: red;">无任何记录！</td>
						</tr>	
					</c:otherwise>
				</c:choose>			
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
				<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="20" <c:if test="${page.pageSize==20}">selected="selected"</c:if>>20</option>
					<option value="50" <c:if test="${page.pageSize==50}">selected="selected"</c:if>>50</option>
					<option value="100" <c:if test="${page.pageSize==100}">selected="selected"</c:if>>100</option>
					<option value="200" <c:if test="${page.pageSize==200}">selected="selected"</c:if>>200</option>
				</select>
				<span>条，共${page.totalRow}条</span>
			</div>
			<div class="pagination" targetType="navTab" totalCount="${page.totalRow}" numPerPage="${page.pageSize}" pageNumShown="5" currentPage="${page.pageNumber}"></div>
		</div>
	</div>
</form>
