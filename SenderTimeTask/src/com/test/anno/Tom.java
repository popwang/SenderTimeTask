package com.test.anno;

@Person(name="jerry",main="xx@oo.com",age=18)
public class Tom {
	private String name;
	private String main;
	private Integer age;
	@FieldValue("xx_oo_yy_cc")
	private String address;
	@FieldValue("0100020222")
	private String tel;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
