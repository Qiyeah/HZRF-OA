<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<c:set var="page" value="${page}"/>
<c:set var="folderid" value="${id}"/>
<c:set var="filename" value="${filename}"/>
<div class="pageHeader" style="border:1px #B8D0D6 solid" >
	<div class="searchBar">当前位置：文件管理<c:if test="${! empty id }">>></c:if>${nowPath }</div>
</div>
<div class="pageHeader" style="border:1px #B8D0D6 solid" >
	<form id="pagerForm" onsubmit="return divSearch(this, 'jbsxBox');" action="Main/file/fileList/${id }" method="post">
		<input type="hidden" name="pageNum" value="${page.pageNumber}" />
		<input type="hidden" name="numPerPage" value="${page.pageSize}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>文件名：<input type="text" name="filename" value="${filename }"/></td>
					<td><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></td>
				</tr>
			</table>
		</div>
	</form>
</div>

<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid" >
	<div class="panelBar">
		<ul class="toolBar">
		    <li><a class="add" href="Main/file/createFolderip/${id }" target="dialog" rel="createFolder" mask="true"
                   width="500" height="260"><span>创建目录</span></a></li>
			<c:if test="${! empty id }">
			    <li><a class="edit" href="Main/file/updateFolderip/${id }" target="dialog" rel="updateFolder" mask="true"
                       width="500" height="260"><span>修改目录</span></a></li>
				<li><a class="delete" href="Main/file/deleteFolder/${id }" target="ajaxTodo" title="确定要删除吗?"  rel="wdgl_main"><span>删除目录</span></a></li>
				<li><a class="view" href="Main/file/viewFolder/${id }" target="dialog" rel="viewFolder" mask="true"
                       width="500" height="260"><span>目录属性</span></a></li>
				<li class="line">line</li>
				<li><a class="upload" mask="true" href="Main/file/uploadip/${id }" target="dialog"  rel="uploadFile"
                       width="700" height="350" ><span>上传文件</span></a></li>
                <li><a class="edit" mask="true" href="Main/file/updateip/${fileId }" target="dialog"  rel="updateFile"
                       width="700" height="350" ><span>修改文件</span></a></li>
                <li><a class="delete" mask="true" href="Main/file/delete/${fileId }" target="dialog"  rel="deleteFile"
                       width="700" height="350" ><span>删除文件</span></a></li>
                <li><a class="view" mask="true" href="Main/file/view/${fileId }" target="dialog"  rel="viewFile"
                       width="700" height="350" ><span>查看文件</span></a></li>
			</c:if>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="142" >
		<thead>
			<tr align="center">
				<th width="5%"></th>
				<th>文件名</th>
				<th width="10%">上传者</th>
				<th width="8%">文件大小</th>
				<th width="20%">上传时间</th>
				<th width="8%">下载次数</th>
			</tr>
		</thead>
		<tbody>
		  <c:forEach items="${page.list}" var="list">
			<tr style="cursor:pointer" title="双击打开" align="center"  target="fileId" rel="${list.id}" dbDialog="true" dbTitle="修改" dbDialogId="opinionUpdate" maxable="true"
                mask="true" dbWidth="800" dbHeight="350" href="Main/file/updateip/${list.id}">
				<td><img src="/${project_name }/images/${list.type}.png" height="20"></td>
				<td><a href="Main/file/download/${list.id }" style="color:blue;">${list.filename }</a></td>
				<td>${list.username }</td>
				<td>${list.size }</td>
				<td><fmt:formatDate value="${list.uploadtime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${list.downnum }</td>
			</tr>
		   </c:forEach>	
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value},'fileBox')">
				<option <c:if test="${page.pageSize==20}">selected="selected"</c:if> value="20" >20</option>
				<option <c:if test="${page.pageSize==50}">selected="selected"</c:if> value="50">50</option>
				<option <c:if test="${page.pageSize==100}">selected="selected"</c:if> value="100">100</option>
				<option <c:if test="${page.pageSize==200}">selected="selected"</c:if> value="200">200</option>
			</select>
			<span>条，共${page.totalRow}条</span>
		</div>
		<div class="pagination" targetType="ajax" rel="fileBox" totalCount="${page.totalRow}" numPerPage="${page.pageSize}" pageNumShown="9" currentPage="${page.pageNumber}"></div>
	</div>
</div>