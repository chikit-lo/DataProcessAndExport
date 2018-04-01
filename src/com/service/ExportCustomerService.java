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
	
	// 处理导出数据
	@Scheduled(cron="0 40 23 * * ?")
	public void exportData() {
		//long starttime = System.currentTimeMillis();
		//System.out.println("NC客户档案数据加工开始: " + new Date(starttime));
		System.out.println("NC客户档案数据加工开始");
		
		List<CustomerItem> info2 = customerItemMapper.getCustomerInfo2();
		List<CustomerItem> itemList = new ArrayList<CustomerItem>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String filename = "Customer" + sdf.format(new Date()) + ".csv";
		
		String[] headers = {"主键","客户编码","旧编码","客户名称","地区分类","客户简称","客户基本分类","企业地址","专管业务员","电话","邮箱","表体-客户联系人-联系人","表体-客户联系人-电话","Email","启用状态","启用时间","停用时间"};
		
		int count = 0;
		
		for (CustomerItem customerItem : info2) {
			itemList.add(customerItem);
			count++;
		}
		
		//CSVUtils.exportCsvFileToFTP(filename, headers, itemList, FTPProperties.EXPORTDIR);
		CSVUtils.exportCsvFileToLocal(filename, headers, itemList, "UTF-8");
		
		//long endtime = System.currentTimeMillis();
		//System.out.println("NC客户档案数据加工结束: " + new Date(endtime));
		//System.out.println("共处理: " + count + "条数据, 加工时间: " + (endtime - starttime) / 1000 + "s");
		System.out.println("共处理: " + count + "条数据");
	}

	@Override
	public void run() {
		this.exportData();
	}
}