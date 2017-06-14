package com.misaki.qa.faq.action;

import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.misaki.core.page.Page;
import com.misaki.qa.faq.entity.Faq;
import com.misaki.qa.faq.service.FaqService;

@Controller
@RequestMapping(value = "/qa/faq")
public class FaqAction {
	
	@Resource
	private FaqService faqService;
	
	@RequestMapping(value="/insert")
	@ResponseBody
	public Object insert(Faq faq) {
		int i = 0;
		try {
			faq.setLastModified(new Date());
			i = faqService.insert(faq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	@RequestMapping("/selectPage")
	@ResponseBody
	public Object selectPageUseDyc(Page<Faq> page) {
		
		Page<Faq> result = faqService.selectPage(page);
		
		return result.getPageMap();
	}
	
	@RequestMapping("/deleteByPks")
	@ResponseBody
	public Object deleteByPks(String [] pks) {
		int i = 0;
		try {
			i = faqService.deleteByPks(pks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	@RequestMapping("/updateByPk")
	@ResponseBody
	public Object updateByPk(Faq faq) {
		int i = 0;
		try {
			faq.setLastModified(new Date());
			i = faqService.updateByPk(faq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	@RequestMapping("/findAnswerInFaq")
	@ResponseBody
	public Object findAnswerInFaq(String order) {
		
		Faq result = null;
		try {
			result = faqService.findAnswerInFaq(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result.getFaqAnswer();
	}
	
	@RequestMapping("/synchroSolr")
	@ResponseBody
	public Object synchroSolr() {
		int i = 0;
		try {
			i = faqService.synchroSolr();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
}
