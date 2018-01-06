<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
var htmlHead="<dl class='nowrap'><dt style='width: 100px; text-align: right;'>已上传的模板：</dt><dd id='fileHtmlBody'>";
var htmlBody="";
var htmlFoot="</dd></dl>";
function onComplete(event, queueId, fileObj, response, data){
	var obj = eval( "(" + response + ")" );
	htmlBody+="<div id='file_"+obj.id+"' style='height:25px;line-height:25px;'>";
	htmlBody+="<span style='display:none'><input type='checkbox' name='fjid' value='"+obj.id+"' checked/></span>";
	htmlBody+="<a class='btnAttach'></a><span>"+obj.name+"</span> （"+obj.size+"）<a href='javascript:deleteFile("+obj.id+");'><img src='styles/dwz/uploadify/cancel.png' height='13' align='middle'/></a>";
	htmlBody+="</div>";
	$("#fileList").html(htmlHead+htmlBody+htmlFoot);
}
//删除文件ajax请求
function deleteFile(id){
	$("#file_"+id).load("Main/file/deleteUpLoadFile/"+id);
	$("#file_"+id).remove();
	htmlBody=$("#fileHtmlBody").html();
}
</script>

<dl class="nowrap">
	<dt style="width: 100px; text-align: right;">上传文件：</dt>
	<dd>
		<input id="testFileInput" type="file" name="file" uploader="styles/dwz/uploadify/scripts/uploadify.swf"
			cancelImg="styles/dwz/uploadify/cancel.png"  script="Main/file/upload/${id }" 
			fileQueue="fileQueue" auto="true" onComplete="onComplete" />
		<div id="fileQueue"></div>
	</dd>
</dl>
<div class="divider"></div>
<div id="fileList"></div>
		
