package com.controller;

import java.sql.Timestamp;

public class SeatDTO {
	int number;
	int x;
	int y;
	String macaddress;
	String onoff;
	Timestamp timestamp;
	public SeatDTO(int number, int x, int y, String macaddress, String onoff, Timestamp timestamp) {
		super();
		this.number = number;
		this.x = x;
		this.y = y;
		this.macaddress = macaddress;
		this.onoff = onoff;
		this.timestamp = timestamp;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getMacaddress() {
		return macaddress;
	}
	public void setMacaddress(String macaddress) {
		this.macaddress = macaddress;
	}
	public String getOnoff() {
		return onoff;
	}
	public void setOnoff(String onoff) {
		this.onoff = onoff;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
			
}
