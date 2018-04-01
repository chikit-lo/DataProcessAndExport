package com.mapper;

import java.util.List;
import java.util.Map;

import com.pojo.CustomerItem;

public interface CustomerItemMapper {
	public int add(CustomerItem customerItem);
	public List<Map<String, Object>> getCustomerInfo();
	public List<CustomerItem> getCustomerInfo2();
}