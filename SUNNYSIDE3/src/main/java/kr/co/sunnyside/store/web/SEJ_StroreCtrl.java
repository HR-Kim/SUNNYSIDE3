package kr.co.sunnyside.store.web;

import static kr.co.sunnyside.cmn.StringUtil.UPLOAD_ROOT;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.View;

import com.google.gson.Gson;

import kr.co.sunnyside.cmn.Message;
import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.cmn.StringUtil;
import kr.co.sunnyside.code.service.CodeService;
import kr.co.sunnyside.code.service.CodeVO;
import kr.co.sunnyside.store.service.SEJ_StroreSvc;
import kr.co.sunnyside.store.service.SEJ_StroreVO;

@Controller
public class SEJ_StroreCtrl {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	SEJ_StroreSvc storeService;

	@Resource(name = "downloadView")
	private View download;

	@Autowired
	CodeService codeService;

	// view
	private final String VIEW_LIST_NM = "store/store_main";
	private final String VIEW_POPCORN_LIST_NM = "store/popcorn_list";
	private final String VIEW_DRINK_LIST_NM = "store/drink_list";
	private final String VIEW_TICKET_LIST_NM = "store/ticket_list";
	private final String VIEW_SELECTONE = "store/selectproduct";
	private final String VIEW_SELECTONETOUPDATE = "noTileStore/store_update";
	private final String VIEW_MNG_NM = "store/store_add";
	private final String CART_LIST_VIEW = "cart/cart";
	
	
	//장바구니페이지로 이동
	@RequestMapping(value = "cart/cart.do")
	public String cartMain() {
		LOG.debug("======================");
		LOG.debug("=@Controller cartMain=");
		LOG.debug("======================");

		return CART_LIST_VIEW;
	}

