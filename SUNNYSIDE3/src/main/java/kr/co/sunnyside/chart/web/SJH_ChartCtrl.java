package kr.co.sunnyside.chart.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import kr.co.sunnyside.chart.service.SJH_ChartSvc;
import kr.co.sunnyside.chart.service.SJH_ChartVO;
import kr.co.sunnyside.reservation.service.LGS_TicketVO;


@Controller
public class SJH_ChartCtrl {

	Logger LOG = LoggerFactory.getLogger(SJH_ChartCtrl.class);
	
	@Autowired
	SJH_ChartSvc chartSvc;
	
	
	
	@RequestMapping(value="chart/line_chart.do",method=RequestMethod.GET
			,produces="application/json;charset=utf-8")
	@ResponseBody
	public String line_chart(Model model,SJH_ChartVO user) {
		
		LOG.debug("user: "+user);
		
		user.setMonth("01");
		SJH_ChartVO out01 = new SJH_ChartVO("1월",chartSvc.do_getAll(user));
//		SJH_ChartVO out02 = new SJH_ChartVO("2월",3);
//		SJH_ChartVO out03 = new SJH_ChartVO("3월",5);
//		SJH_ChartVO out04 = new SJH_ChartVO("4월",10);
//		SJH_ChartVO out05 = new SJH_ChartVO("5월",3);
//		SJH_ChartVO out06 = new SJH_ChartVO("6월",2);
//		SJH_ChartVO out07 = new SJH_ChartVO("7월",7);
//		SJH_ChartVO out08 = new SJH_ChartVO("8월",3);
//		SJH_ChartVO out09 = new SJH_ChartVO("9월",9);
//		SJH_ChartVO out10 = new SJH_ChartVO("10월",10);
//		SJH_ChartVO out11 = new SJH_ChartVO("11월",2);
//		SJH_ChartVO out12 = new SJH_ChartVO("12월",1);
		
		List<SJH_ChartVO> list = new ArrayList<SJH_ChartVO>();
		list.add(out01);
//		list.add(out02);
//		list.add(out03);
//		list.add(out04);
//		list.add(out05);
//		list.add(out06);
//		list.add(out07);
//		list.add(out08);
//		list.add(out09);
//		list.add(out10);
//		list.add(out11);
//		list.add(out12);
		
		Gson gson = new Gson();
		JsonArray jArray = new JsonArray();
		
		for(int i=0; i<list.size(); i++) {
			JsonArray sArray = new JsonArray();
			sArray.add(list.get(i).getMonth());
			sArray.add(list.get(i).getHistory());
			jArray.add(sArray);
			
		}
		
		LOG.debug("========================");
		LOG.debug("=jArray="+jArray.toString());
		LOG.debug("========================");
		
		
		return jArray.toString();
	}
	
	
	
}
