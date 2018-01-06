var wnd; // 定义辅助功能全局变量

// 作用：进行甲方签章
function DoJFSignature() {
	var DocForm = document.forms[0];
	DocForm.SignatureControl.FieldsList = ""; // 所保护字段
	DocForm.SignatureControl.Position(460, 260); // 签章位置，屏幕坐标
	DocForm.SignatureControl.UserName = "lyj"; // 文件版签章用户
	DocForm.SignatureControl.RunSignature(false); // 执行签章操作
}

// 作用：进行乙方签章
function DoYFSignature() {
	var DocForm = document.forms[0];
	if (wnd != undefined) {
		var results = wnd.split(";");
		DocForm.SignatureControl.CharSetName = results[0]; // 多语言集
		DocForm.SignatureControl.WebAutoSign = results[1]; // 自动数字签名
		DocForm.SignatureControl.WebCancelOrder = results[2]; // 撤消顺序
		DocForm.SignatureControl.PassWord = results[3]; // 签章密码

		var tmp = DocForm.SignatureControl.WebSetFontOther((results[4] == "1" ? true : false), results[5], results[6], results[7], results[8], results[9], (results[10] == "1" ? true : false)); // 设置签章附加文字格式
		DocForm.SignatureControl.WebIsProtect = results[11]; // 保护表单数据， 0不保护
		// 1保护表单数据，可操作
		// 2保存表单数据，并不能操作
		// 默认值1
	} else {
		DocForm.SignatureControl.WebIsProtect = 1; // 保护表单数据， 0不保护 1保护表单数据，可操作
		// 2保存表单数据，并不能操作 默认值1
		DocForm.SignatureControl.WebCancelOrder = 0; // 签章撤消原则设置, 0无顺序 1先进后出
		// 2先进先出 默认值0
	}

	DocForm.SignatureControl.FieldsList = ""; // 所保护字段
	DocForm.SignatureControl.Position(0, 0); // 签章位置
	DocForm.SignatureControl.SaveHistory = "False"; // 是否自动保存历史记录,true保存
	// false不保存 默认值false
	DocForm.SignatureControl.UserName = "wjd"; // 文件版签章用户
	DocForm.SignatureControl.SetPositionRelativeTag("YF", 1); // 设置签章位置是相对于哪个标记的什么位置
	DocForm.SignatureControl.PositionBySignType = 1; // 设置签章所处位置，1表示中间
	// DocForm.SignatureControl.ValidateCertTime = '1';
	// //检测数字证书有效性，安装目前下必须有根证书Root.cer和吊销列表Crl.crl
	DocForm.SignatureControl.RunSignature(); // 执行签章操作
}

// 作用：进行手写签名
function DoSXSignature() {
	var DocForm = document.forms[0];
	if (wnd != undefined) {
		var results = wnd.split(";");
		DocForm.SignatureControl.CharSetName = results[0]; // 多语言集
		DocForm.SignatureControl.WebAutoSign = results[1]; // 自动数字签名
		DocForm.SignatureControl.WebCancelOrder = results[2]; // 撤消顺序
		DocForm.SignatureControl.PassWord = results[3]; // 签章密码

		var tmp = DocForm.SignatureControl.WebSetFontOther((results[4] == "1" ? true : false), results[5], results[6], results[7], results[8], results[9], (results[10] == "1" ? true : false)); // 设置签章附加文字格式
		DocForm.SignatureControl.WebIsProtect = results[11]; // 保护表单数据， 0不保护
		// 1保护表单数据，可操作
		// 2保存表单数据，并不能操作
		// 默认值1
	} else {
		DocForm.SignatureControl.WebIsProtect = 1; // 保护表单数据， 0不保护 1保护表单数据，可操作
		// 2保存表单数据，并不能操作 默认值1
		DocForm.SignatureControl.WebCancelOrder = 0; // 签章撤消原则设置, 0无顺序 1先进后出
		// 2先进先出 默认值0
	}

	DocForm.SignatureControl.FieldsList = "XYBH=协议编号;BMJH=保密级别;JF=甲方签章;YF=乙方签章;HZNR=合作内容;QLZR=权利责任;CPMC=产品名称;DGSL=订购数量;DGRQ=订购日期"; // 所保护字段
	DocForm.SignatureControl.Position(0, 0); // 手写签名位置
	// DocForm.SignatureControl.SaveHistory="false"; //是否自动保存历史记录,true保存
	// false不保存 默认值false
	DocForm.SignatureControl.Phrase = "同意;不同意;请核实"; // 设置文字批注常用词
	DocForm.SignatureControl.HandPenWidth = 1; // 设置、读取手写签名的笔宽
	DocForm.SignatureControl.HandPenColor = 100; // 设置、读取手写签名笔颜色
	DocForm.SignatureControl.SetPositionRelativeTag("HZNR", 1); // 设置签章位置是相对于哪个标记的什么位置
	DocForm.SignatureControl.PositionBySignType = 1; // 设置签章所处位置，1表示中间
	// DocForm.SignatureControl.UserName="lyj"; //文件版签章用户
	DocForm.SignatureControl.RunHandWrite(); // 执行手写签名
}

