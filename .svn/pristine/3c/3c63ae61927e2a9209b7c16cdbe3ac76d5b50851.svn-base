<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>

<div class="bjui-pageHeader" style="height:70px">
	<form id="pagerForm" action="Main/Docachieve/main" method="post" data-toggle="ajaxsearch">
		<input type="hidden" name="pageSize" value="${page.pageSize}">
		<input type="hidden" name="pageCurrent" value="${page.pageNumber}">
		<input type="hidden" name="orderField" value="${orderField}">
		<input type="hidden" name="orderDirection" value="${orderDirection}">
		<div class="bjui-searchBar">
			<label>文号：</label> <input type="text" name="sdocno" placeholder="请输入关键字" value="${sdocno}" size="12"> 
			<label>来文单位：</label> <input type="text" name="sunit" placeholder="请输入关键字" value="${sunit}" size="12"> 
			<label>文件标题：</label> <input type="text" placeholder="请输入关键字" style="width: 120px" name="stitle" value="${stitle}"> 
			<label>单位分类：</label> <select name="type" data-toggle="selectpicker">
					<option value="">---全部---</option>
					<c:forEach items="${details}" var="temp">
						<option value="${temp.name}" <c:if test="${type==temp.name}">selected</c:if>>${temp.name}</option>
					</c:forEach>
				</select> 
			<div class="pull-right" >
				<c:if test="${pert:hasperti(applicationScope.achieve_add, loginModel.limit)}">
					<button type="button" class="btn-green" data-url="Main/Docachieve/addip" data-toggle="dialog" data-icon="plus" data-mask="true" data-id="awardotadd" data-title="收件登记"
						data-width="850" data-height="450">收件登记</button>
				</c:if>
				<c:if test="${pert:hasperti(applicationScope.achieve_update, loginModel.limit)}">
					<button type="button" class="btn-blue" data-url="Main/Docachieve/updateip/{#bjui-selected}" data-toggle="dialog" data-icon="fa-edit" data-mask="true" data-id="awardotupdate"
						data-title="编辑" data-width="850" data-height="450">编辑</button>
				</c:if>
				<c:if test="${pert:hasperti(applicationScope.achieve_delete, loginModel.limit)}">
					<a  class="btn btn-red" data-url="Main/Docachieve/delete/{#bjui-selected}" data-toggle="doajax" data-icon="fa-trash-o" data-confirm-msg="确定要删除该项吗？">删除</a>
                    <a  class="btn btn-red" data-url="Main/Docachieve/deleteWithFLow/{#bjui-selected}" data-toggle="doajax" data-icon="fa-trash-o" data-confirm-msg="确定要删除文件及审批流程吗？">删除流程</a>
				</c:if>				
				<c:if test="${pert:hasperti(applicationScope.achieve_export, loginModel.limit)}">
					<a class="btn btn-green" href="javascript:exportSubmit('exportDocachieveList')" data-icon="sign-out" title="导出Excel">导出Excel</a>
				</c:if>
			</div>
				<br> 
			<label>状态：</label> <select name="status" data-toggle="selectpicker" data-width="120px">
					<option value="">---全部---</option>
					<option value="0" <c:if test="${status=='0'}">selected</c:if>>未分发</option>
					<option value="1" <c:if test="${status=='1'}">selected</c:if>>已分发</option>
				</select> 
			<label>收文日期：</label> <input type="text" name="sdate" style="width: 120px" data-toggle="datepicker" readonly value="${sdate}"> &nbsp;　&nbsp; —— &nbsp;&nbsp; <input type="text" name="edate" style="width: 120px" 
				data-toggle="datepicker" readonly value="${edate}">			
			<button type="submit" class="btn-default" data-icon="search">查询</button>
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
		</div>
	</form>
</div>

<div class="bjui-pageContent tableContent">
	<form method="post" action="Main/Docachieve/export">
		<input type="hidden" name="sunit" value="${sunit}" /> <input type="hidden" name="sdocno" value="${sdocno}" /> <input type="hidden" name="stitle" value="${stitle}" /> <input
			type="hidden" name="sdate" value="${sdate}" /> <input type="hidden" name="edate" value="${edate}" /> <input type="hidden" name="type" value="${type}" /> <input type="hidden"
			name="status" value="${status}" />
		<button id="exportDocachieveList" style="display: none" hidden="hidden" type="submit">导出excel</button>
	</form>

	<table class="table table-bordered table-hover table-striped table-top table-center" data-nowrap="true" data-toggle="tablefixed">
		<thead>
			<tr align="center">
				<th width="5%">序号</th>
				<th width="10%">收文日期</th>
				<th width="15%">来文单位</th>
				<th width="15%">文号</th>
				<th width="24%">文件标题</th>
				<th width="7%">秘密等级</th>
				<th width="7%">紧急程度</th>
				<th width="5%">份数</th>
				<!-- <th width="7%">承办科室</th> -->
				<th width="6%">备注</th>
				<th width="6%">状态</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty page.list}">
					<c:forEach items="${page.list}" var="dc" varStatus="vs">
						<tr  style="cursor:pointer" title="双击打开" align="center" data-id="${dc.id}" dbDialog="true" dbTitle="查看" dbDialogId="achieve_dialog" maxable="true" mask="true" dbWidth="850" dbHeight="400"
							href="Main/Docachieve/detail/${dc.id}">
							<td>${vs.index+1}</td>
							<td>${dc.receivedate}</td>
							<td align="left">${dc.unit}</td>
							<td align="left">${dc.docno}</td>
							<td class="custom"><c:choose>
									<c:when test="${ dc.receiver == 1}">
										<div style="height: 5px;"></div>
										<a title="点击下载" onclick="doc_filedownload1(this); return false;" href="Main/Attachment/download/${dc.fjid }"><span>${dc.title}</span></a>
									</c:when>
									<c:otherwise>
                                         ${dc.title}
                                    </c:otherwise>
								</c:choose></td>
							<td>${dc.security}</td>
							<td><c:choose>
									<c:when test="${dc.degree=='0'}">平件</c:when>
									<c:when test="${dc.degree=='1'}">平急</c:when>
									<c:when test="${dc.degree=='2'}">特急</c:when>
									<c:otherwise></c:otherwise>
								</c:choose></td>
							<td>${dc.count}</td>
							<%-- <td>${dc.dpname}</td> --%>
							<td>${dc.memo}</td>
							<td><c:choose>
									<c:when test="${dc.status=='0'}">未分发</c:when>
									<c:when test="${dc.status=='1'}">已分发</c:when>
									<c:when test="${dc.status=='2'}">办理中</c:when>
									<c:when test="${dc.status=='3'}">已办结</c:when>
									<c:otherwise></c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr align="center">
						<td colspan="11" style="color: red;">无任何记录！</td>
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
	/**
	 * 关闭高拍仪界面时调用
     * add,update界面声明
	 * @returns true 关闭页面
	 */
	function beforeHighShotMeterPageClose() {
		closeVideo(); // 关闭摄像头
		clearBasePath(); // 删除临时文件
		return true;
	}
</script>