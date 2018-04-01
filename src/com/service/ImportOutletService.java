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
	
	// 处理FTP的渠道网点数据
	@Scheduled(cron="0 10 1 * * ?")
	public void processData() {
		System.out.println("DIS渠道网点档案数据加工开始...");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		//String filename = "Outlet" + sdf.format(new Date()) + ".csv";
		String filename = "Outlet20171013.csv";
		
		// 读取渠道网点数据
		List<OutletItem> outletList = readFile(filename);
		if(outletList == null)
			return;
		
		// 将渠道网点数据插入到临时表
		prepareTempData(outletList);
		
		// 更新临时表数据
		updateTempData();
		
		// 从临时表返回数据并回写文件
		returnAndExportData(filename);
	}

	private void returnAndExportData(String filename) {
		// 重新遍历更新过后的临时表数据, 并将此结果返回到csv文件中
		List<OutletItem> returnList = outletItemMapper.selectOutletData();
		System.out.println("从临时表temp_outlet返回更新后渠道网点数据...");
		
		String[] headers = {"理货城市","供货城市","理货城市负责人","供货城市负责人","DIS门店编码","门店名称","渠道类型","店铺类型","供货级别","理货门店销售人员","理货门店销售人员电话","省份","行政区","路名+门牌号","开店日期","状态","停用日期","经销商名称","分销商名称","系统区域总仓名称","系统区域总仓编码","系统名称","系统编码","特价补差提前供货天数","特价补差供货结束天数","预算归口部门","客户类型","更新时间","经销商编码","分销商编码","门店所在城市"};
		// 将更新后的渠道网点数据导出到本地
		CSVUtils.exportCsvFileToLocal(filename, headers, returnList, "UTF-8");
		// 将更新后的渠道网点数据导出到FTP服务器
		CSVUtils.exportCsvFileToFTP(filename, headers, returnList, FTPProperties.IMPORTDIR, "UTF-8");
	}

	private void updateTempData() {
		// 更新临时表数据, 包括"状态"、"特价补差提前供货天数"、"特价补差供货结束天数"、"门店所在城市"、"供货城市"
		outletItemMapper.updateOutletStatus();
		outletItemMapper.updateOutletAheadSupplyDays();
		outletItemMapper.updateOutletEndSupplyDays();
		outletItemMapper.updateOutletStoreCity();
		outletItemMapper.updateOutletSupplyCity();
		System.out.println("临时表temp_outlet数据更新完成...");
		
		// 根据临时表的系统名称, 遍历自定义档案表的系统名称, 若系统名称不在自定义档案表中, 则将"系统编码"、"系统名称"插入到自定义档案表
		if(!outletItemMapper.selectDistinctSystem().isEmpty()) {
			List<Map<String, String>> systemMap = outletItemMapper.selectDistinctSystem();
			List<Map<String, String>> paramList = new ArrayList<Map<String,String>>();
			for (Map<String, String> map : systemMap) {
				Map<String, String> params = new HashMap<String, String>();
				if(map.get("系统编码").equals(map.get("系统名称")) || map.get("系统编码").length() > 20) {
					params.put("code", "~");
				} else {
					params.put("code", map.get("系统编码"));
				}
				params.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				params.put("name", map.get("系统名称"));
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
			System.out.println("系统信息插入表bd_defdoc完成...");
		}
		
		// 根据临时表的系统区域总仓名称, 遍历自定义档案表的系统区域总仓名称, 若系统区域总仓名称不在自定义档案表中, 则将"系统区域总仓编码"、"系统区域总仓名称"插入到自定义档案表
		if(!outletItemMapper.selectDistinctRegionSystem().isEmpty()) {
			List<Map<String, String>> systemMap = outletItemMapper.selectDistinctRegionSystem();
			List<Map<String, String>> paramList = new ArrayList<Map<String,String>>();
			for (Map<String, String> map : systemMap) {
				Map<String, String> params = new HashMap<String, String>();
				if(map.get("系统区域总仓编码").equals(map.get("系统区域总仓名称")) || map.get("系统区域总仓编码").length() > 20) {
					params.put("code", "~");
				} else {
					params.put("code", map.get("系统区域总仓编码"));
				}
				params.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				params.put("name", map.get("系统区域总仓名称"));
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
			System.out.println("系统区域总仓信息插入表bd_defdoc完成...");
		}
		
		// 根据临时表的行政区, 遍历行政区划表的行政区划名称, 若行政区不在行政区划表中, 则插表
		if(!outletItemMapper.selectDistinctRegion().isEmpty()) {
			int count = outletItemMapper.selectCountRegion();
			List<Map<String, String>> regionMap = outletItemMapper.selectDistinctRegion();
			List<Map<String, String>> paramList = new ArrayList<Map<String,String>>();
			for (Map<String, String> map : regionMap) {
				Map<String, String> params = new HashMap<String, String>();
				params.put("code", "X" + ++count);
				params.put("name", map.get("行政区"));
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
			System.out.println("行政区插入表bd_region完成...");
		}
	}

	private void prepareTempData(List<OutletItem> outletList) {
		// 统计总插入记录数
		int totalCount = 0;
		// 统计经销商编码错误的记录数
		int custErrorCount = 0;
		// 统计经销商编码为空的记录数
		int custNullCount = 0;
		
		boolean custErrorFlag = false;
		boolean custNullFlag = false;
		
		// 清空临时表数据
		outletItemMapper.truncateOutletTable();
		System.out.println("临时表temp_outlet数据清空完成...");
		
		// 遍历csv文件把数据插入临时表
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
		
		System.out.println("渠道网点数据插入临时表temp_outlet, 共处理: " + totalCount + "条数据");
		if(custErrorFlag)
			System.out.println("其中有" + custErrorCount + "条数据的经销商编码有异常");
		if(custNullFlag)
			System.out.println("其中有" + custNullCount + "条数据的经销商编码为空");
	}

	private List<OutletItem> readFile(String filename) {
		// 从FTP服务器获取当天的渠道网点数据
		List<OutletItem> outletList = CSVUtils.importCsvFileFromFTP(filename, new OutletItem(), "UTF-8");
		
		// 从本地获取渠道网点数据
		//List<OutletItem> outletList = CSVUtils.importCsvFileFromLocal(filename, new OutletItem(), "UTF-8");
		
		// 如果找不到渠道网点数据或者数据为空, 返回空
		if(outletList == null) {
			System.out.println("找不到渠道网点数据" + filename);
			return null;
		} else if (outletList.size() == 0) {
			System.out.println("渠道网点数据" + filename + "为空");
			return null;
		}
		return outletList;
	}
	
	@Override
	public void run() {
		this.processData();
	}
}