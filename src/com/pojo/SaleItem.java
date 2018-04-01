package com.pojo;

import com.univocity.parsers.annotations.Parsed;

/* 
 * 使用Parsed注解可以将CSV文件中的属性与字段建立映射。可以用标题中声明的字段名映射属性，也可以用输入中的列索引号进行映射
 * 若要使用注解映射Java Bean, 需要在字段对应的属性上加上@Parsed注解, 注解的field属性对应导出的csv的列,
 * 若csvWriterSettings.setHeaders中设置了header, 则会校验列和属性的映射
 */
public class SaleItem {
	@Parsed(field="物料编码")
	private String materialCode;
	@Parsed(field="经销商产品金额(含税计算)")
	private String agencyTaxAmount;
	@Parsed(field="NC客户编码")
	private String ncCustCode;
	@Parsed(field="经销商名称")
	private String agencyName;
	@Parsed(field="经销商编码")
	private String agencyCode;
	@Parsed(field="分销商名称")
	private String distributorName;
	@Parsed(field="分销商编码")
	private String distributorCode;
	@Parsed(field="单据编号")
	private String billno;
	@Parsed(field="单据日期")
	private String billdate;
	@Parsed(field="单据类型")
	private String billtype;
	@Parsed(field="库存地点")
	private String storeLoc;
	@Parsed(field="经销商处客户编码")
	private String agencyCustCode;
	@Parsed(field="经销商处客户名称")
	private String agencyCustName;
	@Parsed(field="客户标准编码")
	private String custStdCode;
	@Parsed(field="客户标准名称")
	private String custStdName;
	@Parsed(field="经销商产品编码")
	private String agencyProductCode;
	@Parsed(field="经销商产品名称")
	private String agencyProductName;
	@Parsed(field="经销商产品单位")
	private String agencyProductUnit;
	@Parsed(field="经销商产品数量")
	private String agencyProductCount;
	@Parsed(field="经销商产品金额")
	private String agencyProductAmount;
	@Parsed(field="产品标准编码")
	private String productStdCode;
	@Parsed(field="产品标准名称")
	private String productStdName;
	@Parsed(field="统计单位数量")
	private String unitCount;
	@Parsed(field="统计单位")
	private String unit;
	@Parsed(field="产品标准金额")
	private String productStdAmount;
	@Parsed(field="基本单位")
	private String basicUnit;
	@Parsed(field="基本单位数量")
	private String basicUnitCount;
	@Parsed(field="经销商类型")
	private String distributorType;
	@Parsed(field="经销商产品价格(未税计算)")
	private String distributorProductAmount1;
	@Parsed(field="经销商产品金额(计算)")
	private String distributorProductPrice1;
	@Parsed(field="经销商产品金额计算")
	private String distributorProductAmount2;
	@Parsed(field="经销商产品价格计算")
	private String distributorProductPrice2;
	@Parsed(field="回传日期")
	private String returnDate;
	@Parsed(field="系统区域总仓名称")
	private String systemRegionStockName;
	@Parsed(field="系统区域总仓编码")
	private String systemRegionStockCode;
	@Parsed(field="系统名称")
	private String systemName;
	@Parsed(field="系统编码")
	private String systemCode;
	@Parsed(field="经销商产品价格(含税计算)")
	private String agencyTaxPrice;
	@Parsed(field="正常出货价(含税)")
	private String normalTaxPrice;
	@Parsed(field="是否原价出货")
	private String isOriginalPrice;
	
