package kr.co.sunnyside.chart.service;

import java.util.List;

import kr.co.sunnyside.cmn.DTO;


public interface SJH_ChartSvc {
	
	/** 일년 간 예매내역 전체조회 */
	public int do_getAll(DTO dto);
	
}
