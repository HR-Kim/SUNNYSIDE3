package kr.co.sunnyside.store.service;

import java.util.List;

public interface SEJ_CartSvc {
	/**장바구니 저장 */
	public void do_save(SEJ_CartVO vo);
	
	/**장바구니 삭제 */
	public void do_delete(int cartId);
	
	/**장바구니 수정 */
	public void do_update(SEJ_CartVO vo);

	/**장바구니 금액 합계 */
	public int sumMoney(String userId);
	
	/**장바구니 목록 */
	public List<SEJ_CartVO> do_retrieve(String userId);
	
	/**장바구니 동일한 상품 레코드 확인*/
	public int countCart(int productId, String userId);
	
	/**장바구니 상품수량 변경*/
	public void updateCart(SEJ_CartVO vo);

}
