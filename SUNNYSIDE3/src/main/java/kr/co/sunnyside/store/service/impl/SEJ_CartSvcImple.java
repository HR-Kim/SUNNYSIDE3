package kr.co.sunnyside.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import kr.co.sunnyside.store.service.SEJ_CartSvc;
import kr.co.sunnyside.store.service.SEJ_CartVO;

public class SEJ_CartSvcImple implements SEJ_CartSvc {
	
	@Autowired
	private SEJ_CartDaoImpl cartDaoImpl;
	
	@Override
	public void do_save(SEJ_CartVO vo) {
		cartDaoImpl.do_save(vo);
	}

	@Override
	public void do_delete(int cartId) {
		
	}

	@Override
	public void do_update(SEJ_CartVO vo) {
		
	}

	@Override
	public int sumMoney(String userId) {
		return 0;
	}

	@Override
	public List<SEJ_CartVO> do_retrieve(String userId) {
		return null;
	}

	@Override
	public int countCart(String productId, String userId) {
		return 0;
	}

	@Override
	public void updateCart(SEJ_CartVO vo) {
		
	}


	
}
