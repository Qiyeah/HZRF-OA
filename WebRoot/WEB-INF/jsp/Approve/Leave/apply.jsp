<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
    <div class="bjui-pageContent">
		<form id="approveForm" class="pageForm" action="Main/Leave/save" method="post" data-toggle="validate">      
			<input type="hidden" name="t_Leave_Approve.id" value="0" />
			<input type="hidden" name="t_Leave_Approve.u_id" id="userid" value="${u_id}" />
			<input type="hidden" name="t_Leave_Approve.d_id" value="${d_id}" />			
			<input type="hidden" name="t_Leave_Approve.days" id="days" value="0" />
			<input type="hidden" name="t_Leave_Approve.pid" id="pid" value="0" />
			<input type="hidden" name="t_Leave_Approve.pstatus" value="0" />
			<input type="hidden" name="flag" value="0" />
            <div style="width: 95%;height: 40px;text-align:center">
                <span style="font-size:25px; margin-left:10px"><B>请休假审批单</B></span>
            </div>			
			<table class="wordInfo" align="center" style="width: 95%; margin-bottom: 6px">
				<tr>
					<td width="15%" class="title">申 请 人</td>
					<td width="35%" align="center">${uname}</td>
					<td width="15%" class="title">所在科室</td>
					<td width="35%" align="center">${dname}</td>
				</tr>
				<tr>
					<td class="title">请休假类型</td>
					<td><select id="type" name="t_Leave_Approve.type" data-toggle="selectpicker" data-width="100%">
                            <option value="年休假" selected="selected">年休假</option>
                            <option value="事假">事假</option>
                            <option value="病假">病假</option>
                            <option value="婚丧假">婚丧假</option>
							<option value="产假">产假</option>
							<option value="探亲假">探亲假</option>
					</select></td>
					<td class="title">婚姻状态</td>
					<td align="center">${married}</td>
				</tr>
				<tr>
					<td class="title">申请时间</td>
					<td><input name="t_Leave_Approve.approvedate" type="text" value="${today}" class="required" size="27" data-toggle="datepicker" data-rule="required;date" data-msg-date="格式错误" data-msg-required="必填" maxlength="13"/></td>
                    <td class="title">
                    <a href="Main/Leave/showrule" data-toggle="dialog" data-mask="ture" data-id="showrule" data-title="请假说明"
					data-width="850" data-height="600"><span style="color: blue; font-family:'黑体'; font-size: 15px">请假说明</span></a></td>
                    <td align="center"><a href="Main/Leave/showhistory/${u_id}-${year}" data-toggle="dialog" data-mask="ture" data-id="showhistory" data-title="本年度已请假明细"
					data-width="900" data-height="400"><span style="font-size: 14px">本年度已请假明细</span></a>
                    </td>
                </tr>
				<tr>
					<td class="title">起止时间</td>
					<td><input name="t_Leave_Approve.begindate" id="begindate" type="text" size="12" class="required" data-toggle="datepicker" data-rule="开始日期:required;date" data-msg-date="格式错误" data-msg-required="必填" maxlength="13"/>
						 — <input name="t_Leave_Approve.enddate" id="enddate" type="text" size="12" class="required" data-toggle="datepicker" data-rule="结束日期:required; date;match[gte, t_Leave_Approve.begindate, date]" data-msg-date="格式错误" data-msg-required="必填" maxlength="13"/></td>
                    <td class="title">请休假天数</td>
                    <td><select id="leaveday" name="t_Leave_Approve.type" data-toggle="selectpicker" data-width="100">
                            <option value="2" selected="selected">多天</option>
                            <option value="1">一天</option>
                            <option value="0">半天</option>
                    </select> <input name="t_Leave_Approve.dayt" id="dayt" type="text" class="required" size="5" data-rule="required; number" data-msg-required="必填" maxlength="5"/>
                    <a class="btn btn-blue" id="countdays" href="javascript:countDays()">自动计算</a></td>
			    </tr>
				<tr id="annualVacationTr" style="display: none;">
					<td class="title">工作年数</td>
					<td align="center">${workyear}</td>
					<td class="title">年假情况</td>
					<td>${enableday} 天，已休 ${nxjs} 天，剩余 ${canday} 天</td>
				</tr>
				<tr>
					<td class="title" style="height:50px">请休假事由</td>
					<td colspan="3"><textarea name="t_Leave_Approve.reason" rows="3" class="required" style="width: 100%; overflow: auto" data-msg-required="必填" data-rule="required;length[~800]" placeholder="请根据实际情况填写"></textarea></td>
				</tr>
				<tr>
					<td class="title">附件列表</td>
					<td colspan="3">
						<div id="uploadFile" class="unitBox"><c:import url="../../Common/Attachment/add.jsp" /></div>
					</td>
				</tr>
				<tr>
					<td class="title" style="height: 80px">审核意见</td>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td class="title" style="height: 120px">审批意见</td>
					<td colspan="3">&nbsp;</td>
				</tr>
				<tr>
					<td class="title">销假时间</td>
					<td>&nbsp;</td>
					<td class="title">实际天数</td>
					<td>&nbsp;</td>
				</tr>
			</table>
			<input type="hidden" name="positionid" id="positionid" value="${positionid }" />
			<input type="hidden" name="t_Workflow.id" id="id" value="0" />
			<input type="hidden" name="t_Workflow.flowname" id="flowname" value="${wp.name}" />
			<input type="hidden" name="t_Workflow.workpath" value="${wp.path}" />
			<input type="hidden" name="t_Workflow.flowform" value="${wp.flow}" />
			<input type="hidden" name="t_Workflow.formname" value="请休假申请单" />
			<input type="hidden" name="t_Workflow.title" id="title" value="${wp.name}" />
			<input type="hidden" name="t_Workflow.starter" id="starter" value="${u_id}" />
			<input type="hidden" name="t_Workflow.startdept" id="startdept" value="${d_id}" />
			<input type="hidden" name="t_Workflow.starttime" value="" />
			<input type="hidden" name="t_Workflow.reader" value="" />
			<input type="hidden" name="t_Workflow.todoman" value="${u_id}" />
			<input type="hidden" name="t_Workflow.doneuser" value="" />
			<input type="hidden" name="t_Workflow.todousers" value="" />
			<input type="hidden" name="t_Workflow.isopen" value="1" />
			<input type="hidden" name="t_Workflow.editor" value="${u_id}" />
			<input type="hidden" name="t_Workflow.mainflowid" value="" />
			<input type="hidden" name="t_Workflow.subflowid" value="" />
			<input type="hidden" name="t_Workflow.subflowname" value="${wa.subflow}" />
			<input type="hidden" name="t_Workflow.isend" value="0" />
			<input type="hidden" name="t_Workflow.isnormalend" value="0" />
			<input type="hidden" name="t_Workflow.ishold" value="0" />
			<input type="hidden" name="t_Workflow.islock" value="0" />
			<input type="hidden" name="t_Workflow.isnewdoc" value="1" />
			<input type="hidden" name="t_Workflow.itemid" id="itemid" value="${itemid}" />
			<input type="hidden" name="t_Workflow.hastemplate" value="${wp.model}" />
			<input type="hidden" name="t_Workflow.templatename" value="${wp.template}" />
			<input type="hidden" name="t_Workflow.worddocname" value="" />
			<input type="hidden" name="t_Workflow.bodyiscreated" value="0" />
			<input type="hidden" name="t_Workflow.bodyauthor" value="" />
			<input type="hidden" name="t_Workflow.bodyversion" value="" />			
			<input type="hidden" name="todomannum" value="${todomannum}" />
			<input type="hidden" name="issequence" value="0" />
			<input type="hidden" name="havesubflow" value="${wa.havesubflow}" />
			<input type="hidden" name="editword" value="${wa.editword}" />
			<input type="hidden" name="viewword" value="${wa.viewword}" />
			<input type="hidden" name="endprocess" value="${wa.endprocess}" />
			<input type="hidden" name="specialsend" value="${wa.specialsend}" />
			<input type="hidden" name="backlaststep" value="${wa.backlaststep}" />
			<input type="hidden" name="backfirststep" value="${wa.backfirststep}" />
			<input type="hidden" name="haveopinionfield" id="haveopinionfield" value="${wa.haveopinionfield}" />
			<input type="hidden" name="opinionfield" id="opinionfield" value="${wa.opinionfield}" />
			<input type="hidden" name="opinion" id="opinion" value="" />
			<input type="hidden" name="opiniontime" id="opiniontime" value="" />
			<input type="hidden" name="operation" id="operation" value="" />
			<input type="hidden" name="wid" id="wid" value="${wp.id}" />
			<input type="hidden" name="nexttodoman" id="nexttodoman" value="" />
			<input type="hidden" name="nextitemid" id="nextitemid" value="${nextitemid}" />
			<input type="hidden" name="nextstepsnum" id="nextstepsnum" value="${nextstepsnum}" />
			<input type="hidden" name="sendsms" id="sendsms" value="0" />
			<input type="hidden" name="sendemail" id="sendemail" value="0" />
			<input type="hidden" name="curuser" id="curuser" value="${u_id}" />
			<input type="hidden" name="curdept" id="curdept" value="${d_id}" />
			<input type="hidden" name="localarea" value="${localarea}" />
			<input type="hidden" name="seldept" id="seldept" value="${d_id}" />
			<input type="hidden" name="curuser1" id="curuser1" value="${uname}" />
	
	</form>
	<c:if test="${errmsg != null }">
		<script type="text/javascript">
			$(this).alertmsg('error','${errmsg}');
			$(this).dialog("closeCurrent");
		</script>
	</c:if>	
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn btn-close" data-icon="close">关闭</button></li>
		<c:if test="${wa.specialsend == '1'}">
			<li><a class="btn btn-default" data-icon="fa-plane" onclick="TeSong()">特 送 </a></li>
		</c:if>
		<li><a class="btn btn-default" data-icon="save" onclick="ZanCun()">暂存</a></li>
		<li><a class="btn btn-default" data-icon="save" onclick="BaoCunTuiChu()">保存</a></li>
		<li><a class="btn btn-blue" data-icon="check" onclick="FaSong()">提交</a></li>
	</ul>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		//年假行的处理
		handleVacationTypeTr();
		$("#type").change(function() {
			handleVacationTypeTr();
		});
		$("#leaveday").change(function() {
			var ld = $("#leaveday").val();
			if (ld == 2) {
				$("#dayt").show();
                $('#countdays').show();
				$("#enddate").attr("disabled", false);
			} else if (ld == 1) {
				$("#dayt").hide();		
                $('#countdays').hide();
				$("#dayt").val("1");
				$("#enddate").attr("disabled", true);
			} else {
				$("#dayt").hide();
                $('#countdays').hide();
				$("#dayt").val("0.5");
				$("#enddate").attr("disabled", true);
			}			
        });
	});

	function hasFill() {
		var days = $("#days").val();
		if (eval(days) < 0) {
			alert("对不起，请假天数不能为负数！");
			return false;
		}
		if ($("reason").val() == "") {
			alert("对不起，请您输入请假理由！");
			return false;
		}
		return true;
	}

	function handleVacationTypeTr() {
		var vacationType = $("#type  option:selected").val();
		if (vacationType == "年休假") {
			$("#annualVacationTr").show();
		} else {
			$("#annualVacationTr").hide();
		}
	}	

	function countDays() {
		var sdate = $("#begindate").val();
		var edate = $("#enddate").val();
		var listday = listDayBetween(sdate, edate);
		$.post("http://www.easybots.cn/api/holiday.php?d=" + listday, function(data) {
			//alert(data);
			var json = $.parseJSON(data);
			var arrayDay = listday.split(",");
			var i = 0;
			for (d in arrayDay) {
				if (json[arrayDay[d]] == 0)
					i++;
			}
			$("#dayt").val(i);
		});
	}

	/**
	 * 转换日期字符串转换成日期格式
	 */
	function getDate(datestr) {
		var temp = datestr.split("-");
		var date = new Date(temp[0], eval(temp[1]) - 1, temp[2]);
		return date;
	}

	function listDayBetween(start, end) {
		var startTime = getDate(start);
		var endTime = getDate(end);
		var result = "";
		while ((endTime.getTime() - startTime.getTime()) >= 0) {
			var year = startTime.getFullYear();
			var month = startTime.getMonth() < 9 ? "0" + (startTime.getMonth() + 1).toString() : startTime.getMonth() + 1;
			var day = startTime.getDate() < 10 ? "0" + startTime.getDate() : startTime.getDate();
			result = result + "," + (year.toString() + month.toString() + day.toString());
			startTime.setDate(startTime.getDate() + 1);
		}
		if (result.length > 0) {
			result = result.substring(1);
		}
		return result;
	}
</script>