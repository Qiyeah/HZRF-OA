<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/include/inc.jsp"%>
<%
    String basePath = request.getContextPath() + "/";
%>
<html>
<head>
    <link href="<%=basePath%>styles/dwz/themes/default/style.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>styles/dwz/themes/css/core.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>styles/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print" />
    <style>
        body {
            overflow: auto;
            background-color: #ffffff;
            text-align: left;
        }

        @media print {
            .noprint {
                display: none
            }
        }
        wordInfo td div{
            font-family: 华文楷体;
            font-size: 20px;
            min-height:20px;
            letter-spacing:2px;
            font-weight:bold;
            text-align: center;
            margin-top: 10px;
        }

        .floats div{
            float: left;
            height:30px;
            padding-top:5px;

        }
        .divText{
            border: 0px;
            text-align:center;
            font-size:20px;
            border-bottom:1px solid;
        }

    </style>
    <script type="text/javascript">
        function PrintDocument() {
            document.all.bar.style.display = "none";
            //var DocForm = document.forms[0];
            //var mCount = DocForm.SignatureControl.PrintDocument(false, 1, 2); // 打印控制窗体
            window.print();
            alert("实际打印份数：" + mCount);
            document.all.bar.style.display = "";
        }
    </script>
</head>
<body onload="document.forms[0].SignatureControl.ShowSignature('${la.pid}');" style="background-color: #ffffff;">
<form id="DocForm">
    <input type="hidden" name="DocumentID" value="${la.pid}">
    <div id="bar" class="formBar1" style="_top:expression(offsetParent.scrollTop+0);width:100%">
        <ul>
            <li><a class="button" onclick="PrintDocument()"><span>打印</span></a></li>
        </ul>
    </div>
    <div style="height: 120px;">&nbsp;</div>
    <table class="wordInfo" align="center" style="width: 90%;border:1px solid ;margin-top: 10px;">
        <tr>
            <td width="6%" align="center" style="padding:2px;line-height:26px;font-size:24px;font-family: 华文楷体;">党<br/>员<br/>证<br/>明<br/>信<br/>存<br/>根</td>
            
            <td width="94%">
                <div style="width: 100%;padding:5px;" class="floats" >
	                <div style="width: 70%;height: 70px;padding-top:10px;font-family: 华文楷体;text-align:right;margin-top:5px;font-size:25px;letter-spacing:25px;">
	                    党员证明信 
	                </div>
	                <div style="width: 30%;height: 50px;padding-top:10px;text-align:right;margin-top:5px;font-size:18px;">
	                    第${t_present.no}号&nbsp;
	                </div>
                </div>
                <div style="width: 100%;padding-left: 10px; letter-spacing:3px;" class="floats" >
                    <div style="margin-left: 40px;width:42%;text-align:center;font-size:20px;" class="divText">${t_present.pname}</div>
                    <div style="font-size:20px;" >同志系中共
                    <c:if test="${t_present.type == 1}">预备</c:if><c:if test="${t_present.type == 2}">正式</c:if>
                                                                     党员</div>
                </div>
                <div style="width:100%;height: 20px;padding:5px;padding-left: 10px;text-align:left;font-size:20px;" class="floats">
                    <div style="font-size:20px;style="margin-left: 40px;">由</div>
                    <div style="width: 30%;text-align: center;font-size:20px;" class="divText" >${t_present.rfrom}</div>
                    <div style="font-size:20px;">去</div>
                    <div style="width: 40%;text-align: center" class="divText"> ${t_present.rto}</div>
                </div>
                <div style="width: 100%;height: 30px;padding:15px;text-align:right;font-size: 20px;margin-top:0px;margin-left:30px;">
                    ${fn:substring(t_present.rdate, 0, 4)}&nbsp;年&nbsp;&nbsp;${fn:substring(t_present.rdate, 5, 7)}&nbsp;月&nbsp;&nbsp;${fn:substring(t_present.rdate, 8, 10)}&nbsp;日&nbsp;&nbsp;
                </div>
               
            </td>
        </tr>
    </table>
    <table class="wordInfo" align="center" style="width: 90%;border: 0px;margin-top: 10px" >
        <tr>
            <td style="font-size:25px;font-family: 仿宋;text-align: center;border: 0px;border-bottom: 1px dashed dimgray"></td>
        </tr>
    </table>
    <table class="wordInfo" align="center" style="width: 90%;border:1px solid ;margin-top: 50px;">
        <tr>
            <td width="100%">
                <div style="width: 100%;height: 60px;padding-top:25px;text-align:center;">
                    <span style="font-size:25px;font-family:华文楷体;letter-spacing:5px;">中国共产党组织关系证明信</span>
                </div>
                <div style="width:100%;height: 25px;padding-top:5px;text-align:right;margin-top:5px;font-size:18px;">
                  &nbsp;&nbsp;&nbsp;&nbsp;第${t_present.no}号&nbsp;&nbsp;
                </div>
                <div style="width:100%;padding:5px;margin-top: 5px;margin-left: 40px;" class="floats" >
                    <div style="width: 25%;text-align:center;font-size:20px;" class="divText">${t_present.punit}</div>
                    <div>：</div>
                </div>
                <div style="width: 100%;padding:5px; letter-spacing:3px;" class="floats" >
                    <div style="font-size:20px;margin-left: 80px;">现介绍</div>
                    <div style="width:22%;text-align:center;font-size:20px;" class="divText">${t_present.pname}</div>
                    <div style="font-size:20px;" >同志系中共
                    <c:if test="${t_present.type == 1}">预备</c:if><c:if test="${t_present.type == 2}">正式</c:if>
                                                                     党员</div>
                </div>
                <div style="width:100%;height: 20px;padding:5px;padding-left: 2px;text-align:left;font-size:20px;" class="floats">
                    <div style="font-size:20px;margin-left: 40px;">由</div>
                    <div style="width: 24%;text-align: center;font-size:20px;" class="divText">${t_present.rfrom}</div>
                    <div style="font-size:20px;">去</div>
                    <div style="width: 24%;text-align: center;font-size:20px;" class="divText"> ${t_present.rto}</div>
                    <div style="font-size:20px;">工作（学</div>
                </div>
                <div style="width:100%;height: 20px;padding:5px;padding-left: 2px;text-align:left;font-size:20px;" class="floats">
                    <div style="font-size:20px;margin-left: 40px;">习），特此证明。</div>
                   
                </div>
                <div style="width:100%;height: 20px;padding:5px;padding-left: 2px;text-align:left;font-size:20px;" class="floats">
                    <div style="width:30%;padding-left: 90px;font-size:20px;letter-spacing:2px;">中国共产党</div>
                    <div style="width:30%;padding-left: 90px;font-size:20px;letter-spacing:2px;">委员会</div>
                </div>
                <div style="width: 100%;height: 40px;padding:15px;text-align:right;font-size: 20px;margin-top:50px;margin-left:30px;">
                    ${fn:substring(t_present.rdate, 0, 4)}&nbsp;年&nbsp;&nbsp;${fn:substring(t_present.rdate, 5, 7)}&nbsp;月&nbsp;&nbsp;${fn:substring(t_present.rdate, 8, 10)}&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
            </td>
        </tr>
    </table>


</form>
</body>
</html>
