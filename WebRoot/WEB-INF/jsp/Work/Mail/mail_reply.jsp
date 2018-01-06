<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/inc.jsp" %>
<c:set var="model" value="${model}"/>
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" var="now"/>
<div class="bjui-pageContent">
    <form id="pageForm" class="pageForm" action="Main/Mail/save" method="post" data-toggle="validate">   
        <input name="t_Mail.id" type="hidden"/>
        <input name="t_Mail.fromId" type="hidden" value="${sessionScope.loginModel.userId}"/>
        <input name="t_Mail.boxId" type="hidden" id="boxId" value="2"/>
        <input name="t_Mail.isRead" type="hidden" value="0"/>
        <input name="t_Mail.sendTime" type="hidden" value="${now}"/>
        <input name="originalMailId" type="hidden" value="${model.id}"/>
        <input name="originalMailStatus" type="hidden" value="${model.status}"/>
        <div class="pageFormContent" layoutH="58" style="overflow-x:hidden;">
            <table class="wordInfo" align="center" style="width: 99%;">
                <tr class="nowrap">
                    <td style="width: 15%;" class="title">收 件 人</td>
                    <td style="width: 85%;">
                        <input name="param.userId" type="hidden" value="${model.fromId}"/>
                        <input type="text" readonly="readonly" name="param.userName" size="82" value="${fromUser}" class="required"  data-rule="required" data-msg-required="必填"
                        data-group="param" data-title="选择收件人员" data-toggle="lookup" data-url="Main/Mail/getUsers" data-width="600" data-height="550"/>
                    </td>
                </tr>
                <tr class="nowrap">
                    <td style="width: 15%;" class="title">抄 送 人</td>
                    <td style="width: 85%;">
                        <input name="param2.userId" type="hidden" value=""/>
                    	<input type="text" readonly="readonly" name="param2.userName" size="82" data-group="param2" data-title="选择抄送人" data-toggle="lookup"
                    	 data-url="Main/Mail/getUsers" data-width="600" data-height="550"/>
                    </td>
                </tr>
                <tr class="nowrap">
                    <td class="title">主　　题</td>
                    <td>
                        <input name="t_Mail.title" type="text" style="width: 100%;" class="required" data-rule="required" data-msg-required="必填" value="${model.title}"/>
                    </td>
                </tr>
                <tr>
                    <td class="title">附件列表</td>
                    <td>
                        <div id="uploadFile" class="unitBox"><c:import url="common/upload/edit.jsp" /></div>
                    </td>
                </tr>
                <tr>
                    <td class="title">正　　文</td>
                    <td><textarea  name="t_Mail.content" id="mailContent" data-toggle="kindeditor" rows="16" style="width: 100%;" data-items="fontname, fontsize, |, 
                    forecolor, hilitecolor, bold, italic, underline, removeformat, |, 
                    justifyleft, justifycenter, justifyright, insertorderedlist, insertunorderedlist, |, 
                    emoticons">
                      <br/><br/>
                            ------------------ 原始邮件 ------------------<br/>
                            发件人: ${fromUser};<br/>
                            发送时间:${fn:substring(model.sendTime, 0, 19)}<br/>
                            收件人: ${model.receiver};<br/>
                            <c:if test="${!empty model.copyer}">
                            抄送: ${model.copyer}<br/>
                            </c:if>
                            ${model.content}
                    </textarea>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
<div class="bjui-pageFooter">
            <ul>
           		<li><button type="button" class="btn-close" data-icon="close">关闭2</button></li>
                <li><button type="button" class="btn-default" data-icon="fa-plane" onclick="javascript:onSubmit(2);">立即发送</button></li>
            </ul>
     </div>
<script type="text/javascript">
    function onSubmit(flag) {
        $("#boxId").val(flag);
        $("#pageForm").submit();
        $(this).dialog('close', "Mailview");
    }
    $(function(){
        $("#mailContent").focus();
    });
</script>