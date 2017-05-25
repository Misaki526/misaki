package com.misaki.ls.nurse.action;

import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.misaki.core.page.Page;
import com.misaki.ls.nurse.entity.Nurse;
import com.misaki.ls.nurse.service.NurseService;

@Controller
@RequestMapping("/ls/nurse")
public class NurseAction {
	@Resource
	private NurseService nurseService;
	
	@RequestMapping(value="/insert")
	@ResponseBody
	public Object insert(Nurse nurse) {
		int i = 0;
		try {
			nurse.setLastModified(new Date());
			i = nurseService.insert(nurse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	@RequestMapping("/selectPage")
	@ResponseBody
	public Object selectPageUseDyc(Page<Nurse> page) {
		
		Page<Nurse> result = nurseService.selectPage(page);
		
		return result.getPageMap();
	}
	
	@RequestMapping("/deleteByPks")
	@ResponseBody
	public Object deleteByPks(String [] pks) {
		int i = 0;
		try {
			i = nurseService.deleteByPks(pks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	@RequestMapping("/updateByPk")
	@ResponseBody
	public Object updateByPk(Nurse nurse) {
		int i = 0;
		try {
			nurse.setLastModified(new Date());
			i = nurseService.updateByPk(nurse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	@RequestMapping("/synchroSolr")
	@ResponseBody
	public Object synchroSolr() {
		int i = 0;
		try {
			i = nurseService.synchroSolr();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
}
