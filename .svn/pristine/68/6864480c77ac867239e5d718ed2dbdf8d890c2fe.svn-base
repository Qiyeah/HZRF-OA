<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
<!--
	$("input[name='t_Wactive.havecondfield']").on('ifChanged', function(e) {
		if ($("input[name='t_Wactive.havecondfield']:checked").val() == "0") {
			$("#condfield").hide();
			$("#cond1").hide();
			$("#cond2").hide();
			$("#cond3").hide();
			$("#nextstep").show();
		} else {
			$("#condfield").show();
			$("#cond1").show();
			$("#cond2").show();
			$("#cond3").show();
			$("#nextstep").hide();
		}
	});
	
	$("input[name='t_Wactive.amount']").change(function() {
		if ($("input[name='t_Wactive.amount']:checked").val() != "2") {
			$("#zxfs").hide();
		} else {
			$("#zxfs").show();
		}
	});
		
	$("input[name='t_Wactive.participant']").on('ifChanged', function(e) {
		if ($("input[name='t_Wactive.participant']:checked").val() == "0") {
            $("#seldept").show();
            $("#selrole").show();
            $("#seluser").hide();
            $("#p3").parent().removeClass("checked");
            $("#p4").parent().removeClass("checked");
            $("#p2").parent().addClass("checked");
            $("#p1").parent().removeClass("disabled");
            $("#p2").parent().removeClass("disabled");
            $("#p5").parent().removeClass("disabled");
            $("#p3").parent().addClass("disabled");
            $("#p4").parent().addClass("disabled");
            $("#p1").attr("disabled", false);
            $("#p2").attr("disabled", false);
            $("#p5").attr("disabled", false);
            $("#p3").attr("disabled", true);
            $("#p4").attr("disabled", true);
            
        } else {
            $("#seluser").show();
            $("#seldept").hide();
            $("#selrole").hide();
            $("#p1").parent().removeClass("checked");
            $("#p2").parent().removeClass("checked");
            $("#p5").parent().removeClass("checked");
            $("#p3").parent().addClass("checked");
            $("#p1").parent().addClass("disabled");
            $("#p2").parent().addClass("disabled");
            $("#p5").parent().addClass("disabled");
            $("#p1").attr("disabled", true);
            $("#p2").attr("disabled", true);
            $("#p5").attr("disabled", true);
            //$(".iradio_minimal-purple").removeClass("disabled");
            $("#p3").parent().removeClass("disabled");
            $("#p4").parent().removeClass("disabled");
            $("#p3").attr("disabled", false);
            $("#p4").attr("disabled", false);
        }
	});
		
	$("input[name='t_Wactive.ptype']").on('ifChanged', function(e) {
		switch ($("input[name='t_Wactive.ptype']:checked").val()) {
		case "1":
			$("#seldept").show();
			$("#selrole").hide();
			$("#seluser").hide();
			break;
		case "2":
			$("#seldept").show();
			$("#selrole").show();
			$("#seluser").hide();
			break;
		case "3":
			$("#seldept").hide();
			$("#selrole").hide();
			$("#seluser").show();
			break;
		case "4":
			$("#seldept").hide();
			$("#selrole").hide();
			$("#seluser").hide();
			break;
		case "5":
			$("#seldept").show();
			$("#selrole").hide();
			$("#seluser").hide();
			break;
		default:
			break;
		}
	});
		
	$("input[name='t_Wactive.haveopinionfield']").on('ifChanged', function(e) {
		if ($("input[name='t_Wactive.haveopinionfield']checked").val() == '0') {
			$("#opinion").hide();
		} else {
			$("#opinion").show();
		}
	});
	
	$("input[name='t_Wactive.havesubflow']").on('ifChanged', function(e) {
		if ($("input[name='t_Wactive.havesubflow']:checked").val() == '0') {
			$("#subflow").hide();
			$("#subflow1").hide();
		} else {
			$("#subflow").show();
			$("#subflow1").show();
		}
	});
