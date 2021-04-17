package com.sanwaf.sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Success {
  private Success() {
  }

  public static void handle(HttpServletResponse response) throws IOException {
    StringBuilder sb = new StringBuilder();
    sb.append(
        "<html><head><meta content=\"text/html;charset=utf-8\" http-equiv=\"Content-Type\"><meta content=\"utf-8\" http-equiv=\"encoding\"><style>* { Background : #322f2f; color: #d3d0d0;font-family: Arial; }  body { font-size: 1em; }</style></head>");
    sb.append("<body><h1>Success!</h1>");
    sb.append("<br/><b>All parameter passed server-side validation.</b></br>");
    sb.append("<br/><br/><a href=\"javascript:history.back()\">Go Back</a>");
    sb.append("<br/></body></html>");

    PrintWriter out = response.getWriter();
    out.print(sb.toString());
  }
}
