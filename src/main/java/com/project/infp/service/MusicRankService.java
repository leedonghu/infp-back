package com.project.infp.service;

import java.util.ArrayList;
import java.util.List;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.project.infp.entity.Music;

@Service
public class MusicRankService {
    
    public List<Music> serachMusic(String name){
        String url = "https://vibe.naver.com/search/tracks?query=" + name;
        System.out.println("check");
        
        Document doc = null;
        List<Music> musicList = new ArrayList<>();
        try {
            doc = Jsoup.connect(url).get();
            System.out.println(doc.select("#app"));
            Elements element = doc.select("#content > div > div.track_wrap > div.track_section > div:nth-child(2) > div > table > tbody");
            System.out.println(element.hasText());
            for(Element e : element.select("tr")){
                Music music = new Music();
                music.setArtist(e.select("td.song > div.title_badge_wrap > span > a").text());
                music.setTitle(e.select("td.song > div.artist_sub > span:nth-child(1) > span > a > span").text());
                music.setImg(e.select("td.thumb > div > img").attr("src"));
                musicList.add(music);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        for(Music m : musicList){
            System.out.println(m.getArtist());
        }
        
        

        return musicList;
    }

    public List<Music> serachMusic(String name, String page){
        String url = "https://www.melon.com/search/song/index.htm?q=" + name + 
        "&section=&searchGnbYn=Y&kkoSpl=Y&kkoDpType=&mwkLogType=T" + 
        "#params%5Bq%5D=%25EC%2595%2584%25EC%259D%25B4%25EC%259C%25A0&params%5Bsort%5D=hit&params%5Bsection%5D=all&params%5BsectionId%5D=&params%5BgenreDir%5D=&po=pageObj&startIndex=";
        
        
        switch(page){
            case "1" :  url += "1";
            break;
            case "2" :  url += "51";
            break;
            case "3" :  url += "101";
            break;
            case "4" :  url += "151";
            break;
            case "5" :  url += "201";
            break;
        }
        

        Document doc = null;
        List<Music> musicList = new ArrayList<>();
        try {
            
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        Elements element = doc.select("tbody");
        for(Element e : element.select("tr")){
            Music music = new Music();
            music.setArtist(e.select("div#artistName > a.fc_mgray").text());
            music.setTitle(e.select("a.fc_gray").text());
            System.out.println(e.select("div#artistName > a.fc_mgray").text() + " " +e.select("a.fc_gray").text());
            musicList.add(music);
        }
        

        return musicList;
    }
}