package com.sanwaf.sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Success {
  public static void handle(HttpServletResponse response) throws IOException {
    String html = Util.readFile(Error.class.getResource("/success.html").openStream());
    PrintWriter pw = response.getWriter();
    pw.print(html);
  }
}
