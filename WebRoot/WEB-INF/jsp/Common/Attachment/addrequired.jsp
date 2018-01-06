﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	function doc_upload_success(file, data) {
		var json = $.parseJSON(data);
		$(this).bjuiajax('ajaxDone', json);
		var str = $("#fileList").html();
		var ids = $('#fjid').val();
		if (str == null)
			str = "";
		for (var i = 0; i < json.length; i++) {
			str += "<div id='file_"+json[i]['id']+"' style='height:25px;line-height:25px;'>";
			str += "<span style='display:none'><input type='checkbox' name='fjid' value='"+json[i]['id']+"' checked/></span>";
			str += "<a class='btnAttach'></a><span>"
					+ "<a title='点击下载'onclick=\"doc_filedownload1(this); return false;\" href='Main/Attachment/download/"+json[i]['id'] + "'>"
					+ json[i]['name']
					+ "</a>"
					+ "</span> （"
					+ json[i]['size']
					+ "）<a href='javascript:deleteFile("
					+ json[i]['id']
					+ ");'><img src='images/cancel.png' height='13' align='middle'/></a>";
			str += "</div>";
			ids += "," + json[i]['id'];
		}
		if (ids.length > 0)
			ids = ids.substring(1);
		$("#fileList").html(str);
		$('#fjid').val(ids);
	}
	function deleteFile(id) {
		$("#file_" + id).load("Main/Attachment/delete/" + id);
		$("#file_" + id).remove();
		htmlBody = $("#fileHtmlBody").html();
	}
	function doc_filedownload1(a) {
        $.fileDownload($(a).attr('href'), {
            failCallback: function(responseHtml, url) {
                if (responseHtml.trim().startsWith('{')) responseHtml = responseHtml.toObj()
                $(a).bjuiajax('ajaxDone', responseHtml)
            }
        })
    }
</script>
<dl class="nowrap">
		<dd>
			<div data-toggle="upload" class="required" data-uploader="Main/Attachment/saveAttachment" data-icon="cloud-upload" data-multi="true" data-auto="true" data-button-text="附件上传" data-on-upload-success="doc_upload_success"
				data-file-Obj-Name="enclosure" data-file-type-exts="*.pdf;*.img;*.exe;*.xlsx;*.xls;*.jpg;*.docx;*.doc;*.png;*.bmp;*.png;*.tiff;*.gif;*.wps;*.wpt;*.et;*.ett"></div>
		</dd>
	</dl>
	<div id="fileList"></div>