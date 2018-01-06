<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	function send() {
		var sel = $("#select");
		if (sel == null) {
			alert("无下一处理人，请联系管理员！");
			return false;
		}
		var tmpstr = "";
		var tmpnum = 0;
		$("input[name='selectman']:checked").each(function() {
			tmpstr += ";" + $(this).val();
			tmpnum = tmpnum + 1;
		});
		if (tmpnum < 1) {
			alert("请选择下一环节执行人！");
			return false;
		} else if (tmpnum > 1 && $("#amount").val() == "1") {
			alert("下一环节只能选择一个执行人！");
			return false;
		}
		if (tmpstr.length > 0) {
			tmpstr = tmpstr.substring(1);
		}		
		$("#nexttodoman").val(tmpstr);
		$("#approveForm").submit();
		$(this).dialog("close","selna");
		//window.parent.BJUI.dialog("close","approve_dialog"); 
	}
	function sendall() {
		var sel = $("#selectman");
		if (sel == null) {
			alert("无下一处理人，请联系管理员！");
			return false;
		}
		var tmpstr = "";
		var tmpnum = 0;
		$("input[name='selectman']").each(function() {
			tmpstr += ";" + $(this).val();
			tmpnum = tmpnum + 1;
		});
		if (tmpstr.length > 0) {
			tmpstr = tmpstr.substring(1);
		}		
		/* $("#nexttodoman", window.parent.document).val(tmpstr);
		$("#approveForm", window.parent.document).submit(); */
		$("#nexttodoman").val(tmpstr);
		$("#approveForm").submit();
		$(this).dialog("close","selna");
		$(this).dialog("close","approve_dialog");
	}
</script>

<div class="bjui-pageContent">
	<input type="hidden" name="amount" id="amount" value="${amount }" />
	<input type="hidden" name="position" value="${position}" />
	<table class="table table-condensed table-hover">
		<tr>
			<td>
				<label>环节名称</label>
			</td>
			<td>${wa.name }</td>
		</tr>
		<c:if test="${! empty mans}">
			<c:forEach items="${mans}" var="man" varStatus="vs">
				<tr>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="select" name="selectman" value="${man.id }" <c:if test="${default1==man.id}">checked="checked"</c:if> data-toggle="icheck" data-label="${man.name }" />
					</td>
					<td>${man.name } <c:choose>
					       <c:when test="${man.pid==12 }">(科长)</c:when>
					       <c:when test="${man.pid==14 }">(站长)</c:when>
					       <c:when test="${man.pid==16 }">(中心主任)</c:when>
                      </c:choose></td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><a type="button" class="btn btn-close" data-icon="close">关闭</a></li>
        <li><a type="button" class="btn btn-default" data-icon="save" onclick="send()">提交</a></li>
    </ul>
</div>
<script type="text/javascript">
<c:choose>
<c:when test="${mannum == 1}">
	$("#select:eq(0)").attr("checked", true);
	send();
</c:when>
<c:when test="${amount == 3}">
	sendall();
</c:when>
<c:otherwise></c:otherwise>
</c:choose>
</script>

