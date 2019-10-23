package kr.co.sunnyside.userpage.service;

import kr.co.sunnyside.cmn.DTO;

public class listSearchVO extends DTO {
	
	/** 페이지 사이즈 */
	private int pageSize   ; 	
	/** 페이지 번호 */
	private int pageNum    ;	
	/** 검색조건 */
	private String searchDiv  ;
	/** 검색어 */
	private String searchWord ;
	
	private String user_id;
	
	
	public listSearchVO() {}


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


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pageNum;
		result = prime * result + pageSize;
		result = prime * result + ((searchDiv == null) ? 0 : searchDiv.hashCode());
		result = prime * result + ((searchWord == null) ? 0 : searchWord.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		listSearchVO other = (listSearchVO) obj;
		if (pageNum != other.pageNum)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (searchDiv == null) {
			if (other.searchDiv != null)
				return false;
		} else if (!searchDiv.equals(other.searchDiv))
			return false;
		if (searchWord == null) {
			if (other.searchWord != null)
				return false;
		} else if (!searchWord.equals(other.searchWord))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "listSearchVO [pageSize=" + pageSize + ", pageNum=" + pageNum + ", searchDiv=" + searchDiv
				+ ", searchWord=" + searchWord + ", user_id=" + user_id + "]";
	}


	public listSearchVO(int pageSize, int pageNum, String searchDiv, String searchWord, String user_id) {
		super();
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.searchDiv = searchDiv;
		this.searchWord = searchWord;
		this.user_id = user_id;
	}


		
}
