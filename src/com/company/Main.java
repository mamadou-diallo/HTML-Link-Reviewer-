package com.company;

import netscape.javascript.JSObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.json.simple.*;
import com.google.gson.*;

import java.io.File;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    //Setting Colors
    public static final String GREEN = "\033[0;32m";
    public static final String RED = "\033[0;31m";
    public static final String RESET = "\033[0m";

    //Exit Code variable
    public static int systemExitCode = 0;

    public static void main(String[] args) throws IOException {
        String tempFlag;

        if (args.length > 0) {
            //Returns version value
            if (args[0].matches("--v") || args[0].matches("--version")) {
                System.out.print("HTML Link Reviewer 0.1");
            } else if (args[0].matches("--good")) {

                tempFlag = args[0];


                //Copy argument name
                String currentDir = System.getProperty("user.dir");
                String testThis = "\\" + String.valueOf(args[1]);
                String newDirectory = currentDir + testThis;
                {
                    //Open file & read through each line of html found
                    File input = new File(newDirectory);
                    Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
                    Elements links = doc.select("a[href]");
                    for (Element link : links) {
                        String test = link.attr("href");
                        try {
                            URL url = new URL(test);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.connect();

                            int code = conn.getResponseCode();
                            returnCode(code, tempFlag, test);
                        } catch (Exception e) {
                            // the URL is not in a valid form
                            // System.out.print(" " + test);
                            //  System.out.print(RESET + " Unknown Error Code" + '\n' + RESET);
                        }

                    }

                }
            } else if (args[0].matches("--bad")) {
                //Copy argument name
                tempFlag = args[0];
                String currentDir = System.getProperty("user.dir");
                String testThis = "\\" + String.valueOf(args[1]);
                String newDirectory = currentDir + testThis;
                {
                    //Open file & read through each line of html found
                    File input = new File(newDirectory);
                    Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
                    Elements links = doc.select("a[href]");
                    for (Element link : links) {
                        String test = link.attr("href");
                        try {
                            URL url = new URL(test);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.connect();
                            System.out.print("Link :  " + test);
                            int code = conn.getResponseCode();
                            returnCode(code, tempFlag, test);

                        } catch (MalformedURLException e) {
                            // the URL is not in a valid form
                            System.out.print("Link :  " + test);
                            System.out.print(RESET + " Unknown Error Code" + '\n' + RESET);
                            systemExitCode = 1;
                        } catch (IOException e) {
                            // the connection couldn't be established
                            System.out.print("Link :  " + test);
                            System.out.print(RESET + " Failed to establish connection" + '\n' + RESET);
                            systemExitCode = 2;
                        }

                    }

                }
            } else if (args[0].matches("--telescope")) {
                tempFlag = args[0];
                //Copy argument name
                String localHostLink = "http://localhost:3000/posts";
                {
                    //Open html and take content of content
                    Document doc = Jsoup.connect(localHostLink).ignoreContentType(true).get();
                    String body = doc.body().text();

                    String jSonBody;
                    String tempString = null;


                    //Search for values
                    Pattern p = Pattern.compile("\"([^\"]*)\"");
                    Matcher m = p.matcher(body);
                    while (m.find()) {
                        int i = 0;
                        jSonBody = m.group(i++);
                        tempString = returnString(jSonBody);
                        tempString = tempString.replaceAll("^\"|\"$", "");
                        if (tempString.length() == 10) {
                            String tempUrl = "http://localhost:3000/posts/" + tempString;

                            try {
                                URL url = new URL(tempUrl);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.connect();

                                int code = conn.getResponseCode();
                                returnCode(code, tempFlag, tempUrl);
                            } catch (Exception e) {
                                // the URL is not in a valid form
                                // System.out.print(" " + test);
                                //  System.out.print(RESET + " Unknown Error Code" + '\n' + RESET);
                            }

                        }


                    }

                }
            } else {
                //Copies & stores argument name
                String testThis;
                String newDirectory;
                if (args[0].matches("--all")) {
                    String currentDir = System.getProperty("user.dir");
                    testThis = "\\" + String.valueOf(args[1]);
                    newDirectory = currentDir + testThis;
                } else {
                    String currentDir = System.getProperty("user.dir");
                    testThis = "\\" + String.valueOf(args[0]);
                    newDirectory = currentDir + testThis;
                }


                {
                    tempFlag = args[0];
                    //Open file & read through each line of html found
                    File input = new File(newDirectory);
                    Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
                    Elements links = doc.select("a[href]");
                    //Loops through elements & tests connections
                    for (Element link : links) {
                        String test = link.attr("href");
                        try {
                            URL url = new URL(test);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.connect();
                            System.out.print("Link :  " + test);
                            int code = conn.getResponseCode();
                            //Reads reponse code & returns appropriate message
                            returnCode(code, tempFlag, test);

                        } catch (MalformedURLException e) {
                            // the URL is not in a valid form
                            System.out.print("Link :  " + test);
                            System.out.print(RESET + " Unknown Error Code" + '\n' + RESET);
                            systemExitCode = 1;
                        } catch (IOException e) {
                            // the connection couldn't be established
                            System.out.print("Link :  " + test);
                            System.out.print(RESET + " Failed to establish connection" + '\n' + RESET);
                            systemExitCode = 2;
                        }
                    }
                }
            }
        } else {
            System.out.print("To start using this tool please enter the title of a document as a command line argument");
            systemExitCode = 3;
        }

        System.exit(systemExitCode);
    }


    //function that reads code, flag & link
    //Returns expects results from received data

    public static void returnCode(int errorCode, String flagReceived, String receivedLink) {
        if (flagReceived.matches("--good")) {
            if (errorCode == 200) {
                System.out.print("Link :  " + receivedLink);
                System.out.print(GREEN + " Code 200 - Link is good" + '\n' + RESET);
            }
        } else if (flagReceived.matches("--bad")) {
            if (errorCode == 404) {
                System.out.print("Link :  " + receivedLink);
                System.out.print(RED + " Code 404 - Link is bad" + '\n' + RESET);
                systemExitCode = 1;
            } else if (errorCode == 400) {
                System.out.print("Link :  " + receivedLink);
                System.out.print(RED + " Code 400 - Link is bad" + '\n' + RESET);
                systemExitCode = 1;
            } else {
                System.out.print("Link :  " + receivedLink);
                System.out.print(" Unknown Error Code" + '\n');
                systemExitCode = 1;
            }
        } else if (flagReceived.matches("--telescope")) {
            if (errorCode == 200) {
                System.out.print("Link :  " + receivedLink);
                System.out.print(GREEN + " Code 200 - Link is good" + '\n' + RESET);
            } else if (errorCode == 404) {
                System.out.print("Link :  " + receivedLink);
                System.out.print(RED + " Code 404 - Link is bad" + '\n' + RESET);
                systemExitCode = 1;
            } else if (errorCode == 400) {
                System.out.print("Link :  " + receivedLink);
                System.out.print(RED + " Code 400 - Link is bad" + '\n' + RESET);
                systemExitCode = 1;
            } else {
                System.out.print("Link :  " + receivedLink);
                System.out.print(" Unknown Error Code" + '\n');
                systemExitCode = 1;
            }
        } else {
            if (errorCode == 200) {
                System.out.print("Link :  " + receivedLink);
                System.out.print(GREEN + " Code 200 - Link is good" + '\n' + RESET);
            } else if (errorCode == 404) {
                System.out.print("Link :  " + receivedLink);
                System.out.print(RED + " Code 404 - Link is bad" + '\n' + RESET);
            } else if (errorCode == 400) {
                System.out.print("Link :  " + receivedLink);
                System.out.print(RED + " Code 400 - Link is bad" + '\n' + RESET);
            } else {
                System.out.print(" Unknown Error Code" + '\n');
            }
        }
    }

    public static String returnString(String stringReceived) {
        return stringReceived;
    }
}
