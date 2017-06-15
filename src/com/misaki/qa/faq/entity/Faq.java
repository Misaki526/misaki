package com.misaki.qa.faq.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import org.apache.solr.client.solrj.beans.Field;

public class Faq implements Serializable {
	private static final long serialVersionUID = -2918320670721541180L;

	@Field("id")
	private String faqId;
	@Field("faq_title")
	private String faqTitle;
	@Field("faq_answer")
	private String faqAnswer;
	@Field("faq_type")
	private String faqType;
	@Field("faq_comment")
	private String faqComment;
	@Field("last_modified")
	private Date lastModified;

	public Faq() {
		this.faqId = "faq" + UUID.randomUUID().toString().replace("-", "");
	}

	public String getFaqId() {
		return faqId;
	}

	public void setFaqId(String faqId) {
		this.faqId = faqId;
	}

	public String getFaqTitle() {
		return faqTitle;
	}

	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}

	public String getFaqAnswer() {
		return faqAnswer;
	}

	public void setFaqAnswer(String faqAnswer) {
		this.faqAnswer = faqAnswer;
	}

	public String getFaqType() {
		return faqType;
	}

	public void setFaqType(String faqType) {
		this.faqType = faqType;
	}

	public String getFaqComment() {
		return faqComment;
	}

	public void setFaqComment(String faqComment) {
		this.faqComment = faqComment;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	@Override
	public String toString() {
		return "Faq [faqId=" + faqId + ", faqTitle=" + faqTitle
				+ ", faqAnswer=" + faqAnswer + ", faqType=" + faqType
				+ ", faqComment=" + faqComment + ", lastModified="
				+ lastModified + "]";
	}

}
