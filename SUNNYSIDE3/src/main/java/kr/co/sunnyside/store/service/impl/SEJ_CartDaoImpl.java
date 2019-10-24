package kr.co.sunnyside.store.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.cmn.WorkDiv;
import kr.co.sunnyside.store.service.SEJ_CartVO;
@Repository
public class SEJ_CartDaoImpl implements WorkDiv{
	Logger LOG=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private final String NAMESPACE = "kr.co.sunnyside.cart"; 
	
	/**바로 결제하기 기능*/
	public int do_directPay(DTO dto) {
		String statement = NAMESPACE+".do_directPay";
		SEJ_CartVO store = (SEJ_CartVO) dto;
		LOG.debug("===================================");
		LOG.debug("=1. param="+store);
		LOG.debug("===================================");
		LOG.debug("===================================");
		LOG.debug("=2. statement="+statement);
		LOG.debug("===================================");
		int flag = this.sqlSessionTemplate.insert(statement,store);
		LOG.debug("===================================");
		LOG.debug("=3. flag="+flag);
		LOG.debug("===================================");
		
		return flag;
	}
	
	
	/**결제내역 추가*/
	public int do_payComplete(DTO dto) {
		String statement = NAMESPACE+".do_payComplete";
		SEJ_CartVO store = (SEJ_CartVO) dto;
		LOG.debug("===================================");
		LOG.debug("=1. param="+store);
		LOG.debug("===================================");
		LOG.debug("===================================");
		LOG.debug("=2. statement="+statement);
		LOG.debug("===================================");
		int flag = this.sqlSessionTemplate.insert(statement,store);
		LOG.debug("===================================");
		LOG.debug("=3. flag="+flag);
		LOG.debug("===================================");
		
		return flag;
	}
	
	/**결제내역 목록*/
	public List<?> do_payCompleteList(DTO dto) {
		String statement = NAMESPACE+".do_payCompleteList";
		SEJ_CartVO store = (SEJ_CartVO) dto;
		LOG.debug("===================================");
		LOG.debug("=1. param="+store);
		LOG.debug("===================================");
		LOG.debug("===================================");
		LOG.debug("=2. statement="+statement);
		LOG.debug("===================================");
		
		List<SEJ_CartVO> list= this.sqlSessionTemplate.selectList(statement,store);
		LOG.debug("===================================");
		LOG.debug("=3. flag="+list);
		LOG.debug("===================================");
		
		return list;
	}
	
	
	/**장바구니 추가*/
	@Override
	public int do_save(DTO dto) {
		String statement = NAMESPACE+".do_save";
		SEJ_CartVO store = (SEJ_CartVO) dto;
		LOG.debug("===================================");
		LOG.debug("=1. param="+store);
		LOG.debug("===================================");
		LOG.debug("===================================");
		LOG.debug("=2. statement="+statement);
		LOG.debug("===================================");
		int flag = this.sqlSessionTemplate.insert(statement,store);
		LOG.debug("===================================");
		LOG.debug("=3. flag="+flag);
		LOG.debug("===================================");
		
		return flag;
	}
	
	/**장바구니 삭제*/
	@Override
	public int do_delete(DTO dto) {
		String statement = NAMESPACE+".do_delete";
		SEJ_CartVO store = (SEJ_CartVO) dto;
		LOG.debug("===================================");
		LOG.debug("=1. param="+store);
		LOG.debug("===================================");
		LOG.debug("===================================");
		LOG.debug("=2. statement="+statement);
		LOG.debug("===================================");
		
		int flag = this.sqlSessionTemplate.delete(statement,store);
		LOG.debug("===================================");
		LOG.debug("=3. flag="+flag);
		LOG.debug("===================================");
		
		return flag;
	}
	
	/**장바구니 전체삭제*/
	public int do_deleteAll() {
		String statement = NAMESPACE+".do_deleteAll";
		LOG.debug("===================================");
		LOG.debug("=1. statement="+statement);
		LOG.debug("===================================");
		
		int flag = this.sqlSessionTemplate.delete(statement,"");
		LOG.debug("===================================");
		LOG.debug("=2. flag="+flag);
		LOG.debug("===================================");
		
		return flag;
	}
	
