package cn.net.nit.fs.model;

public class User {
	private String userId;
	private String userName;
	/** �Ա�'1'-�У�'0'-Ů */
	private char sex;
	private String password;
	/** �༶��� */
	private String grade;
	/** �ֻ����� */
	private String cell;
	private String email;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public char getSex() {
		return sex;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getGrade() {
		return grade;
	}
	public void setCell(String cell) {
		this.cell = cell;
	}
	public String getCell() {
		return cell;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
}
