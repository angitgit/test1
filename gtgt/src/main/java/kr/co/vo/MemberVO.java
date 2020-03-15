package kr.co.vo;

import java.util.Date;

public class MemberVO {

	private String userId;
	private String userPass;
	private String userName;
	private Date regDate;              //필드는 private 외부로부터 값변경 보호
	
	public String getUserId() {        //메소드는 public
		return userId;
	}
	public void setUserId(String userId) {      //setter  void
		this.userId = userId;                   //필드매개변수
	}
	public String getUserPass() {               //getter  필드타입
		return userPass;                        //return
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "MemberVO [userId=" + userId + ", userPass=" + userPass + ", userName=" + userName + ", regDate="
				+ regDate + "]";
	}
	
}
