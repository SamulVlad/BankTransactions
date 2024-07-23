 package com.samul.banktransactions.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.samul.banktransactions.util.GenerateId;

public class Transaction implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long id;
	private Date date;
	private Operation operation;
	private String content;
	private double sum;
	
	public Transaction() {
	}
	
	public Transaction(long id, Date date, Operation operation, String content, double sum) {
		this.id = id;
		this.date = date;
		this.operation = operation;
		this.content = content;
		this.sum = sum;
	}

	public Transaction(Date date, Operation operation, String content, double sum) {
		this.id = GenerateId.nextId();
		this.date = date;
		this.operation = operation;
		this.content = content;
		this.sum = sum;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, date, id, operation, sum);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return Objects.equals(content, other.content) && Objects.equals(date, other.date) && id == other.id
				&& operation == other.operation && Double.doubleToLongBits(sum) == Double.doubleToLongBits(other.sum);
	}

	@Override
	public String toString() {
		return	this.getClass().getSimpleName() + " [id=" + id + ", date=" + date + ", operation=" + operation + ", content=" + content
				+ ", sum=" + sum + "]";
	}
	

}
