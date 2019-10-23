package kr.co.sunnyside.userpage.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.cmn.WorkDiv;
import kr.co.sunnyside.phototicket.service.SEJ_PhotoTicketVO;
import kr.co.sunnyside.reservation.service.LGS_TicketVO;
import kr.co.sunnyside.userpage.service.KDY_CouponVO;
import kr.co.sunnyside.userpage.service.KDY_MoviehistoryVO;
import kr.co.sunnyside.userpage.service.KDY_ReservationVO;
import kr.co.sunnyside.userpage.service.KDY_UserinfoVO;
import kr.co.sunnyside.userpage.service.listSearchVO;

@Repository
public class KDY_UserpageDaoImpl implements WorkDiv {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "userpage";
	
	
	public List<?> do_reservationList(DTO dto) {
		String statement = this.NAMESPACE+".do_reservationList";//kr.co.ehr.user.get_retrieve
		listSearchVO search = (listSearchVO) dto;
		LOG.debug("========================");
		LOG.debug("01.param="+search);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("02.statement="+statement);
		LOG.debug("========================");
		
		List<KDY_ReservationVO> list = this.sqlSessionTemplate.selectList(statement, search);
		
		LOG.debug("========================");
		LOG.debug("03.outVO="+list);
		LOG.debug("========================");		
		return list;
	}
	public List<?> do_qnaList(DTO dto) {
		String statement = this.NAMESPACE+".do_qnaList";//kr.co.ehr.user.get_retrieve
		listSearchVO search = (listSearchVO) dto;
		LOG.debug("========================");
		LOG.debug("01.param="+search);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("02.statement="+statement);
		LOG.debug("========================");
		
		List<KDY_UserinfoVO> list = this.sqlSessionTemplate.selectList(statement, search);
		
		LOG.debug("========================");
		LOG.debug("03.outVO="+list);
		LOG.debug("========================");		
		return list;
	}
	
	public List<?> do_membership(DTO dto) {
		String statement = this.NAMESPACE+".do_membership";//kr.co.ehr.user.get_retrieve
		listSearchVO search = (listSearchVO) dto;
		LOG.debug("========================");
		LOG.debug("01.param="+search);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("02.statement="+statement);
		LOG.debug("========================");
		
		List<KDY_UserinfoVO> list = this.sqlSessionTemplate.selectList(statement, search);
		
		LOG.debug("========================");
		LOG.debug("03.outVO="+list);
		LOG.debug("========================");		
		return list;
	}
	
	
	
	public List<?> do_coupon_retrieve(DTO dto) {
		String statement = this.NAMESPACE + ".do_coupon_retrieve";
	
		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		listSearchVO search = (listSearchVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("search = " + search);
		LOG.debug("==================================");
		
		List<KDY_CouponVO> list = sqlSessionTemplate.selectList(statement, search);
		
		LOG.debug("==================================");
		LOG.debug("list = " + list);
		LOG.debug("==================================");
		
		return list;
	}
	



	@Override
	public List<?> do_retrieve(DTO dto) {
		String statement = this.NAMESPACE + ".do_retrieve";
	
		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		listSearchVO search = (listSearchVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("search = " + search);
		LOG.debug("==================================");
		
		List<KDY_MoviehistoryVO> list = sqlSessionTemplate.selectList(statement, search);
		
		LOG.debug("==================================");
		LOG.debug("list = " + list);
		LOG.debug("==================================");
		
		return list;
	}







	@Override
	public int do_update(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}







	@Override
	public int do_delete(DTO dto) {
		String statement = this.NAMESPACE + ".do_delete";
		
		LOG.debug("==================================");
		LOG.debug("statement = " + statement);
		LOG.debug("==================================");
		
		KDY_MoviehistoryVO MoviehistoryVO = (KDY_MoviehistoryVO) dto;
		
		LOG.debug("==================================");
		LOG.debug("MoviehistoryVO = " + MoviehistoryVO);
		LOG.debug("==================================");
		
		int flag = sqlSessionTemplate.delete(statement, MoviehistoryVO);
		
		LOG.debug("==================================");
		LOG.debug("flag = " + flag);
		LOG.debug("==================================");
		
		return flag;
	}







	@Override
	public int do_save(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}







	@Override
	public DTO do_selectOne(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}







	@Override
	public List<?> do_excelDown(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
