package com.test.ws.requestobject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Response")
public class Response {
	private Integer status;
	private String  message;
	private String  TransactionDate;
	private String  reason;
	private Object dataList;
	
	
	@XmlElement(name="data")
	public Object getDataList() {
		return dataList;
	}
	public void setDataList(Object dataList) {
		this.dataList = dataList;
	}
	
	
	
	public Response(){
		
	}
	private String getCurrentTimeStemp(String str) throws ParseException{
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return dateFormat.format(new Date());		
	}
	
	public Response(Integer status,String message,String date,String reason,Object list) throws ParseException{
		this.status = status;
		this.message = message;
		this.TransactionDate = getCurrentTimeStemp(date);
		this.reason = reason;
		this.dataList =  list;
	}
	
	@XmlElement(name="status")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@XmlElement(name="message")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@XmlElement(name="transactionDate")
	public String getTransactionDate() {
		return TransactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		TransactionDate = transactionDate;
	}

	@XmlElement(name="reason")
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Data [status=");
		builder.append(status);
		builder.append(", message=");
		builder.append(message);
		builder.append(", data=");
		builder.append(dataList);
		builder.append(", TransactionDate=");
		builder.append(TransactionDate);
		builder.append(", reason=");
		builder.append(reason);
		builder.append("]");
		return builder.toString();
	}
	
}
