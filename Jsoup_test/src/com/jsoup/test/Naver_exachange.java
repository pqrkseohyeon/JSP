package com.jsoup.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Naver_exachange {
	public static void main(String[] args) {
		Document doc;
		try {
			doc = Jsoup.connect("https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=%ED%99%98%EC%9C%A8").get();
			Elements mains = doc.select("table.rate_table_info");
			Elements tr = mains.select("tbody tr");
			//System.out.println(tr.text());
			for(int i=0 ;i < tr.size();i++) {
				String title = tr.get(i).select("th a").text();
				String price = tr.get(i).select("td").first().text();
				System.out.println(title +"\t ==> " + price);
			}
		
		} catch (IOException e) {
	
			e.printStackTrace();
		}
	}
}
