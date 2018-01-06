<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageHeader">
    <div class="bjui-searchBar">
        <form id="pagerForm" action="Main/WorkingDay/main" method="post" data-toggle="ajaxsearch">
            <input type="hidden" name="pageSize" value="${page.pageSize}" /> 
            <input type="hidden" name="pageCurrent" value="${page.pageNumber}" />
            <table>
                <tr style="height: 35px;">
                    <td><label class="control-label x90">日期：</label> <input type="text" name="startDate" value="${startDate}" id="sdate" readonly data-toggle="datepicker"
                        data-pattern="yyyy-MM-dd" maxlength="12" class="form-control" size="15"> - <input type="text" name="endDate" value="${endDate}" id="edate" readonly
                        data-toggle="datepicker" data-pattern="yyyy-MM-dd" maxlength="12" class="form-control" size="15">

                        <button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp; <a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch"
                        data-clear-query="true" data-icon="undo">清空查询</a></td>
                </tr>
                <div class="pull-right">
                    <button type="button" class="btn-green" data-url="Main/WorkingDay/addip" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="addWorkingDay" data-title="添加工作日时间设定"
                        data-width="1000" data-height="600">添加</button>
                </div>
            </table>
        </form>
    </div>
</div>

<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top table-center" data-toggle="tablefixed">
        <thead>
            <tr>
                <th width="5%" class="center">序号</th>
                <th width="75%" class="center">日期</th>
                <th width="20%" class="center">操作</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${! empty page.list}">
                    <c:forEach items="${page.list}" var="model" varStatus="vs">
                        <tr>
                            <td>${vd.count }</td>
                            <td>${model.date}</td>
                            <td><a href="Main/WorkingDay/delete/${model.id}" class="btn btn-red" data-toggle="doajax" data-confirm-msg="确定要删除该工作日设定吗吗？">删除</a></td>
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
