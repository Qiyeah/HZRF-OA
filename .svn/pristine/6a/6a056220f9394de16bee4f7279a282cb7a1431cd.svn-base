<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	function doc_filedownload1(a) {
		$.fileDownload($(a).attr('href'), {
					failCallback: function(responseHtml, url) {
			if (responseHtml.trim().startsWith('{')) responseHtml = responseHtml.toObj()
			$(a).bjuiajax('ajaxDone', responseHtml)
		}
	})
	}

	$(document).ready(function() {
		$("#todoReset").click(function() {
			var table = $("#todoSearch");
			table.find("input").val("");
			table.find("select").val("");
		});
	});
</script>
<div class="bjui-pageHeader">
	<form method="post" id="pagerForm" action="Main/Soft/main" method="post" data-toggle="ajaxsearch">
		<input type="hidden" name="pageSize" value="${page.pageSize}"> <input type="hidden" name="pageCurrent" value="${page.pageNumber}"> <input type="hidden"
			name="orderField" value="${orderField}"> <input type="hidden" name="orderDirection" value="${orderDirection}">
		<div class="bjui-searchBar">
			<label>软件名：</label><input type="text" placeholder="请输入关键字" name="stitle" style="width: 120px;" value="${stitle}">
			<button type="submit" class="btn-default" data-icon="search">查询</button>
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>

			<div class="pull-right">
				<c:if test="${pert:hasperti(applicationScope.achieve_add, loginModel.limit)}">
					<button type="button" class="btn-green" data-url="Main/Soft/addip" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="awardotadd" data-title="软件添加"
						data-width="850" data-height="450">添加</button>
				</c:if>
				<c:if test="${pert:hasperti(applicationScope.achieve_update, loginModel.limit)}">
					<button type="button" class="btn-blue" data-url="Main/Soft/updateip/{#bjui-selected}" data-toggle="dialog" data-icon="fa-edit" data-mask="true" data-id="awardotadd"
						data-title="编辑" data-width="850" data-height="450">编辑</button>
				</c:if>
				<c:if test="${pert:hasperti(applicationScope.achieve_delete, loginModel.limit)}">
					<a class="btn btn-red" data-url="Main/Soft/delete/{#bjui-selected}" data-toggle="doajax" data-icon="fa-trash-o" data-confirm-msg="确定要删除该软件吗？">删除</a>
				</c:if>
			</div>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top table-center" data-nowrap="true"  data-toggle="tablefixed" width="98%">
		<thead>
			<tr>
				<th width="5%">序号</th>
				<th width="30%">软件名</th>
				<th width="50%">软件描述</th>
				<th width="15%">上传者</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="dc" varStatus="vs">
						<tr  style="cursor:pointer" title="双击打开" align="center" data-id="${dc.id}" dbDialog="true" dbTitle="修改" dbDialogId="approve_dialog" maxable="true" mask="true" dbWidth="850" dbHeight="400"
							href="Main/Soft/updateip/${dc.id}">
							<td>${vs.count}</td>
							<td class="custom"><c:choose>
									<c:when test="${not empty dc.fjid}">
										<div style="height: 5px;"></div>
										<a title="点击下载" onclick="doc_filedownload1(this); return false;" href="Main/Attachment/download/${dc.fjid}"><span>${dc.softname}</span></a>
									</c:when>
									<c:otherwise>
                                            ${dc.softname}
                                        </c:otherwise>
								</c:choose></td>
							<td class="custom">${dc.softdetail}</td>
							<td>${dc.uname}</td>
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
<script type="text/javascript">
    function doc_filedownload1(a) {
        $.fileDownload($(a).attr('href'), {
            failCallback: function(responseHtml, url) {
                if (responseHtml.trim().startsWith('{')) responseHtml = responseHtml.toObj()
                $(a).bjuiajax('ajaxDone', responseHtml)
            }
        })
    }
</script>