	/**
	 * 상품저장
	 * 
	 * @param store
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	@RequestMapping(value = "store/do_save.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_save(MultipartHttpServletRequest mReg) throws IllegalStateException, IOException {
		LOG.debug("==========================");
		LOG.debug("=@Controller do_save=");
		LOG.debug("==========================");
		// Upload 파일 정보: 원본, 저장, 사이즈, 확장자 리스트
		List<SEJ_StroreVO> fileList = new ArrayList<SEJ_StroreVO>();

		// 입력한 것 꺼내기
		String productId = StringUtil.nvl(mReg.getParameter("productId"));
		String cartId = StringUtil.nvl(mReg.getParameter("cartId"));
		String productNm = StringUtil.nvl(mReg.getParameter("productNm"));
		String productInfo = StringUtil.nvl(mReg.getParameter("productInfo"));
		String category = mReg.getParameter("category");
		String productCost = mReg.getParameter("productCost");

		LOG.debug("======================");
		LOG.debug("=@Controller productId=" + productId);
		LOG.debug("=@Controller cartId=" + cartId);
		LOG.debug("=@Controller productNm=" + productNm);
		LOG.debug("=@Controller productInfo=" + productInfo);
		LOG.debug("=@Controller category=" + category);
		LOG.debug("=@Controller productCost=" + productCost);

		File fileRootDir = new File(UPLOAD_ROOT);
		if (fileRootDir.isDirectory() == false) {// 디렉토리 유무 체크
			boolean flag = fileRootDir.mkdirs();
			LOG.debug("=@Controller flag=" + flag);

		}

		// 동적으로 년/월 별 디렉토리 생성 : D:\\Product\2019\10
		String yyyy = StringUtil.cureDate("yyyy");
		LOG.debug("=@Controller yyyy=" + yyyy);
		String mm = StringUtil.cureDate("MM");
		LOG.debug("=@Controller yyyy=" + mm);

		// D:\\Product\2019\10
		String datePath = UPLOAD_ROOT + File.separator + yyyy + File.separator + mm;
		LOG.debug("=@Controller datePath=" + datePath);

		File fileYearMM = new File(datePath);
		if (fileYearMM.isDirectory() == false) {
			boolean flag = fileYearMM.mkdirs();
			LOG.debug("=@Controller fileYearMM flag=" + flag);
		}
		int flag = 0;
		Message message = new Message();
		// 파일 읽기
		Iterator<String> files = mReg.getFileNames();

		while (files.hasNext()) {
			SEJ_StroreVO stroreVO = new SEJ_StroreVO();

			String orgFileNM = ""; // 원본파일 명
			String saveFileNM = ""; // 저장파일 명
			String ext = ""; // 확장자

			String uploadFileNm = files.next();
			MultipartFile mFile = mReg.getFile(uploadFileNm);
			orgFileNM = mFile.getOriginalFilename();// 원본파일 명

			if (null == orgFileNM || orgFileNM.equals(""))
				continue; // null값이 아닐때만 실행되도록.
			LOG.debug("=@Controller uploadFileNm=" + uploadFileNm);
			LOG.debug("=@Controller orgFileNM=" + orgFileNM);

			if (orgFileNM.indexOf(".") > -1) {
				ext = orgFileNM.substring(orgFileNM.indexOf(".") + 1);
			}
			LOG.debug("=@Controller ext=" + ext);

			// 중복이름일 경우 새 이름으로 rename
			File orgFileCheck = new File(datePath, orgFileNM);
			
			String newFile = orgFileCheck.getAbsolutePath();
			LOG.debug("=@before newFile=" + newFile);
			if (orgFileCheck.exists() == true) {// 파일이 존재하면 rename 파일
				newFile = StringUtil.fileRename(orgFileCheck);
			}
			String changenewFile = StringUtil.changePath(newFile);
			LOG.debug("=@Controller newFile=" + newFile);		
			LOG.debug("=@Controller changenewFile=" + changenewFile);		

			stroreVO.setOrgFileNm(orgFileNM);
			stroreVO.setSaveFileNm(changenewFile);
			stroreVO.setExt(ext);
			stroreVO.setProductNm(productNm);
			stroreVO.setProductInfo(productInfo);
			stroreVO.setCategory(Integer.parseInt(category));
			stroreVO.setProductCost(Integer.parseInt(productCost));
			fileList.add(stroreVO);
			LOG.debug("fileList:" + fileList);

			mFile.transferTo(new File(newFile));
			flag = storeService.do_save(stroreVO);
			LOG.debug("flag:" + flag);
		}
		// json처리를 위한 메시지vo

		if (flag > 0) {// 등록성공
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("등록성공");

		} else {// 등록실패
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("등록실패");
		}

		Gson gson = new Gson();

		String gsonStr = gson.toJson(message);
		LOG.debug("gsonStr:" + gsonStr);

		return gsonStr;
	}

	/**
	 * 삭제
	 * 
	 * @param store
	 * @return
	 */
	@RequestMapping(value = "store/do_delete.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // 이거 없으면 json으로 안나옴
	public String do_delete(SEJ_StroreVO store) {
		LOG.debug("===============================");
		LOG.debug("=store=" + store);
		LOG.debug("===============================");

		SEJ_StroreVO stroreVO = new SEJ_StroreVO();
		
		//데이터 삭제
		int flag = this.storeService.do_delete(store);
		LOG.debug("===============================");
		LOG.debug("=flag=" + flag);
		LOG.debug("===============================");
		
		Message message = new Message();
		// 삭제 성공 시 물리적 파일도 삭제 
		if(flag>0){
			LOG.debug("store.getSaveFileNm:"+StringUtil.backPath(store.getSaveFileNm()));
			File delFile = new File(StringUtil.backPath(store.getSaveFileNm()));
			boolean delFlag = delFile.delete(); //파일 삭제
			LOG.debug("===============================");
			LOG.debug("=delFlag=" + delFlag);
			LOG.debug("===============================");
			//삭제성공 메시지
			message.setMsgMsg("삭제되었습니다.");
		}else {
			message.setMsgMsg("삭제 실패.");
		}
		//json 데이터 주고받기 
		Gson gson = new Gson();
		String gsonStr = gson.toJson(message);
		LOG.debug("===============================");
		LOG.debug("=gsonStr=" + gsonStr);
		LOG.debug("===============================");

		return gsonStr;
	}

	/**
	 * 상품수정
	 * 
	 * @param store
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	@RequestMapping(value = "store/do_update.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String do_update(MultipartHttpServletRequest mReg) throws IllegalStateException, IOException {
		LOG.debug("==========================");
		LOG.debug("=@Controller do_update=");
		LOG.debug("==========================");
		// Upload 파일 정보: 원본, 저장, 사이즈, 확장자 리스트
		List<SEJ_StroreVO> fileList = new ArrayList<SEJ_StroreVO>();

		// 입력한 것 꺼내기
		String productId = StringUtil.nvl(mReg.getParameter("productId"));
		String productNm = StringUtil.nvl(mReg.getParameter("productNm"));
		String productInfo = StringUtil.nvl(mReg.getParameter("productInfo"));
		String productCost = mReg.getParameter("productCost");

		LOG.debug("======================");
		LOG.debug("=@Controller do_update productId=" + productId);
		LOG.debug("=@Controller do_update productNm=" + productNm);
		LOG.debug("=@Controller do_update productInfo=" + productInfo);
		LOG.debug("=@Controller do_update productCost=" + productCost);

		// 상품이름값이 없을 때
		if (productNm.equals("")) {
			throw new ArithmeticException("상품이름을 입력해주세요.");
		}
		LOG.debug("=****@Controller productName=" + productNm);

		File fileRootDir = new File(UPLOAD_ROOT);
		if (fileRootDir.isDirectory() == false) {// 디렉토리 유무 체크
			boolean flag = fileRootDir.mkdirs();
			LOG.debug("=@Controller flag=" + flag);

		}

		// 동적으로 년/월 별 디렉토리 생성 : D:\\Product\2019\10
		String yyyy = StringUtil.cureDate("yyyy");
		LOG.debug("=@Controller do_update yyyy=" + yyyy);
		String mm = StringUtil.cureDate("MM");
		LOG.debug("=@Controller do_update yyyy=" + mm);

		// D:\\Product\2019\10
		String datePath = UPLOAD_ROOT + File.separator + yyyy + File.separator + mm;
		LOG.debug("=@Controller datePath=" + datePath);

		File fileYearMM = new File(datePath);
		if (fileYearMM.isDirectory() == false) {
			boolean flag = fileYearMM.mkdirs();
			LOG.debug("=@Controller fileYearMM flag=" + flag);
		}
		int flag = 0;
		Message message = new Message();
		// 파일 읽기
		Iterator<String> files = mReg.getFileNames();

		while (files.hasNext()) {
			SEJ_StroreVO stroreVO = new SEJ_StroreVO();

			String orgFileNM = ""; // 원본파일 명
			String saveFileNM = ""; // 저장파일 명
			String ext = ""; // 확장자

			String uploadFileNm = files.next();
			MultipartFile mFile = mReg.getFile(uploadFileNm);
			orgFileNM = mFile.getOriginalFilename();// 원본파일 명

			if (null == orgFileNM || orgFileNM.equals(""))
				continue; // null값이 아닐때만 실행되도록.
			LOG.debug("=@Controller do_update uploadFileNm=" + uploadFileNm);
			LOG.debug("=@Controller  do_update orgFileNM=" + orgFileNM);

			if (orgFileNM.indexOf(".") > -1) {
				ext = orgFileNM.substring(orgFileNM.indexOf(".") + 1);
			}
			LOG.debug("=@Controller ext=" + ext);

			// 중복이름일 경우 새 이름으로 rename
			File orgFileCheck = new File(datePath, orgFileNM);
			String newFile = orgFileCheck.getAbsolutePath();
			if (orgFileCheck.exists() == true) {// 파일이 존재하면 rename 파일
				newFile = StringUtil.fileRename(orgFileCheck);
			}
			String changenewFile = StringUtil.changePath(newFile);
			LOG.debug("=@Controller newFile=" + newFile);		
			LOG.debug("=@Controller changenewFile=" + changenewFile);

			stroreVO.setOrgFileNm(orgFileNM);
			stroreVO.setSaveFileNm(changenewFile);
			stroreVO.setExt(ext);
			stroreVO.setProductNm(productNm);
			stroreVO.setProductId(productId);
			stroreVO.setProductInfo(productInfo);
			stroreVO.setProductCost(Integer.parseInt(productCost));
			fileList.add(stroreVO);
			LOG.debug("fileList:" + fileList);

			mFile.transferTo(new File(newFile));
			
			flag = storeService.do_update(stroreVO);
			LOG.debug("flag:" + flag);
		}
		// json처리를 위한 메시지vo

		if (flag > 0) {// 수정성공
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("수정성공");

		} else {// 수정실패
			message.setMsgId(String.valueOf(flag));
			message.setMsgMsg("수정실패");
		}

		Gson gson = new Gson();

		String gsonStr = gson.toJson(message);
		LOG.debug("gsonStr:" + gsonStr);

		return gsonStr;
	}

	/**
	 * 수정을 위한 단건조회 조회만 get
	 * 
	 * @param store
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "noTileStore/do_selectOneToUpdate.do", method = RequestMethod.GET)
	public String do_selectOneToUpdate(SEJ_StroreVO store, Model model) {
		LOG.debug("===============================");
		LOG.debug("=store=" + store);
		LOG.debug("===============================");

		/** code */
		CodeVO code = new CodeVO();
		// 페이지 사이즈
		code.setCodeId("CATEGORY");

		List<CodeVO> categoryList = (List<CodeVO>) codeService.do_retrieve(code);
		LOG.debug("2.=====================");
		LOG.debug("2.=categoryList=" + categoryList);
		LOG.debug("2.=====================");
		model.addAttribute("categoryList", categoryList);

		SEJ_StroreVO outVO = (SEJ_StroreVO) this.storeService.do_selectOne(store);
		model.addAttribute("vo", outVO);

		return VIEW_SELECTONETOUPDATE;
	}

