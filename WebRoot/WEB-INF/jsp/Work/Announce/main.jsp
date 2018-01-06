<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<form id="pagerForm" method="post" action="Main/announce/main">
		<input type="hidden" name="pageCurrent" value="${page.pageNumber}" />
		<input type="hidden" name="pageSize" value="${page.pageSize}" /> 
</form>
<div class="bjui-pageHeader">
	<form method="post" id="pagerForm" action="Main/Announce/main" data-toggle="ajaxsearch">
	<div class="bjui-searchBar">
	<label>公告标题：</label> <input type="text" name="stitle" value="${stitle }" placeholder="请输入关键字" size="12"/>
	<label>公告类型：</label> <select data-toggle="selectpicker" name="stype" data-width="120px">
		<option value="">---全部---</option>
		<option value="0" <c:if test="${stype==0 }">selected</c:if>>普通公告</option>
		<option value="1" <c:if test="${stype==1 }">selected</c:if>>紧急公告</option>
	</select>
	<div class="pull-right">
            <button type="button" class="btn btn-green" data-url="Main/Announce/addip" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="scheduleid" data-title="添加公告信息" data-width="1100" data-height="700">添加</button>
             <a href="Main/Announce/updateip/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-mask="ture" data-id="Enterprpdate" data-icon="fa-edit" data-title="修改公告信息"
					data-width="1100" data-height="700">编辑</a>
            <a href="Main/Announce/delete/{#bjui-selected}" class="btn btn-red" data-toggle="doajax" data-icon="fa-trash-o" data-confirm-msg="您确定要删除该记录吗?">删除</a>
            <a href="Main/Announce/viewip/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-icon="fa-eye" data-mask="ture" data-id="Enterprpdate" data-title="查看内容"
					data-width="1100" data-height="600">查看内容</a>
            <a href="Main/Announce/viewer/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-icon="fa-eye" data-mask="ture" data-id="Enterprpdate" data-title="查看已阅者"
					data-width="600" data-height="600">查看已阅者</a>     
	</div><br>
	<label>起止日期：</label> <input data-toggle="datepicker" name="sdate" readonly value="${sdate}" size="12"/> 　——　　 <input data-toggle="datepicker" name="edate" readonly value="${edate}" size="12"/>
	<button type="submit" class="btn-default" data-icon="search" >查询</button>
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch"  data-clear-query="true" data-icon="undo">清空查询</a>
	</div>
	</form>
</div>
<div class="bjui-pageContent tableContent" >	
	<table class="table table-bordered table-hover table-striped table-top table-center" data-nowrap="true" data-toggle="tablefixed" data-selected="true"> 
		<thead>
			<tr align="center">
				<th width="5%">序号</th>
				<th width="10%">公告类型</th>
				<th width="55%">公告标题</th>
                <th width="10%">公开范围</th>
				<th width="10%">发布时间</th>
				<th width="10%">公告状态</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="list" varStatus="vs">
						<tr title="双击打开" align="center" data-id="${list.id}"  dbDialog="true" dbTitle="查看内容" dbDialogId="viewAnnounce" maxable="true"
                        data-mask="true" dbWidth="1100" dbHeight="700" href="Main/Announce/viewip/${list.id}" <c:forEach items="${newannounce}" var="newlist" varStatus="nl"><c:if test="${list.id == newlist.id }">style="font-weight:bold;cursor:pointer"</c:if>
                        </c:forEach> style="cursor:pointer">
							<td>${vs.count }</td>
							<td>
								<c:if test="${list.atype==0}">普通公告</c:if>
								<c:if test="${list.atype==1}">紧急公告</c:if>
							</td>
							<td align="left">${list.title}</td>
                            <td>
                                <c:if test="${list.scope==0}">全部人员</c:if>
                                <c:if test="${list.scope==1}">指定人员</c:if>
                            </td>
                            <td><fmt:formatDate value="${list.sendtime}" pattern="yyyy-MM-dd"/></td>
							<td>
								<c:if test="${list.status==1}">在线</c:if>
								<c:if test="${list.status==0}">下线</c:if>
							</td>
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