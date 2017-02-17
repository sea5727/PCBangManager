package com.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.view.FrameChattingServer;

public class ServerBackground implements Runnable{
	private ServerSocket serverSocket;
	private Socket socket;
	
	private DataInputStream in;
	private DataOutputStream out;
	
	private FrameChattingServer gui;

	public ServerBackground(){
		System.out.println("생성자");
	}
	public DataInputStream getDataInputStream(){
		return in;
	}
	public DataOutputStream getOutputStream(){
		return out;
	}
	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(7777);
			
			while(true){
				System.out.println("서버 대기중");
				socket = serverSocket.accept();
				Receiver receiver = new Receiver(socket);
				out = receiver.getDataOutputStream();
				in = receiver.getDataInputStream();
				receiver.start();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setGUI(FrameChattingServer frame){
		gui = frame;
	}
	class Receiver extends Thread{
		private DataInputStream in;
		private DataOutputStream out;
		public Receiver(Socket socket){
			try {
				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		public DataInputStream getDataInputStream(){
			return in;
		}
		public DataOutputStream getDataOutputStream(){
			return out;
		}
		public void run(){
			try {
				while(in != null){
					String msg = in.readUTF();
					gui.appendTextarea(msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args){
		ServerBackground sb = new ServerBackground();
		new Thread(sb).start();
	}
}
