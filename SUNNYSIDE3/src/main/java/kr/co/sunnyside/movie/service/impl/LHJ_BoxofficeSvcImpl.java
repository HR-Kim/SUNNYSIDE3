package kr.co.sunnyside.movie.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.movie.service.LHJ_BoxofficeSvc;
import kr.co.sunnyside.movie.service.LHJ_MovieVO;

@Service
public class LHJ_BoxofficeSvcImpl implements LHJ_BoxofficeSvc {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private LHJ_BoxofficeDaoImpl boxofficeDaoImpl;
	
	@Autowired
	private LHJ_MovieDaoImpl movieDaoImpl;

	
	/**매일 오전 9시마다 박스오피스 데이터 delete 한 뒤 insert*/
	public void do_deleteAndSave() {
		
			boxofficeDaoImpl.do_delete();
			
			List<LHJ_MovieVO> kobisList = LHJ_MovieParsing.getBoxofficeList();
			LOG.debug("============================");
		 	LOG.debug("=kobisList="+kobisList);
		 	LOG.debug("============================");	
			
		 	int count = 0;
		 	for(LHJ_MovieVO vo : kobisList) {
		 		count = movieDaoImpl.do_exist(vo); //kobis(박스오피스)에서 읽어온 영화정보가 movie테이블에 없으면
		 		if(count == 0) {
		 			//kmdb에서 영화제목과 개봉일자를 이용하여 url을 구하고, 데이터를 가져온다.
		 			LOG.debug(vo.getKortitle()+"kmdb에서 영화제목과 개봉일자를 이용하여 url을 구하고, 데이터를 가져온다.");
		 			List<LHJ_MovieVO> kmdbTitleList = LHJ_MovieParsing.getMovieSearchList(vo.getKortitle(), vo.getRelDate());
		 			for(LHJ_MovieVO kmdbVO : kmdbTitleList) {
		 				LOG.debug("============================");
		 				LOG.debug("=kmdbTitleList="+kmdbTitleList);
		 				LOG.debug("============================");	
		 				LOG.debug("============================");
		 			 	LOG.debug("=제목="+kmdbVO.getKortitle());
		 			 	LOG.debug("============================");	
		 			 	try {
		 			 		movieDaoImpl.do_save(kmdbVO); //가져온 데이터를 movie테이블에 save(insert)한다.
		 			 	} catch (Exception e) {
		 					LOG.debug("============================");
		 					LOG.debug("Exception:"+e.toString());
		 					LOG.debug("============================");
		 					continue; 
		 				}	
		 			}
		 		}
		 	}	
	 	
		for(LHJ_MovieVO vo : kobisList) {
			boxofficeDaoImpl.do_save(vo);
		}
		
	}
	
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

	/**박스오피스 메인 전체조회(8)*/
	@Override
	public List<?> do_retrieve_main() {
		return boxofficeDaoImpl.do_retrieve_main();
	}
	
	/**박스오피스 전체조회*/
	@Override
	public List<?> do_retrieve() {
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
