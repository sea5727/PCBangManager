package com.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.chat.ClientBackground;
import com.controller.SeatDTO;

public class FrameChattingClient extends JFrame{
	JTextArea textarea;
	JTextField textfield;
	
	DataOutputStream out;
	DataInputStream in;
	SeatDTO seatDto;

	public FrameChattingClient(ClientBackground cb, SeatDTO seatDto, DataOutputStream out, DataInputStream in){
		
		cb.setGUI(this);
		
		this.out = out;
		this.in = in;
		this.seatDto = seatDto;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(300,400);
		setLocation(700,10);
		setTitle("채팅");
		setVisible(true);
		
		
		textarea = new JTextArea(40, 20);
		textarea.setEditable(false);
		textfield = new JTextField(25);
		
		textfield.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						try {
							out.writeUTF(seatDto.getNumber()+"번자리 : " + textfield.getText() + "\n");
							textarea.append(seatDto.getNumber()+"번자리 : " + textfield.getText() + "\n");
							textfield.setText("");
						} catch (Exception e2) {
							e2.printStackTrace();
						}
						
					}});
		add(textarea , BorderLayout.CENTER);
		add(textfield, BorderLayout.SOUTH);
		
	}
	public void appendTextarea(String msg){
		textarea.append(msg);
	}
	public static void main(String[] args){
		//new FrameChattingServer();
	}

}
