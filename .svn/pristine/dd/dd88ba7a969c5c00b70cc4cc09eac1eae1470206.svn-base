package com.oa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.zhilian.config.Constant;

/**
 * 根据模板文件导出excel 此类所做的功能是读入模板及向输出流写入导出的excel
 *
 * 使用此类的好处是不用通过代码设置单元格的格式，直接通过模板设置格式
 *
 * 通过继承此类，子类可以重载setContent方法，按需求填充数据
 *
 * @Author Andersen mail: yawen199@163.com Date: 15-5-21 Time: 上午11:07
 */
public class ExportExcelByTemplate<T> {

    private String rootPath = Constant.PATH_WEBROOT + Constant.PATH_TEMPLATE; // 模板根目录路径

    public void exportExcel(Collection<T> dataSet, OutputStream out, String templateName, String[] sheetNames,
	    int startRowIndex) {
	// 读取模板文件
	File fi = new File(rootPath + templateName);
	POIFSFileSystem fs = null;
	try {
	    fs = new POIFSFileSystem(new FileInputStream(fi));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	// 读取excel模板
	HSSFWorkbook workbook = null;
	try {
	    workbook = new HSSFWorkbook(fs);
	} catch (IOException e) {
	    e.printStackTrace();
	}

	// 遍历集合数据，产生数据行
	setContent(workbook, dataSet, sheetNames, startRowIndex);
	try {
	    workbook.write(out);
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    /**
     * 设置文件内容
     *
     * @param workbook
     *            excel模板
     * @param dataSet
     *            集合数据
     * @param sheetNames
     *            sheet名数组
     * @param startRowIndex
     *            开始写入集合数据行数
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected void setContent(HSSFWorkbook workbook, Collection<T> dataSet, String[] sheetNames, int startRowIndex) {
	if (sheetNames.length <= 0 || startRowIndex < 0) {
	    return;
	}
	// 读取模板内sheet页的内容
	HSSFSheet sheet = workbook.getSheet(sheetNames[0]);
	HSSFRow row;
	Iterator<T> it = dataSet.iterator();
	while (it.hasNext()) {
	    row = sheet.getRow(startRowIndex);
	    T t = (T) it.next();
	    // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
	    Field[] fields = t.getClass().getDeclaredFields();
	    for (int i = 0; i < fields.length; i++) {
		HSSFCell cell = row.getCell(i);
		Field field = fields[i];
		String fieldName = field.getName();
		String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		Class tCls = t.getClass();
		Method getMethod = null;
		Object value = null;
		try {
		    getMethod = tCls.getMethod(getMethodName, new Class[] {});
		    value = getMethod.invoke(t, new Object[] {});
		} catch (NoSuchMethodException e) {
		    e.printStackTrace();
		} catch (InvocationTargetException e) {
		    e.printStackTrace();
		} catch (IllegalAccessException e) {
		    e.printStackTrace();
		}

		cell.setCellValue(value.toString());
		startRowIndex++;
	    }
	}
    }

}
