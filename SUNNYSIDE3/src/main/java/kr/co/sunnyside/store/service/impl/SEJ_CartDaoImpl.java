package kr.co.sunnyside.store.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.co.sunnyside.store.service.SEJ_CartDao;
import kr.co.sunnyside.store.service.SEJ_CartVO;


public class SEJ_CartDaoImpl implements SEJ_CartDao {
	Logger LOG=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSession sqlSession;
	
	/**장바구니 추가*/
	@Override
	public void do_save(SEJ_CartVO vo) {
		 sqlSession.insert("cart.insertCart",vo);
	}
	
	/**장바구니 삭제*/
	@Override
	public void do_delete(int cartId) {
		 sqlSession.delete("cart.deleteCart",cartId);
	}
	
	/**장바구니 수정*/
	@Override
	public void do_update(SEJ_CartVO vo) {
		 sqlSession.update("cart.updateCart",vo);
	}
	
	/**장바구니 목록*/
	@Override
	public List<SEJ_CartVO> do_retrieve(String userId) {
		return sqlSession.selectList("cart.listCart",userId);
	}
	
	/**장바구니 금액 합계*/
	@Override
	public int sumMoney(String userId) {		
		return sqlSession.selectOne("cart.sumMoney",userId);
	}
	
	/**장바구니 동일한 상품 레코드 확인*/
	@Override
	public int countCart(String productId, String userId) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productId", productId);
		map.put("userId", userId);
		
		return sqlSession.selectOne("cart.countCart",map);
	}

	/**장바구니 상품수량 변경*/
	@Override
	public void updateCart(SEJ_CartVO vo) {
		sqlSession.update("cart.sumCart",vo);
	}


	


}
