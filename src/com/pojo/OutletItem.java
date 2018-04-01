package com.pojo;

import com.univocity.parsers.annotations.Parsed;

/* 
 * 使用Parsed注解可以将CSV文件中的属性与字段建立映射。可以用标题中声明的字段名映射属性，也可以用输入中的列索引号进行映射
 * 若要使用注解映射Java Bean, 需要在字段对应的属性上加上@Parsed注解, 注解的field属性对应导出的csv的列,
 * 若csvWriterSettings.setHeaders中设置了header, 则会校验列和属性的映射
 */
public class OutletItem {
	@Parsed(field="理货城市")
	private String tallyCity;
	@Parsed(field="供货城市")
	private String supplyCity;
	@Parsed(field="理货城市负责人")
	private String tallyCityLeader;
	@Parsed(field="供货城市负责人")
	private String supplyCityLeader;
	@Parsed(field="DIS门店编码")
	private String storeCode;
	@Parsed(field="门店名称")
	private String storeName;
	@Parsed(field="渠道类型")
	private String channelType;
	@Parsed(field="店铺类型")
	private String shopType;
	@Parsed(field="供货级别")
	private String supplyLevel;
	@Parsed(field="理货门店销售人员")
	private String tallyStoreStaff;
	@Parsed(field="理货门店销售人员电话")
	private String tallyStoreStaffTel;
	@Parsed(field="省份")
	private String province;
	@Parsed(field="行政区")
	private String district;
	@Parsed(field="路名+门牌号")
	private String address;
	@Parsed(field="开店日期")
	private String startDate;
	@Parsed(field="状态")
	private String status;
	@Parsed(field="停用日期")
	private String endDate;
	@Parsed(field="经销商名称")
	private String dealerName;
	@Parsed(field="分销商名称")
	private String distributorName;
	@Parsed(field="系统区域总仓名称")
	private String systemRegionalWarehouseName;
	@Parsed(field="系统区域总仓编码")
	private String systemRegionalWarehouseCode;
	@Parsed(field="系统名称")
	private String systemName;
	@Parsed(field="系统编码")
	private String systemCode;
	@Parsed(field="特价补差提前供货天数")
	private String aheadSupplyDays;
	@Parsed(field="特价补差供货结束天数")
	private String endSupplyDays;
	@Parsed(field="预算归口部门")
	private String department;
	@Parsed(field="客户类型")
	private String customerType;
	@Parsed(field="更新时间")
	private String updateTime;
	@Parsed(field="经销商编码")
	private String dealerCode;
	@Parsed(field="分销商编码")
	private String distributorCode;
	@Parsed(field="门店所在城市")
	private String storeCity;
	
