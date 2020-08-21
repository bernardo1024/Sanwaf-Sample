package com.sanwaf.sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sanwaf.core.Sanwaf;

public class SanWafTestServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static Sanwaf sanwaf;

  static {
    try {
      sanwaf = new Sanwaf(new com.sanwaf.log.LoggerSystemOut(), "/sanwaf-template.xml");
    } catch (IOException ioe) {
      System.out.println("Exception Raised Instanciating Sanwaf: " + ioe.getMessage() + "; " + ioe);
    }
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse resopnse) throws ServletException, IOException {
    service(request, resopnse);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse resopnse) throws ServletException, IOException {
    service(request, resopnse);
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String sanWafTrackingId = "";
    String parmsInErrorJson = "";
    String an = "";
    String anam = "";
    String n = "";
    String nd = "";
    String c = "";
    String s = "";

    if (request.getParameterNames() != null && request.getParameterNames().hasMoreElements()) {
      if (sanwaf.isThreatDetected(request)) {
        sanWafTrackingId = Sanwaf.getTrackingId(request);
        parmsInErrorJson = Sanwaf.getErrors(request);
      }
      //note that there is no data encoding, thus code can be XSS'd
      an = request.getParameter("alphanumeric");
      anam = request.getParameter("alphanumericandmore");
      n = request.getParameter("numeric");
      nd = request.getParameter("numericdelimited");
      c = request.getParameter("char");
      s = request.getParameter("string");
    }

    StringBuilder sb = new StringBuilder();
    sb.append("<html><body><h1>Sanwaf Test Page</h1>");
    sb.append("<br/><b>Enter data in the following inputs and submit to see errors caught by Sanwaf</b></br>");
    sb.append("<form action='/sanwaf' method=get>");
    sb.append("<table>");
    sb.append("<tr><td>AlphaNumeric </td><td><input name='alphanumeric' value='" + an + "'/></td></tr>");
    sb.append("<tr><td>AlphaNumericAndMore (single quote allowed) </td><td><input name='alphanumericandmore' value='" + anam + "'/></td></tr>");
    sb.append("<tr><td>Numeric </td><td><input name='numeric' value='" + n + "'/></td></tr>");
    sb.append("<tr><td>NumericDelimited (coma delimiter) </td><td><input name='numericdelimited' value='" + nd + "'/></td></tr>");
    sb.append("<tr><td>Char </td><td><input name='char' value='" + c + "'/></td></tr>");
    sb.append("<tr><td>String </td><td><input name='string' value='" + s + "'/></td></tr>");
    sb.append("</table>");
    sb.append("<br/><input type='submit' value='Submit'/>");
    sb.append("</form>");
    sb.append("<br/><br/><u><b>SanWaf Result</b>,</u><br/>");
    sb.append("<table><tr><th></th><th></th></tr>");
    sb.append("<tr><td><b>Error ID </b></td><td>").append(sanWafTrackingId).append("</td></tr>");
    sb.append("<tr><td><b>Errors (json) </b></td><td><pre>");
    sb.append(htmlEscape(parmsInErrorJson).replaceAll("\\}\\,\\{", "\\}\\,\\<br\\/\\>\\{").replaceAll("\\}\\,\\{", "\\}\\,\\<br\\/\\>\\{"));
    sb.append("</pre></td></tr>");
    sb.append("</table>");
    sb.append("<br/></body></html>");

    PrintWriter out = response.getWriter();
    out.print(sb.toString());
  }

  private static String htmlEscape(String s) {
    s = s.replaceAll("&", "&amp");
    s = s.replaceAll("\"", "&quot");
    s = s.replaceAll("<", "&lt");
    s = s.replaceAll(">", "&gt");
    return s;
  }
}
