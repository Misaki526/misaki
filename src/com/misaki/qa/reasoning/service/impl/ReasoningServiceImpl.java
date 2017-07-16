package com.misaki.qa.reasoning.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.misaki.core.util.NLPUtil;
import com.misaki.qa.reasoning.service.ReasoningService;
import com.misaki.qa.reasoning.util.JdbcUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service("reasoningService")
public class ReasoningServiceImpl implements ReasoningService {

	@Override
	public String findAnswer(String question) throws Exception {
		
		// 获取分词结果
		List<String> splitWords = NLPUtil.getSplitWords(question);
		
		Class.forName("org.neo4j.jdbc.Driver").newInstance();;
		Connection conn = JdbcUtil.getConnection();
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("START n=node(*) RETURN n");
		while (rs.next()) {
			System.out.println(rs.getString("n"));
		}
		
		JdbcUtil.close(conn, stmt);
		
		return "success";
	}
	
}
