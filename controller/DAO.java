package com.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

public class DAO {

	MemberDTO memberDto = null;
	private String url = "jdbc:mysql://192.168.200.100:3306/PCRoom";
	// private String url = "jdbc:mysql://127.0.0.1:3306/PCRoom";
	private String uid = "test";
	private String upw = "1234";

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	SeatDTO seatDto = null;
	private static DAO instance = new DAO();

	public static DAO getInstance() {
		return instance;
	}

	private DAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean loginTest(String id, String password) {

		boolean flag = false;
		String memberId;
		String memberPassword;
		String memberName;
		int memberAge;
		int memberMileage;
		String memberPhone;

		try {

			conn = DriverManager.getConnection(url, uid, upw);
			sql = "SELECT * from MEMBER where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				memberPassword = rs.getString("password");
				if (memberPassword.equals(password)) {
					memberId = rs.getString("id");
					memberName = rs.getString("name");
					memberAge = rs.getInt("age");
					memberMileage = rs.getInt("Mileage");
					memberPhone = rs.getString("phone");
					memberDto = new MemberDTO(memberId, memberPassword, memberName, memberAge, memberMileage,
							memberPhone);
					flag = true;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return flag;
	}

	public SeatDTO setSeat(String _id) {
		String sql = "UPDATE seat_position SET onoff=?, start_time=? WHERE macaddress=?";
		String sql2 = "SELECT * FROM seat_position WHERE macaddress=?";
		SeatDTO tempSeatDto = null;
		try {
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, _id);
			pstmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			pstmt.setString(3, getMACAddress.getMacAddress());
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, getMACAddress.getMacAddress());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int number = rs.getInt("number");
				int x = rs.getInt("x");
				int y = rs.getInt("y");
				String macaddress = rs.getString("macaddress");
				String onoff = rs.getString("onoff");
				Timestamp timestamp = rs.getTimestamp("start_time");
				tempSeatDto = new SeatDTO(number, x, y, macaddress, onoff, timestamp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		return tempSeatDto;
	}

	public ArrayList<SeatDTO> allocateSeat() {
		ArrayList<SeatDTO> seatDto_List = new ArrayList<SeatDTO>();
		try {
			sql = "SELECT * FROM seat_position";
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				int number = rs.getInt("number");
				int x = rs.getInt("x");
				int y = rs.getInt("y");
				String macaddress = rs.getString("macaddress");
				String onoff = rs.getString("onoff");
				Timestamp timestamp = rs.getTimestamp("start_time");

				seatDto = new SeatDTO(number, x, y, macaddress, onoff, timestamp);
				seatDto_List.add(seatDto);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return seatDto_List;
	}

	public boolean Join(MemberDTO member) {

		boolean flag = false;

		String id = member.getId();
		String password = member.getPassword();
		String name = member.getName();
		int age = member.getAge();
		int mileage = member.getMileage();
		String phone = member.getPhone();

		String sql = "INSERT INTO member VALUES(?,?,?,?,?,?)";

		try {
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setInt(4, age);
			pstmt.setInt(5, mileage);
			pstmt.setString(6, phone);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return flag;
	}

	public MemberDTO searchMemberUsingId(String _id) {
		if (_id == null)
			return null;

		String sql = "SELECT * FROM member WHERE id=?";

		try {
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("password");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				int mileage = rs.getInt("mileage");
				String phone = rs.getString("phone");

				memberDto = new MemberDTO(id, pw, name, age, mileage, phone);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return memberDto;
	}

	public void initSeat(SeatDTO seatDto, MemberDTO memberDto) {
		String sql = "UPDATE seat_position SET onoff=NULL, start_time=NULL WHERE number=?";
		// UPDATE `seat_position` SET `onoff` = NULL, `start_time` = NULL WHERE
		// `seat_position`.`number` = 1
		try {
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seatDto.getNumber());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public SeatDTO isUse(int _number) {
		String sql = "SELECT * FROM seat_position WHERE number=?";
		try {
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, _number);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int number = rs.getInt("number");
				int x = rs.getInt("x");
				int y = rs.getInt("y");
				String macaddress = rs.getString("macaddress");
				String onoff = rs.getString("onoff");
				Timestamp start_time = rs.getTimestamp("start_time");
				seatDto = new SeatDTO(number, x, y, macaddress, onoff, start_time);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return seatDto;
	}

	public MemberDTO getMember() {
		return memberDto;
	}

	public void setBenefit(String name, String id, int money) {
		// INSERT INTO `benefit` (`no`, `id`, `date`, `money`) VALUES (NULL,
		// 'sasea', CURRENT_TIMESTAMP, '1234')
		// String sql = "INSERT INTO benefit (id, money) VALUES(?,?)";
		String sql = "INSERT INTO benefit (date, time, name, id, money) VALUES ( CURDATE(), CURTIME(), ?, ?, ?)";
		try {
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setInt(3, money);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public ArrayList<BenefitDTO> getBenefit(Date _date) {

		String sql = "SELECT * FROM benefit WHERE date = ?";
		ArrayList<BenefitDTO> benefitDTO_List = null;
		try {
			conn = DriverManager.getConnection(url, uid, upw);
			pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, _date);
			rs = pstmt.executeQuery();
			benefitDTO_List = new ArrayList<BenefitDTO>();
			while (rs.next()) {
				int no = rs.getInt("no");
				Date date = rs.getDate("date");
				Time time = rs.getTime("time");
				String name = rs.getString("name");
				String id = rs.getString("id");
				int money = rs.getInt("money");

				BenefitDTO benefitDto = new BenefitDTO(no, date, time, name, id, money);
				benefitDTO_List.add(benefitDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return benefitDTO_List;
	}
}
