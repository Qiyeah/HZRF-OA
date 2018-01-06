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
            
            text-align:center;
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
    <div id="bar" class="formBar1" style="_top:expression(offsetParent.scrollTop+0);width: 850px">
        <ul>
            <li><a class="button" onclick="PrintDocument()"><span>打印</span></a></li>
        </ul>
        
    </div>
    <div style="height: 35px;"></div>
    <div  style="width: 95%;height: 20px;padding:2px;margin-top:0px;margin-bottom:10px;text-align:center;">
        <span class="noprint" style="font-size:25px;font-family: 黑体">中国共产党党员组织关系介绍信</span>
    </div>
    <table class="wordInfo" align="center" style="width: 98%;border:0px">
        <tr style="border:0px">
            <td class="noprint" width="10%" align="center" style="border:0px;padding:2px;line-height:26px;font-size:24px;font-family: 华文中宋;font-weight:bold;">党<br/>员<br/>介<br/>绍<br/>信<br/>存<br/>根</td>
            <td width="84%" style="border:0px;">
                <div style="width: 100%;height: 25px;padding-right:25px;text-align:right;margin-top:18px;">
                    <span style="font-size:20px; margin-left:20px"><span class="noprint" style="font-size: 20px;">第</span>&nbsp;&nbsp;${t_relation.no}&nbsp;&nbsp;<span class="noprint" style="font-size: 20px;">号</span>&nbsp;</span>
                </div>
                <div style="width: 100%;padding:10px;margin-top:10px;" class="floats" >
                    <div style="width:40%;text-align:center;margin-left: 70px;" class="divText">${t_relation.name}</div>
                    
                     <div>
                        <span class="noprint" style="font-size: 20px;">同志系<c:if test="${t_relation.type == 1}">预备</c:if><c:if test="${t_relation.type == 2}">正式</c:if>党员，</span>
                     </div>
                </div>
                <div style="width: 100%;height: 25px;margin-top:10px;padding-left: 2px;text-align:left;" class="floats">
                    <div style="width: 24%;"><span class="noprint" style="font-size: 20px;">组织关系由</span></div>
                    <div style="width: 28%;text-align: center" class="divText">${t_relation.rfrom}</div>
                    <div style="width: 6%;"><span class="noprint" style="font-size: 20px;">转到</span></div>
                    <div style="width: 34%;text-align: center" class="divText"> ${t_relation.rto}</div>
                    <div class="noprint">。</div>
                </div>
                <div style="width: 100%;height: 30px;padding:2px;margin-top:25px;text-align:right;font-size: 20px;margin-right:60px;">
                    ${year}&nbsp;<span class="noprint" style="font-size: 20px;">年</span>&nbsp;&nbsp;${month}&nbsp;<span class="noprint" style="font-size: 20px;">月</span>&nbsp;&nbsp;${day}&nbsp;<span class="noprint" style="font-size: 20px;">日</span>&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
            </td>
            <td width="6%" class="noprint" align="center" style="font-family: 仿宋;font-size:16px;border:0px;">&nbsp;</td>
        </tr>
    </table>
    <table  align="center" style="width:98%;border: 0px;" >
        <tr height="20px">
            <td class="noprint" style="font-size:16px;font-family: 仿宋;text-align: center;border: 0px;border-bottom: 1px dashed dimgray">（贴回执联处）</td>
            <td style="5px">&nbsp;</td>
        </tr>
        <tr height="20px">
            <td class="noprint" style="font-size:16px;font-family: 仿宋; padding-left:500px;text-align: right;border: 0px;">（加盖骑缝章）</td>
            <td style="5px">&nbsp;</td>
        </tr>
    </table>

    
    <div style="width: 95%;height: 50px;padding:10px;margin-top:5px;text-align:center;">
        <span class="noprint" style="font-size:30px;font-family: 华文中宋;font-weight:bold;">中国共产党党员组织关系介绍信</span>
    </div>
    <table class="wordInfo" align="center" style="width:98%;border:0px ">
        <tr style="border:0px; ">
            <td style="width:94%;border:0px;">
                <div style="width:100%;height: 20px;padding-right:25px;text-align:right;margin-top:15px;">
                    <span style="font-size:20px;"><span class="noprint" style="font-size: 20px;">第</span>&nbsp;&nbsp;${t_relation.no}&nbsp;&nbsp;<span class="noprint" style="font-size: 20px;">号</span>&nbsp;&nbsp;</span>
                </div>
                <div style="width:100%;padding:5px;margin-top: 0px;" class="floats" >
                    <div style="width: 45%;text-align:left;" class="divText">${t_relation.unit}</div>
                    <div class="noprint">：</div>
                </div>
                <div style="width:100%;height: 20px;padding:4px;" class="floats">
                    <div style="width:28%;margin-left: 40px;" class="begin divText">${t_relation.name}</div>
                     
                    <div style="width:22%;"><span  class="noprint" style="font-size: 20px;">同志，<c:if test="${t_relation.sex == 1}">男</c:if><c:if test="${t_relation.sex == 2}">女</c:if>，</span></div>
                    <div style="width:12%" class="divText">${t_relation.age}</div>
                    <div ><span class="noprint" style="font-size: 20px;">岁，</span></div>
                    <div style="width:20%" class="divText">${t_relation.nation}</div>
                    <div ><span class="noprint" style="font-size: 20px;">族，</span></div>
                </div>
                <div style="width:100%;padding:10px;padding-left: 2px;" class="floats" >
                    
                    <div style="width: 52%;" ><span class="noprint" style="font-size: 20px;">
                        系中共<c:if test="${t_relation.type == 1}">预备</c:if><c:if test="${t_relation.type == 2}">正式</c:if>党员，身份证号码</span></div>
                    <div style="width: 36%;text-align:center;" class="divText">${t_relation.idcard}</div>
                    <div class="noprint">，</div>
                </div>
                <div style="width:100%;height: 20px;padding:5px;padding-left: 2px;text-align:left;" class="floats">
                    <div style="width: 2%;"><span class="noprint" style="font-size: 20px;">由</span></div>
                    <div style="width: 42%;text-align: center" class="divText">${t_relation.rfrom}</div>
                    <div style="width: 2%;"><span class="noprint" style="font-size: 20px;">去</span></div>
                    <div style="width: 42%;text-align: center" class="divText"> ${t_relation.rto}</div>
                    <div style="width: 2%;" class="noprint">，</div>
                </div>
                <div style="width:100%;height: 20px;padding:5px;padding-left: 2px;text-align:left;" class="floats">
                    <div style="width: 52%;"><span class="noprint" style="font-size: 20px;">请转接组织关系。该同志党费已交到</span></div>
                    <div style="width: 8%;text-align: center" class="divText">${t_relation.paid_year}</div>
                    <div style="width: 6%;"><span class="noprint" style="font-size: 20px;">年</span></div>
                    <div style="width: 7%;text-align: center" class="divText"> ${t_relation.paid_month}</div>
                    <div style="width: 2%;"><span class="noprint" style="font-size: 20px;">月。</span></div>
                </div>
                <div style="width:100%;height: 20px;padding:5px;margin-top:8px;text-align:left;font-size:20px;padding-left: 0px;" class="floats">
                    <div style="width: 12%;"><span class="noprint" style="font-size: 20px;">（有效期  </span></div>
                    <div style="width: 7%;" style="margin-right:3px;"> ${t_relation.valid} </div> 
                    <div style="width: 12%;"><span class="noprint" style="font-size: 20px;">天）</span></div> 
                </div>
                <div  style="width:100%;height: 25px;padding:5px;text-align:right;font-size:20px;padding-left: 0px;">
                <span class="noprint" style="font-size: 20px;">
                    （盖章）&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </span>
                </div>
                <div style="width:100%;height: 25px;padding:2px;text-align:right;font-size:20px;margin-right:60px;">
                    ${year}&nbsp;<span class="noprint" style="font-size: 20px;">年</span>&nbsp;&nbsp;${month}&nbsp;<span class="noprint" style="font-size: 20px;">月</span>&nbsp;&nbsp;${day}&nbsp;<span class="noprint" style="font-size: 20px;">日</span>&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
                <div style="width:100%;height: 30px;padding:5px;text-align:left;font-size:20px;padding-left: 2px;margin-top: 5px;" class="floats">
                	<div style="width: 40%;">
                    <span class="noprint" style="font-size:20px">党员联系电话或其他联系方式：</span>
                    </div>
                    <div >
                    <span style="font-size:20px">${t_relation.u_phone}</span>
                    </div>
                </div>
                <div style="width:100%;height: 30px;padding:5px;text-align:left;font-size:20px;padding-left: 2px;margin-top: 0px;" class="floats">
                	<div style="width: 40%;">
                    <span class="noprint" style="font-size:20px">党员原所在基层党委通讯地址：</span>
                    </div>
                    <div >
                    <span style="font-size:20px">${t_relation.u_address}</span>
                    </div>
                </div>
                
                <div style="width: 100%;height:25px;margin-top: 5px;" class="floats" >
                    <div style="width: 15%;text-align:left;padding-left: 2px;"><span class="noprint" style="font-size:20px">联系电话：</span></div>
                    <div style="width: 20%;text-align:left;">${t_relation.phone}</div>
                    <div style="width: 12%;text-align:left;"><span class="noprint" style="font-size:20px">传真：</span></div>
                    <div style="width: 20%;text-align:left;">${t_relation.fax}</div>
                    <div style="width: 10%;text-align:left;"><span class="noprint" style="font-size:20px">邮编：</span></div>
                    <div style="text-align:left;">${t_relation.zipcode}</div>
                </div>
            </td>
            <td class="noprint" width="6%" align="center" style="font-family: 仿宋;font-size:16px;border:0px;">&nbsp;</td>
        </tr>
    </table>
    <table class="noprint" align="center" style="width: 98%;border: 0px;" >
        <tr>
            <td style="font-size:12px;font-family: 仿宋;text-align: center;border: 0px;border-bottom: 1px dashed dimgray">&nbsp;</td>
        </tr>
    </table>
    <div class="noprint" style="width: 95%;height: 20px;padding:5px;text-align:center;margin-top: 10px;">
        <span style="font-size:25px; margin-left:35px;font-family: 华文中宋;font-weight:bold;">中国共产党党员组织关系介绍信回执联</span>
    </div>
    <table class="wordInfo noprint" align="center" style="width: 98%;border:0px ">
        <tr>
            <td style="width:94%;border:0px">
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
            <td width="6%" align="center"  style="font-family: 仿宋;font-size:16px;border:0px">&nbsp;</td>
        </tr>
    </table>
    
    <table class="noprint" class="wordInfo" align="center" style="width: 98%;border: 0px;" >
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
