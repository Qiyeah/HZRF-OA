<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<div class="bjui-pageContent">
    <form method="post" action="Main/LeaderDept/update" class="pageForm" data-toggle="validate">
        <input type="hidden" name="t_Leader_Dept.id" value="${leaderDept.id}" />
        <input type="hidden" name="t_Leader_Dept.userId" value="${leaderDept.userId}" />
        <table class="table table-condensed table-hover">
            <tr height="30">
                <td><label class="control-label x85">分管领导：</label> ${userName}</td>
            </tr>
            <tr height="30">
                <td><label class="control-label x85">分管部门：</label> 
                <select name="depts" data-toggle="selectpicker" multiple="multiple" data-width="300">
                    <c:forEach items="${deptList}" var="dept">
                        <option value="${dept.id }" <c:if test="${str:arraycontains(leaderDept.deptIds,dept.id)}">selected="selected"</c:if>>${dept.sname}</option>
                    </c:forEach>
                </select></td>
            </tr>
        </table>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
        <li><button type="submit" class="btn-default" data-icon="save">保存</button></li>
    </ul>
</div>