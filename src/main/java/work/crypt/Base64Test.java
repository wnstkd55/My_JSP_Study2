package work.crypt;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;




public class Base64Test {
	public static void main(String args[]) {
		base64();		//메인 메소드에서 자신의 클래스의 다른 메소드 호출(static 메소드)
	}
	public static void base64() {
		String text = "1234";	//인코딩할 데이터를 변수에 등록
								//인코딩 : 암호화
		
		// 인코딩 전 텍스트를 byte배열에 저장.
		byte[] targetBytes = text.getBytes();
		
		////인코딩
		Encoder encoder = Base64.getEncoder();
			//메소드를 사용해서 Encoder 객체 생성.
		byte[] encodedByte = encoder.encode(targetBytes);
		String encoderText = new String(encodedByte); //String 형식으로 암호화된 데이터를 적용
		
		////디코딩
		Decoder decoder = Base64.getDecoder();		//Decoder 객체 생성
		byte[] decoderBytes = decoder.decode(encodedByte);
		byte[] decoderBytes2 = decoder.decode(encoderText);	//암호화된 String 복호화
		
		String deocderTxt = new String(decoderBytes);
		String deocderTxt2 = new String(decoderBytes2);
		
		
		System.out.println("================== 인코딩 ====================");
		System.out.println("인코딩 전 데이터 : " + text);
		System.out.println("인코딩 후 데이터 : " + encoderText);
		
		System.out.println("=============== 디코딩(복호화) ==================");
		System.out.println("디코딩 전 데이터 : " + encoderText);
		System.out.println("디코딩 후 데이터(byte) : " + deocderTxt);
		System.out.println("디코딩 후 데이터(byte) : " + deocderTxt2
				);
		System.out.println("============================================");
	}
}
