package com.company;

import org.junit.Test;


import static com.company.Main.returnCode;
import static com.company.Main.returnString;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

  String testUrl = "www.google.com";
  int testErrorCode = 400;
  String testFlagReceived = "--bad";
  String testReceivedLink = "www.google.com";


  @Test
  public void testReturnCode() {
    assertEquals(400, testErrorCode);
    assertEquals("--bad", testFlagReceived);
    assertEquals("www.google.com", testReceivedLink);
    returnCode(testErrorCode,testFlagReceived,testReceivedLink);
    assertEquals(1, Main.systemExitCode);
  }

  @Test
  public void testReturnString() {
    String testReceived = returnString(testUrl);
    assertEquals("www.google.com", testReceived);
  }
}