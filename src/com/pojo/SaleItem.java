package com.pojo;

import com.univocity.parsers.annotations.Parsed;

/* 
 * ʹ��Parsedע����Խ�CSV�ļ��е��������ֶν���ӳ�䡣�����ñ������������ֶ���ӳ�����ԣ�Ҳ�����������е��������Ž���ӳ��
 * ��Ҫʹ��ע��ӳ��Java Bean, ��Ҫ���ֶζ�Ӧ�������ϼ���@Parsedע��, ע���field���Զ�Ӧ������csv����,
 * ��csvWriterSettings.setHeaders��������header, ���У���к����Ե�ӳ��
 */
public class SaleItem {
	@Parsed(field="���ϱ���")
	private String materialCode;
	@Parsed(field="�����̲�Ʒ���(��˰����)")
	private String agencyTaxAmount;
	@Parsed(field="NC�ͻ�����")
	private String ncCustCode;
	@Parsed(field="����������")
	private String agencyName;
	@Parsed(field="�����̱���")
	private String agencyCode;
	@Parsed(field="����������")
	private String distributorName;
	@Parsed(field="�����̱���")
	private String distributorCode;
	@Parsed(field="���ݱ��")
	private String billno;
	@Parsed(field="��������")
	private String billdate;
	@Parsed(field="��������")
	private String billtype;
	@Parsed(field="���ص�")
	private String storeLoc;
	@Parsed(field="�����̴��ͻ�����")
	private String agencyCustCode;
	@Parsed(field="�����̴��ͻ�����")
	private String agencyCustName;
	@Parsed(field="�ͻ���׼����")
	private String custStdCode;
	@Parsed(field="�ͻ���׼����")
	private String custStdName;
	@Parsed(field="�����̲�Ʒ����")
	private String agencyProductCode;
	@Parsed(field="�����̲�Ʒ����")
	private String agencyProductName;
	@Parsed(field="�����̲�Ʒ��λ")
	private String agencyProductUnit;
	@Parsed(field="�����̲�Ʒ����")
	private String agencyProductCount;
	@Parsed(field="�����̲�Ʒ���")
	private String agencyProductAmount;
	@Parsed(field="��Ʒ��׼����")
	private String productStdCode;
	@Parsed(field="��Ʒ��׼����")
	private String productStdName;
	@Parsed(field="ͳ�Ƶ�λ����")
	private String unitCount;
	@Parsed(field="ͳ�Ƶ�λ")
	private String unit;
	@Parsed(field="��Ʒ��׼���")
	private String productStdAmount;
	@Parsed(field="������λ")
	private String basicUnit;
	@Parsed(field="������λ����")
	private String basicUnitCount;
	@Parsed(field="����������")
	private String distributorType;
	@Parsed(field="�����̲�Ʒ�۸�(δ˰����)")
	private String distributorProductAmount1;
	@Parsed(field="�����̲�Ʒ���(����)")
	private String distributorProductPrice1;
	@Parsed(field="�����̲�Ʒ������")
	private String distributorProductAmount2;
	@Parsed(field="�����̲�Ʒ�۸����")
	private String distributorProductPrice2;
	@Parsed(field="�ش�����")
	private String returnDate;
	@Parsed(field="ϵͳ�����ܲ�����")
	private String systemRegionStockName;
	@Parsed(field="ϵͳ�����ֱܲ���")
	private String systemRegionStockCode;
	@Parsed(field="ϵͳ����")
	private String systemName;
	@Parsed(field="ϵͳ����")
	private String systemCode;
	@Parsed(field="�����̲�Ʒ�۸�(��˰����)")
	private String agencyTaxPrice;
	@Parsed(field="����������(��˰)")
	private String normalTaxPrice;
	@Parsed(field="�Ƿ�ԭ�۳���")
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