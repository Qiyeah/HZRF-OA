function hasMakeNote(s) {
	var haveopinion = $("#haveopinionfield").val();
	var opinion = $("#opinion").val();
	var cstr = "";
	var cstr2 = "";
	var cstr3 = "";
	if (haveopinion == "1") { // 应该填写意见  选择了签收则可以不填
		// if (s == 1) { opinion = "同意"; } else { opinion = "不同意"; }
		if (opinion == "") {
			alert("请填写意见！");
			return false;
		}
	}
	return true;
}

function ZanCun() { // “暂存”操作
	if (!hasFill())
		return false;
	$("#operation").val("ZanCun");
	$(this).dialog({
		id : 'selna',
		url : "Main/Workflow/jieguo/" ,
		title : '请手动关掉',
		mask : true,
		drawable : false,
		resizable : false,
		maxable : false,
		width : 0,
		height : 0
	});
	$("#approveForm").submit();
}

function BaoCunTuiChu() { // “保存退出”操作
	if (!hasFill())
		return false;
	var temp = window.confirm("此举将保存当前工作并退出，是否保存？");
	if (temp) {
		$("#operation").val("BaoCunTuiChu");
		$(this).dialog({
			id : 'selna',
			url : "Main/Workflow/jieguo/" ,
			title : '请手动关掉',
			mask : true,
			drawable : false,
			resizable : false,
			maxable : false,
			width : 0,
			height : 0
		});
		$("#approveForm").submit();
	} else {
		$(this).dialog("closeCurrent");
	}
}

function ShengCheng(condfield) { // 生成操作。有条件域的发送操作	
	if (!hasMakeNote(1))
		return false;
	if (!hasFill())
		return false;
	
	var itemid = $("#itemid").val();
	var starter = $("#starter").val();
	var startdept = $("#startdept").val();
	var curdept = $("#curdept").val();
	var seldept = $("#selectdept").val();
	var nextstepsnum = $("#nextstepsnum").val();
	var condvalue = $("#" + condfield).val();
	$("#operation").val("FaSong");
	if (nextstepsnum == "0") {
		alert("环节设置中没有下一环节的信息，请与管理员联系！");
		return false;
	} else {
        var zhongwen = encodeURI(itemid+"-"+starter+"~"+curdept+"~"+seldept+"~"+startdept+"-"+condvalue);
        $.pdialog.open("/Main/Workflow/selectcondstep1/"+zhongwen,"selna","请选择执行人",{mask:true,drawable:true,resizable:true,maxable:false,width:300,height:200});
	}
}

function FaSong() { // 无条件域的“发送”操作
	if (!hasMakeNote(1))
		return false;
	if (!hasFill())
		return false;
	var temp = window.confirm("此操作将结束当前环节，并提交给下一处理人，您将不能对本流程再做修改，是否继续？");
	if (temp) {
		var pid=$("#pid").val();
		var itemid = $("#itemid").val();
		var nextstepsnum = $("#nextstepsnum").val();
		var nextitemid = $("#nextitemid").val();
		var starter = $("#starter").val();
		var startdept = $("#startdept").val();
		var curdept = $("#curdept").val();
		var seldept = $("#selectdept").val();
		var positionid=$("#positionid").val();
		$("#operation").val("FaSong");
        if (nextstepsnum == "0") {
            return false;
        } else if (nextstepsnum == "1") {
            var zhongwen = encodeURI(nextitemid+"-"+starter+"~"+curdept+"~"+seldept+"~"+startdept);
            $(this).dialog({
    			id : 'selna',
    			url : "Main/Workflow/selectman1/" + zhongwen,
    			title : '确认窗口',
    			mask : true,
    			drawable : false,
    			resizable : false,
    			maxable : false,
    			width : 300,
    			height : 400
    		});

        } else { // 有多个可选环节
            var zhongwen = encodeURI(itemid+"-"+starter+"~"+curdept+"~"+seldept+"~"+startdept+"-"+positionid+"-"+pid);
            $(this).dialog({
        		id : 'selna',
        		url : "Main/Workflow/selectstep1/" + zhongwen,
        		title : "确认窗口",
        		mask : true,
        		drawable : true,
        		resizable : true,
        		maxable : true,
        		width : 620,
        		height : 480
        	});
        }
	}
}

