package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;


@Entity
@Table(name = "tbl_reguser")
@SecondaryTable(name = "tbl_user_details", pkJoinColumns = @PrimaryKeyJoinColumn(name = "tbl_user_details_fk_reguser", referencedColumnName = "idtbl_reguser"))

public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idtbl_reguser")
	@PrimaryKeyJoinColumn
	int id;
	
	@Column(name = "tbl_reguser_name")
	String name;
	
	@Column(name = "tbl_reguser_username")
	String username;
	
	@Column(name = "tbl_regusercol")
	String password;
	
	@Column(name = "tbl_user_details_mobile", table="tbl_user_details")
	String mobileno;
	
	@Column(name = "tbl_user_details_aadharno", table="tbl_user_details")
	String aadharno;
	
	@Column(name = "tbl_user_details_email", table="tbl_user_details")
	String email;
	
	@Column(name = "tbl_user_details_liscenseno", table="tbl_user_details")
	String licenseno;
	
	@OneToOne(mappedBy="user")
	Role role;
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getAadharno() {
		return aadharno;
	}
	public void setAadharno(String aadharno) {
		this.aadharno = aadharno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLicenseno() {
		return licenseno;
	}
	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", mobileno="
				+ mobileno + ", aadharno=" + aadharno + ", email=" + email + ", licenseno=" + licenseno + ", role="
				+ role + "]";
	}
	
	
	
}
