package com.view;

import com.controller.DAO;
import com.model.Setting;

public class MyFrame {
	
	DAO dao;
	Setting setting = Setting.getInstance();
	public void drawLogin(){
		new FrameLogin();
	}
	public void DrawManager(){
		new FrameManager();
	}
	
	public static void main(String[] args){
		MyFrame mf = new MyFrame();
		mf.drawLogin();
	}
}