function FaSongdoc() { // 无条件域的“发送”操作	
	var isreceive=$("#isreceive").val();
	var opinion;
	
	if(isreceive=='1'){//判断该文是否已签收,已签收则判断
		opinion=$("#opinion10").val();
		if(opinion==""){
			alert("请填写意见！");
			return false;
		}
	}else{
		var haveopinion = $("#haveopinionfield").val();
		opinion = $("#opinion").val();
		if(opinion=="" && haveopinion=="1"){
			alert("请填写意见！");
			return false;
		}
	}
	
	var temp = window.confirm("此操作将结束当前环节，您将不能对本流程再做修改，是否继续？");
	if (temp) {
		var pid=$("#pid").val();
		var itemid = $("#itemid").val();
		var nextstepsnum = $("#nextstepsnum").val();
		var nextitemid = $("#nextitemid").val();
		var starter = $("#starter").val();
		var startdept = $("#startdept").val();
		var curdept = $("#curdept").val();
		var seldept = $("#selectdept").val();
		var positionid=$("#positionid").val();
		var iscanreceive=$("#iscanreceive").val();
		var isreceive=$("#isreceive").val();
		$("#operation").val("FaSongdoc");
	    
		if (nextstepsnum == "0") {
	            return false;
	        } else if (nextstepsnum == "1") {
	            var zhongwen = encodeURI(nextitemid+"-"+starter+"~"+curdept+"~"+seldept+"~"+startdept);
	            $(this).dialog({
	    			id : 'selna',
	    			url : "Main/Workflow/selectman1/" + zhongwen,
	    			title : '确认窗口',
	    			mask : true,
	    			drawable : false,
	    			resizable : false,
	    			maxable : false,
	    			width : 300,
	    			height : 400
	    		});
	
	        } else { // 有多个可选环节
	        
	        	var zhongwen = encodeURI(itemid+"-"+starter+"~"+curdept+"~"+seldept+"~"+startdept+"-"+positionid+"-"+pid);
	        	
	            $(this).dialog({
	        		id : 'selna',
	        		url : "Main/Workflow/selectstep1/" + zhongwen,
	        		title : "确认窗口",
	        		mask : true,
	        		drawable : true,
	        		resizable : true,
	        		maxable : true,
	        		width : 620,
	        		height : 480
	        	});
	        }
		
	}
}

function ChuLiWanBi() { // “处理完毕”操作
	if (!hasMakeNote(1))
		return false;
	if (!hasFill())
		return false;
	var temp = window.confirm("此操作将结束当前环节，并提交给下一处理人，您将不能对本流程再做修改，是否继续？");
	if (temp) {
		$("#operation").val("ChuLiWanBi");
		$("#nextitemid").val($("#itemid").val());
		$(this).dialog({
			id : 'selna',
			url : "Main/Workflow/jieguo/" ,
			title : '请手动关掉',
			mask : true,
			drawable : false,
			resizable : false,
			maxable : false,
			width : 0,
			height : 0
		});
		$("#approveForm").submit();
	}
}

function TeSong() { // “特送”操作
	var temp = window.confirm("此操作将重置流程环节，是否继续？？");
	if (temp) {
		var wid = $("#wid").val();
		var starter = $("#starter").val();
		var startdept = $("#startdept").val();
		var curdept = $("#curdept").val();
		var seldept = $("#selectdept").val();
		$("#operation").val("TeSong");
        var zhongwen = encodeURI(wid+"-"+starter+"~"+curdept+"~"+seldept+"~"+startdept);
        $(this).dialog({
    		id : 'selna',
    		url : "Main/Workflow/selectsteps/" + zhongwen,
    		title : "确认窗口",
    		mask : true,
    		drawable : true,
    		resizable : true,
    		maxable : true,
    		width : 620,
    		height : 480
    	});
	}
}

