<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	 function donoattend() {
	    var reason = document.getElementById("content").value;
	    if(reason == ""){
	       alert("必须输入理由");
	    }else{
	       /*  var url = "/Main/Meeting/noattend/" + "${pid}" + "-"+"${uid}" + "-"+ reason;
	       $.post(encodeURI(url)); */
		   $(this).dialog("closeCurrent");
	       $(this).dialog('close',"approve");
		   $("#reason1").submit();
	    }
	    
	} 
	
</script>
<div class="bjui-pageContent">
	<form id="reason1" method="post" action="Main/Meeting/noattend" class="pageForm" data-toggle="validate">
		<input type="hidden" name="uid" id = "uid" value="${uid}" />
		<input type="hidden" name="pid" value="${pid}" />
		<div class="pageFormContent" layoutH="56">
			<table class="wordInfo" align="center" style="width: 95%;margin-bottom:23px">
				<tr>
					<td width="25%" class="title">不参加原因</td>
					<td width="75%">
					<textarea rows="5" id= "content" name="reason" class="required" style="width: 99%; overflow:auto"></textarea></td>
				</tr>
			</table>
		</div>
		
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><a class="btn btn-blue" data-icon="check" onclick="donoattend()"><span>提交</span></a></li>
		<li><button  class="btn btn-close" data-icon="remove">关闭</button></li>
	</ul>
</div>
