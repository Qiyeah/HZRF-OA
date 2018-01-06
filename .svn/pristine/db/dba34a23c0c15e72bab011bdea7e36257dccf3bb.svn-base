<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<% String basePath = request.getContextPath() + "/";%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#todoReset").click(function() {
			var table = $("#todoSearch");
			table.find("input").val("");
			table.find("select").val("");
		});
	});
</script>
	<div class="pageHeader">
		<div class="searchBar">
            <form id="pagerForm" method="post" action="Main/Relation/main" onsubmit="return navTabSearch(this);">
                <input type="hidden" name="pageNum" value="${page.pageNumber}" />
                <input type="hidden" name="numPerPage" value="${page.pageSize}" />
                <input type="hidden" name="orderField" value="${orderField}" />  
   				<input type="hidden" name="orderDirection" value="${orderDirection}" />
   				<input type="hidden" name="sort" value="${sort}" />
                <table id="todoSearch" class="searchContent">
                    <tr>
                        <td><span>党员姓名：</span></td><td><input type="text" name="sname" value="${sname}"></td>
                        <td><span>办理日期：</span></td><td><input type="text" name="sdate" style="width: 100px" class="date" readonly value="${sdate}"> - <input type="text" name="edate" style="width: 100px" class="date" readonly value="${edate}"></td>
                        <td colspan='4'><span>介绍地址  由：</span><input type="text" name="sfrom" style="width: 100px"  value="${sfrom}" size = "20"> 去 <input type="text" name="sto" style="width: 100px" value="${sto}" size = "20"></td>
                        
                        <td>
                            <div class="subBar">
                                <div class="buttonActive">
                                    <div class="buttonContent">
                                        <button type="submit">查询</button>
                                    </div>
                                </div>
                                <div class="buttonActive" style="margin-left:10px">
                                    <div class="buttonContent">
                                        <button id="todoReset" type="button">重置</button>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                      
                    <tr>
                        <td><span>身份证号：</span></td><td><input type="text" name="sidcard" value="${sidcard}"></td>
                        <td><span>有效日期：</span></td><td><input type="text" name="validsdate" style="width: 100px" class="date" readonly value="${validsdate}"> - <input type="text" name="validedate" style="width: 100px" class="date" readonly value="${validedate}"></td>
                        <td><span>落实情况：</span></td>
                        <td><select name="sresult" class="combox">
                        <option value="" <c:if test="${sresult == 0}"> selected="selected" </c:if>>请选择</option>
                        <option value="1" <c:if test="${sresult == 1}"> selected="selected" </c:if>>未落实</option>
                        <option value="2" <c:if test="${sresult == 2}"> selected="selected" </c:if>>已落实</option></select>
                        </td>
                        <td><span>介绍类型：</span></td>
                        <td><select name="stotype" class="combox">
                        <option value="" <c:if test="${stotype == 0}"> selected="selected" </c:if>>请选择</option>
                        <option value="1" <c:if test="${stotype == 1}"> selected="selected" </c:if>>转出</option>
                        <option value="2" <c:if test="${stotype == 2}"> selected="selected" </c:if>>转入</option>
                        <option value="3" <c:if test="${stotype == 3}"> selected="selected" </c:if>>市内</option>
                        </select>
                        </td>
                    </tr>
                    <tr>
                        <td><span>抬头单位：</span></td><td><input type="text" name="sunit" value="${sunit}"></td>
                        <td><span>党费日期：</span></td><td><input type="text" name="paidsdate" style="width: 100px" class="date" readonly value="${paidsdate}"> - <input type="text" name="paidedate" style="width: 100px" class="date" readonly value="${paidedate}"></td>
                        <td><span>党员类型：</span></td>
                        <td><select name="stype" class="combox">
                        <option value="" <c:if test="${stype == 0}"> selected="selected" </c:if>>请选择</option>
                        <option value="1" <c:if test="${stype == 1}"> selected="selected" </c:if>>预备</option>
                        <option value="2" <c:if test="${stype == 2}"> selected="selected" </c:if>>正式</option>
                        </select>
                        </td>
                        <td align="right"><span>民族：</span></td><td><input type="text" name="snation" value="${snation}" size = "6"></td>
                    </tr>
                    
                </table>
            </form>
		</div>
	</div>
    <form method="post" action="Main/Relation/export" onsubmit="return validateCallback(this, xxxAjaxDone)">
        <%-- 导出用检索条件保存--%>
        <input type="hidden" name="sname" value="${sname}"/>
        <input type="hidden" name="sdate" value="${sdate}"/>
        <input type="hidden" name="edate" value="${edate}"/>
        <input type="hidden" name="sfrom" value="${sfrom}"/>
        <input type="hidden" name="sto" value="${sto}"/>
        <%-- 身份证号--%>
        <input type="hidden" name="sidcard" value="${sidcard}"/>
        <input type="hidden" name="sunit" value="${sunit}"/>
        <input type="hidden" name="validsdate" value="${validsdate}"/>
        <input type="hidden" name="validedate" value="${validedate}"/>
        <input type="hidden" name="sresult" value="${sresult}"/>
        <input type="hidden" name="stotype" value="${stotype}"/>
        <input type="hidden" name="snation" value="${snation}"/>
        <input type="hidden" name="paidsdate" value="${paidsdate}"/>
        <input type="hidden" name="paidedate" value="${paidedate}"/>
        <input type="hidden" name="stype" value="${stype}"/>
        <button id="exportRelation" style="display: none" hidden="hidden" type="submit">导出Excel</button>
    </form>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
                <c:if test="${pert:hasperti(applicationScope.relationMain_add, loginModel.limit)}">
					<li><a class="add" href="Main/Relation/addip" target="dialog" mask="true" max="false" maxable="true" minable="false" resizable="false" drawable="false" title="新建" width="850" height="550"><span>新建</span></a></li>
                </c:if>
                <c:if test="${pert:hasperti(applicationScope.relationMain_update, loginModel.limit)}">
					<li><a class="edit" href="Main/Relation/updateip/{rid}"  target="dialog" rel="relation_dialog" max="false" mask="true" maxable="false" minable="false" resizable="false" drawable="false" title="修改" width="850" height="550"><span>修改</span></a></li>
                </c:if>
                <c:if test="${pert:hasperti(applicationScope.relationMain_delete, loginModel.limit)}">
					<li><a class="delete" href="Main/Relation/delete/{rid}" target="ajaxTodo" title="您确定要删除该收件吗?"><span>删除</span></a></li>
                </c:if>
                <c:if test="${pert:hasperti(applicationScope.relationMain_export, loginModel.limit)}">
                <li><a class="excel" href="javascript:exportSubmit('exportRelation')" title="导出Excel"><span>导出Excel</span></a></li>
                </c:if>
               
             	<c:if test="${pert:hasperti(applicationScope.eecertadd, loginModel.limit)}">
                <li><a class="upload" href="Main/upload/uploadip/3" target="dialog" mask="true" drawable="false" resizable="false" maxable="false" rel="upload" title="党组织转接导入" width="560" height="300"><span>党组织转接导入</span></a></li>
            </c:if>
             </ul>
          
		</div>
		<table class="table" width="100%" layoutH="160">
			<thead>
				<tr align="center">
                    <th width="5%">序号</th>
                    <th width="19%" orderField="unit" class="desc">抬头单位</th>
                    <th width="6%" orderField="name" class="desc">姓名</th>
                    <th width="5%" orderField="sex" class="desc">性别</th>
                    <th width="5%" orderField="age" class="desc">年龄</th>
                    <th width="5%" orderField="type" class="desc">党员类型</th>
                    <th width="10%" orderField="rfrom" class="desc">转出地</th>
                    <th width="10%" orderField="rto" class="desc">转入地</th>
                    <th width="10%">党员联系电话</th>
                    <th width="5%" orderField="rdate" class="desc">办理日期</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${! empty page.list}">
						<c:forEach items="${page.list}" var="r" varStatus="vs">
                                <tr style="cursor:hand" align="center" target="rid" rel="${r.id}" ondblclick="printPDF(${r.no})">
                                <td>${vs.index+1}</td>
                                <td>${r.unit}</td>
                                <td>${r.name}</td>
                                <td><c:if test="${r.sex == 1}">男</c:if><c:if test="${r.sex == 2}">女</c:if></td>
                                <td>${r.age}</td>
                                <td><c:if test="${r.type == 1}">预备</c:if><c:if test="${r.type == 2}">正式</c:if></td>
                                <td>${r.rfrom}</td>
                                <td>${r.rto}</td>
                                <td>${r.u_phone}</td>
                                <td>${r.rdate}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr align="center">
							<td colspan="10" style="color: red;">无任何记录！</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span> <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
					<option <c:if test="${page.pageSize==20}">selected</c:if> value="20">20</option>
					<option <c:if test="${page.pageSize==50}">selected</c:if> value="50">50</option>
					<option <c:if test="${page.pageSize==100}">selected</c:if> value="100">100</option>
					<option <c:if test="${page.pageSize==200}">selected</c:if> value="200">200</option>
				</select> <span>条，共${page.totalRow}条</span>
			</div>
			<div class="pagination" targetType="navTab" totalCount="${page.totalRow}" numPerPage="${page.pageSize}" pageNumShown="5" currentPage="${page.pageNumber}"></div>
		</div>
	</div>
	<script>
    function printPDF(no){
        window.open(<%=basePath%>+'File/Report/'+no+'.pdf');
    }
    </script>

