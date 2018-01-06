<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<% String basePath = request.getContextPath() + "/";%>
<div class="bjui-pageHeader">
    <div class="searchBar">
            <label>统计类别：</label>
            		<select id="type" name="type" class="combox">
            			<option value="z_01">办理科室别</option>
					    <option value="z_02">分办员</option>
					    <option value="b_01">办文类型</option>
					</select>
            
				<label>收文时间：</label><input data-toggle="datepicker" id="sdate" name="sdate" readonly size="12"> - 
				<input data-toggle="datepicker" type="text" id="edate" name="edate" size="12" readonly>
                   <button id="btngo" type="button" class="btn-default" data-icon="search">查询</button>
			
    </div>
</div>
<!-- ECharts单文件引入 -->
<script src="<%=basePath%>styles/echarts/build/dist/echarts-all.js"></script>

<div class="bjui-pageContent" sytle="vertical-align:middle;">
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="height:450px;margin: 50px;">请选择查询条件进行数据分析...</div>
    <script type="text/javascript">      
        $("#btngo").click(function () {
            var dom = document.getElementById('main');
            var mychart = echarts.init(dom);
            var maptype = $("#type").val().split("_")[0];
			var option = "";
           
            //图表显示提示信息
            mychart.showLoading({
                text: "图表数据正在努力加载..."
            });
            
            switch(maptype)
            {
            case "z":
            	option = {
            	    title : {
            	        text: '各科室收发文统计分析',
            	        subtext: ''
            	    },
            	    tooltip : {
            	        trigger: 'axis'
            	    },
            	    legend: {
            	        data:['收文','发文']
            	    },
            	    calculable : true,
            	    xAxis : [
            	        {
            	            type : 'category',
            	            data : ['办公室','干部一科','干部二科','干部三科','干部信息科','干部监督科','党员电教科','组织一科','组织二科','调研科','人才工作科','党联科']
            	        }
            	    ],
            	    yAxis : [
            	        {
            	            type : 'value'
            	        }
            	    ],
            	    series : [
            	        {
            	            name:'收文',
            	            type:'bar',
            	            data:[20, 30, 40, 23, 25, 76, 135, 162, 32, 20, 6, 30],
            	            /*
            	            markPoint : {
            	                data : [
            	                    {type : 'max', name: '最大值'},
            	                    {type : 'min', name: '最小值'}
            	                ]
            	            },*/
            	            itemStyle: {
            	                normal: {                   // 系列级个性化，横向渐变填充
            	                    color: function(params) {
            	                        // build a color map as your need.
            	                        var colorList = [
            	                          '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
            	                           '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
            	                           '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
            	                        ];
            	                        return colorList[params.dataIndex]
            	                    },
            	                    borderRadius: 5,
            	                    label : {
            	                        show : true,
            	                        textStyle : {
            	                            fontSize : '20',
            	                            fontFamily : '微软雅黑',
            	                            fontWeight : 'bold'
            	                        }
            	                    }
            	                }
            	            },
            	            /*
            	            markLine : {
            	                data : [
            	                    {type : 'average', name: '平均值'}
            	                ]
            	            }*/
            	        }
            	    ]
            	};
                //通过Ajax获取柱状图数据，可以再优化，动态显示数的数量
                $.ajax({
                    type: "post",
                    async: false, //同步执行
                    url: "Main/Docreceive/todoAnalyzeMapGetData",
                    dataType: "json", //返回数据形式为json
                    data: {type:$("#type").val(),sdate:$("#sdate").val(),edate:$("#edate").val()},
                    success: function(result) {
                        if (result) {
                            //将返回的category和series对象赋值给options对象内的category和series
                            //因为xAxis是一个数组 这里需要是xAxis[i]的形式
                            option.title.text = result.titleText;
                            option.legend.data = result.legendData; 
                            option.xAxis[0].data = result.xAxisData;

                            for(var i = 0; i < result.series.length; i++)
                            {
                                option.series[i].data = result.series[i].data;
                                option.series[i].name = result.series[i].name;
                            }
                            mychart.hideLoading();
                            mychart.setOption(option); 
                        }
                    },
                    error: function(errorMsg) {
                        alert("图表请求数据失败啦!");
                    }
                });
            	break;
            case "b":
				option = {
            		color:['#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                           '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                           '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'],
				    title : {
				        text: '',
				        subtext: '',
				        x:'center'
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				        orient : 'vertical',
				        x : 'left',
				        data:[]
				    },
				    calculable : true,
				    series : [
				        {
				            name:'访问来源',
				            type:'pie',
				            radius : '55%',
				            center: ['50%', '60%'],
				            data:[],
			            itemStyle:{ 
			                normal:{ 
			                      label:{ 
			                        show: true, 
			                        formatter: '{b} : {c} ({d}%)' 
			                      }, 
			                      labelLine :{show:true} 
			                    } 
			                } 
				        }
				    ]
				};
                //通过Ajax获取柱状图数据，可以再优化，动态显示数的数量
                $.ajax({
                    type: "post",
                    async: false, //同步执行
                    url: "Main/Docreceive/todoAnalyzeMapGetData",
                    dataType: "json", //返回数据形式为json
                    data: {type:$("#type").val(),sdate:$("#sdate").val(),edate:$("#edate").val()},
                    success: function(result) {
                        if (result) {
                            //将返回的category和series对象赋值给options对象内的category和series
                            //因为xAxis是一个数组 这里需要是xAxis[i]的形式
                            option.title.text = result.titleText;
                            option.legend.data = result.legendData; 

                            for(var i = 0; i < result.series.length; i++)
                            {
                                option.series[0].data = result.series[i].data;
                                option.series[0].name = result.series[i].name;
                            }
                            mychart.hideLoading();
                            mychart.setOption(option); 
                        }
                    },
                    error: function(errorMsg) {
                        alert("图表请求数据失败啦!");
                    }
                });
            	break;
            case "l":
            	option = {
            		color:['#00A2E8','#22B14C','#26C0C0','#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                           '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                           '#D7504B','#C6E579','#F4E001','#F0805A'],
            	    title: {
            	        text: '堆叠区域图'
            	    },
            	    tooltip : {
            	        trigger: 'axis'
            	    },
            	    legend: {
            	        data:['收文']
            	    },
            	    grid: {
            	        left: '3%',
            	        right: '4%',
            	        bottom: '3%',
            	        containLabel: true
            	    },
            	    xAxis : [
            	        {
            	            type : 'category',
            	            boundaryGap : false,
            	            data : ['周一','周二','周三','周四','周五','周六','周日']
            	        }
            	    ],
            	    yAxis : [
            	        {
            	            type : 'value'
            	        }
            	    ],
            	    series : [
            	        {
            	            name:'收文',
            	            type:'line',
            	            stack: '总量',
            	            areaStyle: {normal: {}},
            	            data:[120, 132, 101, 134, 90, 230, 210],
            	            smooth:true,
            	            itemStyle: {
            	            	normal: {
            	                    label : {
            	                        show : true,
            	                        textStyle : {
            	                            fontSize : '20',
            	                            fontFamily : '微软雅黑',
            	                            fontWeight : 'bold'
            	                        }
            	                    },
            	            		areaStyle: {
            	            			type: 'default'
            	            			}
            	            		}
            	            }
            	        }
            	    ]
            	};

            	//mychart.hideLoading();
            	 //mychart.setOption(option); 
            	 
                //通过Ajax获取柱状图数据，可以再优化，动态显示数的数量
                $.ajax({
                    type: "post",
                    async: false, //同步执行
                    url: "Main/Docreceive/todoAnalyzeMapGetData",
                    dataType: "json", //返回数据形式为json
                    data: {type:$("#type").val(),sdate:$("#sdate").val(),edate:$("#edate").val()},
                    success: function(result) {
                        if (result) {
                            //将返回的category和series对象赋值给options对象内的category和series
                            //因为xAxis是一个数组 这里需要是xAxis[i]的形式
                            option.title.text = result.titleText;
                            option.legend.data = result.legendData; 
                            option.xAxis[0].data = result.xAxisData;

                            option.series[0].data = result.series[0].data;
                            mychart.hideLoading();
                            mychart.setOption(option); 
                        }
                    },
                    error: function(errorMsg) {
                        alert("图表请求数据失败啦!");
                    }
                });
            	 
            	break;
            }
        });
    </script>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button class="btn btn-close" data-icon="close" >关闭</button></li>
	</ul>
</div>
