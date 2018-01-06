function hasMakeNote(s) {

	var haveopinion = $("#haveopinionfield").val();
	var opinionname = $("#opinionfield").val();
	var opinion = $("#opinion").val();
	var cstr = "";
	var cstr2 = "";
	var cstr3 = "";
	if (haveopinion == "1") { // 应该填写意见
		// if (s == 1) { opinion = "同意"; } else { opinion = "不同意"; }
		if (opinion == "") {
			$(this).alertmsg('warn', '请填写意见！');
			return false;
		}
	}
	return true;
}

function ZanCun() { // “暂存”操作
	$("#operation").val("ZanCun");
	$("#approveForm").submit();
}

function BaoCunTuiChu() { // “保存退出”操作
	$(this).alertmsg("confirm", "此举将保存当前工作并退出，是否保存？", {
		okCall : BaoCunTuiChu_ok,
		cancelCall : BaoCunTuiChu_cancel
	});
}
function BaoCunTuiChu_ok() {
	$("#operation").val("BaoCunTuiChu");
	$("#approveForm").submit();
}
function BaoCunTuiChu_cancel() {
	$(this).dialog("closeCurrent");
}

function TianXieYiJian() { // “填写意见”操作
	var opinion = $("input[name='opinion']").val();
	var zhongwen = encodeURI(opinion);
	zhongwen = "";
	$(this).dialog({
		id : 'WriteOpinion',
		url : "Main/Workflow/writeopinion/" + zhongwen,
		title : "批办意见及领导批示",
		mask : true,
		drawable : true,
		resizable : true,
		maxable : true,
		width : 600,
		height : 300
	});

}

function ShengCheng(condfield) { // 生成操作。有条件域的发送操作
	if (!hasMakeNote(1))
		return false;
	var itemid = $("#itemid").val();
	var nextstepsnum = $("#nextstepsnum").val();
	var nextitemid = $("#nextitemid").val();
	var startdept = $("#startdept").val();
	var starter = $("#starter").val();
	var curdept = $("#curdept").val();
	var seldept = $("#seldept").val();
	$("#operation").val("FaSong");

	var ato = '';
	if (condfield != "") {
		var condvalue = $("#" + condfield).val();
		var tmpvalue1 = condvalue + $("#condition1").val();
		var tmpvalue2 = condvalue + $("#condition2").val();
		var tmpvalue3 = condvalue + $("#condition3").val();
		if (eval(tmpvalue1)) {
			ato = $("#ato1").val();
		} else if (eval(tmpvalue2)) {
			ato = $("#ato2").val();
		} else if (eval(tmpvalue3)) {
			ato = $("#ato3").val();
		} else {
			$(this).alertmsg('warn', '跳转条件配置错误，请与管理员联系！');
			return false;
		}
	} else {
		$(this).alertmsg('warn', '跳转条件配置错误，请与管理员联系！');
		return false;
	}
	$("#nextitemid").val(ato);
	var zhongwen = encodeURI(ato + "-" + starter + "~" + curdept + "~" + seldept + "~" + startdept);
	$(this).dialog({
		id : 'selna',
		url : "Main/Workflow/selectman/" + zhongwen,
		title : '确认窗口',
		mask : true,
		drawable : false,
		resizable : false,
		maxable : false,
		width : 300,
		height : 400
	});
}

function FaSong1() { // 厂家提交操作
	if (!hasMakeNote(1))
		return false;
	/* 已修改后把提示窗去掉 */
	var flag = true;
	$('#approveForm').isValid(function(v) {
		if (!v) {
			$('#approveForm').trigger("validate");
			flag = v;
		}
	});

	if (!flag) {
		return false;
	}
	$(this).alertmsg("confirm", "此操作将结束当前环节，并提交给下一处理人，您将不能对本流程再做修改，是否继续？", {
		okCall : FaSong_ok1
	});
}

function FaSong_ok1() {
	var itemid = $("#itemid").val();
	var nextstepsnum = $("#nextstepsnum").val();
	var nextitemid = $("#nextitemid").val();
	var startdept = $("#startdept").val();
	var starter = $("#starter").val();
	var curdept = $("#curdept").val();
	var seldept = $("#seldept").val();
	$("#operation").val("FaSong");
	if (nextstepsnum == "0") {
		$(this).alertmsg('warn', '环节设置中没有下一环节的信息，请与管理员联系！');
		return false;
	} else if (nextstepsnum == "1") {
		var zhongwen = encodeURI(nextitemid + "-" + starter + "~" + curdept + "~" + seldept + "~" + startdept);
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
	}
}

