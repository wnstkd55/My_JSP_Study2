package work.crypt;

import org.apache.commons.codec.binary.Base64;



public class Base64Test2 {
	public static void main(String args[]) {
		base64();		//���� �޼ҵ忡�� �ڽ��� Ŭ������ �ٸ� �޼ҵ� ȣ��(static �޼ҵ�)
	}
	public static void base64() {
		String text = "1234";
		
		//���ڵ�
		byte[] encodedBytes = Base64.encodeBase64(text.getBytes());
		String encodedTxt = new String(encodedBytes);
		
		System.out.println("================== ���ڵ� ====================");
		System.out.println("���ڵ��� : "+ text);
		System.out.println("���ڵ��� : "+ encodedTxt);
		//���ڵ�
		
		byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
		String decodedTxt = new String(decodedBytes);
		
		System.out.println("=============== ���ڵ�(��ȣȭ) ==================");
		System.out.println("���ڵ��� : "+ encodedTxt);
		System.out.println("���ڵ��� : "+ decodedTxt);
		System.out.println("============================================");
	}
}
