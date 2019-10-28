package kr.co.sunnyside.cmn;

import kr.co.sunnyside.cmn.DTO;;

public class Line extends DTO {
	
	
	private String month;
	private int history;
	
	
	public Line() {}

	
	public Line(String month, int history) {
		super();
		this.month = month;
		this.history = history;
	}

	
	
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getHistory() {
		return history;
	}

	public void setHistory(int history) {
		this.history = history;
	}

	
	
	@Override
	public String toString() {
		return "Line [month=" + month + ", history=" + history + "]";
	}

	
	
}
