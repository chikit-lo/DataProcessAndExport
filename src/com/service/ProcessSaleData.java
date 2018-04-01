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
		String[] headers = {"物料编码","经销商产品金额(含税计算)","NC客户编码","经销商名称","经销商编码","分销商名称","分销商编码","单据编号","单据日期","单据类型","库存地点","经销商处客户编码","经销商处客户名称","客户标准编码","客户标准名称","经销商产品编码","经销商产品名称","经销商产品单位","经销商产品数量","经销商产品金额","产品标准编码","产品标准名称","统计单位数量","统计单位","产品标准金额","基本单位","基本单位数量","经销商类型","经销商产品金额计算","经销商产品价格计算","回传日期","系统区域总仓名称","系统区域总仓编码","系统名称","系统编码"};
		/*List<SaleItem> saleList = CSVUtils.importCsvFileFromLocalFolder("D:/salesdata", new SaleItem(), "GBK");
		String filename = "Sales" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + ".csv";
		
		for (SaleItem saleItem : saleList) {
			saleItem.setMaterialCode(saleItem.getProductStdCode());
			saleItem.setDistributorProductAmount2(saleItem.getDistributorProductAmount1());
			saleItem.setDistributorProductPrice2(saleItem.getDistributorProductPrice1());
		}
		
		System.out.println("实际记录数: " + saleList.size());
		CSVUtils.exportCsvFileToLocal(filename, headers, saleList, "UTF-8");
		CSVUtils.exportCsvFileToFTP(filename, headers, saleList, FTPProperties.IMPORTDIR, "UTF-8");*/
		
		
		File dir = new File("D:/");
		String[] files = dir.list();
		for (String file : files) {
			if(file.startsWith("转换后销售数据")) {
				System.out.println("正在读取" + file + "数据");
				List<SaleItem> saleList = CSVUtils.importCsvFileFromLocal(file, new SaleItem(), "GBK");
				
				//String suffix = file.substring(file.lastIndexOf("-") + 3, file.length());
				String suffix = file.lastIndexOf("_") == -1 ? ".csv" : file.substring(file.lastIndexOf("_"), file.length());
				String filename = "Sales" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + suffix;
				
				List<SaleItem> returnList = new ArrayList<SaleItem>();
				
				for (SaleItem saleItem : saleList) {
					// 过滤"是否原价出货"为"是"的数据
					/*if(saleItem.getIsOriginalPrice() != null && saleItem.getIsOriginalPrice().equals("是")) {
						continue;
					}*/
					
					// 经销商产品金额计算及经销商产品价格计算数值初始化
					saleItem.setDistributorProductAmount2(saleItem.getDistributorProductAmount1());
					saleItem.setDistributorProductPrice2(saleItem.getDistributorProductPrice1());

					// 替换BB产品的错误物料编码
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

					// 将表体行数值字段为空的替换为0
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
					
					// 将基本单位数量为0的赋值为经销商产品数量的值
					if(saleItem.getBasicUnitCount() == null || saleItem.getBasicUnitCount().equals("0")) {
						saleItem.setBasicUnitCount(saleItem.getAgencyProductCount());
					}
					
					returnList.add(saleItem);
				}
				
				System.out.println("总记录数: " + saleList.size());
				System.out.println("返回记录数: " + returnList.size());
				System.out.println("正在写出" + filename + "数据");
				CSVUtils.exportCsvFileToLocal(filename, headers, returnList, "UTF-8");
				CSVUtils.exportCsvFileToFTP(filename, headers, returnList, FTPProperties.IMPORTDIR, "UTF-8");
			}
		}
	}
}