<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/JavaScript">
	function hasFill() {
		return true;
	}
	function unitwh() { // 编辑文号
	    $("#wh").val($("#wh1").val() + $("#wh2").val() + $("#wh3").val() + $("#wh4").val());
	}	
	function BianJiZhengWen() {
		var templateid = $("#templateid").val();
		var title = $("#title").val();
		var docno = $("#wh").val();
		var send1 = $("#send1").val();
		var send2 = $("#send2").val();
		var num = $("#num").val();
		var url = encodeURI("Main/Office/edit?RecordID=${dc.pid}&EditType=1,0&Template=" + templateid + "&Title=" + title + "&Docno=" + docno + "&Send1=" + send1 + "&Send2=" + send2 + "&Num=" + num);		
		$(this).dialog({id:'editword',url:url, title:'正文拟稿', mask:'true',drawable:'true',resizable:'false',maxable:'false',max:'true'});
	}
	function ShenHeZhengWen() {
		var url = "Main/Office/edit?RecordID=${dc.pid}&EditType=2,0";		
		$(this).dialog({id:'editword',url:url, title:'正文核稿', mask:'true',drawable:'true',resizable:'false',maxable:'false',max:'true'});
	}
	function ChaKanZhengWen() {
		var url = "Main/Office/view?RecordID=${dc.pid}&EditType=0,0";		
		$(this).dialog({id:'editword',url:url, title:'查看正文', mask:'true',drawable:'true',resizable:'false',maxable:'false',max:'true'});
	}
