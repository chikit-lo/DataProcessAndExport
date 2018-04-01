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
	
	// 导出NC系统报表数据
	// 每月1号0点30分执行
	@Scheduled(cron="0 30 0 1 * ?")
	public void exportReports() {
		getExportDate();
		System.out.println("报表定时导出任务开始...");
		System.out.println("导出日期区间: " + startDate + " ~ " + endDate + "...");
		
		exportKHXXB();
		exportZCXPMX();
		exportXSMXB();
		exportXSFPMX();
		exportKHZKDXSMXB();
		exportKHZKDXSMXB2();
		System.out.println("报表定时导出任务结束...");
		
		sendExport();
		System.out.println("邮件发送完毕...");
	}
	
	// 将导出报表邮件方式发送给相关同事
	public void sendExport() {
		// 销售发票明细、客户折扣单销售明细表发送给财务部梁霭泳
		EmailUtils.sendEmail(new String[] {"liangaiyong@abckms.com"}, new String[] {"销售发票明细-" + yyyyMM + ".xlsx", "客户折扣单销售明细表-" + yyyyMM + ".xlsx"}, "Wing");
		//EmailUtils.sendEmail(new String[] {"luzhijie@abckms.com"}, new String[] {"销售发票明细-" + yyyyMM + ".xlsx", "客户折扣单销售明细表-" + yyyyMM + ".xlsx"}, "Wing");
		// 助促销品明细、客户折扣单销售明细表(市场部)发送给市场部区结群
		EmailUtils.sendEmail(new String[] {"queenie@abckms.com"}, new String[] {"销售明细表-" + yyyyMM + ".xlsx", "客户折扣单销售明细表(市场部)-" + yyyyMM + ".xlsx", "助促销品明细-" + yyyyMM + ".xlsx"}, "群姐");
		//EmailUtils.sendEmail(new String[] {"luzhijie@abckms.com"}, new String[] {"销售明细表-" + yyyyMM + ".xlsx", "客户折扣单销售明细表(市场部)-" + yyyyMM + ".xlsx", "助促销品明细-" + yyyyMM + ".xlsx"}, "群姐");
		// NC客户信息发送给销售部莫小红 & 赢销通丁筛艳
		EmailUtils.sendEmail(new String[] {"moxiaohong@abckms.com", "dingshaiyan@winchannel.net"}, new String[] {"NC客户信息-" + yyyyMM + ".xlsx"}, "小红 & 筛艳");
		// 测试
		EmailUtils.sendEmail(new String[] {"luzhijie@abckms.com"}, new String[] {"NC客户信息-" + yyyyMM + ".xlsx"}, "自己");
	}
	
	// 销售发票明细
	public void exportXSFPMX() {
		List<Map<String, Object>> dataList = reportMapper.getXSFPMX(params);
		String fileName = "销售发票明细-" + yyyyMM;
		String[][] headers = {{"发票号", "发票号"}, {"出库单据号", "出库单据号"}, {"应收单号", "应收单号"}, {"发票日期", "发票日期"}, 
				{"出库日期", "出库日期"}, {"单据类型", "单据类型"}, {"仓库", "仓库"}, {"客户", "客户"}, {"客户简称", "客户简称"},
				{"客户编码", "客户编码"}, {"预算地区", "预算地区"}, {"物料编码", "物料编码"}, {"助记码", "助记码"}, {"出库数量", "出库数量"}, 
				{"出库主数量", "出库主数量"}, {"制单人", "制单人"}, {"行号", "行号"}, {"含税单价", "含税单价"}, {"出库折前价税合计", "出库折前价税合计"},
				{"出库价税合计", "出库价税合计"}, {"冲减前单价", "冲减前单价"}, {"发票价税合计", "发票价税合计"}, {"发票折前价税合计", "发票折前价税合计"}, 
				{"发票数量", "发票数量"}, {"发票主数量", "发票主数量"}, {"发票折扣", "发票折扣"}, {"单据状态", "单据状态"}, {"产品线", "产品线"}, 
				{"品牌", "品牌"}, {"换算率", "换算率"}, {"活动单号", "活动单号"}, {"价格促销活动收支项目", "价格促销活动收支项目"}};
		ExcelUtils.exportExcel(fileName, headers, dataList);
	}
	
	// 客户折扣单销售明细表-财务部-Wing 版本
	public void exportKHZKDXSMXB() {
		List<Map<String, Object>> dataList = reportMapper.getKHZKDXSMX_1(params);
		
		String fileName = "客户折扣单销售明细表-" + yyyyMM;
		
		String[][] headers = {{"财务组织", "财务组织"}, {"部门名称", "部门名称"}, {"出库日期", "出库日期"}, 
				{"客户编码", "客户编码"}, {"客户名称", "客户名称"}, {"客户费用单号", "客户费用单号"}, {"订单号", "订单号"}, {"出库单号", "出库单号"}, 
				{"最小出库单号", "最小出库单号"}, {"客户简称", "客户简称"}, {"品牌", "品牌"}, {"生产线", "生产线"},
				{"助记码", "助记码"}, {"实发数量", "实发数量"}, {"价税合计", "价税合计"}, {"含税净价", "含税净价"}, 
				{"累计冲抵金额", "累计冲抵金额"}, {"主数量", "主数量"}, {"费用冲抵金额", "费用冲抵金额"}, {"价税总合计", "价税总合计"}, {"冲减金额", "冲减金额"}, 
				{"发货是否关闭", "发货是否关闭"}, {"支持金额", "支持金额"}, {"冲抵金额", "冲抵金额"}, {"余额", "余额"},
				{"记账金额", "记账金额"}, {"客户费用单品牌", "客户费用单品牌"}, {"客户费用单收支项目", "客户费用单收支项目"}, 
				{"客户费用单状态", "客户费用单状态"}, {"客户费用单类型", "客户费用单类型"}, {"客户费用单日期", "客户费用单日期"}, {"备注", "备注"}, 
				{"年份", "年份"}, {"订单日期", "订单日期"}, {"订单单据状态", "订单单据状态"}, {"出库单单据状态", "出库单单据状态"}, 
				{"订单冲抵金额", "订单冲抵金额"}, {"出库记账金额", "出库记账金额"}, {"活动单号", "活动单号"}, {"活动收支项目", "活动收支项目"}}; 
		
		ExcelUtils.exportExcel(fileName, headers, dataList);
	}
	
	// 客户折扣单销售明细表-市场部-群姐 版本
	public void exportKHZKDXSMXB2() {
		List<Map<String, Object>> dataList = reportMapper.getKHZKDXSMX_2(params);
		
		String fileName = "客户折扣单销售明细表(市场部)-" + yyyyMM;
		
		String[][] headers = {{"财务组织", "财务组织"}, {"部门名称", "部门名称"}, {"出库日期", "出库日期"}, 
				{"客户编码", "客户编码"}, {"客户名称", "客户名称"}, {"客户费用单号", "客户费用单号"}, {"订单号", "订单号"}, {"出库单号", "出库单号"}, 
				{"最小出库单号", "最小出库单号"}, {"客户简称", "客户简称"}, {"品牌", "品牌"}, {"生产线", "生产线"},
				{"助记码", "助记码"}, {"实发数量", "实发数量"}, {"价税合计", "价税合计"}, {"含税净价", "含税净价"}, 
				{"累计冲抵金额", "累计冲抵金额"}, {"主数量", "主数量"}, {"费用冲抵金额", "费用冲抵金额"}, {"价税总合计", "价税总合计"}, {"冲减金额", "冲减金额"}, 
				{"发货是否关闭", "发货是否关闭"}, {"支持金额", "支持金额"}, {"冲抵金额", "冲抵金额"}, {"余额", "余额"},
				{"记账金额", "记账金额"}, {"客户费用单品牌", "客户费用单品牌"}, {"客户费用单收支项目", "客户费用单收支项目"}, 
				{"客户费用单状态", "客户费用单状态"}, {"客户费用单类型", "客户费用单类型"}, {"客户费用单日期", "客户费用单日期"}, {"备注", "备注"}, 
				{"年份", "年份"}, {"订单日期", "订单日期"}, {"订单单据状态", "订单单据状态"}, {"出库单单据状态", "出库单单据状态"}, 
				{"订单冲抵金额", "订单冲抵金额"}, {"出库记账金额", "出库记账金额"}, {"活动单号", "活动单号"}, {"活动收支项目", "活动收支项目"}}; 
		
		ExcelUtils.exportExcel(fileName, headers, dataList);
	}
	
	// 销售明细表
	public void exportXSMXB() {
		List<Map<String, Object>> dataList = reportMapper.getXSMXB(params);
		String fileName = "销售明细表-" + yyyyMM;
		String[][] headers = {{"物料编码", "物料编码"}, {"物料名称", "物料名称"}, {"商品规格", "商品规格"}, {"助记码", "助记码"}, 
				{"物料分类", "物料分类"}, {"品牌", "品牌"}, {"客户简称", "客户简称"}, {"客户名称", "客户名称"}, {"计量单位", "计量单位"},
				{"部门", "部门"}, {"城区", "城区"}, {"财务组织", "财务组织"}, {"库存组织", "库存组织"}, {"销售订单号", "销售订单号"}, 
				{"销售订单日期", "销售订单日期"}, {"单据类型", "单据类型"}, {"摘要", "摘要"}, {"订单审核日期", "订单审核日期"}, {"含税单价", "含税单价"},
				{"含税净价", "含税净价"}, {"是否修订", "是否修订"}, {"冲抵前单价", "冲抵前单价"}, {"订单主数量", "订单主数量"}, 
				{"出库日期", "出库日期"}, {"出库单号", "出库单号"}, {"出库单备注", "出库单备注"}, {"出库单制单时间", "出库单制单时间"}, {"出库单签字时间", "出库单签字时间"}, 
				{"出库仓库", "出库仓库"}, {"制单人", "制单人"}, {"换算率", "换算率"}, {"订单价税合计", "订单价税合计"}, 
				{"订单折前价税合计", "订单折前价税合计"}, {"出库主数量", "出库主数量"}, {"出库价税合计", "出库价税合计"}, {"出库折前价税合计", "出库折前价税合计"}, 
				{"销售出库单状态", "销售出库单状态"}, {"订单行状态", "订单行状态"}, {"订单状态", "订单状态"}};
		ExcelUtils.exportExcel(fileName, headers, dataList);
	}
	
	// 助促销品明细
	public void exportZCXPMX() {
		List<Map<String, Object>> dataList = reportMapper.getZCXPMX(params);
		String fileName = "助促销品明细-" + yyyyMM;
		String[][] headers = {{"库存组织", "库存组织"}, {"仓库", "仓库"}, {"单据类型", "单据类型"}, {"收支项目", "收支项目"}, 
				{"物料分类", "物料分类"}, {"出库号", "出库号"}, {"出库日期", "出库日期"}, {"单据日期", "单据日期"}, {"制单日期", "制单日期"},
				{"修改日期", "修改日期"}, {"备注", "备注"}, {"物料编码", "物料编码"}, {"物料名称", "物料名称"}, {"商品规格", "商品规格"}, 
				{"助记码", "助记码"}, {"主数量", "主数量"}, {"数量", "数量"}, {"金额", "金额"}, {"制单人", "制单人"},
				{"行号", "行号"}, {"客户", "客户"}, {"客户编码", "客户编码"}, {"单据状态", "单据状态"}};
		ExcelUtils.exportExcel(fileName, headers, dataList);
	}
	
	// 客户信息表
	public void exportKHXXB() {
		List<Map<String, Object>> dataList = reportMapper.getKHXXB();
		String fileName = "NC客户信息-" + yyyyMM;
		String[][] headers = {{"客户编码", "客户编码"}, {"客户名称", "客户名称"}, {"客户简称", "客户简称"}, {"客户基本分类", "客户基本分类"}, 
				{"地区分类", "地区分类"}, {"专管部门", "专管部门"}, {"所属区域", "所属区域"}, {"大区", "大区"}, {"分区", "分区"},
				{"结算财务组织", "结算财务组织"}};
		ExcelUtils.exportExcel(fileName, headers, dataList);
	}
}