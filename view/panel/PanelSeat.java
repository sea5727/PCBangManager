package com.view.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.chat.ServerBackground;
import com.controller.DAO;
import com.controller.MemberDTO;
import com.controller.SeatDTO;
import com.model.Setting;
import com.view.FrameCalculation;

public class PanelSeat extends JPanel {
	
	DAO dao = DAO.getInstance();
	SeatDTO seatDto;
	MemberDTO memberDto;
	ArrayList<SeatDTO> seatDto_List = new ArrayList<SeatDTO>();
	
	JButton btn;
	ArrayList<JButton> btn_List = new ArrayList<JButton>();
	
	ServerBackground sb;
	public PanelSeat(ServerBackground sb){
		
		this.sb = sb;
		if(sb == null){
			System.out.println("panelseat() sb는 널");
		}
		System.out.println("PanelSeat()");
		seatDto_List = dao.allocateSeat();
		
		for(int i = 0 ; i < seatDto_List.size(); i++){
			
			seatDto = (SeatDTO) seatDto_List.get(i);
			SeatDTO _seatDto = seatDto;
			
			if(seatDto.getOnoff() != null){
				btn = new JButton(new ImageIcon(Setting.SeatOn_Img));	
			} else{
				btn = new JButton(new ImageIcon(Setting.SeatOff_Img));
			}
			
			btn.setBounds(seatDto.getX(), seatDto.getY(), 100, 100);
			btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					seatDto = dao.isUse(_seatDto.getNumber());
					if(seatDto.getOnoff() == null){
						new FrameCalculation(null, seatDto, null);
					} else{
						memberDto = dao.searchMemberUsingId(seatDto.getOnoff());
						new FrameCalculation(memberDto, seatDto, sb);	
					}
					
				}
			});
			add(btn);
		}
		
	}
}
