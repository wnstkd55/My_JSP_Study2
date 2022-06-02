package work.crypt;

import org.apache.commons.codec.binary.Base64;


public class Base64Test {
	public static void main(String args[]) {
		base64();
	}
	public static void base64() {
		String text = "kktko";
		
		byte[] encodedBytes = Base64.encodeBase64(text.getBytes());
		
		byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
		
		System.out.println(text);
		System.out.println(new String(encodedBytes));
		System.out.println(new String(decodedBytes));
		
	}
}
