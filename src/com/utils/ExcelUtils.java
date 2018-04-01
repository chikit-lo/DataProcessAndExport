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
	 * �����ݵ���ΪExcel�ļ�
	 * 
	 * @param fileName
	 *            ��������
	 * @param headers
	 *            �������������ά����{Ӣ������(��д)��������ͷ}
	 * @param dataList
	 *            List<Map<String, Object>>��������
	 */
	public static void exportExcel(String fileName, String[][] headers, List<Map<String, Object>> dataList) {
		if (dataList == null || dataList.size() == 0) {
			System.out.println(fileName + "��������Ϊ��");
			return;
		}
		System.out.println(fileName + "���ݵ�����ʼ...");

		//XSSFWorkbook workbook = new XSSFWorkbook();
		// �ڴ���ֻ����100������, д��ʱ�ļ�, ������100��, �ͽ��ڴ��в��õĶ����ͷ�
		Workbook workbook = new SXSSFWorkbook(100);
		FileOutputStream fileOutputStream = null;

		// ���ñ���������ʽ
		Font boldFont = workbook.createFont();
		boldFont.setBold(true);
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(boldFont);
		
		// ����һҳ�������
		final int sheetCount = 1000000;
		// ����Excel����
		int loopTimes = (dataList.size() + sheetCount - 1) / sheetCount;

		// ѭ��������
		for (int s = 0; s < loopTimes; ++s) {
			Sheet sheet = workbook.createSheet(loopTimes == 1 ? fileName : fileName + "��" + (s+1) + "ҳ");

			// Ϊ��������ͷ
			Row headerRow = sheet.createRow(0);
			int countColumn = 0;
			for (String[] column : headers) {
				Cell headerColumn = headerRow.createCell(countColumn++);
				headerColumn.setCellValue(column[1]);
				headerColumn.setCellStyle(headerCellStyle);
			}

			// �������ƫ����
			int startIndex = s * sheetCount;
			int endIndex = s < loopTimes - 1 ? s * sheetCount + sheetCount : dataList.size();
			
			// ����������
			for (int r = startIndex; r < endIndex; ++r) {
				Row dataRow = sheet.createRow(r - startIndex + 1);

				Map<String, Object> dataMap = dataList.get(r);

				// Ϊ�����е�Ԫ��ֵ
				for (int c = 0; c < headers.length; ++c) {
					Cell dataCell = dataRow.createCell(c);
					Object dataValue = dataMap.get(headers[c][0]);
					if (dataValue != null && !"".equals(dataValue)) {
						// ����Ԫ��洢����, ����Ԫ��������Ϊ����
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
			System.out.println(fileName + ".xlsx�������, ��" + dataList.size() + "������...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��Excel�е������ݵ�List, ֧��xls��xlsx��ʽ
	 * 
	 * @param fileDir
	 *            �ļ�·��
	 * @return List<Map<String, Object> ���ݼ���(��ֵ��)
	 */
	public static List<Map<String, Object>> importExcel(String fileDir) {
		FileInputStream fileInputStream = null;
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

		// �ж��ļ���ʽ�Ƿ��Ҫ��
		if ((!"xls".equals(fileDir.substring(fileDir.lastIndexOf(".") + 1)))
				&& (!"xlsx".equals(fileDir.substring(fileDir.lastIndexOf(".") + 1)))) {
			System.out.println("Excel�ļ���ʽ����");
			return null;
		}

		try {
			fileInputStream = new FileInputStream(new File(fileDir));

			// ��ű�ͷ�ֶ�ֵ
			List<String> headers = new ArrayList<String>();

			Workbook workbook = WorkbookFactory.create(fileInputStream);
			// ��ȡ����������
			int sheetCount = workbook.getNumberOfSheets();

			// ����������
			for (int s = 0; s < sheetCount; ++s) {
				Sheet sheet = workbook.getSheetAt(s);
				// ��ȡ����������
				int rowCount = sheet.getPhysicalNumberOfRows();

				// ����������
				for (int r = 0; r < rowCount; ++r) {
					Row row = sheet.getRow(r);
					// ��ȡ����
					int cellCount = row.getPhysicalNumberOfCells();
					Map<String, Object> itemMap = new HashMap<String, Object>();

					// ����ÿ������(��Ԫ��)
					for (int c = 0; c < cellCount; ++c) {
						Cell cell = row.getCell(c);

						// ��ͷ��������
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
								// ���ڸ�ʽ�ж�
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
									// ���ָ�ʽ
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