package work.crypt;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;




public class Base64Test {
	public static void main(String args[]) {
		base64();		//���� �޼ҵ忡�� �ڽ��� Ŭ������ �ٸ� �޼ҵ� ȣ��(static �޼ҵ�)
	}
	public static void base64() {
		String text = "1234";	//���ڵ��� �����͸� ������ ���
								//���ڵ� : ��ȣȭ
		
		// ���ڵ� �� �ؽ�Ʈ�� byte�迭�� ����.
		byte[] targetBytes = text.getBytes();
		
		////���ڵ�
		Encoder encoder = Base64.getEncoder();
			//�޼ҵ带 ����ؼ� Encoder ��ü ����.
		byte[] encodedByte = encoder.encode(targetBytes);
		String encoderText = new String(encodedByte); //String �������� ��ȣȭ�� �����͸� ����
		
		////���ڵ�
		Decoder decoder = Base64.getDecoder();		//Decoder ��ü ����
		byte[] decoderBytes = decoder.decode(encodedByte);
		byte[] decoderBytes2 = decoder.decode(encoderText);	//��ȣȭ�� String ��ȣȭ
		
		String deocderTxt = new String(decoderBytes);
		String deocderTxt2 = new String(decoderBytes2);
		
		
		System.out.println("================== ���ڵ� ====================");
		System.out.println("���ڵ� �� ������ : " + text);
		System.out.println("���ڵ� �� ������ : " + encoderText);
		
		System.out.println("=============== ���ڵ�(��ȣȭ) ==================");
		System.out.println("���ڵ� �� ������ : " + encoderText);
		System.out.println("���ڵ� �� ������(byte) : " + deocderTxt);
		System.out.println("���ڵ� �� ������(byte) : " + deocderTxt2
				);
		System.out.println("============================================");
	}
}
