<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	function hasFill() {
		return true;
	}
</script>
<div class="bjui-pageContent tableContent">
	<form id="approveForm" method="post" action="Main/Meeting/save"class="pageForm" data-toggle="validate">
		
		<div class="pageFormContent" layoutH="59">
			<input type="hidden" name="t_Meeting_Approve.id" value="${mt.id}" />
			<input type="hidden" name="t_Meeting_Approve.u_id" id="userid" value="${mt.u_id}" />
			<input type="hidden" name="t_Meeting_Approve.d_id" value="${mt.d_id}" />
			<input type="hidden" name="t_Meeting_Approve.opinion1" id="opinion1" value="${mt.opinion1}" />
			<input type="hidden" name="t_Meeting_Approve.opinion2" id="opinion2" value="${mt.opinion2}" />
			<input type="hidden" name="t_Meeting_Approve。opinion3" id="opinion3" value="${mt.opinion3}" />
			<input type="hidden" name="t_Meeting_Approve.opinion4" id="opinion4" value="${mt.opinion4}" />
			<input type="hidden" name="t_Meeting_Approve。pid" id="pid" value="${mt.pid}" />
			<input type="hidden" name="t_Meeting_Approve.pstatus" value="${mt.pstatus}" />
			<input type="hidden" name="flag" value="0" />
			<div style="height: 35px;"></div>
            <div style="width: 95%;height: 50px;padding:15px;text-align:center">
                <span style="font-size:25px; margin-left:10px"><B>会议审批单</B></span>
            </div>			
			<table class="wordInfo" align="center" style="width: 95%;margin-bottom:23px">
				<tr>
					<td width="15%" class="title">申 请 人</td>
					<td width="35%">${starter}</td>
					<td width="15%" class="title">申请科室</td>
					<td width="35%">${startdept}</td>
				</tr>
				<tr>
					<td class="title">申请日期</td>
					<td><input type="text" data-toggle="datepicker" name="t_Meeting_Approve.approvedate" value="${mt.approvedate }" readonly class="required date" data-rule="required" style="width: 100%" /></td>
                    <td class="title">会议时间</td>
                    <td>
                        <table style="width: 100%">
                            <tr>
                                <td style="border: 0px;width:85px" >
                                    <input data-toggle="datepicker" type="text" name="t_Meeting_Approve.mdate" class="date" size="12"  readonly class="required"  data-rule="required" style="width: 100%" value="${mt.mdate}"/>
                                </td>
                                <td style="border: 0px;width:20px">日</td>
                                <td style="border: 0px;width:20px"> <select name="t_Meeting_Approve.hour">
                                    <c:forEach var="x" begin="7" end="23" step="1">
                                        <option value="${x}" <c:if test="${mt.hour == x}">selected="selected"</c:if>> ${x}</option>
                                    </c:forEach>
                                </select>
                                </td>
                                <td style="border: 0px;width:20px">时</td>
                                <td style="border: 0px;width:20px"><select name="t_Meeting_Approve.minute">
                                    <c:forEach var="x" begin="0" end="50" step="10">
                                        <option value="${x}" <c:if test="${mt.minute == x}">selected="selected"</c:if>>${x}</option>
                                    </c:forEach>
                                </select>
                                </td>
                                <td style="border: 0px;">分</td>
                            </tr>
                        </table>
                    </td>
				</tr>
				<tr>
					<td class="title">地　　点</td>
					<td colspan="3"><input type="text" name="t_Meeting_Approve.address" value="${mt.address }" class="required" data-rule="required" style="width: 100%" /></td>
				</tr>
				<tr>
                    <td class="title">主 持 人</td>
					<td><input type="hidden" name="chairman.userid" value="${mt.chairman }" />					
						<input type="text" name="chairman.username" value="${chairman }" suggestFields="username" suggestUrl="Main/search/searchUser" lookupGroup="chairman" class="required" data-rule="required"/></td>
                    <td class="title">类　　型</td>
					<td><input type="text" name="t_Meeting_Approve.type" value="${mt.type }" style="width: 100%" /></td>
				</tr>
				<tr>
					<td class="title">参加人员</td>
					<td colspan="3"><input type="hidden" name="attendee.id" value="${mt.attendee }" />
						<input type="text" name="attendee.name" value="${attendee }" readonly="readonly"  
						 href="Main/search/searchUsers" lookupGroup="attendee" data-id="searchUser" data-width="500" data-height="480" style="padding-right: 0px;width:100%;" data-title="出席人"></td>
				</tr>
				<tr>
                    <td class="title">标　　题</td>
					<td colspan="3"><input type="text" name="t_Meeting_Approve.title" value="${mt.title }" class="required" style="width: 98%" /></td>
				</tr>
				<tr>
                    <td class="title">内　　容</td>
					<td colspan="3"><textarea name="t_Meeting_Approve.content" rows="6" style="width: 98%; overflow:auto">${mt.content }</textarea></td>
				</tr>
				<tr>
					<td class="title">附件列表</td>
					<td colspan="3">
						<div id="uploadFile" class="unitBox"><c:import url="../../Common/Attachment/edit.jsp" /></div>
					</td>
				</tr>
				<tr>
                    <td class="title" style="height: 80px">科　　室<br/>意　　见</td>
					<td colspan="3"><div id="opinion1_1">${opinion1 }</div>
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion1'}">
					<div class="noprint">
                            <br/>
                            <a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
                            <c:if test="${wa.atype != '3'}">
                                <a class="btn btn-blue" data-icon="fa-check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
                            </c:if>
					</div>                            
                    </c:if></td>
				</tr>
				<tr>
                    <td class="title" style="height: 120px">副 主 任<br/>批　　办</td>
					<td colspan="3"><div id="opinion2_1">${opinion2 }</div>
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion2'}">
					<div class="noprint">
                            <br/>
                            <a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
                            <c:if test="${wa.sealword == '1'}">
						        <a class="btn btn-blue" data-icon="fa-tag" onclick="DoSignature()" style="margin-left: 5px;">签章</a>
					        </c:if>
                            <c:if test="${wa.atype != '3'}">
                                <a class="btn btn-blue" data-icon="fa-check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
                            </c:if>
                     </div>
                     </c:if>
                   </td>
				</tr>
				<tr>
                    <td class="title" style="height: 120px">主　　任<br/>批　　办</td>
					<td colspan="3"><div id="opinion3_1">${opinion3 }</div>
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion3'}">
					<div class="noprint">
                            <br/>
                            <a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
                            <c:if test="${wa.sealword == '1'}">
						        <a class="btn btn-blue" data-icon="fa-tag" onclick="DoSignature()" style="margin-left: 5px;">签章</a>
					         </c:if>
                            <c:if test="${wa.atype != '3'}">
                                <a class="btn btn-blue" data-icon="fa-check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
                            </c:if>
                        </div>
                        </c:if>
                    </td>
				</tr>
				<%-- <tr>
                    <td class="title" style="height: 80px">科　　室<br/>意　　见</td>
					<td colspan="3"><div id="opinion1_1">${opinion1 }</div></td>
				</tr>
				<tr>
                    <td class="title" style="height: 120px">副 主 任<br/>批　　办</td>
					<td colspan="3"><div id="opinion2_1">${opinion2 }</div></td>
				</tr>
				<tr>
                    <td class="title" style="height: 120px">主　　任<br/>批　　办</td>
					<td colspan="3"><div id="opinion3_1">${opinion3 }</div></td>
				</tr> --%>
			</table>
			<input type="hidden" name="positionid" id="positionid" value="${positionid }" />
			<input type="hidden" name="sendnote" id="num" value="${sendnote}" />
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
		</div>
		
	</form>
