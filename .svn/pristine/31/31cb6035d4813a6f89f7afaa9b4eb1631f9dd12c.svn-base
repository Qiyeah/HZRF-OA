<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	//选择事件
	function S_NodeCheck(data) {
		var id =data.type;
		
		if(id == 1){
		$('#lediv').show();
		$('#fediv').hide();
		$('#NGselect').selectpicker('val', '');
		}else if(id == 2){
		$('#lediv').hide();
		$('#fediv').show();
		$('#DWselect').selectpicker('val', '');
		$('#Cbselect').selectpicker('val', '');
		}else{
		$('#lediv').hide();
		$('#fediv').hide();
		$('#NGselect').selectpicker('val', '');
		$('#DWselect').selectpicker('val', '');
		$('#Cbselect').selectpicker('val', '');
		}
	}

	$(function(){
		var id = "${type}";
		if(id == 1){
		$('#lediv').show();
		$('#fediv').hide();
		
		 <c:if test="${type == 1}">
		
		var creditChart = echarts.init(document.getElementById('creditCharts1'));
		option = {
			tooltip: {
				trigger: 'item',
				formatter: "{a} <br/>{b} ({d}%)"
			},		
			title: {
				show: true,
				text:'收文归档统计图',
				textStyle : {
					fontSize: 16
				}
			},
			color: ['#eb4f38','#ea8010','#f4c600','#11cd6e','#00bb9c','#56abe4','#9d55b8','#33475f'],
		    series: [
		        {
		            name:'收文归档数量',
		            type:'pie',
		            radius: [0, '55%'],	
		            data:[
		            	 <c:forEach items="${swmap}" var="o" varStatus="vs">
		            	<c:if test="${!vs.first }">,</c:if>
		            	 {value:${o.value}, name:'${o.key}'}
						</c:forEach> 
		            ]
		        }
		    ]
		};		
		creditChart.setOption(option);
		</c:if>  
		
		}else if(id == 2){
		$('#lediv').hide();
		$('#fediv').show();
		
		<c:if test="${type == 2}">
		var creditChartf = echarts.init(document.getElementById('creditChartf1'));
		optionf = {
			tooltip: {
				trigger: 'item',
				formatter: "{a} <br/>{b} ({d}%)"
			},		
			title: {
				show: true,
				text:'发文归档统计图',
				textStyle : {
					fontSize: 16
				}
			},
			color: ['#eb4f38','#ea8010','#f4c600','#11cd6e','#00bb9c','#56abe4','#9d55b8','#33475f'],
		    series: [
		        {
		            name:'发文归档数量',
		            type:'pie',
		            radius: [0, '55%'],	
		            data:[
		                <c:forEach items="${fwmap}" var="o" varStatus="vs">
		            	<c:if test="${!vs.first }">,</c:if>
		            	 {value:${o.value}, name:'${o.key}'}
						</c:forEach> 
		            ]
		        }
		    ]
		};		
		creditChartf.setOption(optionf);
		</c:if> 
		
		}else{
		$('#lediv').hide();
		$('#fediv').hide();
		
		<c:if test="${type == 0}">
		var creditChart = echarts.init(document.getElementById('creditCharts'));
		option = {
			tooltip: {
				trigger: 'item',
				formatter: "{a} <br/>{b} ({d}%)"
			},		
			title: {
				show: true,
				text:'收文归档统计图',
				textStyle : {
					fontSize: 16
				}
			},
			color: ['#eb4f38','#ea8010','#f4c600','#11cd6e','#00bb9c','#56abe4','#9d55b8','#33475f'],
		    series: [
		        {
		            name:'收文归档数量',
		            type:'pie',
		            radius: [0, '55%'],	
		            data:[
		            	 <c:forEach items="${swmap}" var="o" varStatus="vs">
		            	<c:if test="${!vs.first }">,</c:if>
		            	 {value:${o.value}, name:'${o.key}'}
						</c:forEach> 
		            ]
		        }
		    ]
		};		
		creditChart.setOption(option);
		
		var creditChartf = echarts.init(document.getElementById('creditChartf'));
		optionf = {
			tooltip: {
				trigger: 'item',
				formatter: "{a} <br/>{b} ({d}%)"
			},		
			title: {
				show: true,
				text:'发文归档统计图',
				textStyle : {
					fontSize: 16
				}
			},
			color: ['#eb4f38','#ea8010','#f4c600','#11cd6e','#00bb9c','#56abe4','#9d55b8','#33475f'],
		    series: [
		        {
		            name:'发文归档数量',
		            type:'pie',
		            radius: [0, '55%'],	
		            data:[
		                <c:forEach items="${fwmap}" var="o" varStatus="vs">
		            	<c:if test="${!vs.first }">,</c:if>
		            	 {value:${o.value}, name:'${o.key}'}
						</c:forEach> 
		            ]
		        }
		    ]
		};		
		creditChartf.setOption(optionf);
		</c:if> 
		
		}
	});
	
	
