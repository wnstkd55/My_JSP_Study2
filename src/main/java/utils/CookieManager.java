package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {

	//1.쿠키 생성 메소드
		//명시한 이름 , 값, 유지기간을 조건으로 새로운 쿠키를 생성.
	public static void makeCookie(HttpServletResponse response, String cName, String cvalue, int cTime) {
		
		Cookie cookie = new Cookie(cName, cvalue); //쿠키 생성
		cookie.setPath("/"); //경로 설정
		cookie.setMaxAge(cTime); //유지기간 
		response.addCookie(cookie); //쿠키 저장(Client HDD에 저장)
	} 	
	//2.쿠키 정보를 읽는 메소드
		//명시한 쿠키이름을 찾아 그 값을 반환하는 메소드
	public static String readCookie(HttpServletRequest request, String cName) {
		String cookieValue =""; //반환 값
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c : cookies) {
				String cookieName = c.getName();
				
				//매개변수로 입풋되는 cName 변수에 해당하는 이름의 value 를 리턴
				if(cookieName.equals(cName)) {
					cookieValue = c.getValue(); //이름 인풋받아서 값을 아웃풋
				}
			}
		}
		return cookieValue; 
	}
	
	//3.쿠키 삭제 메소드
		//명시한 이름의 쿠키를 삭제하는 메소드 
	public static void deleteCookie(HttpServletResponse response, String cName) {
		makeCookie(response, cName, "", 0);
	}
	
	
	
}
