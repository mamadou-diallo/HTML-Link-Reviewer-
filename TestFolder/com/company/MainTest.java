package com.company;

import org.junit.Test;


import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.company.Main.returnCode;
import static com.company.Main.returnString;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

  String goodUrl = "http://www.google.com";
  String badUrl = "http://ww.fakeurl323232.com/";


  @Test
  public void testUrlGoodReponse() {
    int code =0;
    try {
      URL url = new URL(goodUrl);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.connect();
      code = conn.getResponseCode();

    } catch (Exception e) {
      // the URL is not in a valid form
      // System.out.print(" " + test);
      //  System.out.print(RESET + " Unknown Error Code" + '\n' + RESET);
    }
    assertEquals(200, code);
  }

  @Test
  public void testUrlBadReponse() {
    int code = 0;
    try {
      URL url = new URL(badUrl);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.connect();
      code = conn.getResponseCode();


    } catch (Exception e) {
      //link couldn't be opened
      code = 400;
      // the URL is not in a valid form
      // System.out.print(" " + test);
      //  System.out.print(RESET + " Unknown Error Code" + '\n' + RESET);
    }
    assertEquals(400,code);
  }

  @Test
  public void testReturnString() {
    String testReceived = returnString(goodUrl);
    assertEquals("www.google.com", testReceived);
  }
}