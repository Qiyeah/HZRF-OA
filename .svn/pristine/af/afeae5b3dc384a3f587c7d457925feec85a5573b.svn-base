<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<form id="pagerForm" action="Main/MyOpinion/main" method="post" data-toggle="ajaxsearch">
		<input type="hidden" name="pageCurrent" value="${page.pageNumber}" />
		<input type="hidden" name="pageSize" value="${page.pageSize}" /> 
		<div class="bjui-searchBar">
		<label>意见内容：</label>
		<input type="text" name="opinion" value="${opinion}" placeholder="请输入关键字"/>
        <button type="submit" class="btn-default" data-icon="search">查询</button>
		<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>         
               <div class="pull-right">
            <c:if test="${pert:hasperti(applicationScope.addOpinion, loginModel.limit)}">
            	<button type="button" class="btn btn-green" data-url="Main/MyOpinion/addip" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="scheduleid" data-title="添加意见"
					data-width="900" data-height="450">添加</button>
            </c:if>
            
            <c:if test="${pert:hasperti(applicationScope.updateOpinion, loginModel.limit)}">
            <a href="Main/MyOpinion/updateip/{#bjui-selected}" class="btn btn-blue" data-toggle="dialog" data-mask="ture" data-id="Enterprpdate" data-icon="fa-edit" data-title="修改意见"
					data-width="900" data-height="450">编辑</a> 
					</c:if>
           
            
            <c:if test="${pert:hasperti(applicationScope.deleteOpinion, loginModel.limit)}">
                <a href="Main/MyOpinion/delete/{#bjui-selected}" class="btn btn-red" data-toggle="doajax" data-icon="fa-trash-o"
					data-confirm-msg="您确定要删除该记录吗?">删除</a>
            </c:if>          
         
		</div>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-nowrap="true" data-toggle="tablefixed" data-selected="true">
		<thead>
			<tr>
				<th width="10%">序号</th>
				<th width="80%">意见内容</th>
				<th width="10%">是否启用</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="temp">
						<tr style="cursor:pointer" title="双击打开" align="center" data-id="${temp.id}" dbDialog="true" dbTitle="修改意见" dbDialogId="opinionUpdate" maxable="true"
                            mask="true" dbWidth="900" dbHeight="450" href="Main/MyOpinion/updateip/${temp.id}">
							<td>${temp.opinionindex}</td>
							<td class="custom">${temp.opinion}</td>
							<td>
                                <c:choose>
                                    <c:when test="${temp.status==1}">是</c:when>
                                    <c:otherwise>否</c:otherwise>
                                </c:choose>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr align="center">
						<td colspan="3" style="color: red;">无任何记录！</td>
					</tr>
				</c:otherwise>
			</c:choose>

		</tbody>
	</table>
</div>
	<%@ include file="/include/pagination.jsp" %>
