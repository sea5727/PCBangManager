package com.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.controller.DAO;
import com.controller.SeatDTO;
import com.model.Setting;

public class FrameLogin extends JFrame implements ActionListener{
	
	DAO dao = DAO.getInstance();
	
	private JTextField loginTextField;
	private JPasswordField passwordField;
	private JButton loginBtn;
	private JButton joinBtn;
	
	public FrameLogin(){
		System.out.println("FrameLogin()");
		//setUndecorated(true);
		setTitle(Setting.FrameLoginTitle);
		setSize(Setting.bDimen);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		layeredPane.setBounds(Setting.bRect);
		
		Background background = new Background();
		background.setBounds(Setting.bRect);
		
		loginTextField = new JTextField(15);
		loginTextField.setBounds(Setting.bLogin_LoginTextField);
		loginTextField.setForeground(Color.green);
		loginTextField.setOpaque(false);
		loginTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		passwordField = new JPasswordField(15);
		passwordField.setBounds(Setting.bLogin_PasswordField);
		passwordField.setForeground(Color.green);
		passwordField.setOpaque(false);
		passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		loginBtn = new JButton(Setting.Login_Button_Img);
		loginBtn.setBounds(Setting.bLogin_LoginBtnField);
		loginBtn.setBorderPainted(false);
		loginBtn.setFocusPainted(false);
		loginBtn.setContentAreaFilled(false);
		loginBtn.addActionListener(this);
		
		joinBtn = new JButton("회원가입");
		joinBtn.setBounds(Setting.bLogin_JoinBtnField);
		//joinBtn.setBorderPainted(false);
		//joinBtn.setFocusPainted(false);
		//joinBtn.setContentAreaFilled(false);
		joinBtn.addActionListener(this);
		
		
		
		layeredPane.add(loginTextField);
		layeredPane.add(passwordField);
		layeredPane.add(loginBtn);
		layeredPane.add(joinBtn);
		layeredPane.add(background);
		add(layeredPane);
		setVisible(true);
		
		
	}
	class Background extends JPanel{
		public void paint(Graphics g){
			g.drawImage(Setting.Login_Background_Img, 0, 0, null);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		SeatDTO tempSeatDto = null;
		if(e.getSource() == loginBtn){
			String id = loginTextField.getText();
			char[] pass = passwordField.getPassword();
			String password = new String(pass);
			if(loginTextField.getText().equals("") || passwordField.getPassword().equals("")){
				JOptionPane.showMessageDialog(null, "아이디나 비밀번호를 입력하세요");
			} else {
				boolean existLogin = dao.loginTest(id, password);
			
				if(existLogin){
					JOptionPane.showMessageDialog(null, "로그인 성공");
					if(dao.getMember().getId().equals("root")){
						
						new FrameManager();
					} else{
						tempSeatDto = dao.setSeat(dao.getMember().getId());
						new FramePC(dao.getMember(), tempSeatDto);
					}
					dispose();					
				} else {
					JOptionPane.showMessageDialog(null, "로그인 실패");
				}
			}
		} else if(e.getSource() == joinBtn){
			new FrameJoin();
		}
	}
	public static void main(String[] args){
		new FrameLogin();
	}
}



/*final class getMACAddress{
	public static String getShortMacAddress() {
        String value = "";

        try {
            value = getMacAddress();
        } catch (IOException e) {
            e.printStackTrace();
        }
        value = value.replaceAll("-", "");

        return value;
    }


    public final static String getMacAddress() throws IOException {
        String os = System.getProperty("os.name");

        if (os.startsWith("Windows")) {
            return ParseMacAddress(windowsRunIpConfigCommand());
        } else if (os.startsWith("Linux")) {
            return ParseMacAddress(linuxRunIfConfigCommand());
        } else {
            throw new IOException("unknown operating system: " + os);
        }

    }

 

    private final static String linuxRunIfConfigCommand() throws IOException {
        Process p = Runtime.getRuntime().exec("ifconfig");
        InputStream stdoutStream = new BufferedInputStream(p.getInputStream());

        StringBuffer buffer = new StringBuffer();
        for (;;) {
            int c = stdoutStream.read();
            if (c == -1)
                break;
            buffer.append((char) c);
        }
        String outputText = buffer.toString();

        stdoutStream.close();

        return outputText;
    }



    private final static String windowsRunIpConfigCommand() throws IOException {
        Process p = Runtime.getRuntime().exec("ipconfig /all");
        InputStream stdoutStream = new BufferedInputStream(p.getInputStream());

        StringBuffer buffer = new StringBuffer();
        for (;;) {
            int c = stdoutStream.read();
            if (c == -1)
                break;
            buffer.append((char) c);
        }
        String outputText = buffer.toString();

        stdoutStream.close();

        return outputText;
    }


    public static String ParseMacAddress(String text) {
        String result = null;
        String[] list = text.split("\\p{XDigit}{2}(-\\p{XDigit}{2}){5}");
        int index = 0;
        for (String str : list) {
            if (str.length() < text.length()) {
                index = str.length();
                result = text.substring(index, index + 17);
                if (!result.equals("00-00-00-00-00-00")) {
                    break;
                }
                text = text.substring(index + 17);

            }
        }
        return result;
    }
   
    public static void main(String[] args)throws IOException {
        System.out.print("MAC Address :: ");
        System.out.println(getMACAddress.getMacAddress());
    }

}
*/
	
