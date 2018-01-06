<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<c:set var="fileList" value="${fileList}"/>
<c:if test="${!empty fileList}">
	<dl class='nowrap'>
		<dd>
			<c:forEach items="${fileList}" var="list">
				<div id="file_${list.id}" style="height:25px;line-height:25px;">
					<a class="btnAttach"></a><a title="点击下载" href="Main/Attachment/download/${list.id }" onclick="doc_filedownload1(this); return false;">${list.name}</a> （${list.size}）
				</div>
			</c:forEach>
		</dd>
	</dl>
</c:if>
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