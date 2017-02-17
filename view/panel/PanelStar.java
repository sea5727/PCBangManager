package com.view.panel;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.model.Setting;

public class PanelStar extends JPanel implements Runnable{
	int i;
	int x;
	int y;
	public PanelStar(){
		i = 1;
		x = 60;
		y = 40;
		
	}
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(Setting.StarDdong_Img, x, y, this);
	}
	@Override
	public void run() {
		try{
			while(true){
				switch(i){
				case 1: y += 2; if(y > 670) i = 2; break;
				case 2: x += 2; if(x > 1280) i = 3; break;
				case 3: y -= 2; if(y < 41) i = 4; break;
				case 4: x -= 2; if(x < 61) i = 1; break;
				}
				repaint();
				Thread.sleep(20);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