// 作用：根据鼠标定位签章
function DoMouseSignature() {
	var DocForm = document.forms[0];
	var mx = event.clientX + document.body.scrollLeft - document.body.clientLeft; // 获取X坐标值
	var my = event.clientY + document.body.scrollTop - document.body.clientTop; // 获取Y坐标值

	DocForm.SignatureControl.FieldsList = ""; // 所保护字段
	DocForm.SignatureControl.Position(mx, my); // 签章位置
	DocForm.SignatureControl.UserName = "lyj"; // 文件版签章用户
	DocForm.SignatureControl.PositionBySignType = 1; // 设置签章所处位置，1表示中间
	DocForm.SignatureControl.RunSignature(); // 执行签章操作
}

// 作用：获取签章信息，以XML格式返回，并且分析显示数据.具体的XML格式请参照技术白皮书
// 具体分析后的内容如何处理，请自己做适当处理,本示例仅将返回结果进行提示。
function WebGetSignatureInfo() {
	var DocForm = document.forms[0];
	var mSignXMl = DocForm.SignatureControl.GetSignatureInfo(); // 读取当前文档签章信息，以XML返回
	alert(mSignXMl); // 调试信息

	var XmlObj = new ActiveXObject("Microsoft.XMLDOM");
	XmlObj.async = false;
	var LoadOk = XmlObj.loadXML(mSignXMl);
	var ErrorObj = XmlObj.parseError;

	if (ErrorObj.errorCode != 0) {
		alert("返回信息错误...");
	} else {

		var CurNodes = XmlObj.getElementsByTagName("iSignature_HTML");
		for ( var iXml = 0; iXml < CurNodes.length; iXml++) {
			var TmpNodes = CurNodes.item(iXml);
			/*
			 * alert(TmpNodes.selectSingleNode("SignatureOrder").text); //签章序列号
			 * alert(TmpNodes.selectSingleNode("SignatureName").text); //签章名称
			 * alert(TmpNodes.selectSingleNode("SignatureUnit").text); //签章单位
			 * alert(TmpNodes.selectSingleNode("SignatureUser").text); //签章用户
			 * alert(TmpNodes.selectSingleNode("SignatureDate").text); //签章日期
			 * alert(TmpNodes.selectSingleNode("SignatureIP").text); //签章电脑IP
			 * alert(TmpNodes.selectSingleNode("KeySN").text); //钥匙盘序列号
			 * alert(TmpNodes.selectSingleNode("SignatureSN").text); //签章序列号
			 * alert(TmpNodes.selectSingleNode("SignatureResult").text);
			 * //签章验测结果
			 */
		}

	}
}

// 作用：设置禁止(允许)签章的密钥盘 具体参数信息请参照技术白皮书
function WebAllowKeySN() {
	var DocForm = document.forms[0];
	var KeySn = window.prompt("请输入禁止在此页面上签章的钥匙盘序列号:");
	DocForm.SignatureControl.WebAllowKeySN(false, KeySn);
}

// 作用：获取KEY密钥盘的SN序列号
function WebGetKeySN() {
	var DocForm = document.forms[0];
	var KeySn = DocForm.SignatureControl.WebGetKeySN();
	alert("您的钥匙盘序列号为:" + KeySn);
}