</div>
<div class="bjui-pageFooter">
			<ul>
                <c:if test="${wkit1 != null}">
                <li><a class="btn btn-blue" data-icon="fa-check" onclick="QuHui()"><span>取回</span></a></li></c:if>
                <c:if test="${wf.editor == userid && wf.ishold == '0'}">
                    <c:if test="${wa.handround == '1'}">
                        <li><a class="btn btn-blue" data-icon="fa-check" onclick="TongYi()"><span>已阅</span></a></li>
                    </c:if>
                    <c:if test="${wa.haveopinionfield == '1'}">
                        <li><a class="btn btn-blue" data-icon="edit" onclick="TianXieYiJian()"><span>填写意见</span></a></li>
                    </c:if>
                    <c:if test="${wa.atype != '1'}">
                        <c:if test="${wa.endprocess == '1'}">
                            <li><a class="btn btn-blue" data-icon="fa-power-off" onclick="ZhongZhi()"><span>终止</span></a></li>
                        </c:if>
                        <c:if test="${wa.backlaststep == '1'}">
                            <li><a class="btn btn-blue" data-icon="fa-share-square" onclick="TuiHuiShangBu()"><span>退回</span></a></li>
                        </c:if>
                        <c:if test="${wa.backfirststep == '1'}">
                            <li><a class="btn btn-blue" data-icon="fa-share-square" onclick="TuiHuiNiGao()"><span>退回经办人</span></a></li>
                        </c:if>
                    </c:if>
                    <c:if test="${wa.specialsend == '1'}">
                        <li><a class="btn btn-blue" data-icon="plane" onclick="TeSong()"><span>特送</span></a></li>
                    </c:if>
                    <c:if test="${todomannum > 1}">
                        <li><a class="btn btn-blue" data-icon="fa-check" onclick="ChuLiWanBi()"><span>提交</span></a></li>
                    </c:if>
                    <c:if test="${canedit || wa.haveopinionfield == '1'}">
						<li><a class="btn btn-default" data-icon="fa-save" onclick="BaoCunTuiChu()"><span>保存</span></a></li>
					</c:if>
                    <c:if test="${todomannum <= 1}">
                        <c:if test="${wa.atype == '3'}">
                            <li><a class="btn btn-default" data-icon="fa-save" onclick="WanCheng()"><span>完成</span></a></li>
                        </c:if>
                        <c:if test="${wa.atype != '3'}">
							<c:choose>
							     <c:when test="${fn:contains(wa.name, '通知')}">
							           <li><a class="btn btn-blue" data-icon="fa-bullhorn" onclick="FaSong()"><span>发布</span></a></li>
							     </c:when>
							     <c:otherwise>
								     <li><a class="btn btn-blue" data-icon="fa-check" onclick="FaSong()"><span>提交</span></a></li>
								 </c:otherwise>
							 </c:choose>
                        </c:if>
                    </c:if>
					
                </c:if>
			</ul>
		</div>