</script>
<div class="bjui-pageContent">
	 <form id="approveForm" action="Main/Docsend/save2" method="post" data-toggle="validate" >   
		<div class="pageFormContent" layoutH="65">
			<input type="hidden" name="t_Doc_Send.id" value="${dc.id}" />
			<input type="hidden" name="t_Doc_Send.u_id" id="userid" value="${dc.u_id}" />
			<input type="hidden" name="t_Doc_Send.d_id" value="${dc.d_id}" />
			<input type="hidden" name="t_Doc_Send.opinion1" id="opinion1" value="${dc.opinion1}" />
			<input type="hidden" name="t_Doc_Send.opinion2" id="opinion2" value="${dc.opinion2}" />
			<input type="hidden" name="t_Doc_Send.opinion3" id="opinion3" value="${dc.opinion3}" />
			<input type="hidden" name="t_Doc_Send.opinion4" id="opinion4" value="${dc.opinion4}" />
			<input type="hidden" name="t_Doc_Send.pid" id="pid" value="${dc.pid }" />
			<input type="hidden" name="t_Doc_Send.pstatus" value="${dc.pstatus }" />
			<div style="width: 95%;height: 40px;text-align:center">
		    	<span style="font-size:25px; margin-left:10px"><B>惠州人防办发文呈批笺</B></span>
			</div>
			<table class="wordInfo" align="center" style="width: 98%">				
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">拟稿科室</td>
					<td colspan="2">${startdept}</td>
					<td class="title" style="height: 40px;font-size:15px;">文　号</td>
					
					<td colspan="2"> <c:if test="${empty dc.docno}"><select id="wh1" name="wh1" data-toggle="selectpicker" onchange="unitwh()">
					    <option value="">请选择</option>
						<c:forEach items="${docnoList}" var="docnoList" >
						<option value="${docnoList.name}">${docnoList.name}</option>
						</c:forEach>
					</select>
					<input id="wh2" name="wh2" value="〔${nowyear}〕" size="8" onchange="unitwh()" />
					<input id="wh3" name="wh3" value="" size="4" onchange="unitwh()" />
					<select id="wh4" name="wh4" data-toggle="selectpicker" onchange="unitwh()">
						<option value="号">号</option>
						<option value="期">期</option>
					</select>
				
					<input type="hidden" name="t_Doc_Send.docno" id="wh" value="${dc.docno }" style="width: 98%" /></td>
					</c:if>
					<c:if test="${ !empty dc.docno}">
					<input  name="t_Doc_Send.docno" id="wh" value="${dc.docno }" style="text-align: center;width: 100%;" />
					</c:if>
				</tr>
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">拟稿时间</td>
					<td colspan="3"><input name="t_Doc_Send.approvedate" type="text" value="${dc.approvedate}" class="required" data-rule="required;date" data-msg-date="格式错误" data-msg-required="必填" size="15" /></td>
					<td class="title" style="height: 40px;font-size:15px;">密　级</td>
					<td><select name="t_Doc_Send.security" data-toggle="selectpicker" data-width="100%">
							<option value="无" <c:if test="${dc.security=='无' }">selected="selected"</c:if>>无</option>
							<option value="秘密" <c:if test="${dc.security=='秘密' }">selected="selected"</c:if>>秘密</option>
							<option value="机密" <c:if test="${dc.security=='机密' }">selected="selected"</c:if>>机密</option>
							<option value="绝密" <c:if test="${dc.security=='绝密' }">selected="selected"</c:if>>绝密</option>
						</select>
					</td>
				</tr>
				<tr>
					<td width="15%" class="title" style="height: 40px;font-size:15px;">拟稿人</td>
					<td width="18%">${starter}</td>
					<td width="15%" class="title" style="height: 40px;font-size:15px;">校稿人</td>
					<td width="18%"><input name="jbr.username" type="text" suggestFields="username" suggestUrl="Main/search/searchUser" lookupGroup="jbr" value="${dc.proof}" style="width: 96%" /></td>
					<td width="15%" class="title" style="height: 40px;font-size:15px;">份　数</td>
					<td width="18%"><input name="t_Doc_Send.num" id="num" class="number" value="${dc.num }" class="required" type="text" style="width: 50%" /> 份</td>
				</tr>
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">主　送</td>
					<td colspan="5">
					<input name="sendDept1.send1" id="send1" type="text" class="required" data-rule="required" data-msg-required="必填" suggestFields="send1" suggestUrl="Main/search/send1" lookupGroup="sendDept1" value="${dc.send1}" style="width: 100%" />
					</td>
				</tr>
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">抄　送</td>
					<td colspan="5">
					<input name="sendDept2.send2" id="send2" type="text"  suggestFields="send2" suggestUrl="Main/search/send2" lookupGroup="sendDept2" value="${dc.send2}" style="width: 100%" />
					</td>
				</tr>
				<tr>
					<td class="title" style="height: 60px;font-size:15px;">文件标题</td>
					<td colspan="5"><input name="t_Doc_Send.title" id="title" type="text" value="${dc.title }" class="required" data-rule="required" data-msg-required="必填" style="font-family: '方正小标宋'; font-size: 20px;height: 40px; font-weight: bold; width: 100%" /></td>
				</tr>
				<tr>
					<td class="title" style="height: 40px;font-size:15px;">正　文</td>
					<td colspan="5">
						<c:if test="${wa.editword == '1' && wa.atype == '1'}">
							<table>
								<tr>
								    <td style="width: 60px; border: 0px">
								    <a class="btn btn-blue" data-icon="fa-edit" onclick="BianJiZhengWen()" style="margin-left: 5px;"><span>拟稿</span></a>
								    </td>
									<td style="border: 0px"></td>									
								</tr>
							</table>
						</c:if>
					</td>
				</tr>
				<tr>
					<td class="title">附件列表</td>
					<td colspan="5"><div id="uploadFile" class="unitBox"><c:import url="../../Common/Attachment/edit.jsp" /></div></td>
				</tr>
				<tr>
					<td class="title"><br>批办意见<br><br>及<br><br>领导批示<br></td><td colspan="6"><div id="opinion1_1">${dc.opinion1 }</div><c:if test="${wa.haveopinionfield == '1'}">
						<div class="noprint">
						<br/>
						<a class="btn btn-blue" data-icon="fa-edit" onclick="TianXieYiJian()" style="margin-left: 5px;">填写意见</a>
						<a class="btn btn-blue" data-icon="fa-check"  onclick="FaSong()" style="margin-left: 5px;">提交</a>
					</div>
					</c:if>
					</td>
				</tr>
				<%-- <c:if test="${d_id != '2'}">
				<tr>
					<td class="title" style="height: 140px;font-size:15px" >科　室<br>意　见</td>
					<td colspan="5"><div id="opinion1_1">${dc.opinion1 }</div>
					
					</td>
				</tr>
				<tr>
					<td class="title" style="height: 140px;font-size:15px">办公室<br>意　见</td>
					<td colspan="5"><div id="opinion2_1">${dc.opinion2 }</div>
					
					</td>
				</tr>
				</c:if>
				<c:if test="${d_id == '2'}">
				<tr>
					<td class="title" style="height: 140px;font-size:15px" >办公室<br>意　见</td>
					<td colspan="5"><div id="opinion1_1">${dc.opinion1 }</div>
					</td>
				</tr>
				
				</c:if>
				<tr>
					<td class="title" style="height: 140px;font-size:15px">分　管<br>副部长<br>批　示</td>
					<td colspan="5"><div id="opinion3_1">${dc.opinion3 }</div>
					
					</td>
				</tr>
				<tr>
					<td class="title" style="height: 140px;font-size:15px">部　长<br>批　示</td>
					<td colspan="5"><div id="opinion4_1">${dc.opinion4 }</div>
					</td>
				</tr> --%>
			</table>
		</div>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close" data-icon="close">关闭</button></li>
		<li><button type="button" class="btn-default" onclick="BaoCunTuiChu()" data-icon="save">保存</button></li>
	</ul>
</div>