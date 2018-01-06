<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<style>
<!--
form#approveForm span.wrap_bjui_btn_box{
	 width:100%;  
}
-->
</style>
<script type="text/JavaScript">
	function hasFill() {
		return true;
	}
</script>
<div class="bjui-pageContent tableContent">
<form id="approveForm" action="Main/Docreceive/save" method="post" class="pageForm" data-toggle="validate">
			<input type="hidden" name="t_Doc_Receive.id" value="${dc.id}" />
			<input type="hidden" name="t_Doc_Receive.u_id" id="userid" value="${dc.u_id}" />
			<input type="hidden" name="t_Doc_Receive.d_id" value="${dc.d_id}" />
			<input type="hidden" name="t_Doc_Receive.achieveid" value="${dc.achieveid}" />
			<input type="hidden" name="t_Doc_Receive.receiver" value="${dc.receiver}" />
			<input type="hidden" name="t_Doc_Receive.opinion1" id="opinion1" value="${dc.opinion1}" />
			<input type="hidden" name="t_Doc_Receive.opinion2" id="opinion2" value="${dc.opinion2}" />
			<input type="hidden" name="t_Doc_Receive.opinion3" id="opinion3" value="${dc.opinion3}" />
			<input type="hidden" name="t_Doc_Receive.opinion4" id="opinion4" value="${dc.opinion4}" />
			<input type="hidden" name="t_Doc_Receive.opinion5" id="opinion5" value="${dc.opinion5}" />
			<input type="hidden" name="t_Doc_Receive.opinion6" id="opinion6" value="${dc.opinion6}" />
			<input type="hidden" name="t_Doc_Receive.pid" id="pid" value="${dc.pid }" />
			<input type="hidden" name="t_Doc_Receive.pstatus" value="${dc.pstatus }" />
			<div style="width: 100%;height: 60px;padding:15px;text-align:center">
		    <span style="font-size:30px; margin-left:10px"><B>文件呈批表</B></span>		    
		    </div>
			<table class="wordInfo" align="center" style="width: 95%;margin-bottom:30px">
				<tr>
					<td width="15%" class="title">来文单位</td>
					<td width="25%" colspan="2"><input name="t_Doc_Receive.unit" type="text" value="${dc.unit}" class="required" data-msg-required="必填" data-rule="required" maxlength="50" style="width: 100%" /></td>
					<td width="13%" class="title">收文日期</td>
					<td width="17%"><input name="t_Doc_Receive.receivedate" type="text" value="${dc.receivedate}" data-toggle="datepicker" class="required date" data-rule="required" style="width: 100%" /></td>
					<td width="12%" class="title">办文编号</td>
					<td width="18%"><input name="t_Doc_Receive.docno" type="text" value="${dc.docno}" maxlength="50" style="width: 100%" /></td>
				</tr>
				<tr>
					<td class="title">经办人</td><td colspan="2">${receiver}</td>
					<td class="title">分办人</td><td><input name="zhk.auditor" type="text" suggestFields="auditor" suggestUrl="Main/search/auditor" lookupGroup="zhk" value="${dc.auditor}"
						 maxlength="50" style="width: 100%" style="text-align: center;" /></td>
					<td class="title">处理类型</td>
					<td><select name="t_Doc_Receive.doflag" id ="doflag" data-toggle="selectpicker" data-width="100%">
						<option value="1" <c:if test="${dc.doflag=='1'}">selected</c:if>>一般阅知</option>
						<option value="2" <c:if test="${dc.doflag=='2'}">selected</c:if>>普通收文</option>
					</select></td>
				</tr>
				<tr>
					<td class="title">密　级</td>
					<td width="15%"><select name="t_Doc_Receive.security" data-style="selectrequired" data-msg-required="必填" data-rule="required" data-toggle="selectpicker" data-width="100%">
							<option value="无" <c:if test="${dc.security=='无' }">selected="selected"</c:if>>无</option>
							<option value="秘密" <c:if test="${dc.security=='秘密' }">selected="selected"</c:if>>秘密</option>
					</select></td>
					<td class="title" rowspan="2">标　题</td>
					<td colspan="4" rowspan="2" class="normal" align="center"><input name="t_Doc_Receive.title" type="text" style="font-size:20px;width:100%;height:40px" data-rule="required" class="required" data-msg-required="必填" maxlength="250" value="${dc.title}"/></td>
				</tr>
				<tr>
					<td class="title">紧急程度</td>
					<td><select name="t_Doc_Receive.degree" data-toggle="selectpicker" data-width="100%" >
                            <option value="0" <c:if test="${dc.degree=='0' }">selected="selected"</c:if>>平件</option>
                            <option value="1" <c:if test="${dc.degree=='1' }">selected="selected"</c:if>>急件</option>
                        </select></td>
				</tr>
				<tr>
					<td class="title">文件列表</td>
					<td colspan="6">
					<div id="uploadFile" class="unitBox"><c:import url="../../Common/Attachment/edit.jsp" /></div>
					</td>
				</tr>
	            <tr>
	                <td class="title">备 注</td>
	                <td colspan="6"><textarea name="t_Doc_Receive.memo" rows="2" maxlength="250" style="width: 100%;">${dc.memo}</textarea></td>
	            </tr>
				<tr>
					<td class="title"><br>批办意见<br><br>及<br><br>领导批示<br></td><td colspan="6"><div id="opinion1_1">${opinion1 }</div><c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion1'}">
						<div class="noprint">
						<br/>
						<a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
						<a class="btn btn-blue" data-icon="fa-check"  onclick="FaSong()" style="margin-left: 5px;">提交</a>
					</div>
					</c:if>
					</td>
				</tr>
				<tr>
					<td class="title" style="height: 150px;"><br>传<br><br>阅<br><br>签<br><br>名<br></td>
					<td colspan="6"><div id="opinion2_1">${opinion2}</div>
					<c:if test="${wa.haveopinionfield == '1' && wa.opinionfield == 'opinion2'}">
					<div class="noprint">
						<br/>
						<a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
						<c:if test="${wa.sealword == '1'}">
						<a class="btn btn-blue" data-icon="fa-tag" onclick="DoSignature()" style="margin-left: 5px;">签章</a>
					    </c:if>
					    <c:if test="${todomannum > 1}">
					          <c:if test="${wa.amount != 3}">
					             <a class="btn btn-blue" data-icon="fa-check" onclick="ChuLiWanBi()" style="margin-left: 5px;">提交</a>
					          </c:if>
					          <c:if test="${wa.amount == 3}">
					             <a class="btn btn-blue" data-icon="fa-check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
					          </c:if>
				        </c:if>
				        <c:if test="${todomannum <= 1}">
					      <c:if test="${wa.atype == '3'}">
						      <a class="btn btn-default" data-icon="fa-save" onclick="WanCheng()" style="margin-left: 5px;">完成</a>
					      </c:if>
					      <c:if test="${wa.atype != '3'}">
						      <a class="btn btn-blue" data-icon="fa-check" onclick="FaSong()" style="margin-left: 5px;">提交</a>
					      </c:if>
				          </c:if>
					    </div>
					</c:if></td>
				</tr>
			</table>
			<input type="hidden" name="positionid" id="positionid" value="${positionid }" />
			<input type="hidden" name="t_Workflow.id" id="id" value="${wf.id}" />
			<input type="hidden" name="t_Workflow.flowname" value="${wf.flowname}" />
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
		</form>
	</div>