//-->
</script>
<div class="bjui-pageContent">
	<form method="post" action="Main/Workflow/activeadd" class="pageForm" data-toggle="validate">
	<input type="hidden" name="t_Wactive.wid" value="${wid}">
		<ul class="nav nav-tabs" role="tablist">
            <li class="active"><a href="#base_config" role="tab" data-toggle="tab">基本配置</a></li>
            <li><a href="#people_config" role="tab" data-toggle="tab">执行人配置</a></li>
            <li><a href="#rights_config" role="tab" data-toggle="tab">权限配置</a></li>
            <li><a href="#subprocess_config" role="tab" data-toggle="tab">子流程配置</a></li>
        </ul>
        <div class="tab-content">
        
        	 <div class="tab-pane fade active in" id="base_config">
        	 	<table class="table table-condensed table-hover">
					<tr>
						<td>
							<label class="control-label x110">环节名称:</label>
							<input name="t_Wactive.name" type="text" class="required" data-rule="required" size="45"/>
						</td>
					</tr>
					<tr>
						<td>
							<label class="control-label x110">环节序号:</label>
							<input name="t_Wactive.num" type="text" class="required" data-rule="required number" size="45"/>
						</td>
					</tr>
					<tr>
						<td>
							<label class="control-label x110">环节类型:</label>
							<input type="radio" name="t_Wactive.atype" value="1" <c:if test="${tf=='3'||tf=='1'}">disabled</c:if> data-toggle="icheck" data-label="开始"> 
							<input type="radio" name="t_Wactive.atype" value="2" checked data-toggle="icheck" data-label="中间">
							<input type="radio" name="t_Wactive.atype" value="3" <c:if test="${tf=='3'||tf=='2'}">disabled</c:if> data-toggle="icheck" data-label="结束">
						</td>
					</tr>
					<tr>
						<td>
							<label class="control-label x110">发送短信提醒:</label>
							<input type="radio" name="t_Wactive.sendsms" value="1" data-toggle="icheck" data-label="是"> 
							<input type="radio" name="t_Wactive.sendsms" value="0" checked data-toggle="icheck" data-label="否">
						</td>
					</tr>
					<tr>
						<td>
							<label class="control-label x110">发送邮件提醒:</label>
							<input type="radio" name="t_Wactive.sendemail" value="1" data-toggle="icheck" data-label="是">
							<input type="radio" name="t_Wactive.sendemail" value="0" checked data-toggle="icheck" data-label="否">
						</td>
					</tr>
					<tr>
						<td>
							<label class="control-label x110">启用条件域:</label>
							<input type="radio" name="t_Wactive.havecondfield" value="1" data-toggle="icheck" data-label="是"> 
							<input type="radio" name="t_Wactive.havecondfield" value="0" data-toggle="icheck" checked data-label="否">
						</td>
					</tr>
					<tr id="nextstep">
						<td>
							<label class="control-label x110">下一环节:</label>
							<select name="nextactives" multiple="multiple" data-toggle="selectpicker" data-width="450">
								<c:if test="${! empty actives}">
									<c:forEach items="${actives}" var="ac">
										<option value="${ac.id}">${ac.name}</option>
									</c:forEach>
								</c:if>
							</select>
						</td>
					</tr>
					<tr id="condfield" style="display: none">
						<td>
							<label class="control-label x110">条件域:</label>
							<select name="t_Wactive.condfield" data-toggle="selectpicker">
								<option value=""></option>
								<c:if test="${! empty conds}">
									<c:forEach items="${conds}" var="cf">
										<option value="${cf.name}">${cf.description}</option>
									</c:forEach>
								</c:if>
							</select>
						</td>
					</tr>
					<tr id="cond1" style="display: none">
						<td>
							<label class="control-label x110">条件一:</label>
							<input name="condition1" type="text">
							<label class="control-label x110">流转到：</label>
							<select name="ato1" data-toggle="selectpicker">
								<option value=""></option>
								<c:if test="${! empty actives}">
									<c:forEach items="${actives}" var="ac">
										<option value="${ac.id}">${ac.name}</option>
									</c:forEach>
								</c:if>
							</select>
						</td>					
					</tr>
					<tr id="cond2" style="display: none">
						<td>
							<label class="control-label x110">条件二:</label>
							<input name="condition2" type="text">
							<label class="control-label x110">流转到：</label>
							<select name="ato2" data-toggle="selectpicker">
								<option value=""></option>
								<c:if test="${! empty actives}">
									<c:forEach items="${actives}" var="ac">
										<option value="${ac.id}">${ac.name}</option>
									</c:forEach>
								</c:if>
							</select>
						</td>					
					</tr>
					<tr id="cond3" style="display: none">
						<td>
							<label class="control-label x110">条件三:</label>
							<input name="condition3" type="text">
							<label class="control-label x110">流转到：</label>
							<select name="ato3" data-toggle="selectpicker">
								<option value=""></option>
								<c:if test="${! empty actives}">
									<c:forEach items="${actives}" var="ac">
										<option value="${ac.id}">${ac.name}</option>
									</c:forEach>
								</c:if>
							</select>
						</td>					
					</tr>
				</table>
        	 </div>
        	 
        	 <div class="tab-pane fade" id="people_config">
				<table class="table table-condensed table-hover">
					<tr>
						<td>
							<label class="control-label x110">执行人数:</label>
							<input type="radio" name="t_Wactive.amount" value="1" checked data-toggle="icheck" data-label="单人">
						 	<input type="radio" name="t_Wactive.amount" value="2" data-toggle="icheck" data-label="多人">
						 	<input type="radio" name="t_Wactive.amount" value="3" data-toggle="icheck" data-label="多选一">
						 </td>
					</tr>
					<tr id="zxfs" style="display: none">
						<td>
							<label class="control-label x110">执行方式:</label>
							<input type="radio" name="t_Wactive.issequence" value="1" checked data-toggle="icheck" data-label="顺序执行"> 
							<input type="radio" name="t_Wactive.issequence" value="0" data-toggle="icheck" data-label="同时执行">
						</td>
					</tr>
					<tr>
						<td>
							<label class="control-label x110">执行人:</label>
							<input type="radio" name="t_Wactive.participant" value="0" checked data-toggle="icheck" data-label="用户选择 ">
							<input type="radio" name="t_Wactive.participant" value="1" data-toggle="icheck" data-label="指定用户">
						</td>
					</tr>
					<tr>
						<td>
							<label class="control-label x110">选择方式:</label>
							<input id="p2" type="radio" name="t_Wactive.ptype" value="2" checked="checked" data-toggle="icheck" data-label="指定职位"/>
							<input id="p1" type="radio" name="t_Wactive.ptype" value="1" data-toggle="icheck" data-label="指定部门"/>
							<input id="p5" type="radio" name="t_Wactive.ptype" value="5" data-toggle="icheck" data-label="指定部门(全部)"/>
							<input id="p3" type="radio" name="t_Wactive.ptype" value="3" disabled="disabled" data-toggle="icheck" data-label="用户定义"/>
							<input id="p4" type="radio" name="t_Wactive.ptype" value="4" disabled="disabled" data-toggle="icheck" data-label="初始处理人"/>
						</td>
					</tr>
					<tr id="seldept">
						<td>
							<label class="control-label x110">指定部门:</label>
							<select name="dept" data-toggle="selectpicker">
								<option value="0">所有部门</option>
								<option value="curdept">当前部门</option>
								<option value="startdept">发起部门</option>
								<option value="updept">上级部门</option>
								<option value="selectdept">选择部门</option>
								<option value="leader">分管领导</option>
								<c:if test="${! empty depts}">
									<c:forEach items="${depts}" var="dept">
										<option value="${dept.id}">${dept.fname}</option>
									</c:forEach>
								</c:if>
							</select>
						</td>
					</tr>
					<tr id="selrole">
						<td>
							<label class="control-label x110">指定职位:</label>
							<select name="position" data-toggle="selectpicker" multiple="multiple" data-rule="required">
								<c:if test="${! empty positions}">
									<c:forEach items="${positions}" var="position">
										<option value="${position.id}">${position.name}</option>
									</c:forEach>
								</c:if>
							</select>
						</td>
					</tr>
					<tr id="seluser" style="display: none;">
						<td>
							<label class="control-label x110">指定用户:</label>
							<input name="param.userId" type="hidden" />
							<input name="param.userName" type="text" readonly data-toggle="lookup" data-url="Main/Workflow/getUser" data-group="param" data-width="600" data-height="550" size="40"/>
						</td>
					</tr>
					<tr>
						<td>
							<label class="control-label x110">默认处理人:</label>
							<input name="default.userId" type="hidden" /> 
							<input name="default.userName" type="text" readonly data-toggle="lookup" data-url="Main/Workflow/getUser" data-group="default" data-width="600" data-height="550" size="40"/>
						</td>
					</tr>
				</table>
        	 </div>
        	 
        	 <div class="tab-pane fade" id="rights_config">
        	 	<table class="table table-condensed table-hover">
					<tr>
						<td>
							<label class="control-label x110">可以编辑表单:</label>
							<input type="radio" name="t_Wactive.editable" value="1" data-toggle="icheck" data-label="是"> 
							<input type="radio" name="t_Wactive.editable" value="0" checked data-toggle="icheck" data-label="否">
						</td>
					</tr>
					<tr>
						<td>
							<label class="control-label x110">可以查看正文:</label>
							<input type="radio" name="t_Wactive.viewword" value="1" data-toggle="icheck" data-label="是">
						 	<input type="radio" name="t_Wactive.viewword" value="0" checked data-toggle="icheck" data-label="否">
						 </td>
					</tr>
					<tr>
						<td>
							<label class="control-label x110">可以编辑正文:</label>
							<input type="radio" name="t_Wactive.editword" value="1" data-toggle="icheck" data-label="是">
					 		<input type="radio" name="t_Wactive.editword" value="0" checked data-toggle="icheck" data-label="否">
					 	</td>
					</tr>
					<tr>
						<td>
							<label class="control-label x110">可以表单盖章:</label>
							<input type="radio" name="t_Wactive.sealword" value="1" data-toggle="icheck" data-label="是">
							<input type="radio" name="t_Wactive.sealword" value="0" checked data-toggle="icheck" data-label="否">
						</td>
					</tr>
					<tr>
						<td>
							<label class="control-label x110">可以打印表单:</label>
							<input type="radio" name="t_Wactive.canprint" value="1" data-toggle="icheck" data-label="是">
							<input type="radio" name="t_Wactive.canprint" value="0" checked data-toggle="icheck" data-label="否">
						</td>
					</tr>
					<tr>
						<td>
							<label class="control-label x110">可以签名表单:</label>
							<input type="radio" name="t_Wactive.signname" value="1" data-toggle="icheck" data-label="是"> 
							<input type="radio" name="t_Wactive.signname" value="0" checked data-toggle="icheck" data-label="否">
						</td>
					</tr>
					<tr>
						<td>
							<label class="control-label x110">可以填写意见:</label>
							<input type="radio" name="t_Wactive.haveopinionfield" value="1" data-toggle="icheck" data-label="是">
							<input type="radio" name="t_Wactive.haveopinionfield" value="0" checked data-toggle="icheck" data-label="否">
						</td>
					</tr>
					<%-- <tr id="opinion" style="display: none">
						<td>
							<label class="control-label x110">意见域:</label>
							<select name="t_Wactive.opinionfield" data-toggle="selectpicker">
								<c:if test="${! empty fields}">
									<c:forEach items="${fields}" var="field">
										<option value="${field.name}">${field.description}</option>
									</c:forEach>
								</c:if>
							</select>
						</td>
					</tr> --%>
					<tr>
						<td>
							<label class="control-label x110">可以传阅会签:</label>
							<input type="radio" name="t_Wactive.handround" value="1" data-toggle="icheck" data-label="是">
							<input type="radio" name="t_Wactive.handround" value="0" checked data-toggle="icheck" data-label="否">
						</td>
					</tr>
					<tr>
						<td>
							<label class="control-label x110">可以终止流程:</label>
							<input type="radio" name="t_Wactive.endprocess" value="1" data-toggle="icheck" data-label="是">
							<input type="radio" name="t_Wactive.endprocess" value="0" checked data-toggle="icheck" data-label="否">
						</td>
					</tr>
					<tr>
						<td>
							<label class="control-label x110">可以退回上一步:</label>
							<input type="radio" name="t_Wactive.backlaststep" value="1" data-toggle="icheck" data-label="是">
							<input type="radio" name="t_Wactive.backlaststep" value="0" checked data-toggle="icheck" data-label="否">
						</td>
					</tr>
					<tr>
						<td>
							<label class="control-label x110">可以退出发起人:</label>
							<input type="radio" name="t_Wactive.backfirststep" value="1" data-toggle="icheck" data-label="是">
							<input type="radio" name="t_Wactive.backfirststep" value="0" checked data-toggle="icheck" data-label="否">
						</td>
					</tr>
					<tr>
						<td>
							<label class="control-label x110">可以特送:</label>
							<input type="radio" name="t_Wactive.specialsend" value="1" data-toggle="icheck" data-label="是">
							<input type="radio" name="t_Wactive.specialsend" value="0" checked data-toggle="icheck" data-label="否">
						</td>
					</tr>
				</table>
        	 </div>
        	 
        	 <div class="tab-pane fade" id="subprocess_config">
        	 	<table class="table table-condensed table-hover">
					<tr>
						<td>
							<label class="control-label x110">可以启动子流程:</label>
							<input type="radio" name="t_Wactive.havesubflow" value="1" data-toggle="icheck" data-label="是">
						 	<input type="radio" name="t_Wactive.havesubflow" value="0" checked data-toggle="icheck" data-label="否">
						</td>
					</tr>
					<tr id="subflow" style="display: none">
						<td>
							<label class="control-label x110">子流程名称:</label>
							<select name="t_Wactive.subflow" data-toggle="selectpicker">
								<c:if test="${! empty process}">
									<c:forEach items="${process}" var="pc">
										<option value="${pc.id}">${pc.name}</option>
									</c:forEach>
								</c:if>
							</select>
						</td>
					</tr>
					<tr id="subflow1" style="display: none">
						<td>
							<label class="control-label x110">执行方式:</label>
							<input type="radio" name="t_Wactive.ishold" value="0" data-toggle="icheck" data-label="并发执行 ">
							<input type="radio" name="t_Wactive.ishold" value="1" checked data-toggle="icheck" data-label="顺序执行">
						</td>
					</tr>
				</table>
        	 </div>
        </div>
	</form>
</div>
<%@ include file="/include/operation_footer.jsp"%>