	/**장바구니 수정*/
	@Override
	public int do_update(DTO dto) {
		String statement = NAMESPACE+".do_update";
		SEJ_CartVO store = (SEJ_CartVO) dto;
		LOG.debug("===================================");
		LOG.debug("=1. param="+store);
		LOG.debug("===================================");
		LOG.debug("===================================");
		LOG.debug("=2. statement="+statement);
		LOG.debug("===================================");
		
		int flag = this.sqlSessionTemplate.update(statement,store);
		LOG.debug("===================================");
		LOG.debug("=3. flag="+flag);
		LOG.debug("===================================");
		return flag;
	}
	
	/**장바구니 목록*/
	@Override
	public List<?> do_retrieve(DTO dto) {
		String statement = NAMESPACE+".do_retrieve";
		SEJ_CartVO store = (SEJ_CartVO) dto;
		LOG.debug("===================================");
		LOG.debug("=1. param="+store);
		LOG.debug("===================================");
		LOG.debug("===================================");
		LOG.debug("=2. statement="+statement);
		LOG.debug("===================================");
		
		List<SEJ_CartVO> list= this.sqlSessionTemplate.selectList(statement,store);
		LOG.debug("===================================");
		LOG.debug("=3. flag="+list);
		LOG.debug("===================================");
		
		return list;
	}
	  
	@Override
	public DTO do_selectOne(DTO dto) {
		return dto;
	}
	
	/**장바구니 금액 총합계 */  
	public int do_totalCost(String userId) {
		String statement = NAMESPACE+".do_totalCost";
		int outVO = this.sqlSessionTemplate.selectOne(statement,userId); //이 역할을 해주는 것이 mybatis
		LOG.debug("=3. outVO="+outVO);
		LOG.debug("===================================");
		return outVO;
	}
	
	/**장바구니 동일한 상품 레코드 확인*/
	public int do_countCart(String productId, String userId) {
		String statement = NAMESPACE+".do_countCart";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productId", productId);
		map.put("userId", userId);
		
		return sqlSessionTemplate.selectOne(statement,map);
	}

	/**장바구니 상품수량 변경*/
	public int do_updateCountCart(DTO dto) {
		String statement = NAMESPACE+".do_updateCountCart";
		SEJ_CartVO store = (SEJ_CartVO) dto;
		LOG.debug("===================================");
		LOG.debug("=1. param="+store);
		LOG.debug("===================================");
		LOG.debug("===================================");
		LOG.debug("=2. statement="+statement);
		LOG.debug("===================================");
		
		int flag = this.sqlSessionTemplate.update(statement,store);
		LOG.debug("===================================");
		LOG.debug("=3. flag="+flag);
		LOG.debug("===================================");
		return flag;
	}


	@Override
	public List<?> do_excelDown(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	/**주문코드 생성후 업데이트*/
	public int do_make_codeNm(DTO dto) {
		String statement = NAMESPACE+".do_make_codeNm";
		SEJ_CartVO store = (SEJ_CartVO) dto;
		LOG.debug("===================================");
		LOG.debug("=1. param="+store);
		LOG.debug("===================================");
		LOG.debug("===================================");
		LOG.debug("=2. statement="+statement);
		LOG.debug("===================================");
		
		int flag = this.sqlSessionTemplate.update(statement,store);
		LOG.debug("===================================");
		LOG.debug("=3. flag="+flag);
		LOG.debug("===================================");
		
		return flag;
	}

	/**주문하기창 목록*/
	public List<?> do_payRetrieve(DTO dto) {
		String statement = NAMESPACE+".do_payRetrieve";
		SEJ_CartVO store = (SEJ_CartVO) dto;
		LOG.debug("===================================");
		LOG.debug("=1. param="+store);
		LOG.debug("===================================");
		LOG.debug("===================================");
		LOG.debug("=2. statement="+statement);
		LOG.debug("===================================");
		
		List<SEJ_CartVO> list= this.sqlSessionTemplate.selectList(statement,store);
		LOG.debug("===================================");
		LOG.debug("=3. flag="+list);
		LOG.debug("===================================");
		
		return list;
	}





	


	


}
