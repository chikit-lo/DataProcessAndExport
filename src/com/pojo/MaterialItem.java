package com.pojo;

import com.univocity.parsers.annotations.Parsed;

/* 
 * ʹ��Parsedע����Խ�CSV�ļ��е��������ֶν���ӳ�䡣�����ñ������������ֶ���ӳ�����ԣ�Ҳ�����������е��������Ž���ӳ��
 * ��Ҫʹ��ע��ӳ��Java Bean, ��Ҫ���ֶζ�Ӧ�������ϼ���@Parsedע��, ע���field���Զ�Ӧ������csv����,
 * ��csvWriterSettings.setHeaders��������header, ���У���к����Ե�ӳ��
 */
public class MaterialItem {
	@Parsed(field="���ϱ���")
	private String code;
	@Parsed(field="ԭ����")
	private String oldcode;
	@Parsed(field="������")
	private String materialmnecode;
	@Parsed(field="��������")
	private String name;
	@Parsed(field="������")
	private String materialbarcode;
	@Parsed(field="Ʒ��")
	private String brand;
	@Parsed(field="���Ϸ���")
	private String marbasclass;
	@Parsed(field="Ʒ��")
	private String category;
	@Parsed(field="���")
	private String surface;
	@Parsed(field="�䷽")
	private String formular;
	@Parsed(field="��״")
	private String shape;
	@Parsed(field="���")
	private String thickness;
	@Parsed(field="����")
	private String unitlength;
	@Parsed(field="��;")
	private String useage;
	@Parsed(field="���")
	private String materialspec;
	@Parsed(field="��װ����")
	private String packagetype;
	@Parsed(field="��Ʒ����")
	private String productattribute;
	@Parsed(field="����λ")
	private String auxiliaryunit;
	@Parsed(field="����λ")
	private String primaryunit;
	@Parsed(field="���ϼ۸�")
	private String price;
	@Parsed(field="��С��λ")
	private String minunit;
	@Parsed(field="������λ������")
	private String measrate;
	@Parsed(field="����˰��")
	private String tax;
	@Parsed(field="����")
	private String pk_material;
	@Parsed(field="DRֵ")
	private String dr;
	@Parsed(field="����ʱ��")
	private String creationtime;
	@Parsed(field="����޸�ʱ��")
	private String modifiedtime;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOldcode() {
		return oldcode;
	}
	public void setOldcode(String oldcode) {
		this.oldcode = oldcode;
	}
	public String getMaterialmnecode() {
		return materialmnecode;
	}
	public void setMaterialmnecode(String materialmnecode) {
		this.materialmnecode = materialmnecode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMaterialbarcode() {
		return materialbarcode;
	}
	public void setMaterialbarcode(String materialbarcode) {
		this.materialbarcode = materialbarcode;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getMarbasclass() {
		return marbasclass;
	}
	public void setMarbasclass(String marbasclass) {
		this.marbasclass = marbasclass;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSurface() {
		return surface;
	}
	public void setSurface(String surface) {
		this.surface = surface;
	}
	public String getFormular() {
		return formular;
	}
	public void setFormular(String formular) {
		this.formular = formular;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public String getThickness() {
		return thickness;
	}
	public void setThickness(String thickness) {
		this.thickness = thickness;
	}
	public String getUnitlength() {
		return unitlength;
	}
	public void setUnitlength(String unitlength) {
		this.unitlength = unitlength;
	}
	public String getUseage() {
		return useage;
	}
	public void setUseage(String useage) {
		this.useage = useage;
	}
	public String getMaterialspec() {
		return materialspec;
	}
	public void setMaterialspec(String materialspec) {
		this.materialspec = materialspec;
	}
	public String getPackagetype() {
		return packagetype;
	}
	public void setPackagetype(String packagetype) {
		this.packagetype = packagetype;
	}
	public String getProductattribute() {
		return productattribute;
	}
	public void setProductattribute(String productattribute) {
		this.productattribute = productattribute;
	}
	public String getAuxiliaryunit() {
		return auxiliaryunit;
	}
	public void setAuxiliaryunit(String auxiliaryunit) {
		this.auxiliaryunit = auxiliaryunit;
	}
	public String getPrimaryunit() {
		return primaryunit;
	}
	public void setPrimaryunit(String primaryunit) {
		this.primaryunit = primaryunit;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getMinunit() {
		return minunit;
	}
	public void setMinunit(String minunit) {
		this.minunit = minunit;
	}
	public String getMeasrate() {
		return measrate;
	}
	public void setMeasrate(String measrate) {
		this.measrate = measrate;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getPk_material() {
		return pk_material;
	}
	public void setPk_material(String pk_material) {
		this.pk_material = pk_material;
	}
	public String getDr() {
		return dr;
	}
	public void setDr(String dr) {
		this.dr = dr;
	}
	public String getCreationtime() {
		return creationtime;
	}
	public void setCreationtime(String creationtime) {
		this.creationtime = creationtime;
	}
	public String getModifiedtime() {
		return modifiedtime;
	}
	public void setModifiedtime(String modifiedtime) {
		this.modifiedtime = modifiedtime;
	}
	@Override
	public String toString() {
		return "[" + code + ", " + oldcode
				+ ", " + materialmnecode + ", " + name
				+ ", " + materialbarcode + ", " + brand
				+ ", " + marbasclass + ", " + category
				+ ", " + surface + ", " + formular
				+ ", " + shape + ", " + thickness
				+ ", " + unitlength + ", " + useage
				+ ", " + materialspec + ", "
				+ packagetype + ", " + productattribute
				+ ", " + auxiliaryunit + ", "
				+ primaryunit + ", " + price + ", " + minunit
				+ ", " + measrate + ", " + tax + ", "
				+ pk_material + ", " + dr + ", " + creationtime
				+ ", " + modifiedtime + "]";
	}
	
}