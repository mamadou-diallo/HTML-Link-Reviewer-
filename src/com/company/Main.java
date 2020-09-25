package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static final String GREEN = "\033[0;32m";
    public static final String RED = "\033[0;31m";
    public static final String RESET = "\033[0m";

    public static void main(String[] args) throws IOException{


        if(args.length > 0)
        {
               //Copy argument name
                String currentDir = System.getProperty("user.dir");
                String testThis = "\\" + String.valueOf(args[0]);
                String newDirectory = currentDir + testThis;
            {
                //Open file & read through each line of html found
                File input = new File(newDirectory);
                Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
                Elements links = doc.select("a[href]");
                for (Element link : links){
                    String test = link.attr("href");
                    try {
                        URL url = new URL(test);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.connect();
                        System.out.print("Link :  " + test);
                        int code = conn.getResponseCode();
                        if(code == 200 )
                        {
                            System.out.print(GREEN + " Code 200 - Link is good" + '\n' + RESET );
                        }else if(code == 404){
                            System.out.print(RED + " Code 404 - Link is bad" + '\n' + RESET);
                        }else if(code == 400){
                            System.out.print(RED + " Code 400 - Link is bad" + '\n' + RESET);
                        }else{
                            System.out.print(" Unknown Error Code"+ '\n');
                        }

                    } catch (MalformedURLException e) {
                        // the URL is not in a valid form
                        System.out.print("Link :  " + test);
                        System.out.print(RESET + " Unknown Error Code" + '\n' + RESET);
                    } catch (IOException e) {
                        // the connection couldn't be established
                        System.out.print("Link :  " + test);
                        System.out.print(RESET + " Failed to establish connection" + '\n' + RESET);
                    }

                }

            }
        }else{
            System.out.print("To start using this tool please enter the title of a document as a command line argument");
        }
    }
}