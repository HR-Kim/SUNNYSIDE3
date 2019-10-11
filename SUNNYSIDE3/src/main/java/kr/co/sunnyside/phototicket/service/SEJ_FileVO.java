package kr.co.sunnyside.phototicket.service;

public class SEJ_FileVO {
	public int count;
	public String body;
	public String f;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getF() {
		return f;
	}
	public void setF(String f) {
		this.f = f;
	}
	@Override
	public String toString() {
		return "FileVO [count=" + count + ", body=" + body + ", f=" + f + "]";
	}
	public SEJ_FileVO(int count, String body, String f) {
		super();
		this.count = count;
		this.body = body;
		this.f = f;
	}
	
	
}
