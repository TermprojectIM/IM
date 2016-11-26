package data;

import type.Rank;
//
public class UserInformationData {
	private String id=null,pw=null;
	private String name=null,phone=null;
	private Rank rank = Rank.Unrank;
	private boolean admin = false;
	
	public UserInformationData(String id,String pw, String name,String phone,int level){
		this.id=id;
		this.pw=pw;
		this.name=name;
		this.phone=phone;
		
		switch(level){
		case 1: rank = Rank.Unrank; break;
		case 2: rank = Rank.Bronze; break;
		case 3: rank = Rank.Silver; break;
		case 4: rank = Rank.Gold; break;
		default: admin = true; break;
		}
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public void setPW(String pw) {
		this.pw = pw;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPhoneNum(String phone) {
		this.phone = phone;
	}
	
	public void setLevel(int level){
		switch(level){
		case 1: rank = Rank.Unrank; break;
		case 2: rank = Rank.Bronze; break;
		case 3: rank = Rank.Silver; break;
		case 4: rank = Rank.Gold; break;
		default: rank = Rank.Unrank; break;
		}
	}
	
	public String getID() {
		return id;
	}
	
	public String getPW() {
		return pw;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPhoneNum() {
		return phone;
	}
	
	public Rank getLevel(){
		return rank;
	}
	public boolean getAdmin() {
		return admin;
	}	
}
