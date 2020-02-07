package com.yndg.blog.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Script {
	
	public static void href(HttpServletResponse resp, String msg, String uri) throws IOException {
		
		PrintWriter out = resp.getWriter();
		out.print("<script>");
		out.print("alert('"+msg+"');");
		out.print("location.href='"+uri+"';");
		out.print("</script>");
	}
	
}
