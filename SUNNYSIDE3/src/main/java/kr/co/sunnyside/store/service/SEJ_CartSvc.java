package kr.co.sunnyside.store.service;

import java.util.List;

import kr.co.sunnyside.cmn.DTO;

public interface SEJ_CartSvc {
	/**결제내역 추가 */
	public int do_payComplete(DTO dto);
	
//	/**결제내역 추가 */
//	public void do_payComplete(List<SEJ_CartVO> list);
	
	/**결제내역 목록 */
	public List<?> do_payCompleteList(DTO dto);
	
	/**장바구니 저장 */
	public int do_save(DTO dto);
	
	/**장바구니 삭제 */
	public int do_delete(DTO dto);
	
	/**장바구니 전체삭제 */
	public int do_deleteAll();
	
	/**장바구니 수정 */
	public int do_update(DTO dto);

	/**장바구니 목록 */
	public List<?> do_retrieve(DTO dto);

	public int do_selectOne(DTO dto);
	
	/**장바구니 금액 총합계 */ 
	public int do_totalCost(String userId);
	
	/**장바구니 동일한 상품 레코드 확인*/
	public int do_countCart(String productId, String userId);
	
	/**장바구니 상품수량 변경*/
	public int do_updateCountCart(DTO dto);
	
	/**주문코드 생성후 업데이트*/
	public int do_make_codeNm(DTO dto);

	/**주문하기창 목록 */
	public List<?> do_payRetrieve(DTO dto);

	
}