function WanCheng() { // “完成”操作
	if (!hasMakeNote(1))
		return false;
	if (!hasFill())
		return false;
	var temp = confirm("此操作将结束流程是否继续？");
	if (temp) {
		$("#operation").val("WanCheng");
		$("#nextitemid").val($("#itemid").val());
		$(this).dialog({
			id : 'selna',
			url : "Main/Workflow/jieguo/" ,
			title : '请手动关掉',
			mask : true,
			drawable : false,
			resizable : false,
			maxable : false,
			width : 0,
			height : 0
		});
		$("#approveForm").submit();
	}
}

function ZhongZhi() { // “终止流程”操作
	if (!hasMakeNote(1))
		return false;
	var temp = confirm("此操作将撤销 " + $("#flowname").val() + " 流程,是否继续?");
	if (temp) {
		$("#operation").val("ZhongZhi");
		$("#nextitemid").val($("#itemid").val());
		$(this).dialog({
			id : 'selna',
			url : "Main/Workflow/jieguo/" ,
			title : '请手动关掉',
			mask : true,
			drawable : false,
			resizable : false,
			maxable : false,
			width : 0,
			height : 0
		});
		$("#approveForm").submit();
	}
}

function QuHui() { // 取回
	var temp = window.confirm("此举将取回，\n是否继续？");
	if (temp) {
		$("#operation").val("QuHui");
		$("#nextitemid").val($("#itemid").val());
		$(this).dialog({
			id : 'selna',
			url : "Main/Workflow/jieguo/" ,
			title : '请手动关掉',
			mask : true,
			drawable : false,
			resizable : false,
			maxable : false,
			width : 0,
			height : 0
		});
		$("#approveForm").submit();
	}
}

function TuiHuiShangBu() { // 退回上一步
	if (!hasMakeNote(0))
		return false;
	var temp = window.confirm("此举表示您对上一个处理的处理意见有异议，\n将要将本事务退回给上一处理人重新拟定办理意见后，在此提交到您本人，\n是否继续？");
	if (temp) {
		$("#operation").val("TuiHuiShangBu");
		$("#nextitemid").val($("#itemid").val());
		$(this).dialog({
			id : 'selna',
			url : "Main/Workflow/jieguo/" ,
			title : '请手动关掉',
			mask : true,
			drawable : false,
			resizable : false,
			maxable : false,
			width : 0,
			height : 0
		});
		$("#approveForm").submit();
	}
}

function TuiHuiNiGao() { // 退回拟稿人
	if (!hasMakeNote(0))
		return false;
	var temp = window.confirm("此举将退回给经办人，是否继续？");
	if (temp) {
		$("#operation").val("TuiHuiNiGao");
		$("#nextitemid").val($("#itemid").val());
		$(this).dialog({
			id : 'selna',
			url : "Main/Workflow/jieguo/" ,
			title : '请手动关掉',
			mask : true,
			drawable : false,
			resizable : false,
			maxable : false,
			width : 0,
			height : 0
		});
		$("#approveForm").submit();
	}
}

function JieSuo() { // “解锁”操作
	var temp = confirm("此操作将解除当前流程的锁定，是否继续？");
	if (temp) {
		$("#operation").val("JieSuo");
		$(this).dialog({
			id : 'selna',
			url : "Main/Workflow/jieguo/" ,
			title : '请手动关掉',
			mask : true,
			drawable : false,
			resizable : false,
			maxable : false,
			width : 0,
			height : 0
		});
		$("#approveForm").submit();
	}
}

