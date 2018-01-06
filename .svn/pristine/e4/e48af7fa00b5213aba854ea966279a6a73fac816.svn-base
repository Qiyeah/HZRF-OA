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
        
        table.wordInfo td div{
            font-family: 仿宋;
            font-size: 20px;
            min-height:22px;
            text-align: center;
        }

        .floats div{
            float: left;
            height:25px;
            padding-top:3px;

        }
        .divText{
            border: 0px;
            border-bottom:1px solid;
            text-align:center;
        }
        
        @media print {
			.noprint {
				display: none
			}
		}

    </style>
    <script type="text/javascript">
        function PrintDocument() {
            document.all.bar.style.display = "none";
            //var DocForm = document.forms[0];
            //var mCount = DocForm.SignatureControl.PrintDocument(false, 1, 2); // 打印控制窗体
            window.print();
            document.all.bar.style.display = "";
        }
    </script>
</head>
<body onload="document.forms[0].SignatureControl.ShowSignature('${la.pid}');" style="background-color: #ffffff;">
<form id="DocForm">
    <input type="hidden" name="DocumentID" value="${la.pid}">
    <div id="bar" class="formBar1 noprint" style="_top:expression(offsetParent.scrollTop+0);width: 850px">
        <ul>
            <li><a class="button" onclick="PrintDocument()"><span>打印</span></a></li>
        </ul>
        
    </div>
    <div style="height: 35px;"></div>
    <div  class="noprint" style="width: 95%;height: 20px;padding:2px;margin-top:0px;margin-bottom:10px;text-align:center;">
        <span style="font-size:25px;font-family: 黑体">中国共产党党员组织关系介绍信</span>
    </div>
    <table class="wordInfo" align="center" style="width: 98%;border:1px solid black;">
        <tr>
            <td width="10%" align="center" style="padding:2px;line-height:26px;font-size:24px;font-family: 华文中宋;font-weight:bold;">党<br/>员<br/>介<br/>绍<br/>信<br/>存<br/>根</td>
            <td width="84%">
                <div style="width: 100%;height: 25px;padding-left:5px;text-align:right;margin-top:2px;">
                    <span style="font-size:20px; margin-left:20px">第&nbsp;&nbsp;${t_relation.no}&nbsp;&nbsp;号&nbsp;</span>
                </div>
                <div style="width: 100%;padding:10px;margin-top:20px;" class="floats" >
                    <div style="width:40%;text-align:center;margin-left: 50px;" class="divText">${t_relation.name}</div>
                    <div>
                        同志系中共<c:if test="${t_relation.type == 1}">预备</c:if><c:if test="${t_relation.type == 2}">正式</c:if>党员，</div>
                </div>
                <div style="width: 100%;height: 25px;padding:5px;padding-left: 2px;text-align:left;" class="floats">
                    <div>组织关系由</div>
                    <div style="width: 34%;text-align: center" class="divText">${t_relation.rfrom}</div>
                    <div>转到</div>
                    <div style="width: 34%;text-align: center" class="divText"> ${t_relation.rto}</div>
                    <div>。</div>
                </div>
                <div style="width: 100%;height: 30px;padding:2px;margin-top:10px;text-align:right;font-size: 20px;">
                    ${year}年&nbsp;&nbsp;${month}月&nbsp;&nbsp;${day}日&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
            </td>
            <td width="6%" align="center" style="font-family: 仿宋;font-size:16px;">第<br/>一<br/>联</td>
        </tr>
    </table>
    <table  align="center" style="width:98%;border: 0px;" >
        <tr height="20px">
            <td style="font-size:16px;font-family: 仿宋;text-align: center;border: 0px;border-bottom: 1px dashed dimgray">（贴回执联处）</td>
        </tr>
        <tr height="20px">
            <td style="font-size:16px;font-family: 仿宋; padding-left:500px;text-align: right;border: 0px;">（加盖骑缝章）</td>
        </tr>
    </table>

    
    <div style="width: 95%;height: 30px;padding:10px;margin-top:5px;text-align:center;">
        <span style="font-size:30px;font-family: 华文中宋;font-weight:bold;">中国共产党党员组织关系介绍信</span>
    </div>
    <table class="wordInfo" align="center" style="width:98%;border:1px solid ">
        <tr>
            <td style="width:94%">
                <div style="width:100%;height: 20px;padding:5px;text-align:right;margin-top:5px;">
                    <span style="font-size:20px;">第&nbsp;&nbsp;${t_relation.no}&nbsp;&nbsp;号&nbsp;</span>
                </div>
                <div style="width:100%;padding:5px;margin-top: 5px;" class="floats" >
                    <div style="width: 45%;text-align:center;" class="divText">${t_relation.unit}</div>
                    <div>：</div>
                </div>
                <div style="width:100%;height: 20px;padding:2px;" class="floats">
                    <div style="width:28%;margin-left: 30px;" class="begin divText">${t_relation.name}</div>
                    <div>同志，<c:if test="${t_relation.sex == 1}">男</c:if><c:if test="${t_relation.sex == 2}">女</c:if>，</div>
                    <div style="width:10%" class="divText">${t_relation.age}</div>
                    <div>岁，</div>
                    <div style="width:25%" class="divText">${t_relation.nation}</div>
                    <div>族，</div>
                </div>
                <div style="width:100%;padding:5px;padding-left: 2px;" class="floats" >
                    <div>
                        系中共<c:if test="${t_relation.type == 1}">预备</c:if><c:if test="${t_relation.type == 2}">正式</c:if>党员，身份证号码</div>
                    <div style="width: 52%;text-align:center;" class="divText">${t_relation.idcard}</div>
                    <div>，</div>
                </div>
                <div style="width:100%;height: 20px;padding:5px;padding-left: 2px;text-align:left;" class="floats">
                    <div>由</div>
                    <div style="width: 45%;text-align: center" class="divText">${t_relation.rfrom}</div>
                    <div>去</div>
                    <div style="width: 44%;text-align: center" class="divText"> ${t_relation.rto}</div>
                    <div>，</div>
                </div>
                <div style="width:100%;height: 20px;padding:5px;padding-left: 2px;text-align:left;" class="floats">
                    <div>请转接组织关系。该同志党费已交到</div>
                    <div style="width: 10%;text-align: center" class="divText">${t_relation.paid_year}</div>
                    <div>年</div>
                    <div style="width: 7%;text-align: center" class="divText"> ${t_relation.paid_month}</div>
                    <div>月。</div>
                </div>
                <div style="width:100%;height: 20px;padding:5px;margin-top:5px;text-align:left;font-size:20px;padding-left: 0px;">
                    （有效期  ${t_relation.valid}  天）
                </div>
                <div style="width:100%;height: 25px;padding:5px;text-align:right;font-size:20px;padding-left: 0px;">
                    （盖章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                <div style="width:100%;height: 25px;padding:5px;text-align:right;font-size:20px;padding-left: 0px;">
                    ${year}年&nbsp;&nbsp;${month}月&nbsp;&nbsp;${day}日&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                <div style="width:100%;height: 30px;padding:5px;text-align:left;font-size:20px;padding-left: 2px;margin-top: 10px;">
                    党员联系电话或其他联系方式：<span style="font-size:20px">${t_relation.u_phone}</span>
                </div>
                <div style="width:100%;height: 30px;padding:5px;text-align:left;font-size:20px;padding-left: 2px;">
                    党员原所在基层党委通讯地址：<span style="font-size:20px">${t_relation.u_address}</span>
                </div>
                <div style="width: 100%;height:25px;margin-top: 5px;" class="floats" >
                    <div style="width: 20%;text-align:left;padding-left: 2px;">联系电话：</div>
                    <div style="width: 15%;">${t_relation.phone}</div>
                    <div style="width: 16%;text-align:left;">传真：</div>
                    <div style="width: 16%;">${t_relation.fax}</div>
                    <div style="width: 16%;text-align:left;">邮编：</div>
                    <div style="">${t_relation.zipcode}</div>
                </div>
            </td>
            <td width="6%" align="center" style="font-family: 仿宋;font-size:16px;">第<br/>二<br/>联</td>
        </tr>
    </table>
    <table align="center" style="width: 98%;border: 0px;" >
        <tr>
            <td style="font-size:12px;font-family: 仿宋;text-align: center;border: 0px;border-bottom: 1px dashed dimgray">&nbsp;</td>
        </tr>
    </table>
    <div style="width: 95%;height: 20px;padding:5px;text-align:center;margin-top: 10px;">
        <span style="font-size:25px; margin-left:35px;font-family: 华文中宋;font-weight:bold;">中国共产党党员组织关系介绍信回执联</span>
    </div>
    <table class="wordInfo" align="center" style="width: 98%;border:1px solid ">
        <tr>
            <td style="width:94%">
                <div style="width:100%;padding:10px;height: 20px;" class="floats" >
                    <div style="width: 45%;text-align:center;" class="divText"></div>
                    <div>：</div>
                </div>
                <div style="width:100%;padding:5px;height: 20px;" class="floats" >
                    <div style="width: 25%;text-align:center;margin-left:20px;" class="divText"></div>
                    <div>同志的党员组织关系已转达我处，特此回复。</div>
                </div>
                <div style="width:100%;height: 20px;padding:5px;text-align:right;font-size:20px;padding-left: 0px;">
                    （盖章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                <div style="width:100%;height: 20px;padding:5px;text-align:right;font-size:20px;padding-left: 0px;">
                    年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                <div style="width: 100%;height:20px;margin-top: 5px;" class="floats" >
                    <div style="width: 20%;text-align:left;padding-left: 4px;">经办人：</div>
                    <div style="width: 20%;"></div>
                    <div style="width: 30%;text-align:left;">联系电话：</div>
                    <div style=""></div>
                </div>
            </td>
            <td width="6%" align="center"  style="font-family: 仿宋;font-size:16px;"">第<br/>三<br/>联</td>
        </tr>
    </table>
    
    <table class="wordInfo" align="center" style="width: 98%;border: 0px;" >
        <tr>
            <td style="font-size:16px;font-family: 仿宋;text-align: left;border: 0px;">注：回执联有接受党组织关系的基层党委在接收党员后一个月内邮件或传真至党员原所</td>
        </tr>
        <tr>
            <td style="font-size:16px;font-family: 仿宋;border: 0px;">在基层党委。</td>
        </tr>
    </table>
</form>
</body>
</html>
