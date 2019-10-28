package kr.co.sunnyside.coupon.service.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.WorkDiv;
import kr.co.sunnyside.coupon.service.SJH_CouponVO;
import kr.co.sunnyside.login.service.impl.SJH_LoginDao;
import kr.co.sunnyside.usermypage.service.SJH_MypageVO;

@Repository
public class SJH_CouponDao implements WorkDiv {

	Logger LOG = LoggerFactory.getLogger(SJH_LoginDao.class);
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "kr.co.sunnyside.coupon";
	
	
	/**
	 * 쿠폰발급
	 */
	@Override
	public int do_save(DTO dto) {
		String statement = this.NAMESPACE+".do_save";
		SJH_CouponVO coupon = (SJH_CouponVO) dto;
		
		LOG.debug("========================");
		LOG.debug("=01.param="+coupon);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("=02.statement="+statement);
		LOG.debug("========================");
		
		int flag = this.sqlSessionTemplate.insert(statement, coupon);
		
		LOG.debug("========================");
		LOG.debug("03.flag="+flag);
		LOG.debug("========================");	
		
		return flag;
	}	
	
	
	
	/**
	 * 쿠폰삭제 (사용 시)
	 */
	@Override
	public int do_delete(DTO dto) {
		String statement = this.NAMESPACE+".do_delete";
		SJH_CouponVO coupon = (SJH_CouponVO) dto;
		
		LOG.debug("========================");
		LOG.debug("=01.param="+coupon);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("=02.statement="+statement);
		LOG.debug("========================");
		
		int flag = this.sqlSessionTemplate.delete(statement, coupon);
		
		LOG.debug("========================");
		LOG.debug("03.flag="+flag);
		LOG.debug("========================");	
		
		return flag;
	}
	
	
	@Override
	public DTO do_selectOne(DTO dto) {
		String statement = this.NAMESPACE+".do_selectOne";
		SJH_CouponVO inVO = (SJH_CouponVO) dto;
		
		LOG.debug("========================");
		LOG.debug("=01.param="+inVO);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("=02.statement="+statement);
		LOG.debug("========================");
		
		SJH_CouponVO outVO = this.sqlSessionTemplate.selectOne(statement, inVO);
		
		LOG.debug("========================");
		LOG.debug("03.outVO="+outVO);
		LOG.debug("========================");	
		
		return outVO;
	}	
	
	
	@Override
	public List<?> do_retrieve(DTO dto) {
		String statement = this.NAMESPACE+".do_retrieve";
		SJH_CouponVO inVO = (SJH_CouponVO) dto;
		
		LOG.debug("========================");
		LOG.debug("=01.param="+inVO);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("=02.statement="+statement);
		LOG.debug("========================");
		
		List<SJH_CouponVO> outVO = this.sqlSessionTemplate.selectList(statement, inVO);
		
		LOG.debug("========================");
		LOG.debug("03.outVO="+outVO);
		LOG.debug("========================");	
		
		return outVO;
	}

	
	@Override
	public int do_update(DTO dto) {
		String statement = this.NAMESPACE+".do_update";
		SJH_CouponVO coupon = (SJH_CouponVO) dto;
		
		LOG.debug("========================");
		LOG.debug("=01.param="+coupon);
		LOG.debug("========================");
		
		LOG.debug("========================");
		LOG.debug("=02.statement="+statement);
		LOG.debug("========================");
		
		int flag = this.sqlSessionTemplate.update(statement, coupon);
		
		LOG.debug("========================");
		LOG.debug("03.flag="+flag);
		LOG.debug("========================");	
		
		return flag;
	}


	@Override
	public List<?> do_excelDown(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
