package com.oa.controller.export;

import org.apache.poi.hssf.usermodel.*;

import com.oa.model.work.Attendance_Status;
import com.oa.util.ExportExcel;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15-2-2
 * Time: 下午18:08
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("rawtypes")
public class ExportAttenceStatistics extends ExportExcel {
    @SuppressWarnings("unchecked")
    @Override
    protected void setContent(Collection dataSet, String pattern, HSSFWorkbook workbook, HSSFSheet sheet, HSSFCellStyle style2, HSSFPatriarch patriarch) {
        HSSFRow row;
        Iterator<Attendance_Status> it = dataSet.iterator();
        int index = 1;

        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            Attendance_Status o = it.next();
            String textValue;

            textValue = null == o.get("name") ? null : o.get("name").toString();
            setContentCell(workbook, style2, row, textValue,0);
            // 科室
            textValue = null == o.get("deptName") ? null : o.get("deptName").toString();
            setContentCell(workbook, style2, row, textValue,1);
            // 迟到
            textValue = null == o.get("late") ? null : o.get("late").toString();
            setContentCell(workbook, style2, row, textValue,2);
            // 早退
            textValue = null == o.get("leave_early") ? null : o.get("leave_early").toString();
            setContentCell(workbook, style2, row, textValue,3);
            // 缺勤
            textValue = null == o.get("absence") ? null : o.get("absence").toString();
            setContentCell(workbook, style2, row, textValue,4);
        }
    }
}
