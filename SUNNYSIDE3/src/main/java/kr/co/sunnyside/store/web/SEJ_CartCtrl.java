package kr.co.sunnyside.store.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.co.sunnyside.store.service.SEJ_CartSvc;
import kr.co.sunnyside.store.service.SEJ_CartVO;
import kr.co.sunnyside.store.service.SEJ_StroreSvc;

@Controller
public class SEJ_CartCtrl {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SEJ_CartSvc cartService;
	
	@Autowired
	SEJ_StroreSvc storeService;
	
	// 1. 장바구니 저장(추가)
	@RequestMapping(value ="cart/do_save.do")
	public String do_save(@ModelAttribute SEJ_CartVO vo, HttpSession session){
		String userId = (String) session.getAttribute("user");
		LOG.debug("**userId"+userId);
		vo.setUserId(userId);
		// 장바구니에 기존 상품이 있는지 검사
		int count = cartService.countCart(vo.getProductId(), userId);
		if(count == 0){		
			// 없으면 insert
			cartService.do_save(vo);
		} else {
			// 있으면 update
			cartService.updateCountCart(vo);
		}
		return "cart/do_retrieve.do";
	}
	
	// 2. 장바구니 삭제
	@RequestMapping(value ="cart/do_delete.do")
	public String delete(@RequestParam int cartId){
		cartService.do_delete(cartId);
		return "cart/do_retrieve.do";
	}
	
	// 3. 장바구니 수정
	@RequestMapping(value ="cart/do_update.do")
	public String update(@RequestParam int[] count, @RequestParam String[] productId, HttpSession session) {
		// session의 id
		String userId = (String) session.getAttribute("userId");
		LOG.debug("userId"+userId);
		
		// 레코드의 갯수 만큰 반복문 실행
		for(int i=0; i<productId.length; i++){
			SEJ_CartVO vo = new SEJ_CartVO();
			vo.setUserId(userId);
			vo.setCount(count[i]);
			vo.setProductId(productId[i]);
			cartService.do_update(vo);
		}
		return "redirect:/cart/do_retrieve.do";
	}
		
	// 4. 장바구니 목록
	@RequestMapping(value ="cart/do_retrieve.do")
	public ModelAndView do_retrieve(HttpSession session, ModelAndView mav){
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId"); // session에 저장된 userId
		LOG.debug("userId"+userId);
		List<SEJ_CartVO> list = cartService.do_retrieve(userId); // 장바구니 정보 
		LOG.debug("list"+list);
		int sumMoney = cartService.sumMoney(userId); // 장바구니 전체 금액 호출
		LOG.debug("sumMoney"+sumMoney);
		
		// 장바구니 전체 긍액
		map.put("list", list);				// 장바구니 정보를 map에 저장
		map.put("count", list.size());		// 장바구니 상품의 유무
		map.put("sumMoney", sumMoney);		// 주문 상품 전체 금액 
		mav.setViewName("cart/cart");	// view(jsp)의 이름 저장
		mav.addObject("map", map);			// map 변수 저장
		return mav;
	}
}
