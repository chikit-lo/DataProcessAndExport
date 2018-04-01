package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.common.processor.BeanWriterProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

public class CSVUtils {
	/**
	 * 将csv文件导出到本地目录
	 * @param filename 文件名
	 * @param headers 表头列名
	 * @param data 表体数据
	 * @param encoding 字符编码
	 */
	public static <T> void exportCsvFileToLocal(String filename, String[] headers, List<T> data, String encoding) {
		File outputFile = new File("D:/" + filename);
		CsvWriterSettings csvWriterSettings = new CsvWriterSettings();
		// 设置文件标题(针对选择的内容，不会自动写入)
		csvWriterSettings.setHeaders(headers);
		// 如果为类的字段使用 com.univocity.parsers.annotations 包中定义的注解, 可以通过 BeanWriterProcessor将字段直接映射到输出。RowWriterProcessor是一个接, 可以"知道"如何将给定对象映射到值序列
		csvWriterSettings.setRowWriterProcessor(new BeanWriterProcessor<T>((Class<T>) data.get(0).getClass()));
		
		// 为上面的配置创建一个writer
		CsvWriter localWriter = new CsvWriter(outputFile, encoding, csvWriterSettings);
		//localWriter.writeRow(headers);
		// 为上面的配置写入指定的标题
		localWriter.writeHeaders();
		localWriter.processRecords(data);
		localWriter.close();
		
		System.out.println("文件" + filename + "导出成功");
	}
	
	/**
	 * 将csv文件导出到FTP服务器
	 * @param filename 文件名
	 * @param headers 表头列名
	 * @param data 表体数据
	 * @param dir 导出路径
	 * @param encoding 字符编码
	 */
	public static <T> void exportCsvFileToFTP(String filename, String[] headers, List<T> data, String dir, String encoding) {
		CsvWriterSettings csvWriterSettings = new CsvWriterSettings();
		// 设置文件标题(针对选择的内容，不会自动写入)
		csvWriterSettings.setHeaders(headers);
		// 如果为类的字段使用 com.univocity.parsers.annotations 包中定义的注解, 可以通过 BeanWriterProcessor将字段直接映射到输出。RowWriterProcessor是一个接, 可以"知道"如何将给定对象映射到值序列
		csvWriterSettings.setRowWriterProcessor(new BeanWriterProcessor<T>((Class<T>) data.get(0).getClass()));
		
		// 导出到FTP服务器
		FTPUtils ftpUtils = new FTPUtils();
		OutputStream outputStream = ftpUtils.getFTPOutputStream(FTPProperties.HOST, FTPProperties.PORT, FTPProperties.USER, FTPProperties.PASSWORD, filename, dir);
		CsvWriter ftpWriter = new CsvWriter(outputStream, encoding, csvWriterSettings);
		ftpWriter.writeHeaders();
		ftpWriter.processRecords(data);
		ftpWriter.close();
		System.out.println("文件写入成功");
		ftpUtils.releaseSource();
	}
	
	/**
	 * 从FTP服务器导入csv文件
	 * @param filename 文件名
	 * @param bean JavaBean
	 * @param encoding 字符编码
	 * @return Bean集合
	 */
	public static <T> List<T> importCsvFileFromFTP(String filename, T bean, String encoding) {
		CsvParserSettings csvParserSettings = new CsvParserSettings();
		
		BeanListProcessor<T> beanListProcessor = new BeanListProcessor<T>((Class<T>) bean.getClass());
		csvParserSettings.setProcessor(beanListProcessor);
		csvParserSettings.setHeaderExtractionEnabled(true);
		
		CsvParser parser = new CsvParser(csvParserSettings);
		
		FTPUtils ftpUtils = new FTPUtils();
		InputStream inputStream = ftpUtils.getFTPInputStream(FTPProperties.HOST, FTPProperties.PORT, FTPProperties.USER, FTPProperties.PASSWORD, filename, FTPProperties.IMPORTDIR);
		if(inputStream == null) {
			return null;
		}
		parser.parse(inputStream, encoding);
		List<T> returnList = beanListProcessor.getBeans();
		
		System.out.println("文件读取成功");
		ftpUtils.releaseSource();
		
		return returnList;
	}
	
	/**
	 * 从本地导入csv文件
	 * @param filename 文件名
	 * @param bean JavaBean
	 * @param encoding 字符编码
	 * @return Bean集合
	 */
	public static <T> List<T> importCsvFileFromLocal(String filename, T bean, String encoding) {
		CsvParserSettings csvParserSettings = new CsvParserSettings();
		
		BeanListProcessor<T> beanListProcessor = new BeanListProcessor<T>((Class<T>) bean.getClass());
		csvParserSettings.setProcessor(beanListProcessor);
		csvParserSettings.setHeaderExtractionEnabled(true);
		
		CsvParser parser = new CsvParser(csvParserSettings);
		
		File importFile = new File("D:/" + filename);
		if(!importFile.exists()) {
			System.out.println("找不到文件: " + filename);
			return null;
		}
		parser.parse(importFile, encoding);
		List<T> returnList = beanListProcessor.getBeans();
		
		System.out.println("文件读取成功, " + importFile.getAbsolutePath());
		
		return returnList;
	}
	
	/**
	 * 从本地目录导入csv文件
	 * @param direction 文件导入路径
	 * @param bean JavaBean
	 * @param encoding 字符编码
	 * @return Bean集合
	 */
	public static <T> List<T> importCsvFileFromLocalFolder(String direction, T bean, String encoding) {
		CsvParserSettings csvParserSettings = new CsvParserSettings();
		
		BeanListProcessor<T> beanListProcessor = new BeanListProcessor<T>((Class<T>) bean.getClass());
		csvParserSettings.setProcessor(beanListProcessor);
		csvParserSettings.setHeaderExtractionEnabled(true);
		
		CsvParser parser = new CsvParser(csvParserSettings);
		
		File folder = new File(direction);
		if(!folder.isDirectory()) {
			System.out.println(direction + " 路径不存在");
			return null;
		} else {
			System.out.println("路径 " + direction + "文件读取开始");
			File[] files = folder.listFiles();
			Vector<InputStream> vectorStreams = new Vector<InputStream>();
			for (File file : files) {
				if(file.isFile()) {
					InputStream inputStream = null;
					try {
						inputStream = new FileInputStream(file);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					vectorStreams.add(inputStream);
				}
			}
			SequenceInputStream sequenceInputStream = new SequenceInputStream(vectorStreams.elements());
			
			parser.parse(sequenceInputStream, encoding);
			List<T> returnList = beanListProcessor.getBeans();
			
			System.out.println("文件读取成功" + Arrays.toString(folder.list()));
			System.out.println("总记录数: " + returnList.size());
			
			return returnList;
		}
	}
}