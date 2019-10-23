package kr.co.sunnyside.store.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sunnyside.cmn.DTO;
import kr.co.sunnyside.store.service.SEJ_CartSvc;
import kr.co.sunnyside.store.service.SEJ_CartVO;

@Service
public class SEJ_CartSvcImple implements SEJ_CartSvc {
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	//dao 등록
	@Autowired
	private SEJ_CartDaoImpl cartDaoImpl;

	
	/**결제내역 추가 */
	@Override
	public int do_payComplete(DTO dto) {
		LOG.debug("==================");
		LOG.debug("=@Service do_payComplete="+dto);
		LOG.debug("==================");
		
		return cartDaoImpl.do_payComplete(dto);
	}
	
	/**결제내역 목록 */
	@Override
	public List<?> do_payCompleteList(DTO dto) {
		LOG.debug("==================");
		LOG.debug("=@Service do_payCompleteList="+dto);
		LOG.debug("==================");
		return cartDaoImpl.do_payCompleteList(dto);
	}
	
	/**장바구니 저장 */
	@Override
	public int do_save(DTO dto) {
		LOG.debug("==================");
		LOG.debug("=@Service do_save="+dto);
		LOG.debug("==================");
		
		return cartDaoImpl.do_save(dto);
	}
	
	/**장바구니 삭제 */
	@Override
	public int do_delete(DTO dto) {
		LOG.debug("==================");
		LOG.debug("=@Service do_delete="+dto);
		LOG.debug("==================");
		
		return cartDaoImpl.do_delete(dto);
	}
	
	/**장바구니 전체삭제 */
	@Override
	public int do_deleteAll() {
		LOG.debug("==================");
		LOG.debug("=@Service do_deleteAll=");
		LOG.debug("==================");
		return cartDaoImpl.do_deleteAll();
	}
	
	/**장바구니 수정 */
	@Override
	public int do_update(DTO dto) {
		LOG.debug("==================");
		LOG.debug("=@Service do_update="+dto);
		LOG.debug("==================");
		return cartDaoImpl.do_update(dto);
	}
	
	/**장바구니 목록 */
	@Override
	public List<?> do_retrieve(DTO dto) {
		LOG.debug("==================");
		LOG.debug("=@Service do_retrieve="+dto);
		LOG.debug("==================");
		return cartDaoImpl.do_retrieve(dto);
	}
	
	/**장바구니 금액 총합계 */    
	@Override
	public int do_selectOne(DTO dto) {
		return 0;	
	}

	/**장바구니 동일한 상품 레코드 확인*/
	@Override
	public int do_countCart(String productId, String userId) {
		LOG.debug("==================");
		LOG.debug("=@Service countCart productId="+productId);
		LOG.debug("=@Service countCart userId="+userId);
		LOG.debug("==================");
		
		return cartDaoImpl.do_countCart(productId, userId);
	}

	/**장바구니 상품수량 변경*/
	@Override
	public int do_updateCountCart(DTO dto) {
		LOG.debug("==================");
		LOG.debug("=@Service updateCart="+dto);
		LOG.debug("==================");
		return cartDaoImpl.do_updateCountCart(dto);
	}
	/**장바구니 금액 총합계 */   
	@Override
	public int do_totalCost(String userId) {
		LOG.debug("==================");
		LOG.debug("=@Service do_totalCost="+userId);
		LOG.debug("==================");
		return cartDaoImpl.do_totalCost(userId);
	}
	/**주문코드 생성후 업데이트*/
	@Override
	public int do_make_codeNm(DTO dto) {
		LOG.debug("==================");
		LOG.debug("=@Service do_make_codeNm="+dto);
		LOG.debug("==================");
		return cartDaoImpl.do_make_codeNm(dto);
	}
	/**주문하기창 목록 */
	@Override
	public List<?> do_payRetrieve(DTO dto) {
		LOG.debug("==================");
		LOG.debug("=@Service do_payRetrieve="+dto);
		LOG.debug("==================");
		return cartDaoImpl.do_payRetrieve(dto);
	}


	

	

	


	
}
