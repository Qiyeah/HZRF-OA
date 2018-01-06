<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
    $('input[name="doc-check-t"]').on('ifChanged', function(e) {
        var checked = $(this).is(':checked'), val = $(this).val();
        $("#tau1").val("");
        $("#tau2").val("");
        $("#tau3").val("");
        $("#tau4").val("");
        if (checked){
        $("#typeuid").css("display", "");
        $("#typeidu_s").css("display", "none");
        
        $("#remind_u1").css("display", "none");
        $("#remind_u2").css("display", "");
        
        $("#doc-check").val("1");
        }else{
         $("#typeuid").css("display", "none");
         $("#typeidu_s").css("display", "");
         
         $("#remind_u1").css("display", "");
         $("#remind_u2").css("display", "none");
         
         $("#doc-check").val("");
        }
    });
    
    $('input[name="t_Myschedule.scope"]').on('ifChanged', function(e) {
        var checked = $(this).is(':checked'), val = $(this).val();
       	$("#hiddenuid").val("");
       	$("#useuid").val("");
        if (checked){
        	if(val == 1){
        	 $("#openuid").css("display", "");
        	}else {
        	$("#openuid").css("display", "none");
        	}
        }
    });
    
   $(function (){
    	var event = "${model.event}";
    	var wdate = "${model.wdate}";
    	var edate = "${model.edate}";
    	var scope = "${model.scope}";
    	$("#tau1").val(wdate.substring(0,wdate.length-2));
        $("#tau2").val(edate.substring(0,edate.length-2));
    	if( event != ""){
    	  $("#typeuid").css("display", "");
       	  $("#typeidu_s").css("display", "none");
       	  
       	  $("#remind_u1").css("display", "none");
          $("#remind_u2").css("display", "");
       	  
       	  $("#doc-check").val("1");
    	}
    	if(scope == 1){
    	  $("#openuid").css("display", "");
    	}
    });  
</script>

<div class="bjui-pageContent">
		<form class="pageForm" action="Main/Myschedule/update" method="post" data-toggle="validate">
		<input type="hidden" name="t_Myschedule.id" value="${model.id}" />
		<input type="hidden" name="t_Myschedule.u_id" value="${model.u_id}" />
			<table class="wordInfo" align="center" style="width: 98%;">
				<tr>
					<td class="title">编辑人</td>
					<td>${HMname.get(model.u_id)}</td>
				</tr>
				<tr>
					<td class="title">全天事件</td>
					<td><input type="checkbox" name="doc-check-t" id="doc-check" value="" data-toggle="icheck" <c:if test="${! empty model.event}">checked="checked"</c:if> /></td>
				</tr>
				<tr>
					<td class="title">提醒</td>
					<td id="remind_u1"><select name="t_Myschedule.remind" data-toggle="selectpicker" data-width="100%">
							<option value="">不提醒</option>
						<optgroup label="提醒">
						<option value="1" <c:if test="${model.remind == 1}">selected="selected"</c:if> >5分钟前</option>
						<option value="2" <c:if test="${model.remind == 2}">selected="selected"</c:if> >10分钟前</option>
						<option value="3" <c:if test="${model.remind == 3}">selected="selected"</c:if> >30分钟前</option>
						<option value="4" <c:if test="${model.remind == 4}">selected="selected"</c:if> >1小时钟前</option>	
						<option value="5" <c:if test="${model.remind == 5}">selected="selected"</c:if> >2小时钟前</option>
						</optgroup>
					</select>
					
					<td id="remind_u2" style="display: none">
					<select name="rem" data-toggle="selectpicker" data-width="100%">
							<option value="">不提醒</option>
						<optgroup label="提醒">
						<option value="6" <c:if test="${model.remind == 6}">selected="selected"</c:if> >1天前</option>		
						</optgroup>
					</select></td>
				</tr>
				<tr>
					<td width="25%" class="title">日程时间</td>
					<td width="75%" id="typeidu_s"><input type="text" name="t_Myschedule.wdate" id="tau1" data-toggle="datepicker" data-pattern="yyyy-MM-dd HH:mm:ss" data-rule="datetime" maxlength="25" size="26" class="required"/> 至
									<input type="text" name="t_Myschedule.edate" id="tau2" data-toggle="datepicker" data-pattern="yyyy-MM-dd HH:mm:ss"  data-rule="datetime" maxlength="25" size="27" class="required"/>
                    
                    <td width="75%" id="typeuid" style="display:none"><input type="text" name="t_Myschedule.event" id="tau3" value="${model.event}" data-toggle="datepicker"  data-rule="date" maxlength="13" size="26" class="required"/> 至
									<input type="text" name="t_Myschedule.event1" id="tau4" value="${model.event1}" data-toggle="datepicker" data-rule="date" maxlength="13" size="27" class="required"/> 
                    </td>
				</tr>
				<tr>
			<td class="title">公开范围</td>
			<td><label><input type="radio" name="t_Myschedule.scope" value="3" data-toggle="icheck" <c:if test="${model.scope == 3}">checked</c:if>/>私人</label>
				<label><input type="radio" name="t_Myschedule.scope" value="0" data-toggle="icheck" <c:if test="${model.scope == 0}">checked</c:if>/>全部人员</label>
		 		<label><input type="radio" name="t_Myschedule.scope" value="1" data-toggle="icheck" <c:if test="${model.scope == 1}">checked</c:if>/>指定人员</label></td>
			</tr>
			<tr style="display:none" id="openuid">
				<td  class="title">公开人员</td>
				<td ><input name="userId" type="hidden" id="hiddenuid" value="${model.receiver_id}" /><input type="text" readonly="readonly" name="userName" id="useuid" class="required"
					size="56" lookupGroup="param" data-title="选择公开人员" data-toggle="lookup" data-url="Main/Mail/getUsers" data-width="600" data-height="550" value="${model.receiver}" /></td>
			</tr>
				<tr>
					<td class="title">日程类型</td>
					<td><input name="chairman.type" type="text" value="${model.type}" suggestFields="type" suggestUrl="Main/Myschedule/type" lookupGroup="chairman" size="56" maxlength="200"/></td>
				</tr>
				<tr>
					<td class="title">日程标题</td>
					<td><input type="text" name="t_Myschedule.title" class="required" value="${model.title}" data-rule="required" data-msg-required="必填" maxlength="150" style="width: 100%" /></td>
				</tr>
				<tr>
					<td class="title">日程内容</td>
					<td><textarea name="t_Myschedule.content" rows="7" style="width: 100%; overflow:auto">${model.content}</textarea></td>
				</tr>
				<tr>
					<td class="title">日程备注</td>
					<td><input type="text" name="t_Myschedule.reason" value="${model.reason}" maxlength="450" style="width: 100%" /></td>
				</tr>
			</table>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
		<li><a href="Main/Myschedule/delete/${model.id}-${model.u_id}" class="btn btn-red" data-toggle="doajax" data-icon="fa-trash-o"
				data-confirm-msg="您确定要删除该记录吗?">删除</a></li>
		<li><button type="submit" class="btn-default" data-icon="save">保存</button></li>
	</ul>
</div>