package kr.co.sunnyside.cmn;

import kr.co.sunnyside.cmn.DTO;

public class SearchVO extends DTO {
	
	/** 페이지 사이즈 */
	private int pageSize; 	
	/** 페이지 번호 */
	private int pageNum;	
	/** 검색조건 */
	private String searchDiv;
	/** 검색어 */
	private String searchWord;
	/** 두번째 검색어 */
	private String searchWord_second;
	
	public SearchVO() {}


	public SearchVO(int pageSize, int pageNum, String searchDiv, String searchWord) {
		super();
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.searchDiv = searchDiv;
		this.searchWord = searchWord;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getPageNum() {
		return pageNum;
	}


	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}


	public String getSearchDiv() {
		return searchDiv;
	}


	public void setSearchDiv(String searchDiv) {
		this.searchDiv = searchDiv;
	}


	public String getSearchWord() {
		return searchWord;
	}


	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}


	public String getSearchWord_second() {
		return searchWord_second;
	}


	public void setSearchWord_second(String searchWord_second) {
		this.searchWord_second = searchWord_second;
	}


	@Override
	public String toString() {
		return "SearchVO [pageSize=" + pageSize + ", pageNum=" + pageNum + ", searchDiv=" + searchDiv + ", searchWord="
				+ searchWord + ", searchWord_second=" + searchWord_second + ", toString()=" + super.toString() + "]";
	}

}
