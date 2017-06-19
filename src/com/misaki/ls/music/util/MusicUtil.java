package com.misaki.ls.music.util;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("resource")
public class MusicUtil {
	private static final String searchURL = "http://musicmini.baidu.com/app/search/searchList.php?ie=utf-8&page={1}&qword=";
	private static final String musicURL = "http://ting.baidu.com/data/music/links?songIds=";

	/**
	 * 根据歌曲名返回该歌曲在百度音乐中的id
	 */
	public static String getMusicIdInBaiduMusic(String musicName) throws Exception {
		URL url = new URL(searchURL + musicName);
		URLConnection urlconn = url.openConnection();
		
		// 形如：id="23456789"
		String regex = "^id=\"[0-9]{6,10}\"$";
		Pattern p = Pattern.compile(regex);
		
		InputStream is = urlconn.getInputStream();
		Scanner sc = new Scanner(is);
		
		String musicId = null;
		while (sc.hasNext()) {
			String str = sc.next();
			Matcher matcher = p.matcher(str);
			
			if (matcher.find()) {
				String result = matcher.group();
				musicId = result.substring(4, result.length() - 1);
				break;
			}
		}
		
		return musicId;
	}
	
	/**
	 * 根据歌曲id返回该歌曲的详细信息
	 */
	public static String getMusicInfo(String musicId) throws Exception {
		URL url = new URL(musicURL + musicId);
		URLConnection urlconn = url.openConnection();
		
		InputStream is = urlconn.getInputStream();
		StringBuffer musicInfo = new StringBuffer();
		
		byte[] buf = new byte[4096];
		int size = 0;
		while ((size = is.read(buf)) != -1) {
			musicInfo.append(new String(buf, 0, size));
		} 
		
		return musicInfo.toString();
	}
}
