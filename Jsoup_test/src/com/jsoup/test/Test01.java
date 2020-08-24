package com.jsoup.test;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Test01 {

	public static void main(String[] args) {
	try {
		Document	 doc = 
				Jsoup.connect("https://search.daum.net/search?w=tot&DA=YZR&t__nil_searchbox=btn&sug=&sugo=&sq=&o=&q=%EC%98%81%ED%99%94").get();
		
		//System.out.println(doc);
		Elements movie_list =  doc.select("ol.movie_list li");
		Elements titleContainer = movie_list.select("div.info_tit a");
		Elements rateContainer = movie_list.select("em.rate");
		ArrayList<Movie> arr = new ArrayList<>();
		for(int i= 0; i<titleContainer.size();i++) {
			Movie movie = new Movie();
			String title = titleContainer.get(i).text();
			String rate = rateContainer.get(i).text();
			movie.setTitle(title);
			movie.setRate(rate);
			arr.add(movie);
			
		}
		for(Movie movie :  arr) {
			System.out.println("제목 : " + movie.getTitle());
			System.out.println("평점 : " + movie.getRate());
			System.out.println();
		}
		
		
	} catch (IOException e) {
		e.printStackTrace();
	}
		

	}

}
