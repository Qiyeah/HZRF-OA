<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/inc.jsp" %>
<c:set var="model" value="${model}"/>
<c:set var="fileList" value="${fileList}"/>
<script type="text/javascript">
	function reply() {
	    var url = "Main/Mail/reply/" + "${model.id}";
	    $(this).dialog({
				id : 'replyid',
				url : url,
				title : '回复',
				mask : true,
				drawable : true,
				resizable : true,
				maxable : true,
				width : 1000,
				height : 600
			});
	    
	}
	function forward() {
	    var url = "Main/Mail/forward/" + "${model.id}";
	      $(this).dialog({
				id : 'forwardid',
				url : url,
				title : '转发',
				mask : true,
				drawable : true,
				resizable : true,
				maxable : true,
				width : 1000,
				height : 600
			});
	}
</script>
<div class="bjui-pageContent">
       <form  class="pageForm" action="Main/Mail/closeView" method="post" data-toggle="validate">
            <table class="wordInfo" align="center" style="width: 99%;">
                <tr>
                    <td style="width: 15%;height: 40px;" class="title">主　　题</td>
                    <td style="width: 85%;">
                        <h2 style="font-size: 14px;">${model.title}</h2>
                    </td>
                </tr>
                <tr class="nowrap">
                    <td class="title" style="height: 40px;">发 件 人</td>
                    <td>${model.fromUserName}(${model.dlid})</td>
                </tr>
                <tr class="nowrap">
                    <td class="title" style="height: 40px;">时　　间</td>
                    <td><fmt:formatDate value="${model.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                <tr class="nowrap">
                    <td class="title" style="height: 40px;">收 件 人</td>
                    <td>${model.receiver}</td>
                </tr>
                <c:if test="${!empty model.copyer}">
                    <tr class="nowrap">
                        <td class="title" style="height: 40px;">抄 送 人</td>
                        <td>${model.copyer}</td>
                    </tr>
                </c:if>
                <tr>
                    <td class="title" style="height: 40px;">附件列表</td>
                    <td>
                        <div id="uploadFile" class="unitBox"><c:import url="common/upload/view.jsp" /></div>
                    </td>
                </tr>
                <tr class="nowrap">
                    <td class="title" style="height: 210px;">正　　文</td>
                    <td valign="top">
                    <%-- <textarea  name="t_Mail.content" data-toggle="kindeditor" rows="16" style="width: 100%;" data-items="fontname, fontsize, |, 
                    forecolor, hilitecolor, bold, italic, underline, removeformat, |, 
                    justifyleft, justifycenter, justifyright, insertorderedlist, insertunorderedlist, |, 
                    emoticons">${model.content}</textarea> --%>
                    <pre style="background-color:white; border:0; line-height:25px">${model.content}</pre>
                    
                    </td>
                </tr>
            </table>
    </form>
</div>
<div class="bjui-pageFooter">
            <ul>
            	<li><button type="submit" class="btn-close" data-icon="close">关闭</button></li>
            	<li><button type="button" class="btn-default"  data-icon="fa-share-square" onclick="forward()">转发</a></li>
           		<li><button type="button" class="btn-default"  data-icon="fa-edit" onclick="reply()">回复</a></li>
            </ul>
        </div>