<div class="bjui-pageFooter">
	<ul>
                <c:if test="${wkit1 != null}"><li><a class="btn btn-blue" data-icon="fa-check" onclick="QuHui()">取回</a></li></c:if>
                <c:if test="${wf.editor == userid && wf.ishold == '0'}">
                    <c:if test="${wa.haveopinionfield == '1'}">
                        <li><a class="btn btn-blue" data-icon="edit" onclick="TianXieYiJian()">填写意见</a></li>
                    </c:if>
					<c:if test="${wa.sealword == '1'}">
						<li><a class="btn btn-blue" data-icon="fa-tag" onclick="DoJFSignature()">签章</a></li>
					</c:if>
                    <c:if test="${wa.atype != '1'}">
                        <c:if test="${wa.endprocess == '1'}">
                            <li><a class="btn btn-blue" data-icon="fa-power-off" onclick="ZhongZhi()">终止</a></li>
                        </c:if>
                        <c:if test="${wa.backlaststep == '1'}">
                            <li><a class="btn btn-close" data-icon="fa-share-square" onclick="TuiHuiShangBu()">退回</a></li>
                        </c:if>
                        <c:if test="${wa.backfirststep == '1'}">
                            <li><a class="btn btn-close" data-icon="fa-share-square" onclick="TuiHuiNiGao()">退回经办人</a></li>
                        </c:if>
                    </c:if>
                    <c:if test="${wa.specialsend == '1'}">
                        <li><a class="btn btn-default" data-icon="fa-plane" onclick="TeSong()">特送</a></li>
                    </c:if>
                    <c:if test="${todomannum > 1}">
                        <li><a class="btn btn-blue" data-icon="check" onclick="ChuLiWanBi()">提交</a></li>
                    </c:if>
                    <c:if test="${canedit || wa.haveopinionfield == '1'}">
						<li><a class="btn btn-default" data-icon="save" onclick="BaoCunTuiChu()">保存</a></li>
					</c:if>
                    <c:if test="${todomannum <= 1}">
                        <c:if test="${wa.atype == '3'}">
                            <li><a class="btn btn-default" data-icon="save" onclick="WanCheng()">完成</a></li>
                        </c:if>
                        <c:if test="${wa.atype != '3'}">
                            <li><a class="btn btn-blue" data-icon="check" onclick="FaSong()">提交</a></li>
                        </c:if>
                    </c:if>
					
				</c:if>
			</ul>
</div>
