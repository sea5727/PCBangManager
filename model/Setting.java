package com.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Setting {
	private static Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int width = (int) windowSize.getWidth();
	public static int height = (int) windowSize.getHeight();
	public static Point zeroPoint = new Point(0,0);
	

	
	//Text
	/*	Inner Field of FrameLogin  "Login_" means FrameLogin  */
	/** Inner Field of Login Btn and TextField**/
	/** Each valuables have a name behind 'b' because theses like btn are overlapping with other Frame**/
	//LoginField Setting
	private static Point bLogin_LoginPoint = new Point(width/2 - 50, height/2 - 45);
	private static Dimension bLogin_LoginSize = new Dimension(200, 30);
	public static Rectangle bLogin_LoginTextField = new Rectangle( bLogin_LoginPoint, bLogin_LoginSize);
	
	//Password Field Setting
	private static Point bLogin_PassPoint = new Point(width/2 - 50, height/2 +65);
	private static Dimension bLogin_PassSize = new Dimension(200, 30);
	public static Rectangle bLogin_PasswordField = new Rectangle(bLogin_PassPoint, bLogin_PassSize);
	
	//Loingbtn Field Setting
	private static Point bLogin_LoginBtnPoint = new Point(width/2 - 50, height/2 +175);
	private static Dimension bLogin_LoginBtnSize = new Dimension(104, 48);
	public static Rectangle bLogin_LoginBtnField = new Rectangle(bLogin_LoginBtnPoint, bLogin_LoginBtnSize);
	
	//bLogin_JoinBtnField
	private static Point bLogin_JoinBtnPoint = new Point(width/2 - 50, height/2 + 225);
	private static Dimension bLogin_JoinBtnSize = new Dimension(104, 48);
	public static Rectangle bLogin_JoinBtnField = new Rectangle(bLogin_JoinBtnPoint, bLogin_JoinBtnSize);
	
	
	public static Rectangle bBtn = new Rectangle();
	/*	Inner Field of FrameLogin  "Login_" means FrameLogin  */
	
	
	/*	Inner Field of FrameManager  "Manager_" means FrameManager  */
	/** Inner Field of Manager Background**/
	//prefix 'b' means Basic setting
	public static Font bFont = new Font("배달의민족 한나", Font.BOLD, 12);
	public static Color bColor = new Color(36, 205, 198);
	public static Point bPoint = new Point(width, height);
	public static Dimension bDimen = new Dimension(width, height);
	public static Rectangle bRect = new Rectangle(zeroPoint, bDimen);
	
	//panel Location
	public static Rectangle panelClock = new Rectangle(13, 62, 179, 149);
	public static Rectangle panelTime = new Rectangle(65, 85, 100, 100);
	public static Rectangle panelStar = new Rectangle(windowSize);
	public static Rectangle panelSeat = new Rectangle(100, 100, 1200, 500);
	public static Rectangle panelButton = new Rectangle(width - 600, 5, 400, 60);
	
	// Label Location
	public static Rectangle bAmpmLabel = new Rectangle(15, 20, 100, 30);
	public static Rectangle bTimeLabel = new Rectangle(0, 0, 100, 20);
	/** Inner Field of Manager Background**/
	
	/** Inner Field of Manager Seat and Btn**/
	/** Each valuables have a name behind 'b' because theses like btn are overlapping with other Frame**/
	//btn Location
	public static int BUTTON_NUMBER = 4;
	
	public static Point bManager_BtnPoint0 = new Point(0, 0);
	public static Point bManager_BtnPoint1 = new Point(100,0);
	public static Point bManager_BtnPoint2 = new Point(200,0);
	public static Point bManager_BtnPoint3 = new Point(300,0);
	
	public static Dimension bManager_BtnSize = new Dimension(100, 60);
	
	public static Rectangle bManager_BtnField0 = new Rectangle(bManager_BtnPoint0, bManager_BtnSize);
	public static Rectangle bManager_BtnField1 = new Rectangle(bManager_BtnPoint1, bManager_BtnSize);
	public static Rectangle bManager_BtnField2 = new Rectangle(bManager_BtnPoint2, bManager_BtnSize);
	public static Rectangle bManager_BtnField3 = new Rectangle(bManager_BtnPoint3, bManager_BtnSize);
	
	//seat Location
	public static int MAX_SEAT = 100;
	public static int SEAT_WIDTH = 99;
	public static int SEAT_HEIGHT = 99;

	/** Inner Field of Manager Seat and Btn**/
	
	
	

	
	/*	Inner Field of FrameManager  "Manager_" means FrameManager  */
	

	
	
	/* Inner FIeld of Edit Seat */
	public static int EDIT_BUTTON_NUMBER = 4;
	
	public static Point bEdit_BtnPoint0 = new Point(800, 0);
	public static Point bEdit_BtnPoint1 = new Point(900,0);
	public static Point bEdit_BtnPoint2 = new Point(1000,0);
	public static Point bEdit_BtnPoint3 = new Point(1100,0);
	
	public static Dimension bEdit_BtnSize = new Dimension(100, 60);
	
	public static Rectangle bEdit_BtnField0 = new Rectangle(bEdit_BtnPoint0,  bEdit_BtnSize);
	public static Rectangle bEdit_BtnField1 = new Rectangle(bEdit_BtnPoint1,  bEdit_BtnSize);
	public static Rectangle bEdit_BtnField2 = new Rectangle(bEdit_BtnPoint2,  bEdit_BtnSize);
	public static Rectangle bEdit_BtnField3 = new Rectangle(bEdit_BtnPoint3,  bEdit_BtnSize);
	
	/* Inner FIeld of Edit Seat */
	
	//Image
	
	//prefix 'l' means "Location" setting
	public static String ManagerImg_Path = "img/mainHud_back.png";
	public static String LoginImg_Path = "img/login.png";
	public static String LoginButton_Path = "img/btLogin_hud.png";
	public static String ManagerBtn_Path0 = "img/bt_navi_0.png";
	public static String ManagerBtn_Path1 = "img/bt_navi_1.png";
	public static String ManagerBtn_Path2 = "img/bt_navi_2.png";
	public static String ManagerBtn_Path3 = "img/bt_navi_3.png";
	public static String ManagerClock_Path1 = "img/cl1.png";
	public static String ManagerClock_Path2 = "img/cl2.png";
	public static String ManagerClock_Path3 = "img/cl3.png";
	public static String ManagerSeatOff_Path= "img/gameOff.png";
	public static String ManagerSeatOn_Path = "img/gameOn.png";
	public static String ManagerCheck_Path = "img/check.png";
	public static String StarDdong_Path = "img/starDdong.png";
	public static String EditPlus_Path = "img/plus.png";
	public static String EditMinus_Path = "img/minus.png";
	public static String EditOK_Path = "img/ok.png";
	public static String EditCancel_Path = "img/cancel.png";
	//Title
	public static String FrameManagerTitle = "Manager";
	public static String FrameLoginTitle = "Login";
	
	public static Image Manager_Background_Img = new ImageIcon(ManagerImg_Path).getImage();
	public static Image Login_Background_Img = new ImageIcon(LoginImg_Path).getImage();
	public static ImageIcon Login_Button_Img = new ImageIcon(LoginButton_Path);
	public static ImageIcon ManagerBtn0_Img = new ImageIcon(ManagerBtn_Path0);
	public static ImageIcon ManagerBtn1_Img = new ImageIcon(ManagerBtn_Path1);
	public static ImageIcon ManagerBtn2_Img = new ImageIcon(ManagerBtn_Path2);
	public static ImageIcon ManagerBtn3_Img = new ImageIcon(ManagerBtn_Path3);
	public static Image ManagerClock1_Img = new ImageIcon(ManagerClock_Path1).getImage();
	public static Image ManagerClock2_Img = new ImageIcon(ManagerClock_Path2).getImage();
	public static Image ManagerClock3_Img = new ImageIcon(ManagerClock_Path3).getImage();
	public static Image ManagerCheck_Img = new ImageIcon(ManagerCheck_Path).getImage();
	public static Image SeatOff_Img = new ImageIcon(ManagerSeatOff_Path).getImage();
	public static Image SeatOn_Img = new ImageIcon(ManagerSeatOn_Path).getImage();
	public static Image StarDdong_Img = new ImageIcon(StarDdong_Path).getImage();
	public static Image EditPlus_Img = new ImageIcon(EditPlus_Path).getImage();
	public static Image EditMinus_Img = new ImageIcon(EditMinus_Path).getImage();
	public static Image EditOK_Img = new ImageIcon(EditOK_Path).getImage();
	public static Image EditCancel_Img = new ImageIcon(EditCancel_Path).getImage();
	
	
	private static Setting instance = new Setting();
	
	public static Setting getInstance(){
		return instance;
	}
	
	
	
}
