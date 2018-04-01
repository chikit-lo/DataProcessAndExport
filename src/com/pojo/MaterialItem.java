package com.pojo;

import com.univocity.parsers.annotations.Parsed;

/* 
 * 使用Parsed注解可以将CSV文件中的属性与字段建立映射。可以用标题中声明的字段名映射属性，也可以用输入中的列索引号进行映射
 * 若要使用注解映射Java Bean, 需要在字段对应的属性上加上@Parsed注解, 注解的field属性对应导出的csv的列,
 * 若csvWriterSettings.setHeaders中设置了header, 则会校验列和属性的映射
 */
public class MaterialItem {
	@Parsed(field="物料编码")
	private String code;
	@Parsed(field="原编码")
	private String oldcode;
	@Parsed(field="助记码")
	private String materialmnecode;
	@Parsed(field="物料名称")
	private String name;
	@Parsed(field="条形码")
	private String materialbarcode;
	@Parsed(field="品牌")
	private String brand;
	@Parsed(field="物料分类")
	private String marbasclass;
	@Parsed(field="品类")
	private String category;
	@Parsed(field="表层")
	private String surface;
	@Parsed(field="配方")
	private String formular;
	@Parsed(field="形状")
	private String shape;
	@Parsed(field="厚度")
	private String thickness;
	@Parsed(field="长度")
	private String unitlength;
	@Parsed(field="用途")
	private String useage;
	@Parsed(field="规格")
	private String materialspec;
	@Parsed(field="包装类型")
	private String packagetype;
	@Parsed(field="产品属性")
	private String productattribute;
	@Parsed(field="辅单位")
	private String auxiliaryunit;
	@Parsed(field="主单位")
	private String primaryunit;
	@Parsed(field="物料价格")
	private String price;
	@Parsed(field="最小单位")
	private String minunit;
	@Parsed(field="计量单位换算率")
	private String measrate;
	@Parsed(field="物料税率")
	private String tax;
	@Parsed(field="主键")
	private String pk_material;
	@Parsed(field="DR值")
	private String dr;
	@Parsed(field="创建时间")
	private String creationtime;
	@Parsed(field="最后修改时间")
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