package com.sanwaf.sample;

import org.owasp.encoder.Encode;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanwaf.core.Sanwaf;

public class Error {

  public static void handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String sanWafTrackingId = Sanwaf.getTrackingId(request);
    String parmsInErrorJson = Sanwaf.getErrors(request);

    
    StringBuilder sb = new StringBuilder();
    sb.append("<html><head><meta content=\"text/html;charset=utf-8\" http-equiv=\"Content-Type\"><meta content=\"utf-8\" http-equiv=\"encoding\"><style>* { Background : #322f2f; color: #d3d0d0;font-family: Arial; }  body { font-size: 1em; }</style></head>");
    sb.append("<body><h1>Oops! an error occurred...</h1>");
    sb.append("<br/><b>The following items are in error</b></br>");

    
    
    
    sb.append("<br/><br/><u><b>SanWaf Result</b>,</u><br/>");
    sb.append("<table><tr><th></th><th></th></tr>");
    sb.append("<tr><td><b>Error ID </b></td><td>").append(sanWafTrackingId).append("</td></tr>");
    sb.append("<tr><td><b>Errors (json) </b></td><td><pre>");
    sb.append(Encode.forHtmlContent(parmsInErrorJson).replaceAll("\\}\\,\\{", "\\}\\,\\<br\\/\\>\\{"));
    sb.append("</pre></td></tr>");
    sb.append("</table>");
    
    sb.append("<br/><br/><a href=\"javascript:history.back()\">Go Back</a>");
    sb.append("<br/></body></html>");

    PrintWriter out = response.getWriter();
    out.print(sb.toString());
  }

}
