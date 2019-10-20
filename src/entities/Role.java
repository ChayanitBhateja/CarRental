package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tbl_role")
	int id;
	
	@Column(name = "tbl_rolename")
	String rolename;
	
	@OneToOne(targetEntity = User.class)
	@JoinColumn(name = "tbl_role_reguser")
	User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public User getFkUser() {
		return user;
	}

	public void setFkUser(User fkUser) {
		this.user = fkUser;
	}

}
