package work.crypt;

import org.apache.commons.codec.binary.Base64;



public class Base64Test2 {
	public static void main(String args[]) {
		base64();		//메인 메소드에서 자신의 클래스의 다른 메소드 호출(static 메소드)
	}
	public static void base64() {
		String text = "1234";
		
		//인코딩
		byte[] encodedBytes = Base64.encodeBase64(text.getBytes());
		String encodedTxt = new String(encodedBytes);
		
		System.out.println("================== 인코딩 ====================");
		System.out.println("인코딩전 : "+ text);
		System.out.println("인코딩후 : "+ encodedTxt);
		//디코딩
		
		byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
		String decodedTxt = new String(decodedBytes);
		
		System.out.println("=============== 디코딩(복호화) ==================");
		System.out.println("디코딩전 : "+ encodedTxt);
		System.out.println("디코딩후 : "+ decodedTxt);
		System.out.println("============================================");
	}
}
