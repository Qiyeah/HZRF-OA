package com.oa.controller.export;

import java.util.Collection;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.oa.model.approve.T_Leave_Stat;
import com.oa.util.ExportExcel;

/**
 * 请休假统计导出
 */
@SuppressWarnings("rawtypes")
public class ExportLeaveStatistics extends ExportExcel {

    @SuppressWarnings("unchecked")
    @Override
    protected void setContent(Collection dataSet, String pattern, HSSFWorkbook workbook, HSSFSheet sheet,
	    HSSFCellStyle style2, HSSFPatriarch patriarch) {
	HSSFRow row;
	Iterator<T_Leave_Stat> it = dataSet.iterator();
	int index = 1;

	while (it.hasNext()) {
	    index++;
	    row = sheet.createRow(index);
	    T_Leave_Stat o = it.next();
	    String textValue;

	    textValue = o.getInt("year").toString();
	    setContentCell(workbook, style2, row, textValue, 0);

	    textValue = o.getStr("uname");
	    setContentCell(workbook, style2, row, textValue, 1);

	    textValue = o.getStr("dpname");
	    setContentCell(workbook, style2, row, textValue, 2);

	    textValue = o.getInt("nxj").toString();
	    setContentCell(workbook, style2, row, textValue, 3);

	    textValue = o.getInt("sij").toString();
	    setContentCell(workbook, style2, row, textValue, 4);

	    textValue = o.getInt("bij").toString();
	    setContentCell(workbook, style2, row, textValue, 5);

	    textValue = o.getInt("jhj").toString();
	    setContentCell(workbook, style2, row, textValue, 6);

	    textValue = o.getInt("jsj").toString();
	    setContentCell(workbook, style2, row, textValue, 7);

	    textValue = o.getInt("tqj").toString();
	    setContentCell(workbook, style2, row, textValue, 8);

	    textValue = o.getInt("days").toString();
	    setContentCell(workbook, style2, row, textValue, 9);

	}
    }

}
