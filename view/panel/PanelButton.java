package com.view.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.model.Setting;
import com.view.FrameBenefit;
import com.view.FrameCalculation;
import com.view.FrameEditor;

public class PanelButton extends JPanel implements ActionListener{

	JButton[] btn = new JButton[4];
	
	
	public PanelButton(){
		
		btn[0] = new JButton(Setting.ManagerBtn0_Img);
		btn[1] = new JButton(Setting.ManagerBtn1_Img);
		btn[2] = new JButton(Setting.ManagerBtn2_Img);
		btn[3] = new JButton(Setting.ManagerBtn3_Img);
		
		btn[0].setBounds(Setting.bManager_BtnField0);
		btn[1].setBounds(Setting.bManager_BtnField1);
		btn[2].setBounds(Setting.bManager_BtnField2);
		btn[3].setBounds(Setting.bManager_BtnField3);
		
		for(int i = 0 ; i < Setting.BUTTON_NUMBER ; i++){
			btn[i].setBorderPainted(false);
			btn[i].setFocusPainted(false);
			btn[i].setContentAreaFilled(false);
			btn[i].addActionListener(this);
			add(btn[i]);
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn[0]){
			new FrameEditor();
		}else if(e.getSource() == btn[1]){
			new FrameCalculation(null, null, null);
		}else if(e.getSource() == btn[2]){
			JOptionPane.showMessageDialog(null, "BTN2");
		}else if(e.getSource() == btn[3]){
			new FrameBenefit();
		}
	}
	

}
