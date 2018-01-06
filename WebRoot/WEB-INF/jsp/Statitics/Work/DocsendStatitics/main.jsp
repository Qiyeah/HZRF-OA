<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	$(function(){
		var id = "${TWorkflowList}";
		if(id != ''){
		<c:if test="${!empty TWorkflowList }">
		var creditChart = echarts.init(document.getElementById('creditChartDocsendStatics'));
		option = {
			tooltip: {
				trigger: 'item',
				formatter: "{a} <br/>{b} ({d}%)"
			},		
			title: {
				show: true,
				text:'发文汇总统计图',
				textStyle : {
					fontSize: 16
				}
			},
			color: ['#eb4f38','#ea8010','#f4c600','#11cd6e','#00bb9c','#56abe4','#9d55b8','#33475f'],
		    series: [
		        {
		            name:'发文数量',
		            type:'pie',
		            radius: [0, '55%'],	
		            data:[
		            	 <c:forEach items="${TWorkflowList}" var="o" varStatus="vs">
		            	<c:if test="${!vs.first }">,</c:if>
		            	 {value:${map1.get(o.workpath)}, name:'${o.workpath}'}
						</c:forEach> 
		            ]
		        }
		    ]
		};		
		creditChart.setOption(option);
		</c:if>
		}
	});
</script>
<div class="bjui-pageHeader">
	<form id="pagerForm" action="Main/DocsendStatitics/main" method="post" data-toggle="ajaxsearch">
		<div class="bjui-searchBar">
			
			<label>流程环节：</label><select name="type" data-toggle="selectpicker" >
							<option value="">--请选择--</option>
							<option value="0" <c:if test="${type == 0}">selected="selected"</c:if>>全部</option>
							<c:forEach items="${TWalist}" var="TWalist">
							<option value="${TWalist.id}" <c:if test="${type == TWalist.id}">selected="selected"</c:if>>${TWalist.name} </option>
							</c:forEach>
						&nbsp;
					<select>
			<label>发文文号</label>
					<td><select name="doflag" data-toggle="selectpicker">
					    <option value="" >--请选择--</option>
					    <option value="0" <c:if test="${doflag == '0'}">selected</c:if>>全部</option>
					    <c:forEach items="${docnoList}" var="docnoList">
						<option value="${docnoList.name}" <c:if test="${doflag == docnoList.name}">selected</c:if>>${docnoList.name}</option>
						</c:forEach>
						
					</select>
							&nbsp;
		<label>拟搞科室：</label> <select name="qdeptid" data-toggle="selectpicker" id="Cbselect">
						<option value="">--请选择--</option>
						<option value="0" <c:if test="${qdeptid==0}">selected</c:if> >全部科室</option>
						 <c:if test="${! empty depts}">
							<c:forEach items="${depts}" var="dept">
								<option value="${dept.id}" <c:if test="${!empty qdeptid && dept.id==qdeptid}">selected="selected"</c:if>>${dept.sname}</option>
							</c:forEach>
						</c:if> 
					</select>
		
		
		<label>发文日期：</label> <input type="text" name="sdate" style="width: 120px" data-toggle="datepicker" readonly value="${sdate}">-<input type="text" name="edate" style="width: 120px" 
			data-toggle="datepicker" readonly value="${edate}">
			<button type="submit" class="btn-default" data-icon="search">查询</button>
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
		
		</div>
	</form>
</div>



<div class="bjui-pageContent">
	<div style="width:50%;float: left">
		<c:if test="${!empty TWorkflowList }">
	<table class="wordInfo">
		<tr>
			<td class="title" width="25%">流程环节</td>
			
			 <c:forEach items="${set}" var="o" varStatus="vs">
			<td class="title" width="25%">${o}</td>
			</c:forEach>
			<c:if test="${doflag == '0' || empty doflag }">
			<td class="title" width="25%">合计</td>
		</c:if>
		</tr>
		 <c:forEach items="${TWorkflowList}" var="o" varStatus="vs">
		<tr>
		
			<td align="center">${o.workpath}</td>
			
			<c:forEach items="${o.formname}" var="o1" varStatus="vs1">
			<td align="center">${o1}</td>
			</c:forEach> 
			
			 <c:if test="${doflag == '0' || empty doflag }">
			<td align="center">${map1.get(o.workpath)}</td>
			</c:if>  
			
		</tr>
		</c:forEach> 
		<tr>
			<td align="center" style="font-weight: bold;">总计</td>
			<c:forEach items="${totalArray}" var="o" varStatus="vs">
				<td align="center" style="font-weight: bold;">${o}</td>
			</c:forEach>
			
			<c:if test="${doflag == '0' || empty doflag }">
			<td align="center" style="font-weight: bold;">${zjNumber}</td> 
			</c:if>
		</tr>
		</table>
	</c:if>
	</div>
	
	<c:if test="${!empty TWorkflowList }">
	<div style="width:50%;float: right">
			<div style="width: 500px; height: 350px;float: left;margin-left: 50px;" id="creditChartDocsendStatics"></div>
	</div>
	</c:if>
	
	<c:if test="${empty TWorkflowList}">
		<div align="center">
			<span style="color: red;">无任何记录！！</span>
		</div>
	</c:if>
</div>


<div class="bjui-pageFooter">
	
</div>
 
 
 