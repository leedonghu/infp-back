package com.project.infp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.infp.entity.Music;
import com.project.infp.service.MusicRankService;

@Controller
public class InfpController {
    
    @Autowired
    private MusicRankService musicRankService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/music/{name}", method = RequestMethod.GET)
    public List<Music> musicName(@PathVariable("name") String name){
        List<Music> title = musicRankService.serachMusic(name);

        return title;

    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/music/{name}/{page}", method = RequestMethod.GET)
    public List<Music> musicName(@PathVariable("name") String name, @PathVariable("page") String page){
        
        List<Music> title = musicRankService.serachMusic(name, page);

        return title;

    }

    @CrossOrigin
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test(){
        return "test";
    }
}