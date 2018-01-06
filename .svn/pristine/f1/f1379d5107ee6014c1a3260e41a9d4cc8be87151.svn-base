<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#todoReset").click(function() {
			var table = $("#todoSearch");
			table.find("input").val("");
			table.find("select").val("");
		});
	});
</script>
	<div class="pageHeader">
		<div class="searchBar">
            <form id="pagerForm" method="post" action="Main/Present/main" onsubmit="return navTabSearch(this);">
                <input type="hidden" name="pageNum" value="${page.pageNumber}" />
                <input type="hidden" name="numPerPage" value="${page.pageSize}" />
                <table id="todoSearch" class="searchContent">
                    <tr>
                        <td><span>办理日期：</span></td><td><input type="text" name="sdate" style="width: 100px" class="date" readonly value="${sdate}"> - <input type="text" name="edate" style="width: 100px" class="date" readonly value="${edate}"></td>
                        <td>
                            <div class="subBar">
                                <div class="buttonActive">
                                    <div class="buttonContent">
                                        <button type="submit">查询</button>
                                    </div>
                                </div>
                                <div class="buttonActive" style="margin-left:10px">
                                    <div class="buttonContent">
                                        <button id="todoReset" type="button">重置</button>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
            </form>
		</div>
	</div>
    <form method="post" action="Main/Present/export" onsubmit="return validateCallback(this, xxxAjaxDone)">
        <input type="hidden" name="sdate" value="${sdate}"/>
        <input type="hidden" name="edate" value="${edate}"/>
        <button id="exportPresent" style="display: none" hidden="hidden" type="submit">导出Excel</button>
    </form>
	<div class="pageContent">
		<div class="panelBar">

			<ul class="toolBar">
                <c:if test="${pert:hasperti(applicationScope.present_add, loginModel.limit)}">
					<li><a class="add" href="Main/Present/addip" target="dialog" mask="true" max="false" maxable="true" minable="false" resizable="false" drawable="false" title="新建" width="850" height="300"><span>新建</span></a></li>
                </c:if>
                <c:if test="${pert:hasperti(applicationScope.present_update, loginModel.limit)}">
					<li><a class="edit" href="Main/Present/updateip/{rid}"  target="dialog" rel="present_dialog" max="false" mask="true" maxable="false" minable="false" resizable="false" drawable="false" title="修改" width="850" height="300"><span>修改</span></a></li>
                </c:if>
                <c:if test="${pert:hasperti(applicationScope.present_delete, loginModel.limit)}">
					<li><a class="delete" href="Main/Present/delete/{rid}" target="ajaxTodo" title="您确定要删除该收件吗?"><span>删除</span></a></li>
                </c:if>
                <c:if test="${pert:hasperti(applicationScope.present_export, loginModel.limit)}">
                <li><a class="excel" href="javascript:exportSubmit('exportPresent')" title="导出Excel"><span>导出Excel</span></a></li>
                </c:if>
             </ul>
		</div>
		<table class="table" width="100%" layoutH="110">
			<thead>
				<tr align="center">
                    <th width="3%">序号</th>
                    <th width="24%">抬头</th>
                    <th width="10%">姓名</th>
                    <th width="8%">党员类型</th>
                    <th width="24%">所在工作（学习）单位</th>
                    <th width="24%">目的工作（学习）单位</th>
                    <th width="12%">办理日期</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${! empty page.list}">
						<c:forEach items="${page.list}" var="r" varStatus="vs">
                                <tr style="cursor:pointer" title="双击打开" align="center" target="rid" rel="${r.id}" dbDialog="true" dbDialogId="achieve_dialog" dbTitle="查看/打印" dbWidth="850" dbHeight="600" mask="true" href="Main/Present/toprint/${r.id}">
                                <td>${vs.index+1}</td>
                                <td>${r.punit}</td>
                                <td>${r.pname}</td>
                                <td><c:if test="${r.type == 1}">预备</c:if><c:if test="${r.type == 2}">正式</c:if></td>
                                <td>${r.rfrom}</td>
                                <td>${r.rto}</td>
                                <td>${r.rdate}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr align="center">
							<td colspan="10" style="color: red;">无任何记录！</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span> <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
					<option <c:if test="${page.pageSize==20}">selected</c:if> value="20">20</option>
					<option <c:if test="${page.pageSize==50}">selected</c:if> value="50">50</option>
					<option <c:if test="${page.pageSize==100}">selected</c:if> value="100">100</option>
					<option <c:if test="${page.pageSize==200}">selected</c:if> value="200">200</option>
				</select> <span>条，共${page.totalRow}条</span>
			</div>
			<div class="pagination" targetType="navTab" totalCount="${page.totalRow}" numPerPage="${page.pageSize}" pageNumShown="5" currentPage="${page.pageNumber}"></div>
		</div>
	</div>

