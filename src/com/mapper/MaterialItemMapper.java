package com.mapper;

import java.util.List;
import java.util.Map;

import com.pojo.MaterialItem;

public interface MaterialItemMapper {
	public int add(MaterialItem materialItem);
	public List<Map<String, Object>> getMaterialInfo();
	public String getBrand(String pk_brand);
	public String getMarbasclass(String pk_marbasclass);
	public String getDefdoc(String pk_defdoc);
	public String getAuxiliaryunit(String pk_material);
	public String getPrimaryunit(String pk_measdoc);
	public float getPrice(String pk_material);
	public String getMeasrate(String pk_material);
	public List<MaterialItem> getMaterialInfo2();
}