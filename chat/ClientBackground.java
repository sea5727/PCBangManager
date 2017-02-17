package com.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import com.view.FrameChattingClient;
import com.view.FramePC;

public class ClientBackground implements Runnable {
	private Socket socket;
	
	private DataOutputStream out;
	private DataInputStream in;
	
	private String msg;
	private FrameChattingClient gui;
	public ClientBackground(){
		System.out.println("생성자");
		try {
			socket = new Socket("127.0.0.1",7777);
			
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	public DataOutputStream getDataOutputStream(){
		return out;
	}
	public DataInputStream getDataInputStream(){
		return in;
	}
	public void setGUI(FrameChattingClient frame){
		gui = frame;
	}
	public void sendMessage(String msg){
		try {
			out.writeUTF(msg);	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void run() {
		try {
			while(in != null){
				msg = in.readUTF();
				gui.appendTextarea(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		
	}
	
	public static void main(String[] args){
		ClientBackground cb =  new ClientBackground();
		new Thread(cb).start();
	}
}
