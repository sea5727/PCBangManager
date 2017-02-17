package com.view.panel;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.model.Setting;

public class PanelTime extends JPanel implements Runnable{
	
	int i;
	String[] ampm = {"AM", "PM"};
	SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
	String time = dateFormat.format(new Date());
	JLabel timeLabel, ampmLabel;
	
	public PanelTime(){
		
		setLayout(null);
		i = Calendar.getInstance().get(Calendar.AM_PM);
		timeLabel = new JLabel(time);
		timeLabel.setForeground(Setting.bColor);
		timeLabel.setFont(Setting.bFont);
		timeLabel.setBounds(Setting.bTimeLabel);
		
		ampmLabel = new JLabel(ampm[i]);
		ampmLabel.setForeground(Setting.bColor);
		ampmLabel.setFont(Setting.bFont);
		ampmLabel.setBounds(Setting.bAmpmLabel);
		
		add(timeLabel, BorderLayout.NORTH);
		add(ampmLabel, BorderLayout.CENTER);
	}

	@Override
	public void run() {
		while(true){
			try{
				Thread.sleep(1000);
			}catch(Exception e){
				e.printStackTrace();
			}
			timeLabel.setText(dateFormat.format(new Date()));
		}
		
	}
	
	
}
