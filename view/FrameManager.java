package com.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.chat.ServerBackground;
import com.model.Setting;
import com.view.panel.PanelButton;
import com.view.panel.PanelClock;
import com.view.panel.PanelSeat;
import com.view.panel.PanelStar;
import com.view.panel.PanelTime;

public class FrameManager extends JFrame implements ActionListener {

	PanelClock panelClock = new PanelClock();
	PanelTime panelTime = new PanelTime();
	PanelStar panelStar = new PanelStar();
	PanelSeat panelSeat;
	PanelButton panelButton = new PanelButton();
	
	
	
	public FrameManager(){
		System.out.println("FrameManager()");
		//setUndecorated(true);
	
		ServerBackground sb = new ServerBackground();
		new Thread(sb).start();
		
		panelSeat = new PanelSeat(sb);
		SetFrame();
		
		Background background = new Background();
		background.setBounds(Setting.bRect);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		layeredPane.setBounds(Setting.bRect);
		
		try{
			setComponents(this, Setting.getInstance());	
		}catch(Exception e){
			System.out.println("Frame() setComponents() " + e.toString());
			e.printStackTrace();
		}
		
		addComponents(layeredPane, panelTime, panelClock, panelStar, panelSeat, panelButton, background);
		//addComponents(layeredPane, panelTime, panelClock, panelStar,  panelButton, background);
		addComponents(this, layeredPane);
		setVisible(true);
		
		
		
	}
	private void SetFrame(){
		
		setTitle(Setting.FrameManagerTitle);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(Setting.zeroPoint);
		setLayout(null);
		setSize(Setting.bDimen);
	}
	private void addComponents(Container to, Component...components) {
		for(Component component : components){
			to.add(component);
		}
	}
	public void setComponents(Object frame, Setting setting) throws Exception{
		Class<?> cls = Class.forName(frame.getClass().getName());
		for(Field field : cls.getDeclaredFields()){
			Object object = field.get(frame);
			if(object instanceof JComponent){
				Rectangle rectangle = (Rectangle) setting.getClass().getDeclaredField(field.getName()).get(setting);
				((JComponent) object).setBounds(rectangle);
				((JComponent) object).setLayout(null);
				((JComponent) object).setOpaque(false);
			}
			if(object instanceof Runnable){
				new Thread((Runnable) object).start();
			}
		}
	}
		
	class Background extends JPanel{
		public void paint(Graphics g){
			g.drawImage(Setting.Manager_Background_Img, 0, 0, null);
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args){
		new FrameManager();
	}
	

}
