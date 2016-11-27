package data;

public class Info {
	public String time;
	public String name;
	public String date;

	public Info(String date, String time, String name){
		this.date = date;
		this.time = time;
		this.name = name;
	}
	public Info(String date, String name){
		this.date = date;
		this.name = name;
	}
	public Info(){
		
	}
}
