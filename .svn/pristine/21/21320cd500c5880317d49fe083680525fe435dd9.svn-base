<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="Main/Field/main" method="post">
        <input type="hidden" name="pageSize" value="${page.pageSize}">
        <input type="hidden" name="pageCurrent" value="${page.pageNumber}">
        <input type="hidden" name="orderField" value="${orderField}">
        <input type="hidden" name="orderDirection" value="${orderDirection}">
        <div class="bjui-searchBar">           
            <label>数据表名：</label><select name="qtid" data-toggle="selectpicker" class="show-tick" style="display: none;">
				<option value="">---全部---</option>
				<c:forEach items="${tables }" var="table">
					<option <c:if test="${table.id==qtid }">selected</c:if> value="${table.id }">${table.tablename }</option>
				</c:forEach>
            </select>&nbsp;
            <label>字段名：</label><input type="text" name="qname" value="${qname}" class="form-control" size="8">&nbsp;            
            <label>替换字符：</label><input type="text" name="qkeyword" value="${qkeyword}" class="form-control" size="8">&nbsp;            
            <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
            <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
            <div class="pull-right">
				<button type="button" class="btn-blue" data-url="Main/Field/addip" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="fieldadd" data-title="新增数据字段" data-width="500" data-height="250">添加</button>&nbsp;
				<button type="button" class="btn-blue" data-url="Main/Field/deletes" data-toggle="doajaxchecked" data-confirm-msg="确定要删除选中项吗？" data-group="ids" data-icon="remove" title="可以在控制台(network)查看被删除ID">删除选中</button>&nbsp;
				<button type="button" class="btn-blue" data-url="Main/Field/importTableip" data-toggle="dialog" data-icon="import" data-mask="true" data-id="fieldadd" data-title="导入数据字段" data-width="500" data-height="250">数据表导入</button>&nbsp;
            </div>
        </div>
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed">
        <thead>
            <tr>
				<th width="20%" class="center">数据表名</th>
				<th width="20%" class="center" data-order-field="name">字段名</th>
				<th width="20%" class="center" data-order-field="keyword">替换字</th>
				<th width="10%" class="center">替换频率</th>
				<th width="10%" class="center">计算值</th>
                <th width="5%" class="center"><input type="checkbox" class="checkboxCtrl" data-group="ids" data-toggle="icheck"></th>
                <th width="15%" class="center">操作</th>
            </tr>
        </thead>
        <tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="model" varStatus="vs">
			            <tr>
							<td>${tableHm.get(model.tid)}</td>
							<td>${model.name}</td>
							<td>${model.keyword}</td>
							<td>
                                <c:choose>
                                    <c:when test="${model.type=='0'}">1次</c:when>
                                    <c:when test="${model.type=='1'}">多次</c:when>
                                </c:choose>
							</td>
							<td>
                                <c:choose>
                                    <c:when test="${model.calculation==0}">否</c:when>
                                    <c:when test="${model.calculation==1}">是</c:when>
                                </c:choose>
							</td>
                			<td><input type="checkbox" name="ids" data-toggle="icheck" value="${model.id}"></td>
                			<td>
                    			<a href="Main/Field/updateip/${model.id}" class="btn btn-green" data-toggle="dialog" data-mask="ture" data-id="fieldudate" data-title="编辑数据字段" data-width="500" data-height="250">编辑</a>
                    			<a href="Main/Field/delete/${model.id}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除该数据字段吗？">删</a>&nbsp;&nbsp;&nbsp;
                			</td>								
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