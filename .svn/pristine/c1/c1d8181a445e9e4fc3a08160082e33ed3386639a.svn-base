<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<script type="text/javascript">

$(document).ready(function(){
	var scope = ${announce.scope};
	if (1 != scope){
		$("#person2").show();
		
        $("#person").hide();
        $("#person1").hide();
        
	}else{
		$("#person1").show();
        $("#person").show();
        $("#person2").hide();
      
	}
});
$("input[name='t_Announce.scope']").on('ifChanged', function() {
    if ($("input[name='t_Announce.scope']:checked").val() != "1") {
        $("#person2").show();
        $("#person").hide();
        $("#person1").hide();
    } else {
    	$("#person1").show();
        $("#person").show();
        $("#person2").hide();
    }
});
</script>
    <input name="t_Announce.id" type="hidden" value="${announce.id}">
    <table class="wordInfo" align="center" style="width: 100%;">
		<tr>
            <td style="width: 10%;" class="title">公告状态</td>
            <td style="width: 40%;">
                <select name="t_Announce.status" data-width="100%" data-toggle="selectpicker">
						<option value="1" <c:if test="${announce.status==1 }">selected</c:if>>在线显示</option>
						<option value="0" <c:if test="${announce.status==0 }">selected</c:if>>公告下线</option>
					</select>
            </td>
            <td style="width: 10%;" class="title">公告类型</td>
            <td style="width: 40%;">
                <select name="t_Announce.atype" data-width="100%"  data-toggle="selectpicker">
                    <option value="0" <c:if test="${announce.atype==0 }">selected</c:if>>普通公告</option>
                    <option value="1" <c:if test="${announce.atype==1 }">selected</c:if>>紧急公告</option>
                </select>
            </td>
        </tr>
        <tr>
            <td class="title">公开范围</td>
            <td>
                <label> <input type="radio" name="t_Announce.scope" value="0" data-toggle="icheck" checked="checked"/>全部人员</label>
                <label>  <input type="radio" name="t_Announce.scope" value="1" data-toggle="icheck" <c:if test="${ announce.scope == 1 }">checked="checked"</c:if>/>指定人员</label>
            </td>
            <td class="title" id="person">公开人员</td>
            <td id="person1">
                <input name="userId" type="hidden" value="${announce.scope_uid}"/>
            	<input type="text" readonly="readonly" name="userName" style="width:100%" lookupGroup="param" data-title="选择公开人员" data-toggle="lookup" data-url="Main/Mail/getUsers/${announce.scope_uid}" data-width="600" data-height="550" <c:if test="${ announce.scope == 1 }"> value="${ announce.scope_uname}" </c:if>/>
            </td>
            <td id="person2" colspan="2"></td>
        </tr>
        <tr>
            <td class="title">公告标题</td>
            <td colspan="3"><input name="t_Announce.title" style="width: 100%"  value="${announce.title}" class="required" type="text" maxlength="100" data-rule="required" data-msg-required="必填" alt="请输入标题"/></td>
        </tr>
        <tr>
            <td colspan="4"><textarea name="t_Announce.content" id="j_form_content" class="j-content" style="width: 100%; height: 510px;" data-toggle="kindeditor" data-minheight="360">${announce.content}</textarea></td>
        </tr>
    </table>
