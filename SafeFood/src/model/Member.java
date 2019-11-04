package model;

import java.util.Arrays;

public class Member {
	private String id;
	private String pw;
	private String name;
	private String address;
	private String[] allergy;
	private String allergyStr;
	public Member(String pw, String name, String address, String[] allergy) {
		super();
		this.pw = pw;
		this.name = name;
		this.address = address;
		this.allergy = allergy;
	}
	public Member(String id, String pw, String name, String address, String allergyStr) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.address = address;
		this.allergyStr = allergyStr;
	}
	public String getAllergyStr() {
		return allergyStr;
	}
	public void setAllergyStr(String allergyStr) {
		this.allergyStr = allergyStr;
	}
	public Member() {
		super();
	}
	@Override
	public String toString() {
		return "Member [id=" + id + ", pw=" + pw + ", name=" + name + ", address=" + address + ", allergy="
				+ Arrays.toString(allergy) + "]";
	}
	public Member(String id, String pw, String name, String address, String[] allergy) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.address = address;
		this.allergy = allergy;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAllergy() {
		String s = "";
		for(int i=0;i<allergy.length-1;i++) {
			s+=allergy[i]+",";
		}
		s+=allergy[allergy.length-1];
		
		
		return s;
	}
	public void setAllergy(String[] allergy) {
		this.allergy = allergy;
	}


}