	/**
	 * 단건조회 조회만 get
	 * 
	 * @param store
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "store/do_selectOne.do", method = RequestMethod.GET)
	public String do_selectOne(SEJ_StroreVO store, Model model) {
		LOG.debug("===============================");
		LOG.debug("=store=" + store);
		LOG.debug("===============================");

		SEJ_StroreVO outVO = (SEJ_StroreVO) this.storeService.do_selectOne(store);
		model.addAttribute("vo", outVO);

		return VIEW_SELECTONE;
	}

	/**
	 * 전체조회 조회만 get
	 * 
	 * @param search
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "store/do_retrieve.do", method = RequestMethod.GET)
	public String do_retrieve_all(SearchVO search, Model model) {
		LOG.debug("1.=====================");
		LOG.debug("1.= param=" + search);
		LOG.debug("1.=====================");

		// 디폴트 값 설정 페이지사이즈:10
		if (search.getPageSize() == 0) {
			search.setPageSize(10);
		}

		// 디폴트 값 설정 페이지번호:1
		if (search.getPageNum() == 0) {
			search.setPageNum(1);
		}

		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv()));
		search.setSearchWord(StringUtil.nvl(search.getSearchWord()));

		LOG.debug("2.=====================");
		LOG.debug("2.=param=" + search);
		LOG.debug("2.=====================");
		model.addAttribute("vo", search);

		List<SEJ_StroreSvc> list = (List<SEJ_StroreSvc>) this.storeService.do_retrieve(search);
		model.addAttribute("list", list);

		return VIEW_LIST_NM;
	}
	

	/**
	 * 팝콘 전체조회 조회만 get
	 * 
	 * @param search
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "store/do_retrieve_popcorn.do", method = RequestMethod.GET)
	public String do_retrieve_popcorn(SearchVO search, Model model) {
		LOG.debug("1.=====================");
		LOG.debug("1.= param=" + search);
		LOG.debug("1.=====================");

		// 디폴트 값 설정 페이지사이즈:10
		if (search.getPageSize() == 0) {
			search.setPageSize(100);
		}

		// 디폴트 값 설정 페이지번호:1
		if (search.getPageNum() == 0) {
			search.setPageNum(1);
		}

		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv()));

		LOG.debug("2.=====================");
		LOG.debug("2.=param=" + search);
		LOG.debug("2.=====================");
		model.addAttribute("vo", search);

		List<SEJ_StroreSvc> list = (List<SEJ_StroreSvc>) this.storeService.do_retrieve_popcorn(search);
		model.addAttribute("list", list);

		return VIEW_POPCORN_LIST_NM;
	}

	/**
	 * 음료 전체조회 조회만 get
	 * 
	 * @param search
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "store/do_retrieve_drink.do", method = RequestMethod.GET)
	public String do_retrieve_drink(SearchVO search, Model model) {
		LOG.debug("1.=====================");
		LOG.debug("1.= param=" + search);
		LOG.debug("1.=====================");

		// 디폴트 값 설정 페이지사이즈:10
		if (search.getPageSize() == 0) {
			search.setPageSize(100);
		}

		// 디폴트 값 설정 페이지번호:1
		if (search.getPageNum() == 0) {
			search.setPageNum(1);
		}

		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv()));

		LOG.debug("2.=====================");
		LOG.debug("2.=param=" + search);
		LOG.debug("2.=====================");
		model.addAttribute("vo", search);

		List<SEJ_StroreSvc> list = (List<SEJ_StroreSvc>) this.storeService.do_retrieve_drink(search);
		model.addAttribute("list", list);

		return VIEW_DRINK_LIST_NM;
	}

	/**
	 * 영화예매권 전체조회 조회만 get
	 * 
	 * @param search
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "store/do_retrieve_movieticket.do", method = RequestMethod.GET)
	public String do_retrieve_movieticket(SearchVO search, Model model) {
		LOG.debug("1.=====================");
		LOG.debug("1.= param=" + search);
		LOG.debug("1.=====================");

		// 디폴트 값 설정 페이지사이즈:10
		if (search.getPageSize() == 0) {
			search.setPageSize(100);
		}

		// 디폴트 값 설정 페이지번호:1
		if (search.getPageNum() == 0) {
			search.setPageNum(1);
		}

		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv()));

		LOG.debug("2.=====================");
		LOG.debug("2.=param=" + search);
		LOG.debug("2.=====================");
		model.addAttribute("vo", search);

		List<SEJ_StroreSvc> list = (List<SEJ_StroreSvc>) this.storeService.do_retrieve_movieticket(search);
		model.addAttribute("list", list);

		return VIEW_TICKET_LIST_NM;
	}

	/**
	 * 메인페이지 조회 
	 * 조회만 get
	 * 
	 * @param search
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "store/do_main.do", method = RequestMethod.GET)
	public String do_main(SearchVO search, Model model) {
		LOG.debug("1.=====================");
		LOG.debug("1.= param=" + search);
		LOG.debug("1.=====================");

		search.setSearchDiv(StringUtil.nvl(search.getSearchDiv()));

		LOG.debug("2.=====================");
		LOG.debug("2.=param=" + search);
		LOG.debug("2.=====================");
		model.addAttribute("vo", search);

		List<SEJ_StroreSvc> poplist = (List<SEJ_StroreSvc>) this.storeService.do_main_popcorn(search);
		List<SEJ_StroreSvc> drinklist = (List<SEJ_StroreSvc>) this.storeService.do_main_drink(search);
		List<SEJ_StroreSvc> ticketlist = (List<SEJ_StroreSvc>) this.storeService.do_main_ticket(search);
		model.addAttribute("popcornlist", poplist);
		model.addAttribute("drinklist", drinklist);
		model.addAttribute("ticketlist", ticketlist);


		return VIEW_LIST_NM;
	}
	
}
