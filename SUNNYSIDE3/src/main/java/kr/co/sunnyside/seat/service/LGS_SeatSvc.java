package kr.co.sunnyside.seat.service;

import java.util.List;

import kr.co.sunnyside.cmn.DTO;

public interface LGS_SeatSvc {

	public int do_save(DTO dto);

	public int do_delete(DTO dto);
	
	public int do_update(DTO dto);

	public List<?> do_retrieve(DTO dto);

	public DTO do_selectOne(DTO dto);
	
	public int do_save_reservation(DTO dto);

	public int do_delete_reservation(DTO dto);
	
	public int do_update_reservation(DTO dto);

	public List<?> do_retrieve_reservation(DTO dto);

	public DTO do_selectOne_reservation(DTO dto);
	
}
