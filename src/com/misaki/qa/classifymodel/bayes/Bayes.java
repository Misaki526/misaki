package com.misaki.qa.classifymodel.bayes; 
import java.util.ArrayList; 
import java.util.HashMap; 
import java.util.HashSet;
import java.util.Map; 
import java.util.Set;

import com.misaki.qa.classifymodel.util.DecimalCalculate;

/**
 * 贝叶斯分类器
 */
public class Bayes {
	
	/**
     * 将【原训练元组】按类别划分
     * @param datas 训练元组
     * @return Map<类别，属于该类别的所有训练元组>
     */ 
    private static Map<String, ArrayList<ArrayList<String>>> datasOfClass(ArrayList<ArrayList<String>> datas){ 
        Map<String, ArrayList<ArrayList<String>>> map = new HashMap<String, ArrayList<ArrayList<String>>>(); 
        
        // 迭代每一个问题
        for (int i = 0; i < datas.size(); i++) {
        	ArrayList<String> question = datas.get(i);
            String type = question.get(question.size() - 1);     // 获取该问题的类别
            
            if (map.containsKey(type)) {
                map.get(type).add(question); 
            } else { 
            	// 如果之前未出现过该类别
                ArrayList<ArrayList<String>> newType = new ArrayList<ArrayList<String>>();
                newType.add(question); 
                map.put(type, newType); 
            } 
        } 
        return map; 
    } 
     
    /**
     * 在训练数据的基础上预测测试元组的类别
     * 
     * 先验概率P(c)= 类c下单词总数/整个训练样本的单词总数
     * 类条件概率P(tk|c)=(类c下单词tk在各个文档中出现过的次数之和+1)/(类c下单词总数+|V|)
     * V是训练样本的单词表（即抽取单词，单词出现多次，只算一个），|V|则表示训练样本包含多少种单词。
     * 
     * @param datas 训练元组
     * @param testT 测试元组
     * @return 测试元组的类别
     */ 
    public static String predictClass(ArrayList<ArrayList<String>> datas, ArrayList<String> testT) {
        Map<String, ArrayList<ArrayList<String>>> doc = Bayes.datasOfClass(datas);
        Object classes[] = doc.keySet().toArray(); 
        
        double maxP = 0.00; 
        int maxPIndex = -1;
        
        // 训练文本的总单词数
        int totalA = 0;      
        for (int i = 0; i < datas.size(); i++) {
        	totalA += datas.get(i).size() - 1;
        }
        
        /*
         *  对每个类别进行分别计算
         */
        for (int i = 0; i < doc.size(); i++) {
            String type = classes[i].toString();
            ArrayList<ArrayList<String>> questionList = doc.get(type); 
            
            // 该类的总单词数
            int totalB = 0;
            for (int j = 0; j < questionList.size(); j++) {
            	totalB += questionList.get(j).size() - 1;
            }
            
            // 计算先验概率P(c)
            double pOfC = DecimalCalculate.div(totalB, totalA, 10);
            
            // 计算类条件概率P(tk | c)：c类问题中，tk出现的概率，tk为出现在问题中的词汇
            for (int j = 0; j < testT.size(); j++) {
                double pv = Bayes.pOfT(questionList, testT.get(j), datas);
                pOfC = DecimalCalculate.mul(pOfC, pv); 
            }
            
            if(pOfC > maxP){ 
                maxP = pOfC; 
                maxPIndex = i;
            } 
            System.out.println(type + " :: " + pOfC);
        } 
        return classes[maxPIndex].toString(); 
    } 
    
    /**
     * 计算每个词汇的后验概率
     * @param d 样本中某个类中的所有问题
     * @param value 某个词汇
     * @param datas 样本所有数据
     */
    private static double pOfT(ArrayList<ArrayList<String>> d, String value, ArrayList<ArrayList<String>> datas) {
        int V = Bayes.getSize(datas); // 样本中，单词的总数（去重）
        int count = 0;   // 类c下，该单词在文档中出现的次数
        int count2 = 0;  // 类c下，单词的总数
        
        for (int i = 0; i < d.size(); i++) {
        	for (int j = 0; j < d.get(i).size() - 1; j++) {
	        	if (d.get(i).get(j).equals(value)){ 
	                count++;
	            }
	        	count2++;
        	}
        }
        
        return DecimalCalculate.div(count + 1, count2 + V, 10);
    }
    
    /**
     * 集合的非重复元素个数
     */
    private static int getSize(ArrayList<ArrayList<String>> datas) {
    	Set<String> set = new HashSet<String>();
    	for (int i = 0; i < datas.size(); i++) {
    		for (int j = 0; j < datas.get(i).size(); j++) {
    			set.add(datas.get(i).get(j));
    		}
    	}
    	return set.size();
    }
    
} 