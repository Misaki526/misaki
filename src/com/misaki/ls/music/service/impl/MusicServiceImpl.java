package com.misaki.ls.music.service.impl;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.misaki.ls.music.service.MusicService;

@Service("musicService")
public class MusicServiceImpl implements MusicService {
	
	private final String searchURL = "http://musicmini.baidu.com/app/search/searchList.php?ie=utf-8&page={1}&qword=";
	// private final String musicURL = "http://ting.baidu.com/data/music/links?songIds={0}";

	@Override
	public String findMusic(String information) throws Exception {
		URL url = new URL(searchURL + "{黑白之间}");
		URLConnection urlconn = url.openConnection();
		
		String regex = "[0-9]{6,8}";
		Pattern p = Pattern.compile(regex);
		
		InputStream is = urlconn.getInputStream();
		Scanner sc = new Scanner(is);
		while (sc.hasNext()) {
			String line = sc.nextLine();
//			System.out.println(line);
			Matcher matcher = p.matcher(line);
			while (matcher.find()) {
				System.out.println(matcher.group());
			}
		}
		
		return null;
	}
	
	@Test
	public void test() throws Exception {
		findMusic("a");
	}

}
