package com.team404.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "home";
	}
	
	//타일즈 템플릿 요청
	@GetMapping("/aaa")
    public String callApi() throws IOException {
        StringBuilder result = new StringBuilder();

        String urlStr = 
        		"http://www.mygreenery.co.kr/ajax_local_callback.jsp;" +
        		"09DTPDKUPHLJTUORKIJSFQ?" +
                "garden" +
                "lightList" +
                "nongsaroApiLoadingArea" +
                "&type=json";
        URL url = new URL(urlStr);
        System.out.println("url : " + url);
        System.out.println("urlStr : " + urlStr);

        HttpURLConnection urlConnection = (HttpURLConnection)  url.openConnection();
        urlConnection.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

        String returnLine;

        while ((returnLine = br.readLine()) != null ) {
            result.append(returnLine+"\n\r");
        }

        urlConnection.disconnect();
        System.out.println(result.toString());

        return "test/aaa";
    }
	@RequestMapping("/bbb")
	public String bbb() {
		return "test/bbb";
	}
	@RequestMapping("/zzz")
	public String zzz() {
		return "zzz";
	}
	@RequestMapping("/kkk")
	public String kkk() {
		return "kkk";
	}
	
}