// 作用：校验用户的 PIN码是否正确
function WebVerifyKeyPIN() {
	var DocForm = document.forms[0];
	var KeySn = DocForm.SignatureControl.WebGetKeySN();
	var mBoolean = DocForm.SignatureControl.WebVerifyKeyPIN("123456");
	if (mBoolean) {
		alert(KeySn + ":通过校验");
	} else {
		alert(KeySn + ":未通过校验");
	}
}

// 作用：修改钥匙盘PIN码,参数1为原PIN码,参数2为修改后的PIN码
function WebEditKeyPIN() {
	var DocForm = document.forms[0];
	var oldPIN = window.prompt("请输入原来的PIN码");
	if (oldPIN == null) {
		return;
	}
	var newPIN = window.prompt("请输入修改后的PIN码");
	if (newPIN == null) {
		return;
	}
	var mBoolean = DocForm.SignatureControl.WebEditKeyPIN(oldPIN, newPIN);
	if (mBoolean) {
		alert("钥匙盘PIN码修改成功!");
	} else {
		alert("钥匙盘PIN码修改不成功!");
	}
}

// 作用：批量验证签章
function BatchCheckSign() {
	var DocForm = document.forms[0];
	DocForm.SignatureControl.BatchCheckSign();
}

// 作用：辅助功能
function ParameterSetting() {
	var DocForm = document.forms[0];
	var mParameter = new Array();
	mParameter[0] = DocForm.SignatureControl.CharSetName; // 多语言集
	mParameter[1] = DocForm.SignatureControl.WebAutoSign; // 自动数字签名
	mParameter[2] = DocForm.SignatureControl.WebCancelOrder; // 撤消顺序
	mParameter[3] = DocForm.SignatureControl.PassWord; // 签章密码
	if (wnd != undefined) {
		var results = wnd.split(";");
		mParameter[4] = results;
	}

	tmp = window.showModalDialog("ParameterSetting.jsp", mParameter, "dialogWidth:350px;dialogHeight:520px;menubar:no;toolbar:no;scrollbars:no;resizable:no;center:yes;status:no;help:no;");
	if (tmp != undefined) {
		wnd = tmp;
	}
	if (wnd != undefined) {
		var results = wnd.split(";");
		DocForm.SignatureControl.CharSetName = results[0];
		DocForm.SignatureControl.WebAutoSign = results[1];
		DocForm.SignatureControl.WebCancelOrder = results[2];
		DocForm.SignatureControl.PassWord = results[3];

		var tmp = DocForm.SignatureControl.WebSetFontOther((results[4] == "1" ? true : false), results[5], results[6], results[7], results[8], results[9], (results[10] == "1" ? true : false));
	}
}

// 作用：显示或隐藏签章
function ShowSignature(visibleValue) {
	var DocForm = document.forms[0];
	var mLength = document.getElementsByName("iHtmlSignature").length;
	for ( var i = 0; i < mLength; i++) {
		var vItem = document.getElementsByName("iHtmlSignature")[i];
		vItem.Visiabled = visibleValue;
	}
}

// 作用：删除签章
function DeleteSignature() {
	var DocForm = document.forms[0];
	var mLength = document.getElementsByName("iHtmlSignature").length;
	var mSigOrder = "";
	for ( var i = mLength - 1; i >= 0; i--) {
		var vItem = document.getElementsByName("iHtmlSignature")[i];
		// mSigOrder :=
		if (vItem.SignatureOrder == "1") {
			vItem.DeleteSignature();
		}
	}
}

// 作用：移动签章
function MoveSignature() {
	var DocForm = document.forms[0];
	DocForm.SignatureControl.MovePositionByNoSave(100, 100);
	alert("位置增加100");
	DocForm.SignatureControl.MovePositionByNoSave(-100, -100);
	alert("回到原来位置");
	DocForm.SignatureControl.MovePositionToNoSave(100, 100);
	alert("移动到100，100");
}

// 作用：脱密
function ShedCryptoDocument() {
	var DocForm = document.forms[0];
	DocForm.SignatureControl.ShedCryptoDocument();
}

// 作用：脱密还原
function ResetCryptoDocument() {
	var DocForm = document.forms[0];
	DocForm.SignatureControl.ResetCryptoDocument();
}

// 作用：打印文档
function PrintDocument() {
	var DocForm = document.forms[0];
	var mCount = DocForm.SignatureControl.PrintDocument(false, 1, 2); // 打印控制窗体
	alert("实际打印份数：" + mCount);
}