	public String getMaterialCode() {
		return materialCode;
	}
	public String getAgencyTaxAmount() {
		return agencyTaxAmount;
	}
	public void setAgencyTaxAmount(String agencyTaxAmount) {
		this.agencyTaxAmount = agencyTaxAmount;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getNcCustCode() {
		return ncCustCode;
	}
	public void setNcCustCode(String ncCustCode) {
		this.ncCustCode = ncCustCode;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public String getAgencyCode() {
		return agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	public String getDistributorName() {
		return distributorName;
	}
	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}
	public String getDistributorCode() {
		return distributorCode;
	}
	public void setDistributorCode(String distributorCode) {
		this.distributorCode = distributorCode;
	}
	public String getBillno() {
		return billno;
	}
	public void setBillno(String billno) {
		this.billno = billno;
	}
	public String getBilldate() {
		return billdate;
	}
	public void setBilldate(String billdate) {
		this.billdate = billdate;
	}
	public String getBilltype() {
		return billtype;
	}
	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}
	public String getStoreLoc() {
		return storeLoc;
	}
	public void setStoreLoc(String storeLoc) {
		this.storeLoc = storeLoc;
	}
	public String getAgencyCustCode() {
		return agencyCustCode;
	}
	public void setAgencyCustCode(String agencyCustCode) {
		this.agencyCustCode = agencyCustCode;
	}
	public String getAgencyCustName() {
		return agencyCustName;
	}
	public void setAgencyCustName(String agencyCustName) {
		this.agencyCustName = agencyCustName;
	}
	public String getCustStdCode() {
		return custStdCode;
	}
	public void setCustStdCode(String custStdCode) {
		this.custStdCode = custStdCode;
	}
	public String getCustStdName() {
		return custStdName;
	}
	public void setCustStdName(String custStdName) {
		this.custStdName = custStdName;
	}
	public String getAgencyProductCode() {
		return agencyProductCode;
	}
	public void setAgencyProductCode(String agencyProductCode) {
		this.agencyProductCode = agencyProductCode;
	}
	public String getAgencyProductName() {
		return agencyProductName;
	}
	public void setAgencyProductName(String agencyProductName) {
		this.agencyProductName = agencyProductName;
	}
	public String getAgencyProductUnit() {
		return agencyProductUnit;
	}
	public void setAgencyProductUnit(String agencyProductUnit) {
		this.agencyProductUnit = agencyProductUnit;
	}
	public String getAgencyProductCount() {
		return agencyProductCount;
	}
	public void setAgencyProductCount(String agencyProductCount) {
		this.agencyProductCount = agencyProductCount;
	}
	public String getAgencyProductAmount() {
		return agencyProductAmount;
	}
	public void setAgencyProductAmount(String agencyProductAmount) {
		this.agencyProductAmount = agencyProductAmount;
	}
	public String getProductStdCode() {
		return productStdCode;
	}
	public void setProductStdCode(String productStdCode) {
		this.productStdCode = productStdCode;
	}
	public String getProductStdName() {
		return productStdName;
	}
	public void setProductStdName(String productStdName) {
		this.productStdName = productStdName;
	}
	public String getUnitCount() {
		return unitCount;
	}
	public void setUnitCount(String unitCount) {
		this.unitCount = unitCount;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getProductStdAmount() {
		return productStdAmount;
	}
	public void setProductStdAmount(String productStdAmount) {
		this.productStdAmount = productStdAmount;
	}
	public String getBasicUnit() {
		return basicUnit;
	}
	public void setBasicUnit(String basicUnit) {
		this.basicUnit = basicUnit;
	}
	public String getBasicUnitCount() {
		return basicUnitCount;
	}
	public void setBasicUnitCount(String basicUnitCount) {
		this.basicUnitCount = basicUnitCount;
	}
	public String getDistributorType() {
		return distributorType;
	}
	public void setDistributorType(String distributorType) {
		this.distributorType = distributorType;
	}
	public String getDistributorProductAmount1() {
		return distributorProductAmount1;
	}
	public void setDistributorProductAmount1(String distributorProductAmount1) {
		this.distributorProductAmount1 = distributorProductAmount1;
	}
	public String getDistributorProductPrice1() {
		return distributorProductPrice1;
	}
	public void setDistributorProductPrice1(String distributorProductPrice1) {
		this.distributorProductPrice1 = distributorProductPrice1;
	}
	public String getDistributorProductAmount2() {
		return distributorProductAmount2;
	}
	public void setDistributorProductAmount2(String distributorProductAmount2) {
		this.distributorProductAmount2 = distributorProductAmount2;
	}
	public String getDistributorProductPrice2() {
		return distributorProductPrice2;
	}
	public void setDistributorProductPrice2(String distributorProductPrice2) {
		this.distributorProductPrice2 = distributorProductPrice2;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getSystemRegionStockName() {
		return systemRegionStockName;
	}
	public void setSystemRegionStockName(String systemRegionStockName) {
		this.systemRegionStockName = systemRegionStockName;
	}
	public String getSystemRegionStockCode() {
		return systemRegionStockCode;
	}
	public void setSystemRegionStockCode(String systemRegionStockCode) {
		this.systemRegionStockCode = systemRegionStockCode;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getSystemCode() {
		return systemCode;
	}
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	public String getAgencyTaxPrice() {
		return agencyTaxPrice;
	}
	public void setAgencyTaxPrice(String agencyTaxPrice) {
		this.agencyTaxPrice = agencyTaxPrice;
	}
	public String getNormalTaxPrice() {
		return normalTaxPrice;
	}
	public void setNormalTaxPrice(String normalTaxPrice) {
		this.normalTaxPrice = normalTaxPrice;
	}
	public String getIsOriginalPrice() {
		return isOriginalPrice;
	}
	public void setIsOriginalPrice(String isOriginalPrice) {
		this.isOriginalPrice = isOriginalPrice;
	}
}