package com.pojo;

import com.univocity.parsers.annotations.Parsed;

/* 
 * ʹ��Parsedע����Խ�CSV�ļ��е��������ֶν���ӳ�䡣�����ñ������������ֶ���ӳ�����ԣ�Ҳ�����������е��������Ž���ӳ��
 * ��Ҫʹ��ע��ӳ��Java Bean, ��Ҫ���ֶζ�Ӧ�������ϼ���@Parsedע��, ע���field���Զ�Ӧ������csv����,
 * ��csvWriterSettings.setHeaders��������header, ���У���к����Ե�ӳ��
 */
public class OutletItem {
	@Parsed(field="�������")
	private String tallyCity;
	@Parsed(field="��������")
	private String supplyCity;
	@Parsed(field="������и�����")
	private String tallyCityLeader;
	@Parsed(field="�������и�����")
	private String supplyCityLeader;
	@Parsed(field="DIS�ŵ����")
	private String storeCode;
	@Parsed(field="�ŵ�����")
	private String storeName;
	@Parsed(field="��������")
	private String channelType;
	@Parsed(field="��������")
	private String shopType;
	@Parsed(field="��������")
	private String supplyLevel;
	@Parsed(field="����ŵ�������Ա")
	private String tallyStoreStaff;
	@Parsed(field="����ŵ�������Ա�绰")
	private String tallyStoreStaffTel;
	@Parsed(field="ʡ��")
	private String province;
	@Parsed(field="������")
	private String district;
	@Parsed(field="·��+���ƺ�")
	private String address;
	@Parsed(field="��������")
	private String startDate;
	@Parsed(field="״̬")
	private String status;
	@Parsed(field="ͣ������")
	private String endDate;
	@Parsed(field="����������")
	private String dealerName;
	@Parsed(field="����������")
	private String distributorName;
	@Parsed(field="ϵͳ�����ܲ�����")
	private String systemRegionalWarehouseName;
	@Parsed(field="ϵͳ�����ֱܲ���")
	private String systemRegionalWarehouseCode;
	@Parsed(field="ϵͳ����")
	private String systemName;
	@Parsed(field="ϵͳ����")
	private String systemCode;
	@Parsed(field="�ؼ۲�����ǰ��������")
	private String aheadSupplyDays;
	@Parsed(field="�ؼ۲������������")
	private String endSupplyDays;
	@Parsed(field="Ԥ���ڲ���")
	private String department;
	@Parsed(field="�ͻ�����")
	private String customerType;
	@Parsed(field="����ʱ��")
	private String updateTime;
	@Parsed(field="�����̱���")
	private String dealerCode;
	@Parsed(field="�����̱���")
	private String distributorCode;
	@Parsed(field="�ŵ����ڳ���")
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