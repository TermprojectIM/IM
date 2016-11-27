package data;

public class SelfInfo extends Info{
	public int start;
	public int end; 
	public int month;
	public int day;
	public int moveCost;
	public int sleepCost;
	public int eatCost;
	public SelfInfo(String date, String time, String name) {
		super(date, time, name);
		String split1[] = time.split("-");
		start = Integer.parseInt(split1[0]);
		end = Integer.parseInt(split1[1]);
		String split2[] = date.split("-");
		month = Integer.parseInt(split2[0]);
		day = Integer.parseInt(split2[1]);
		moveCost = 2400;
		sleepCost = 30000;
		eatCost = 12000;
	}

}
