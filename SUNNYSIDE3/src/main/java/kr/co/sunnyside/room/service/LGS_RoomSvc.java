package kr.co.sunnyside.room.service;

import java.util.List;

import kr.co.sunnyside.cmn.DTO;

public interface LGS_RoomSvc {
	
	public int do_save(DTO dto);

	public int do_delete(DTO dto);

	public int do_update(DTO dto);

	public List<?> get_retrieve(DTO dto);

	public DTO get_selectOne(DTO dto);
}
