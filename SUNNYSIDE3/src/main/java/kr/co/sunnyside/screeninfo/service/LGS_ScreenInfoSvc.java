package kr.co.sunnyside.screeninfo.service;

import java.util.List;

import kr.co.sunnyside.cmn.DTO;

public interface LGS_ScreenInfoSvc {

	public int do_save(DTO dto);

	public int do_delete(DTO dto);

	public int do_update(DTO dto);

	public List<?> do_retrieve(DTO dto);

	public DTO do_selectOne(DTO dto);
	
	public List<?> do_retrieve_forUser(DTO dto);
	
	public List<?> do_retrieve_branchNroom(DTO dto);
	
	public List<?> do_retrieve_All_branchNroom();
}
