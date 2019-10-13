package kr.co.sunnyside.main.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.sunnyside.movie.service.LHJ_MovieVO;

@Controller
public class LHJ_MainCtrl {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	/**단건조회 */
	@RequestMapping(value="main/main.do",method = RequestMethod.GET)
	public String do_selectOne(LHJ_MovieVO inVO,Model model) {
	 
	 return "main/main";
	}
}
