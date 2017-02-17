package com.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.chat.ServerBackground;
import com.controller.DAO;
import com.controller.MemberDTO;
import com.controller.SeatDTO;

public class FrameCalculation extends JFrame {
	DAO dao = DAO.getInstance();
	Panel myPanel;
	MemberDTO memberDto;
	SeatDTO seatDto;
	ServerBackground sb;
	public FrameCalculation(MemberDTO memberDto, SeatDTO seatDto, ServerBackground sb) {
		this.memberDto = memberDto;
		this.seatDto = seatDto;
		
		this.sb = sb;
		if(sb == null)
			System.out.println("frameCalculate sb는 널");
		
		setTitle("계산");
		setLocation(700, 200);
		setSize(300, 330);
		myPanel = new Panel();
		add(myPanel);
		setVisible(true);
	}

	class Panel extends JPanel{

		JLabel label_Search;
		JTextField text_Search;
		JButton btn_Search;
		JLabel label_Number;
		JLabel label_Id;
		JLabel label_Name;
		JLabel label_Age;
		JLabel label_Time;
		JLabel label_Price;
		JButton btn_Pay;
		JButton btn_Cancel;
		JButton btn_Message;
		Timestamp timestamp;
		int price;
		int SeatNumber;
		
		public Panel() {
			setLayout(null);
			label_Search = new JLabel("검 색 : ");
			label_Search.setBounds(10, 10, 70, 30);
			text_Search = new JTextField();
			text_Search.setBounds(80, 10, 100, 30);
			btn_Search = new JButton("찾 기");
			btn_Search.setBounds(190, 10, 90, 30);
			label_Number = new JLabel("좌석번호 : ");
			label_Number.setBounds(10, 40, 200, 30);
			label_Id = new JLabel("아이디 : " );
			label_Id.setBounds(10, 70, 200, 30);
			label_Name = new JLabel("이름 : ");
			label_Name.setBounds(10, 100, 200, 30);
			label_Age = new JLabel("나이 : ");
			label_Age.setBounds(10, 130, 200, 30);
			label_Time = new JLabel("사용시간 : ");
			label_Time.setBounds(10, 160, 200, 30);
			label_Price = new JLabel("가격 : ");
			label_Price.setBounds(10, 190, 200, 30);
			if(seatDto != null){
				label_Number.setText("좌석번호 : " + seatDto.getNumber());
			}
			if(memberDto != null){
				label_Id.setText("아이디 : " + memberDto.getId());
				label_Name.setText("이름 : " + memberDto.getName());
				label_Age.setText("나이 : " + memberDto.getAge());
				long timeoffset = System.currentTimeMillis() - seatDto.getTimestamp().getTime();
				int hour = (int)(timeoffset / 1000 / 60 / 60);
				int minute = (int)((timeoffset / 1000 / 60) % 60);
				
				price = hour * 1000 + (minute / 6) * 100;
				if(price < 1000){
					price = 1000;
				}
				label_Time.setText("사용시간 : " + hour + " : " + minute);
				label_Price.setText("가격 : " + price);
			}
			btn_Search.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e){
							if(!text_Search.getText().equals("")){
								int number = Integer.parseInt(text_Search.getText());
								seatDto = dao.isUse(number);
								memberDto = dao.searchMemberUsingId(seatDto.getOnoff());
								initCalculation();
							}
						}}); 
					
			
			add(label_Search);
			add(text_Search);
			add(btn_Search);
			add(label_Number);
			add(label_Id);
			add(label_Name);
			add(label_Age);
			add(label_Time);
			add(label_Price);

			
			btn_Pay = new JButton("계산");
			btn_Pay.setBounds(10, 230, 80, 50);
			btn_Pay.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e){
							if(seatDto != null && memberDto != null){
								if(seatDto.getOnoff() != null){
									JOptionPane.showMessageDialog(null, "계산 완료");
									dao.setBenefit(memberDto.getName(), memberDto.getId(), price);
									dao.initSeat(seatDto, memberDto);
								}	
							}
							dispose();
						}
					}
					);
			btn_Cancel = new JButton("취소");
			btn_Cancel.setBounds(100, 230, 80, 50);
			btn_Cancel.addActionListener(new ActionListener(){ 
				public void actionPerformed(ActionEvent e){
				dispose();		
					}});
			
			btn_Message = new JButton("메시지");
			btn_Message.setBounds(190, 230, 80, 50);
			btn_Message.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					ServerBackground _sb = sb;
					if(sb == null){
						System.out.println("actionListener 안에서 sb가 널");
					} 
					if(_sb == null){
						System.out.println("actionListener 안에서 sb가 널");
					}
					new FrameChattingServer(_sb);
				}
			});
			add(btn_Pay);
			add(btn_Cancel);
			add(btn_Message);

		}
		
		public void initCalculation(){
			if(seatDto != null){
				label_Number.setText("좌석번호 : " + seatDto.getNumber());
			}
			if(memberDto != null){
				label_Id.setText("아이디 : " + memberDto.getId());
				label_Name.setText("이름 : " + memberDto.getName());
				label_Age.setText("나이 : " + memberDto.getAge());
				long timeoffset = System.currentTimeMillis() - seatDto.getTimestamp().getTime();
				int hour = (int)(timeoffset / 1000 / 60 / 60);
				int minute = (int)((timeoffset / 1000 / 60) % 60);
				
				int price = hour * 1000 + (minute / 6) * 100;
				label_Time.setText("사용시간 : " + hour + " : " + minute);
				label_Price.setText("가격 : " + price);
			}
		}
	}

}
