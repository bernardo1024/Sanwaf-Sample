package com.sanwaf.sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanwaf.core.Sanwaf;

public class Error {
  public static void handle(Sanwaf sanwaf, HttpServletRequest request, HttpServletResponse response) throws IOException {
    String sanwafTrackingId = Sanwaf.getTrackingId(request);
    String parmsInErrorJson = sanwaf.getAllErrors(request);
    String html = Util.readFile(Error.class.getResource("/error.html").openStream());
    html = Util.replaceKey(html, "%%sanwafTrackingId%%", sanwafTrackingId);
    html = Util.replaceKey(html, "%%parmsInErrorJson%%", parmsInErrorJson);
    PrintWriter pw = response.getWriter();
    pw.print(html);
  }
}
