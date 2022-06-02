package logon02;

import java.sql.Timestamp;
import java.util.Date;

public class LogonDTO {
	private String u_id;
	private String u_pass;
	private String u_name;
	private Timestamp r_date;
	private String u_address;
	private String u_tel;
	private String u_birthdate;
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getU_pass() {
		return u_pass;
	}
	public void setU_pass(String u_pass) {
		this.u_pass = u_pass;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public Timestamp getR_date() {
		return r_date;
	}
	public void setR_date(Timestamp r_date) {
		this.r_date = r_date;
	}
	public String getU_address() {
		return u_address;
	}
	public void setU_address(String u_address) {
		this.u_address = u_address;
	}
	public String getU_tel() {
		return u_tel;
	}
	public void setU_tel(String u_tel) {
		this.u_tel = u_tel;
	}
	public String getU_birthdate() {
		return u_birthdate;
	}
	public void setU_birthdate(String u_birthdate) {
		this.u_birthdate = u_birthdate;
	}
	
	
}
