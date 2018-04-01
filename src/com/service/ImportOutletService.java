package com.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mapper.OutletItemMapper;
import com.pojo.OutletItem;
import com.utils.CSVUtils;
import com.utils.FTPProperties;
import com.utils.GenerateUUID;

@Component
public class ImportOutletService extends TimerTask{
	@Autowired
	private OutletItemMapper outletItemMapper;
	
	// ����FTP��������������
	@Scheduled(cron="0 10 1 * * ?")
	public void processData() {
		System.out.println("DIS�������㵵�����ݼӹ���ʼ...");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//String filename = "Outlet" + sdf.format(new Date()) + ".csv";
		String filename = "Outlet20171013.csv";
		
		// ��ȡ������������
		List<OutletItem> outletList = readFile(filename);
		if(outletList == null)
			return;
		
		// �������������ݲ��뵽��ʱ��
		prepareTempData(outletList);
		
		// ������ʱ������
		updateTempData();
		
		// ����ʱ�������ݲ���д�ļ�
		returnAndExportData(filename);
	}

	private void returnAndExportData(String filename) {
		// ���±������¹������ʱ������, �����˽�����ص�csv�ļ���
		List<OutletItem> returnList = outletItemMapper.selectOutletData();
		System.out.println("����ʱ��temp_outlet���ظ��º�������������...");
		
		String[] headers = {"�������","��������","������и�����","�������и�����","DIS�ŵ����","�ŵ�����","��������","��������","��������","����ŵ�������Ա","����ŵ�������Ա�绰","ʡ��","������","·��+���ƺ�","��������","״̬","ͣ������","����������","����������","ϵͳ�����ܲ�����","ϵͳ�����ֱܲ���","ϵͳ����","ϵͳ����","�ؼ۲�����ǰ��������","�ؼ۲������������","Ԥ���ڲ���","�ͻ�����","����ʱ��","�����̱���","�����̱���","�ŵ����ڳ���"};
		// �����º�������������ݵ���������
		CSVUtils.exportCsvFileToLocal(filename, headers, returnList, "UTF-8");
		// �����º�������������ݵ�����FTP������
		CSVUtils.exportCsvFileToFTP(filename, headers, returnList, FTPProperties.IMPORTDIR, "UTF-8");
	}

	private void updateTempData() {
		// ������ʱ������, ����"״̬"��"�ؼ۲�����ǰ��������"��"�ؼ۲������������"��"�ŵ����ڳ���"��"��������"
		outletItemMapper.updateOutletStatus();
		outletItemMapper.updateOutletAheadSupplyDays();
		outletItemMapper.updateOutletEndSupplyDays();
		outletItemMapper.updateOutletStoreCity();
		outletItemMapper.updateOutletSupplyCity();
		System.out.println("��ʱ��temp_outlet���ݸ������...");
		
		// ������ʱ���ϵͳ����, �����Զ��嵵�����ϵͳ����, ��ϵͳ���Ʋ����Զ��嵵������, ��"ϵͳ����"��"ϵͳ����"���뵽�Զ��嵵����
		if(!outletItemMapper.selectDistinctSystem().isEmpty()) {
			List<Map<String, String>> systemMap = outletItemMapper.selectDistinctSystem();
			List<Map<String, String>> paramList = new ArrayList<Map<String,String>>();
			for (Map<String, String> map : systemMap) {
				Map<String, String> params = new HashMap<String, String>();
				if(map.get("ϵͳ����").equals(map.get("ϵͳ����")) || map.get("ϵͳ����").length() > 20) {
					params.put("code", "~");
				} else {
					params.put("code", map.get("ϵͳ����"));
				}
				params.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				params.put("name", map.get("ϵͳ����"));
				params.put("uuid", GenerateUUID.getUUID());
				params.put("pk_defdoclist", "1001N6100000000029KV");
				
				paramList.add(params);
			}
			
			int insertCount = 100;
			int loopTimes = (paramList.size() + insertCount - 1) / insertCount;
			for (int i = 0; i < loopTimes; ++i) {
				int start = i * insertCount;
				int end = i < loopTimes - 1 ? i * insertCount + insertCount : paramList.size();
				List<Map<String, String>> subList = new ArrayList<Map<String, String>>(insertCount);
				for (int j = start; j < end; ++j) {
					subList.add(paramList.get(j));
				}
				outletItemMapper.insertDefdocBatch(subList);
			}
			System.out.println("ϵͳ��Ϣ�����bd_defdoc���...");
		}
		
		// ������ʱ���ϵͳ�����ܲ�����, �����Զ��嵵�����ϵͳ�����ܲ�����, ��ϵͳ�����ܲ����Ʋ����Զ��嵵������, ��"ϵͳ�����ֱܲ���"��"ϵͳ�����ܲ�����"���뵽�Զ��嵵����
		if(!outletItemMapper.selectDistinctRegionSystem().isEmpty()) {
			List<Map<String, String>> systemMap = outletItemMapper.selectDistinctRegionSystem();
			List<Map<String, String>> paramList = new ArrayList<Map<String,String>>();
			for (Map<String, String> map : systemMap) {
				Map<String, String> params = new HashMap<String, String>();
				if(map.get("ϵͳ�����ֱܲ���").equals(map.get("ϵͳ�����ܲ�����")) || map.get("ϵͳ�����ֱܲ���").length() > 20) {
					params.put("code", "~");
				} else {
					params.put("code", map.get("ϵͳ�����ֱܲ���"));
				}
				params.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				params.put("name", map.get("ϵͳ�����ܲ�����"));
				params.put("uuid", GenerateUUID.getUUID());
				params.put("pk_defdoclist", "1001N6100000000029LY");
				
				paramList.add(params);
			}
			
			int insertCount = 100;
			int loopTimes = (paramList.size() + insertCount - 1) / insertCount;
			for (int i = 0; i < loopTimes; ++i) {
				int start = i * insertCount;
				int end = i < loopTimes - 1 ? i * insertCount + insertCount : paramList.size();
				List<Map<String, String>> subList = new ArrayList<Map<String, String>>(insertCount);
				for (int j = start; j < end; ++j) {
					subList.add(paramList.get(j));
				}
				outletItemMapper.insertDefdocBatch(subList);
			}
			System.out.println("ϵͳ�����ܲ���Ϣ�����bd_defdoc���...");
		}
		
		// ������ʱ���������, ���������������������������, ������������������������, ����
		if(!outletItemMapper.selectDistinctRegion().isEmpty()) {
			int count = outletItemMapper.selectCountRegion();
			List<Map<String, String>> regionMap = outletItemMapper.selectDistinctRegion();
			List<Map<String, String>> paramList = new ArrayList<Map<String,String>>();
			for (Map<String, String> map : regionMap) {
				Map<String, String> params = new HashMap<String, String>();
				params.put("code", "X" + ++count);
				params.put("name", map.get("������"));
				params.put("uuid", GenerateUUID.getUUID());
				params.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				
				paramList.add(params);
			}
			
			int insertCount = 100;
			int loopTimes = (paramList.size() + insertCount - 1) / insertCount;
			for (int i = 0; i < loopTimes; ++i) {
				int start = i * insertCount;
				int end = i < loopTimes - 1 ? i * insertCount + insertCount : paramList.size();
				List<Map<String, String>> subList = new ArrayList<Map<String, String>>(insertCount);
				for (int j = start; j < end; ++j) {
					subList.add(paramList.get(j));
				}
				outletItemMapper.insertRegionBatch(subList);
			}
			System.out.println("�����������bd_region���...");
		}
	}

