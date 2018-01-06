<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
	<form id="pagerForm" action="Main/InnerSend/main" method="post" data-toggle="ajaxsearch">
		<input type="hidden" name="pageSize" value="${page.pageSize}">
		<input type="hidden" name="pageCurrent" value="${page.pageNumber}">
		<input type="hidden" name="orderField" value="${orderField}">
		<input type="hidden" name="orderDirection" value="${orderDirection}">
		<div class="bjui-searchBar">
		<label>文号：</label> <input type="text" name="sdocno" placeholder="请输入关键字" value="${sdocno}" size="12" />
		 <label>发文单位：</label> <input type="text" name="sunit" placeholder="请输入关键字" value="${sunit}" size="12" /> 
		<label>文件标题：</label> <input type="text" name="stitle" placeholder="请输入关键字" value="${stitle}" size="12" /> 
        <label>收文日期：</label> <input type="text" name="sdate" size="12" data-toggle="datepicker" readonly value="${sdate}" /> — <input type="text" name="edate" size="12" 
            data-toggle="datepicker" readonly value="${edate}" />        
        <button type="submit" class="btn-default" data-icon="search">查询</button>
        <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
		<div class="pull-right" >
            <button type="button" class="btn-blue" data-url="Main/InnerSend/viewerList/{#bjui-selected}" data-toggle="dialog" data-icon="fa-eye" data-mask="true" data-id="innersendviewer"
                    data-title="查看收阅情况" data-width="500" data-height="450">查看读者</button>
 			<c:if test="${pert:hasperti(applicationScope.addInnerSend, loginModel.limit)}">
				<button type="button" class="btn-green" data-url="Main/InnerSend/addip" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="innnersendadd" data-title="添加内部发文"
					data-width="850" data-height="450">内部发文</button>
			</c:if>
			<c:if test="${pert:hasperti(applicationScope.updateInnerSend, loginModel.limit)}">
				<button type="button" class="btn-blue" data-url="Main/InnerSend/updateip/{#bjui-selected}" data-toggle="dialog" data-icon="fa-edit" data-mask="true" data-id="innersendupdate"
					data-title="编辑内部发文" data-width="850" data-height="450">编辑</button>
			</c:if>
			<c:if test="${pert:hasperti(applicationScope.deleteInnerSend, loginModel.limit)}">
				<a  class="btn btn-red" data-url="Main/InnerSend/delete/{#bjui-selected}" data-toggle="doajax" data-icon="fa-trash-o" data-confirm-msg="确定要删除该项吗？">删除</a>
			</c:if>
			<c:if test="${pert:hasperti(applicationScope.exportInnerSend, loginModel.limit)}">
				<a class="btn btn-green" href="javascript:exportSubmit('exportInnerSendList')" data-icon="sign-out" title="导出Excel">导出Excel</a>
			</c:if>
		</div>
		</div>
	</form>
</div>

<div class="bjui-pageContent tableContent">
	<form method="post" action="Main/InnerSend/export">
		<input type="hidden" name="sunit" value="${sunit}" /> 
		<input type="hidden" name="sdocno" value="${sdocno}" /> 
		<input type="hidden" name="stitle" value="${stitle}" /> 
		<input type="hidden" name="sdate" value="${sdate}" /> 
		<input type="hidden" name="edate" value="${edate}" />
		<button id="exportInnerSendList" style="display: none" hidden="hidden" type="submit">导出excel</button>
	</form>
	<table class="table table-bordered table-hover table-striped table-top table-center" data-nowrap="true" data-toggle="tablefixed">
		<thead>
			<tr align="center">
				<th width="5%">序号</th>
				<th width="10%">发文日期</th>
				<th width="15%">发文单位</th>
				<th width="15%">文号</th>
				<th width="25%">文件标题</th>
				<th width="5%">秘密等级</th>
				<th width="5%">紧急程度</th>
				<th width="20%">接收人</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="dc" varStatus="vs">
						<tr align="center" data-id="${dc.id}">
							<td>${vs.index+1}</td>
							<td>${dc.senddate}</td>
							<td align="left">${dc.unit}</td>
							<td align="left">${dc.docno}</td>
							<td align="left">${dc.title}</td>
							<td>${dc.security}</td>
							<td><c:choose>
									<c:when test="${dc.degree=='0'}">平件</c:when>
									<c:when test="${dc.degree=='1'}">急件</c:when>
									<c:when test="${dc.degree=='2'}">特急</c:when>
									<c:otherwise></c:otherwise>
								</c:choose></td>
							<td>${dc.unames}</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr align="center">
						<td colspan="8" style="color: red;">无任何记录！</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>
<div class="bjui-pageFooter">
	<div class="pages">
		<span>每页&nbsp;</span>
		<div class="selectPagesize">
			<select data-toggle="selectpicker" data-toggle-change="changepagesize">
				<option <c:if test="${page.pageSize==20}">selected="selected"</c:if> value="20">20</option>
				<option <c:if test="${page.pageSize==50}">selected="selected"</c:if> value="50">50</option>
				<option <c:if test="${page.pageSize==100}">selected="selected"</c:if> value="100">100</option>
				<option value="200" <c:if test="${page.pageSize==200}">selected="selected"</c:if>>200</option>
			</select>
		</div>
		<span>条，共${page.totalRow}条</span>
	</div>
	<div class="pagination-box" data-toggle="pagination" data-total="${page.totalRow}" data-page-size="${page.pageSize}" data-page-current="${page.pageNumber}"></div>
</div>