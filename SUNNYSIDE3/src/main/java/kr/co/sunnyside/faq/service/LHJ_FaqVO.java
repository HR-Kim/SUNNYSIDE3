package kr.co.sunnyside.faq.service;

import kr.co.sunnyside.cmn.DTO;

public class LHJ_FaqVO extends DTO{
	private String questionId;
	private String title;
	private String contents;
	private String regDt;
	
	public LHJ_FaqVO() {}
	
	public LHJ_FaqVO(String questionId, String title, String contents, String regDt) {
		super();
		this.questionId = questionId;
		this.title = title;
		this.contents = contents;
		this.regDt = regDt;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	@Override
	public String toString() {
		return "LHJ_FaqVO [questionId=" + questionId + ", title=" + title + ", contents=" + contents + ", regDt="
				+ regDt + ", toString()=" + super.toString() + "]";
	}
}
