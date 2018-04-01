package com.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mapper.CustomerItemMapper;
import com.pojo.CustomerItem;

import com.utils.CSVUtils;
import com.utils.FTPProperties;

@Component
public class ExportCustomerService extends TimerTask {
	@Autowired
	private CustomerItemMapper customerItemMapper;
	
	// ����������
	@Scheduled(cron="0 40 23 * * ?")
	public void exportData() {
		//long starttime = System.currentTimeMillis();
		//System.out.println("NC�ͻ��������ݼӹ���ʼ: " + new Date(starttime));
		System.out.println("NC�ͻ��������ݼӹ���ʼ");
		
		List<CustomerItem> info2 = customerItemMapper.getCustomerInfo2();
		List<CustomerItem> itemList = new ArrayList<CustomerItem>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String filename = "Customer" + sdf.format(new Date()) + ".csv";
		
		String[] headers = {"����","�ͻ�����","�ɱ���","�ͻ�����","��������","�ͻ����","�ͻ���������","��ҵ��ַ","ר��ҵ��Ա","�绰","����","����-�ͻ���ϵ��-��ϵ��","����-�ͻ���ϵ��-�绰","Email","����״̬","����ʱ��","ͣ��ʱ��"};
		
		int count = 0;
		
		for (CustomerItem customerItem : info2) {
			itemList.add(customerItem);
			count++;
		}
		
		//CSVUtils.exportCsvFileToFTP(filename, headers, itemList, FTPProperties.EXPORTDIR);
		CSVUtils.exportCsvFileToLocal(filename, headers, itemList, "UTF-8");
		
		//long endtime = System.currentTimeMillis();
		//System.out.println("NC�ͻ��������ݼӹ�����: " + new Date(endtime));
		//System.out.println("������: " + count + "������, �ӹ�ʱ��: " + (endtime - starttime) / 1000 + "s");
		System.out.println("������: " + count + "������");
	}

	@Override
	public void run() {
		this.exportData();
	}
}