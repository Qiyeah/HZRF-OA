<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	function dealActive(pam) {
		var f = document.forms[0];
		var tn = new RegExp("^" + pam + ",", "i");
		var str = $("#a-" + pam).html();
		if (f.active != null) {
			if (f.active.length > 1) {
				for ( var i = 0; i < f.active.length; i++) {
					if (f.active[i].value.search(tn) != -1) {
						if (str == "－") {
							document.getElementById("tr-" + f.active[i].value).style.display = 'none';
						} else {
							document.getElementById("tr-" + f.active[i].value).style.display = '';
						}
					}
				}
			} else {
				if (f.active.value.search(tn) != -1) {
					if (str == "－") {
						document.getElementById("tr-" + f.active.value).style.display = 'none';
					} else {
						document.getElementById("tr-" + f.active.value).style.display = '';
					}
				}
			}
		}
		if (str == "－") {
			str = "＋";
		} else {
			str = "－";
		}
		$("#a-" + pam).html(str);
	}

	function dealAllAct() {
		var f = document.forms[0];
		var str = $("#a-allact").html();
		if (f.active != null) {
			if (f.active.length > 1) {
				for ( var i = 0; i < f.active.length; i++) {
					if (str == "－")
						document.getElementById("tr-" + f.active[i].value).style.display = 'none';
					else
						document.getElementById("tr-" + f.active[i].value).style.display = '';
				}
			} else {
				if (str == "－") {
					document.getElementById("tr-" + f.active.value).style.display = 'none';
				} else {
					document.getElementById("tr-" + f.active.value).style.display = '';
				}
			}
		}
		if (str == "－") {
			str = "＋";
		} else {
			str = "－";
		}
		$("#a-allact").html(str);
		if (f.process != null) {
			if (f.process.length > 1) {
				for ( var i = 0; i < f.process.length; i++)
					$("#a-" + f.process[i].value).html(str);
			} else
				$("#a-" + f.process.value).html(str);
		}
	}
</script>
<div class="bjui-pageHeader">
	<div class="bjui-searchBar">
		<div class="pull-right">
			<button type="button" class="btn-green" data-url="Main/Workflow/processaddip" data-toggle="dialog" data-id="addFlow" data-width="550" data-height="450" data-title="添加流程" data-mask="true">添加</button>
			<button type="button" class="btn-blue" data-url="Main/Workflow/processupdateip/{#bjui-selected}" data-toggle="dialog" data-mask="true" data-id="editFlow" data-title="编辑流程" data-width="550" data-height="450">编辑</button>
            <button type="button" class="btn-red" data-url="Main/Workflow/processdel/{#bjui-selected}" data-toggle="doajax" data-confirm-msg="删除此流程及其所有下属环节">删除</button>
		</div>
	</div>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-top">
		<thead>
			<tr>
				<th width="5%"><a id="a-allact" onclick="dealAllAct()">－</a></th>
				<th width="25%">流程名</th>
				<th width="35%">环节名</th>
				<th width="25%">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${! empty wplist}">
					<c:forEach items="${wplist}" var="wp">
						<tr data-id="${wp.id}" style="background-color: #f9f9f9;">
							<td align="center"><a id="a-${wp.id}" onclick="dealActive('${wp.id}')">－</a></td>
							<td>${wp.name}</td>
							<td><input type="hidden" name="process" value="${wp.id}" /></td>
							<td align="center">
								<a href="Main/Workflow/activeaddip/${wp.id}" class="btn btn-green" data-toggle="dialog" data-mask="true" data-id="addStep" data-title="新增下属环节" data-width="700" data-height="600">新增环节</a>
								<a href="Main/Workflow/fieldmain/${wp.id}"  class="btn btn-blue" data-toggle="dialog" data-mask="true" data-id="editfield" data-width="600" data-height="500" data-title="配置流程域">配置流程域</a>
							</td>
						</tr>
						<c:choose>
							<c:when test="${! empty walist}">
								<c:forEach items="${walist}" var="wa">
									<c:if test="${wa.wid==wp.id}">
										<tr rel="${wa.id}" id="tr-${wp.id},${wa.id}">
											<td></td>
											<td><input type="hidden" name="active" value="${wp.id},${wa.id}" /></td>
											<td>${wa.num}. ${wa.name} [<c:if test="${wa.atype=='1'}">开始</c:if> <c:if test="${wa.atype=='2'}">中间</c:if> <c:if test="${wa.atype=='3'}">结束</c:if>]
											</td>
											<td align="center">
												<a class="btn btn-blue" href="Main/Workflow/activeupdateip/${wa.id}" data-toggle="dialog" data-mask="true"  data-id="editactive" data-width="700" data-height="600" data-title="编辑环节">编辑</a>
												<a class="btn btn-red" href="Main/Workflow/activedel/${wa.id}" data-toggle="doajax" data-confirm-msg="确定要删除该条数据吗？">删除</a>
												<%-- <a class="btn btn-green" href="Main/Workflow/transmain/${wa.id}" data-toggle="dialog" data-mask="true" data-id="edittrans" data-width="600" data-height="500" data-title="配置流转特例">流转特例</a> --%>
											</td>
										</tr>
									</c:if>
								</c:forEach>
							</c:when>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr align="center">
						<td colspan="4" style="color: red;">无任何记录！</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>