function FaSong() { // 无条件域的“发送”操作
	if (!hasMakeNote(1))
		return false;
	/* 已修改后把提示窗去掉 */
	var flag = true;
	$('#approveForm').isValid(function(v) {
		if (!v) {
			$('#approveForm').trigger("validate");
			flag = v;
		}
	});

	if (!flag) {
		return false;
	}
	$(this).alertmsg("confirm", "此操作将结束当前环节，并提交给下一处理人，您将不能对本流程再做修改，是否继续？", {
		okCall : FaSong_ok
	});

}
function FaSong_ok() {
	var itemid = $("#itemid").val();
	var nextstepsnum = $("#nextstepsnum").val();
	var nextitemid = $("#nextitemid").val();
	var startdept = $("#startdept").val();
	var starter = $("#starter").val();
	var curdept = $("#curdept").val();
	var seldept = $("#seldept").val();
	var positionid = $("#positionid").val();
	$("#operation").val("FaSong");
	if (nextstepsnum == "0") {
		$(this).alertmsg('warn', '环节设置中没有下一环节的信息，请与管理员联系！');
		return false;
//	} else if (nextstepsnum == "1") {
//		var zhongwen = encodeURI(nextitemid + "-" + starter + "~" + curdept + "~" + seldept + "~" + startdept);
//		$(this).dialog({
//			id : 'selna',
//			url : "Main/Workflow/selectman/" + zhongwen,
//			title : '确认窗口',
//			mask : true,
//			drawable : false,
//			resizable : false,
//			maxable : false,
//			width : 300,
//			height : 400
//		});
	} else { // 有多个可选环节
		var zhongwen = encodeURI(itemid + "-" + starter + "~" + curdept + "~" + seldept + "~" + startdept + "-" + positionid + "-" + nextitemid);
		$(this).dialog({
			id : 'selna',
			url : "Main/Workflow/selectstep/" + zhongwen,
			title : '确认窗口',
			mask : true,
			drawable : false,
			resizable : false,
			maxable : false,
			width : 620,
			height : 480
		});
	}
}

function ChuLiWanBi() { // “处理完毕”操作
	if (!hasMakeNote(1))
		return false;
	$(this).alertmsg("confirm", "此操作将结束当前环节，并提交给下一处理人，您将不能对本流程再做修改，是否继续？", {
		okCall : ChuLiWanBi_ok
	});
}
function ChuLiWanBi_ok() {
	$("#operation").val("ChuLiWanBi");
	$("#nextitemid").val($("#itemid").val());
	$("#approveForm").submit();
}

function TeSong() { // “特送”操作
	if (!hasMakeNote(1))
		return false;
	$(this).alertmsg("confirm", "此操作将结束当前环节，并提交给下一处理人，您将不能对本流程再做修改，是否继续？", {
		okCall : TeSong_ok
	});
}

function TeSong_ok() {
	var wid = $("#wid").val();
	var starter = $("#starter").val();
	var startdept = $("#startdept").val();
	var curdept = $("#curdept").val();
	var seldept = $("#seldept").val();
	$("#operation").val("TeSong");
	var zhongwen = encodeURI(wid + "-" + starter + "~" + curdept + "~" + seldept + "~" + startdept);
	$(this).dialog({
		id : 'selna',
		url : "Main/Workflow/selectsteps/" + zhongwen,
		title : '确认窗口',
		mask : true,
		drawable : false,
		resizable : false,
		maxable : false,
		width : 300,
		height : 400
	});
}

function WanCheng() { // “完成”操作
	if (!hasMakeNote(1))
		return false;
	$(this).alertmsg("confirm", "此操作将结束流程是否继续？", {
		okCall : WanCheng_ok
	});
}
function WanCheng_ok() {
	$("#operation").val("WanCheng");
	$("#nextitemid").val($("#itemid").val());
	$("#approveForm").submit();
}

