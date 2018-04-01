package com.service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pojo.SaleItem;
import com.utils.CSVUtils;
import com.utils.FTPProperties;

@Component
public class ProcessSaleData {
	public void processData() {
		String[] headers = {"���ϱ���","�����̲�Ʒ���(��˰����)","NC�ͻ�����","����������","�����̱���","����������","�����̱���","���ݱ��","��������","��������","���ص�","�����̴��ͻ�����","�����̴��ͻ�����","�ͻ���׼����","�ͻ���׼����","�����̲�Ʒ����","�����̲�Ʒ����","�����̲�Ʒ��λ","�����̲�Ʒ����","�����̲�Ʒ���","��Ʒ��׼����","��Ʒ��׼����","ͳ�Ƶ�λ����","ͳ�Ƶ�λ","��Ʒ��׼���","������λ","������λ����","����������","�����̲�Ʒ������","�����̲�Ʒ�۸����","�ش�����","ϵͳ�����ܲ�����","ϵͳ�����ֱܲ���","ϵͳ����","ϵͳ����"};
		/*List<SaleItem> saleList = CSVUtils.importCsvFileFromLocalFolder("D:/salesdata", new SaleItem(), "GBK");
		String filename = "Sales" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".csv";
		
		for (SaleItem saleItem : saleList) {
			saleItem.setMaterialCode(saleItem.getProductStdCode());
			saleItem.setDistributorProductAmount2(saleItem.getDistributorProductAmount1());
			saleItem.setDistributorProductPrice2(saleItem.getDistributorProductPrice1());
		}
		
		System.out.println("ʵ�ʼ�¼��: " + saleList.size());
		CSVUtils.exportCsvFileToLocal(filename, headers, saleList, "UTF-8");
		CSVUtils.exportCsvFileToFTP(filename, headers, saleList, FTPProperties.IMPORTDIR, "UTF-8");*/
		
		
		File dir = new File("D:/");
		String[] files = dir.list();
		for (String file : files) {
			if(file.startsWith("ת������������")) {
				System.out.println("���ڶ�ȡ" + file + "����");
				List<SaleItem> saleList = CSVUtils.importCsvFileFromLocal(file, new SaleItem(), "GBK");
				
				//String suffix = file.substring(file.lastIndexOf("-") + 3, file.length());
				String suffix = file.lastIndexOf("_") == -1 ? ".csv" : file.substring(file.lastIndexOf("_"), file.length());
				String filename = "Sales" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + suffix;
				
				List<SaleItem> returnList = new ArrayList<SaleItem>();
				
				for (SaleItem saleItem : saleList) {
					// ����"�Ƿ�ԭ�۳���"Ϊ"��"������
					/*if(saleItem.getIsOriginalPrice() != null && saleItem.getIsOriginalPrice().equals("��")) {
						continue;
					}*/
					
					// �����̲�Ʒ�����㼰�����̲�Ʒ�۸������ֵ��ʼ��
					saleItem.setDistributorProductAmount2(saleItem.getDistributorProductAmount1());
					saleItem.setDistributorProductPrice2(saleItem.getDistributorProductPrice1());

					// �滻BB��Ʒ�Ĵ������ϱ���
					if(saleItem.getProductStdCode() != null && (saleItem.getProductStdCode().equals("0104010029") || saleItem.getProductStdCode().equals("0104010030"))) {
						saleItem.setMaterialCode("0104010031");
					} else if(saleItem.getProductStdCode() != null && (saleItem.getProductStdCode().equals("0104010032") || saleItem.getProductStdCode().equals("0104010033"))) {
						saleItem.setMaterialCode("0104010034");
					} else if(saleItem.getProductStdCode() != null && (saleItem.getProductStdCode().equals("0104010035") || saleItem.getProductStdCode().equals("0104010036"))) {
						saleItem.setMaterialCode("0104010037");
					} else if(saleItem.getProductStdCode() != null && saleItem.getProductStdCode().equals("0104010041")) {
						saleItem.setMaterialCode("0104010042");
					} else if(saleItem.getProductStdCode() != null && saleItem.getProductStdCode().equals("0104010043")) {
						saleItem.setMaterialCode("0104010058");
					} else {
						saleItem.setMaterialCode(saleItem.getProductStdCode());
					}

					// ����������ֵ�ֶ�Ϊ�յ��滻Ϊ0
					if(saleItem.getAgencyTaxAmount() == null) {
						saleItem.setAgencyTaxAmount("0");
					}
					if(saleItem.getAgencyProductCount() == null) {
						saleItem.setAgencyProductCount("0");
					}
					if(saleItem.getAgencyProductAmount() == null) {
						saleItem.setAgencyProductAmount("0");
					}
					if(saleItem.getUnitCount() == null) {
						saleItem.setUnitCount("0");
					}
					if(saleItem.getProductStdAmount() == null) {
						saleItem.setProductStdAmount("0");
					}
					if(saleItem.getDistributorProductAmount1() == null) {
						saleItem.setDistributorProductAmount1("0");
					}
					if(saleItem.getDistributorProductPrice1() == null) {
						saleItem.setDistributorProductPrice1("0");
					}
					if(saleItem.getAgencyTaxPrice() == null) {
						saleItem.setAgencyTaxPrice("0");
					}
					if(saleItem.getNormalTaxPrice() == null) {
						saleItem.setNormalTaxPrice("0");
					}
					
					// ��������λ����Ϊ0�ĸ�ֵΪ�����̲�Ʒ������ֵ
					if(saleItem.getBasicUnitCount() == null || saleItem.getBasicUnitCount().equals("0")) {
						saleItem.setBasicUnitCount(saleItem.getAgencyProductCount());
					}
					
					returnList.add(saleItem);
				}
				
				System.out.println("�ܼ�¼��: " + saleList.size());
				System.out.println("���ؼ�¼��: " + returnList.size());
				System.out.println("����д��" + filename + "����");
				CSVUtils.exportCsvFileToLocal(filename, headers, returnList, "UTF-8");
				CSVUtils.exportCsvFileToFTP(filename, headers, returnList, FTPProperties.IMPORTDIR, "UTF-8");
			}
		}
	}
}