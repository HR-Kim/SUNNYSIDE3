package kr.co.sunnyside.movie.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.movie.service.LHJ_BoxofficeSvc;
import kr.co.sunnyside.movie.service.LHJ_MovieVO;

@Service
public class LHJ_BoxofficeSvcImpl implements LHJ_BoxofficeSvc {

	@Autowired
	private LHJ_BoxofficeDaoImpl boxofficeDaoImpl;

	/**박스오피스 상태값 0으로 초기화(OFF)*/	
	@Override
	public int do_boxofficeOff_update(DTO dto) {
		return boxofficeDaoImpl.do_boxofficeOff_update();
	}

	/**박스오피스 상태값 1으로 초기화(On)*/	
	@Override
	public int do_boxofficeOn_update(DTO dto) {
		return boxofficeDaoImpl.do_boxofficeOn_update(dto);
	}

	/**박스오피스 삭제 */	
	@Override
	public int do_delete() {
		return boxofficeDaoImpl.do_delete();
	}

	/**박스오피스 단건조회*/
	@Override
	public DTO do_selectOne(DTO dto) {
		return boxofficeDaoImpl.do_selectOne(dto);
	}

	/**박스오피스 전체조회*/
	@Override
	public List<?> do_retrieve() {
		//조회시마다 박스오피스 데이터 delete 한 뒤 insert
//		boxofficeDaoImpl.do_delete();
//		List<LHJ_MovieVO> kobisList = LHJ_MovieParsing.getBoxofficeList();
//		for(LHJ_MovieVO vo : kobisList) {
//			boxofficeDaoImpl.do_save(vo);
//		}
		return boxofficeDaoImpl.do_retrieve();
	}

	/**박스오피스 테이블에 movieId, 순위 정보 저장*/	
	@Override
	public int do_save(DTO dto) {
		return boxofficeDaoImpl.do_save(dto);
	}

	@Override
	public int do_delete(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<?> do_retrieve(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> do_excelDown(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int do_update(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}



}
