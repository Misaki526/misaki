package com.misaki.ls.music.action;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.misaki.ls.music.service.MusicService;

@Controller
@RequestMapping("/ls/music")
public class MusicAction {
	@Resource
	private MusicService musicService;
	
	@RequestMapping("/findMusic")
	@ResponseBody
	public Object findMusic(String order) {
		String musicAddr = null;
		
		try {
			int musicNameStartIndex = 0;
			if (order.contains("听")) {
				musicNameStartIndex = order.indexOf("听") + 1;
			} else if (order.contains("放")) {
				musicNameStartIndex = order.indexOf("放") + 1;
			}
			musicAddr = musicService.findMusic(order.substring(musicNameStartIndex));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return musicAddr;
	}
}
