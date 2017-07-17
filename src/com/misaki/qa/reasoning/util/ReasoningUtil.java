package com.misaki.qa.reasoning.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReasoningUtil {

	public static String getAnswerFromGraph(List<String> words) throws Exception {
		Class.forName("org.neo4j.jdbc.Driver").newInstance();
		
		Connection conn = JdbcUtil.getConnection();
		Statement stmt = conn.createStatement();
		String result = null;
		
		/**
		 * 1. 问英文名
		 * 2. 问就诊科室
		 * 3. 问常见症状
		 * 4. 问传染性
		 * 5. 问病因
		 * 6. 问临床表现
		 * 7. 问检查
		 * 8. 问诊断
		 * 9. 问治疗
		 * 10.问传播途径
		 * 。。。
		 */

		// 拼凑查询语句
		StringBuffer sb = new StringBuffer();
		
		// 找到疾病本体
		String disease = null;
		for (String word : words) {
			if (diseases.contains(word)) {
				disease = word;
				break;
			}
		}
		sb.append("START n=node(*) WHERE n.name='" + disease + "'");
		
		boolean flag = false;
		String temp = null;
		for (String word : words) {
			if ("英文名称".equals(word)) {
				sb.append(" RETURN n." + word);
				temp = "n." + word;
				flag = true;break;
			} else if ("就诊科室".equals(word)) {
				sb.append(" RETURN n." + word);
				temp = "n." + word;
				flag = true;break;
			} else if ("常见症状".equals(word)) {
				sb.append(" RETURN n." + word);
				temp = "n." + word;
				flag = true;break;
			} else if ("传染性".equals(word)) {
				sb.append(" RETURN n." + word);
				temp = "n." + word;
				flag = true;break;
			} else if ("传播途径".equals(word)) {
				sb.append(" RETURN n." + word);
				temp = "n." + word;
				flag = true;break;
			} else if ("别称".equals(word)) {
				sb.append(" RETURN n." + word);
				temp = "n." + word;
				flag = true;break;
			}
		}
		if (flag == false) {
			sb.append(" RETURN n");
			temp = "n";
		}
		
		
		ResultSet rs = stmt.executeQuery(sb.toString());
		if (rs.next()) {
			result = rs.getString(temp);
		} else {
			result = "Sorry";
		}
		
		JdbcUtil.close(conn, stmt);
		
		return result;
	}
	
	
	private static List<String> diseases;
	
	static {
		diseases = new ArrayList<String>();
		diseases.add("糖尿病");
		diseases.add("艾滋病");
		diseases.add("自慰");
		diseases.add("湿疹");
		diseases.add("荨麻疹");
		diseases.add("手足口病");
		diseases.add("高血压");
		diseases.add("前列腺炎");
		diseases.add("尖锐湿疣");
		diseases.add("抑郁症");
		diseases.add("梅毒");
		diseases.add("痛风");
		diseases.add("早泄");
		diseases.add("颈椎病");
		diseases.add("肺结核");
		diseases.add("肾结石");
		diseases.add("口腔溃疡");
		diseases.add("白血病");
		diseases.add("子宫肌瘤");
		diseases.add("带状疱疹");
		diseases.add("便秘");
		diseases.add("智齿");
		diseases.add("痤疮");
		diseases.add("脂肪肝");
	}
}
