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
	 * ��csv�ļ�����������Ŀ¼
	 * @param filename �ļ���
	 * @param headers ��ͷ����
	 * @param data ��������
	 * @param encoding �ַ�����
	 */
	public static <T> void exportCsvFileToLocal(String filename, String[] headers, List<T> data, String encoding) {
		File outputFile = new File("D:/" + filename);
		CsvWriterSettings csvWriterSettings = new CsvWriterSettings();
		// �����ļ�����(���ѡ������ݣ������Զ�д��)
		csvWriterSettings.setHeaders(headers);
		// ���Ϊ����ֶ�ʹ�� com.univocity.parsers.annotations ���ж����ע��, ����ͨ�� BeanWriterProcessor���ֶ�ֱ��ӳ�䵽�����RowWriterProcessor��һ����, ����"֪��"��ν���������ӳ�䵽ֵ����
		csvWriterSettings.setRowWriterProcessor(new BeanWriterProcessor<T>((Class<T>) data.get(0).getClass()));
		
		// Ϊ��������ô���һ��writer
		CsvWriter localWriter = new CsvWriter(outputFile, encoding, csvWriterSettings);
		//localWriter.writeRow(headers);
		// Ϊ���������д��ָ���ı���
		localWriter.writeHeaders();
		localWriter.processRecords(data);
		localWriter.close();
		
		System.out.println("�ļ�" + filename + "�����ɹ�");
	}
	
	/**
	 * ��csv�ļ�������FTP������
	 * @param filename �ļ���
	 * @param headers ��ͷ����
	 * @param data ��������
	 * @param dir ����·��
	 * @param encoding �ַ�����
	 */
	public static <T> void exportCsvFileToFTP(String filename, String[] headers, List<T> data, String dir, String encoding) {
		CsvWriterSettings csvWriterSettings = new CsvWriterSettings();
		// �����ļ�����(���ѡ������ݣ������Զ�д��)
		csvWriterSettings.setHeaders(headers);
		// ���Ϊ����ֶ�ʹ�� com.univocity.parsers.annotations ���ж����ע��, ����ͨ�� BeanWriterProcessor���ֶ�ֱ��ӳ�䵽�����RowWriterProcessor��һ����, ����"֪��"��ν���������ӳ�䵽ֵ����
		csvWriterSettings.setRowWriterProcessor(new BeanWriterProcessor<T>((Class<T>) data.get(0).getClass()));
		
		// ������FTP������
		FTPUtils ftpUtils = new FTPUtils();
		OutputStream outputStream = ftpUtils.getFTPOutputStream(FTPProperties.HOST, FTPProperties.PORT, FTPProperties.USER, FTPProperties.PASSWORD, filename, dir);
		CsvWriter ftpWriter = new CsvWriter(outputStream, encoding, csvWriterSettings);
		ftpWriter.writeHeaders();
		ftpWriter.processRecords(data);
		ftpWriter.close();
		System.out.println("�ļ�д��ɹ�");
		ftpUtils.releaseSource();
	}
	
	/**
	 * ��FTP����������csv�ļ�
	 * @param filename �ļ���
	 * @param bean JavaBean
	 * @param encoding �ַ�����
	 * @return Bean����
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
		
		System.out.println("�ļ���ȡ�ɹ�");
		ftpUtils.releaseSource();
		
		return returnList;
	}
	
	/**
	 * �ӱ��ص���csv�ļ�
	 * @param filename �ļ���
	 * @param bean JavaBean
	 * @param encoding �ַ�����
	 * @return Bean����
	 */
	public static <T> List<T> importCsvFileFromLocal(String filename, T bean, String encoding) {
		CsvParserSettings csvParserSettings = new CsvParserSettings();
		
		BeanListProcessor<T> beanListProcessor = new BeanListProcessor<T>((Class<T>) bean.getClass());
		csvParserSettings.setProcessor(beanListProcessor);
		csvParserSettings.setHeaderExtractionEnabled(true);
		
		CsvParser parser = new CsvParser(csvParserSettings);
		
		File importFile = new File("D:/" + filename);
		if(!importFile.exists()) {
			System.out.println("�Ҳ����ļ�: " + filename);
			return null;
		}
		parser.parse(importFile, encoding);
		List<T> returnList = beanListProcessor.getBeans();
		
		System.out.println("�ļ���ȡ�ɹ�, " + importFile.getAbsolutePath());
		
		return returnList;
	}
	
	/**
	 * �ӱ���Ŀ¼����csv�ļ�
	 * @param direction �ļ�����·��
	 * @param bean JavaBean
	 * @param encoding �ַ�����
	 * @return Bean����
	 */
	public static <T> List<T> importCsvFileFromLocalFolder(String direction, T bean, String encoding) {
		CsvParserSettings csvParserSettings = new CsvParserSettings();
		
		BeanListProcessor<T> beanListProcessor = new BeanListProcessor<T>((Class<T>) bean.getClass());
		csvParserSettings.setProcessor(beanListProcessor);
		csvParserSettings.setHeaderExtractionEnabled(true);
		
		CsvParser parser = new CsvParser(csvParserSettings);
		
		File folder = new File(direction);
		if(!folder.isDirectory()) {
			System.out.println(direction + " ·��������");
			return null;
		} else {
			System.out.println("·�� " + direction + "�ļ���ȡ��ʼ");
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
			
			System.out.println("�ļ���ȡ�ɹ�" + Arrays.toString(folder.list()));
			System.out.println("�ܼ�¼��: " + returnList.size());
			
			return returnList;
		}
	}
}