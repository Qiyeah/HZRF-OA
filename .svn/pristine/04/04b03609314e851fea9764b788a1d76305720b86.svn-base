package com.oa.controller.export;

import java.util.Collection;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.oa.model.approve.T_Personal;
import com.oa.util.ExportExcel;
import com.zhilian.model.T_Department;

/**
 * 通讯录导出
 */
@SuppressWarnings("rawtypes")
public class ExportPersonalTel extends ExportExcel {

    @SuppressWarnings("unchecked")
    @Override
    protected void setContent(Collection dataSet, String pattern, HSSFWorkbook workbook, HSSFSheet sheet,
	    HSSFCellStyle style2, HSSFPatriarch patriarch) {
	HSSFRow row;
	Iterator<T_Personal> it = dataSet.iterator();
	int index = 1;

	while (it.hasNext()) {
	    index++;
	    row = sheet.createRow(index);
	    T_Personal o = it.next();
	    String textValue;

	    textValue = o.getStr("uname");
	    setContentCell(workbook, style2, row, textValue, 0);

	    textValue = o.getStr("phone");
	    setContentCell(workbook, style2, row, textValue, 1);

	    textValue = o.getStr("mbphone");
	    setContentCell(workbook, style2, row, textValue, 2);

	    int deptId = o.getInt("d_id");
	    T_Department department = T_Department.dao.findById(deptId);
	    if (null != department) {
		textValue = department.getStr("fname");
	    } else {
		textValue = "";
	    }
	    setContentCell(workbook, style2, row, textValue, 3);

	    textValue = o.getStr("gradename");
	    setContentCell(workbook, style2, row, textValue, 4);

	    textValue = o.getStr("gradelevel");
	    setContentCell(workbook, style2, row, textValue, 5);
	}
    }

}
