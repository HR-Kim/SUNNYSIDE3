package kr.co.sunnyside.store.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.sunnyside.store.service.SEJ_CartDao;
import kr.co.sunnyside.store.service.SEJ_CartVO;

@Repository
public class SEJ_CartDaoImpl implements SEJ_CartDao {
	Logger LOG=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE = "kr.co.sunnyside.cart"; 
	
	/**장바구니 추가*/
	@Override
	public void do_save(SEJ_CartVO vo) {
		String statement = NAMESPACE+".do_save";
		 sqlSession.insert(statement,vo);
	}
	
	/**장바구니 삭제*/
	@Override
	public void do_delete(int cartId) {
		String statement = NAMESPACE+".do_delete";
		 sqlSession.delete(statement,cartId);
	}
	
	/**장바구니 수정*/
	@Override
	public void do_update(SEJ_CartVO vo) {
		String statement = NAMESPACE+".do_update";
		 sqlSession.update(statement,vo);
	}
	
	/**장바구니 목록*/
	@Override
	public List<SEJ_CartVO> do_retrieve(String userId) {
		String statement = NAMESPACE+".do_retrieve";
		List<SEJ_CartVO> list = sqlSession.selectList(statement,userId);
		return list;
	}
	
	/**장바구니 금액 합계*/
	@Override
	public int sumMoney(String userId) {	
		String statement = NAMESPACE+".sumMoney";
		return sqlSession.selectOne(statement,userId);
	}
	
	/**장바구니 동일한 상품 레코드 확인*/
	@Override
	public int countCart(String productId, String userId) {
		String statement = NAMESPACE+".countCart";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productId", productId);
		map.put("userId", userId);
		
		return sqlSession.selectOne(statement,map);
	}

	/**장바구니 상품수량 변경*/
	@Override
	public void updateCountCart(SEJ_CartVO vo) {
		String statement = NAMESPACE+".updateCountCart";
		sqlSession.update(statement,vo);
	}


	


}
