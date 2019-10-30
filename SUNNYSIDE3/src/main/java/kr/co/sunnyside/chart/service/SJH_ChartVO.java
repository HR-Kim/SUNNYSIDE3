package kr.co.sunnyside.chart.service;

import kr.co.sunnyside.cmn.DTO;;

public class SJH_ChartVO extends DTO {
	
	private String ticketCode;
	private String userId;
	private String month;
	private int history;
	
	
	public SJH_ChartVO() {}


	public SJH_ChartVO(String month, int history) {
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
		return "SJH_ChartVO [month=" + month + ", history=" + history + "]";
	}


	
	


	
}