function ChongZhi() { // “重置”操作
	var temp = window.confirm("此操作将重新设置当前环节处理人，是否继续？");
	if (temp) {
		var wid = $("#wid").val();
		var starter = $("#starter").val();
		var startdept = $("#startdept").val();
		var curdept = $("#curdept").val();
		var seldept = $("#selectdept").val();
		$("#operation").val("ChongZhi");
        var zhongwen = encodeURI(wid+"-"+starter+"~"+curdept+"~"+seldept+"~"+startdept);
        $(this).dialog({
    		id : 'selna',
    		url : "Main/Workflow/selectsteps/" + zhongwen,
    		title : "重置处理人",
    		mask : true,
    		drawable : true,
    		resizable : true,
    		maxable : true,
    		width : 620,
    		height : 480
    	});
	}
}

function TongYi() { // “同意”操作
	var timer = new Date();
	var mon, day, hour, minu, secd;
	if (timer.getMonth() < 9) {
		mon = "0" + (timer.getMonth() + 1);
	} else {
		mon = (timer.getMonth() + 1);
	}
	if (timer.getDate() < 10) {
		day = "0" + timer.getDate();
	} else {
		day = timer.getDate();
	}
	if (timer.getHours() < 10) {
		hour = "0" + timer.getHours();
	} else {
		hour = timer.getHours();
	}
	if (timer.getMinutes() < 10) {
		minu = "0" + timer.getMinutes();
	} else {
		minu = timer.getMinutes();
	}
	if (timer.getSeconds() < 10) {
		secd = "0" + timer.getSeconds();
	} else {
		secd = timer.getSeconds();
	}
	var optime = timer.getFullYear() + "-" + mon + "-" + day + " " + hour + ":" + minu + ":" + secd;
	$("#opinion").val("已阅。");
	$("#opiniontime").val(optime);
	var opinionfield = $("#opinionfield").val();
	$("#" + opinionfield + "_1").html($("#" + opinionfield).val() + $("#opinion").val() + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + $("#curuser1").val() + "&nbsp;&nbsp;[" + optime + "]<br>");
	doit();
}

function TianXieYiJian() { // “填写意见”操作
	var opinion = $("input[name='opinion']").val();
    var zhongwen = encodeURI(opinion);
    zhongwen = "";
    $(this).dialog({
		id : 'WriteOpinion',
		url : "Main/Workflow/writeopinion1/" + zhongwen,
		title : "批办意见及领导批示",
		mask : true,
		drawable : true,
		resizable : true,
		maxable : true,
		width : 600,
		height : 300
	});

}



function LiuZhuanJiLu() { // “流程记录”操作
	var id = $("#id").val();
	$(this).dialog({
		id : 'ShowSteps',
		url : "Main/Workflow/showsteps/" + id,
		title : '流转记录',
		mask : true,
		drawable : true,
		resizable : true,
		maxable : true,
		width : 800,
		height : 400
	});
}

function XuanZheDuZhe() { // “选择读者”操作
	$.pdialog.open("Main/Workflow/selectreader1/", "SelectReader", "选择读者", {mask:true,drawable:false,resizable:false,width:600,height:400});
}

function XuanZheDuBan() { // “选择督办”操作
	$.pdialog.open("Main/Workflow/selectsupervisor1/", "SelectSupervisor", "选择督办", {mask:true,drawable:false,resizable:false,width:600,height:400});
}

function transStr(s) { // 转换特殊字符
	var str = new String("");
	var tmp = new String("");
	str = s;
	tmp = str.replace(/'/g, "’");
	str = tmp;
	tmp = str.replace(/</g, "〈");
	str = tmp;
	tmp = str.replace(/>/g, "〉");
	str = tmp;
	tmp = str.replace(/\\/g, "＼");
	str = tmp;
	tmp = str.replace(/\"/g, "”");
	str = tmp;
	tmp = str.replace(/%/g, "％");
	str = tmp;
	return str;
}
