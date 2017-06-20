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
	
	/**
	 * 根据返回的音乐详细信息，返回其下载地址
	 */
	public static String getMusicAddr(String musicInfo) {
		int beginIndex = musicInfo.indexOf("songLink") + 11;
		
		int endIndex = musicInfo.indexOf("\"", beginIndex);
		
		return musicInfo.substring(beginIndex, endIndex).replace("\\/", "/");
	}
	
	// {"errorCode":22000,"data":{"xcode":"50ca66f24e21ae2d2a7250ec29f7e010","songList":[{"queryId":"7503853","songId":7503853,"songName":"\u9ed1\u767d\u4e4b\u95f4","artistId":"1509,8524","artistName":"\u621a\u8587,\u8303\u9038\u81e3","albumId":7344488,"albumName":"\u65e0\u61c8\u53ef\u51fb\u4e4b\u9ad8\u624b\u5982\u6797 \u5f71\u89c6\u539f\u58f0\u789f","songPicSmall":"","songPicBig":"","songPicRadio":"http:\/\/musicdata.baidu.com\/data2\/pic\/246680861\/246680861.jpg@s_0,w_300","lrcLink":"http:\/\/musicdata.baidu.com\/data2\/lrc\/13897600\/13897600.lrc","version":"","copyType":0,"time":295,"linkCode":22000,"songLink":"http:\/\/yinyueshiting.baidu.com\/data2\/music\/bcb8ebc2effea451973d0724af15b1c2\/542034486\/75038531497844861128.mp3?xcode=50ca66f24e21ae2d21ec55f2ef944ac8","showLink":"http:\/\/yinyueshiting.baidu.com\/data2\/music\/bcb8ebc2effea451973d0724af15b1c2\/542034486\/75038531497844861128.mp3?xcode=50ca66f24e21ae2d21ec55f2ef944ac8","format":"mp3","rate":128,"size":4735114,"relateStatus":"0","resourceType":"0"}]}}

	
	// http:\/\/yinyueshiting.baidu.com\/data2\/music\/bcb8ebc2effea451973d0724af15b1c2\/542034486\/75038531497844861128.mp3?xcode=50ca66f24e21ae2d21ec55f2ef944ac8
}
