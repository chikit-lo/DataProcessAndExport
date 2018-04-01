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
	
	// ����NCϵͳн�ʵ�����P2��P3��
	// ÿ��22-31��8��30�֡�23��30��ִ��
	@Scheduled(cron="0 30 8,23 21-31 * ?")
	public void updateData() {
		String[] code = new String[] {"0101", "0102"};
		String yyyyMM = DateUtils.getCurrentyyyyMM();
		List<Map<String, String>> paramList = new ArrayList<Map<String, String>>();
		
		System.out.println("NC���ݸ��¿�ʼ, ��������: " + yyyyMM + "...");
		
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
		
		System.out.println("NC���ݸ��½���...");
	}
}