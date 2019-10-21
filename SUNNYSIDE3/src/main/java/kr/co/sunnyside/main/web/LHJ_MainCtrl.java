package kr.co.sunnyside.main.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.sunnyside.code.service.CodeService;
import kr.co.sunnyside.code.service.CodeVO;
import kr.co.sunnyside.main.service.LHJ_MainImageVO;
import kr.co.sunnyside.main.service.impl.LHJ_MainSvcImpl;
import kr.co.sunnyside.movie.service.LHJ_MovieVO;
import kr.co.sunnyside.movie.service.impl.LHJ_BoxofficeSvcImpl;
import kr.co.sunnyside.movie.service.impl.LHJ_ScreeningSvcImpl;

@Controller
public class LHJ_MainCtrl {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LHJ_MainSvcImpl service;
	
	@Autowired
	LHJ_BoxofficeSvcImpl boxofficeService;	 

	@Autowired
	private CodeService codeService;
	 
	//view
	private final String VIEW_MAIN_NM = "main/main";
	
	/**목록조회 */
	@RequestMapping(value="main/main.do",method = RequestMethod.GET)
	public String do_selectOne(LHJ_MovieVO inVO,Model model) {

		
		List<LHJ_MainImageVO> bannerList = (List<LHJ_MainImageVO>) this.service.do_banner_retrieve();
		model.addAttribute("bannerList", bannerList);
		
		List<LHJ_MovieVO> boxofficeList = (List<LHJ_MovieVO>) this.boxofficeService.do_retrieve_main();
		model.addAttribute("boxofficeList", boxofficeList);

		return VIEW_MAIN_NM;
	}
}
