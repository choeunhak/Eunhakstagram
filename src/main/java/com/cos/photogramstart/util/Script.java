package com.cos.photogramstart.util;

public class Script {

	
	public static String back(String msg) {
		
		//js에서 20자 제한 막기
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("history.back();");
		sb.append("</script>");
		
		return sb.toString();
	}
}