</script>
<div class="bjui-pageHeader">
	<form id="pagerForm" action="Main/DispatchStatitics/main" method="post" data-toggle="ajaxsearch">
		<div class="bjui-searchBar">
			<div style="float: left">
			<label>收发文类型：</label><select name="type" data-toggle="selectpicker" onchange="S_NodeCheck({type:this.value})">
							<option value="">--请选择--</option>
							<option value="0" <c:if test="${type == 0}">selected="selected"</c:if>>全部</option>
							<option value="1" <c:if test="${type == 1}">selected="selected"</c:if>>收文归档 </option>
							<option value="2" <c:if test="${type == 2}">selected="selected"</c:if>>发文归档</option>
						&nbsp;
					<select>
			</div>
		<div style="display: none;float:left" id="lediv">		
		<label>单位分类：</label> <select name="ltype" data-toggle="selectpicker" id="DWselect">
				<option value="">--请选择--</option>
				<option value="0" <c:if test="${ltype==0}">selected</c:if>>全部</option>
				<c:forEach items="${details}" var="temp">
					<option value="${temp.name}" <c:if test="${ltype==temp.name}">selected</c:if>>${temp.name}</option>
				</c:forEach>
					</select>
					
		<label>承办科室：</label> <select name="qdeptid" data-toggle="selectpicker" id="Cbselect">
						<option value="">--请选择--</option>
						<option value="0" <c:if test="${qdeptid==0}">selected</c:if> >全部科室</option>
						 <c:if test="${! empty depts}">
							<c:forEach items="${depts}" var="dept">
								<option value="${dept.id}" <c:if test="${!empty qdeptid && dept.id==qdeptid}">selected="selected"</c:if>>${dept.sname}</option>
							</c:forEach>
						</c:if> 
					</select>
		</div>
		<div style="display: none;float:left" id="fediv">			
		<label>拟稿科室：</label><select name="nqdeptid" data-toggle="selectpicker" id="NGselect">
						<option value="">--请选择--</option>
						<option value="0" <c:if test="${nqdeptid==0}">selected</c:if> >全部科室</option>
						 <c:if test="${! empty depts}">
							<c:forEach items="${depts}" var="dept">
								<option value="${dept.id}" <c:if test="${!empty nqdeptid && dept.id==nqdeptid}">selected="selected"</c:if>>${dept.sname}</option>
							</c:forEach>
						</c:if> 
					</select>
		</div>
		<div style="float: left">
		<label>收文日期：</label> <input type="text" name="sdate" style="width: 120px" data-toggle="datepicker" readonly value="${sdate}">-<input type="text" name="edate" style="width: 120px" 
			data-toggle="datepicker" readonly value="${edate}">
			<button type="submit" class="btn-default" data-icon="search">查询</button>
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
		</div>
		</div>
	</form>
</div>

<div class="bjui-pageContent">
	<div style="width:40%;float: left">
		<c:if test="${type == 0}">
	<table class="wordInfo">
		<tr>
			<td class="title" width="50%">承办(拟搞)科室</td>
			<td class="title" width="25%">收文归档数量</td>
			<td class="title" width="25%">发文归档数量</td>
		</tr>
		 <c:forEach items="${SWlist}" var="o" varStatus="vs">
		<tr>
			<td align="center">${o.fname}</td>
			<td align="center">${o.pid}</td>
			<td align="center">${FWlist.get(vs.index).pid}</td>
		</tr>
		</c:forEach> 
		
		<tr>
			<td align="center" style="font-weight: bold;">合计</td>
			<td align="center" style="font-weight: bold;">${swNumber}</td>
			<td align="center" style="font-weight: bold;">${fwNumber}</td>
		</tr>
		</table>
	</c:if>
	
	<c:if test="${type == 1}">
	<table class="wordInfo">
		
		<tr>
			<td class="title" width="50%">承办科室</td>
			<td class="title" width="50%">收文归档数量</td>
		</tr>
		 <c:forEach items="${SWlist}" var="o" varStatus="vs">
		<tr>
			<td align="center">${o.fname}</td>
			<td align="center">${o.pid}</td>
		</tr>
		</c:forEach> 
		
		<tr>
			<td align="center" style="font-weight: bold;">合计</td>
			<td align="center" style="font-weight: bold;">${swNumber}</td>
		</tr>
		</table>
	</c:if>
	
	<c:if test="${type == 2}">
	<table class="wordInfo">
		<tr>
			<td class="title" width="50%">拟搞科室</td>
			<td class="title" width="50%">发文归档数量</td>
		</tr>
		 <c:forEach items="${FWlist}" var="o" varStatus="vs">
		<tr>
			<td align="center">${o.fname}</td>
			<td align="center">${o.pid}</td>
		</tr>
		</c:forEach> 
		
		<tr>
			<td align="center" style="font-weight: bold;">合计</td>
			<td align="center" style="font-weight: bold;">${fwNumber}</td>
		</tr>
		</table>
	</c:if>
	
	</div>
	
	<div style="width:60%;float: right">
		<c:if test="${type == 0}">
			<div style="width: 300px; height: 350px;float: left;margin-left: 25px;" id="creditCharts"></div>
			<div style="width: 300px; height: 350px;float:left" id="creditChartf"></div>
		</c:if>
		<c:if test="${type == 1}">	
			<div style="width: 300px; height: 350px;margin-left: 150px;" id="creditCharts1"></div>
			</c:if>
			<c:if test="${type == 2}">	
			<div style="width: 300px; height: 350px;margin-left: 150px;"" id="creditChartf1"></div>
			</c:if>
	</div>
	<c:if test="${empty type}">
		<div align="center">
			<span style="color: red;">无任何记录！</span>
		</div>
	</c:if>
</div>


<div class="bjui-pageFooter">
	
</div>
 
 
 