package data;

import DB.DBManager;

public class HelpInfo extends Info{
	public int order;
	public int start;
	public int end;
	public int month;
	public int day;
	public String name; // position
	public String name1; // name1~4
	public String name2;
	public String name3;
	public String name4;
	public int moveCost;
	public int sleepCost;
	public int eatCost;
	public String comment;
	DBManager db;
	public HelpInfo(String date, String name, int order, String name1, String name2, String name3, String name4) {
		super(date, name);
		this.name1 = name1;
		this.name2 = name2;
		this.name3 = name3;
		this.name4 = name4;
		this.order = order;
		String split2[] = date.split("-");
		month = Integer.parseInt(split2[0]);
		day = Integer.parseInt(split2[1]);
	}
	
	public HelpInfo(String name, int start, int end){
		this.name = name;
		this.start = start;
		this.end = end;
		moveCost = 2400;
		sleepCost = 30000;
		eatCost = 12000;
		comment = db.ReadComment(name);
	}
}
