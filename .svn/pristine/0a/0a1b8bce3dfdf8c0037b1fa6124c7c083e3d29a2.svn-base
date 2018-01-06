<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/include/inc.jsp" %>
<div class="bjui-pageContent">
    <form method="post" action="Main/Area/add" class="pageForm" data-toggle="validate">
        <input type="hidden" name="t_Area.pid" value="${areaId}"/>
        <input type="hidden" name="t_Area.level" value="${level }"/>
        <table class="table table-condensed table-hover">
            <tr>
                <td>
                    <label for="j_name" class="control-label x85">区划名称：</label>
                    <input type="text" name="t_Area.name" id="j_name" maxlength="20" size="30" data-rule="required">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="j_xh" class="control-label x85">区划排序：</label>
                    <input type="text" name="t_Area.xh" id="j_xh" maxlength="20" size="10" data-rule="digits">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="j_code" class="control-label x85">车牌编码：</label>
                    <input type="text" name="t_Area.code" id="j_code" maxlength="20" size="10">
                </td>
            </tr>
            <tr>
                <td>
                    <label for="j_status" class="control-label x85">启用状态：</label>
                    <select name="t_Area.status" id="j_status" data-toggle="selectpicker" class="show-tick" style="display: none;">
                        <option value="1" selected>已启用</option>
                        <option value="0">未启用</option>
                    </select>
                </td>
            </tr>
        </table>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li>
            <button type="button" class="btn-close" data-icon="close">关闭</button>
        </li>
        <li>
            <button type="submit" class="btn-default" data-icon="save">保存</button>
        </li>
    </ul>
</div>
