package com.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.chat.ClientBackground;
import com.controller.DAO;
import com.controller.MemberDTO;
import com.controller.SeatDTO;
import com.model.Setting;

public class FramePC extends JFrame {
	MemberDTO memberDto;
	SeatDTO seatDto;
	panel myPanel;

	DataOutputStream out;
	DataInputStream in;
	DAO dao = DAO.getInstance();
	ClientBackground cb =  new ClientBackground();
	public FramePC(MemberDTO memberDto, SeatDTO seatDto) {
		
		
		new Thread(cb).start();
		
		
		out = cb.getDataOutputStream();
		in = cb.getDataInputStream();
		
		
		this.memberDto = memberDto;
		this.seatDto = seatDto;
		
		setTitle("");
		setSize(200, 180);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(Setting.width - 200, 0);

		myPanel = new panel();
		add(myPanel);
		setVisible(true);
		
		
	}
	
	class panel extends JPanel implements Runnable {
		JLabel name;
		JLabel age;
		JLabel startTime;
		JLabel fee;
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
		SimpleDateFormat startDate = new SimpleDateFormat("hh:mm:ss");
		String time = dateFormat.format(new Date());
		JLabel timeLabel;
		
		JButton btn_Chat;
		JLabel test;
		
		public panel() {
			setLayout(null);

			timeLabel = new JLabel();
			timeLabel.setBounds(10, 70, 150, 20);
			new Thread(this).start();

			name = new JLabel("이         름 : ");
			name.setBounds(10, 10, 150, 20);
			age = new JLabel("나         이 : ");
			age.setBounds(10, 30, 150, 20);
			startTime = new JLabel("시작시간 : " + startDate.format(new Date()));
			startTime.setBounds(10, 50, 150, 20);
			btn_Chat = new JButton("카운터 문의");
			btn_Chat.setBounds(10, 100, 150, 30);
			btn_Chat.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e){
							new FrameChattingClient(cb, seatDto ,out, in);
						}
					}
					);
			
			if(memberDto != null){
				name.setText("이         름 : " + memberDto.getName());
				age.setText("나         이 : " + memberDto.getAge());
			}
			
			
			add(name);
			add(age);
			add(startTime);
			add(timeLabel);
			add(btn_Chat);
			
			
		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				timeLabel.setText("시         간 : " + dateFormat.format(new Date()));
			}

		}
	}

	public static void main(String[] args) {
		//new FramePC(null);
	}

}
