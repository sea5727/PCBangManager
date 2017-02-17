package com.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.controller.DAO;
import com.controller.MemberDTO;
import com.model.Setting;

public class FrameJoin extends JFrame {
	
	JButton btn_OK;
	JButton btn_Cancel;
	
	MemberDTO memberDto;
	public FrameJoin(){
		setTitle("회원가입");
		setSize(300,350);
		setLayout(null);
		setLocation(Setting.width/4, Setting.height/4);
		
		PanelJoin panel = new PanelJoin();
		add(panel);
		
		btn_OK = new JButton("확인");
		btn_OK.setBounds(120,260, 70, 40);
		btn_OK.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				memberDto = panel.getMember();
				DAO dao = DAO.getInstance();
				dao.Join(memberDto);
				JOptionPane.showMessageDialog(null, "완료");
				dispose();
			}
		});
		add(btn_OK);
		
		btn_Cancel = new JButton("취소");
		btn_Cancel.setBounds(200, 260, 70, 40);
		btn_Cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		}
				);
		add(btn_Cancel);
		
		
		setVisible(true);
	}
	public static void main(String[] args){
		new FrameJoin();
	}
}
class PanelJoin extends JPanel{
	JLabel label_Id;
	JLabel label_Password;
	JLabel label_Name;
	JLabel label_Age;
	JLabel label_Phone;
	JTextField text_Id;
	JPasswordField text_Password;
	JTextField text_Name;
	JTextField text_Age;
	JTextField text_Phone;
	
	public PanelJoin(){
		setLayout(null);
		setBounds(0,0,300,260);
		
		JLayeredPane layeredPaneLabel = new JLayeredPane();
		layeredPaneLabel.setBounds(0,0,100,260);
		
		label_Id = new JLabel("아 이 디 : ");
		label_Password = new JLabel("비 밀 번 호 : ");
		label_Name = new JLabel("이    름 : ");
		label_Age = new JLabel(" 나    이 : ");
		label_Phone = new JLabel("전 화 번 호 : ");
		
		label_Id.setFont(new Font("돋움", Font.PLAIN, 15));
		label_Id.setBounds(20,10,100,50);
		layeredPaneLabel.add(label_Id);
		
		label_Password.setFont(new Font("돋움", Font.PLAIN, 15));
		label_Password.setBounds(20,60,100,50);
		layeredPaneLabel.add(label_Password);
		
		label_Name.setFont(new Font("돋움", Font.PLAIN, 15));
		label_Name.setBounds(20,110,100,50);
		layeredPaneLabel.add(label_Name);
		
		label_Age.setFont(new Font("돋움", Font.PLAIN, 15));
		label_Age.setBounds(20,160,100,50);
		layeredPaneLabel.add(label_Age);
		
		label_Phone.setFont(new Font("돋움", Font.PLAIN, 15));
		label_Phone.setBounds(20,210,100,50);
		layeredPaneLabel.add(label_Phone);
		
		JLayeredPane layeredPaneText = new JLayeredPane();
		layeredPaneText.setBounds(130,0,150,260);
		
		text_Id = new JTextField();
		text_Id.setBounds(0,25, 150, 20);
		layeredPaneText.add(text_Id);
		
		text_Password = new JPasswordField();
		text_Password.setBounds(0,75, 150, 20);
		layeredPaneText.add(text_Password);
		
		text_Name = new JTextField();
		text_Name.setBounds(0,125, 150, 20);
		layeredPaneText.add(text_Name);
		
		text_Age = new JTextField();
		text_Age.setBounds(0,175, 150, 20);
		layeredPaneText.add(text_Age);
		
		text_Phone = new JTextField();
		text_Phone.setBounds(0,225, 150, 20);
		layeredPaneText.add(text_Phone);

		
		add(layeredPaneLabel);
		add(layeredPaneText);
	}
	public MemberDTO getMember(){
		String id = text_Id.getText();
		char pw[] = text_Password.getPassword();
		String password = new String(pw);
		String name = text_Name.getText();
		int age = Integer.parseInt(text_Age.getText());
		int mileage = 0;
		String phone = text_Phone.getText();
		MemberDTO memberDTO = new MemberDTO(id, password, name, age, mileage, phone);
		return memberDTO;
	}
}
