package com.view.panel;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.model.Setting;

public class PanelClock extends JPanel implements Runnable{

	Image img[];
	int i = 2;
	
	public PanelClock(){
		img = new Image[4];
		try{
			img[1] = Setting.ManagerClock1_Img;
			img[2] = Setting.ManagerClock2_Img;
			img[3] = Setting.ManagerClock3_Img;
			img[0] = img[1];
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(img[0], 0, 0, this);
	}
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(1000);
				i = (i == 3) ? 1 : ++i;
				img[0] = img[i];
				repaint();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
	}

}
