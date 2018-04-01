package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class ExcelUtils {
	/**
	 * 将数据导出为Excel文件
	 * 
	 * @param fileName
	 *            表格标题名
	 * @param headers
	 *            表格属性列名二维数组{英文列名(大写)，中文列头}
	 * @param dataList
	 *            List<Map<String, Object>>集合数据
	 */
	public static void exportExcel(String fileName, String[][] headers, List<Map<String, Object>> dataList) {
		if (dataList == null || dataList.size() == 0) {
			System.out.println(fileName + "上月数据为空");
			return;
		}
		System.out.println(fileName + "数据导出开始...");

		//XSSFWorkbook workbook = new XSSFWorkbook();
		// 内存中只创建100个对象, 写临时文件, 当超过100条, 就将内存中不用的对象释放
		Workbook workbook = new SXSSFWorkbook(100);
		FileOutputStream fileOutputStream = null;

		// 设置标题字体样式
		Font boldFont = workbook.createFont();
		boldFont.setBold(true);
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(boldFont);
		
		// 定义一页最大行数
		final int sheetCount = 1000000;
		// 计算Excel表单数
		int loopTimes = (dataList.size() + sheetCount - 1) / sheetCount;

		// 循环创建表单
		for (int s = 0; s < loopTimes; ++s) {
			Sheet sheet = workbook.createSheet(loopTimes == 1 ? fileName : fileName + "第" + (s+1) + "页");

			// 为表单创建表头
			Row headerRow = sheet.createRow(0);
			int countColumn = 0;
			for (String[] column : headers) {
				Cell headerColumn = headerRow.createCell(countColumn++);
				headerColumn.setCellValue(column[1]);
				headerColumn.setCellStyle(headerCellStyle);
			}

			// 计算表单行偏移量
			int startIndex = s * sheetCount;
			int endIndex = s < loopTimes - 1 ? s * sheetCount + sheetCount : dataList.size();
			
			// 创建表体行
			for (int r = startIndex; r < endIndex; ++r) {
				Row dataRow = sheet.createRow(r - startIndex + 1);

				Map<String, Object> dataMap = dataList.get(r);

				// 为表体行单元格赋值
				for (int c = 0; c < headers.length; ++c) {
					Cell dataCell = dataRow.createCell(c);
					Object dataValue = dataMap.get(headers[c][0]);
					if (dataValue != null && !"".equals(dataValue)) {
						// 若单元格存储数字, 将单元格类型设为数字
						if (dataValue.toString().matches("^((-?[1-9]+)|(-?\\d+\\.))(\\d+)?$")) {
							dataCell.setCellValue(Double.parseDouble(dataValue.toString()));
						} else {
							dataCell.setCellValue(dataValue.toString());
						}
					}
				}
			}
		}

		try {
			fileOutputStream = new FileOutputStream(new File("D:/" + fileName + ".xlsx"));
			workbook.write(fileOutputStream);
			fileOutputStream.flush();
			workbook.close();
			if (fileOutputStream != null)
				fileOutputStream.close();
			System.out.println(fileName + ".xlsx导出完成, 共" + dataList.size() + "条数据...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从Excel中导入数据到List, 支持xls和xlsx格式
	 * 
	 * @param fileDir
	 *            文件路径
	 * @return List<Map<String, Object> 数据集合(键值对)
	 */
	public static List<Map<String, Object>> importExcel(String fileDir) {
		FileInputStream fileInputStream = null;
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

		// 判断文件格式是否合要求
		if ((!"xls".equals(fileDir.substring(fileDir.lastIndexOf(".") + 1)))
				&& (!"xlsx".equals(fileDir.substring(fileDir.lastIndexOf(".") + 1)))) {
			System.out.println("Excel文件格式有误");
			return null;
		}

		try {
			fileInputStream = new FileInputStream(new File(fileDir));

			// 存放表头字段值
			List<String> headers = new ArrayList<String>();

			Workbook workbook = WorkbookFactory.create(fileInputStream);
			// 获取工作表数量
			int sheetCount = workbook.getNumberOfSheets();

			// 遍历工作表
			for (int s = 0; s < sheetCount; ++s) {
				Sheet sheet = workbook.getSheetAt(s);
				// 获取工作表行数
				int rowCount = sheet.getPhysicalNumberOfRows();

				// 遍历数据行
				for (int r = 0; r < rowCount; ++r) {
					Row row = sheet.getRow(r);
					// 获取列数
					int cellCount = row.getPhysicalNumberOfCells();
					Map<String, Object> itemMap = new HashMap<String, Object>();

					// 遍历每列数据(单元格)
					for (int c = 0; c < cellCount; ++c) {
						Cell cell = row.getCell(c);

						// 表头单独处理
						if (r == 0) {
							if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING)
								headers.add(cell.getStringCellValue());
						} else {
							int cellType = cell.getCellType();
							switch (cellType) {
							case Cell.CELL_TYPE_STRING:
								itemMap.put(headers.get(c), cell.getStringCellValue());
								break;
							case Cell.CELL_TYPE_BOOLEAN:
								itemMap.put(headers.get(c), cell.getBooleanCellValue());
								break;
							case Cell.CELL_TYPE_NUMERIC:
								// 日期格式判断
								if (DateUtil.isCellDateFormatted(cell)) {
									short format = cell.getCellStyle().getDataFormat();
									SimpleDateFormat simpleDateFormat = null;

									if (format == 14 || format == 31 || format == 57 || format == 58
											|| (176 <= format && format <= 178) || (182 <= format && format <= 196)
											|| (210 <= format && format <= 213) || format == 208) {
										simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
									} else if (format == 20 || format == 32 || format == 183
											|| (200 <= format && format <= 209)) {
										simpleDateFormat = new SimpleDateFormat("HH:mm");
									} else {
										simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									}

									Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
									if (date == null && date.equals("")) {
										itemMap.put(headers.get(c), "");
									} else {
										itemMap.put(headers.get(c), simpleDateFormat.format(date));
									}
								} else {
									// 数字格式
									itemMap.put(headers.get(c), cell.getNumericCellValue());
								}
								break;
							default:
								itemMap.put(headers.get(c), "");
							}
						}
					}
					if(!itemMap.isEmpty()) 
						dataList.add(itemMap);
				}
			}

			fileInputStream.close();
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}

		return dataList;
	}

}