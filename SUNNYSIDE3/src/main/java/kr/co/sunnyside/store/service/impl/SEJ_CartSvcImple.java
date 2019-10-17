package kr.co.sunnyside.store.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.store.service.SEJ_CartSvc;
import kr.co.sunnyside.store.service.SEJ_CartVO;

@Service
public class SEJ_CartSvcImple implements SEJ_CartSvc {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	//dao 등록
	@Autowired
	private SEJ_CartDaoImpl cartDaoImpl;
	
	/**장바구니 저장 */
	@Override
	public void do_save(SEJ_CartVO vo) {
		LOG.debug("==================");
		LOG.debug("=@Service do_save="+vo);
		LOG.debug("==================");
		
		cartDaoImpl.do_save(vo);
	}
	
	/**장바구니 삭제 */
	@Override
	public void do_delete(int cartId) {
		LOG.debug("==================");
		LOG.debug("=@Service do_delete="+cartId);
		LOG.debug("==================");
		
		cartDaoImpl.do_delete(cartId);
	}

	/**장바구니 수정 */
	@Override
	public void do_update(SEJ_CartVO vo) {
		LOG.debug("==================");
		LOG.debug("=@Service do_update="+vo);
		LOG.debug("==================");
		
		cartDaoImpl.do_update(vo);
	}

	/**장바구니 금액 합계 */
	@Override
	public int sumMoney(String userId) {
		LOG.debug("==================");
		LOG.debug("=@Service sumMoney="+userId);
		LOG.debug("==================");
		
		return cartDaoImpl.sumMoney(userId);
	}

	/**장바구니 목록 */
	@Override
	public List<SEJ_CartVO> do_retrieve(String userId) {
		LOG.debug("==================");
		LOG.debug("=@Service do_retrieve="+userId);
		LOG.debug("==================");
		
		return cartDaoImpl.do_retrieve(userId);
	}

	/**장바구니 동일한 상품 레코드 확인*/
	@Override
	public int countCart(String productId, String userId) {
		LOG.debug("==================");
		LOG.debug("=@Service countCart productId="+productId);
		LOG.debug("=@Service countCart userId="+userId);
		LOG.debug("==================");
		
		return cartDaoImpl.countCart(productId, userId);
	}

	/**장바구니 상품수량 변경*/
	@Override
	public void updateCountCart(SEJ_CartVO vo) {
		LOG.debug("==================");
		LOG.debug("=@Service updateCart="+vo);
		LOG.debug("==================");
		
		cartDaoImpl.do_update(vo);
	}


	
}
