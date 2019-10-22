package kr.co.sunnyside.store.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.sunnyside.cmn.StringUtil;
import kr.co.sunnyside.login.service.SJH_LoginVO;
import kr.co.sunnyside.store.service.SEJ_CartSvc;
import kr.co.sunnyside.store.service.SEJ_CartVO;
import kr.co.sunnyside.store.service.SEJ_PayVO;
import kr.co.sunnyside.store.service.SEJ_StroreSvc;

@Controller
public class SEJ_CartCtrl {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SEJ_CartSvc cartService;
	
	@Autowired
	SEJ_StroreSvc storeService;
	
	// view
	private final String CART_LIST_VIEW = "cart/cart";
	private final String BEFORE_PAY_LIST_VIEW = "cart/payment";
	private final String AFTER_PAY_LIST_VIEW = "cart/successPay";
	
	// 0. 결제내역 저장(추가)
		@RequestMapping(value ="cart/do_payComplete.do", method = RequestMethod.POST)
		public String do_payComplete(SEJ_PayVO inVO, HttpSession session){
			LOG.debug("============================");
			LOG.debug("=do_payComplete inVo="+inVO);
			LOG.debug("============================");
			
				cartService.do_payComplete(inVO);
				cartService.do_deleteAll();
		
			return AFTER_PAY_LIST_VIEW;
		}
		
	// 0. 결제내역 목록
		@RequestMapping(value ="cart/do_payCompleteList.do",method = RequestMethod.GET)
		public String do_payCompleteList(SEJ_PayVO inVO,Model model){
			LOG.debug("1.=====================");
			LOG.debug("1.= do_payCompleteList param=" + inVO);
			LOG.debug("1.=====================");
			
			List<SEJ_PayVO> list = (List<SEJ_PayVO>) this.cartService.do_payCompleteList(inVO);
			model.addAttribute("list", list);
			
			return AFTER_PAY_LIST_VIEW;
		}
	
	
	// 1. 장바구니 저장(추가)
	@RequestMapping(value ="cart/do_save.do", method = RequestMethod.POST)
	public String do_save(SEJ_CartVO inVO, HttpSession session){
		LOG.debug("============================");
		LOG.debug("=do_save inVo="+inVO);
		LOG.debug("============================");
		
		//로그인 확인
		SJH_LoginVO userId = (SJH_LoginVO) session.getAttribute("user");
		LOG.debug("**userId"+userId);
		if(null !=userId) {
			inVO.setUserId(userId.getUserId()); //아이디로 입력되게 처리한 것 . 이러면 세션 처리 끝. 
		}
		// 장바구니에 기존 상품이 있는지 검사
		int count = cartService.do_countCart(inVO.getProductId(), inVO.getUserId());
		if(count == 0){		
			// 없으면 insert
			cartService.do_save(inVO);
		} else {
			// 있으면 update
			cartService.do_updateCountCart(inVO);
		}
		return CART_LIST_VIEW;
	}
	 
	// 2. 장바구니 삭제
	@RequestMapping(value ="cart/do_delete.do", method = RequestMethod.POST)
	public String do_delete(SEJ_CartVO inVO){
		LOG.debug("============================");
		LOG.debug("=do_delete inVO="+inVO);
		LOG.debug("============================");
		
		cartService.do_delete(inVO);
		
		return CART_LIST_VIEW;
	}
	
	// 2-1. 장바구니 전체 삭제
	@RequestMapping(value ="cart/do_deleteAll.do", method = RequestMethod.POST)
	public String do_deleteAll(){
		LOG.debug("============================");
		LOG.debug("=do_deleteAll");
		LOG.debug("============================");
		
		cartService.do_deleteAll();
		
		return CART_LIST_VIEW;
	}
	
	// 3. 장바구니 수정
	@RequestMapping(value ="cart/do_update.do",method = RequestMethod.POST )
	public String do_update(SEJ_CartVO inVO) {
		LOG.debug("============================");
		LOG.debug("=do_update inVO="+inVO);
		LOG.debug("============================");		
		
		cartService.do_update(inVO);

		return CART_LIST_VIEW;
	}
	
	// 4. 장바구니 목록
	@RequestMapping(value ="cart/do_retrieve.do",method = RequestMethod.GET)
	public String do_retrieve(SEJ_CartVO inVO,Model model){
		LOG.debug("1.=====================");
		LOG.debug("1.= do_retrieve param=" + inVO);
		LOG.debug("1.=====================");

		List<SEJ_CartVO> list = (List<SEJ_CartVO>) this.cartService.do_retrieve(inVO);
		int totalCost=cartService.do_totalCost(inVO.getUserId());
		model.addAttribute("list", list);
		model.addAttribute("totalCost", totalCost);

		return CART_LIST_VIEW;
	}
	
	// 5. 주문하기 목록
		@RequestMapping(value ="cart/do_payRetrieve.do",method = RequestMethod.GET)
		public String do_payRetrieve(SEJ_CartVO inVO,Model model){
			LOG.debug("1.=====================");
			LOG.debug("1.= do_retrieve param=" + inVO);
			LOG.debug("1.=====================");

			List<SEJ_CartVO> list = (List<SEJ_CartVO>) this.cartService.do_retrieve(inVO);
			int totalCost=cartService.do_totalCost(inVO.getUserId());
			model.addAttribute("list", list);
			model.addAttribute("totalCost", totalCost);

			return BEFORE_PAY_LIST_VIEW;
		}
}
