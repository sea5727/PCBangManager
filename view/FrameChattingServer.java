package com.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.chat.ServerBackground;

public class FrameChattingServer extends JFrame{
	JTextArea textarea;
	JTextField textfield;
	
	DataOutputStream out;
	DataInputStream in;
	ServerBackground sb;
	public FrameChattingServer(ServerBackground sb){
		
		this.sb = sb;
		
		if(sb != null){
			out = sb.getOutputStream();
			in = sb.getDataInputStream();
			sb.setGUI(this);
		}
		else {
			System.out.println("framechattingserver 널임");
		}
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(300,400);
		setLocation(700,10);
		setTitle("서버채팅");
	
		
		
		textarea = new JTextArea(40, 20);
		textarea.setEditable(false);
		textfield = new JTextField(25);
		
		
		textfield.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						try {
						
							out.writeUTF("카운터 : " + textfield.getText());
							textarea.append("카운터 : " + textfield.getText() + "\n");
							textfield.setText("");	
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}});
		add(textarea , BorderLayout.CENTER);
		add(textfield, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	public void appendTextarea(String msg){
		textarea.append(msg);
	}
	
	public static void main(String[] args){
		new FrameChattingServer(null);
	}

}
