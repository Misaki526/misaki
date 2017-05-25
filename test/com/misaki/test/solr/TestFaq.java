package com.misaki.test.solr;

import java.util.UUID;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

import com.misaki.qa.faq.entity.Faq;

public class TestFaq {
	private static final String URL = "http://localhost:8080/solr/collection1";
	
	@Test
	public void testSearchFaq() throws Exception {
		HttpSolrServer solrServer = new HttpSolrServer(URL);
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.set("q", "faq_title:贫血");
		QueryResponse response = solrServer.query(solrQuery);
		
		SolrDocumentList solrList = response.getResults();
		for (SolrDocument sd : solrList) {
			Faq faq = solrServer.getBinder().getBean(Faq.class, sd);
			System.out.println(faq.getFaqId());
			System.out.println(faq.getFaqTitle());
		}
	}
	
	@Test
	public void testAddFaq() throws Exception {
		@SuppressWarnings("resource")
		HttpSolrServer solrServer = new HttpSolrServer(URL);
		
		String prefix = "faq_";
		
		Faq u1 = new Faq();
		u1.setFaqId(prefix + UUID.randomUUID().toString().substring(4).substring(prefix.length()));
		u1.setFaqTitle("系统b超跟四维彩超一样吗");
		u1.setFaqAnswer("基本一样的，诊断畸形的话二维超声满足了。四维看起来高大上些");
		u1.setFaqType("DES_JUDGE");
		u1.setFaqComment("");
		solrServer.addBean(u1);
		
		Faq u2 = new Faq();
		u2.setFaqId(prefix + UUID.randomUUID().toString().substring(4).substring(prefix.length()));
		u2.setFaqTitle("14.5周岁一次透视对身体损伤大吗");
		u2.setFaqAnswer("偶然一次检查，对身体影响不大");
		u2.setFaqType("DES_OTHER");
		u2.setFaqComment("");
		solrServer.addBean(u2);
		
		Faq u3 = new Faq();
		u3.setFaqId(prefix + UUID.randomUUID().toString().substring(4).substring(prefix.length()));
		u3.setFaqTitle("体内有环能做磁共振吗");
		u3.setFaqAnswer("节育环含有金属，一般不能做磁共振检查。如果想检查头部，可以先做个头颅cta?ctp看看");
		u3.setFaqType("DES_JUDGE");
		u3.setFaqComment("");
		solrServer.addBean(u3);
		
		Faq u4 = new Faq();
		u4.setFaqId(prefix + UUID.randomUUID().toString().substring(4).substring(prefix.length()));
		u4.setFaqTitle("贫血为什么会觉得很累很软吗");
		u4.setFaqAnswer("贫血会使血红蛋白偏低，血红蛋白负责将肺部吸进来的氧气输往全身各个部位。当身体组织供氧不足时，人就会觉得疲劳");
		u4.setFaqType("DES_OTHER");
		u4.setFaqComment("");
		solrServer.addBean(u4);
		
		Faq u5 = new Faq();
		u5.setFaqId(prefix + UUID.randomUUID().toString().substring(4).substring(prefix.length()));
		u5.setFaqTitle("贫血分哪些种类");
		u5.setFaqAnswer("“缺铁性贫血”、“出血性贫血”、“溶血性贫血”、“巨幼红细胞性贫血”、“恶性贫血”、“再生障碍性贫血”");
		u5.setFaqType("DES_DEFINITION");
		u5.setFaqComment("");
		solrServer.addBean(u5);
		
		Faq u6 = new Faq();
		u6.setFaqId(prefix + UUID.randomUUID().toString().substring(4).substring(prefix.length()));
		u6.setFaqTitle("贫血值为多少算是严重的");
		u6.setFaqAnswer("男性正常值是120-160，女性正常值是110-150,90到110是轻度贫血，60到90是中度贫血，60到30是重度贫血，小于30是极重度的");
		u6.setFaqType("DES_OTHER");
		u6.setFaqComment("");
		solrServer.addBean(u6);
		
		Faq u7 = new Faq();
		u7.setFaqId(prefix + UUID.randomUUID().toString().substring(4).substring(prefix.length()));
		u7.setFaqTitle("什么叫做贫血");
		u7.setFaqAnswer("贫血(anaemia)是指全身循环血液中红细胞总量少于正常值以下");
		u7.setFaqType("DES_DEFINITION");
		u7.setFaqComment("");
		solrServer.addBean(u7);
		
		Faq u8 = new Faq();
		u8.setFaqId(prefix + UUID.randomUUID().toString().substring(4).substring(prefix.length()));
		u8.setFaqTitle("糖尿病人适宜喝茶吗");
		u8.setFaqAnswer("糖尿病人是可以喝茶的，但不能乱喝，有些茶喝了可能会使血糖升高。可以选用一些对糖尿病有帮助的茶，如花草茶或中药材泡茶");
		u8.setFaqType("DES_JUDGE");
		u8.setFaqComment("");
		solrServer.addBean(u8);
		
		Faq u9 = new Faq();
		u9.setFaqId(prefix + UUID.randomUUID().toString().substring(4).substring(prefix.length()));
		u9.setFaqTitle("糖尿病会不会死人");
		u9.setFaqAnswer("会死人啊。只要你不控制血糖，不治疗并发症，肯定会死人");
		u9.setFaqType("DES_JUDGE");
		u9.setFaqComment("");
		solrServer.addBean(u9);
		
		Faq u10 = new Faq();
		u10.setFaqId(prefix + UUID.randomUUID().toString().substring(4).substring(prefix.length()));
		u10.setFaqTitle("糖尿病与糖的摄取有关吗");
		u10.setFaqAnswer("长期高糖饮食可引起肥胖及加重胰岛细胞分泌胰岛素的负担，可诱发或促发糖尿病");
		u10.setFaqType("DES_JUDGE");
		u10.setFaqComment("");
		solrServer.addBean(u10);
		
		solrServer.commit();
	}
}
