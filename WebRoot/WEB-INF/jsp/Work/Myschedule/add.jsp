<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
    $('input[name="doc-check-t"]').on('ifChanged', function(e) {
        var checked = $(this).is(':checked'), val = $(this).val();
        $("#ta1").val("");
        $("#ta2").val("");
        $("#ta3").val("");
        $("#ta4").val("");
        if (checked){
        $("#typeid").css("display", "");
        $("#typeid_s").css("display", "none");
        
        $("#remind_1").css("display", "none");
        $("#remind_2").css("display", "");
        
        $("#doc-check-t1").val("1");
        }else{
         $("#typeid").css("display", "none");
         $("#typeid_s").css("display", "");
         
         $("#remind_1").css("display", "");
         $("#remind_2").css("display", "none");
         
         $("#doc-check-t1").val("");
        }
    });
    
    $('input[name="t_Myschedule.scope"]').on('ifChanged', function(e) {
        var checked = $(this).is(':checked'), val = $(this).val();
       	$("#hiddenid").val("");
       	$("#useid").val("");
        if (checked){
        	if(val == 1){
        	 $("#openid").css("display", "");
        	}else {
        	$("#openid").css("display", "none");
        	}
        }
    });
</script>
<div class="bjui-pageContent">
		<form class="pageForm" action="Main/Myschedule/add" method="post" data-toggle="validate">
		<input type="hidden" name="t_Myschedule.u_id" value="${u_id}" />
			<table class="wordInfo" align="center" style="width: 98%;">
				<tr>
					<td class="title">编辑人</td>
					<td>${name}</td>
				</tr>
				<tr>
					<td class="title">全天事件</td>
					<td><input type="checkbox" name="doc-check-t" id="doc-check-t1" value="" data-toggle="icheck" /></td>
				</tr>
				<tr>
					<td class="title">提醒</td>
					<td id="remind_1"><select name="t_Myschedule.remind" data-toggle="selectpicker" data-width="100%">
							<option value="">不提醒</option>
						<optgroup label="提醒">
						<option value="1">5分钟前</option>
						<option value="2">10分钟前</option>
						<option value="3">30分钟前</option>
						<option value="4">1小时钟前</option>	
						<option value="5">2小时钟前</option>	
						</optgroup>
					</select>
					
					<td id="remind_2" style="display: none">
					<select name="rem" data-toggle="selectpicker" data-width="100%">
							<option value="">不提醒</option>
						<optgroup label="提醒">
						<option value="6">1天前</option>		
						</optgroup>
					</select></td>
				</tr>
				<tr>
					<td width="25%" class="title">日程时间</td>
					<td width="75%" id="typeid_s"><input type="text" name="t_Myschedule.wdate" id="ta1" value="${sdate}" data-toggle="datepicker" data-pattern="yyyy-MM-dd HH:mm:ss" data-rule="datetime" maxlength="25" size="26" class="required"/> 至
									<input type="text" name="t_Myschedule.edate" id="ta2" value="${edate}" data-toggle="datepicker" data-pattern="yyyy-MM-dd HH:mm:ss"  data-rule="datetime" maxlength="25" size="27" class="required"/>
                    
                    <td width="75%" id="typeid" style="display:none"><input type="text" name="t_Myschedule.event" id="ta3" value="" data-toggle="datepicker"  data-rule="date" maxlength="13" size="26" class="required"/> 至
									<input type="text" name="t_Myschedule.event1" id="ta4" value="" data-toggle="datepicker" data-rule="date" maxlength="13" size="27" class="required"/> 
                    </td>
				</tr>
				<tr>
			<td class="title">公开范围</td>
			<td><label><input type="radio" name="t_Myschedule.scope" value="3" data-toggle="icheck" checked/>私人</label>
				<label><input type="radio" name="t_Myschedule.scope" value="0" data-toggle="icheck"/>全部人员</label>
		 		<label><input type="radio" name="t_Myschedule.scope" value="1" data-toggle="icheck"/>指定人员</label></td>
			</tr>
			<tr style="display:none" id="openid">
				<td  class="title">公开人员</td>
				<td><input name="userId" type="hidden" id="hiddenid" /><input type="text" readonly="readonly" name="userName" id="useid" class="required"
					size="56" lookupGroup="param" data-title="选择公开人员" data-toggle="lookup" data-url="Main/Mail/getUsers" data-width="600" data-height="550" value="${leader_Schedule.receiver}" /></td>
			</tr>
				<tr>
					<td class="title">日程类型</td>
					<td><input name="chairman.type" type="text" suggestFields="type" suggestUrl="Main/Myschedule/type" lookupGroup="chairman" size="56" maxlength="200"/></td>
				</tr>
				<tr>
					<td class="title">日程标题</td>
					<td><input type="text" name="t_Myschedule.title" class="required" data-rule="required" data-msg-required="必填" maxlength="150" style="width: 100%" /></td>
				</tr>
				<tr>
					<td class="title">日程内容</td>
					<td><textarea name="t_Myschedule.content" rows="7" style="width: 100%; overflow:auto"></textarea></td>
				</tr>
				<tr>
					<td class="title">日程备注</td>
					<td><input type="text" name="t_Myschedule.reason" maxlength="450" style="width: 100%" /></td>
				</tr>
			</table>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li>
			<button type="button" class="btn-close" data-icon="close">关闭</button></li>
		<li>
			<button type="submit" class="btn-default" data-icon="save">保存</button></li>
	</ul>
</div>