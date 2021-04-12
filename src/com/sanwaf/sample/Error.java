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
    String sanwafTrackingId = Sanwaf.getTrackingId(request);
    String parmsInErrorJson = Sanwaf.getErrors(request);

    StringBuilder sb = new StringBuilder();
    sb.append("<html><head><meta content=\"text/html;charset=utf-8\" http-equiv=\"Content-Type\"><meta content=\"utf-8\" http-equiv=\"encoding\"><style>* { Background : #322f2f; color: #d3d0d0;font-family: Arial; }  body { font-size: 1em; }</style></head>");
    sb.append("<body><h1>Oops! an error occurred...</h1>");
    
    sb.append("<br/><b>Sanwaf Tracking ID</b><br/>");
    sb.append(sanwafTrackingId).append("<br/><br/>");
    
    sb.append("<b>The following items are in error</b> (sanwaf-server errors in json format)<br/><pre>");
    sb.append(Encode.forHtmlContent(parmsInErrorJson).replaceAll("\\}\\,\\{", "\\}\\,\\<br\\/\\>\\{"));
    sb.append("</pre><br/><br/>");
    
    sb.append("<b>The following items are in error</b> (in table format)<br/>");
    sb.append("<div id='table'></div>");
    
    StringBuilder js = new StringBuilder();
    js.append("<script>var _table_=document.createElement('table'),_tr_=document.createElement('tr'),_th_=document.createElement('th'),_td_ = document.createElement('td');");
    js.append("function buildHtmlTable(arr){var table=_table_.cloneNode(),columns=addAllColumnHeaders(arr,table);for(var i=0,maxi=arr.length;i<maxi;++i){var tr=_tr_.cloneNode();for(var j=0,maxj=columns.length;j<maxj;++j) {var td=_td_.cloneNode();cellValue=arr[i][columns[j]];td.appendChild(document.createTextNode(arr[i][columns[j]] || ''));tr.appendChild(td);}table.appendChild(tr);}return table;}");
    js.append("function addAllColumnHeaders(arr,table){var columnSet=[],tr=_tr_.cloneNode(false);for(var i=0,l=arr.length;i<l;i++){for(var key in arr[i]){if(arr[i].hasOwnProperty(key) && columnSet.indexOf(key)===-1){columnSet.push(key);var th=_th_.cloneNode(false);th.appendChild(document.createTextNode(key));tr.appendChild(th);}}}table.appendChild(tr);return columnSet;}</script>");
    js.append("<script>document.getElementById('table').appendChild(buildHtmlTable(" + parmsInErrorJson + "));(document.getElementsByTagName('table')[0]).style=\"border-spacing: 15px; border:thick solid #FF0000\";</script>");
    sb.append(js);

    sb.append("<br/><br/><a href=\"javascript:history.back()\">Go Back</a>");
    sb.append("<br/></body></html>");

    PrintWriter out = response.getWriter();
    out.print(sb.toString());
  }
}
