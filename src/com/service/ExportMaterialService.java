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
	
	// ����������
	@Scheduled(cron="0 40 23 * * ?")
	public void exportData() {
		//long starttime = System.currentTimeMillis();
		//System.out.println("NC���ϵ������ݼӹ���ʼ: " + new Date(starttime));
		System.out.println("NC���ϵ������ݼӹ���ʼ");
		
		List<MaterialItem> info2 = materialItemMapper.getMaterialInfo2();
		List<MaterialItem> itemList = new ArrayList<MaterialItem>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String filename = "Product" + sdf.format(new Date()) + ".csv";
		
		String[] headers = {"���ϱ���","ԭ����","������","��������","������","Ʒ��","���Ϸ���","Ʒ��","���","�䷽","��״","���","����","��;","���","��װ����","��Ʒ����","����λ","����λ","���ϼ۸�","��С��λ","������λ������","����˰��","����","DRֵ","����ʱ��","����޸�ʱ��"};
		
		String[] rate = null;
		int count = 0;
		
		// ��������ʽ��ȡ����˰��
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
		//System.out.println("NC���ϵ������ݼӹ�����: " + new Date(endtime));
		//System.out.println("������: " + count + "������, �ӹ�ʱ��: " + (endtime - starttime) / 1000 + "s");
		System.out.println("������: " + count + "������");
	}

	@Override
	public void run() {
		this.exportData();
	}
}