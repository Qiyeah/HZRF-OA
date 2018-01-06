<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<script type="text/javascript">
	$(function(){
		var id = "${TClist}";
		if(id != ''){
		<c:if test="${!empty TClist}">
		
		var creditChart = echarts.init(document.getElementById('creditChartTherecipient'));
		option = {
			tooltip: {
				trigger: 'item',
				formatter: "{a} <br/>{b} ({d}%)"
			},		
			title: {
				show: true,
				text:'收件登记汇总统计图',
				left: 140,
				textStyle : {
					fontSize: 16
				}
			},
			color: ['#eb4f38','#ea8010','#f4c600','#11cd6e','#00bb9c','#56abe4','#9d55b8','#33475f'],
		    series: [
		        {
		            name:'收件登记数量',
		            type:'pie',
		            radius: [0, '40%'],
		            center: ['50%', '50%'],	
		            data:[
		            	 <c:forEach items="${TClist}" var="o" varStatus="vs">
		            	<c:if test="${!vs.first }">,</c:if>
		            	 {value:${o.cid + o.value}, name:'${o.name}'}
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
	<form id="pagerForm" action="Main/TherecipientStatitics/main" method="post" data-toggle="ajaxsearch">
		<div class="bjui-searchBar">
			
			<label>状态：</label>
					<td><select name="status" data-toggle="selectpicker">
					    <option value="" >--请选择--</option>
					    <option value="99" <c:if test="${status == '99'}">selected</c:if>>全部</option>
						<option value="0" <c:if test="${status == '0'}">selected</c:if>>未分发</option>
						<option value="1" <c:if test="${status == '1'}">selected</c:if>>已分发</option>
					</select>
							&nbsp;
		<label>单位分类：</label> <select name="ltype" data-toggle="selectpicker" id="DWselect">
				<option value="">--请选择--</option>
				<option value="0" <c:if test="${ltype==0}">selected</c:if>>全部</option>
				<c:forEach items="${details}" var="temp">
					<option value="${temp.name}" <c:if test="${ltype==temp.name}">selected</c:if>>${temp.name}</option>
				</c:forEach>
					</select>
		<label>日期：</label> <input type="text" name="sdate" style="width: 120px" data-toggle="datepicker" readonly value="${sdate}">-<input type="text" name="edate" style="width: 120px" 
			data-toggle="datepicker" readonly value="${edate}">
			<button type="submit" class="btn-default" data-icon="search">查询</button>
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
		
		</div>
	</form>
</div>

<div class="bjui-pageContent">
	<div style="width:50%;float: left">
		<c:if test="${!empty TClist }">
		
	<table class="wordInfo">
		<tr>
			<td class="title" width="40%">单位分类</td>
			<c:if test="${status =='0' || status =='99' || empty status}">
			 <td class="title" width="20%">未分发</td>
			 </c:if>
			 
			 <c:if test="${status =='1' || status =='99' || empty status}">
			 <td class="title" width="20%">已分发</td>
			 </c:if>
			 
			 <c:if test="${status =='99' || empty status}">
			 <td class="title" width="20%">合计</td>
			</c:if>
			
		</tr>
		<c:forEach items="${TClist}" var="o" varStatus="vs">
			<tr>
				<td align="center">${o.name}</td>
				<c:if test="${status =='0' || status =='99' || empty status}">
				<td align="center">${o.cid}</td>
				</c:if>
				
				<c:if test="${status =='1' || status =='99' || empty status}">
				<td align="center">${o.value}</td>
				</c:if>
				
				 <c:if test="${status =='99' || empty status}">
				<td align="center">${o.value+o.cid}</td>
				</c:if>
			</tr>
		</c:forEach>
			
			 <tr>
				<td align="center" style="font-weight: bold;">总计</td>
				
				<c:if test="${status =='0'}">
				<td align="center" style="font-weight: bold;">${zwfNumber}</td>
				</c:if>
				
				<c:if test="${status =='1'}">
				<td align="center" style="font-weight: bold;">${zyfNumber}</td>
				</c:if>
				
				<c:if test="${status =='99' || empty status}">
				<td align="center" style="font-weight: bold;">${zwfNumber}</td>
				<td align="center" style="font-weight: bold;">${zyfNumber}</td>
				<td align="center" style="font-weight: bold;">${zzyfNumber}</td>
				</c:if>
			</tr> 
			
		</table>
		
	</c:if>
	</div>
	
	<c:if test="${!empty TClist }">
	<div style="width:50%;float: right">
			<div style="width: 550px; height: 350px;float: left;margin-left: 10px;" id="creditChartTherecipient"></div> 
	</div>
	</c:if>
	
	<c:if test="${empty TClist}">
		<div align="center">
			<span style="color: red;">无任何记录！！</span>
		</div>
	</c:if>
</div>


<div class="bjui-pageFooter">
	
</div>
 
 
 