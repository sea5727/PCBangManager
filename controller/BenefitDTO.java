package com.controller;

import java.sql.Date;
import java.sql.Time;

public class BenefitDTO {
	int no;
	Date date;
	Time time;
	String name;
	String id;
	int money;
	public BenefitDTO(int no, Date date, Time time, String name, String id, int money) {
		super();
		this.no = no;
		this.date = date;
		this.time = time;
		this.name = name;
		this.id = id;
		this.money = money;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	
	
}