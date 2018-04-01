package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mapper.ReportMapper;
import com.utils.DateUtils;
import com.utils.EmailUtils;
import com.utils.ExcelUtils;

@Component
public class ExportReportsService {
	@Autowired
	private ReportMapper reportMapper;
	
	private String startDate;
	private String endDate;
	private String yyyyMM;
	private Map<String, String> params;
	
	public void getExportDate() {
		startDate = DateUtils.getFirstDayOfLastMonth("yyyy-MM-dd");
		//startDate = "2017-12-01";
		endDate = DateUtils.getLastDayOfLastMonth("yyyy-MM-dd");
		//endDate = "2017-12-31";
		
		yyyyMM = DateUtils.getYearMonth("yyyyMM", -1);
		
		params = new HashMap<String, String>();
		params.put("startDate", startDate);
		params.put("endDate", endDate);
	}
	
	// ����NCϵͳ��������
	// ÿ��1��0��30��ִ��
	@Scheduled(cron="0 30 0 1 * ?")
	public void exportReports() {
		getExportDate();
		System.out.println("����ʱ��������ʼ...");
		System.out.println("������������: " + startDate + " ~ " + endDate + "...");
		
		exportKHXXB();
		exportZCXPMX();
		exportXSMXB();
		exportXSFPMX();
		exportKHZKDXSMXB();
		exportKHZKDXSMXB2();
		System.out.println("����ʱ�����������...");
		
		sendExport();
		System.out.println("�ʼ��������...");
	}
	
	// �����������ʼ���ʽ���͸����ͬ��
	public void sendExport() {
		// ���۷�Ʊ��ϸ���ͻ��ۿ۵�������ϸ���͸���������Ӿ
		EmailUtils.sendEmail(new String[] {"liangaiyong@abckms.com"}, new String[] {"���۷�Ʊ��ϸ-" + yyyyMM + ".xlsx", "�ͻ��ۿ۵�������ϸ��-" + yyyyMM + ".xlsx"}, "Wing");
		//EmailUtils.sendEmail(new String[] {"luzhijie@abckms.com"}, new String[] {"���۷�Ʊ��ϸ-" + yyyyMM + ".xlsx", "�ͻ��ۿ۵�������ϸ��-" + yyyyMM + ".xlsx"}, "Wing");
		// ������Ʒ��ϸ���ͻ��ۿ۵�������ϸ��(�г���)���͸��г�������Ⱥ
		EmailUtils.sendEmail(new String[] {"queenie@abckms.com"}, new String[] {"������ϸ��-" + yyyyMM + ".xlsx", "�ͻ��ۿ۵�������ϸ��(�г���)-" + yyyyMM + ".xlsx", "������Ʒ��ϸ-" + yyyyMM + ".xlsx"}, "Ⱥ��");
		//EmailUtils.sendEmail(new String[] {"luzhijie@abckms.com"}, new String[] {"������ϸ��-" + yyyyMM + ".xlsx", "�ͻ��ۿ۵�������ϸ��(�г���)-" + yyyyMM + ".xlsx", "������Ʒ��ϸ-" + yyyyMM + ".xlsx"}, "Ⱥ��");
		// NC�ͻ���Ϣ���͸����۲�ĪС�� & Ӯ��ͨ��ɸ��
		EmailUtils.sendEmail(new String[] {"moxiaohong@abckms.com", "dingshaiyan@winchannel.net"}, new String[] {"NC�ͻ���Ϣ-" + yyyyMM + ".xlsx"}, "С�� & ɸ��");
		// ����
		EmailUtils.sendEmail(new String[] {"luzhijie@abckms.com"}, new String[] {"NC�ͻ���Ϣ-" + yyyyMM + ".xlsx"}, "�Լ�");
	}
	
	// ���۷�Ʊ��ϸ
	public void exportXSFPMX() {
		List<Map<String, Object>> dataList = reportMapper.getXSFPMX(params);
		String fileName = "���۷�Ʊ��ϸ-" + yyyyMM;
		String[][] headers = {{"��Ʊ��", "��Ʊ��"}, {"���ⵥ�ݺ�", "���ⵥ�ݺ�"}, {"Ӧ�յ���", "Ӧ�յ���"}, {"��Ʊ����", "��Ʊ����"}, 
				{"��������", "��������"}, {"��������", "��������"}, {"�ֿ�", "�ֿ�"}, {"�ͻ�", "�ͻ�"}, {"�ͻ����", "�ͻ����"},
				{"�ͻ�����", "�ͻ�����"}, {"Ԥ�����", "Ԥ�����"}, {"���ϱ���", "���ϱ���"}, {"������", "������"}, {"��������", "��������"}, 
				{"����������", "����������"}, {"�Ƶ���", "�Ƶ���"}, {"�к�", "�к�"}, {"��˰����", "��˰����"}, {"������ǰ��˰�ϼ�", "������ǰ��˰�ϼ�"},
				{"�����˰�ϼ�", "�����˰�ϼ�"}, {"���ǰ����", "���ǰ����"}, {"��Ʊ��˰�ϼ�", "��Ʊ��˰�ϼ�"}, {"��Ʊ��ǰ��˰�ϼ�", "��Ʊ��ǰ��˰�ϼ�"}, 
				{"��Ʊ����", "��Ʊ����"}, {"��Ʊ������", "��Ʊ������"}, {"��Ʊ�ۿ�", "��Ʊ�ۿ�"}, {"����״̬", "����״̬"}, {"��Ʒ��", "��Ʒ��"}, 
				{"Ʒ��", "Ʒ��"}, {"������", "������"}, {"�����", "�����"}, {"�۸�������֧��Ŀ", "�۸�������֧��Ŀ"}};
		ExcelUtils.exportExcel(fileName, headers, dataList);
	}
	
