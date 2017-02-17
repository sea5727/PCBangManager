package com.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import com.model.Setting;

public class FrameEditor extends JFrame implements Runnable, MouseListener, MouseMotionListener{
	
	int seat_number = 0;
	int x;
	int y;
	boolean isPlus = false;
	boolean isMinus = false;
	boolean isSeatPosition = false;
	Image buffImage;
	Graphics buffGraphics;
	
	Thread th;
	Rectangle btn_Rect;
	Rectangle seat_Rect;
	Image img;
	
	ArrayList<Rectangle> btn_List = new ArrayList<Rectangle>();
	ArrayList<Rectangle> seat_List = new ArrayList<Rectangle>();
	
	ArrayList<Image> img_List = new ArrayList<Image>();
	
	public FrameEditor(){
		setTitle("Edit Seat");
		setSize(Setting.bDimen);
		setUndecorated(true);
		setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		layeredPane.setBounds(Setting.bRect);
		
		btn_Rect = new Rectangle(Setting.bEdit_BtnField0);
		btn_List.add(btn_Rect);
		btn_Rect = new Rectangle(Setting.bEdit_BtnField1);
		btn_List.add(btn_Rect);
		btn_Rect = new Rectangle(Setting.bEdit_BtnField2);
		btn_List.add(btn_Rect);
		btn_Rect = new Rectangle(Setting.bEdit_BtnField3);
		btn_List.add(btn_Rect);
		
		img = new ImageIcon(Setting.EditPlus_Img).getImage();
		img_List.add(img);
		img = new ImageIcon(Setting.EditMinus_Img).getImage();
		img_List.add(img);
		img = new ImageIcon(Setting.EditOK_Img).getImage();
		img_List.add(img);
		img = new ImageIcon(Setting.EditCancel_Img).getImage();
		img_List.add(img);
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		
		add(layeredPane);
		setVisible(true);
		start();
	}
	public void start(){
		th = new Thread(this);
		th.start();
	}

	@Override
	public void run() {
		while(true){
			try {
				repaint();
				Thread.sleep(20);	
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	}
	public void paint(Graphics g){
		buffImage = createImage(Setting.width, Setting.height);
		buffGraphics = buffImage.getGraphics();
		drawBackground();
		drawButton();
		drawSeat();
		if(isPlus) drawAdd(x,y);
		g.drawImage(buffImage, 0, 0, this);
	}
	public void drawAdd(int x, int y){
		if(isPlus){
			buffGraphics.drawImage(Setting.SeatOff_Img, x, y, null);
		}
	}
	public void drawBackground(){
		buffGraphics.drawImage(Setting.Manager_Background_Img, 0, 0, null);
	}
	public void drawButton(){
		
		for(int i = 0 ; i < btn_List.size(); i++){
			btn_Rect = (Rectangle) btn_List.get(i);
			img = (Image) img_List.get(i);
			buffGraphics.drawImage(img, btn_Rect.x, btn_Rect.y, null);
		}
	}
	public void drawSeat(){
		for(int i = 0 ; i < seat_number ; i++){
			seat_Rect = (Rectangle) seat_List.get(i);
			buffGraphics.drawImage(Setting.SeatOff_Img, seat_Rect.x, seat_Rect.y, seat_Rect.width, seat_Rect.height, null);
		}
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i = 0 ; i < 4 ; i++){
			btn_Rect = (Rectangle) btn_List.get(i);
			if(btn_Rect.contains(e.getPoint())){
				if(i == 0){
					if(isPlus){
						JOptionPane.showMessageDialog(null, "좌석추가를 취소합니다");
						isPlus = false;
					} else{
						JOptionPane.showMessageDialog(null, "좌석을 추가합니다");
						isPlus = true;
						isMinus = false;
					}
				} else if(i == 1){
					if(isMinus){
						JOptionPane.showMessageDialog(null, "좌석제거를 취소합니다");
						isMinus = false;
					} else{
						JOptionPane.showMessageDialog(null, "좌석을 제거합니다");
						isMinus = true;
						isPlus = false;
						
					}
				} else if(i == 2){
					JOptionPane.showMessageDialog(null, seat_number + "개 확인");
				} else if(i == 3){
					JOptionPane.showMessageDialog(null, seat_number + "개 취소");
					dispose();
				}
			}
		}
		isSeatPosition = false;
		int seat_index = -1;
		for(int i = 0 ; i < seat_number; i++){
			seat_Rect = (Rectangle) seat_List.get(i);
			Rectangle newRect = new Rectangle(seat_Rect.x - seat_Rect.width, seat_Rect.y-seat_Rect.height, seat_Rect.width * 2, seat_Rect.height*2);
			if(newRect.contains(e.getPoint())){
				isSeatPosition = true;
				seat_index = i;
				break;
			}
		}
		if(isPlus && !isSeatPosition){
			if(e.getX() > 90 && e.getX() < 1300 && e.getY() > 70 && e.getY() < 700){
				seat_Rect = new Rectangle(e.getX(), e.getY(), 99, 99);
				seat_List.add(seat_Rect);
				seat_number++;
			}
		}
		if(isMinus && isSeatPosition){
			if(e.getX() > 90 && e.getX() < 1300 && e.getY() > 70 && e.getY() < 700){
				seat_List.remove(seat_index);
				seat_number--;
			}
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args){
		new FrameEditor();
	}
}
