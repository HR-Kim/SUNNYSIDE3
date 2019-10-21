package kr.co.sunnyside.movie.service.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.movie.service.LHJ_MovieSvc;
import kr.co.sunnyside.movie.service.LHJ_MovieVO;

@Service
public class LHJ_MovieSvcImpl implements LHJ_MovieSvc {
	@Autowired
	private LHJ_MovieDaoImpl movieDaoImpl;
	
	@Override
	public DTO do_selectOne(DTO dto) {
		return movieDaoImpl.do_selectOne(dto);
	}

	@Override
	public List<?> do_retrieve(DTO dto) {
		return movieDaoImpl.do_retrieve(dto);
	}

	public void do_save(int startNum, int lastNum) {
		URL url;
		List<LHJ_MovieVO> list = new ArrayList<LHJ_MovieVO>();	
		
//		for(int i=0; i<74520; i++) {																
		for(int i=startNum; i<lastNum; i++) {																
			try {
				url = new URL(LHJ_MovieParsing.KmdbUrl(i));//url
				list=LHJ_MovieParsing.getKmdbData(url);//데이터를 List
				movieDaoImpl.do_save(list.get(0));//담아온 정보를 insert	
			} catch (Exception e) {
				continue; 
			}	
		}				
	}

	@Override
	public int do_visitorRate_update(DTO dto) {
		return movieDaoImpl.do_visitorRate_update(dto);
	}
	
	@Override
	public int do_save(DTO dto) {
		return 0;
	}

	@Override
	public int do_exist(DTO dto) {		
		return movieDaoImpl.do_exist(dto);
	}

}