	// �ͻ��ۿ۵�������ϸ��-����-Wing �汾
	public void exportKHZKDXSMXB() {
		List<Map<String, Object>> dataList = reportMapper.getKHZKDXSMX_1(params);
		
		String fileName = "�ͻ��ۿ۵�������ϸ��-" + yyyyMM;
		
		String[][] headers = {{"������֯", "������֯"}, {"��������", "��������"}, {"��������", "��������"}, 
				{"�ͻ�����", "�ͻ�����"}, {"�ͻ�����", "�ͻ�����"}, {"�ͻ����õ���", "�ͻ����õ���"}, {"������", "������"}, {"���ⵥ��", "���ⵥ��"}, 
				{"��С���ⵥ��", "��С���ⵥ��"}, {"�ͻ����", "�ͻ����"}, {"Ʒ��", "Ʒ��"}, {"������", "������"},
				{"������", "������"}, {"ʵ������", "ʵ������"}, {"��˰�ϼ�", "��˰�ϼ�"}, {"��˰����", "��˰����"}, 
				{"�ۼƳ�ֽ��", "�ۼƳ�ֽ��"}, {"������", "������"}, {"���ó�ֽ��", "���ó�ֽ��"}, {"��˰�ܺϼ�", "��˰�ܺϼ�"}, {"������", "������"}, 
				{"�����Ƿ�ر�", "�����Ƿ�ر�"}, {"֧�ֽ��", "֧�ֽ��"}, {"��ֽ��", "��ֽ��"}, {"���", "���"},
				{"���˽��", "���˽��"}, {"�ͻ����õ�Ʒ��", "�ͻ����õ�Ʒ��"}, {"�ͻ����õ���֧��Ŀ", "�ͻ����õ���֧��Ŀ"}, 
				{"�ͻ����õ�״̬", "�ͻ����õ�״̬"}, {"�ͻ����õ�����", "�ͻ����õ�����"}, {"�ͻ����õ�����", "�ͻ����õ�����"}, {"��ע", "��ע"}, 
				{"���", "���"}, {"��������", "��������"}, {"��������״̬", "��������״̬"}, {"���ⵥ����״̬", "���ⵥ����״̬"}, 
				{"������ֽ��", "������ֽ��"}, {"������˽��", "������˽��"}, {"�����", "�����"}, {"���֧��Ŀ", "���֧��Ŀ"}}; 
		
		ExcelUtils.exportExcel(fileName, headers, dataList);
	}
	
	// �ͻ��ۿ۵�������ϸ��-�г���-Ⱥ�� �汾
	public void exportKHZKDXSMXB2() {
		List<Map<String, Object>> dataList = reportMapper.getKHZKDXSMX_2(params);
		
		String fileName = "�ͻ��ۿ۵�������ϸ��(�г���)-" + yyyyMM;
		
		String[][] headers = {{"������֯", "������֯"}, {"��������", "��������"}, {"��������", "��������"}, 
				{"�ͻ�����", "�ͻ�����"}, {"�ͻ�����", "�ͻ�����"}, {"�ͻ����õ���", "�ͻ����õ���"}, {"������", "������"}, {"���ⵥ��", "���ⵥ��"}, 
				{"��С���ⵥ��", "��С���ⵥ��"}, {"�ͻ����", "�ͻ����"}, {"Ʒ��", "Ʒ��"}, {"������", "������"},
				{"������", "������"}, {"ʵ������", "ʵ������"}, {"��˰�ϼ�", "��˰�ϼ�"}, {"��˰����", "��˰����"}, 
				{"�ۼƳ�ֽ��", "�ۼƳ�ֽ��"}, {"������", "������"}, {"���ó�ֽ��", "���ó�ֽ��"}, {"��˰�ܺϼ�", "��˰�ܺϼ�"}, {"������", "������"}, 
				{"�����Ƿ�ر�", "�����Ƿ�ر�"}, {"֧�ֽ��", "֧�ֽ��"}, {"��ֽ��", "��ֽ��"}, {"���", "���"},
				{"���˽��", "���˽��"}, {"�ͻ����õ�Ʒ��", "�ͻ����õ�Ʒ��"}, {"�ͻ����õ���֧��Ŀ", "�ͻ����õ���֧��Ŀ"}, 
				{"�ͻ����õ�״̬", "�ͻ����õ�״̬"}, {"�ͻ����õ�����", "�ͻ����õ�����"}, {"�ͻ����õ�����", "�ͻ����õ�����"}, {"��ע", "��ע"}, 
				{"���", "���"}, {"��������", "��������"}, {"��������״̬", "��������״̬"}, {"���ⵥ����״̬", "���ⵥ����״̬"}, 
				{"������ֽ��", "������ֽ��"}, {"������˽��", "������˽��"}, {"�����", "�����"}, {"���֧��Ŀ", "���֧��Ŀ"}}; 
		
		ExcelUtils.exportExcel(fileName, headers, dataList);
	}
	
