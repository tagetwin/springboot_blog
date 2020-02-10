package com.yndg.blog.util;

public class Script {
	
	public static String href(String msg, String uri) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("location.href='"+uri+"';");
		sb.append("</script>");
		
		return sb.toString();
	}
	
	public static String back(String msg) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("history.back();");
		sb.append("</script>");
		
		return sb.toString();
	}
}
