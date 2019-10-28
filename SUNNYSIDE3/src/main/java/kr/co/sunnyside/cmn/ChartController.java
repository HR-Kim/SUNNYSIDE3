package kr.co.sunnyside.cmn;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;


@Controller
public class ChartController {

	Logger LOG = LoggerFactory.getLogger(ChartController.class);
	
	
	@RequestMapping(value="chart/line_chart_view.do",method=RequestMethod.GET)
	public String doLineView(Model model) {
		return "chart/line_chart";
	}
	
	
	@RequestMapping(value="chart/line_chart.do",method=RequestMethod.GET
			,produces="application/json;charset=utf-8")
	@ResponseBody
	public String line_chart(Model model) {
		/*
			  ['month', 'history'],
		      ['1월',       1	 ],
		      ['2월',       2  	 ],
		      ['3월',       3	 ],
		      ['4월',       4  	 ]
		
		*/
		
		Line out01 = new Line("1월",1);
		Line out02 = new Line("2월",3);
		Line out03 = new Line("3월",5);
		Line out04 = new Line("4월",10);
		Line out05 = new Line("5월",3);
		Line out06 = new Line("6월",2);
		Line out07 = new Line("7월",7);
		Line out08 = new Line("8월",3);
		Line out09 = new Line("9월",9);
		Line out10 = new Line("10월",10);
		Line out11 = new Line("11월",2);
		Line out12 = new Line("12월",1);
		
		
		List<Line> list = new ArrayList<Line>();
		list.add(out01);
		list.add(out02);
		list.add(out03);
		list.add(out04);
		list.add(out05);
		list.add(out06);
		list.add(out07);
		list.add(out08);
		list.add(out09);
		list.add(out10);
		list.add(out11);
		list.add(out12);
		
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
