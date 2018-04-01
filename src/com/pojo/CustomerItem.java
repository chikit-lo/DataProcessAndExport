package com.pojo;

import com.univocity.parsers.annotations.Parsed;

/* 
 * ʹ��Parsedע����Խ�CSV�ļ��е��������ֶν���ӳ�䡣�����ñ������������ֶ���ӳ�����ԣ�Ҳ�����������е��������Ž���ӳ��
 * ��Ҫʹ��ע��ӳ��Java Bean, ��Ҫ���ֶζ�Ӧ�������ϼ���@Parsedע��, ע���field���Զ�Ӧ������csv����,
 * ��csvWriterSettings.setHeaders��������header, ���У���к����Ե�ӳ��
 */
public class CustomerItem {
	@Parsed(field="����")
	private String pk_customer;
	@Parsed(field="�ͻ�����")
	private String code;
	@Parsed(field="�ɱ���")
	private String encode;
	@Parsed(field="�ͻ�����")
	private String name;
	@Parsed(field="��������")
	private String area;
	@Parsed(field="�ͻ����")
	private String shortname;
	@Parsed(field="�ͻ���������")
	private String custclass;
	@Parsed(field="��ҵ��ַ")
	private String address;
	@Parsed(field="ר��ҵ��Ա")
	private String manager;
	@Parsed(field="�绰")
	private String officephone;
	@Parsed(field="����")
	private String mailbox;
	@Parsed(field="����-�ͻ���ϵ��-��ϵ��")
	private String contact;
	@Parsed(field="����-�ͻ���ϵ��-�绰")
	private String contacttel;
	@Parsed(field="Email")
	private String email;
	@Parsed(field="����״̬")
	private String status;
	@Parsed(field="����ʱ��")
	private String enabletime;
	@Parsed(field="ͣ��ʱ��")
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