	// ������ϸ��
	public void exportXSMXB() {
		List<Map<String, Object>> dataList = reportMapper.getXSMXB(params);
		String fileName = "������ϸ��-" + yyyyMM;
		String[][] headers = {{"���ϱ���", "���ϱ���"}, {"��������", "��������"}, {"��Ʒ���", "��Ʒ���"}, {"������", "������"}, 
				{"���Ϸ���", "���Ϸ���"}, {"Ʒ��", "Ʒ��"}, {"�ͻ����", "�ͻ����"}, {"�ͻ�����", "�ͻ�����"}, {"������λ", "������λ"},
				{"����", "����"}, {"����", "����"}, {"������֯", "������֯"}, {"�����֯", "�����֯"}, {"���۶�����", "���۶�����"}, 
				{"���۶�������", "���۶�������"}, {"��������", "��������"}, {"ժҪ", "ժҪ"}, {"�����������", "�����������"}, {"��˰����", "��˰����"},
				{"��˰����", "��˰����"}, {"�Ƿ��޶�", "�Ƿ��޶�"}, {"���ǰ����", "���ǰ����"}, {"����������", "����������"}, 
				{"��������", "��������"}, {"���ⵥ��", "���ⵥ��"}, {"���ⵥ��ע", "���ⵥ��ע"}, {"���ⵥ�Ƶ�ʱ��", "���ⵥ�Ƶ�ʱ��"}, {"���ⵥǩ��ʱ��", "���ⵥǩ��ʱ��"}, 
				{"����ֿ�", "����ֿ�"}, {"�Ƶ���", "�Ƶ���"}, {"������", "������"}, {"������˰�ϼ�", "������˰�ϼ�"}, 
				{"������ǰ��˰�ϼ�", "������ǰ��˰�ϼ�"}, {"����������", "����������"}, {"�����˰�ϼ�", "�����˰�ϼ�"}, {"������ǰ��˰�ϼ�", "������ǰ��˰�ϼ�"}, 
				{"���۳��ⵥ״̬", "���۳��ⵥ״̬"}, {"������״̬", "������״̬"}, {"����״̬", "����״̬"}};
		ExcelUtils.exportExcel(fileName, headers, dataList);
	}
	
	// ������Ʒ��ϸ
	public void exportZCXPMX() {
		List<Map<String, Object>> dataList = reportMapper.getZCXPMX(params);
		String fileName = "������Ʒ��ϸ-" + yyyyMM;
		String[][] headers = {{"�����֯", "�����֯"}, {"�ֿ�", "�ֿ�"}, {"��������", "��������"}, {"��֧��Ŀ", "��֧��Ŀ"}, 
				{"���Ϸ���", "���Ϸ���"}, {"�����", "�����"}, {"��������", "��������"}, {"��������", "��������"}, {"�Ƶ�����", "�Ƶ�����"},
				{"�޸�����", "�޸�����"}, {"��ע", "��ע"}, {"���ϱ���", "���ϱ���"}, {"��������", "��������"}, {"��Ʒ���", "��Ʒ���"}, 
				{"������", "������"}, {"������", "������"}, {"����", "����"}, {"���", "���"}, {"�Ƶ���", "�Ƶ���"},
				{"�к�", "�к�"}, {"�ͻ�", "�ͻ�"}, {"�ͻ�����", "�ͻ�����"}, {"����״̬", "����״̬"}};
		ExcelUtils.exportExcel(fileName, headers, dataList);
	}
	
	// �ͻ���Ϣ��
	public void exportKHXXB() {
		List<Map<String, Object>> dataList = reportMapper.getKHXXB();
		String fileName = "NC�ͻ���Ϣ-" + yyyyMM;
		String[][] headers = {{"�ͻ�����", "�ͻ�����"}, {"�ͻ�����", "�ͻ�����"}, {"�ͻ����", "�ͻ����"}, {"�ͻ���������", "�ͻ���������"}, 
				{"��������", "��������"}, {"ר�ܲ���", "ר�ܲ���"}, {"��������", "��������"}, {"����", "����"}, {"����", "����"},
				{"���������֯", "���������֯"}};
		ExcelUtils.exportExcel(fileName, headers, dataList);
	}
}