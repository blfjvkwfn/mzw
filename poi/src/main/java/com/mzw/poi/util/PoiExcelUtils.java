package com.mzw.poi.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取Excel 97~2003 xls格式 /2007~ xlsx格式
 * 
 * @author mengzhaowei
 */
public class PoiExcelUtils {
	private static Log log = LogFactory.getLog(PoiExcelUtils.class);

	/**
	 * 创建工作簿对象
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static final Workbook createWb(String filePath) throws IOException {
		if (StringUtils.isBlank(filePath)) {
			throw new IllegalArgumentException("参数错误!!!");
		}
		if (filePath.trim().toLowerCase().endsWith("xls")) {
			return new HSSFWorkbook(new FileInputStream(filePath));
		} else if (filePath.trim().toLowerCase().endsWith("xlsx")) {
			return new XSSFWorkbook(new FileInputStream(filePath));
		} else {
			throw new IllegalArgumentException("不支持除：xls/xlsx以外的文件格式!!!");
		}
	}
	public static final Workbook createWb(InputStream inputStream, String fileType) throws IOException {
		if (fileType.trim().toLowerCase().endsWith("xls")) {
			return new HSSFWorkbook(inputStream);
		} else if (fileType.trim().toLowerCase().endsWith("xlsx")) {
			return new XSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("不支持除：xls/xlsx以外的文件格式!!!");
		}
	}

	public static final Sheet getSheet(Workbook wb, String sheetName) {
		return wb.getSheet(sheetName);
	}

	public static final Sheet getSheet(Workbook wb, int index) {
		return wb.getSheetAt(index);
	}

	public static final List<Object[]> listFromSheet(Sheet sheet) {

//		int rowTotal = sheet.getPhysicalNumberOfRows();

		List<Object[]> list = new ArrayList<Object[]>();
		for (int r = sheet.getFirstRowNum(); r <= sheet.getLastRowNum(); r++) {
			Row row = sheet.getRow(r);
			if (row == null)
				continue;
			// 不能用row.getPhysicalNumberOfCells()，可能会有空cell导致索引溢出
			// 使用row.getLastCellNum()至少可以保证索引不溢出，但会有很多Null值，如果使用集合的话，就不说了
			Object[] cells = new Object[row.getLastCellNum()];
			for (int c = row.getFirstCellNum(); c <= row.getLastCellNum(); c++) {
				Cell cell = row.getCell(c);
				if (cell == null)
					continue;
				cells[c] = getValueFromCell(cell);
			}
			list.add(cells);
		}

		return list;
	}

	/**
	 * 获取单元格内文本信息
	 * 
	 * @param cell
	 * @return
	 */
	public static final String getValueFromCell(Cell cell) {
		if (cell == null) {
			return null;
		}
		String value = null;
		switch (cell.getCellTypeEnum()) {
		case NUMERIC: // 数字
			if (HSSFDateUtil.isCellDateFormatted(cell)) { // 如果是日期类型
				value = new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
			} else{
				DecimalFormat df = new DecimalFormat("#.####");
				value = df.format(cell.getNumericCellValue()); 
			}
			break;
		case STRING: // 字符串
			value = cell.getStringCellValue();
			break;
		case FORMULA: // 公式
			try {
				// 用数字方式获取公式结果，根据值判断是否为日期类型
				cell.setCellType(CellType.NUMERIC);
				double numericValue = cell.getNumericCellValue();
				if (HSSFDateUtil.isValidExcelDate(numericValue)) { // 如果是日期类型
//					value = new SimpleDateFormat(DatePattern.LOCALE_ZH_DATE.getValue()).format(cell.getDateCellValue());
				} else
					value = String.valueOf(numericValue);
			} catch (Exception e) {
//				e.printStackTrace();
				log.error("读取单元格公式数据错误，将以字符串方式获取，SheetName:"+cell.getSheet().getSheetName()+",RowNum:"+cell.getRow().getRowNum()+",ColumnIndex:"+cell.getColumnIndex());
				value = cell.getStringCellValue();
			}
			
			break;
		case BLANK: // 空白
//			value = ExcelConstants.EMPTY_CELL_VALUE;
			break;
		case BOOLEAN: // Boolean
			value = String.valueOf(cell.getBooleanCellValue());
			break;
		case ERROR: // Error，返回错误码
			value = String.valueOf(cell.getErrorCellValue());
			break;
		default:
			value = StringUtils.EMPTY;
			break;
		}
		return value;
	}
	
	/**
	 * 判断单元格是否是合并的单元格
	 * @param cellRangeAddresses
	 * @param cell
	 * @return
	 */
	public static boolean isMergedRegion(List<CellRangeAddress> cellRangeAddresses, Cell cell) {
		if (null == cell) {
			return false;
		}
		int firstC = 0;
		int lastC = 0;
		int firstR = 0;
		int lastR = 0;
		for (CellRangeAddress cellRangeAddress : cellRangeAddresses) {
			// 获得合并单元格的起始行, 结束行, 起始列, 结束列
			firstC = cellRangeAddress.getFirstColumn();
			lastC = cellRangeAddress.getLastColumn();
			firstR = cellRangeAddress.getFirstRow();
			lastR = cellRangeAddress.getLastRow();
			if (cell.getColumnIndex() <= lastC && cell.getColumnIndex() >= firstC) {
				if (cell.getRowIndex() <= lastR && cell.getRowIndex() >= firstR) {
					return true;
				}
			}
		}
		return false;
	}
}