function ZhongZhi() { // “终止流程”操作
	if (!hasMakeNote(1))
		return false;
	$(this).alertmsg("confirm", "此操作将撤销 " + $("#flowname").val() + " 流程,是否继续?", {
		okCall : ZhongZhi_ok
	});
}
function ZhongZhi_ok() {
	$("#operation").val("ZhongZhi");
	$("#nextitemid").val($("#itemid").val());
	$("#approveForm").submit();
}

function QuHui() { // 取回
	$(this).alertmsg("confirm", "此举将取回，\n是否继续？", {
		okCall : QuHui_ok
	});
}
function QuHui_ok() {
	$("#operation").val("QuHui");
	$("#nextitemid").val($("#itemid").val());
	$("#approveForm").submit();
}

function TuiHuiShangBu() { // 退回上一步
	if (!hasMakeNote(0))
		return false;
	$(this).alertmsg("confirm", "此举将退回上一步，\n是否继续？", {
		okCall : TuiHuiShangBu_ok
	});
}
function TuiHuiShangBu_ok() {
	$("#operation").val("TuiHuiShangBu");
	$("#nextitemid").val($("#itemid").val());
	$("#approveForm").submit();
}

function TuiHuiNiGao() { // 退回拟稿人
	if (!hasMakeNote(0))
		return false;
	$(this).alertmsg("confirm", "此举将退回申请人，是否继续？", {
		okCall : TuiHuiNiGao_ok
	});
}
function TuiHuiNiGao_ok() {
	$("#operation").val("TuiHuiNiGao");
	$("#nextitemid").val($("#itemid").val());
	$("#approveForm").submit();
}

function JieSuo() { // “解锁”操作
	$(this).alertmsg("confirm", "此操作将解除当前流程的锁定，是否继续？", {
		okCall : JieSuo_ok
	});
}
function JieSuo_ok() {
	$("#operation").val("JieSuo");
	$("#approveForm").submit();
}

function ChongZhi() { // “重置”操作
	$(this).alertmsg("confirm", "此操作将重新设置当前环节处理人，是否继续？", {
		okCall : ChongZhi_ok
	});
}
function ChongZhi_ok() {
	var itemid = $("#itemid").val();
	var starter = $("#starter").val();
	var startdept = $("#startdept").val();
	var curdept = $("#curdept").val();
	var seldept = $("#seldept").val();
	$("#operation").val("ChongZhi");
	var zhongwen = encodeURI(itemid + "-" + starter + "~" + curdept + "~" + seldept + "~" + startdept);
	$(this).dialog({
		id : 'selna',
		url : "Main/Workflow/selectman/" + zhongwen,
		title : '重置处理人',
		mask : true,
		drawable : false,
		resizable : false,
		maxable : false,
		width : 300,
		height : 400
	});
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
	$("#opinion").val("同意。");
	$("#opiniontime").val(optime);
	var opinionfield = $("#opinionfield").val();
	$("#" + opinionfield + "_1").html(
			$("#" + opinionfield).val() + $("#opinion").val() + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + $("#curuser1").val() + "&nbsp;&nbsp;[" + optime + "]<br>");
	doit();
}

function TianXieYiJian() { // “填写意见”操作
	var opinion = $("input[name='opinion']").val();

	var zhongwen = encodeURI(opinion);
	$(this).dialog({
		id : 'WriteOpinion',
		url : "Main/Workflow/writeopinion/" + zhongwen,
		title : "批办意见及领导批示",
		mask : true,
		drawable : false,
		resizable : false,
		maxable : false,
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
		drawable : false,
		resizable : false,
		maxable : false,
		width : 700,
		height : 400
	});
}

function XuanZheDuZhe() { // “选择读者”操作
	$(this).dialog({
		id : 'SelectReader',
		url : "Main/Workflow/selectreader/",
		title : '选择读者',
		mask : true,
		drawable : false,
		resizable : false,
		maxable : false,
		width : 600,
		height : 400
	});
}

function XuanZheDuBan() { // “选择读者”操作
	$(this).dialog({
		id : 'SelectSupervisor',
		url : "Main/Workflow/selectsupervisor/",
		title : '选择督办',
		mask : true,
		drawable : false,
		resizable : false,
		maxable : false,
		width : 600,
		height : 400
	});
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