	public String getTallyCity() {
		return tallyCity;
	}
	public void setTallyCity(String tallyCity) {
		this.tallyCity = tallyCity;
	}
	public String getSupplyCity() {
		return supplyCity;
	}
	public void setSupplyCity(String supplyCity) {
		this.supplyCity = supplyCity;
	}
	public String getTallyCityLeader() {
		return tallyCityLeader;
	}
	public void setTallyCityLeader(String tallyCityLeader) {
		this.tallyCityLeader = tallyCityLeader;
	}
	public String getSupplyCityLeader() {
		return supplyCityLeader;
	}
	public void setSupplyCityLeader(String supplyCityLeader) {
		this.supplyCityLeader = supplyCityLeader;
	}
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public String getShopType() {
		return shopType;
	}
	public void setShopType(String shopType) {
		this.shopType = shopType;
	}
	public String getSupplyLevel() {
		return supplyLevel;
	}
	public void setSupplyLevel(String supplyLevel) {
		this.supplyLevel = supplyLevel;
	}
	public String getTallyStoreStaff() {
		return tallyStoreStaff;
	}
	public void setTallyStoreStaff(String tallyStoreStaff) {
		this.tallyStoreStaff = tallyStoreStaff;
	}
	public String getTallyStoreStaffTel() {
		return tallyStoreStaffTel;
	}
	public void setTallyStoreStaffTel(String tallyStoreStaffTel) {
		this.tallyStoreStaffTel = tallyStoreStaffTel;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getDistributorName() {
		return distributorName;
	}
	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}
	public String getSystemRegionalWarehouseName() {
		return systemRegionalWarehouseName;
	}
	public void setSystemRegionalWarehouseName(String systemRegionalWarehouseName) {
		this.systemRegionalWarehouseName = systemRegionalWarehouseName;
	}
	public String getSystemRegionalWarehouseCode() {
		return systemRegionalWarehouseCode;
	}
	public void setSystemRegionalWarehouseCode(String systemRegionalWarehouseCode) {
		this.systemRegionalWarehouseCode = systemRegionalWarehouseCode;
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
	public String getAheadSupplyDays() {
		return aheadSupplyDays;
	}
	public void setAheadSupplyDays(String aheadSupplyDays) {
		this.aheadSupplyDays = aheadSupplyDays;
	}
	public String getEndSupplyDays() {
		return endSupplyDays;
	}
	public void setEndSupplyDays(String endSupplyDays) {
		this.endSupplyDays = endSupplyDays;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getDealerCode() {
		return dealerCode;
	}
	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}
	public String getDistributorCode() {
		return distributorCode;
	}
	public void setDistributorCode(String distributorCode) {
		this.distributorCode = distributorCode;
	}
	public String getStoreCity() {
		return storeCity;
	}
	public void setStoreCity(String storeCity) {
		this.storeCity = storeCity;
	}
	
	@Override
	public String toString() {
		return "OutletItem [tallyCity=" + tallyCity + ", supplyCity="
				+ supplyCity + ", tallyCityLeader=" + tallyCityLeader
				+ ", supplyCityLeader=" + supplyCityLeader + ", storeCode="
				+ storeCode + ", storeName=" + storeName + ", channelType="
				+ channelType + ", shopType=" + shopType + ", supplyLevel="
				+ supplyLevel + ", tallyStoreStaff=" + tallyStoreStaff
				+ ", tallyStoreStaffTel=" + tallyStoreStaffTel + ", province="
				+ province + ", district=" + district + ", address=" + address
				+ ", startDate=" + startDate + ", status=" + status
				+ ", endDate=" + endDate + ", dealerName=" + dealerName
				+ ", distributorName=" + distributorName
				+ ", systemRegionalWarehouseName="
				+ systemRegionalWarehouseName
				+ ", systemRegionalWarehouseCode="
				+ systemRegionalWarehouseCode + ", systemName=" + systemName
				+ ", systemCode=" + systemCode + ", aheadSupplyDays="
				+ aheadSupplyDays + ", endSupplyDays=" + endSupplyDays
				+ ", department=" + department + ", customerType="
				+ customerType + ", updateTime=" + updateTime + ", dealerCode="
				+ dealerCode + ", distributorCode=" + distributorCode
				+ ", storeCity=" + storeCity + ", getTallyCity()="
				+ getTallyCity() + ", getSupplyCity()=" + getSupplyCity()
				+ ", getTallyCityLeader()=" + getTallyCityLeader()
				+ ", getSupplyCityLeader()=" + getSupplyCityLeader()
				+ ", getStoreCode()=" + getStoreCode() + ", getStoreName()="
				+ getStoreName() + ", getChannelType()=" + getChannelType()
				+ ", getShopType()=" + getShopType() + ", getSupplyLevel()="
				+ getSupplyLevel() + ", getTallyStoreStaff()="
				+ getTallyStoreStaff() + ", getTallyStoreStaffTel()="
				+ getTallyStoreStaffTel() + ", getProvince()=" + getProvince()
				+ ", getDistrict()=" + getDistrict() + ", getAddress()="
				+ getAddress() + ", getStartDate()=" + getStartDate()
				+ ", getStatus()=" + getStatus() + ", getEndDate()="
				+ getEndDate() + ", getDealerName()=" + getDealerName()
				+ ", getDistributorName()=" + getDistributorName()
				+ ", getSystemRegionalWarehouseName()="
				+ getSystemRegionalWarehouseName()
				+ ", getSystemRegionalWarehouseCode()="
				+ getSystemRegionalWarehouseCode() + ", getSystemName()="
				+ getSystemName() + ", getSystemCode()=" + getSystemCode()
				+ ", getAheadSupplyDays()=" + getAheadSupplyDays()
				+ ", getEndSupplyDays()=" + getEndSupplyDays()
				+ ", getDepartment()=" + getDepartment()
				+ ", getCustomerType()=" + getCustomerType()
				+ ", getUpdateTime()=" + getUpdateTime() + ", getDealerCode()="
				+ getDealerCode() + ", getDistributorCode()="
				+ getDistributorCode() + ", getStoreCity()=" + getStoreCity()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
}