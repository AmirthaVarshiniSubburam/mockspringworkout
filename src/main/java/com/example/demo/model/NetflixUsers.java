package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
public class NetflixUsers {
	
	@Id
	@Column(name = "Username")
	String id;
//	@Column(name = "Fullname")
	String name;
//	@Column(name = "Email_Id")
	String emailid;
//	@Column(name = "Mobile_No")
	Long mobno;
//	@Column(name = "Password_E")
	String password;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public Long getMobno() {
		return mobno;
	}
	public void setMobno(Long mobno) {
		this.mobno = mobno;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		
		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		String encryptedPwd = pwdEncoder.encode(password);
		this.password = encryptedPwd;

		
	}
	@Override
	public String toString() {
		return "NetflixUsers [id=" + id + ", name=" + name + ", emailid=" + emailid + ", mobno=" + mobno + ", password="
				+ password + "]";
	}
	public NetflixUsers(String id, String name, String emailid, Long mobno, String password) {
		super();
		this.id = id;
		this.name = name;
		this.emailid = emailid;
		this.mobno = mobno;
		this.password = password;
	}
	public NetflixUsers() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(emailid, id, mobno, name, password);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NetflixUsers other = (NetflixUsers) obj;
		return Objects.equals(emailid, other.emailid) && Objects.equals(id, other.id)
				&& Objects.equals(mobno, other.mobno) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password);
	}
	
	
	
	
	

}
