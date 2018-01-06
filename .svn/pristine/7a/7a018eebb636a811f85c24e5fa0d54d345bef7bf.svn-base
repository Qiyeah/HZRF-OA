<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<%
	String basePath = request.getContextPath() + "/";
%>
<c:set var="fileList" value="${fileList}" />
<script type="text/javascript">
	function onComplete(event, queueId, fileObj, response, data) {
		var htmlBody = $("#fileHtmlBody").html();
		if (htmlBody == null) htmlBody = "";
		var obj = eval("(" + response + ")");//转换后的JSON对象
		htmlBody += "<div id='file_"+obj.id+"' style='height:25px;line-height:25px;'>";
		htmlBody += "<span style='display:none'><input type='checkbox' name='fjid' value='"+obj.id+"' checked/></span>";
		htmlBody += "<a class='btnAttach'></a>";
		htmlBody += "<a title='点击下载' href='<%=basePath%>Main/Attachment/download/"+obj.id+"'>" + obj.name + "<a/> （" + obj.size + "）";
		htmlBody += "<a href='javascript:deleteFile(" + obj.id + ")'><img src='<%=basePath%>styles/dwz/uploadify/cancel.png' height='13' align='middle'/></a>";
		htmlBody += "</div>";
		$("#fileList").html("<dl class='nowrap'><dd id='fileHtmlBody'>" + htmlBody + "</dd></dl>");
	}
	//删除文件ajax请求
	function deleteFile(id) {
		$("#file_" + id).load("<%=basePath%>Main/Attachment/delete/" + id);
		$("#file_" + id).remove();
		htmlBody = $("#fileHtmlBody").html();
	}
</script>

<dl class="nowrap">
		<dd>
			<div data-toggle="upload" data-uploader="Main/Attachment/saveAttachment" data-icon="cloud-upload" data-multi="true" data-auto="true" data-button-text="附件上传" data-on-upload-success="doc_upload_success"
				data-file-Obj-Name="enclosure" data-file-type-exts="*.pdf;*.img;*.exe;*.zip;*.xlsl;*.jpg"></div>
		</dd>
	</dl>

<div id="fileList">
	<c:if test="${!empty fileList}">
		<dl class="nowrap">
			<dd id="fileHtmlBody">
				<c:forEach items="${fileList}" var="list">
					<div id="file_${list.id}" style="height: 25px; line-height: 25px;">
						<span style="display: none"><input type="checkbox" name="fjid" value="${list.id}" checked /></span> <a class="btnAttach" ></a> <a title="点击下载"
							href="Main/Attachment/download/${list.id }">${list.name}</a> （${list.size}）<a href="javascript:deleteFile(${list.id});"><img src="images/cancel.png" height="13" align="middle" /></a>
					</div>
				</c:forEach>
			</dd>
		</dl>
	</c:if>
</div>
<%-- <div class="noprint">
	<dl class="nowrap">
		<dd>
			<input id="testFileInput" type="file" name="file" uploader="<%=basePath%>styles/dwz/uploadify/scripts/uploadify.swf" cancelImg="<%=basePath%>styles/dwz/uploadify/cancel.png" script="<%=basePath%>Main/Attachment/save"
				fileQueue="fileQueue" auto="true" onComplete="onComplete" />（点击上传附件）
			<div id="fileQueue"></div>
		</dd>
	</dl>
</div>
<div id="fileList">
	<c:if test="${!empty fileList}">
		<dl class="nowrap">
			<dd id="fileHtmlBody">
				<c:forEach items="${fileList}" var="list">
					<div id="file_${list.id}" style="height: 25px; line-height: 25px;">
						<span style="display: none"><input type="checkbox" name="fjid" value="${list.id}" checked /></span> <a class="btnAttach"></a> <a title="点击下载"
							href="<%=basePath%>Main/Attachment/download/${list.id }">${list.name}</a> （${list.size}）<a href="javascript:deleteFile(${list.id});"><img src="<%=basePath%>styles/dwz/uploadify/cancel.png" height="13" align="middle" /></a>
					</div>
				</c:forEach>
			</dd>
		</dl>
	</c:if>
</div> --%>