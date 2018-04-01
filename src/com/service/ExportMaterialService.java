package com.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mapper.MaterialItemMapper;
import com.pojo.MaterialItem;
import com.utils.CSVUtils;
import com.utils.FTPProperties;

@Component
public class ExportMaterialService extends TimerTask {
	@Autowired
	private MaterialItemMapper materialItemMapper;
	
	// 处理导出数据
	@Scheduled(cron="0 40 23 * * ?")
	public void exportData() {
		//long starttime = System.currentTimeMillis();
		//System.out.println("NC物料档案数据加工开始: " + new Date(starttime));
		System.out.println("NC物料档案数据加工开始");
		
		List<MaterialItem> info2 = materialItemMapper.getMaterialInfo2();
		List<MaterialItem> itemList = new ArrayList<MaterialItem>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String filename = "Product" + sdf.format(new Date()) + ".csv";
		
		String[] headers = {"物料编码","原编码","助记码","物料名称","条形码","品牌","物料分类","品类","表层","配方","形状","厚度","长度","用途","规格","包装类型","产品属性","辅单位","主单位","物料价格","最小单位","计量单位换算率","物料税率","主键","DR值","创建时间","最后修改时间"};
		
		String[] rate = null;
		int count = 0;
		
		// 用正则表达式提取物料税率
		Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
		Matcher matcher = null;
		
		for (MaterialItem materialItem : info2) {
			rate = materialItem.getMeasrate().split("/");
			if (materialItem.getOldcode() != null) {
				materialItem.setOldcode(materialItem.getOldcode().replaceAll("\r|\n", ""));
			} else {
				materialItem.setOldcode(null);
			}
			
			if (materialItem.getPrice() != null) {
				materialItem.setPrice(materialItem.getPrice());
			} else {
				materialItem.setPrice("0");
			}
			
			if (Integer.parseInt(rate[0]) >= Integer.parseInt(rate[1])) {
				materialItem.setMeasrate(rate[0]);
			} else {
				materialItem.setMeasrate(rate[1]);
			}
			
			matcher = pattern.matcher(materialItem.getTax());
			if (matcher.find())
				materialItem.setTax(Float.parseFloat(matcher.group()) / 100 + "");
			
			itemList.add(materialItem);
			count++;
		}
		
		//CSVUtils.exportCsvFileToFTP(filename, headers, itemList, FTPProperties.EXPORTDIR);
		CSVUtils.exportCsvFileToLocal(filename, headers, itemList, "UTF-8");
		
		//long endtime = System.currentTimeMillis();
		//System.out.println("NC物料档案数据加工结束: " + new Date(endtime));
		//System.out.println("共处理: " + count + "条数据, 加工时间: " + (endtime - starttime) / 1000 + "s");
		System.out.println("共处理: " + count + "条数据");
	}

	@Override
	public void run() {
		this.exportData();
	}
}