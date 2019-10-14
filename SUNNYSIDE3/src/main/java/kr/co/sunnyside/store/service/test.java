package kr.co.sunnyside.store.service;

public class test {

	public static void main(String[] args) {
		String str = "C:\\Users\\sist\\git\\SUNNYSIDE3\\SUNNYSIDE3\\src\\main\\webapp\\resources\\image\\2019\\10\\plainPopcorn(m).jpg";
		
//		../resources/image/2019/10/plainPopcorn.jpg"
		
		str = ".."+str.substring(str.indexOf("\\resources")).replace("\\", "/");

		
		System.out.println(str);
		System.out.println("../resources/image/2019/10/plainPopcorn.jpg");
		

	}

}
