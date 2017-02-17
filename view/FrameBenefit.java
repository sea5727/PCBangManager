package com.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.controller.BenefitDTO;
import com.controller.DAO;

public class FrameBenefit extends JFrame{
	
	public FrameBenefit(){
		setTitle("수입");
		setLocation(700, 200);
		setSize(300, 430);
		add(new panel());
		setVisible(true);
	}
	public class panel extends JPanel{
		JLabel label_Year;
		JTextField text_Year;
		JLabel label_Month;
		JTextField text_Month;
		JLabel label_Day;
		JTextField text_Day;
		JLabel label_Today;
		JCheckBox checkbox_Today;
		
		JButton btn_Benefit;
		
		JLabel label_Menu;
		
		JScrollPane scrollPane;
		JTextArea textarea_Result;
		
		JTextField text_Total;
		JLabel label_Total;
		
		ArrayList<BenefitDTO> benefitDto_List;
		BenefitDTO benefitDto;
		
		DAO dao = DAO.getInstance();
		public panel(){
			setLayout(null);
			label_Year = new JLabel("년");
			label_Year.setBounds(60,10,15,15);
			text_Year = new JTextField(4);
			text_Year.setBounds(20, 10, 40, 15);
			
			label_Month = new JLabel("월");
			label_Month.setBounds(120,10,15,15);
			text_Month = new JTextField(2);
			text_Month.setBounds(90, 10, 30, 15);
			
			label_Day = new JLabel("일");
			label_Day.setBounds(180,10,15,15);
			text_Day = new JTextField(2);
			text_Day.setBounds(150, 10, 30, 15);
			
			label_Today = new JLabel("오늘");
			label_Today.setBounds(240,10,35,15);
			
			checkbox_Today = new JCheckBox("오늘", true);
			checkbox_Today.setBounds(220,10,20,15);
			checkbox_Today.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent e){
							if(checkbox_Today.isSelected() == true){
								text_Year.setEditable(false);
								text_Month.setEditable(false);
								text_Day.setEditable(false);
								text_Year.setText("");
								text_Month.setText("");
								text_Day.setText("");
							} else {
								text_Year.setEditable(true);
								text_Month.setEditable(true);
								text_Day.setEditable(true);
								text_Year.setText("");
								text_Month.setText("");
								text_Day.setText("");
							}
						}
					}
					);
			
			btn_Benefit = new JButton("수입보기");
			btn_Benefit.setBounds(10,30,100,40);
			btn_Benefit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					if(checkbox_Today.isSelected() == true){
						Date date = new Date(System.currentTimeMillis());
						selectBenefit(date);
					} else {
						boolean flag = true;
						try {
							Integer.parseInt(text_Year.getText());
							Integer.parseInt(text_Month.getText());
							Integer.parseInt(text_Day.getText());	
						} catch (Exception e2) {
							flag = false;
						}
						
						if(flag){
							String stringDate = text_Year.getText() + "-" + text_Month.getText() + "-" + text_Day.getText();
							java.sql.Date date = java.sql.Date.valueOf(stringDate);
							selectBenefit(date);
						} else {
							JOptionPane.showMessageDialog(null, "숫자를 입력하세요");
						}
				
					}
					}});
			
			add(label_Year);
			add(text_Year);
			add(label_Month);
			add(text_Month);
			add(label_Day);
			add(text_Day);
			add(label_Today);
			add(checkbox_Today);
			add(btn_Benefit);
			
			
			label_Menu = new JLabel("날짜     시간        이름         아이디      요금");
			label_Menu.setBounds(10, 70, 260, 20 );
			
			
			textarea_Result = new JTextArea();
			
			
			textarea_Result.setEditable(false);
			text_Year.setEditable(false);
			text_Month.setEditable(false);
			text_Day.setEditable(false);

			scrollPane = new JScrollPane(textarea_Result);
			scrollPane.setBounds(10,90, 260, 200);			
			
			add(label_Menu);
			add(scrollPane);
			
			text_Total = new JTextField(200);
			text_Total.setBounds(10, 310, 270, 50);
			text_Total.setEditable(false);
			label_Total = new JLabel("총 수입");
			label_Total.setBounds(10, 280, 100,30);
			
			
			add(label_Total);
			add(text_Total);
			
			
		}
		public void selectBenefit(Date date){
			int total_money = 0;
			
			textarea_Result.setText(null);
			
			benefitDto_List = dao.getBenefit(date);
			for(int i = 0 ; i < benefitDto_List.size(); i++){
				benefitDto = benefitDto_List.get(i);
				String result = benefitDto.getDate().toString() + " " 
				+ benefitDto.getTime().toString() + " "
				+ benefitDto.getId() + " " 
				+ benefitDto.getMoney() + "\n";
				textarea_Result.append(result);
				total_money += benefitDto.getMoney();
			}
			
			text_Total.setText(String.valueOf(total_money));
		}
		
	}
	public static void main(String[] args){
		new FrameBenefit();
	}

}
