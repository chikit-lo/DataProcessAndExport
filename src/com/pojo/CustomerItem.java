package com.pojo;

import com.univocity.parsers.annotations.Parsed;

/* 
 * 使用Parsed注解可以将CSV文件中的属性与字段建立映射。可以用标题中声明的字段名映射属性，也可以用输入中的列索引号进行映射
 * 若要使用注解映射Java Bean, 需要在字段对应的属性上加上@Parsed注解, 注解的field属性对应导出的csv的列,
 * 若csvWriterSettings.setHeaders中设置了header, 则会校验列和属性的映射
 */
public class CustomerItem {
	@Parsed(field="主键")
	private String pk_customer;
	@Parsed(field="客户编码")
	private String code;
	@Parsed(field="旧编码")
	private String encode;
	@Parsed(field="客户名称")
	private String name;
	@Parsed(field="地区分类")
	private String area;
	@Parsed(field="客户简称")
	private String shortname;
	@Parsed(field="客户基本分类")
	private String custclass;
	@Parsed(field="企业地址")
	private String address;
	@Parsed(field="专管业务员")
	private String manager;
	@Parsed(field="电话")
	private String officephone;
	@Parsed(field="邮箱")
	private String mailbox;
	@Parsed(field="表体-客户联系人-联系人")
	private String contact;
	@Parsed(field="表体-客户联系人-电话")
	private String contacttel;
	@Parsed(field="Email")
	private String email;
	@Parsed(field="启用状态")
	private String status;
	@Parsed(field="启用时间")
	private String enabletime;
	@Parsed(field="停用时间")
	private String downtime;
	
	public String getPk_customer() {
		return pk_customer;
	}
	public void setPk_customer(String pk_customer) {
		this.pk_customer = pk_customer;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getEncode() {
		return encode;
	}
	public void setEncode(String encode) {
		this.encode = encode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getCustclass() {
		return custclass;
	}
	public void setCustclass(String custclass) {
		this.custclass = custclass;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getOfficephone() {
		return officephone;
	}
	public void setOfficephone(String officephone) {
		this.officephone = officephone;
	}
	public String getMailbox() {
		return mailbox;
	}
	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContacttel() {
		return contacttel;
	}
	public void setContacttel(String contacttel) {
		this.contacttel = contacttel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEnabletime() {
		return enabletime;
	}
	public void setEnabletime(String enabletime) {
		this.enabletime = enabletime;
	}
	public String getDowntime() {
		return downtime;
	}
	public void setDowntime(String downtime) {
		this.downtime = downtime;
	}

	public String toString() {
		return "CustomerItem [pk_customer=" + pk_customer + ", code=" + code
				+ ", encode=" + encode + ", name=" + name + ", area=" + area
				+ ", shortname=" + shortname + ", custclass=" + custclass
				+ ", address=" + address + ", manager=" + manager
				+ ", officephone=" + officephone + ", mailbox=" + mailbox
				+ ", contact=" + contact + ", contacttel=" + contacttel
				+ ", email=" + email + ", status=" + status + ", enabletime="
				+ enabletime + ", downtime=" + downtime + "]";
	}
}