package com.misaki.ls.music.service.impl;

import org.springframework.stereotype.Service;
import com.misaki.ls.music.service.MusicService;
import com.misaki.ls.music.util.MusicUtil;

@Service("musicService")
public class MusicServiceImpl implements MusicService {
	
	@Override
	public String findMusic(String information) throws Exception {
		String musicId = MusicUtil.getMusicIdInBaiduMusic("{" + information + "}");
		
		String musicInfo = null;
		if (musicId != null)
			musicInfo = MusicUtil.getMusicInfo("{" + musicId + "}");
		
		String musicAddr = null;
		if (musicInfo != null) {
			musicAddr = MusicUtil.getMusicAddr(musicInfo);
		}
		
		return musicAddr;
	}

}
