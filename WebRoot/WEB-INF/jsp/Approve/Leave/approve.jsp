<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/JavaScript">
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
</script>
<div class="bjui-pageContent">
        <form id="approveForm" class="pagerForm" action="Main/Leave/save" method="post" data-toggle="validate">        
        <div class="pageFormContent">
			<input type="hidden" name="t_Leave_Approve.id" value="${la.id}" />
			<input type="hidden" name="t_Leave_Approve.u_id" id="userid" value="${la.u_id}" />
			<input type="hidden" name="t_Leave_Approve.d_id" value="${la.d_id}" />
			<input type="hidden" name="t_Leave_Approve.opinion1" id="opinion1" value="${la.opinion1}" />
			<input type="hidden" name="t_Leave_Approve.opinion2" id="opinion2" value="${la.opinion2}" />
			<input type="hidden" name="t_Leave_Approve.opinion3" id="opinion3" value="${la.opinion3}" />
			<input type="hidden" name="t_Leave_Approve.opinion4" id="opinion4" value="${la.opinion4}" />
			<input type="hidden" name="t_Leave_Approve.pid" id="pid" value="${la.pid}" />
			<input type="hidden" name="t_Leave_Approve.pstatus" value="${la.pstatus}" />
			<input type="hidden" name="flag" value="0" />
			
            <div style="width: 95%;height: 40px;text-align:center">
                <span style="font-size:25px; margin-left:10px"><B>请休假审批单</B></span>
            </div>
			<table class="wordInfo" align="center" style="width: 95%; margin-bottom: 6px">
				<tr>
					<td width="15%" class="title">申 请 人</td>
					<td width="35%" align="center">${starter}</td>
					<td width="15%" class="title">所在科室</td>
					<td width="35%" align="center">${startdept}</td>
				</tr>
				<tr>
                    <td class="title">请休假类型</td>
                    <td><select id="type" name="t_Leave_Approve.type" data-toggle="selectpicker" data-width="100%">
                            <option value="年休假" <c:if test="${la.type == '年休假'}">selected</c:if>>年休假</option>
                            <option value="事假" <c:if test="${la.type == '事假'}">selected</c:if>>事假</option>
                            <option value="病假" <c:if test="${la.type == '病假'}">selected</c:if>>病假</option>
                            <option value="婚丧假" <c:if test="${la.type == '婚丧假'}">selected</c:if>>婚丧假</option>
                            <option value="产假" <c:if test="${la.type == '产假'}">selected</c:if>>产假</option>
                            <option value="探亲假" <c:if test="${la.type == '探亲假'}">selected</c:if>>探亲假</option>
                        </select></td>
					<td class="title">婚姻状态</td>
					<td align="center">${married}</td>
				</tr>
				<tr>
					<td class="title">申请时间</td>
					<td><input name="t_Leave_Approve.approvedate" type="text" value="${la.approvedate}" class="required" size="27" data-toggle="datepicker" data-rule="required;date" data-msg-date="格式错误" data-msg-required="必填" maxlength="13"/></td>
                 	<td class="title"><a href="Main/Leave/showrule" data-toggle="dialog" data-mask="ture" data-id="showrule" data-title="请假说明"
					data-width="850" data-height="600"><span style="color: blue; font-family:'黑体'; font-size: 15px">请假说明</span></a></td>
                    <td align="center"><a href="Main/Leave/showhistory/${la.u_id}-${year}" data-toggle="dialog" data-mask="ture" data-id="showhistory" data-title="本年度已请假明细"
					data-width="900" data-height="400"><span style="font-size: 14px">本年度已请假明细</span></a>
                    </td>
                </tr>
				<tr>
					<td class="title">起止时间</td>
					<td><table>
							<tr>
								<td style="border: 0px"><input name="t_Leave_Approve.begindate" id="begindate" type="text" value="${la.begindate }" size="12" class="required" data-toggle="datepicker" data-rule="required;date" data-msg-date="格式错误" data-msg-required="必填" maxlength="13"/></td>
								<td style="border: 0px">—</td>
								<td style="border: 0px"><input	name="t_Leave_Approve.enddate" id="enddate" type="text" value="${la.enddate }" size="12" class="required" data-toggle="datepicker" data-rule="date" data-msg-date="格式错误" data-msg-required="必填" maxlength="13"/></td>
							</tr>
						</table></td>
	                <td class="title">请休假天数</td>
	                <td align="center"><input name="t_Leave_Approve.dayt" id="dayt" type="text" value="${la.dayt }" class="required" style="width: 98%; text-align: center" data-rule="required; number" data-msg-required="必填" maxlength="5"/></td>
                </tr>
                <tr id="annualVacationTr" style="display: none;">
                    <td class="title">工作年数</td>
                    <td align="center">${workyear}</td>
                    <td class="title">年假情况</td>
                    <td>${enableday} 天，已休 ${nxjs} 天，剩余 ${canday} 天</td>
                </tr>
				<tr>
					<td class="title" style="height:50px">请休假事由</td>
					<td colspan="3"><textarea name="t_Leave_Approve.reason" rows="3" class="required" style="width: 100%; overflow: auto" data-rule="required;length[~800]" data-msg-required="必填" placeholder="请根据实际情况填写">${la.reason }</textarea></td>
				</tr>
				<tr>
					<td class="title">附件列表</td>
					<td colspan="3">
						<div id="uploadFile" class="unitBox"><c:import url="../../Common/Attachment/edit.jsp" /></div>
					</td>
				</tr>
				<tr>
					<td class="title" style="height: 80px">审核意见</td>
					<td colspan="3">
                        <div id="opinion1_1" class="opinion">${opinion1 }</div>
                        <c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion1'}">
                            <br/><br/>
                            <a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;"><span>填写意见</span></a>
                            <c:if test="${wa.atype != '3'}">
				                <c:if test="${wa.havecondfield == '0'}">
				                    <a class="btn btn-blue" data-icon="check" onclick="FaSong()" style="margin-left: 5px;"><span>提交</span></a>
				                </c:if>
				                <c:if test="${wa.havecondfield == '1'}">
				                    <a class="btn btn-blue" data-icon="check" onclick="ShengCheng('${wa.condfield}')" style="margin-left: 5px;"><span>提交</span></a>
				                </c:if>
                            </c:if>
                        </c:if>
                    </td>
				</tr>
				<tr>
					<td class="title" style="height: 120px">审批意见</td>
					<td colspan="3">
                        <div id="opinion2_1" class="opinion">${opinion2 }</div>
                        <c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion2'}">
                            <br/><br/>
                            <a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;"><span>填写意见</span></a>
                            <c:if test="${wa.atype != '3'}">
                                <a class="btn btn-blue" data-icon="check" onclick="FaSong()" style="margin-left: 5px;"><span>提交</span></a>
                            </c:if>
                        </c:if>
                    </td>
				</tr>
				<c:if test="${wa.atype == '3'}">
				<tr>
					<td class="title" >销假时间</td>
					<td><input name="t_Leave_Approve.backdate" type="text" value="${la.backdate}" data-toggle="datepicker" data-rule="date" data-msg-date="格式错误" maxlength="13" style="width: 100%" /></td>
					<td class="title" >实际天数</td>
					<td><input name="t_Leave_Approve.days" id="days" type="text" value="${la.days}" data-rule="number" style="width: 100%" /></td>
				</tr>
				</c:if>
			</table>
			<input type="hidden" name="positionid" id="positionid" value="${positionid }" />
			<input type="hidden" name="t_Workflow.id" id="id" value="${wf.id}" />
			<input type="hidden" name="t_Workflow.flowname" id="flowname" value="${wf.flowname}" />
			<input type="hidden" name="t_Workflow.workpath" value="${wf.workpath}" />
			<input type="hidden" name="t_Workflow.flowform" value="${wf.flowform}" />
			<input type="hidden" name="t_Workflow.formname" value="${wf.formname}" />
			<input type="hidden" name="t_Workflow.title" id="title" value="${wf.title}" />
			<input type="hidden" name="t_Workflow.starter" id="starter" value="${wf.starter}" />
			<input type="hidden" name="t_Workflow.startdept" id="startdept" value="${wf.startdept}" />
			<input type="hidden" name="t_Workflow.starttime" value="${wf.starttime}" />
			<input type="hidden" name="t_Workflow.reader" value="${wf.reader}" />
			<input type="hidden" name="t_Workflow.todoman" value="${wf.todoman}" />
			<input type="hidden" name="t_Workflow.doneuser" value="${wf.doneuser}" />
			<input type="hidden" name="t_Workflow.todousers" value="${wf.todousers}" />
			<input type="hidden" name="t_Workflow.isopen" value="${wf.isopen}" />
			<input type="hidden" name="t_Workflow.editor" value="${wf.editor}" />
			<input type="hidden" name="t_Workflow.mainflowid" value="${wf.mainflowid}" />
			<input type="hidden" name="t_Workflow.subflowid" value="${wf.subflowid}" />
			<input type="hidden" name="t_Workflow.subflowname" value="${wf.subflowname}" />
			<input type="hidden" name="t_Workflow.isend" value="${wf.isend}" />
			<input type="hidden" name="t_Workflow.isnormalend" value="${wf.isnormalend}" />
			<input type="hidden" name="t_Workflow.ishold" value="${wf.ishold}" />
			<input type="hidden" name="t_Workflow.islock" value="${wf.islock}" />
			<input type="hidden" name="t_Workflow.isnewdoc" value="0" />
			<input type="hidden" name="t_Workflow.itemid" id="itemid" value="${wf.itemid}" />
			<input type="hidden" name="t_Workflow.hastemplate" value="${wf.hastemplate}" />
			<input type="hidden" name="t_Workflow.templatename" value="${wf.templatename}" />
			<input type="hidden" name="t_Workflow.worddocname" value="${wf.worddocname}" />
			<input type="hidden" name="t_Workflow.bodyiscreated" value="${wf.bodyiscreaded}" />
			<input type="hidden" name="t_Workflow.bodyauthor" value="${wf.bodyauthor}" />
			<input type="hidden" name="t_Workflow.bodyversion" value="${wf.bodyversion}" />			
			<input type="hidden" name="todomannum" value="${todomannum}" />
			<input type="hidden" name="issequence" value="${wa.issequence}" />
			<input type="hidden" name="havesubflow" value="${wa.havesubflow}" />
			<input type="hidden" name="editword" value="${wa.editword}" />
			<input type="hidden" name="viewword" value="${wa.viewword}" />
			<input type="hidden" name="endprocess" value="${wa.endprocess}" />
			<input type="hidden" name="specialsend" value="${wa.specialsend}" />
			<input type="hidden" name="backlaststep" value="${wa.backlaststep}" />
			<input type="hidden" name="backfirststep" value="${wa.backfirststep}" />
			<input type="hidden" name="haveopinionfield" id="haveopinionfield" value="${wa.haveopinionfield}" />
			<input type="hidden" name="opinionfield" id="opinionfield" value="${wa.opinionfield}" />
			<input type="hidden" name="opinionname" id="opinionname" value="${opinionname}" />
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
			<c:if test="${fn:length(trans)>0 }">
            <input type="hidden" name="condition1" id="condition1" value="${trans.get(0).condition1}" />
            <input type="hidden" name="ato1" id="ato1" value="${trans.get(0).ato}" />
            </c:if>
            <c:if test="${fn:length(trans)>1 }">
            <input type="hidden" name="condition2" id="condition2" value="${trans.get(1).condition1}" />
            <input type="hidden" name="ato2" id="ato2" value="${trans.get(1).ato}" />
            </c:if>
            <c:if test="${fn:length(trans)>2 }">
            <input type="hidden" name="condition3" id="condition3" value="${trans.get(2).condition1}" />
            <input type="hidden" name="ato3" id="ato3" value="${trans.get(2).ato}" />
            </c:if>
		</div>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><a class="btn btn-default" data-icon="fa-edit" onclick="LiuZhuanJiLu()">记录</a></li>
		<c:if test="${wkit1 != null}">
			<li><a class="btn btn-blue" onclick="QuHui()">取回</a></li>
		</c:if>
 		<c:if test="${candeal}">
			<c:if test="${wa.haveopinionfield == '1' && wa.handround == '1'}">
				<li><a class="btn btn-blue" onclick="TongYi()">已阅</a></li>
			</c:if>
			<c:if test="${wa.haveopinionfield == '1' && wa.haveopinionfield == '1'}">
				<li><a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()">填写意见</a></li>
			</c:if>
			<li class="line"></li>
			<c:if test="${wa.specialsend == '1'}">
				<li><a class="btn btn-default" data-icon="fa-plane" onclick="TeSong()">特送</a></li>
			</c:if>
			<c:if test="${wa.endprocess == '1'}">
				<li><a class="btn btn-red" data-icon="fa-share-square" onclick="ZhongZhi()">撤销</a></li>
			</c:if>
			<c:if test="${wa.atype != '1'}">
				<c:if test="${wa.backlaststep == '1'}">
					<li><a class="btn btn-red" data-icon="fa-share-square" onclick="TuiHuiShangBu()">退回</a></li>
				</c:if>
				<c:if test="${wa.backfirststep == '1'}">
					<li><a class="btn btn-red" data-icon="fa-share-square" onclick="TuiHuiNiGao()">退回经办人</a></li>
				</c:if>
			</c:if>
			<c:if test="${wa.atype == '3'}">
				<li><a class="btn btn-default" data-icon="save" onclick="WanCheng()">完成</a></li>
			</c:if>
			<c:if test="${wa.atype != '3'}">
			    <c:if test="${wa.havecondfield == '0'}">
				    <li><a class="btn btn-blue" data-icon="check" onclick="FaSong()">提交</a></li>
				</c:if>
                <c:if test="${wa.havecondfield == '1'}">
                    <li><a class="btn btn-blue" data-icon="check" onclick="ShengCheng('${wa.condfield}')">提交</a></li>
                </c:if>
			</c:if>
			<c:if test="${canedit || wa.haveopinionfield == '1'}">
				<li><a class="btn btn-default" data-icon="save" onclick="BaoCunTuiChu()">保存</a></li>
			</c:if>
 		</c:if>
	</ul>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		//年假行的处理
		handleVacationTypeTr();
		$("#type").change(function() {
			handleVacationTypeTr();
		});
	});

	function handleVacationTypeTr() {
		var vacationType = $("#type  option:selected").val();
		if (vacationType == "年休假") {
			$("#annualVacationTr").show();
		} else {
			$("#annualVacationTr").hide();
		}
	}
</script>