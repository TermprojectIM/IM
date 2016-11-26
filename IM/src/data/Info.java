package data;

public class Info {
	public String time;
	public String name;
	public String date;
	public int start;
	public int end; 
	public int month;
	public int day;
	public Info(String date, String time, String name){
		this.date = date;
		this.time = time;
		this.name = name;
		String split1[] = time.split("-");
		start = Integer.parseInt(split1[0]);
		end = Integer.parseInt(split1[1]);
		String split2[] = date.split("-");
		month = Integer.parseInt(split2[0]);
		day = Integer.parseInt(split2[1]);
	}
}