	private void prepareTempData(List<OutletItem> outletList) {
		// ͳ���ܲ����¼��
		int totalCount = 0;
		// ͳ�ƾ����̱������ļ�¼��
		int custErrorCount = 0;
		// ͳ�ƾ����̱���Ϊ�յļ�¼��
		int custNullCount = 0;
		
		boolean custErrorFlag = false;
		boolean custNullFlag = false;
		
		// �����ʱ������
		outletItemMapper.truncateOutletTable();
		System.out.println("��ʱ��temp_outlet����������...");
		
		// ����csv�ļ������ݲ�����ʱ��
		for (OutletItem outletItem : outletList) {
			if(outletItem.getDealerCode() != null && !outletItem.getDealerCode().startsWith("KH")) {
				custErrorCount++;
				custErrorFlag = true;
			} else if (outletItem.getDealerCode() == null) {
				custNullCount++;
				custNullFlag = true;
			}
			
			totalCount++;
		}
		
		int insertCount = 100;
		int loopTimes = (outletList.size() + insertCount - 1) / insertCount;
		for (int i = 0; i < loopTimes; ++i) {
			int start = i * insertCount;
			int end = i < loopTimes - 1 ? i * insertCount + insertCount : outletList.size();
			List<OutletItem> subList = new ArrayList<OutletItem>(insertCount);
			for (int j = start; j < end; ++j) {
				subList.add(outletList.get(j));
			}
			outletItemMapper.insertOutletTableBatch(subList);
		}
		
		System.out.println("�����������ݲ�����ʱ��temp_outlet, ������: " + totalCount + "������");
		if(custErrorFlag)
			System.out.println("������" + custErrorCount + "�����ݵľ����̱������쳣");
		if(custNullFlag)
			System.out.println("������" + custNullCount + "�����ݵľ����̱���Ϊ��");
	}

	private List<OutletItem> readFile(String filename) {
		// ��FTP��������ȡ�����������������
		List<OutletItem> outletList = CSVUtils.importCsvFileFromFTP(filename, new OutletItem(), "UTF-8");
		
		// �ӱ��ػ�ȡ������������
		//List<OutletItem> outletList = CSVUtils.importCsvFileFromLocal(filename, new OutletItem(), "UTF-8");
		
		// ����Ҳ��������������ݻ�������Ϊ��, ���ؿ�
		if(outletList == null) {
			System.out.println("�Ҳ���������������" + filename);
			return null;
		} else if (outletList.size() == 0) {
			System.out.println("������������" + filename + "Ϊ��");
			return null;
		}
		return outletList;
	}
	
	@Override
	public void run() {
		this.processData();
	}
}