package kr.co.sunnyside.store.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.sunnyside.store.service.SEJ_CartSvc;
import kr.co.sunnyside.store.service.SEJ_CartVO;

public class SEJ_CartCtrl {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SEJ_CartSvc cartService;
	
	// 1. 장바구니 저장(추가)
	@RequestMapping("cart/do_save.do")
	public String do_save(@ModelAttribute SEJ_CartVO vo, HttpSession session){
		String userId = (String) session.getAttribute("userId");
		vo.setUserId(userId);
		// 장바구니에 기존 상품이 있는지 검사
		int count = cartService.countCart(vo.getProductId(), userId);
		if(count == 0){		
			// 없으면 insert
			cartService.do_save(vo);
		} else {
			// 있으면 update
			cartService.updateCart(vo);
		}
		return "redirect:/shop/cart/list.do";
	}
		
}
