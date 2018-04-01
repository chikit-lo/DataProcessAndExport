package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mapper.UpdateDataMapper;
import com.utils.DateUtils;

@Component
public class UpdateDataService {
	@Autowired
	private UpdateDataMapper updateDataMapper;
	
	// 更新NC系统薪资档案、P2、P3价
	// 每月22-31号8点30分、23点30分执行
	@Scheduled(cron="0 30 8,23 21-31 * ?")
	public void updateData() {
		String[] code = new String[] {"0101", "0102"};
		String yyyyMM = DateUtils.getCurrentyyyyMM();
		List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();
		
		System.out.println("NC数据更新开始, 更新年月: " + yyyyMM + "...");
		
		for(int i = 0; i < 2; ++i) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("code", code[i]);
			params.put("yyyyMM", yyyyMM);
			paramList.add(params);
		}
		
		for (Map<String, String> params : paramList) {
			updateDataMapper.updateFiorgvid(params);
			updateDataMapper.updateLiabilityorg(params);
			updateDataMapper.updateFinanceorg(params);
		}
		
		updateDataMapper.updateLiabilitydept(yyyyMM);
		updateDataMapper.updateLibdeptvid(yyyyMM);
		updateDataMapper.updateFipdeptvid(yyyyMM);
		updateDataMapper.updateFinancedept(yyyyMM);
		
		updateDataMapper.updateP2value();
		updateDataMapper.updateP3value();
		
		System.out.println("NC数据更新结束...");
	}
}