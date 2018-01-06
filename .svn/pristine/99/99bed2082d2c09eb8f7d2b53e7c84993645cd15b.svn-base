<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<c:set var="deparmentList" value="${deparmentList}" />
<c:set var="userList" value="${userList}" />
<script type="text/javascript">
	$("input[name=selectwas]").click(function() {
		var itemid = $("input[name='selectwas']:checked").val();
		$("#nextitemid").val(itemid);
		$("#users").ajaxUrl({
			url : "Main/Workflow/selectman2/" + itemid + "-${cond}"+"-${pid}",
			loadingmask : true
		})
	   
	});

	function onCheck(event, treeId, treeNode) {
		var treeObj = $.fn.zTree.getZTreeObj("user-tree"), nodes = treeObj.getCheckedNodes(true), result = "", tmpnum = 0;
		for (var i = 0; i < nodes.length; i++) {
			if (nodes[i].id == 10000) {
				result += ";" + nodes[i].value;
				tmpnum = tmpnum + 1;
			}
		}
		$("#result").val(result);
		$("#tmpnum").val(tmpnum);
	}

	function send() {
		var tmpstr = $("#result").val();
		var tmpnum = $("#tmpnum").val();
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
		$(this).dialog("close", "selna");
		//注掉后综合科科长那一步可以正常提示和刷新
		//window.parent.BJUI.dialog("close", "approve_dialog"); 
	}
</script>
<div class="bjui-pageContent">
	<form>
		<input type="hidden" name="cond" id="cond" value="${cond}" />
		<input type="hidden" name="pid" id="pid" value="${pid}" />
		
		<div style="float: left; display: block; margin: 5px; overflow: auto; width: 250px; height: 90%;">
			<table class="wordInfo" align="center" style="width: 100%; height: 320px; border: solid 1px #4CA5D8;">
				<tr>
					<td class="title">选择下一环节</td>
				</tr>
				<tr>
					<td>
						<div style="width: 100%; height: 280px; overflow: auto">
							<c:if test="${! empty was}">
								<c:forEach items="${was}" var="wa" varStatus="vs">									
									<span style="line-height:30px">&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" data-toggle="icheck" name="selectwas" value="${wa.id }" />&nbsp;${wa.name }</span><br />
								</c:forEach>
							</c:if>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div style="float: left; margin-top: 150px;">
			<img src="images/arrow_left.gif" border="0" />
		</div>
		<div style="float: left; display: block; margin: 5px; overflow: hidden; width: 285px; height: 90%;">
			<table class="wordInfo" align="center" style="width: 100%; height: 320px; border: solid 1px #4CA5D8;">
				<tr>
					<td class="title">选择处理人<input type="hidden" id="result"><input type="hidden" id="tmpnum"></td>
				</tr>
				<tr>
					<td>
						<div id="users" style="width: 100%; height: 280px; overflow: auto"></div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn btn-close" data-icon="remove" class="close">取消</button></li>
		<c:if test="${! empty was}">
			<li><button type="button" class="btn btn-green" data-icon="save" onclick="send()">确认</button></li>
		</c:if>
	</ul>
</div>
<script type="text/javascript">
//$("input:radio:first").attr("checked", "checked");
selectman99();
</script>