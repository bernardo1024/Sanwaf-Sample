package com.sanwaf.sample;

import java.io.IOException;
import java.io.InputStream;

public class Util {
  static String replaceKey(String html, String key, String value) {
    int pos = 0;
    String out = new String(html);
    while(true) {
      pos = html.indexOf(key, pos);
      if(pos >= 0) {
        out = html.substring(0, pos) + value + html.substring(pos + key.length());
        pos += key.length();
      }
      else {
        break;
      }
    }
    return out;
  }
  
  static String readFile(InputStream is) throws IOException {
    StringBuilder sb = new StringBuilder();
    int read = 0;
    byte[] data = new byte[1024];
    while (true) {
      read = is.read(data);
      if (read < 0) {
        break;
      }
      sb.append(new String(data).trim());
      data = new byte[1024];
    }
    is.close();
    return sb.toString();
  }
}

