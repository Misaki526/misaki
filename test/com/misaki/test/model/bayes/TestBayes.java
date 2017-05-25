package com.misaki.test.model.bayes; 

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.util.ArrayList; 
import java.util.StringTokenizer;
import com.misaki.qa.classifymodel.bayes.Bayes;

public class TestBayes {
	
	/**
     * 读取测试元组
     * @return 一条测试元组
     */ 
    public ArrayList<String> readTestData() throws IOException{ 
        ArrayList<String> dataTest = new ArrayList<String>(); 
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
        String str = ""; 
        
        while (!(str = reader.readLine()).equals("")) { 
            StringTokenizer tokenizer = new StringTokenizer(str); 
            while (tokenizer.hasMoreTokens()) { 
            	dataTest.add(tokenizer.nextToken()); 
            } 
        }
        
        return dataTest; 
    } 
    
    /**
     * 读取训练元组
     * @return 训练元组集合
     */ 
    public ArrayList<ArrayList<String>> readData() throws IOException { 
        ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>(); 
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
        String str = ""; 
        
        while (!(str = reader.readLine()).equals("")) { 
            StringTokenizer tokenizer = new StringTokenizer(str); 
            ArrayList<String> s = new ArrayList<String>(); 
            while (tokenizer.hasMoreTokens()) { 
                s.add(tokenizer.nextToken()); 
            } 
            datas.add(s);
        } 
        
        return datas; 
    } 
     
    public static void main(String[] args) { 
        TestBayes tb = new TestBayes(); 
        try {
            System.out.println("请输入训练数据"); 
            ArrayList<ArrayList<String>> datas = tb.readData();
            
            while (true) { 
                System.out.println("请输入测试元组"); 
                ArrayList<String> testT = tb.readTestData();
                
                // 预测
                String c = Bayes.predictClass(datas, testT); 
                System.out.println("The class is: " + c); 
            }
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
    }
} 