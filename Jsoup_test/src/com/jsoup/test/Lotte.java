package com.jsoup.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Lotte {

	public static void main(String[] args) {
		Document doc;
		try {
			doc = Jsoup.connect("https://m.dhlottery.co.kr/common.do?method=main").get();
		//	Element lottoDrwNo = doc.select("div.prizeresult").first();
			Element lottoDrwNo = doc.selectFirst("div.prizeresult");
			Elements num = lottoDrwNo.select("div.num span");
			System.out.println(num.text());
	
//			for(int i=0; i<num.size(); i++) {
//				String su =  num.get(i).text();
//				 System.out.print(su +"\t");
//		  }
			System.out.println("------------");
			Elements nums = lottoDrwNo.select("div.num");
			 System.out.print(nums.select("span").text()+"\t");
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
