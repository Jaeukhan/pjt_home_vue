package com.ssafy.finalbe.model.service;

import com.ssafy.finalbe.model.NewsDto;
import com.ssafy.finalbe.model.mapper.NewsMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class NewsServiceImpl {
	@Autowired
	private NewsMapper ndao;
	
	public List<NewsDto> newsList(){
		return ndao.selectAll();
	}
	public void load() throws IOException{
		init();
		newscrolling();
	}
	//뉴스 크롤링
	public void newscrolling() throws IOException {
		String url = "https://land.naver.com/news/";
		Document doc= Jsoup.connect(url).get();
		doc.text();
		Elements elem = doc.select("div[class=\"group2\"]");
		for(Element e: elem.select("li")) {
			Elements span = e.select("span");
			List<String> list = new ArrayList();
			for(Element s: span) {
				if(s.text().length()>0) list.add(s.text());
			}

			if(span.text()!=null&&span.text().length()>0) {
				ndao.insert(new NewsDto(1, list.get(0),list.get(1),list.get(2), span.select("a").attr("href")));
			}
		}
	}

	public void init() {
		ndao.init();//TRUNCATE newsinfo;
	}
	
}
