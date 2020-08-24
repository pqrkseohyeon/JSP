package com.jsoup.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Son {
	public static void main(String[] args) {
		Document doc;
		try {
			doc = Jsoup.connect("https://search.daum.net/search?nil_suggest=btn&w=tot&DA=SBC&q=%EC%86%90%ED%9D%A5%EB%AF%BC").get();
			Elements container = doc.select("div.type_thumb_s160 dl");
			Elements title = doc.select("div.type_thumb_s160 dl dt");
			Elements data = doc.select("div.type_thumb_s160 dl dd");
			System.out.println("손흥민에 대한 정보 ");
			for(int i=0;i<container.size();i++) {
				String titleText = title.get(i).text();
				String dataText = data.get(i).text();
				System.out.println(titleText+": "+dataText);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}