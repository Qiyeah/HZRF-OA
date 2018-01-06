package com.oa.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

@SuppressWarnings("deprecation")
public class ExportExcel<T> {
    /**
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
     *
     * @param title
     *            表格标题名
     * @param headers
     *            表格属性列名数组
     * @param dataSet
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out
     *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern
     *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
     */
    public static final String FILLER = "-----";
    public static final short TITLE_FONT_SIZE = 16;
    public static final short HEADER_FONT_SIZE = 12;
    public static final short DEFAULT_COLUMN_WIDTH = 15;// 字节数
    public static final int DECIMAL_PLACES = 2;

    @SuppressWarnings({ "unused" })
    public void exportExcel(String title, String[] headers, Collection<T> dataSet, OutputStream out, String pattern) {
	// 声明一个工作薄
	HSSFWorkbook workbook = new HSSFWorkbook();
	// 生成一个表格
	HSSFSheet sheet = workbook.createSheet(title);
	// 设置表格默认列宽度为15个字节
	sheet.setDefaultColumnWidth(DEFAULT_COLUMN_WIDTH);
	// 生成标题样式
	HSSFCellStyle titleStyle = workbook.createCellStyle();
	// 设置样式
	setTitleStyle(titleStyle);
	// 生成标题字体
	HSSFFont titleFont = workbook.createFont();
	setTitleFont(titleFont);
	// 把字体应用到当前的样式
	titleStyle.setFont(titleFont);
	// 生成表头样式
	HSSFCellStyle headerStyle = workbook.createCellStyle();
	// 设置样式
	setHeaderStyle(headerStyle);
	// 生成表头字体
	HSSFFont headerFont = workbook.createFont();
	setHeaderFont(headerFont);
	// 把字体应用到当前的样式
	headerStyle.setFont(headerFont);
	// 生成并设置内容样式
	HSSFCellStyle contentStyle = workbook.createCellStyle();
	setContentStyle(contentStyle);
	// 生成内容字体
	HSSFFont contentFont = workbook.createFont();
	setContentFont(contentFont);
	// 把字体应用到当前的样式
	contentStyle.setFont(contentFont);

	// 声明一个画图的顶级管理器
	HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
	// 定义注释的大小和位置,详见文档
	HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
	// 设置注释内容
	/* comment.setString(new HSSFRichTextString("智联科技")); */
	// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
	/* comment.setAuthor("lyw"); */
	// 产生表格标题行
	HSSFRow titleRow = sheet.createRow(0);
	HSSFCell cell = titleRow.createCell(0);
	mergeCells(title, sheet, titleStyle, cell, 0, 0, 0, headers.length - 1);
	HSSFRichTextString text;

	// 产生表格表头
	setHeader(headers, sheet, headerStyle);

	// 遍历集合数据，产生数据行
	setContent(dataSet, pattern, workbook, sheet, contentStyle, patriarch);
	try {
	    workbook.write(out);
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    protected void setHeader(String[] headers, HSSFSheet sheet, HSSFCellStyle headerStyle) {
	HSSFCell cell;
	HSSFRichTextString text;
	HSSFRow headerRow = sheet.createRow(1);
	for (short i = 0; i < headers.length; i++) {
	    cell = headerRow.createCell(i);
	    cell.setCellStyle(headerStyle);
	    text = new HSSFRichTextString(headers[i]);
	    cell.setCellValue(text);
	}
    }

    protected void mergeCells(String content, HSSFSheet sheet, HSSFCellStyle cellStyle, HSSFCell cell, int startRow,
	    int endRow, int startCol, int endCol) {
	/**
	 * 合并单元格 第一个参数：第一个单元格的行数（从0开始） 第二个参数：第二个单元格的行数（从0开始）
	 * 第三个参数：第一个单元格的列数（从0开始） 第四个参数：第二个单元格的列数（从0开始）
	 */
	CellRangeAddress range = new CellRangeAddress(startRow, endRow, startCol, endCol);
	sheet.addMergedRegion(range);
	cell.setCellStyle(cellStyle);
	HSSFRichTextString text = new HSSFRichTextString(content);
	cell.setCellValue(text);
    }

    protected void setTitleFont(HSSFFont font) {
	font.setColor(HSSFColor.BLACK.index);
	font.setFontHeightInPoints(TITLE_FONT_SIZE);
	font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    }

    protected void setTitleStyle(HSSFCellStyle style) {
	// style.setFillForegroundColor(HSSFColor.ORANGE.index);
	style.setFillForegroundColor(HSSFColor.WHITE.index);
	style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    }

    protected void setHeaderFont(HSSFFont font) {
	font.setColor(HSSFColor.BLACK.index);
	font.setFontHeightInPoints(HEADER_FONT_SIZE);
	font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    }

    protected void setHeaderStyle(HSSFCellStyle style) {
	// style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
	style.setFillForegroundColor(HSSFColor.WHITE.index);
	style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    }

    protected void setContentFont(HSSFFont font) {
	font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
    }

    protected void setContentStyle(HSSFCellStyle style) {
	style.setFillForegroundColor(HSSFColor.WHITE.index);
	style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
    }

    protected void setContentCell(HSSFWorkbook workbook, HSSFCellStyle style, HSSFRow row, String textValue, int col) {
	HSSFCell cell = row.createCell(col);
	cell.setCellStyle(style);
	HSSFRichTextString richString = new HSSFRichTextString(textValue);
	HSSFFont font3 = workbook.createFont();
	font3.setColor(HSSFColor.BLACK.index);
	richString.applyFont(font3);
	cell.setCellValue(richString);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected void setContent(Collection<T> dataset, String pattern, HSSFWorkbook workbook, HSSFSheet sheet,
	    HSSFCellStyle style, HSSFPatriarch patriarch) {
	HSSFRow row;
	Iterator<T> it = dataset.iterator();
	int index = 0;
	while (it.hasNext()) {
	    index++;
	    row = sheet.createRow(index);
	    T t = (T) it.next();
	    // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
	    Field[] fields = t.getClass().getDeclaredFields();
	    for (short i = 0; i < fields.length; i++) {
		HSSFCell cell = row.createCell(i);
		cell.setCellStyle(style);
		Field field = fields[i];
		String fieldName = field.getName();
		String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		try {
		    Class tCls = t.getClass();
		    Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
		    Object value = getMethod.invoke(t, new Object[] {});
		    // 判断值的类型后进行强制类型转换
		    String textValue = null;
		    if (value instanceof Boolean) {
			boolean bValue = (Boolean) value;
			textValue = "男";
			if (!bValue) {
			    textValue = "女";
			}
		    } else if (value instanceof Date) {
			Date date = (Date) value;
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			textValue = sdf.format(date);
		    } else if (value instanceof byte[]) {
			// 有图片时，设置行高为60px;
			row.setHeightInPoints(60);
			// 设置图片所在列宽度为80px,注意这里单位的一个换算
			sheet.setColumnWidth(i, (short) (35.7 * 80));
			// sheet.autoSizeColumn(i);
			byte[] bsValue = (byte[]) value;
			HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, index, (short) 6,
				index);
			anchor.setAnchorType(2);
			patriarch.createPicture(anchor, workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
		    } else {
			// 其它数据类型都当作字符串简单处理
			textValue = value + "";
		    }
		    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
		    if (textValue != null) {
			Pattern p = Pattern.compile("^//d+(//.//d+)?$");
			Matcher matcher = p.matcher(textValue);
			if (matcher.matches()) {
			    // 是数字当作double处理
			    cell.setCellValue(Double.parseDouble(textValue));
			} else {
			    HSSFRichTextString richString = new HSSFRichTextString(textValue);
			    HSSFFont font3 = workbook.createFont();
			    font3.setColor(HSSFColor.BLACK.index);
			    richString.applyFont(font3);
			    cell.setCellValue(richString);
			}
		    }
		} catch (SecurityException e) {
		    e.printStackTrace();
		} catch (NoSuchMethodException e) {
		    e.printStackTrace();
		} catch (IllegalArgumentException e) {
		    e.printStackTrace();
		} catch (IllegalAccessException e) {
		    e.printStackTrace();
		} catch (InvocationTargetException e) {
		    e.printStackTrace();
		} finally {
		    // 清理资源
		}
	    }
	}
    }
}
